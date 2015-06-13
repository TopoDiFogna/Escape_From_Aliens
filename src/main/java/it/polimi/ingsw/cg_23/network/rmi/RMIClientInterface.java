package it.polimi.ingsw.cg_23.network.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The interface the client expose to the server
 * 
 * @author Paolo
 *
 */
public interface RMIClientInterface extends Remote {
    
    /**
     * This method receive the message from the server and prints it on the console
     * 
     * @param msg the message received from the server
     * @throws RemoteException
     */
    public void dispatchMessage(String msg) throws RemoteException;
}
