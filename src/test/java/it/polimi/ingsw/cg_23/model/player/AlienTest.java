package it.polimi.ingsw.cg_23.model.player;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_23.model.cards.AttackCard;
import it.polimi.ingsw.cg_23.model.map.Sector;
import it.polimi.ingsw.cg_23.model.map.SectorTypeEnum;
import it.polimi.ingsw.cg_23.model.players.Alien;
import it.polimi.ingsw.cg_23.model.players.Human;

import org.junit.Test;

public class AlienTest {

    @Test
    public void testAlien() {
        Alien alien = new Alien("dummy");
        assertNotNull(alien);
    }
    
    @Test
    public void testHumanName(){
        Alien alien = new Alien("dummy");
        assertEquals("dummy", alien.getName()); 
    }
    
    @Test
    public void testHumanId(){
        Alien alien = new Alien("dummy");
        assertEquals(Alien.getCounter()-1, alien.getPlayerId());
    }

    @Test
    public void testGetHasKilledTrue() {
        Alien alien = new Alien("dummy");
        alien.setHasKilled();
        assertTrue(alien.getHasKilled());
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
    public void testSetDead() {
        Alien alien = new Alien("dummy");
        alien.setDead();
        assertFalse(alien.isAlive());
    }

    @Test
    public void testGetSector() {
        Sector sector = new Sector(1, 1, SectorTypeEnum.VOID, true);
        Alien alien = new Alien("dummy");
        alien.setCurrentSector(sector);
        assertEquals(sector, alien.getCurrentSector());
    }

    @Test
    public void testGetCards() {
        Alien alien = new Alien("dummy");
        AttackCard card = new AttackCard();
        alien.getCards().add(card);
        assertTrue(alien.getCards().contains(card));
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
    public void testToString() {
      Alien alien= new Alien("Dummy");
       assertTrue(alien.toString().equals("Alien"));
    }
}
