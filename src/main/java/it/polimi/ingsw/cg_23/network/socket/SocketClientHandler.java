package it.polimi.ingsw.cg_23.network.socket;

import it.polimi.ingsw.cg_23.model.cards.AdrenalineCard;
import it.polimi.ingsw.cg_23.model.cards.AttackCard;
import it.polimi.ingsw.cg_23.model.cards.Card;
import it.polimi.ingsw.cg_23.model.cards.DefenseCard;
import it.polimi.ingsw.cg_23.model.cards.SedativesCard;
import it.polimi.ingsw.cg_23.model.cards.SpotlightCard;
import it.polimi.ingsw.cg_23.model.cards.TeleportCard;
import it.polimi.ingsw.cg_23.model.map.Sector;
import it.polimi.ingsw.cg_23.model.players.Alien;
import it.polimi.ingsw.cg_23.model.players.Human;
import it.polimi.ingsw.cg_23.model.players.Player;
import it.polimi.ingsw.cg_23.model.status.GameState;
import it.polimi.ingsw.cg_23.model.status.Match;
import it.polimi.ingsw.cg_23.network.ServerStatus;
import it.polimi.ingsw.cg_23.network.rmi.RMIBroker;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class receive commands from the client and executes them.
 * 
 * 
 * @author Paolo
 *
 */
public class SocketClientHandler implements Runnable{
    
    private static final String NOTINGAME = "You are not in a game! Join one first!";    
    private static final String NOTRUNNING = "Game game is not running!";
    private static final String NOTYOURTURN = "It's not your turn!";
    private static final String CANTUSECARD = "You can't use that card!";
    private static final String SPOTLIGHTSYNTAX = "Spotlight syntax: use spotlight letter number";
    private static final String NOISESYNTAX = "Noise syntax: noise letter number";
    
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
    
    /**
     * Status of all the matches, broker and clients connected.
     */
    private ServerStatus serverStatus;
    
    /**
     * Tokenizer used to parse the received command
     */
    private StringTokenizer tokenizer;
    
    /**
     * Unique id of the client connected
     */
    private String id;

