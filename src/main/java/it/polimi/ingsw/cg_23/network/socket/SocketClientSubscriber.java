package it.polimi.ingsw.cg_23.network.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class SocketClientSubscriber extends Thread{
    
    private BufferedReader socketIn;
    
    private Socket socket;
    
    
    public SocketClientSubscriber(Socket socket) {
        this.socket=socket;
        try {
            socketIn = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        } catch (IOException e) {
            System.err.println("Can't create the reader to recieve server messages.");
        } 
    }

    @Override
    public void run() {
        while(true){
            receive();
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void receive() {
        String msg = null;
        try {
            msg=socketIn.readLine();
            if(msg!=null){
                System.out.println(msg);
            }
        } catch (IOException e) {                
            e.printStackTrace();

        }
        
    }
}
