package it.polimi.ingsw.cg_23.model.cards;

import it.polimi.ingsw.cg_23.model.players.Player;

/**
 * The adrenaline card gives humans to move 2 sectors instead 1. It must be used before move.
 * 
 * @author Arianna
 */
public class AdrenalineCard extends Card {
	
	/**
	 * constructor
	 */
	public AdrenalineCard() {
	}
	
	/**
	 * In this case gives the player the possibility to move 2 sectors instead 2.
	 */
	@Override
	public void doAction(Player player) {
		player.setCanMoveFaster(true);
	}

}
