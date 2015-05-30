package it.polimi.ingsw.cg_23.network;

import it.polimi.ingsw.cg_23.controller.GameLogic;
import it.polimi.ingsw.cg_23.model.players.Alien;
import it.polimi.ingsw.cg_23.model.players.Human;
import it.polimi.ingsw.cg_23.model.players.Player;
import it.polimi.ingsw.cg_23.model.status.GameState;
import it.polimi.ingsw.cg_23.model.status.Match;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameManager implements Communicator, Runnable{
    
    /**
     * List of socket connected but still choosing a map
     */
    private static List<Socket> sockets = new ArrayList<Socket>();
    
    /**
     * Map of all matches with their controller
     */
    private static Map<Match, GameLogic> matches = new HashMap<Match, GameLogic>();
    
    /**
     * List of all brokers currently avaible
     */
    private static List<Broker> brokers= new ArrayList<Broker>();
    
    /**
     * This number is used only to distinguish matches with the same name
     */
    private static int counter = 0;
    
    /**
     * Scanner to read client input
     */
    private Scanner socketIn;
    
    /**
     * PrintWriter to send client output
     */
    private PrintWriter socketOut;
    
    /**
     * The socket of the player who is connecting to join a game
     */
    private Socket socket;
    
    /**
     * Constructor of this class, adds the received socket to the connected sockets. <br>
     * Also creates a new PrintWriter and a new Scanner to send and receive commands from the client.
     * 
     * @param socket the socket to be added to the list
     */
    public GameManager(Socket socket) {
        sockets.add(socket);
        this.socket = socket;
        
        try {
            
            socketIn = new Scanner(socket.getInputStream());
            
        } catch (IOException e) {
            
            System.err.println("ERROR: Cannot create a Scanner to recieve input from the client");
                sockets.remove(socket);
                close();
        }
        
        try {
            
            socketOut= new PrintWriter(socket.getOutputStream());
        } catch (IOException e2) {
            
            System.err.println("ERROR: Cannot create a PrintWriter to send output to the client");           
            
            close();
            return;
        }
    }
    
    
    /**
     * Make a player join the specified game.
     * 
     * @param clientHandler the client handler
     * @param match the match the client will join
     * @param gameLogic the rules of the game
     */
    private void joinGame(Match match, GameLogic gameLogic){
        
        ExecutorService executor = Executors.newCachedThreadPool();
        
        ClientHandler clientHandler = new ClientHandler(socket);
        
        for (Broker broker : brokers) {
            if(broker.getTopic()==""+match.getName()+counter){
                broker.addSubscriber(clientHandler);
            }
        }
        
        //clientHandler.addObserver(gameLogic);
        
        //match.addObserver(clientHandler);
        
        Player newPlayer;
        send("Enter your name: ");
        String name = receive();
        
        int nAlien = 0; //number of aliens in the match
        
        int nHuman = 0;//number of humans in the match
        
        /*for (Player player : match.getSocketMap().keySet()) {
            if(player instanceof Alien){
                nAlien++;
            }
            else
                nHuman++;
        }*/
        
        if(nAlien>=nHuman)
            newPlayer = new Human(name);
        else
            newPlayer = new Human(name);
            
        match.addNewPlayerToMap(newPlayer, socket);
        
        executor.submit(clientHandler);
    }
    
    /**
     * Make the client join a fresh game.
     * 
     * @param clientHandler what will handle clients requests
     * @param match the match the client will join
     * @param gameLogic the rules of the game
     */
    private void joinNewGame(String mapName){
        
        ExecutorService executor = Executors.newCachedThreadPool();
        
        ClientHandler clientHandler = new ClientHandler(socket);
        
        Match match = new Match(mapName);
        
        GameLogic gameLogic = new GameLogic(match);
        
        Broker broker = new Broker(""+mapName+counter);
        
        counter++;
        
        brokers.add(broker);
        
        broker.addSubscriber(clientHandler);
        
        //clientHandler.addObserver(gameLogic);
        
        //match.addObserver(clientHandler);
        
        send("Enter your name: ");
        
        String name = receive();
        
        match.addNewPlayerToMap(new Alien(name), socket);
        
        executor.submit(clientHandler);
        
        //TODO here we manage when to start the game?
    }

    
    /**
     * Overridden method because this class implements Runnable<br>
     * 
     * Asks the player for a map to play and calls joinGame to make it play
     * 
     */
    @Override
    public void run() {
        
        send("Welcome to Escape From The Aliens In Outer Space!!");
        send("Which map you want to play?");
        String mapName = receive().toLowerCase();
        
        
        //TODO synchronized??? maybe yes
        for (Match match : matches.keySet()) {
            
            if(match.getName() == mapName && match.getMatchState() != GameState.RUNNING){
                
                joinGame(match, matches.get(match));
            }
            else{

                joinNewGame(mapName);
            }
        }
    }

    /**
     * Closes the connection with a client
     * 
     */
    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println("ERROR: Cannot close the connection of client with socket number "+sockets.indexOf(socket));
        } finally {
            socket = null;
        }
    }
}
