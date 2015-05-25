package it.polimi.ingsw.cg_23.model.map;

import static org.junit.Assert.*;

import org.junit.Test;

public class MapTest {

    @Test
    public void testMap() {
        Map map = new Map("galilei");
        Sector[][] sectors = map.getSector();
        for(int letter = 0; letter<23;letter++){
            for(int number =0; number<14;number++)
                assertNotNull(sectors[letter][number]);
        }
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
        Map map = new Map("galilei");
        assertNotNull(map.getSector());
    }

    @Test
    public void testGetHumanSector(){
        Map map = new Map("galilei");
        assertTrue(map.getHumanSector().getType()==SectorTypeEnum.HUMAN);
    }
    
    @Test
    public void testGetAlienSector(){
        Map map = new Map("galilei");
        assertTrue(map.getAlienSector().getType()==SectorTypeEnum.ALIEN);
    }
}
