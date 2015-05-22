package it.polimi.ingsw.cg_23.model.cards;

import it.polimi.ingsw.cg_23.model.players.Player;

/**
 * If player uses this card, he must chose a sector. We tells to all players who is in this and nearby sector.
 * @author Arianna
 */
public class SpotlightCard extends Card{
	
	/**
	 * constructor
	 */
	public SpotlightCard() {
	}
	
	/**
	 * This method asks controller to ask view to ask human player the sector. <br>
	 * After that the controller check if in the selected sector and in the nearby there are someone. <br>
	 * If this sector aren't empty the model notify the view that communicate the position of this players. 
	 */
	@Override
	public void doAction(Player player) {
		// TODO il controller prende il settore dall'utente, e vede se nel settore e in quelli vicini c'è qualcuno e lo notifica alla view che lo stampa

	}

}