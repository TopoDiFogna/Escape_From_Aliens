package it.polimi.ingsw.cg_23.model.cards;

import it.polimi.ingsw.cg_23.model.players.Player;

/**
 * When human uses this card he doesn't must pick-up a SectorCard. <br>
 * @author Arianna
 */
public class SedativesCard extends Card implements Action {

	/**
	 * deckType tells in what deck we must put this card (in this case 1 indicates ItemDeck).	
	 */
	protected int deckType=1;
	public SedativesCard() {
		// TODO constructor
	}
	
	/**
	 * This method implements the method in Action interface. <br>
	 * This method prevents human to pick-up card.
	 */
	@Override
	public void doAction(Player player) {
		// TODO Auto-generated method stub
	}

}
