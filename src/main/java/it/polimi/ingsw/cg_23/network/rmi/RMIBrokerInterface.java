package it.polimi.ingsw.cg_23.network.rmi;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIBrokerInterface extends Remote {

	public void subscribe(RMIClientInterface r) throws RemoteException;
	
}
