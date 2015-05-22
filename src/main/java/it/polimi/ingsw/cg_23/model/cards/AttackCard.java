package it.polimi.ingsw.cg_23.model.cards;



import it.polimi.ingsw.cg_23.model.players.Player;

/**
 * The attack card gives the humans the possibility to attack in the sector he stands on, killing other humans and aliens who stand on. <br>
 * If a human has Defence card, he doesn't die.
 * 
 * @author Arianna
 */
public class AttackCard extends Card {
	
	/**
	 * constructor
	 */
	public AttackCard() {
	}

	/**
	 * In this case gives the player the possibility to attack in his sector. <br>
	 * The action calls attack method in PlayerController class (Attack method is the same for humans and aliens).
	 */
	@Override
	public void doAction(Player player) {
		for (Player players : player.getCurrentSector().getPlayer()) {
			if(players!=player && !players.getCards().contains(new DefenseCard())){
				players.setDead();
				//TODO quando esisterà il controllore qua chiamerò un metodo per togliere i player morti dalla lista player
			}
		}
				
	}

}
