package it.polimi.ingsw.cg_23.entrypoint;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.polimi.ingsw.cg_23.controller.GameManager;



public class Server {
    
    private final static int SOCKET_PORT=10412;
    
    private GameManager gameManager;

    public Server() {
 //       gameManager = new GameManager();
        this.startSocket();
    }
    
    
    public void startSocket(){
        
        //Creat a new thread pool
        ExecutorService executor = Executors.newCachedThreadPool();
        
        //initialize server socket
 //       ServerSocket serverSocket = new ServerSocket(SOCKET_PORT);
        
        System.out.println("SERVER: Ready");
        
        while(isRunning()){
            
            //waiting for connections
//            Socket socket = serverSocket.accept();
            
            
 //           gameManager = new GameManager(socket);
            
            
            //this.addClient(view, partita, controller);
            
            executor.submit(gameManager);
        }
    }
    
    private boolean isRunning(){
        return true;
    }
    
    
    public static void main(String[] args) {
        
        //create the server and starts it
        new Server();
    }

}
