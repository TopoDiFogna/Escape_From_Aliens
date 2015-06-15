package it.polimi.ingsw.cg_23.network.socket;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the broker thread. Manages all the broker threads.
 * 
 * @author Paolo
 *
 */
public class SocketBroker{

    /**
     * The unique topic of the broker.
     */
    private String topic;

    /**
     * List of all subscribers
     */
    private List<BrokerThread> subscribers;
    
    /**
     * Constructor. Initializes the list of subscribers and the topic
     * 
     * @param topic
     */
    public SocketBroker(String topic) {
        this.topic = topic;
        subscribers = new ArrayList<BrokerThread>();
    }
    
    /**
     * Sends a message to every subscriber in the list
     * 
     * @param msg the message to be send
     */
    public void publish(String msg){
        if(!subscribers.isEmpty()){
            System.out.println("SERVER: Broadcast: "+msg);
            for(BrokerThread subscriber : subscribers){
                subscriber.dispatchMessage(msg);
            }
        }
        else{
            System.out.println("No subscribers!!");
        }
    }

    /**
     * Returns the topic of the broker
     * 
     * @return string with the topic of the broker
     */
    public String getTopic() {//not really needed
        return topic;
    }
    
    /**
     * Adds a subscriber to the list
     * 
     * @param brokerThread the subscriber to add
     */
    public void addSubscriber(BrokerThread brokerThread){
        subscribers.add(brokerThread);
    }
}
