package it.polimi.ingsw.cg_23.network.entrypoint;

import it.polimi.ingsw.cg_23.network.rmi.RMIClientHandler;
import it.polimi.ingsw.cg_23.network.rmi.RMIClientHandlerInterface;
import it.polimi.ingsw.cg_23.network.socket.SocketClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * From here we start the server.<br>
 * It accepts connection and passes them to the client handler.
 * 
 * @author Paolo
 *
 */

public class Server {
    /**
     * The port the server listen on.
     */
    private static final int SOCKET_PORT=10412;
        
    /**
     * The client handler that will manage every socket connection to the game.
     */
    private SocketClientHandler socketClientHandler;
    
    /**
     * The client handler that will manage every rmi connection to the game.
     */
    private RMIClientHandler rmiClientHandler;
    
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
        //Nothing to do here
    }
    
    
    /**
     * Socket connection handling.<br>
     * If cannot accept a client connection doesn't try to add to the GameManager.
     */
    private void startSocket(){
        
        //Create a new thread pool
        ExecutorService executor = Executors.newCachedThreadPool();
        
        //initialize server socket
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(SOCKET_PORT);
            System.out.println("SERVER: Ready");
        } catch (IOException e1) {
            System.err.println("ERROR: Cannot run server on port: "+SOCKET_PORT+"!");
            running=false;
        }

        while(isRunning()){
            
            error = false; //resets the error flag
            
            Socket socket = null;
            
            try {
                socket = serverSocket.accept(); //waiting for connections
            } catch (IOException e) {
                System.err.println("ERROR: Cannot accept client connection!");
                error=true;  
            }
            
            if(!error){
                socketClientHandler = new SocketClientHandler(socket);//creates a new client handler for this socket
            
                executor.submit(socketClientHandler);//runs the client handler for this connection
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
     * Create the registry with the interface and binds them
     */
    private void startRMI(){
        
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            
            rmiClientHandler = new RMIClientHandler();
            
            RMIClientHandlerInterface serverStub = (RMIClientHandlerInterface) UnicastRemoteObject.exportObject(rmiClientHandler, 1099);
            
            registry.rebind("game", serverStub);
            
        } catch (RemoteException e) {
            System.err.println("ERROR: Cannot create RMI registry!");
        }  
    }

    /**
     * Entry point for the server.
     * 
     * @param args no args required
     */
    public static void main(String[] args) {
        
        //create the server and starts it
        Server server = new Server();
        server.startRMI();
        server.startSocket();
    }
}
