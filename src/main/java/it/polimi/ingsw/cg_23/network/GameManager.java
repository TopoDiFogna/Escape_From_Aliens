package it.polimi.ingsw.cg_23.network;

import it.polimi.ingsw.cg_23.controller.GameLogic;
import it.polimi.ingsw.cg_23.model.players.Alien;
import it.polimi.ingsw.cg_23.model.players.Human;
import it.polimi.ingsw.cg_23.model.players.Player;
import it.polimi.ingsw.cg_23.model.status.GameState;
import it.polimi.ingsw.cg_23.model.status.Match;
import it.polimi.ingsw.cg_23.view.ClientHandler;

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
     * List of socket connected but still chosing a map
     */
    private static List<Socket> sockets = new ArrayList<Socket>();
    
    /**
     * Map of all matches with their controller
     */
    private static Map<Match, GameLogic> matches = new HashMap<Match, GameLogic>();
    
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
     * Constructor of this class, adds the received socket to the connected sockets.
     * 
     * @param socket the socket to be added to the list
     */
    public GameManager(Socket socket) {
        sockets.add(socket);
        this.socket = socket;
    }
    
    
    /**
     * Make a player join the specified game.
     * 
     * @param clientHandler the client handler
     * @param match the match the client will join
     * @param gameLogic the rules of the game
     */
    private void joinGame(ClientHandler clientHandler, Match match, GameLogic gameLogic){
        
        ExecutorService executor = Executors.newCachedThreadPool();
        
        int nAlien = 0; //number of aliens in the match
        
        int nHuman = 0;//numbaer of humans in the match
        
        clientHandler.addObserver(gameLogic);
        
        match.addObserver(clientHandler);
        
        Player newPlayer;
        send("Enter your name: ");
        String name = receive();
        
        for (Player player : match.getPlayers()) {
            if(player instanceof Alien){
                nAlien++;
            }
            else
                nHuman++;
        }
        
        if(nAlien>nHuman)
            newPlayer = new Human(name);
        else
            newPlayer = new Human(name);
            
        match.addNewPlayerToMap(newPlayer, socket);
        match.addNewPlayerToList(newPlayer);
        
        executor.submit(clientHandler);
    }
    
    /**
     * Make the client join a fresh game.
     * 
     * @param clientHandler what will handle clients requests
     * @param match the match the client will join
     * @param gameLogic the rules of the game
     */
    private void joinNewGame(ClientHandler clientHandler, Match match, GameLogic gameLogic){
        
        ExecutorService executor = Executors.newCachedThreadPool();
        
        clientHandler.addObserver(gameLogic);
        
        match.addObserver(clientHandler);
        
        //TODO create broker for pub-sub
        
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
                ClientHandler clientHandler = new ClientHandler(socket);
                joinGame(clientHandler, match, matches.get(match));
                sockets.remove(socket);
            }
            else{
                send("Enter your name: ");
                String name = receive();
                ClientHandler clientHandler = new ClientHandler(socket);
                Match newMatch = new Match(mapName, new Alien(name), socket);
                GameLogic newGameLogic = new GameLogic(newMatch);
                joinNewGame(clientHandler, newMatch, newGameLogic);
                matches.put(newMatch, newGameLogic);
                sockets.remove(socket);
            }
        }
    }


    /**
     * Sends Strings to the client
     * 
     * @param msg the string to sent to the client
     */
    @Override
    public void send(String msg) {
        
        try {
            
            socketOut= new PrintWriter(socket.getOutputStream());
        } catch (IOException e2) {
            
            System.err.println("ERROR: Cannot create a PrintWriter to send output to the client");           
            
            close();
            return;
        }

        socketOut.println(msg);
        socketOut.flush();
        
    }


    /**
     * Receives strings from the clients and returns them
     * 
     * @return the line received
     */
    @Override
    public String receive() {
        
        try {
            
            socketIn = new Scanner(socket.getInputStream());
            
        } catch (IOException e) {
            
            System.err.println("ERROR: Cannot create a Scanner to recieve input from the client");
                sockets.remove(socket);
                close();
                return null;
        }
        
        return socketIn.nextLine();
    }


    /**
     * Closes the connection with a client
     * 
     */
    @Override
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
