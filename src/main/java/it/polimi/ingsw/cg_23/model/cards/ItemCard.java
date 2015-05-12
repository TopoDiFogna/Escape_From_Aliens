package it.polimi.ingsw.cg_23.model.cards;

/**
 * Defines the structure of item cards.
 * 
 * @author Arianna
 */

public class ItemCard extends Card{
    
    private final ItemCardEnum type;

    /**
     * Constructor.
     * 
     * @param type takes the item card type from enumeration
     */
    public ItemCard(ItemCardEnum type) {
        this.type = type;
    }

    /**
     * Card type can be one of defined type in ItemCardEnum.
     * 
     * @return type 
     */
    public ItemCardEnum getType() {
        return type;
    }

}
