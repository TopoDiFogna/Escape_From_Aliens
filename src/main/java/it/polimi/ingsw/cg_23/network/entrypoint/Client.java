package it.polimi.ingsw.cg_23.network.entrypoint;

import it.polimi.ingsw.cg_23.network.rmi.RMIClient;
import it.polimi.ingsw.cg_23.network.rmi.RMIClientHandlerInterface;
import it.polimi.ingsw.cg_23.network.rmi.RMIClientInterface;
import it.polimi.ingsw.cg_23.network.rmi.RMIGameCommandsInterface;
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

/**
 * This class is the client core. It choose how to connect to the server and handles the connction.
 * 
 * @author Paolo
 *
 */
public class Client {

    /**
     * Port used for connections
     */
    private final int port;
    
    /**
     * Ip where connect to
     */
    private final String ip;
    
    /**
     * Identifier of the client. It's Unique
     */
    private final String name;
    
    /**
     * String to display if move command syntax is not correct
     */
    private static final String MOVE_ERROR = "Move sintax: move letter number. The letter can go from A to W, the number from 1 to 14.";
    
    /**
     * String to display if use card command syntax is not correct
     */
    private static final String CARD_ERROR = "Use sintax: use cardname. Available cardnames are: Adrenaline, Attack, Sedatives, Spotlight, Teleport";
    
    /**
     * String to display if noise command syntax is not correct
     */
    private static final String NOISE_ERROR = "Noise sintax: move letter number. The letter can go from A to W, the number from 1 to 14.";

    /**
     * String to display an error if the player is not in a game
     */
    private static final String NOT_IN_GAME = "NOT:IN_GAME!";
    
    /**
     * Scanner used to read from console command
     */
    private Scanner stdin;
    
    /**
     * Socket used to connect to the server
     */
    private Socket socket = null;
    
    /**
     * Scanner used to read server input
     */
    private Scanner socketIn = null;
    
    /**
     * PrintWriter to send to the server the commands
     */
    private PrintWriter socketOut = null;
    
    /**
     * Tells if we are using rmi
     */
    private boolean rmi;
    
    /**
     * The interface to expose to the server to get responses when using rmi
     */
    private RMIClient clientInterface;
    
    /**
     * Where the server interface exposed to the client is saved
     */
    private RMIClientHandlerInterface clientHandler;
    
    /**
     * Where the interface containing the game commands is saved
     */
    private RMIGameCommandsInterface gameCommands=null;
    
    /**
     * The receiver to get server messages when using socket. It runs on one other thread.
     */
    private SocketClientSubscriber receiver;
    
    private boolean clientJoined = false;
    
    /**
     * Constructor <br>
     * Asks to enter various things used to determine where to connect, which connection to use and the client identifier
     */
    private Client() {
        
        stdin = new Scanner(System.in);
        
        System.out.println("Enter IP address");
        ip = stdin.nextLine();
        System.out.println("Socket or RMI? (Default is Socket)");
        if("rmi".equalsIgnoreCase(stdin.nextLine())){
            rmi=true;
            port=1099;
        }
        else
            port=10412;   
        System.out.println("Enter Nickname");
        name=stdin.nextLine(); 
    }
    
    /**
     * This method is called only if socket is chosen as a kind of connection.<br>
     * First initialize the scanner and gets the first command from the client. Then, if it isn't the exit command, creates a socket, connects to the server and sends the command.<br>
     * In particular if the command is one to join a match creates a new thread to listen to the match broker.
     * 
     */
    public void startSocketClient() {

        String inputLine = "";
        String serverMessage = null;
        
        while(!("exit".equalsIgnoreCase(inputLine))){
            
            stdin = new Scanner(System.in);
            inputLine = stdin.nextLine();
            
            if(!("exit".equalsIgnoreCase(inputLine))){
        
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
                
                if("join galilei".equalsIgnoreCase(inputLine) || "join fermi".equalsIgnoreCase(inputLine) || "join galvani".equalsIgnoreCase(inputLine))
                {
                    receiver= new SocketClientSubscriber(socket);
                    receiver.start();
                }
            }
        }
    }
    
