package it.polimi.ingsw.cg_23.model.player;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_23.model.cards.AttackCard;
import it.polimi.ingsw.cg_23.model.map.Sector;
import it.polimi.ingsw.cg_23.model.map.SectorTypeEnum;
import it.polimi.ingsw.cg_23.model.players.Human;
import it.polimi.ingsw.cg_23.model.players.Player;

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
        assertEquals(Human.getCounter()-1, human.getPlayerId());
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
        Human human = new Human("dummy");
        AttackCard card = new AttackCard();
        human.getCards().add(card);
        assertTrue(human.getCards().contains(card));       
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
    
    @Test
    public void testToString() {
       Human human= new Human("Dummy");
       assertTrue(human.toString().equals("Human"));
    }
    
    @Test
    public void testHasMoved() {
       Player player= new Human("Dummy");
       player.setHasMoved(true);
       assertTrue(player.hasMoved());
    }

}
