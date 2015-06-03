package it.polimi.ingsw.cg_23.network.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIClientHandlerInterface extends Remote{
    
    public void getGameList(RMIClientInterface clientInterface) throws RemoteException;
    
    public void joinGame(RMIClientInterface clientIterface) throws RemoteException;

}
