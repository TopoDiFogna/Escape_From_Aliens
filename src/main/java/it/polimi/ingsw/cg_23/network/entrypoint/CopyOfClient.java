package it.polimi.ingsw.cg_23.network.entrypoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class CopyOfClient extends Thread{
    
    private String serverMessage;
    
    private BufferedReader socketIn;
    
    private Socket socket;
    
    
    public CopyOfClient(Socket socket) throws IOException{
        socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.socket=socket;
    }

    @Override
    public void run() {
        //while(true){
            String msg = null;
            try {
                msg=socketIn.readLine();
                if(msg!=null){
                    System.out.println(msg);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        //}
    }
}
