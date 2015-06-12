package it.polimi.ingsw.cg_23.network.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIGameCommandsInterface extends Remote {

    public void movePlayer(RMIClientInterface clientInterface, String id, int letter, int number) throws RemoteException;
    
    public void moveAndAttack(RMIClientInterface clientInterface, String id, int letter, int number) throws RemoteException;
    
    public void useCard(RMIClientInterface clientInterface, String id, String cardUsed, int letter, int number) throws RemoteException;
    
    public void makeNoise(RMIClientInterface clientInterface, String id, int letter, int number) throws RemoteException;
    
    public void discardCard(RMIClientInterface clientInterface, String id, String cardDiscarded) throws RemoteException;
    
    public void endTurn(RMIClientInterface clientInterface, String id) throws RemoteException;
}
