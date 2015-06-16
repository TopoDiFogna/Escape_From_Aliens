package it.polimi.ingsw.cg_23.model.cards;

import it.polimi.ingsw.cg_23.controller.GameLogic;
import it.polimi.ingsw.cg_23.model.players.Player;

/**
 * The adrenaline card gives humans the possibility to move 2 sectors instead 1.
 * 
 * @author Arianna
 */
public class AdrenalineCard extends Card {

    /**
     * constructor
     */
    public AdrenalineCard() {
    }

    @Override
    public String toString() {
        return "Adrenaline";
    }

    /**
     * Calls the specific method in GameLogic class.
     */
    @Override
    public void doAction(Player player, GameLogic controller) {
        controller.useAdrenaline(player);
    }

}