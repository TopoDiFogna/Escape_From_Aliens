package it.polimi.ingsw.cg_23.model.player;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_23.model.players.Alien;

import org.junit.Test;

public class AlienTest {

    @Test
    public void testAlien() {
        Alien alien = new Alien("dummy");
        assertNotNull(alien);
        //TODO assertEquals("dummy", alien.getName());
    }

    @Test
    public void testGetHasKilledTrue() {
        fail("Not yet implemented");
    }
    
    @Test
    public void testGetHasKilledTrueAfterHasKilled() {
        fail("Not yet implemented");
    }

    @Test
    public void testSetHasKilled() {
        Alien alien = new Alien("dummy");
        alien.setHasKilled();
        assertTrue(alien.getHasKilled());
    }

    @Test
    public void testIsAliveTrue() {
        Alien alien = new Alien("dummy");
        assertTrue(alien.isAlive());
    }
    
    @Test
    public void testIsAliveFalseAfterBeingKilled() {
        fail("Not yet implemented");
    }

    @Test
    public void testSetDead() {
        Alien alien = new Alien("dummy");
        alien.setDead();
        assertFalse(alien.isAlive());
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
        Alien alien = new Alien("dummy");
        alien.setCanMoveFaster(true);
        assertTrue(alien.getCanMoveFaster());
    }
    
    @Test
    public void testGetCanMoveFasterFalseAsDefault() {
        Alien alien = new Alien("dummy");
        assertFalse(alien.getCanMoveFaster());
    }
    
    @Test
    public void testSetCanMoveFasterTrue() {
        Alien alien = new Alien("dummy");
        alien.setCanMoveFaster(true);
        assertTrue(alien.getCanMoveFaster());
    }
}
