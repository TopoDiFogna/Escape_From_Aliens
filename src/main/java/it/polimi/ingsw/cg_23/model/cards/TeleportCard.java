package it.polimi.ingsw.cg_23.model.cards;

import it.polimi.ingsw.cg_23.model.players.Player;

/**
 * If humsn uses this card, he is teleport at HumanSector (the starting sector of humans) 
 * @author Arianna
 */
public class TeleportCard extends Card implements Action {
	
	/**
	 * deckType tells in what deck we must put this card (in this case 1 indicates ItemDeck).	
	 */
	protected int deckType=1;
	public TeleportCard() {
		// TODO constructor
	}

	/**
	 * This method implements the method in Action interface. <br>
	 * 
	 */
	@Override
	public void doAction(Player player) {
		// TODO sposto il giocatore che ha usato la carta nel settore umani, senza fare il controllo di mossa valida.
		//devo però chiedere al controllore inc he mappa stiamo giocando e cercare le coordinate del settore di destinazione

	}

}
