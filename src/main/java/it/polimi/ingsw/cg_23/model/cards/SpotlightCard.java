package it.polimi.ingsw.cg_23.model.cards;

import it.polimi.ingsw.cg_23.model.players.Player;

/**
 * If player uses this card, he must chose a sector. We tells to all players who is in this and nearby sector.
 * @author Arianna
 */
public class SpotlightCard extends Card implements Action {
	
	/**
	 * deckType tells in what deck we must put this card (in this case 1 indicates ItemDeck).	
	 */
	protected int deckType=1;
	public SpotlightCard() {
		// TODO constructor
	}
	
	/**
	 * This method implements the method in Action interface. <br>
	 * This method asks controller to ask view to ask human player the sector. <br>
	 * After that the controller check if in the selected sector and in the nearby there are someone. <br>
	 * If this sector aren't empty the model notify the view that communicate the position of this players. 
	 */
	@Override
	public void doAction(Player player) {
		// TODO Auto-generated method stub

	}

}
