package it.polimi.ingsw.cg_23.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Logger;


public class ClientSubscriber extends Thread{
    
    private static final Logger LOGGER = Logger.getLogger("EscapeFromAliensLogger");
    
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
                LOGGER.throwing("ClientSubscriber", "run", e);
            }
        }
    }
    
    private void receive() {
        String msg = null;
        try {
            msg=socketIn.readLine();
            if(msg!=null){
                LOGGER.info(msg);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            LOGGER.throwing("ClientSubscriber", "recive", e);
        }
        
    }
}
