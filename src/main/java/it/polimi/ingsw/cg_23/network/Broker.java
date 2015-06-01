package it.polimi.ingsw.cg_23.network;

import java.util.ArrayList;
import java.util.List;

public class Broker{
    
    private String topic;

    private List<BrokerThread> subscribers;
    
    public Broker(String topic) {
        this.topic = topic;
        subscribers = new ArrayList<BrokerThread>();
    }
    
    public void publish(String msg){
        if(!subscribers.isEmpty()){
            System.out.println("Publishing message: "+msg);
            for(BrokerThread subscriber : subscribers){
                subscriber.dispatchMessage(msg);
            }
        }
    }

    public String getTopic() {
        return topic;
    }
    
    public void addSubscriber(BrokerThread brokerThread){
        subscribers.add(brokerThread);
    }
}
