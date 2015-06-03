package it.polimi.ingsw.cg_23.network;

import it.polimi.ingsw.cg_23.model.cards.AdrenalineCard;
import it.polimi.ingsw.cg_23.model.cards.Card;
import it.polimi.ingsw.cg_23.model.cards.SedativesCard;
import it.polimi.ingsw.cg_23.model.cards.SpotlightCard;
import it.polimi.ingsw.cg_23.model.cards.TeleportCard;
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
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class receive commands from the client and executes them.
 * 
 * 
 * @author Paolo
 *
 */
public class ClientHandler implements Runnable{
    
    private String notInGame = "You are not in a game! Join one first!";    
    private String notStartedYet = "Game has not started yet!";
    private String notYourTurn = "It's not your turn!";
    private String cantUseCard = "You can't use that card!";
    private String spotlightSyntax = "Spotlight syntax: use spotlight letter number";
    private String noiseSyntax = "Noise syntax: noise letter number";
    
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
                response = movePlayer();
                break;
               
            case "moveattack":
                response = moveAndAttack();
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
    
    private String checkGames(){
        
        if(!tokenizer.hasMoreTokens())
            return "Join sintax: join mapname";
        
        String mapName = tokenizer.nextToken().toLowerCase();
        
        String response = null;
        
        if(!checkIdIfPresent())
            return "You are already in a game!";
        
        if("galilei".equals(mapName) || "fermi".equals(mapName) || "galvani".equals(mapName)){
            
            for (Match match : serverStatus.getMatchBrokerMap().keySet()) {
                if(match.getName().equals(mapName) && match.getMatchState() != GameState.RUNNING && match.getPlayers().size()<8){
                    joinGame(match, serverStatus.getMatchBrokerMap().get(match));
                    response = "You were added to a game with map "+mapName;
                }
                else{
                    joinNewGame(mapName);
                    response = "You were added to a new game with map "+mapName;
                }
            }
            
            if(serverStatus.getMatchBrokerMap().isEmpty()){
                joinNewGame(mapName);
                response = "You were added to a new game with map "+mapName;
            }
        }
        else
            return "Map "+mapName+" not implemented!";
        
        return response;
    }
    
    private void joinGame(Match match, Broker broker){
        
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
        
    }
    
    private void joinNewGame(String mapName){
        
        Match match = new Match(mapName);
        
        Broker broker = new Broker(""+mapName+serverStatus.getBrokerNumber());
        
        BrokerThread brokerThread = new BrokerThread(socket);
        brokerThread.start();
        broker.addSubscriber(brokerThread);
        
        match.addNewPlayerToList(new Alien(id));
        
        match.getGameLogic().setBroker(broker);
        
        serverStatus.addBrokerToMatch(match, broker);
        
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
    
    
    private String moveError(){
        return "Move sintax: move letter number. The letter can go from A to W, the number from 1 to 14.";
    }
    
    private String movePlayer(){
        
        if(checkIdIfPresent())
            return notInGame;
        
        if(serverStatus.getIdMatchMap().get(id).getMatchState()!=GameState.RUNNING){
            return notStartedYet;
        }
            
        if(!serverStatus.getIdMatchMap().get(id).getCurrentPlayer().getName().equalsIgnoreCase(id)){
            return notYourTurn;
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
        
        Match match = serverStatus.getIdMatchMap().get(id);
        
        Sector[][] sector = match.getMap().getSector();
        
        String response = "";
        for (Player playerInList : match.getPlayers()) {
            if(playerInList.getName().equals(id)){
                if(!(playerInList.needSectorNoise() || playerInList.hasFourCard() || playerInList.hasMoved())){
                    if(match.getGameLogic().validMove(playerInList, sector[letter][number])){
                        response = match.getGameLogic().movePlayer(playerInList, sector[letter][number]);
                        response = response + "You moved in sector "+letter+" "+number;
                        break;
                    }
                    else 
                        return "You can't move there!";
                } else {
                    if(playerInList.needSectorNoise())
                        response = response + "You need to specify a sector where make a noise ";
                    if(playerInList.hasFourCard())
                        response =  response + "You need to specify what you want to di with the card in excess ";
                    if(playerInList.hasMoved())
                        response =  response + "You have already moved! ";
                }
            }
        } 
        return response;
        
    }    
    
    private String moveAndAttack(){
        
        if(checkIdIfPresent())
            return notInGame;
        
        if(serverStatus.getIdMatchMap().get(id).getMatchState()!=GameState.RUNNING){
            return notStartedYet;
        }
        
        if(!serverStatus.getIdMatchMap().get(id).getCurrentPlayer().getName().equalsIgnoreCase(id)){
            return notYourTurn;
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
        
        Match match = serverStatus.getIdMatchMap().get(id);
        
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
                        response = response + "You need to specify a sector where make a noise ";
                    if(playerInList.hasFourCard())
                        response = response + "You need to specify what you want to di with the card in excess ";
                    if(playerInList.hasMoved())
                        response =  response + "You have already moved! ";
                }
            }
        } 
        return response;
    }
    
