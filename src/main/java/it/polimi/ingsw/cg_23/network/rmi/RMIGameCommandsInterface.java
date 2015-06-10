package it.polimi.ingsw.cg_23.network.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import it.polimi.ingsw.cg_23.model.cards.Card;

public interface RMIGameCommandsInterface extends Remote {

    public void movePlayer(RMIClientInterface clientInterface, String id, int letter, int number) throws RemoteException;
    
    public void moveAndAttack(RMIClientInterface clientInterface, String id, int letter, int number) throws RemoteException;
    
    public void useCard(RMIClientInterface clientInterface, String id, Card card, int letter, int number) throws RemoteException;
    
    public void makeNoise(RMIClientInterface clientInterface, String id, int letter, int number) throws RemoteException;
    
    public void discardCard(RMIClientInterface clientInterface, String id, Card card) throws RemoteException;
    
    public void endTurn(RMIClientInterface clientInterface, String id) throws RemoteException;
}
