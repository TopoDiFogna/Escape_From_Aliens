package it.polimi.ingsw.cg_23.view;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;
import java.util.Scanner;

public class ClientHandler extends View implements Runnable{
    
    /**
     * The socket of the client
     */
    private final Socket socket;
    
    /**
     * The scanner to acquire client inputs
     */
    private Scanner socketIn;
    
    /**
     * The PrintWriter to send messages to the client
     */
    private PrintWriter socketOut;

    public ClientHandler(Socket socket) {
        this.socket=socket;
    }

    
    /**
     * Notifies the client of some changes.
     * 
     * @param o the object that has called this method
     * @param arg what to notify to the client
     */
    @Override
    public void update(Observable o, Object arg) {
        
        System.out.println("SERVER: sending the message to the client");
        
        socketOut.println(arg);
        socketOut.flush();

    }

    @Override
    public void run() {//TODO
        while(isConnected()){
            
            String line = socketIn.nextLine();
            
            
        }
        
    }
    
    private boolean isConnected(){
        //TODO
        return false;
    }

}
