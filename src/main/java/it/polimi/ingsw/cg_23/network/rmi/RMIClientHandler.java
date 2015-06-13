package it.polimi.ingsw.cg_23.network.rmi;

import it.polimi.ingsw.cg_23.model.players.Alien;
import it.polimi.ingsw.cg_23.model.players.Human;
import it.polimi.ingsw.cg_23.model.players.Player;
import it.polimi.ingsw.cg_23.model.status.GameState;
import it.polimi.ingsw.cg_23.model.status.Match;
import it.polimi.ingsw.cg_23.network.ServerStatus;
import it.polimi.ingsw.cg_23.network.socket.SocketBroker;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Timer;
import java.util.TimerTask;

public class RMIClientHandler implements RMIClientHandlerInterface {
    
    /**
     * Where to save the server status object
     */
    private ServerStatus serverStatus;
    
    /**
     * Generic error message
     */
    private static final String ERROR_MESSAGE = "Cannot send message to client!";
    
    /**
     * Where to save the interface conaining all the game commands
     */
    private RMIGameCommandsInterface gameInterface;

    /**
     * Construnctor. Gets the only instance of server status
     */
    public RMIClientHandler() {
        serverStatus = ServerStatus.getInstance();
    }

    /**
     * Method called from remote.<br>
     * Gets the game list from the server status and returns it to the client
     */
    @Override
    public void getGameList(RMIClientInterface clientInterface) {
        
        System.out.println("SERVER: Received Command: getGameList");
        
        try {
            clientInterface.dispatchMessage("This maps are playable: Galilei, Galvani, Fermi");
        } catch (RemoteException e) {
            System.err.println(ERROR_MESSAGE);
        }
        
    }
    
    /**
     * Checks if the specified id is already in a match
     * 
     * @param id the name identifier of a client
     * @return true if the client is already associated with a match, false otherwise
     */
    private boolean checkIdIfPresent(String id){
        if(!serverStatus.getIdMatchMap().containsKey(id))
            return true;
        return false;
    }
    

    /**
     * Method called from remote.<br>
     * First checks if a player is already in a game, if not, creates a new match if non available or makes the client join a match with the specified map.
     */
    @Override
    public RMIGameCommandsInterface joinMatch(String id, String mapName, RMIClientInterface clientInterface) throws RemoteException {//TODO synchronized

        gameInterface = new RMIGameCommands();

        if(!checkIdIfPresent(id)){
            try {
                clientInterface.dispatchMessage("You are already in a game!");
                return gameInterface;
            } catch (RemoteException e) {
                System.err.println(ERROR_MESSAGE);
            }
        }
        if("galilei".equals(mapName) || "fermi".equals(mapName) || "galvani".equals(mapName)){
            
            for (Match match : serverStatus.getMatchRMIBrokerMap().keySet()) {
                if(match.getName().equals(mapName) && match.getMatchState() == GameState.WAITING && match.getPlayers().size()<8){
                    joinGame(id, clientInterface, match, serverStatus.getMatchRMIBrokerMap().get(match));
                    try {
                        clientInterface.dispatchMessage("You were added to a game with map "+mapName);
                    } catch (RemoteException e) {
                        System.err.println(ERROR_MESSAGE);
                    }
                }
                else{
                    joinNewGame(id, clientInterface, mapName);
                    try {
                        clientInterface.dispatchMessage("You were added to a new game with map "+mapName);
                    } catch (RemoteException e) {
                        System.err.println(ERROR_MESSAGE);
                    }
                }
            }
            
            if(serverStatus.getMatchRMIBrokerMap().isEmpty()){
                joinNewGame(id, clientInterface, mapName);
                try {
                    clientInterface.dispatchMessage("You were added to a new game with map "+mapName);
                } catch (RemoteException e) {
                    System.err.println(ERROR_MESSAGE);
                }
            }
        } else
            try {
                clientInterface.dispatchMessage("Map "+mapName+" not implemented!");
            } catch (RemoteException e) {
                System.err.println(ERROR_MESSAGE);
            }
        
        
        
        return (RMIGameCommandsInterface) UnicastRemoteObject.exportObject(gameInterface,0);
    }
    
    /**
     * Makes a client join a already created match which is waiting for more players.
     * 
     * @param id the unique name of the client
     * @param clientInterface the interface of the client that permits to send messages
     * @param match the match to be joined
     * @param broker the broker associated to the match that is joined
     */
    private void joinGame(String id, RMIClientInterface clientInterface, Match match, RMIBroker broker){
        
        broker.subscribe(clientInterface);
        
        Player newPlayer;
        
        int nAlien = 0; //number of aliens in the match
        
        int nHuman = 0;//number of humans in the match
        
        for (Player player : match.getPlayers()) {
            if(player instanceof Alien){
                nAlien++;
            }
            else
                nHuman++;
        }
        
        if(nAlien>=nHuman)
            newPlayer = new Human(id);
        else
            newPlayer = new Alien(id);
            
        match.addNewPlayerToList(newPlayer);
        
        serverStatus.addPlayerToMatch(id, match);
        
    }
    
    /**
     * Makes a client join a new match and sets it waiting for more players.
     * 
     * @param id the unique name of the client
     * @param clientInterface the interface of the client that permits to send messages
     * @param mapName the name of the map where the match is palyed on
     */
    private void joinNewGame(final String id, RMIClientInterface clientInterface, String mapName){
        
        Match match = new Match(mapName);
        
        SocketBroker broker = new SocketBroker(""+mapName+serverStatus.getBrokerNumber());
        
        RMIBroker rmiBroker = new RMIBroker(""+mapName+serverStatus.getBrokerNumber());
        
        rmiBroker.subscribe(clientInterface);
        
        match.addNewPlayerToList(new Alien(id));
        
        match.getGameLogic().setSocketBroker(broker);
        
        match.getGameLogic().setRMIBroker(rmiBroker);
        
        serverStatus.addSocketBrokerToMatch(match, broker);
        
        serverStatus.addRMIBrokerToMatch(match, rmiBroker);

        serverStatus.addPlayerToMatch(id, match);
        
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){

            @Override
            public void run() {
                Match match = serverStatus.getIdMatchMap().get(id);
                if(match.getPlayers().size() > 1){
                    match.getGameLogic().startGame();
                }
                else{
                    System.out.println("SERVER: Cannot start the game");
                    serverStatus.getIdMatchMap().remove(id);
                }
            }
        }, 20000);//20 seconds
    }
}
