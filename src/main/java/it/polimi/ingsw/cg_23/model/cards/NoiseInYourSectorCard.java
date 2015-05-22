package it.polimi.ingsw.cg_23.model.cards;

import it.polimi.ingsw.cg_23.model.players.Player;

/**
 * This card is auto-used when picked-up by players. <br>
 * This card tells automatically other player where is the noise. <br>
 * We don't need to ask player where is the noise, because the noise is the player sector.
 * @author Arianna
 */
public class NoiseInYourSectorCard extends Card{
	
	/**
	 * The boolean hasItem indicates if the card has the item symbol. <br>
	 * If hasItem is true the player (both humans and aliens) picks-up an Item card from ItemDeck. 
	 */
	private final boolean hasItem;
	
	/**
	 * constructor
	 */
	public NoiseInYourSectorCard(boolean hasItem) {
		this.hasItem=hasItem;
	}

	public boolean hasItem() {
		return hasItem;
	}

	/**
	 * This method is auto-called when player pick-up this card. <br>
	 * Asks controller to ask view to ask user in what sector there is noise.
	 */
	@Override
	public void doAction(Player player) {
		//TODO nel controller c'è da creare un metodo per passargli il mio settore player.getCurrentSector()
		if(hasItem){
			//TODO altro metodo del controller che sa da che mazzo pescare, nel controller chiamerò il metodo drawCard() che c'è nel player
		}
	}

}
