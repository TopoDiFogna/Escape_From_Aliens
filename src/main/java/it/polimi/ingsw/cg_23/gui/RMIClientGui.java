package it.polimi.ingsw.cg_23.gui;

import java.rmi.RemoteException;
import java.util.StringTokenizer;

import it.polimi.ingsw.cg_23.network.rmi.RMIClientInterface;

/**
 * This class contains the methods exposed to the server.
 * 
 * @author Paolo
 */
public class RMIClientGui implements RMIClientInterface {

    /**
     * The constructor.
     */
    public RMIClientGui() {
        // no need to do things in here
    }

    /**
     * Prints a message on the chat panel.
     */
    @Override
    public void dispatchMessage(String msg) throws RemoteException {
        ChatPanel.appendMessages(msg);
        StringTokenizer tokenizer = new StringTokenizer(msg);
        while(tokenizer.hasMoreTokens()){
            CardsPanel.enableCard(tokenizer.nextToken().toLowerCase());
        }
    }
}
