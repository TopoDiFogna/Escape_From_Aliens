package it.polimi.ingsw.cg_23.gui;

import it.polimi.ingsw.cg_23.network.rmi.RMIClientHandlerInterface;
import it.polimi.ingsw.cg_23.network.rmi.RMIClientInterface;
import it.polimi.ingsw.cg_23.network.rmi.RMIGameCommandsInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Class that calls methods on the server.
 * 
 * @author Paolo
 */
public class RMIConnection extends Connection {
    
    private static final String ERR = "Cannot contact the server.";
    
    /**
     * The interface containing methods exposed to the server to get responses when using rmi.
     */
    private RMIClientGui clientInterface;
    
    /**
     * Where the interface containing the game commands is saved.
     */
    private RMIGameCommandsInterface gameCommands=null;
    
    /**
     * Interface exposed to the server.
     */
    private RMIClientInterface exportedClientInterface;
    
    /**
     * Port to connect to the server.
     */
    private static final int PORT = 1099;
    
    /**
     * Unique identifier of the client.
     */
    private String nickname;
    
    /**
     * The constructor. Locates the registry on the specified ip, port, saves the server interface and joins a match
     * 
     * @param ip server ip
     * @param nickname client identifier
     * @param mapName the name of the map for the match
     */
    public RMIConnection(String ip, String nickname, String mapName) {
        
        this.nickname=nickname;
        
        clientInterface = new RMIClientGui();
        
        try {
            Registry registry = LocateRegistry.getRegistry(ip,PORT);
            
            RMIClientHandlerInterface clientHandler = (RMIClientHandlerInterface) registry.lookup("game");
                        
            exportedClientInterface = (RMIClientInterface)UnicastRemoteObject.exportObject(clientInterface,0);
            
            gameCommands=clientHandler.joinMatch(nickname, mapName, exportedClientInterface);
    
        } catch (RemoteException e) {
            System.err.println("Error!");
        } catch (NotBoundException e) {
            System.err.println("Cannot read the RMI registry!");
        } 
    }

    /**
     * Calls the method move on the server interface.
     */
    @Override
    public void move(String letter, String number) {
        
        int letterAsInt = Character.getNumericValue(letter.toLowerCase().charAt(0))-10;
        int numberAsInt;
        try{
            numberAsInt = (Character.getNumericValue(number.toLowerCase().charAt(0))*10)+(Character.getNumericValue(number.toLowerCase().charAt(1)))-1;
        }catch (IndexOutOfBoundsException e){
            numberAsInt = Character.getNumericValue(number.toLowerCase().charAt(0))-1;
        }
        
        try {
            gameCommands.movePlayer(exportedClientInterface, nickname, letterAsInt, numberAsInt);
        } catch (RemoteException e) {
            System.err.println(ERR);
        }
        getCards();
    }
    
    /**
     * Calls the method move and attack on the server interface.
     */
    @Override
    public void moveAndAttack(String letter, String number) {
        
        int letterAsInt = Character.getNumericValue(letter.toLowerCase().charAt(0))-10;
        int numberAsInt;
        try{
            numberAsInt = (Character.getNumericValue(number.toLowerCase().charAt(0))*10)+(Character.getNumericValue(number.toLowerCase().charAt(1)))-1;
        }catch (IndexOutOfBoundsException e){
            numberAsInt = Character.getNumericValue(number.toLowerCase().charAt(0))-1;
        }
        
        try {
            gameCommands.moveAndAttack(exportedClientInterface, nickname, letterAsInt, numberAsInt);
        } catch (RemoteException e) {
            System.err.println(ERR);
        }
        getCards();
    }

    /**
     * Calls the method noise on the server interface.
     */
    @Override
    public void makeNoise(String letter, String number) {
        
        int letterAsInt = Character.getNumericValue(letter.toLowerCase().charAt(0))-10;
        int numberAsInt;
        try{
            numberAsInt = (Character.getNumericValue(number.toLowerCase().charAt(0))*10)+(Character.getNumericValue(number.toLowerCase().charAt(1)))-1;
        }catch (IndexOutOfBoundsException e){
            numberAsInt = Character.getNumericValue(number.toLowerCase().charAt(0))-1;
        }
        
        try {
            gameCommands.makeNoise(exportedClientInterface, nickname, letterAsInt, numberAsInt);
        } catch (RemoteException e) {
            System.err.println(ERR);
        }
    }

    /**
     * Calls the method end turn on the server interface.
     */
    @Override
    public void endTurn() {
        try {
            gameCommands.endTurn(exportedClientInterface, nickname);
        } catch (RemoteException e) {
            System.err.println(ERR);
        }
    }

    /**
     * Calls the method chat on the server interface.
     */
    @Override
    public void chat(String msg) {
        try {
            gameCommands.chat(exportedClientInterface, nickname, msg);
        } catch (RemoteException e) {
            System.err.println(ERR);
        }
    }

    /**
     * Calls the method use card on the server interface.
     */
    @Override
    public void useCard(String card, int letter, int number) {
        
        try {
            gameCommands.useCard(exportedClientInterface, nickname, card, letter, number);
        } catch (RemoteException e) {
            System.err.println(ERR);
        }        
    }

    /**
     * Calls the method discard card on the server interface.
     */
    @Override
    public void discardCard(String card) {
        try {
            gameCommands.discardCard(exportedClientInterface, nickname, card);
        } catch (RemoteException e) {
            System.err.println(ERR);
        }        
    }

    /**
     * Asks the server for the cards the player has in his hand
     */
    @Override
    public void getCards() {
        try {
            gameCommands.getCards(clientInterface, nickname);
        } catch (RemoteException e) {
            System.err.println(ERR);
        }   
    }
}
