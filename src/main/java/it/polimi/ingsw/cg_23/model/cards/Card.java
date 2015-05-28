package it.polimi.ingsw.cg_23.model.cards;

import it.polimi.ingsw.cg_23.controller.GameLogic;
import it.polimi.ingsw.cg_23.model.players.Player;

/**
 * Abtract class of card.
 * 
 * @author Arianna
 */
public abstract class Card {

    /**
     * Constructor.
     */
    protected Card() {

    }

    /**
     * This method is implemented in every card classes with different actions.
     * 
     * @param player who uses the card
     */
    public abstract void doAction(Player player, GameLogic controller);

}