package it.polimi.ingsw.cg_23.model.cards;

import it.polimi.ingsw.cg_23.model.players.Player;

/**
 * if a player picks-up this card, he can not tells other where noise is.
 * @author Arianna
 */
public class SilenceCard extends Card implements Action {
	
	/**
	 * deckType tells in what deck we must put this card (in this case 0 indicates SectorDeck).	
	 */
	protected int deckType=0;
	
	/**
	 * The boolean hasItem indicates if the card has the item symbol. <br>
	 * This boolean here is always false, because silence hasn't item symbol, but we added it here to semplify the SectorDeck creation.
	 */
	boolean hasItem;
	public SilenceCard(boolean hasItem){
		
	}
	
	/**
	 * This method implements the method in Action interface. <br>
	 * This method is auto-called when player pick-up this card. <br>
	 * The action simply notify the view to tells other "silence".
	 */
	@Override
	public void doAction(Player player) {
		// TODO devo solamente chiedere alla view di scrivere "silenzio".

	}

}
