package it.polimi.ingsw.cg_23.network;

/**
 * Interface with all the methods to handle messages
 * 
 * @author Paolo
 *
 */
public interface Communicator {
    
    void send(String msg);
    
    String receive();
    
    void close();

}
