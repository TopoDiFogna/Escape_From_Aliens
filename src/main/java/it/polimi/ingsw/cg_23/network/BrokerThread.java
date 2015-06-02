package it.polimi.ingsw.cg_23.network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BrokerThread extends Thread{

    private Socket socket;
    private PrintWriter out;
    ConcurrentLinkedQueue<String> buffer;

    public BrokerThread(Socket socket){
        this.socket = socket;
        buffer = new ConcurrentLinkedQueue<String>();
        
        try {
            out = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            System.err.println("Cannot connect to subscriber");
        } 
    }

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
                   System.err.println("Cannot wait on the queue");
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
