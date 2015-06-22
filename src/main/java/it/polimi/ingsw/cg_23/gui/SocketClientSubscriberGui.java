package it.polimi.ingsw.cg_23.gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * This class handles the thread to receive message from the server.
 * 
 * @author Paolo
 *
 */
public class SocketClientSubscriberGui extends Thread{
    
    /**
     * The reader to receive the messages.
     */
    private BufferedReader socketIn;
    
    /**
     * Socket associates to the subscriber in the broker.
     */
    private Socket socket;
    
    /**
     * Constructor. Initialize the socket and the reader.
     * 
     * @param socket
     */
    public SocketClientSubscriberGui(Socket socket) {
        this.socket=socket;
        try {
            socketIn = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        } catch (IOException e) {
            System.err.println("Can't create the reader to recieve server messages.");
        } 
    }

    /**
     * Main method of the thread. Receives the messages and calls a method to handle them.
     */
    @Override
    public void run() {
        while(true){
            receive();
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                System.err.println("Cannot pause the thread.");
            }
        }
    }
    
    /**
     * If the input from the server is not empty prints it on the chat.
     */
    private void receive() {
        String msg = null;
        try {
            msg=socketIn.readLine();
            if(msg!=null){
                ChatPanel.appendMessages(msg);
            }
        } catch (IOException e) {                
            System.err.println("Cannot read the server stream.");
        }
    }
}
