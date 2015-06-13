package it.polimi.ingsw.cg_23.network;

import it.polimi.ingsw.cg_23.model.status.Match;
import it.polimi.ingsw.cg_23.network.rmi.RMIBroker;
import it.polimi.ingsw.cg_23.network.socket.SocketBroker;

import java.util.HashMap;
import java.util.Map;

/**
 * This class handles all the matches, brokers and players that are in the game.
 * 
 * @author Paolo
 *
 */
public class ServerStatus {
    
    /**
     * The instance of this class since this is a singleton
     */
    private static ServerStatus instance;
    
    /**
     * Map for mapping unic client id and hia match
     */
    private Map<String, Match> idLogicMap;
    
    /**
     * Mapping for every match with its socket broker
     */
    private Map<Match, SocketBroker> logicSocketBrokerMap;
    
    /**
     * Mapping for every match with its rmi broker
     */
    private Map<Match, RMIBroker> logicRMIBrokerMap;
    
    /**
     * Number used to make broker topics unique
     */
    private int brokerNumber = 0;

    /**
     * Initializes every map used
     */
    private ServerStatus() {
        idLogicMap= new HashMap<>();
        logicSocketBrokerMap= new HashMap<>();
        logicRMIBrokerMap= new HashMap<>();
    }

    /**
     * This method returns the only instance of this class or creates it if it doesn't exists
     * 
     * @return the only instance of this class
     */
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
    
    /**
     * Adds the unique id of a player to a match
     * 
     * @param name the unique id of the player
     * @param match match the player is added to
     */
    public void addPlayerToMatch(String name, Match match){
        idLogicMap.put(name, match);
    }
    
    /**
     * Pairs every match with his socket broker
     * 
     * @param match match to be paired
     * @param broker broker associated to the match
     */
    public void addSocketBrokerToMatch(Match match, SocketBroker broker){
        logicSocketBrokerMap.put(match, broker);
    }

    /**
     * Returns the entire map player id match
     * 
     * @return the map linking every player to his match
     */
    public Map<String, Match> getIdMatchMap() {
        return idLogicMap;
    }

    /**
     * Returns the entire map match, socket broker
     * 
     * @return the map linking every match to its socket broker
     */
    public Map<Match, SocketBroker> getMatchSocketBrokerMap() {
        return logicSocketBrokerMap;
    }
    
    /**
     * Returns the entire map match, rmi broker
     * 
     * @return the map linking every match to its rmi broker
     */
    public Map<Match, RMIBroker> getMatchRMIBrokerMap() {
        return logicRMIBrokerMap;
    }
    
    /**
     * Pairs every match with his rmi broker
     * 
     * @param match match to be paired
     * @param broker broker associated to the match
     */
   public void addRMIBrokerToMatch(Match match, RMIBroker broker){
        logicRMIBrokerMap.put(match, broker);
    }

   /**
    * This method makes sure that every broker has a unique id first incrementing the number used for id then returning it
    * 
    * @return the unique id of the next broker
    */
    public int getBrokerNumber() {
        brokerNumber++;
        return brokerNumber;
    }
}
