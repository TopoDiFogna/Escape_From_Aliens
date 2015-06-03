package it.polimi.ingsw.cg_23.network.entrypoint;

import it.polimi.ingsw.cg_23.network.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * From here we start the server.<br>
 * It accepts connection and passes them to the client handler.
 * 
 * @author Paolo
 *
 */

public class Server {
    
    private static final Logger LOGGER = Logger.getLogger("EscapeFromAliensLogger");
    
    /**
     * The port the server listen on.
     */
    private static final int SOCKET_PORT=10412;
        
    /**
     * The client handler will manage every connection to the game.
     */
    private ClientHandler clientHandler;
    
    /**
     * Error to handle client connection.
     */
    private boolean error;

    /**
     * The status of the server.
     */
    private boolean running = true;

    /**
     * Constructor: spawns the server and launches it
     */
    public Server() {
        this.startSocket();
    }
    
    
    /**
     * Connection handling.<br>
     * If cannot accept a client connection doesn't try to add to the GameManager.
     */
    public void startSocket(){
        
        //Create a new thread pool
        ExecutorService executor = Executors.newCachedThreadPool();
        
        //initialize server socket
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(SOCKET_PORT);
            LOGGER.info("SERVER: Ready");
        } catch (IOException e1) {
            LOGGER.log(Level.SEVERE, "ERROR: Cannot run server on port: "+SOCKET_PORT+"!", e1);
            running=false;
        }

        while(isRunning()){
            
            error = false; //resets the error flag
            
            Socket socket = null;
            
            try {
                socket = serverSocket.accept(); //waiting for connections
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE,"ERROR: Cannot accept client connection!", e);
                error=true;  
            }
            
            if(!error){
                clientHandler = new ClientHandler(socket);//creates a new client handler for this socket
            
                executor.submit(clientHandler);//runs the client handler for this connection
            }
        }
    }
    
    /**
     * Tells if the server is running
     * 
     * @return the status of the server, true when running, false otherwise.
     */
    private boolean isRunning(){
        
        return running;
    }

    /**
     * Entry point for the server.
     * 
     * @param args
     */
    public static void main(String[] args) {
        
        //create the server and starts it
        new Server();
    }
}
