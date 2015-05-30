package it.polimi.ingsw.cg_23.network;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Broker implements Runnable{
    
    private String topic;

    private List<BrokerThread> subscribers;
    
    public Broker(String topic) {
        this.topic = topic;
        subscribers = new ArrayList<>();
    }
    
    public void publish(String msg){
        if(!subscribers.isEmpty()){
            System.out.println("Publishing message");
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

    @Override
    public void run() {
        
        Scanner in = new Scanner(System.in);

        while(true){
            String msg = in.nextLine();
            publish(msg);
        }        
    }

}
