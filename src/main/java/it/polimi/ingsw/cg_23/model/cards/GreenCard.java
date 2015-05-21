package it.polimi.ingsw.cg_23.model.cards;

import it.polimi.ingsw.cg_23.model.players.Player;

/**
 * This card is picked-up by humans if they reach one of the escape hatch sectors. <br>
 * If a human picks-up this card, he wins! 
 * @author Arianna
 */
public class GreenCard extends Card implements Action {
	
	/**
	 * deckType tells in what deck we must put this card (in this case 2 indicates EscapeHatchDeck).	
	 */
	protected int deckType=2;
	public GreenCard() {
		// TODO constructor
	}

	/**
	 * This method implements the method in Action interface. <br>
	 * This method set as true human attribute escaped.
	 * Set this escape hatch sector as unusable.
	 */
	@Override
	public void doAction(Player player) {
		// TODO Player.Human.setEscaped(true);
		//oltre a dirgli che è scappato, dobbiamo pensare a come gestire l'eliminazione del giocatore dalla partita, ora che ha vinto
		//e dobbiamo settare a "non utilizzabile" questo settore scialuppa.

	}

}
