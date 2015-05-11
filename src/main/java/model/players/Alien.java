/*******************************************************************************
 * 2015, All rights reserved.
 *******************************************************************************/
package model.players;

import model.players.Player;

// End of user code

/**
 * Description of Alien.
 * 
 * @author Arianna
 */
public class Alien extends Player {
    /**
     * Description of the property hasKilled.
     */
    private Boolean hasKilled = Boolean.FALSE;

    // Start of user code (user defined attributes for Alien)

    // End of user code

    /**
     * The constructor.
     */
    public Alien() {
        // Start of user code constructor for Alien)
        super();
        // End of user code
    }

    // Start of user code (user defined methods for Alien)

    // End of user code
    /**
     * Returns hasKilled.
     * 
     * @return hasKilled
     */
    public Boolean getHasKilled() {
        return this.hasKilled;
    }

    /**
     * Sets a value to attribute hasKilled.
     * 
     * @param newHasKilled
     */
    public void setHasKilled(Boolean newHasKilled) {
        this.hasKilled = newHasKilled;
    }

}
