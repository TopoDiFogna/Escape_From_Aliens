package it.polimi.ingsw.cg_23.model.cards;

import it.polimi.ingsw.cg_23.controller.GameLogic;
import it.polimi.ingsw.cg_23.model.players.Player;

/**
 * When human uses this card he doesn't must pick-up a SectorCard. <br>
 * 
 * @author Arianna
 */
public class SedativesCard extends Card {

    /**
     * constructor
     */
    public SedativesCard() {
    }

    /**
     * This method prevents human to pick-up card.
     */
    @Override
    public void doAction(Player player, GameLogic controller) {
        controller.useSedatives(player);
    }

}
