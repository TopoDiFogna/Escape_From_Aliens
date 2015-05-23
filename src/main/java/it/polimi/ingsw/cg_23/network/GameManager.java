package it.polimi.ingsw.cg_23.network;

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
     * @param map map tthe player wants to join
     */
    private void joinGame(Socket socket, String map){
        
    }

    
    /**
     * Overridden method because this class implemets Runnable<br>
     * 
     * Asks the player for a map to play and calls joinGame to make it play
     * 
     */
    @Override
    public void run() {
        
        send("Welcome to Escape From The Aliens In Outer Space!!");
        send("Which map you wanto to play?");
        String mapName = receive().toLowerCase();
        
        for (Match match : matches) {
            if(match.getName() == mapName && match.getMatchState() != GameState.RUNNING){
                //TODO add the player to the match
            }
            else{
                //TODO creates a new match, sets match state to waiting and adds the player
                sockets.remove(socket);
            }
        }

    }



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
