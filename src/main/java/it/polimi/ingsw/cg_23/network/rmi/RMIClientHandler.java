package it.polimi.ingsw.cg_23.network.rmi;

import it.polimi.ingsw.cg_23.model.players.Alien;
import it.polimi.ingsw.cg_23.model.players.Human;
import it.polimi.ingsw.cg_23.model.players.Player;
import it.polimi.ingsw.cg_23.model.status.GameState;
import it.polimi.ingsw.cg_23.model.status.Match;
import it.polimi.ingsw.cg_23.network.ServerStatus;
import it.polimi.ingsw.cg_23.network.socket.SocketBroker;

import java.rmi.RemoteException;
import java.util.Timer;
import java.util.TimerTask;

public class RMIClientHandler implements RMIClientHandlerInterface {
    
    private ServerStatus serverStatus;
    
    private static final String messageError = "Cannot send message to client!";
    
    private RMIGameCommandsInterface gameInterface;

    public RMIClientHandler() {
        serverStatus = ServerStatus.getInstance();
    }

    @Override
    public void getGameList(RMIClientInterface clientInterface) {
        try {
            clientInterface.dispatchMessage("This maps are playable: Galilei, Galvani, Fermi");
        } catch (RemoteException e) {
            System.err.println(messageError);
        }
        
    }
    
    
    private boolean checkIdIfPresent(String id){
        if(!serverStatus.getIdMatchMap().containsKey(id))
            return true;
        return false;
    }
    

    @Override
    public RMIGameCommandsInterface joinMatch(String id, String mapName, RMIClientInterface clientInterface) {//TODO synchronized
        if(!checkIdIfPresent(id))
            try {
                clientInterface.dispatchMessage("You are already in a game!");
            } catch (RemoteException e) {
                System.err.println(messageError);
            }
        
        if("galilei".equals(mapName) || "fermi".equals(mapName) || "galvani".equals(mapName)){
            
            for (Match match : serverStatus.getMatchRMIBrokerMap().keySet()) {
                if(match.getName().equals(mapName) && match.getMatchState() == GameState.WAITING && match.getPlayers().size()<8){
                    joinGame(id, clientInterface, match, serverStatus.getMatchRMIBrokerMap().get(match));
                    try {
                        clientInterface.dispatchMessage("You were added to a game with map "+mapName);
                    } catch (RemoteException e) {
                        System.err.println(messageError);
                    }
                }
                else{
                    joinNewGame(id, clientInterface, mapName);
                    try {
                        clientInterface.dispatchMessage("You were added to a new game with map "+mapName);
                    } catch (RemoteException e) {
                        System.err.println(messageError);
                    }
                }
            }
            
            if(serverStatus.getMatchRMIBrokerMap().isEmpty()){
                joinNewGame(id, clientInterface, mapName);
                try {
                    clientInterface.dispatchMessage("You were added to a new game with map "+mapName);
                } catch (RemoteException e) {
                    
                }
            }
        } else
            try {
                clientInterface.dispatchMessage("Map "+mapName+" not implemented!");
            } catch (RemoteException e) {
                System.err.println(messageError);
            }
        return gameInterface;
    }
    
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
