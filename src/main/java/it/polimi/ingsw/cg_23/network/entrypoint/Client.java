package it.polimi.ingsw.cg_23.network.entrypoint;

import it.polimi.ingsw.cg_23.network.rmi.RMIClient;
import it.polimi.ingsw.cg_23.network.rmi.RMIClientHandlerInterface;
import it.polimi.ingsw.cg_23.network.rmi.RMIClientInterface;
import it.polimi.ingsw.cg_23.network.socket.SocketClientSubscriber;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Client {

    private final int port;
    private final String ip;
    private final String name;
    
    private Scanner stdin;
    
    private Socket socket = null;
    private Scanner socketIn = null;
    private PrintWriter socketOut = null;
    
    private boolean RMI;
    
    private SocketClientSubscriber receiver;
    
    
    public Client() {
        
        stdin = new Scanner(System.in);
        
        System.out.println("inserisci indirizzo ip");
        ip = stdin.nextLine();
        System.out.println("Socket or RMI?");
        if(stdin.nextLine().equalsIgnoreCase("rmi")){
            RMI=true;
            port=1099;
        }
        else
            port=10412;   
        System.out.println("Inserisci nick");
        name=stdin.nextLine(); 
    }
    
    public void startSocketClient() throws IOException{

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
                    receiver= new SocketClientSubscriber(socket);
                    receiver.start();
                }
            }
        }
    }
    
    public void startRMIClient(){
        
        String inputLine = "";
        String commandParsed = "";
        stdin = new Scanner(System.in);
        StringTokenizer tokenizer;
        RMIClientHandlerInterface clientHandler;
        
        
        RMIClient clientInterface = new RMIClient();
        
        try {
            Registry registry = LocateRegistry.getRegistry(ip,port);
            
            clientHandler = (RMIClientHandlerInterface) registry.lookup("game");
            
            System.out.println("What you want to do? (Available commands: gamelist, join mapname)");
            
            RMIClientInterface exportedClientInterface = (RMIClientInterface)UnicastRemoteObject.exportObject(clientInterface,0);
            
            while(!("exit".equalsIgnoreCase(inputLine))){
            
                inputLine = stdin.nextLine().toLowerCase();
                
                tokenizer = new StringTokenizer(inputLine);
                
                commandParsed=tokenizer.nextToken();
                
                switch(commandParsed){
                
                    case "gamelist":
                        clientHandler.getGameList(exportedClientInterface);
                        break;
                        
                    case "join":
                        if(tokenizer.hasMoreTokens())
                            clientHandler.joinMatch(name, tokenizer.nextToken(), exportedClientInterface);
                        else
                            System.out.println("You need to speicify a map name.");
                        break;
    
                    case "exit":
                        System.out.println("Bye Bye!");
                        break;
                        
                    default:
                        System.out.println("Command not found!");
                        break;
                }
            } 
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
    }
    

    public boolean useRMI() {
        return RMI;
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        if(client.useRMI()){
            client.startRMIClient();
        }
        else
            client.startSocketClient();

    }
}
