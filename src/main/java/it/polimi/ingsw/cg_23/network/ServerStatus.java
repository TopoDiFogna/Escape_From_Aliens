package it.polimi.ingsw.cg_23.network;

import it.polimi.ingsw.cg_23.model.status.Match;
import it.polimi.ingsw.cg_23.network.rmi.RMIBroker;
import it.polimi.ingsw.cg_23.network.socket.SocketBroker;

import java.util.HashMap;

public class ServerStatus {
    
    private static ServerStatus instance;
    
    private HashMap<String, Match> idLogicMap;
    
    private HashMap<Match, SocketBroker> logicSocketBrokerMap;
    
    private int brokerNumber = 0;

    private HashMap<Match, RMIBroker> logicRMIBrokerMap;

    private ServerStatus() {
        idLogicMap= new HashMap<>();
        logicSocketBrokerMap= new HashMap<>();
        logicRMIBrokerMap= new HashMap<>();
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
    
    public void addSocketBrokerToMatch(Match match, SocketBroker broker){
        logicSocketBrokerMap.put(match, broker);
    }

    public HashMap<String, Match> getIdMatchMap() {
        return idLogicMap;
    }

    public HashMap<Match, SocketBroker> getMatchSocketBrokerMap() {
        return logicSocketBrokerMap;
    }
    
    public HashMap<Match, RMIBroker> getMatchRMIBrokerMap() {
        return logicRMIBrokerMap;
    }
    
   public void addRMIBrokerToMatch(Match match, RMIBroker broker){
        logicRMIBrokerMap.put(match, broker);
    }

    public int getBrokerNumber() {
        brokerNumber++;
        return brokerNumber;
    }
    
    
}
