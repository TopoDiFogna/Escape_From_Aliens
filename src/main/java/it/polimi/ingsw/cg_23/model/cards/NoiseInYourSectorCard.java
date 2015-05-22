package it.polimi.ingsw.cg_23.model.cards;

import it.polimi.ingsw.cg_23.model.players.Player;

/**
 * This card is auto-used when picked-up by players. <br>
 * This card tells automatically other player where is the noise. <br>
 * We don't need to ask player where is the noise, because the noise is the player sector.
 * @author Arianna
 */
public class NoiseInYourSectorCard extends Card implements Action {
	
	/**
	 * deckType tells in what deck we must put this card (in this case 0 indicates SectorDeck).	
	 */
	protected int deckType=0;
	/**
	 * The boolean hasItem indicates if the card has the item symbol. <br>
	 * If hasItem is true the player (both humans and aliens) picks-up an Item card from ItemDeck. 
	 */
	private final boolean hasItem;
	public NoiseInYourSectorCard(boolean hasItem) {
		this.hasItem=hasItem;
	}

	public boolean hasItem() {
		return hasItem;
	}

	/**
	 * This method implements the method in Action interface. <br>
	 * This method is auto-called when player pick-up this card. <br>
	 * Asks controller to ask view to ask user in what sector there is noise.
	 */
	@Override
	public void doAction(Player player) {
		// TODO legge il settore dal player e notifica la view col settore.

	}

}
