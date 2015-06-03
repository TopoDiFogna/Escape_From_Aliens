package it.polimi.ingsw.cg_23.model.cards;

import it.polimi.ingsw.cg_23.controller.GameLogic;
import it.polimi.ingsw.cg_23.model.players.Player;

/**
 * This card is auto-used when picked-up by players. <br>
 * This card tells automatically other player where is the noise. <br>
 * We don't need to ask player where is the noise, because the noise is the player sector.
 * 
 * @author Arianna
 */
public class NoiseInYourSectorCard extends Card {

    /**
     * The boolean hasItem indicates if the card has the item symbol. <br>
     * If hasItem is true the player (both humans and aliens) picks-up an Item card from ItemDeck.
     */
    private final boolean hasItem;

    /**
     * constructor
     */
    public NoiseInYourSectorCard(boolean hasItem) {
        this.hasItem = hasItem;
    }

    /** 
     * @return true if card has item symbol, false if not
     */
    public boolean hasItem() {
        return hasItem;
    }

    /**
     * This method is auto-called when player pick-up this card. <br>
     */
    @Override
    public void doAction(Player player, GameLogic controller) {
        controller.useNoiseInYourSector(player);
    }

}
