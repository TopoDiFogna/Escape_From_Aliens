package model.cards;

/**
 * Defines the structure of sector cards.
 * 
 * @author Arianna
 */

public class SectorCard extends Card{
    
    private final SectorCardEnum type;
    private boolean hasItem = false;
    /**
     * Constructor.
     *  
     * @param type takes the sector card type from enumeration
     */
    public SectorCard(SectorCardEnum type, boolean hasItem) {
        this.type = type;
        this.hasItem = hasItem;
    }    
       
    /**
     * If return true this card has Item; if return false don't.
     * 
     * @return hasItem
     */
    public boolean getHasItem() {
        return this.hasItem;
    }

    /**
     * Sets true or false to attribute hasItem.
     * 
     * @param newHasItem
     */
    public void setHasItem(boolean hasItem) {
        this.hasItem = hasItem;
    }

    /**
     * Card type can be one of defined type in SectorCardEnum.
     * 
     * @return type
     */
    public SectorCardEnum getType() {
        return type;
    }
}