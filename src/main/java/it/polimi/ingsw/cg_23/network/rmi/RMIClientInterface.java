package it.polimi.ingsw.cg_23.network.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIClientInterface extends Remote {
    
    public void getName() throws RemoteException;
    
    public void dispatchMessage(String msg) throws RemoteException;
}
