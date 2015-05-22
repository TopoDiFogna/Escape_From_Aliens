package it.polimi.ingsw.cg_23.model.cards;

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
	public void doAction(Player player) {
		//TODO player.setCurrentSector(getHumanSector);
		//dobbiamo capire come prendere il settore umano, visto che nella mappa quel metodo non è statico e non possso metterlo statico
	}

}