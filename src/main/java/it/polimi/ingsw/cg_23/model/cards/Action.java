package it.polimi.ingsw.cg_23.model.cards;

import it.polimi.ingsw.cg_23.model.players.Player;

/**
 * This interface has the signatures of the doAction method.
 * 
 * @author Arianna
 */
public interface Action {
	
	/**
	 * This method is implemented in every card classes with different actions. 
	 * @param player who uses the card
	 */	
	public abstract void doAction(Player player);

}
