package it.polimi.ingsw.cg_23.network.entrypoint;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Client {

    private final int port;
    private final String ip;
    private final String name;
    
    private Scanner stdin;
    
    private Socket socket = null;
    private Scanner socketIn = null;
    private PrintWriter socketOut = null;
    
    private CopyOfClient receiver;
    
    
    public Client() {
        
        stdin = new Scanner(System.in);
        
        System.out.println("inserisci indirizzo ip");
        ip = stdin.nextLine();
        System.out.println("inserisci porta");
        port=stdin.nextInt();
        stdin.nextLine();
        System.out.println("Inserisci nick");
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
                    System.err.println("ERROR: Unknown host!");
                    System.err.println("ERROR: Try verifying your ip and relaunch the client");
                    return;
                } catch (IOException e) {
                    System.err.println("ERROR: Cannot connect to the specified host!");
                    System.err.println("ERROR: Try verifying your ip and port and relaunch the client");
                    return;
                }
                
                try {
                    socketIn = new Scanner(socket.getInputStream());
                } catch (IOException e) {
                    System.err.println("ERROR: Stream error!");
                }
                
                try {
                    socketOut = new PrintWriter(socket.getOutputStream());
                } catch (IOException e) {
                    System.err.println("ERROR: Stream error!");
                }
                
                socketOut.println(name+" "+inputLine);
                socketOut.flush();
                
                serverMessage = socketIn.nextLine();
                System.out.println(serverMessage);
                
                if(inputLine.equalsIgnoreCase("join galilei") || inputLine.equalsIgnoreCase("join fermi") || inputLine.equalsIgnoreCase("join galvani"))
                {
                    receiver= new CopyOfClient(socket);
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
