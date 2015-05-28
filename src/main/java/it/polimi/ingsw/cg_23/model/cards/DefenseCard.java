package it.polimi.ingsw.cg_23.model.cards;

import it.polimi.ingsw.cg_23.controller.GameLogic;
import it.polimi.ingsw.cg_23.model.players.Player;

/**
 * This card is auto-activated if humans has it when he is attacked. <br>
 * Defense card prevent human to be killed.
 * 
 * @author Arianna
 */
public class DefenseCard extends Card {

    /**
     * constructor
     */
    public DefenseCard() {
    }

    /**
     * This card can not be used from human, but auto-used when human is attacked.
     */
    @Override
    public void doAction(Player player, GameLogic controller) {
        controller.useDefense(player);

    }

}
