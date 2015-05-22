package it.polimi.ingsw.cg_23.entrypoint;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Client {

    private final int port;
    private final String ip;
    
    public Client() {
        System.out.println("inserisci indirizzo ip");
        Scanner stdin = new Scanner(System.in);
        ip = stdin.nextLine();
        System.out.println("inserisci porta");
        port=stdin.nextInt();
        
    }
    
    public void startClient(){
                
        Socket socket = null;
        Scanner SocketIn = null;
        PrintWriter socketOut = null;
        
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
            SocketIn = new Scanner(socket.getInputStream());
        } catch (IOException e) {
            System.err.println("ERROR: Stream error!");
        }
        
        try {
            socketOut = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        Scanner stdin = new Scanner(System.in);
        
        /*while(true){
            
            String inputLine = stdin.nextLine();
            
            socketOut.println(inputLine);
            socketOut.flush();
            
            String serverMessage = SocketIn.nextLine();
            System.out.println(serverMessage);
        }*/
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.startClient();

    }

}