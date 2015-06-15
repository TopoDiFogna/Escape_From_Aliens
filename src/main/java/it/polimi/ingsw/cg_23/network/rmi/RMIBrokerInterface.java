package it.polimi.ingsw.cg_23.network.rmi;

/**
 * Interface for the rmi broker.
 * 
 * @author Paolo
 *
 */
public interface RMIBrokerInterface {

    /**
     * The method updates the list of subscriber interfaces that are subscribed to the broker
     * 
     * @param r is the Subcriber's remote interface that the broker can use to publish messages
     */
    public void subscribe(RMIClientInterface r);

}
