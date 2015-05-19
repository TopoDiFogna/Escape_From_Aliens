package it.polimi.ingsw.cg_23.model.cards;

import static org.junit.Assert.*;

import org.junit.Test;

public class ItemCardTest {

    @Test
    public void testItemCard() {
        ItemCard itemCard = new ItemCard(ItemCardEnum.ADRENALINE);
        assertEquals(ItemCardEnum.ADRENALINE, itemCard.getType());
    }

    @Test
    public void testGetType() {
        ItemCard itemCard = new ItemCard(ItemCardEnum.SEDATIVES);
        assertEquals(ItemCardEnum.SEDATIVES, itemCard.getType());
    }

}
