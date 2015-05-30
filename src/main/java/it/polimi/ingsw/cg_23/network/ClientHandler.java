package it.polimi.ingsw.cg_23.network;

import it.polimi.ingsw.cg_23.controller.GameLogic;
import it.polimi.ingsw.cg_23.model.map.Sector;
import it.polimi.ingsw.cg_23.model.players.Alien;
import it.polimi.ingsw.cg_23.model.players.Human;
import it.polimi.ingsw.cg_23.model.players.Player;
import it.polimi.ingsw.cg_23.model.status.GameState;
import it.polimi.ingsw.cg_23.model.status.Match;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ClientHandler implements Runnable{
    
    /**
     * The socket of the client
     */
    private Socket socket;
    
    /**
     * The scanner to acquire client inputs
     */
    private Scanner socketIn;
    
    /**
     * The PrintWriter to send messages to the client
     */
    private PrintWriter socketOut;
    
    private ServerStatus serverStatus;
    
    private StringTokenizer tokenizer;
    
    private String id;

    /**
     * Constructor
     * 
     * @param socket
     */
    public ClientHandler(Socket socket) {
        
        this.socket=socket;
        
        serverStatus=ServerStatus.getInstance();
        
        try {
            socketIn=new Scanner(socket.getInputStream());
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        try {
            socketOut = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    @Override
    public void run() {
            
        String line = socketIn.nextLine();
        
        System.out.println("SERVER: getting the command "+line);
        
        tokenizer = new StringTokenizer(line);
        
        id=tokenizer.nextToken();
        
        if(checkId()){
        
            send(parseCommand(tokenizer.nextToken()));
        }
        else send("Name already in use! Enter a new name."); 
        
        close();
    }
    
    /**
     * Checks if the name is already in the list
     * 
     * @return true if name is avaible, false otherwise
     */
    private boolean checkId(){
        if(!serverStatus.getIdMatchMap().containsKey(id))
            return true;
        return false;
    }
    
    private String parseCommand(String msg){//TODO finish this
        
        String response = null;
        
        if(msg == null){
            response="Command not found!";
            return response;
        }
                
        switch(msg.toLowerCase()){
            default:
                response="Command not found!";
                
            case "gamelist":
                response="This maps are playable: Galilei, Galvani, Fermi";
                break;
                
            case "join":
                response=checkGames();
                
            case "move":
                response = movePlayer();
                
            case "use":
                if(!tokenizer.hasMoreTokens()){
                    response="Use sintax: use cardname. Avaible card names are Adrenaline, Attack, Sedatives, Spotlight, Teleport.";
                    break;
                }
                
            case "noise":
                if(!tokenizer.hasMoreTokens()){
                    response="Use sintax: use cardname. Avaible card names are Adrenaline, Attack, Sedatives, Spotlight, Teleport.";
                    break;
                }
                
            
                
                
        
        
        
        }
        return response;
    }
    
    /**
     * Sends Strings to the client
     * 
     * @param msg the string to sent to the client
     */
    private void send(String msg){
        socketOut.println(msg);
        socketOut.flush();
    }
    
    /**
     * Closes the connection with a client
     * 
     */
    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println("ERROR: Cannot close the connection");
        } finally {
            socket = null;
        }
    }
    
    private String checkGames(){
        
        String mapName = tokenizer.nextToken().toLowerCase();
        
        String response = null;
        
        if(mapName != "galilei" || mapName != "fermi" || mapName != "galvani"){
            return "Map "+mapName+" not implemented!";
        }
        
        for (Match match : serverStatus.getMatchBrokerMap().keySet()) {
            if(match.getName() == mapName && match.getMatchState() != GameState.RUNNING){
                joinGame(match, serverStatus.getMatchBrokerMap().get(match));
                response = "You were added to a game with map "+mapName;
            }
            else{
                joinNewGame(mapName);
                response = "You were added to a new game with map "+mapName;
            }
        }
        return response;
    }
    
    private void joinGame(Match match, Broker broker){
        
        BrokerThread brokerThread = new BrokerThread(socket);
        brokerThread.start();
        broker.addSubscriber(new BrokerThread(socket));
        
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
        
    }
    
    private void joinNewGame(String mapName){
        
        Match match = new Match(mapName);
        
        Broker broker = new Broker(""+mapName+serverStatus.getBrokerNumber());
        
        BrokerThread brokerThread = new BrokerThread(socket);
        brokerThread.start();
        broker.addSubscriber(new BrokerThread(socket));
        
        match.addNewPlayerToList(new Alien(id));
        
        serverStatus.addBrokerToMatch(broker, match);
        
        serverStatus.addPlayerToMatch(id, match);
    }
    
    
    private String moveError(){
        return "Move sintax: move letter number. The letter can go from A to W, the number from 1 to 14.";
    }
    
    private String movePlayer(){

        int letter;
        int number;
        
        if(tokenizer.hasMoreTokens())
            letter=Integer.parseInt(tokenizer.nextToken().toLowerCase())-Integer.parseInt("a");
        
        else
            return moveError();
        
        if(tokenizer.hasMoreTokens())
            number=Integer.parseInt(tokenizer.nextToken())-1;

        else
            return moveError();
        
        if(letter<0 || letter>=23 || number <0 || number >=14)
            return moveError();
        
        Match match = serverStatus.getIdMatchMap().get(id);
        
        Sector[][] sector = match.getMap().getSector();
        
        for (Player playerInList : match.getPlayers()) {
            if(playerInList.getName()==id){
                match.getGameLogic().movePlayer(playerInList, sector[letter][number]);
                break;
            }
        }     
        return "You moved in sector "+letter+" "+number;
    }    
}
