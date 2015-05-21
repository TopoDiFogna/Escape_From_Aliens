package it.polimi.ingsw.cg_23.model.cards;

import it.polimi.ingsw.cg_23.model.players.Player;

/**
 * This card is picked-up by humans if they reach one of the escape hatch sectors. <br>
 * If a human picks-up this card, he must reach another escape hatch sector to save himself.
 * @author Arianna
 */
public class RedCard extends Card implements Action {
	
	/**
	 * deckType tells in what deck we must put this card (in this case 2 indicates EscapeHatchDeck).	
	 */
	protected int deckType=2;
	public RedCard() {
		// TODO cosntructor
	}

	/**
	 * This method implements the method in Action interface. <br>
	 * Set this escape hatch sector as unusable.
	 */
	@Override
	public void doAction(Player player) {
		// TODO dobbiamo settare a "non utilizzabile" questo settore scialuppa.

	}

}
