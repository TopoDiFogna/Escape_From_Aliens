package it.polimi.ingsw.cg_23.model.cards;

import it.polimi.ingsw.cg_23.model.players.Player;

/**
 * This card is picked-up by humans if they reach one of the escape hatch sectors. <br>
 * If a human picks-up this card, he wins! 
 * @author Arianna
 */
public class GreenCard extends Card {
	
	/**
	 * constructor
	 */
	public GreenCard() {
	}

	/**
	 * This method set as true human attribute escaped.
	 * Set this escape hatch sector as unusable.
	 */
	@Override
	public void doAction(Player player) {
		//TODO metodo del controller che farà player.setEscaped();
		//TODO quando esisterà il controllore qua chiamerò un metodo per togliere i player scappati dalla lista player
	}

}
