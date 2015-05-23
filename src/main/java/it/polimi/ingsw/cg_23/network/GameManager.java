package it.polimi.ingsw.cg_23.network;

import it.polimi.ingsw.cg_23.controller.GameLogic;
import it.polimi.ingsw.cg_23.model.players.Alien;
import it.polimi.ingsw.cg_23.model.players.Player;
import it.polimi.ingsw.cg_23.model.status.GameState;
import it.polimi.ingsw.cg_23.model.status.Match;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameManager implements Communicator, Runnable{
    
    /**
     * List of socket connected but still chosing a map
     */
    private static List<Socket> sockets = new ArrayList<Socket>();
    
    /**
     * List of all matches
     */
    private static List<Match> matches = new ArrayList<Match>();
    
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
     * @param socket the socket of the player joining
     * @param map map the player wants to join
     */
    /*private void joinGame(View view, Match match, GameLogic gameLogic){//TODO finish this
        
        view.addObserver(gameLogic);
        
        match.addObserver(view);
    }*/

    
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
        
        for (Match match : matches) {
            if(match.getName() == mapName && match.getMatchState() != GameState.RUNNING){
                //joinGame(view, match, new GameLogic(match));
                sockets.remove(socket);
            }
            else{
                send("Enter your name: ");
                String name = receive();
                //joinGame(view, new Match(mapName, new Alien(name), socket), new GameLogic(match));
                sockets.remove(socket);
            }
        }

    }


    /**
     * Sends connection to the clients
     * 
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
