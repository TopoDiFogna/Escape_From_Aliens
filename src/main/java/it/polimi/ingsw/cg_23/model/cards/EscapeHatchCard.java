package it.polimi.ingsw.cg_23.model.cards;

/**
 * Defines the structure of escape hatch cards.
 * 
 * @author Arianna
 */

public class EscapeHatchCard extends Card{

    private final EscapeHatchCardEnum type;

    /**
     * Constructor.
     * 
     * @param type takes the escape hatch card type from enumeration
     */
    public EscapeHatchCard(EscapeHatchCardEnum type) {
        this.type = type;
    }

    /**
     * Card type can be one of defined types in EscapeHatchCardEnum.
     * 
     * @return type
     */
    public EscapeHatchCardEnum getType() {
        return type;
    }

}
