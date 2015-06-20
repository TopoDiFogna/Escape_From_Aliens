package it.polimi.ingsw.cg_23.gui;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketConnection extends Connection {
    
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
     * The receiver to get server messages when using socket. It runs on one other thread.
     */
    private SocketClientSubscriberGui receiver;
    
    private static final int PORT = 10412;
    
    private final String nickname;
    
    private final String ip;

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

    @Override
    public void move(String letter, String number) {
        createSockets();
        socketOut.println(nickname+" move "+letter+" "+number);
        socketOut.flush();
        ChatPanel.appendMessages(socketIn.nextLine());

    }

    @Override
    public void moveAndAttack(String letter, String number) {
        createSockets();
        socketOut.println(nickname+" moveattack "+letter+" "+number);
        socketOut.flush();
        ChatPanel.appendMessages(socketIn.nextLine());

    }

    @Override
    public void makeNoise(String letter, String number) {
        createSockets();
        socketOut.println(nickname+" noise "+letter+" "+number);
        socketOut.flush();
        ChatPanel.appendMessages(socketIn.nextLine());

    }

    @Override
    public void endTurn() {
        createSockets();
        socketOut.println(nickname+" endturn");
        socketOut.flush();
        ChatPanel.appendMessages(socketIn.nextLine());

    }

    @Override
    public void chat(String msg) {
        createSockets();
        socketOut.println(nickname+" "+ msg);
        socketOut.flush();
        ChatPanel.appendMessages(socketIn.nextLine());
    }

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
