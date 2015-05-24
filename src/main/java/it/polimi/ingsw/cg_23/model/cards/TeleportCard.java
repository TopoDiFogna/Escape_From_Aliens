package it.polimi.ingsw.cg_23.model.cards;

import it.polimi.ingsw.cg_23.controller.GameLogic;
import it.polimi.ingsw.cg_23.model.players.Player;

/**
 * If human uses this card, he is teleport at HumanSector (the starting sector of humans) 
 * @author Arianna
 */
public class TeleportCard extends Card{
	
	/**
	 * constructor
	 */
	public TeleportCard() {
	}

	/**
	 * The humans is moved to the starting human sector.
	 */
	@Override
	public void doAction(Player player, GameLogic controller) {
		controller.useTeleport(player);
	}

}
