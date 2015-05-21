package it.polimi.ingsw.cg_23.model.map;

import static org.junit.Assert.*;

import org.junit.Test;

public class MapTest {

    @Test
    public void testMap() {
        //TODO fail("Not yet implemented");
    }

    @Test
    public void testGetSIZENUMBER() {
        Map map = new Map("galilei");
        assertEquals(14, map.getSIZENUMBER());
    }

    @Test
    public void testGetSIZELETTER() {
        Map map = new Map("galilei");
        assertEquals(23, map.getSIZELETTER());
    }

    @Test
    public void testGetSector() {
        //TODO fail("Not yet implemented");
    }

}
