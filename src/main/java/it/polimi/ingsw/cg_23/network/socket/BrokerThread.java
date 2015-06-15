package it.polimi.ingsw.cg_23.network.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * This class runs on its own thread. Sends messages to its subscriber.
 * 
 * @author Paolo
 *
 */
public class BrokerThread extends Thread{

    /**
     * The socket connected to this thread
     */
    private Socket socket;
    /**
     * The PrintWriter to send messages to the subscriber
     */
    private PrintWriter out;

    /**
     * The buffer where the mesage to send is saved
     */
    ConcurrentLinkedQueue<String> buffer;

    /**
     * Constructor. Initialize the buffer and the socket.
     * 
     * @param socket the socket connected to this thread
     */
    public BrokerThread(Socket socket){
        this.socket = socket;
        buffer = new ConcurrentLinkedQueue<String>();
        
        try {
            out = new PrintWriter(this.socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Cannot connect to subscriber");
        } 
    }

    /**
     * Main method of the thread. listen for new messages and if at least one message is found sends all the messages in the queue
     */
    @Override
    public void run() {
        while(true){
            String msg = buffer.poll();
            if(msg != null){ 
                send(msg);
                System.out.println("Sending...");
            }
            else{
                try {
                    synchronized (buffer) {
                        buffer.wait();  
                    }
                } catch (InterruptedException e) {
                    System.out.println("Cannot wait on the queue");
                }
            }
        }
    }
    
    /**
    * Adds a message to the queue and notifies that the queue is not empty 
    * 
    * @param msg the message to add to the queue
    */
    public void dispatchMessage(String msg){
        buffer.add(msg);
        synchronized(buffer){
            buffer.notify();
        }
    }
    
    /**
     * Sends the message to the subscriber
     * 
     * @param msg the message to send to the subscriber
     */
    public void send(String msg){
        out.println(msg);
        out.flush();
    }
}
