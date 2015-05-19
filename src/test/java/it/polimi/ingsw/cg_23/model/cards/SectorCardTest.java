package it.polimi.ingsw.cg_23.model.cards;

import static org.junit.Assert.*;

import org.junit.Test;

public class SectorCardTest {

    @Test
    public void testSectorCard() {
        SectorCard card = new SectorCard(SectorCardEnum.NOISEINANYSECTOR, true);
        assertEquals(SectorCardEnum.NOISEINANYSECTOR, card.getType());
        assertTrue(card.getHasItem());
    }

    @Test
    public void testGetHasItem() {
        SectorCard card = new SectorCard(SectorCardEnum.NOISEINANYSECTOR, false);
        assertFalse(card.getHasItem());
    }

    @Test
    public void testSetHasItem() {
        SectorCard card = new SectorCard(SectorCardEnum.NOISEINANYSECTOR, false);
        assertFalse(card.getHasItem());
    }

    @Test
    public void testGetType() {
        SectorCard card = new SectorCard(SectorCardEnum.NOISEINYOURSECTOR, true);
        assertEquals(SectorCardEnum.NOISEINYOURSECTOR, card.getType());
    }

}
