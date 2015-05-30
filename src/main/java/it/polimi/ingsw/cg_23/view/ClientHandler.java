package it.polimi.ingsw.cg_23.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ClientHandler implements Runnable{
    
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
    
    /**
     * Queue of messages to send to the client.<br>
     * Messages in this queue are added by the broker.
     */
    ConcurrentLinkedQueue<String> buffer;
    
    StringTokenizer tokenizer;
    
    boolean commandParsed = false;

    /**
     * Constructor
     * 
     * @param socket
     */
    public ClientHandler(Socket socket) {
        
        this.socket=socket;
        
        buffer = new ConcurrentLinkedQueue<String>();
        
        try {
            socketIn=new Scanner(socket.getInputStream());
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        try {
            socketOut = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void run() {//TODO
        while(isConnected()){
            
            String line = socketIn.nextLine();
            
            System.out.println("SERVER: getting the command "+line);
            
            tokenizer = new StringTokenizer(line);
            
            send(parseCommand(tokenizer.nextToken()));
        }  
    }
    
    private boolean isConnected(){
        //TODO
        return true;
    }
    
    private void send(String msg){
        socketOut.println(msg);
        socketOut.flush();
    }
    
    private String moveError(){
        return "Move sintax: move letter number. The letter can go from A to W, the number from 1 to 14.";
    }
    
    
    
    private String movePlayer(){//TODO check parameters
        String result=null;
        int letter;
        int number;
        
        if(tokenizer.hasMoreTokens())
            letter=Integer.parseInt(tokenizer.nextToken().toLowerCase())-Integer.parseInt("a");
        
        else
            return moveError();
        
        if(tokenizer.hasMoreTokens())
            number=Integer.parseInt(tokenizer.nextToken())-1;

        else
            return moveError();
        
        if(letter<0 || letter>=23 || number <0 || number >=14)
            return moveError();
        
        
        
        return result;
    }
    
    private String parseCommand(String msg){//TODO finish this
        
        String response = null;
        
        commandParsed = false;
        
        if(msg == null){
            response="Command not found!";
            return response;
        }
                
        switch(msg.toLowerCase()){
            default:
                response="Command not found!";
                
            case "gamelist":
                response="This maps are playable: Galilei, Galvani, Fermi";
                break;
                
            case "join":
                
                
            case "move":
                response = movePlayer();
                
            case "use":
                if(!tokenizer.hasMoreTokens()){
                    response="Use sintax: use cardname. Avaible card names are Adrenaline, Attack, Sedatives, Spotlight, Teleport.";
                    break;
                }
                
            case "noise":
                if(!tokenizer.hasMoreTokens()){
                    response="Use sintax: use cardname. Avaible card names are Adrenaline, Attack, Sedatives, Spotlight, Teleport.";
                    break;
                }
                
            
                
                
        
        
        
        }
        return response;
    }

}
