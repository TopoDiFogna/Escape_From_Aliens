package it.polimi.ingsw.cg_23.network.socket;

import java.util.ArrayList;
import java.util.List;

public class SocketBroker{
    
    private String topic;

    private List<BrokerThread> subscribers;
    
    public SocketBroker(String topic) {
        this.topic = topic;
        subscribers = new ArrayList<BrokerThread>();
    }
    
    public void publish(String msg){
        if(!subscribers.isEmpty()){
            System.out.println("SERVER: Broadcast: "+msg);
            for(BrokerThread subscriber : subscribers){
                subscriber.dispatchMessage(msg);
            }
        }
        else{
            System.err.println("No subscribers!!");
        }
    }


    public String getTopic() {
        return topic;
    }
    
    public void addSubscriber(BrokerThread brokerThread){
        subscribers.add(brokerThread);
    }
}
