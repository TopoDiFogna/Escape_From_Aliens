package it.polimi.ingsw.cg_23.model.cards;

import java.util.ArrayList;

/**
 * Implements a generic list who extends card.
 * 
 * @author Arianna
 */
public class Deck<T extends Card> extends ArrayList<T> {
    
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 2229743970656975155L;

    /**
     * Constructor.
     */
    protected Deck(){
        
    }
   
}
