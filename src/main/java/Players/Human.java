/*******************************************************************************
 * 2015, All rights reserved.
 *******************************************************************************/
package Players;

import Players.Player;
// Start of user code (user defined imports)

// End of user code

/**
 * Description of Human.
 * 
 * @author Arianna
 */
public class Human extends Player {
    /**
     * Description of the property escaped.
     */
    private boolean escaped = false;

    // Start of user code (user defined attributes for Human)

    // End of user code

    /**
     * The constructor.
     */
    public Human() {
        // Start of user code constructor for Human)
        super();
        // End of user code
    }

    /**
     * Description of the method hasDefenceCard.
     * 
     * @return
     */
    public boolean hasDefenceCard() {
        // Start of user code for method hasDefenceCard
        boolean hasDefenceCard = false;
        return hasDefenceCard;
        // End of user code
    }

    // Start of user code (user defined methods for Human)

    // End of user code
    /**
     * Returns escaped.
     * 
     * @return escaped
     */
    public boolean getEscaped() {
        return this.escaped;
    }

    /**
     * Sets a value to attribute escaped.
     * 
     * @param newEscaped
     */
    public void setEscaped(boolean newEscaped) {
        this.escaped = newEscaped;
    }

}
