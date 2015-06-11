package it.polimi.ingsw.cg_23.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class ClientSubscriber extends Thread{
    
    private BufferedReader socketIn;
    
    private Socket socket;
    
    
    public ClientSubscriber(Socket socket) throws IOException{
        this.socket=socket;
        socketIn = new BufferedReader(new InputStreamReader(this.socket.getInputStream())); 
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
