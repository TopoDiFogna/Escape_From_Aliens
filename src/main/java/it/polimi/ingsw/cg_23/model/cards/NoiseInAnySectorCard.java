package it.polimi.ingsw.cg_23.model.cards;

import it.polimi.ingsw.cg_23.model.players.Player;

/**
 * This card is auto-used when picked-up by players. <br>
 * This card asks the human to chose in what sector other player hear noise.
 * @author Arianna
 */
public class NoiseInAnySectorCard extends Card{
	
	/**
	 * The boolean hasItem indicates if the card has the item symbol. <br>
	 * If hasItem is true the player (both humans and aliens) picks-up an Item card from ItemDeck. 
	 */
	private final boolean hasItem;
	
	/**
	 * constructor
	 */
	public NoiseInAnySectorCard(boolean hasItem) {
		this.hasItem=hasItem;
	}
	
	public boolean hasItem() {
		return hasItem;
	}

	/**
	 * Asks controller to ask view to ask user in what sector there is noise.
	 */
	@Override
	public void doAction(Player player) {
		// il controller deve chiedere alla view di chiedere al giocatore in input il settore che vuole esporre
		if(hasItem){
			//TODO altro metodo del controller che sa da che mazzo pescare, nel controller chiamerò il metodo drawCard() che c'è nel player
		}

	}

}
