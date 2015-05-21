package it.polimi.ingsw.cg_23.model.cards;

import it.polimi.ingsw.cg_23.model.players.Player;

/**
 * This card is auto-used when picked-up by players. <br>
 * This card asks the human to chose in what sector other player hear noise.
 * @author Arianna
 */
public class NoiseInAnySectorCard extends Card implements Action {
	
	/**
	 * deckType tells in what deck we must put this card (in this case 0 indicates SectorDeck).	
	 */
	protected int deckType=0;
	/**
	 * The boolean hasItem indicates if the card has the item symbol. <br>
	 * If hasItem is true the player (both humans and aliens) picks-up an Item card from ItemDeck. 
	 */
	boolean hasItem;
	public NoiseInAnySectorCard(boolean hasItem) {
		//TODO constructor
	}
	
	/**
	 * This method implements the method in Action interface. <br>
	 * This method is auto-called when player pick-up this card. <br>
	 * Asks controller to ask view to ask user in what sector there is noise.
	 */
	@Override
	public void doAction(Player player) {
		// TODO Auto-generated method stub

	}

}
