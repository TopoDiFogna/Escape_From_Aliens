package it.polimi.ingsw.cg_23.network.entrypoint;

import it.polimi.ingsw.cg_23.network.ClientSubscriber;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Client {

    private final int port;
    private final String ip;
    private final String name;
    
    private Logger logger;
    
    private Scanner stdin;
    
    private Socket socket = null;
    private Scanner socketIn = null;
    private PrintWriter socketOut = null;
    
    private ClientSubscriber receiver;
    
    
    public Client() {
        
        stdin = new Scanner(System.in);
        
        logger.info("inserisci indirizzo ip");
        ip = stdin.nextLine();
        logger.info("inserisci porta");
        port=stdin.nextInt();
        stdin.nextLine();
        logger.info("Inserisci nick");
        name=stdin.nextLine();        
    }
    
    public void startClient() throws IOException{

        String inputLine = "";
        String serverMessage = null;
        
        while(!inputLine.equalsIgnoreCase("exit")){
            
            stdin = new Scanner(System.in);
            inputLine = stdin.nextLine();
            
            if(!inputLine.equalsIgnoreCase("exit")){
        
                try {
                    socket = new Socket(ip,port);
                } catch (UnknownHostException e) {
                    logger.log(Level.SEVERE, "ERROR: Unknown host!", e);
                    logger.log(Level.SEVERE, "ERROR: Try verifying your ip and relaunch the client", e);
                    return;
                } catch (IOException e) {
                    logger.log(Level.SEVERE, "ERROR: Cannot connect to the specified host!", e);
                    logger.log(Level.SEVERE, "ERROR: Try verifying your ip and port and relaunch the client", e);
                    return;
                }
                
                try {
                    socketIn = new Scanner(socket.getInputStream());
                } catch (IOException e) {
                    logger.log(Level.SEVERE,"ERROR: Stream error!", e);
                }
                
                try {
                    socketOut = new PrintWriter(socket.getOutputStream());
                } catch (IOException e) {
                    logger.log(Level.SEVERE, "ERROR: Stream error!", e);
                }
                
                socketOut.println(name+" "+inputLine);
                socketOut.flush();
                
                serverMessage = socketIn.nextLine();
                logger.info(serverMessage);
                
                if(inputLine.equalsIgnoreCase("join galilei") || inputLine.equalsIgnoreCase("join fermi") || inputLine.equalsIgnoreCase("join galvani"))
                {
                    receiver= new ClientSubscriber(socket);
                    receiver.start();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.startClient();

    }
}
