package it.polimi.ingsw.cg_23.model.cards;

import it.polimi.ingsw.cg_23.model.players.Player;

/**
 * When human uses this card he doesn't must pick-up a SectorCard. <br>
 * @author Arianna
 */
public class SedativesCard extends Card {

	/**
	 * constructor
	 */
	public SedativesCard() {
	}
	
	/**
	 * This method prevents human to pick-up card.
	 */
	@Override
	public void doAction(Player player) {
		// TODO nel controller è come se passassi su un settore sicuro (dopo il controllo del tipo di settore di destinazione si fa un IF: per controllare se prima ès tata usata questa carta 
	}

}
