package model.cards;

// Start of user code (user defined imports)

// End of user code

/**
 * Description of Card.
 * 
 * @author Arianna
 */
public abstract class Card {
    /**
     * Description of the property hasItem.
     */
    private boolean hasItem = false;

    // Start of user code (user defined attributes for Card)

    // End of user code

    // Start of user code (user defined methods for Card)

    // End of user code
    /**
     * Returns hasItem.
     * 
     * @return hasItem
     */
    public boolean getHasItem() {
        return this.hasItem;
    }

    /**
     * Sets a value to attribute hasItem.
     * 
     * @param newHasItem
     */
    public void setHasItem(boolean newHasItem) {
        this.hasItem = newHasItem;
    }
}
