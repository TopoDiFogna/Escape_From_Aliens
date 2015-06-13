package it.polimi.ingsw.cg_23.network.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This interface is not directly exposed by the server.<br>
 * It's returned when a client wants to play.
 * 
 * @author Paolo
 *
 */
public interface RMIGameCommandsInterface extends Remote {

    /**
     * Moves the player in the specified sector if he can go there
     * 
     * @param clientInterface the interface of the client used to send messages
     * @param id the unique identifier of the client
     * @param letter the letter of the sector the player wants to move to
     * @param number the number of the sector the player wants to move to
     * @throws RemoteException
     */
    public void movePlayer(RMIClientInterface clientInterface, String id, int letter, int number) throws RemoteException;
    
    /**
     * Moves the player and makes him attack in the specified sector if he can go there
     * 
     * @param clientInterface the interface of the client used to send messages
     * @param id the unique identifier of the client
     * @param letter the letter of the sector the player wants to move to
     * @param number the number of the sector the player wants to move to
     * @throws RemoteException
     */
    public void moveAndAttack(RMIClientInterface clientInterface, String id, int letter, int number) throws RemoteException;
    
    /**
     * Makes the client use a specified card
     * 
     * @param clientInterface the interface of the client used to send messages
     * @param id the unique identifier of the client
     * @param cardUsed the card the player wants to use
     * @param letter this parameter is used only when a spotlight card is used. If not used it's set to 0. Indicates the sector's letter where the player wants to use the spotlight card
     * @param number this parameter is used only when a spotlight card is used. If not used it's set to 0. Indicates the sector's number where the player wants to use the spotlight card
     * @throws RemoteException
     */
    public void useCard(RMIClientInterface clientInterface, String id, String cardUsed, int letter, int number) throws RemoteException;
    
    /**
     * Make a noise in a sector when the player needs to do that and letter and number of the sector are specified 
     * 
     * @param clientInterface the interface of the client used to send messages
     * @param id the unique identifier of the client
     * @param letter the sector's letter where the player wants to make a noise
     * @param number the sector's number where the player wants to make a noise
     * @throws RemoteException
     */
    public void makeNoise(RMIClientInterface clientInterface, String id, int letter, int number) throws RemoteException;
    
    /**
     * Makes the player discard a card. This can occur when the player has more than 3 cards in his hand
     * 
     * @param clientInterface the interface of the client used to send messages
     * @param id the unique identifier of the client
     * @param cardDiscarded the card the player wants to discard
     * @throws RemoteException
     */
    public void discardCard(RMIClientInterface clientInterface, String id, String cardDiscarded) throws RemoteException;
    
    /**
     * Makes the player end his turn
     * 
     * @param clientInterface the interface of the client used to send messages
     * @param id the unique identifier of the client
     * @throws RemoteException
     */
    public void endTurn(RMIClientInterface clientInterface, String id) throws RemoteException;
}
