package it.polimi.ingsw.cg_23.model.cards;

import it.polimi.ingsw.cg_23.model.players.Player;

/**
 * The adrenaline card gives humans to move 2 sectors instead 1. It must be used before move.
 * 
 * @author Arianna
 */
public class AdrenalineCard extends Card implements Action {
	
	/**
	 * deckType tells in what deck we must put this card (in this case 1 indicates ItemDeck).	
	 */
	protected int deckType=1;
	public AdrenalineCard() {
		// TODO constructor
	}
	
	/**
	 * This method implements the method in Action interface. <br>
	 * In this case gives the player the possibility to move 2 sectors instead 2.
	 */
	@Override
	public void doAction(Player player) {
		// TODO Player.setCanMoveFaster(true);

	}

}
