package it.polimi.ingsw.cg_23.model.cards;

import it.polimi.ingsw.cg_23.model.players.Player;

/**
 * The attack card gives the humans the possibility to attack in the sector he stands on, killing other humans and aliens who stand on. <br>
 * If a human has Defence card, he doesn't die.
 * 
 * @author Arianna
 */
public class AttackCard extends Card implements Action {
	
	/**
	 * deckType tells in what deck we must put this card (in this case 1 indicates ItemDeck).	
	 */
	protected int deckType=1;
	public AttackCard() {
		// TODO constructor
	}

	/**
	 * This method implements the method in Action interface. <br>
	 * In this case gives the player the possibility to attack in his sector. <br>
	 * The action calls attack method in PlayerController class (Attack method is the same for humans and aliens).
	 */
	@Override
	public void doAction(Player player) {
		//TODO PlayerController.attack(Sector sector);
		

	}

}