    /**
     * Constructor. Initialize the socket and gets the intance of serverstatus
     * 
     * @param socket
     */
    public SocketClientHandler(Socket socket) {
        
        this.socket=socket;
        
        serverStatus=ServerStatus.getInstance();
        
        try {
            socketIn=new Scanner(socket.getInputStream());
        } catch (IOException e1) {
            e1.printStackTrace();

        }
        
        try {
            socketOut = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Main method of the client handler. Receives the command and calls another method to parse it.<br>
     * When the command is parsed sends a response to the client e closes the connection.
     */
    @Override
    public void run() {
            
        String line = socketIn.nextLine();
        
        System.out.println("CLIENT: Command: "+line);
        
        tokenizer = new StringTokenizer(line);
        
        id=tokenizer.nextToken();
        
        if(tokenizer.hasMoreTokens()){
            send(parseCommand(tokenizer.nextToken()));
        }
        else 
            send("Hi "+id+"! Nothing to do here! Thanks for coming! Bye!");        
    }
    
    /**
     * Checks if the name is already in the list
     * 
     * @return true if name is not in the list, false otherwise
     */
    private boolean checkIdIfPresent(){
        if(!serverStatus.getIdMatchMap().containsKey(id))
            return true;
        return false;
    }
    
    /**
     * Parses the command sent by the client
     * 
     * @param msg the command received
     * @return the response for the client to its command
     */
    private String parseCommand(String msg){
        
        String response = null;
        if(msg == null){
            response="Command not found!";
            return response;
        }
                
        switch(msg.toLowerCase()){
                           
            case "gamelist":
                response="This maps are playable: Galilei, Galvani, Fermi";
                break;
                
            case "join":
                response=checkGames();
                break;
                
            case "move":
                response = movePlayer() + " " + getCards();
                break;
               
            case "moveattack":
                response = moveAndAttack() + " " + getCards();
                break;
                
            case "use":
                response = useCard();
                break;
                
            case "noise":
                response = makeNoise();
                break;
                
            case "discard":
                response = discardCard();
                break;
                     
            case "endturn":
                response = endTurn();
                break;
                
            case "getcards":
                response = getCards();
                break;
                
            default:
                response="Command not found!";
                break; 
        }
        return response;
    }
    

    /**
     * Sends Strings to the client
     * 
     * @param msg the string to sent to the client
     */
    private void send(String msg){
        System.out.println("SERVER: Sending: "+msg);
        socketOut.println(msg);
        socketOut.flush();
    }
    
    /**
     * Checks for games and make a client join one
     * 
     * @return the response for the client to its command
     */
    private synchronized String checkGames(){//TODO check synchronized here
        
        if(!tokenizer.hasMoreTokens())
            return "Join syntax: join mapname";
        
        String mapName = tokenizer.nextToken().toLowerCase();
        
        String response = null;
        
        if(!checkIdIfPresent())
            return "You are already in a game!";
        
        if("galilei".equals(mapName) || "fermi".equals(mapName) || "galvani".equals(mapName)){
            
            for (Match match : serverStatus.getMatchSocketBrokerMap().keySet()) {
                if(match.getName().equals(mapName) && match.getMatchState() == GameState.WAITING && match.getPlayers().size()<8){   
                    response = "You were added to a game with map "+mapName+ " "+joinGame(match, serverStatus.getMatchSocketBrokerMap().get(match));
                }
                else{
                    response = "You were added to a new game with map "+mapName+" "+joinNewGame(mapName);
                }
            }
            
            if(serverStatus.getMatchSocketBrokerMap().isEmpty()){
                response = "You were added to a new game with map "+mapName+" "+joinNewGame(mapName);;
            }
        }
        else
            return "Map "+mapName+" not implemented!";
        
        return response;
    }
    
    /**
     * Makes the client join an already existing game
     * 
     * @param match the match to be joint
     * @param broker the broker linked to the match
     */
    private String joinGame(Match match, SocketBroker broker){
        
        BrokerThread brokerThread = new BrokerThread(socket);
        brokerThread.start();
        broker.addSubscriber(brokerThread);
        
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
        
        if(nAlien>=nHuman)
            return "You are an Human!";
        else
            return "You are an Alien";
    }
    
    /**
     * Makes the client join a fresh match. The match is created and addded to the list of waiting Matches, the the client joins it.
     * 
     * @param mapName the name of the map the palyer has chosen to play
     */
    private String joinNewGame(String mapName){
        
        Match match = new Match(mapName);
        
        SocketBroker broker = new SocketBroker(""+mapName+serverStatus.getBrokerNumber());
        
        RMIBroker rmiBroker = new RMIBroker(""+mapName+serverStatus.getBrokerNumber());
        
        BrokerThread brokerThread = new BrokerThread(socket);
        brokerThread.start();
        broker.addSubscriber(brokerThread);
        
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
        return "You are an Alien";
    }
    
    /**
     * Returns a string for the move error
     * 
     * @return the response for the client to its command
     */
    private String moveError(){
        return "Move syntax: move letter number. The letter can go from A to W, the number from 1 to 14.";
    }
    
    /**
     * Makes a player move in the specified sector if he can go there
     * 
     * @return the response for the client to its command
     */
    private String movePlayer(){
        
        Match match = serverStatus.getIdMatchMap().get(id);
        
        if(checkIdIfPresent())
            return NOTINGAME;
        
        if(!(match.getMatchState()==GameState.RUNNING)){
            return NOTRUNNING;
        }
            
        if(!match.getCurrentPlayer().getName().equalsIgnoreCase(id)){
            return NOTYOURTURN;
        }
            
        int letter;
        int number;
        
        if(tokenizer.hasMoreTokens())
            letter = Character.getNumericValue(tokenizer.nextToken().toLowerCase().charAt(0))-10;
        
        else
            return moveError();
        
        if(tokenizer.hasMoreTokens())
            number=Integer.parseInt(tokenizer.nextToken())-1;

        else
            return moveError();
        
        if(letter<0 || letter>=23 || number <0 || number >=14)
            return moveError();
        
        Sector[][] sector = match.getMap().getSector();
        
        String response = "";
        for (Player playerInList : match.getPlayers()) {
            if(playerInList.getName().equals(id)){
                if(!(playerInList.needSectorNoise() || playerInList.hasFourCard() || playerInList.hasMoved())){
                    if(match.getGameLogic().validMove(playerInList, sector[letter][number])){
                        response = match.getGameLogic().movePlayer(playerInList, sector[letter][number]);
                        response = "You moved in sector "+letter+" "+number+ " " +response;
                        break;
                    }
                    else 
                        return "You can't move there!";
                } else {
                    if(playerInList.needSectorNoise())
                        response = response + " " + "You need to specify a sector where make a noise";
                    if(playerInList.hasFourCard())
                        response =  response + " " + "You need to specify what you want to di with the card in excess";
                    if(playerInList.hasMoved())
                        response =  response + " " + "You have already moved!";
                }
            }
        } 
        return response;
        
    }    
    
    /**
     * Makes the player move and attack in the specified sector if he can go there
     * 
     * @return the response for the client to its command
     */
    private String moveAndAttack(){
        
        Match match = serverStatus.getIdMatchMap().get(id);
        
        if(checkIdIfPresent())
            return NOTINGAME;
        
        if(!(match.getMatchState()==GameState.RUNNING)){
            return NOTRUNNING;
        }
        
        if(!match.getCurrentPlayer().getName().equalsIgnoreCase(id)){
            return NOTYOURTURN;
        }
            
        int letter;
        int number;
        
        if(tokenizer.hasMoreTokens())
            letter = Character.getNumericValue(tokenizer.nextToken().toLowerCase().charAt(0))-10;
        
        else
            return moveError();
        
        if(tokenizer.hasMoreTokens())
            number=Integer.parseInt(tokenizer.nextToken())-1;

        else
            return moveError();
        
        if(letter<0 || letter>=23 || number <0 || number >=14)
            return moveError();
        
        Sector[][] sector = match.getMap().getSector();
        
        String response = null;
        for (Player playerInList : match.getPlayers()) {
            if(playerInList.getName().equals(id)){
                if(!(playerInList.needSectorNoise() || playerInList.hasFourCard())){
                    if(match.getGameLogic().validMove(playerInList, sector[letter][number])){
                        match.getGameLogic().movePlayerAndAttack(playerInList, sector[letter][number]);
                        response = "You moved and attacked in sector "+letter+" "+number;
                        break;
                    }
                    else 
                        return "You can't move there!";
                } else {
                    if(playerInList.needSectorNoise())
                        response = response + " " + "You need to specify a sector where make a noise";
                    if(playerInList.hasFourCard())
                        response = response + " " + "You need to specify what you want to di with the card in excess";
                    if(playerInList.hasMoved())
                        response = response + " " + "You have already moved!";
                }
            }
        } 
        return response;
    }
    
    /**
     * Makes the player use the specified card if he can
     * 
     * @return the response for the client to its command
     */
    private String useCard(){
        
        Match match = serverStatus.getIdMatchMap().get(id);
        
        String response = "";
        
        if(checkIdIfPresent())
            return NOTINGAME;
        
        if(!(match.getMatchState()==GameState.RUNNING)){
            return NOTRUNNING;
        }
        
        if(!match.getCurrentPlayer().getName().equalsIgnoreCase(id)){
            return NOTYOURTURN;
        }
        
        if(!tokenizer.hasMoreTokens()){
            return "Use syntax: use cardname. Available cardnames are: Adrenaline, Attack, Sedatives, Spotlight, Teleport";
        }
        

        
        for (Player playerInList : match.getPlayers()) {
            if(playerInList.getName().equals(id)){
                if(!(playerInList instanceof Human)){
                    return "You are an alien! You can't use Item Cards!";
                }
                if(playerInList.needSectorNoise())
                    return "You need to specify a sector where make a noise";
            }
        }
        
        switch (tokenizer.nextToken().toLowerCase()) {
        
        case "adrenaline":
            for (Player playerInList : match.getPlayers()) {
                if(playerInList.getName().equals(id)){
                    Card card = new AdrenalineCard();
                    if(match.getGameLogic().hasCard(playerInList, card)){
                        match.getGameLogic().useItemCard(playerInList, card);
                        response="You used the Adrenaline card!";
                    }
                    else 
                        response=CANTUSECARD;
                }  
            }
            
            break;
       
        case "attack":            
            response="You can not use the Attack card!";
            break;
        
        case "defense":
            response="You can not use the Defense card!";
            break;
            
        case "sedatives":
            for (Player playerInList : match.getPlayers()) {
                if(playerInList.getName().equals(id)){
                    Card card = new SedativesCard();
                    if(match.getGameLogic().hasCard(playerInList, card)) {
                        match.getGameLogic().useItemCard(playerInList, card);
                        response="You used the Sedatives card!";
                    }
                    else 
                        response=CANTUSECARD;
                }
            }
            break;
            
        case "spotlight":
            int letter;
            int number;
            
            if(tokenizer.hasMoreTokens())
                letter = Character.getNumericValue(tokenizer.nextToken().toLowerCase().charAt(0))-10;
            
            else
                return SPOTLIGHTSYNTAX;
            
            if(tokenizer.hasMoreTokens())
                number=Integer.parseInt(tokenizer.nextToken())-1;

            else
                return SPOTLIGHTSYNTAX;
            
            if(letter<0 || letter>=23 || number <0 || number >=14)
                return SPOTLIGHTSYNTAX;
            
            for (Player playerInList : match.getPlayers()) {
                if(playerInList.getName().equals(id)){
                    Card card = new SpotlightCard();
                    if(match.getGameLogic().hasCard(playerInList, card)) {
                        match.getGameLogic().useSpotlight(letter, number);
                        response="You used the Spotlight card!";
                    }
                    else
                        response = CANTUSECARD;
                }
            }
            
            break;
            
        case "teleport":
            for (Player playerInList : match.getPlayers()) {
                if(playerInList.getName().equals(id)){
                    Card card = new TeleportCard();
                    if(match.getGameLogic().hasCard(playerInList, card)) {
                        match.getGameLogic().useItemCard(playerInList, card);
                        response="You used the Teleport card!";
                    }
                    else
                        response = CANTUSECARD;
                }
            }
            break;

        default:
            response= "Use syntax: use cardname. Available card names are: Adrenaline, Attack, Sedatives, Spotlight, Teleport";
            break;
        }
        
        return response;
    }
    
    /**
     * Make a noise in the specified sector
     * 
     * @return the response for the client to its command
     */
    private String makeNoise(){
        int letter;
        int number;
       
        Match match = serverStatus.getIdMatchMap().get(id);
        
        if(checkIdIfPresent())
            return NOTINGAME;
        
        if(!(match.getMatchState()==GameState.RUNNING)){
            return NOTRUNNING;
        }
        
        if(!match.getCurrentPlayer().getName().equalsIgnoreCase(id)){
            return NOTYOURTURN;
        }     
        
        if(tokenizer.hasMoreTokens())
            letter = Character.getNumericValue(tokenizer.nextToken().toLowerCase().charAt(0))-10;
        
        else
            return NOISESYNTAX;
        
        if(tokenizer.hasMoreTokens())
            number=Integer.parseInt(tokenizer.nextToken())-1;

        else
            return NOISESYNTAX;
        
        if(letter<0 || letter>=23 || number <0 || number >=14)
            return NOISESYNTAX;
        
        for (Player playerInList : match.getPlayers()) {
            if(playerInList.getName().equals(id))
                if(playerInList.needSectorNoise()){
                    match.getGameLogic().makeANoise(letter, number);
                    playerInList.setNeedSectorNoise(false);
                } else
                    return "No need to make a noise";
        }
        
        return "Noise done";
    }
    
    /**
     * Makes a client discard the specified card only of he has more than 3 card
     * 
     * @return the response for the client to its command
     */
    private String discardCard() {
        String response = null;
        
        Match match = serverStatus.getIdMatchMap().get(id);
        
        if(checkIdIfPresent())
            return NOTINGAME;
        
        if(serverStatus.getIdMatchMap().get(id).getMatchState()!=GameState.RUNNING){
            return NOTRUNNING;
        }
        
        if(!serverStatus.getIdMatchMap().get(id).getCurrentPlayer().getName().equalsIgnoreCase(id)){
            return NOTYOURTURN;
        }
        
        if(!tokenizer.hasMoreTokens()){
            return "Use syntax: use cardname. Available cardnames are: Adrenaline, Attack, Sedatives, Spotlight, Teleport";
        }
        
        switch (tokenizer.nextToken().toLowerCase()) {
        
        case "adrenaline":
            for (Player playerInList : match.getPlayers()) {
                if(playerInList.getName().equals(id)){
                    Card card = new AdrenalineCard();
                    if(match.getGameLogic().hasCard(playerInList, card)){
                        match.getGameLogic().discardItemCard(playerInList, card);
                        response="You discarded the Adrenaline card!";
                    }
                    else
                        response = "You don't have an Adrenaline card!";
                }
            }
            break;
            
        case "attack":
            for (Player playerInList : match.getPlayers()) {
                if(playerInList.getName().equals(id)){
                    Card card = new AttackCard();
                    if(match.getGameLogic().hasCard(playerInList, card)){
                        match.getGameLogic().discardItemCard(playerInList, card);
                        response="You discarded the Attack card!";
                    }
                    else
                        response = "You don't have an Attack card!";
                }
            }
            break;
        
        case "defense":
            for (Player playerInList : match.getPlayers()) {
                if(playerInList.getName().equals(id)){
                    Card card = new DefenseCard();
                    if(match.getGameLogic().hasCard(playerInList, card)){
                        match.getGameLogic().discardItemCard(playerInList, card);
                        response="You discarded the Defense card!";
                    }
                    else
                        response = "You don't have a Defence card!";
                }
            }
            break;
            
        case "sedatives":
            for (Player playerInList : match.getPlayers()) {
                if(playerInList.getName().equals(id)){
                    Card card = new SedativesCard();
                    if(match.getGameLogic().hasCard(playerInList, card)){
                        match.getGameLogic().discardItemCard(playerInList, card);
                        response="You discarded the Sedatives card!";
                    }
                    else 
                        response = "You don't have a Defence card!";
                }
            }
            break;
        
        case "spotlight":
            for (Player playerInList : match.getPlayers()) {
                if(playerInList.getName().equals(id)){
                    Card card = new SpotlightCard();
                    if(match.getGameLogic().hasCard(playerInList, card)){
                        match.getGameLogic().discardItemCard(playerInList, card);
                        response="You discarded the Spotlight card!";
                    }
                    else
                        response = "You don't have a Defence card!";
                }
            }
            break;
            
        case "teleport":
            for (Player playerInList : match.getPlayers()) {
                if(playerInList.getName().equals(id)){
                    Card card = new TeleportCard();
                    if(match.getGameLogic().hasCard(playerInList, card)){
                        match.getGameLogic().discardItemCard(playerInList, card);
                        response="You discarded the Teleport card!";
                    }
                    else
                        response = "You don't have a Defence card!";
                }
            }
            break;
            
        default:
            response = "That card doens't exist!";
        }
        return response;
    }
    
    /**
     * Makes the player end his turn
     * 
     * @return the response for the client to its command
     */
    private String endTurn() {
        
        Match match = serverStatus.getIdMatchMap().get(id);
        
        if(checkIdIfPresent())
            return NOTINGAME;
        
        if(match.getMatchState()!=GameState.RUNNING){
            return NOTRUNNING;
        }
        
        if(!match.getCurrentPlayer().getName().equalsIgnoreCase(id)){
            return NOTYOURTURN;
        }
        
        
        String response = "";

        for (Player playerInList : match.getPlayers()) {
            if(playerInList.getName().equals(id)){
                if(playerInList.needSectorNoise()){
                    return "You need to specify a sector where make a noise";
                }
                if(playerInList.hasFourCard()){
                    return "You have four cards! You must discard one";
                }
                if(playerInList.hasMoved()){
                    match.getGameLogic().endTurn();
                    response = "Your turn has ended";
                }
                else
                    response = "You need to move before ending your turn!";
            }
        }
        return response;
    }
    
    private String getCards(){
        
        Match match = serverStatus.getIdMatchMap().get(id);
        
        String response = "You don't have any card";
        
        if(checkIdIfPresent())
            return NOTINGAME;
        
        if(!(match.getMatchState()==GameState.RUNNING)){
            return NOTRUNNING;
        }
        
        List<Card> cards = new ArrayList<Card>();
        
        for (Player playerInList : match.getPlayers()) {
            if(playerInList.getName().equals(id)){
                cards=playerInList.getCards();
                response = "";
            }
        }
        
        for(Card playerCard : cards){
            response=response+playerCard.toString()+" ";
        }
        
        return response;
    }
}
