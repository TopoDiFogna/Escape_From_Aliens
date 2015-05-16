package it.polimi.ingsw.cg_23.model.player;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_23.model.players.Human;

import org.junit.Test;

public class HumanTest {

    @Test
    public void testHuman() {
        Human human = new Human("dummy");
        assertNotNull(human);
        assertEquals("dummy", human.getName());
        //fail("Not yet implemented");
    }

    @Test
    public void testHasDefenceCard() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetEscapedFalse() {
        Human human = new Human("dummy");
        assertFalse(human.getEscaped());
    }
    
    @Test
    public void testGetEscapedTrue() {
        //Human human = new Human("dummy");
        //assertFalse(human.getEscaped());
        fail("Not yet implemented");
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
    public void testIsAliveFalseAfterBeingKilled() {
        fail("Not yet implemented");
    }

    @Test
    public void testSetDead() {
        Human human = new Human("dummy");
        human.setDead();
        assertFalse(human.isAlive());
    }

    @Test
    public void testGetSector() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetCards() {
        fail("Not yet implemented");
    }

    @Test
    public void testAddCard() {
        fail("Not yet implemented");
    }

    @Test
    public void testSetSector() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetCanMoveFasterTrue() {
        Human human = new Human("dummy");
        human.setCanMoveFaster(true);
        assertTrue(human.getCanMoveFaster());
    }
    
    @Test
    public void testGetCanMoveFasterFalseDefault() {
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
