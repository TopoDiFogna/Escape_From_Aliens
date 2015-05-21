package it.polimi.ingsw.cg_23.model.cards;

import it.polimi.ingsw.cg_23.model.players.Player;

/**
 * This card is auto-activated if humans has it when he is attacked. <br>
 * Defense card prevent human to be killed.
 * @author Arianna
 */
public class DefenseCard extends Card implements Action {
	
	/**
	 * deckType tells in what deck we must put this card (in this case 1 indicates ItemDeck).	
	 */
	protected int deckType=1;
	public DefenseCard() {
		// TODO constructor
	}
	
	/**
	 * This method implements the method in Action interface. <br>
	 * This card can not be used from human, but auto-used when human is attacked.
	 */
	@Override
	public void doAction(Player player) {
		//TODO o viene risettato isAlive a true, oppure si fa il controllo tra le carte dell'umano, e se ha la carta difesa non viene messo a false isAlive.
		
	}

}
