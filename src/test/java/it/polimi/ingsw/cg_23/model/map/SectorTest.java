package it.polimi.ingsw.cg_23.model.map;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_23.model.players.Player;

import java.util.ArrayList;

import org.junit.Test;

public class SectorTest {

    @Test
    public void testSector() {
       Sector sector = new Sector(3, 5, SectorTypeEnum.DANGEROUS, true);
       assertEquals(3, sector.getLetter());
       assertEquals(5, sector.getNumber());
       assertEquals(SectorTypeEnum.DANGEROUS, sector.getType());
       assertEquals(true, sector.isCrossable());
       
    }

    @Test
    public void testGetPlayer() {
    	fail("Not yet implemented");
    }

    @Test
    public void testSetPlayer() {
        fail("Not yet implemented");
    }


    @Test
    public void testSetEscapeHatchSectorNotCrossable() {
    	Sector sector = new Sector(3, 5, SectorTypeEnum.ESCAPEHATCH, true);
    	sector.setEscapeHatchSectorNotCrossable();
    	assertFalse(sector.isCrossable());
    }


}
