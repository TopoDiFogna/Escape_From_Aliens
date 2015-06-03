package it.polimi.ingsw.cg_23.network.rmi;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BrokerInterface extends Remote {

	public void subscribe(SubscriberInterface r) throws RemoteException;
	
}
