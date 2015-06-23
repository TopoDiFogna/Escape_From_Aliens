package it.polimi.ingsw.cg_23.gui;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Class that calls methods on the server.
 * 
 * @author Paolo
 */
public class SocketConnection extends Connection {
    
    /**
     * Socket used to connect to the server.
     */
    private Socket socket = null;
    
    /**
     * Scanner used to read server input.
     */
    private Scanner socketIn = null;
    
    /**
     * PrintWriter to send to the server the commands.
     */
    private PrintWriter socketOut = null;
    
    /**
     * The receiver to get server messages when using socket. It runs on one other thread.
     */
    private SocketClientSubscriberGui receiver;
    
    /**
     * Port to connect to the server.
     */
    private static final int PORT = 10412;

    /**
     * Unique identifier of the client.
     */
    private final String nickname;
    
    /**
     * Server IP address.
     */
    private final String ip;

    /**
     * The constructor. Connects to the server using sockets and join a game.
     * 
     * @param ip server ip address
     * @param nickname client identifier
     * @param mapName map name to join a match
     */
    public SocketConnection(String ip, String nickname, String mapName) {
        
        this.nickname=nickname;
        
        this.ip=ip;
        
        createSockets();
        
        socketOut.println(nickname+" join "+mapName);
        socketOut.flush();
        
        ChatPanel.appendMessages(socketIn.nextLine());
        
        receiver= new SocketClientSubscriberGui(socket);
        receiver.start();
    }

    /**
     * Sends a string corresponding to the move command.
     */
    @Override
    public void move(String letter, String number) {
        createSockets();
        socketOut.println(nickname+" move "+letter+" "+number);
        socketOut.flush();
        ChatPanel.appendMessages(socketIn.nextLine());
        getCards();
    }

    /**
     * Sends a string corresponding to the move and attack command.
     */
    @Override
    public void moveAndAttack(String letter, String number) {
        createSockets();
        socketOut.println(nickname+" moveattack "+letter+" "+number);
        socketOut.flush();
        ChatPanel.appendMessages(socketIn.nextLine());
        getCards();
    }

    /**
     * Sends a string corresponding to the noise command.
     */
    @Override
    public void makeNoise(String letter, String number) {
        createSockets();
        socketOut.println(nickname+" noise "+letter+" "+number);
        socketOut.flush();
        ChatPanel.appendMessages(socketIn.nextLine());
    }

    /**
     * Sends a string corresponding to the end turn command.
     */
    @Override
    public void endTurn() {
        createSockets();
        socketOut.println(nickname+" endturn");
        socketOut.flush();
        ChatPanel.appendMessages(socketIn.nextLine());
    }
    
    /**
     * Sends a string corresponding to the chat command.
     */
    @Override
    public void chat(String msg) {
        createSockets();
        socketOut.println(nickname+" chat "+ msg);
        socketOut.flush();
        ChatPanel.appendMessages(socketIn.nextLine());
    }
    
    /**
     * Sends a string corresponding to the use card command.
     */
    @Override
    public void useCard(String card, int letter, int number) {
        String response = "";
        createSockets();
        socketOut.println(nickname+ " " + "use "+card+ " " + letter + " " + number);
        socketOut.flush();
        response=socketIn.nextLine();
        ChatPanel.appendMessages(response);
        StringTokenizer tokenizer = new StringTokenizer(response);
        while(tokenizer.hasMoreTokens()){
            if("used".equals(tokenizer.nextToken())){
                CardsPanel.disableCard(card);
            }
        }
        getCards();
    }

    /**
     * Sends a string corresponding to the discard card command.
     */
    @Override
    public void discardCard(String card) {
        createSockets();
        socketOut.println(nickname+ " " + "discard "+card);
        socketOut.flush();
        ChatPanel.appendMessages(socketIn.nextLine()); 
        getCards();
    }
    
    /**
     * Asks the server for the cards the player has in his hand
     */
    @Override
    public void getCards() {
        createSockets();
        socketOut.println(nickname + " getcards");
        socketOut.flush();
        String response = socketIn.nextLine();
        //ChatPanel.appendMessages(response);
        StringTokenizer tokenizer = new StringTokenizer(response);
        while(tokenizer.hasMoreTokens()){
            CardsPanel.enableCard(tokenizer.nextToken().toLowerCase());
        }
    }

    /**
     * Creates communication socket.
     */
    private void createSockets(){
        try {
            socket = new Socket(ip,PORT);
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
    }
}
