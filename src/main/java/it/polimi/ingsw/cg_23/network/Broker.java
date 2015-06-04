package it.polimi.ingsw.cg_23.network;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Broker{
    private static PrintStream out=new PrintStream(System.out);
    private static final Logger LOGGER = Logger.getLogger("EscapeFromAliensLogger");
    private String topic;

    private List<BrokerThread> subscribers;
    
    public Broker(String topic) {
        this.topic = topic;
        subscribers = new ArrayList<BrokerThread>();
    }
    
    public void publish(String msg){
        if(!subscribers.isEmpty()){
            out.println("SERVER: Broadcast: "+msg);
            for(BrokerThread subscriber : subscribers){
                subscriber.dispatchMessage(msg);
            }
        }
        else{
            LOGGER.warning("No subscribers!!");
        }
    }


    public String getTopic() {
        return topic;
    }
    
    public void addSubscriber(BrokerThread brokerThread){
        subscribers.add(brokerThread);
    }
}
