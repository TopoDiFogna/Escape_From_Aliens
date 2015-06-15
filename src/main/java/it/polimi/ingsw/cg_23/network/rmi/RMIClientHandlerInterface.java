package it.polimi.ingsw.cg_23.network.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface exposed when the server starts.
 * 
 * @author Paolo
 *
 */
public interface RMIClientHandlerInterface extends Remote{
    
    /**
     * Method called from remote.<br>
     * Gets the game list from the server status and returns it to the client
     * 
     * @param clientInterface the interface of the client to end messages
     * @throws RemoteException
     */
    public void getGameList(RMIClientInterface clientInterface) throws RemoteException;
    
    /**
     * Method called from remote.<br>
     * First checks if a player is already in a game, if not, creates a new match if non available or makes the client join a match with the specified map.
     * 
     * @param id the unique identifier of the client
     * @param mapName the name of the map the client wants to play
     * @param clientInterface the interface of the client to send messages
     * @return the interface that makes the client aware of the methods used to play
     * @throws RemoteException
     */
    public RMIGameCommandsInterface joinMatch(String id, String mapName, RMIClientInterface clientInterface) throws RemoteException;

}
