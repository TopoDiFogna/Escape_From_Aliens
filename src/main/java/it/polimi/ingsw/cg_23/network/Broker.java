package it.polimi.ingsw.cg_23.network;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Broker{
    
    private Logger logger = Logger.getLogger("logger");
    
    private String topic;

    private List<BrokerThread> subscribers;
    
    public Broker(String topic) {
        this.topic = topic;
        subscribers = new ArrayList<BrokerThread>();
    }
    
    public void publish(String msg){
        if(!subscribers.isEmpty()){
            logger.info("SERVER: Broadcast: "+msg);
            for(BrokerThread subscriber : subscribers){
                subscriber.dispatchMessage(msg);
            }
        }
        else{
            logger.warning("No subscribers!!");
        }
    }


    public String getTopic() {
        return topic;
    }
    
    public void addSubscriber(BrokerThread brokerThread){
        subscribers.add(brokerThread);
    }
}
