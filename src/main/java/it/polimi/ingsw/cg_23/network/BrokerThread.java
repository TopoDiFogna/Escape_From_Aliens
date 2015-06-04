package it.polimi.ingsw.cg_23.network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BrokerThread extends Thread{

    private Socket socket;
    private PrintWriter out;
    private static final Logger LOGGER = Logger.getLogger("EscapeFromAliensLogger");
    ConcurrentLinkedQueue<String> buffer;

    public BrokerThread(Socket socket){
        this.socket = socket;
        buffer = new ConcurrentLinkedQueue<String>();
        
        try {
            out = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Cannot connect to subscriber", e);
        } 
    }

    @Override
    public void run() {
        while(true){
            String msg = buffer.poll();
            if(msg != null){ 
                send(msg);
                LOGGER.info("Sending...");
            }
            else{
                try {
                    synchronized (buffer) {
                        buffer.wait();  
                    }
                } catch (InterruptedException e) {
                    LOGGER.log(Level.WARNING, "Cannot wait on the queue", e);
                }
            }
        }
    }
    
    
    public void dispatchMessage(String msg){
        buffer.add(msg);
        synchronized(buffer){
            buffer.notify();
        }
    }
    
    public void send(String msg){
        out.println(msg);
        out.flush();
    }
}
