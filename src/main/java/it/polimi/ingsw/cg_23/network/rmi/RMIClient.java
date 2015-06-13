package it.polimi.ingsw.cg_23.network.rmi;

import java.rmi.RemoteException;

/**
 * This class is handles the client methods exposed to the server
 * 
 * @author Paolo
 *
 */
public class RMIClient implements RMIClientInterface {

    /**
     * Constructor
     */
    public RMIClient() {
        // empty. no need to instantiate variables.
    }

    /**
     * Prints the message sent by the server
     */
    @Override
    public void dispatchMessage(String msg) throws RemoteException {
        System.out.println("Received message: "+msg);

    }

}
