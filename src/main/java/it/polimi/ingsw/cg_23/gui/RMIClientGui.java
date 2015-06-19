package it.polimi.ingsw.cg_23.gui;

import java.rmi.RemoteException;

import it.polimi.ingsw.cg_23.network.rmi.RMIClientInterface;

public class RMIClientGui implements RMIClientInterface {

    public RMIClientGui() {
        // no need to do things in here
    }

    @Override
    public void dispatchMessage(String msg) throws RemoteException {
        ChatPanel.appendMessages(msg);

    }

}
