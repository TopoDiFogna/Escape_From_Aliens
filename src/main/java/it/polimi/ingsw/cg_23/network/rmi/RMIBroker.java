package it.polimi.ingsw.cg_23.network.rmi;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the broker for a match.<br>
 * A broker sends messages to all its subscribers.
 * 
 * @author Paolo
 *
 */
public class RMIBroker implements RMIBrokerInterface{

    /**
     * List of all subscribers for the broker
     */
	private  List<RMIClientInterface> subscribers = new ArrayList<RMIClientInterface>();
	
	/**
	 * Topic for the broker. It's unique for every broker
	 */
	private String topic;
	
	/**
	 * Constructor. Saves this topic in a variable.
	 * 
	 * @param topic
	 */
	public RMIBroker(String topic){
		this.topic = topic;
	}
	
	/**
	 * Sends a message to all subscribers
	 * This is not a remote method, however it calls the remote 
     * method dispatchMessage for each Subscriber. 
	 * 
	 * @param msg - message to be published to all the subscribers 
	 */
	public void publish(String msg){
		if(!subscribers.isEmpty()){
			System.out.println("Publishing message on topic "+topic);
			for (RMIClientInterface sub : subscribers) {
				try {
					sub.dispatchMessage(msg);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		}else{
			System.err.println("No subscribers!!");
		}
	}

	/**
	 * The method updates the list of subscriber interfaces that are subscribed to the broker
	 */
	@Override
	public void subscribe(RMIClientInterface r) {
		subscribers.add(r);
	}
}
