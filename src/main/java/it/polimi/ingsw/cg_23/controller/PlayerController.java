/*******************************************************************************
 * 2015, All rights reserved.
 *******************************************************************************/
package it.polimi.ingsw.cg_23.controller;

import it.polimi.ingsw.cg_23.model.cards.Card;
import it.polimi.ingsw.cg_23.model.cards.Deck;
import it.polimi.ingsw.cg_23.model.map.Sector;
import it.polimi.ingsw.cg_23.model.players.Player;

// End of user code

/**
 * Description of PlayerController.
 * 
 * @author Arianna
 */
public class PlayerController {

    // Start of user code (user defined attributes for PlayerController)

    // End of user code

    /**
     * The constructor.
     */
    public PlayerController() {
        // Start of user code constructor for PlayerController)
        super();
        // End of user code
    }

    /**
     * Description of the method move.
     * 
     * @param player
     * @param destination
     */
    public void move(Player player, Sector destination) {
        // Start of user code for method move
        // End of user code
    }

    /**
     * Description of the method useCard.
     * 
     * @param player
     * @param card
     */
    public void useCard(Player player, Card card) {
        // Start of user code for method useCard
        // End of user code
    }

    /**
     * Description of the method pickCard.
     * 
     * @param player
     * @param deck
     */
    public void pickCard(Player player, Deck deck) {
        // Start of user code for method pickCard
        // End of user code
    }

    /**
     * Description of the method discardCard.
     * 
     * @param player
     */
    public void discardCard(Player player) {
        // Start of user code for method discardCard
        // End of user code
    }

    /**
     * Description of the method attack.
     * 
     * @param player
     */
    public void attack(Player player) {
        // Start of user code for method attack
        // End of user code
    }

    /**
     * Description of the method validMove.
     * 
     * @param player
     */
    private void validMove(Player player) {
        // Start of user code for method validMove
        // End of user code
    }

    // Start of user code (user defined methods for PlayerController)

    // End of user code
    /**
     * Returns players.
     * 
     * @return players
     */
    public Player getPlayers() {
        return this.getPlayers();
    }
    
    public boolean HasCard(Player player, Card card){
    	//TODO deve controllare se il giocatore ha effettivamente la carta prima di giocarla. il return è ovviamente non sempre true, ma poi ci penso    	
    	return true;
    }

}
