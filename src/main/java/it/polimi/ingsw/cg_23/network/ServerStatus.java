package it.polimi.ingsw.cg_23.network;

import it.polimi.ingsw.cg_23.model.status.Match;

import java.util.HashMap;

public class ServerStatus {
    
    private static ServerStatus instance;
    
    private HashMap<String, Match> idLogicMap;
    
    private HashMap<Match, Broker> logicBrokerMap;
    
    private int brokerNumber = 0;

    private ServerStatus() {
        idLogicMap= new HashMap<>();
        logicBrokerMap= new HashMap<>();
    }

    public static ServerStatus getInstance(){
        if(instance==null){
            synchronized (ServerStatus.class) {
                if(instance==null){
                    instance=new ServerStatus();
                }
            }
        }
        return instance;
    }
    
    public void addPlayerToMatch(String name, Match match){
        idLogicMap.put(name, match);
    }
    
    public void addBrokerToMatch(Match match, Broker broker){
        logicBrokerMap.put(match, broker);
    }

    public HashMap<String, Match> getIdMatchMap() {
        return idLogicMap;
    }

    public HashMap<Match, Broker> getMatchBrokerMap() {
        return logicBrokerMap;
    }

    public int getBrokerNumber() {
        brokerNumber++;
        return brokerNumber;
    }
    
    
}
