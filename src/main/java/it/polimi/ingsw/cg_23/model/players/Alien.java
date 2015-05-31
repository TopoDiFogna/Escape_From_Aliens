package it.polimi.ingsw.cg_23.model.players;

import it.polimi.ingsw.cg_23.model.players.Player;

/**
 * Description of Alien.
 * 
 * @author Paolo
 */
public class Alien extends Player {
    /**
     * Tells if an alien has killed some human so he can move faster.
     */
    private boolean hasKilled = false;

    /**
     * The constructor.
     */
    public Alien(String name) {
        super(name);
    }
    
    /**
     * Returns true if the player has killed some humans.
     * 
     * @return hasKilled true if the alien has killed some humans
     */
    public boolean getHasKilled() {
        return this.hasKilled;
    }

    /**
     * Sets an anlien as a killer
     * 
     */
    public void setHasKilled() {
        this.hasKilled = true;
    }

    @Override
    public String toString() {
        return "Alien";
    }

}
