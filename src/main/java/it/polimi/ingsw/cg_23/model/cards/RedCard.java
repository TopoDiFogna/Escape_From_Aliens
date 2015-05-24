package it.polimi.ingsw.cg_23.model.cards;

import it.polimi.ingsw.cg_23.controller.GameLogic;
import it.polimi.ingsw.cg_23.model.players.Player;

/**
 * This card is picked-up by humans if they reach one of the escape hatch sectors. <br>
 * If a human picks-up this card, he must reach another escape hatch sector to save himself.
 * @author Arianna
 */
public class RedCard extends Card{
	
	/**
	 * constructor
	 */
	public RedCard() {
	}

	/**
	 * Set this escape hatch sector as unusable.
	 */
	@Override
	public void doAction(Player player, GameLogic controller) {
		controller.useRed(player);

	}

}
