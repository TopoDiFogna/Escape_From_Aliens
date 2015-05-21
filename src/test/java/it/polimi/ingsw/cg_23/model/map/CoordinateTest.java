package it.polimi.ingsw.cg_23.model.map;

import static org.junit.Assert.*;

import org.junit.Test;

public class CoordinateTest {

    @Test
    public void testCoordinate() {
        Coordinate coordinate = new Coordinate(1, 1);
        assertNotNull(coordinate);
    }

    @Test
    public void testGetLetter() {
        Coordinate coordinate = new Coordinate(3, 1);
        assertEquals(3, coordinate.getLetter());
    }

    @Test
    public void testGetNumber() {
        Coordinate coordinate = new Coordinate(1, 5);
        assertEquals(5, coordinate.getNumber());
    }

}
