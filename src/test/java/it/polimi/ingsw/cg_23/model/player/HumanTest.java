package it.polimi.ingsw.cg_23.model.player;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_23.model.map.Sector;
import it.polimi.ingsw.cg_23.model.map.SectorTypeEnum;
import it.polimi.ingsw.cg_23.model.players.Human;

import org.junit.Test;

public class HumanTest {

    @Test
    public void testHuman() {
        Human human = new Human("dummy");
        assertNotNull(human);
    }
    
    @Test
    public void testHumanName(){
        Human human = new Human("dummy");
        assertEquals("dummy", human.getName()); 
    }
    
    @Test
    public void testHumanId(){
        Human human = new Human("dummy");
        assertEquals(human.getCounter()-1, human.getPlayerId());
    }

    @Test
    public void testGetEscapedFalse() {
        Human human = new Human("dummy");
        assertFalse(human.getEscaped());
    }
    
    @Test
    public void testGetEscapedFalseAsDefault() {
        Human human = new Human("dummy");
        assertFalse(human.getEscaped());
    }

    @Test
    public void testSetEscaped() {
        Human human = new Human("dummy");
        human.setEscaped();
        assertTrue(human.getEscaped());
    }

    @Test
    public void testIsAliveTrue() {
        Human human = new Human("dummy");
        assertTrue(human.isAlive());
    }
    
    @Test
    public void testSetDead() {
        Human human = new Human("dummy");
        human.setDead();
        assertFalse(human.isAlive());
    }

    @Test
    public void testGetSector() {
        Sector sector = new Sector(1, 1, SectorTypeEnum.VOID, true);
        Human human = new Human("dummy");
        human.setCurrentSector(sector);
        assertEquals(sector, human.getCurrentSector());
    }

    @Test
    public void testGetCards() {
        //fail("Not yet implemented");        
    }

    @Test
    public void testSetSector() {
        Sector sector = new Sector(1, 1, SectorTypeEnum.VOID, true);
        Human human = new Human("dummy");
        human.setCurrentSector(sector);
        assertEquals(sector, human.getCurrentSector());
    }

    @Test
    public void testGetCanMoveFasterTrue() {
        Human human = new Human("dummy");
        human.setCanMoveFaster(true);
        assertTrue(human.getCanMoveFaster());
    }
    
    @Test
    public void testGetCanMoveFasterFalseAsDefault() {
        Human human = new Human("dummy");
        assertFalse(human.getCanMoveFaster());
    }

    @Test
    public void testGetCanMoveFasterFalse() {
        Human human = new Human("dummy");
        human.setCanMoveFaster(true);
        human.setCanMoveFaster(false);
        assertFalse(human.getCanMoveFaster());
    }
    
    @Test
    public void testSetCanMoveFasterTrue() {
        Human human = new Human("dummy");
        human.setCanMoveFaster(true);
        assertTrue(human.getCanMoveFaster());
    }
    
    @Test
    public void testSetCanMoveFasterFalseAfterBeignTrue() {
        Human human = new Human("dummy");
        human.setCanMoveFaster(true);
        human.setCanMoveFaster(false);
        assertFalse(human.getCanMoveFaster());
    }

}
