package it.polimi.ingsw.cg_23.model.cards;

import it.polimi.ingsw.cg_23.controller.GameLogic;
import it.polimi.ingsw.cg_23.model.players.Player;

/**
 * if a player picks-up this card, he can not tells other where noise is.
 * @author Arianna
 */
public class SilenceCard extends Card{
	
	/**
	 * The boolean hasItem indicates if the card has the item symbol. <br>
	 * This boolean here is always false, because silence hasn't item symbol, but we added it here to semplify the SectorDeck creation.
	 */
	private final boolean hasItem;
	
	/**
	 * constructor
	 */
	public SilenceCard(boolean hasItem){
		this.hasItem=hasItem;
	}
	
	public boolean isHasItem() {
		return hasItem;
	}

	/**
	 * This method is auto-called when player pick-up this card. <br>
	 * The action simply notify the view to tells other "silence".
	 */
	@Override
	public void doAction(Player player, GameLogic controller) {
		controller.useSilence(player);
	}

}
