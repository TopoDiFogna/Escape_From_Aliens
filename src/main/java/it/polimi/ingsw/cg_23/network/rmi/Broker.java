package it.polimi.ingsw.cg_23.network.rmi;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Broker implements BrokerInterface{

	private  List<SubscriberInterface> subscribers = new ArrayList<SubscriberInterface>();
	private String topic;
	
	public Broker(String topic){
		this.topic = topic;
	}
	
	/**
	 * 
	 * @param msg - message to be published to all the subscribers
	 * This is not a remote method, however it calls the remote 
	 * method dispatchMessage for each Subscriber.  
	 */
	public void publish(String msg){
		if(!subscribers.isEmpty()){
			System.out.println("Publishing message on topic "+topic);
			for (SubscriberInterface sub : subscribers) {
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
	 * @param r is the Subcriber's remote interface that the broker can use to publish messages
	 * The method updates the list of subscriber interfaces that are subscribed to the broker
	 */
	@Override
	public void subscribe(SubscriberInterface r) {
		subscribers.add(r);
	}
}
