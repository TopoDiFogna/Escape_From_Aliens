/*******************************************************************************
 * 2015, All rights reserved.
 *******************************************************************************/
package model.map;

import model.players.Player;

// Start of user code (user defined imports)

// End of user code

/**
 * Description of SectorEscapeHatch.
 * 
 * @author Arianna
 */
public class SectorEscapeHatch extends Sector {
    /**
     * Description of the property used.
     */
    private Boolean used = Boolean.FALSE;

    // Start of user code (user defined attributes for SectorEscapeHatch)

    // End of user code

    /**
     * The constructor.
     */
    public SectorEscapeHatch() {
        // Start of user code constructor for SectorEscapeHatch)
        super();
        // End of user code
    }

    /**
     * Description of the method setUsed.
     */
    public void setUsed() {
        // Start of user code for method setUsed
        // End of user code
    }

    /**
     * Description of the method saveHuman.
     * 
     * @param player
     */
    public void saveHuman(Player player) {
        // Start of user code for method saveHuman
        // End of user code
    }

    // Start of user code (user defined methods for SectorEscapeHatch)

    // End of user code
    /**
     * Returns used.
     * 
     * @return used
     */
    public Boolean getUsed() {
        return this.used;
    }

    /**
     * Sets a value to attribute used.
     * 
     * @param newUsed
     */
    public void setUsed(Boolean newUsed) {
        this.used = newUsed;
    }

}
