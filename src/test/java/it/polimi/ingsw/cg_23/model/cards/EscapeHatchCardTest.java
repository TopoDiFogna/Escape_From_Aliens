package it.polimi.ingsw.cg_23.model.cards;

import static org.junit.Assert.*;

import org.junit.Test;

public class EscapeHatchCardTest {

    @Test
    public void testEscapeHatchCard() {
        EscapeHatchCard escapeHatchCard = new EscapeHatchCard(EscapeHatchCardEnum.RED);
        assertEquals(EscapeHatchCardEnum.RED, escapeHatchCard.getType());
        
    }

    @Test
    public void testGetType() {
        EscapeHatchCard escapeHatchCard = new EscapeHatchCard(EscapeHatchCardEnum.GREEN);
        assertEquals(EscapeHatchCardEnum.GREEN, escapeHatchCard.getType());
    }
}
