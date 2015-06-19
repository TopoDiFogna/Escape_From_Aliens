package it.polimi.ingsw.cg_23.gui;

import it.polimi.ingsw.cg_23.network.rmi.RMIClientHandlerInterface;
import it.polimi.ingsw.cg_23.network.rmi.RMIClientInterface;
import it.polimi.ingsw.cg_23.network.rmi.RMIGameCommandsInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIConnection extends Connection {
    
    /**
     * The class containing methods exposed to the server to get responses when using rmi
     */
    private RMIClientGui clientInterface;
    
    /**
     * Where the interface containing the game commands is saved
     */
    private RMIGameCommandsInterface gameCommands=null;
    
    RMIClientInterface exportedClientInterface;
    
    private static final int PORT = 1099;
    
    private String nickname;
    
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

    @Override
    public void move(String letter, String number) {
        
        int letterAsInt = Character.getNumericValue(letter.toLowerCase().charAt(0))-10;
        int numberAsInt;
        try{
            numberAsInt = (Character.getNumericValue(number.toLowerCase().charAt(0))*10)+(Character.getNumericValue(number.toLowerCase().charAt(1)))-1;
        }catch (IndexOutOfBoundsException e){
            numberAsInt = Character.getNumericValue(number.toLowerCase().charAt(0))-1;
        }
        System.out.println(letterAsInt+" " +numberAsInt);
        
        try {
            gameCommands.movePlayer(exportedClientInterface, nickname, letterAsInt, numberAsInt);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void moveAndAttack(String letter, String number) {
        
        int letterAsInt = Character.getNumericValue(letter.toLowerCase().charAt(0))-10;
        int numberAsInt;
        try{
            numberAsInt = (Character.getNumericValue(number.toLowerCase().charAt(0))*10)+(Character.getNumericValue(number.toLowerCase().charAt(1)))-1;
        }catch (IndexOutOfBoundsException e){
            numberAsInt = Character.getNumericValue(number.toLowerCase().charAt(0))-1;
        }

        System.out.println(letterAsInt+" " +numberAsInt);
        
        try {
            gameCommands.moveAndAttack(exportedClientInterface, nickname, letterAsInt, numberAsInt);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void makeNoise(String letter, String number) {
        
        int letterAsInt = Character.getNumericValue(letter.toLowerCase().charAt(0))-10;
        int numberAsInt;
        try{
            numberAsInt = (Character.getNumericValue(number.toLowerCase().charAt(0))*10)+(Character.getNumericValue(number.toLowerCase().charAt(1)))-1;
        }catch (IndexOutOfBoundsException e){
            numberAsInt = Character.getNumericValue(number.toLowerCase().charAt(0))-1;
        }
        
        System.out.println(letterAsInt+" " +numberAsInt);
        
        try {
            gameCommands.makeNoise(exportedClientInterface, nickname, letterAsInt, numberAsInt);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void endTurn() {
        // TODO Auto-generated method stub

    }

    @Override
    public void chat(String msg) {
        // TODO Auto-generated method stub

    }

}