    /**
     * This method is called only if rmi is chosen as a kind of connection.<br>
     * It creates a client interface to export to the server, then looks  for the server remote interface.<br>
     * Then parses the command and calls the appropriate method on the server interface.
     */
    public void startRMIClient(){
        
        String inputLine = "";
        String commandParsed = "";
        stdin = new Scanner(System.in);
        StringTokenizer tokenizer;

        
        
        clientInterface = new RMIClient();
        
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
                        if(!clientJoined && tokenizer.hasMoreTokens()){
                            gameCommands=clientHandler.joinMatch(name, tokenizer.nextToken(), exportedClientInterface);
                            clientJoined=true;
                        }
                        else if (clientJoined)
                            System.out.println("You are already in a game!");
                        else
                            System.out.println("You need to speicify a map name.");
                        break;
                        
                    case "move":
                        movePlayer(tokenizer);
                        break;
                       
                    case "moveattack":
                        moveAndAttack(tokenizer);
                        break;
                        
                    case "use":
                        useCard(tokenizer);
                        break;
                        
                    case "noise":
                        makeNoise(tokenizer);
                        break;
                        
                    case "discard":
                        discardCard(tokenizer);
                        break;
                             
                    case "endturn":
                        gameCommands.endTurn(clientInterface, name);
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
            System.err.println("Error!");
        } catch (NotBoundException e) {
            System.err.println("Cannot read the RMI registry!");
        } 
    }
    
    /**
     * This method execute the command discard card and calls the relative method on the server
     * 
     * @param tokenizer tokenizer to parse the remaining of the string
     * @throws RemoteException
     */
    private void discardCard(StringTokenizer tokenizer) throws RemoteException {
        if(!clientJoined){
            System.out.println(NOT_IN_GAME);
            return;
        }
        
        if(tokenizer.hasMoreTokens())
                gameCommands.discardCard(clientInterface, name, tokenizer.nextToken());
        else
            System.out.println("You need to specify a card to discard!");
        
    }

    /**
     * This method execute the command noise and calls the relative method on the server
     * 
     * @param tokenizer tokenizer to parse the remaining of the string
     * @throws RemoteException
     */
    private void makeNoise(StringTokenizer tokenizer) throws RemoteException {
        if(!clientJoined){
            System.out.println(NOT_IN_GAME);
            return;
        }
        
        int letter;
        int number;
        
        if(tokenizer.hasMoreTokens()){
            letter = Character.getNumericValue(tokenizer.nextToken().toLowerCase().charAt(0))-10;
        }
        else{
            System.out.println(NOISE_ERROR);
            return;
        }
        
        if(tokenizer.hasMoreTokens()){
            number=Integer.parseInt(tokenizer.nextToken())-1;
        }
        else{
            System.out.println(NOISE_ERROR);
            return;
        }
        
        gameCommands.makeNoise(clientInterface, name, letter, number);
        
    }

    /**
     * This method execute the command use card and calls the relative method on the server
     * 
     * @param tokenizer tokenizer to parse the remaining of the string
     * @throws RemoteException
     */
    private void useCard(StringTokenizer tokenizer) throws RemoteException {
        if(!clientJoined){
            System.out.println(NOT_IN_GAME);
            return;
        }
        
        String card="";
        int letter = 0;
        int number = 0;
        
        if(tokenizer.hasMoreTokens()){
            card=tokenizer.nextToken();
        }
        else
            System.out.println(CARD_ERROR);
        
        if(tokenizer.hasMoreTokens()){
            letter = Character.getNumericValue(tokenizer.nextToken().toLowerCase().charAt(0))-10;
        }
        
        if(tokenizer.hasMoreTokens()){
            number=Integer.parseInt(tokenizer.nextToken())-1;
        }
        
        gameCommands.useCard(clientInterface, name, card, letter, number);
        
    }
    /**
     * This method execute the command move and attack and calls the relative method on the server
     * 
     * @param tokenizer tokenizer to parse the remaining of the string
     * @throws RemoteException
     */
    private void moveAndAttack(StringTokenizer tokenizer) throws RemoteException {
        if(!clientJoined){
            System.out.println(NOT_IN_GAME);
            return;
        }
        
        int letter;
        int number;
        
        if(tokenizer.hasMoreTokens()){
            letter = Character.getNumericValue(tokenizer.nextToken().toLowerCase().charAt(0))-10;
        }
        else{
            System.out.println(MOVE_ERROR);
            return;
        }
        
        if(tokenizer.hasMoreTokens()){
            number=Integer.parseInt(tokenizer.nextToken())-1;
        }
        else{
            System.out.println(MOVE_ERROR);
            return;
        }
        
        gameCommands.moveAndAttack(clientInterface, name, letter, number);
        
    }

    /**
     * Returns the status of rmi value to check if the client is using rmi or socket
     * 
     * @return rmi boolean value
     */
    private boolean useRMI() {
        return rmi;
    }

    /**
     * This method execute the command move and calls the relative method on the server
     * 
     * @param tokenizer tokenizer to parse the remaining of the string
     * @throws RemoteException
     */
    private void movePlayer(StringTokenizer tokenizer) throws RemoteException{
        if(!clientJoined){
            System.out.println(NOT_IN_GAME);
            return;
        }
        
        int letter;
        int number;
        
        if(tokenizer.hasMoreTokens()){
            letter = Character.getNumericValue(tokenizer.nextToken().toLowerCase().charAt(0))-10;
        }
        else{
            System.out.println(MOVE_ERROR);
            return;
        }
        
        if(tokenizer.hasMoreTokens()){
            number=Integer.parseInt(tokenizer.nextToken())-1;
        }
        else{
            System.out.println(MOVE_ERROR);
            return;
        }
        
        gameCommands.movePlayer(clientInterface, name, letter, number);
        
    }
    
    /**
     * The entry point for the client.
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) {
        Client client = new Client();
        if(client.useRMI()){
            client.startRMIClient();
        }
        else
            client.startSocketClient();
    }
}