    private String useCard(){
        String response = null;
        
        if(checkIdIfPresent())
            return notInGame;
        
        if(serverStatus.getIdMatchMap().get(id).getMatchState()!=GameState.RUNNING){
            return notStartedYet;
        }
        
        if(!serverStatus.getIdMatchMap().get(id).getCurrentPlayer().getName().equalsIgnoreCase(id)){
            return notYourTurn;
        }
        
        if(!tokenizer.hasMoreTokens()){
            return "Use sintax: use cardname. Available cardnames are: Adrenaline, Attack, Sedatives, Spotlight, Teleport";
        }
        
        Match match = serverStatus.getIdMatchMap().get(id);
        
        for (Player playerList : match.getPlayers()) {
            if(playerList.getName().equals(id)){
                if(!(playerList instanceof Human)){
                    return "You are an alien! You can't use Item Cards!";
                }
                if(playerList.needSectorNoise())
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
                        response=cantUseCard;
                }  
            }
            
            break;
       
        case "attack":            
            response="You can not the Attack card!";
            break;
        
        case "defense":
            response="You can not the Defense card!";
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
                        response=cantUseCard;
                }
            }
            break;
            
        case "spotlight":
            int letter;
            int number;
            
            if(tokenizer.hasMoreTokens())
                letter = Character.getNumericValue(tokenizer.nextToken().toLowerCase().charAt(0))-10;
            
            else
                return spotlightSyntax;
            
            if(tokenizer.hasMoreTokens())
                number=Integer.parseInt(tokenizer.nextToken())-1;

            else
                return spotlightSyntax;
            
            if(letter<0 || letter>=23 || number <0 || number >=14)
                return spotlightSyntax;
            
            for (Player playerInList : match.getPlayers()) {
                if(playerInList.getName().equals(id)){
                    Card card = new SpotlightCard();
                    if(match.getGameLogic().hasCard(playerInList, card)) {
                        match.getGameLogic().useSpotlight(letter, number);
                        response="You used the Spotlight card!";
                    }
                    else
                        response = cantUseCard;
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
                        response = cantUseCard;
                }
            }
            break;

        default:
            response= "Use sintax: use cardname. Available card names are: Adrenaline, Attack, Sedatives, Spotlight, Teleport";
            break;
        }
        
        return response;
    }
    
    private String makeNoise(){
        int letter;
        int number;
       
        Match match = serverStatus.getIdMatchMap().get(id);
        
        if(checkIdIfPresent())
            return notInGame;
        
        if(serverStatus.getIdMatchMap().get(id).getMatchState()!=GameState.RUNNING){
            return notStartedYet;
        }
        
        if(!serverStatus.getIdMatchMap().get(id).getCurrentPlayer().getName().equalsIgnoreCase(id)){
            return notYourTurn;
        }     
        
        if(tokenizer.hasMoreTokens())
            letter = Character.getNumericValue(tokenizer.nextToken().toLowerCase().charAt(0))-10;
        
        else
            return noiseSyntax;
        
        if(tokenizer.hasMoreTokens())
            number=Integer.parseInt(tokenizer.nextToken())-1;

        else
            return noiseSyntax;
        
        if(letter<0 || letter>=23 || number <0 || number >=14)
            return noiseSyntax;
        
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
    
    private String discardCard() {
        String response = null;
        
        Match match = serverStatus.getIdMatchMap().get(id);
        
        if(checkIdIfPresent())
            return notInGame;
        
        if(serverStatus.getIdMatchMap().get(id).getMatchState()!=GameState.RUNNING){
            return notStartedYet;
        }
        
        if(!serverStatus.getIdMatchMap().get(id).getCurrentPlayer().getName().equalsIgnoreCase(id)){
            return notYourTurn;
        }
        
        if(!tokenizer.hasMoreTokens()){
            return "Use sintax: use cardname. Available cardnames are: Adrenaline, Attack, Sedatives, Spotlight, Teleport";
        }
        
        switch (tokenizer.nextToken().toLowerCase()) {
        
        case "adrenaline":
            for (Player playerInList : match.getPlayers()) {
                if(playerInList.getName().equals(id)){
                    Card card = new AdrenalineCard();
                    if(match.getGameLogic().hasCard(playerInList, card)){
                        match.getGameLogic().discardItemCard(playerInList, card);
                    }
                }
            }
            response="You discarded the Adrenaline card!";
            break;
            
        case "attack":
            for (Player playerInList : match.getPlayers()) {
                if(playerInList.getName().equals(id)){
                    Card card = new AdrenalineCard();
                    if(match.getGameLogic().hasCard(playerInList, card)){
                        match.getGameLogic().discardItemCard(playerInList, card);
                    }
                }
            }
            response="You discarded the Attack card!";
            break;
        
        case "defense":
            for (Player playerInList : match.getPlayers()) {
                if(playerInList.getName().equals(id)){
                    Card card = new AdrenalineCard();
                    if(match.getGameLogic().hasCard(playerInList, card)){
                        match.getGameLogic().discardItemCard(playerInList, card);
                    }
                }
            }
            response="You discarded the Defense card!";
            break;
            
        case "sedatives":
            for (Player playerInList : match.getPlayers()) {
                if(playerInList.getName().equals(id)){
                    Card card = new AdrenalineCard();
                    if(match.getGameLogic().hasCard(playerInList, card)){
                        match.getGameLogic().discardItemCard(playerInList, card);
                    }
                }
            }
            response="You discarded the Sedatives card!";
            break;
            
        case "silence":
            for (Player playerInList : match.getPlayers()) {
                if(playerInList.getName().equals(id)){
                    Card card = new AdrenalineCard();
                    if(match.getGameLogic().hasCard(playerInList, card)){
                        match.getGameLogic().discardItemCard(playerInList, card);
                    }
                }
            }
            response="You discarded the Silence card!";
            break;
        
        case "spotlight":
            for (Player playerInList : match.getPlayers()) {
                if(playerInList.getName().equals(id)){
                    Card card = new AdrenalineCard();
                    if(match.getGameLogic().hasCard(playerInList, card)){
                        match.getGameLogic().discardItemCard(playerInList, card);
                    }
                }
            }
            response="You discarded the Spotlight card!";
            break;
            
        case "teleport":
            for (Player playerInList : match.getPlayers()) {
                if(playerInList.getName().equals(id)){
                    Card card = new AdrenalineCard();
                    if(match.getGameLogic().hasCard(playerInList, card)){
                        match.getGameLogic().discardItemCard(playerInList, card);
                    }
                }
            }
            response="You discarded the Teleport card!";
            break;
        }
        return response;
    }
    

    private String endTurn() {
        
        Match match = serverStatus.getIdMatchMap().get(id);
        
        if(checkIdIfPresent())
            return notInGame;
        
        if(serverStatus.getIdMatchMap().get(id).getMatchState()!=GameState.RUNNING){
            return notStartedYet;
        }
        
        if(!serverStatus.getIdMatchMap().get(id).getCurrentPlayer().getName().equalsIgnoreCase(id)){
            return notYourTurn;
        }
        
        String response = "";

        for (Player playerInList : match.getPlayers()) {
            if(playerInList.getName().equals(id)){
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
}
