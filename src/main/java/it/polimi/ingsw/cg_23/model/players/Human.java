package it.polimi.ingsw.cg_23.model.players;

import it.polimi.ingsw.cg_23.model.players.Player;

/**
 * Type of Player. Humans have to escape from aliens.
 * 
 * @author Paolo
 */
public class Human extends Player {
    /**
     * Boolean. If a human escapes is removed from the game as a winner. 
     */
    private boolean escaped = false;
    
    /**
     * The constructor.
     */
    public Human(String name) {
        super(name);
    }

    /**
     * Returns if a player has escaped hence has win.
     * 
     * @return escaped tells if a player has escaped hence win
     */
    public boolean getEscaped() {
        return this.escaped;
    }

    /**
     * Sets a human as escaped.
     * 
     */
    public void setEscaped() {
        this.escaped = true;
    }

}
