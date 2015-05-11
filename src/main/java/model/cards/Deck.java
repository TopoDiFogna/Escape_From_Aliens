/*******************************************************************************
 * 2015, All rights reserved.
 *******************************************************************************/
package model.cards;

import java.util.List;

// Start of user code (user defined imports)

// End of user code

/**
 * Description of Deck.
 * 
 * @author Arianna
 */
public class Deck {
    /**
     * Description of the property cards.
     */
    // public HashSet<Card> cards = new HashSet<Card>();

    /**
     * Description of the property mazzo.
     */
    public List<Card> mazzo = null;

    // Start of user code (user defined attributes for Deck)

    // End of user code

    /**
     * The constructor.
     */
    public Deck() {
        // Start of user code constructor for Deck)
        super();
        // End of user code
    }

    // Start of user code (user defined methods for Deck)

    // End of user code
    /**
     * Returns mazzo.
     * 
     * @return mazzo
     */
    public List<Card> getMazzo() {
        return this.mazzo;
    }
}
