package it.polimi.ingsw.cg_23.network.rmi;

import it.polimi.ingsw.cg_23.model.cards.Card;

public interface RMIGameCommandsInterface {

    public void movePlayer(RMIClientInterface clientInterface, String id, int letter, int number);
    
    public void moveAndAttack(RMIClientInterface clientInterface, String id, int letter, int number);
    
    public void useCard(RMIClientInterface clientInterface, String id, Card card);
    
    public void makeNoise(RMIClientInterface clientInterface, String id, int letter, int number);
    
    public void discardCard(RMIClientInterface clientInterface, String id, Card card);
    
    public void endTurn(RMIClientInterface clientInterface, String id);
}
