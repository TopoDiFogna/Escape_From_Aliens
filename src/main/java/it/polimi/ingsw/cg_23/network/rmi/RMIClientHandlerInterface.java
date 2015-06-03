package it.polimi.ingsw.cg_23.network.rmi;

import java.rmi.Remote;

public interface RMIClientHandlerInterface extends Remote{
    
    public void getGameList(RMIClientInterface clientInterface);
    
    public void joinGame(RMIClientInterface clientIterface);

}
