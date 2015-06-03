package it.polimi.ingsw.cg_23.network.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SubscriberInterface extends Remote {
	
	public void dispatchMessage(String str) throws RemoteException;

}
