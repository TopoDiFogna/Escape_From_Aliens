package it.polimi.ingsw.cg_23.model.map;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_23.model.players.Human;
import it.polimi.ingsw.cg_23.model.players.Player;

import java.util.ArrayList;
import java.util.List;

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
        Human human = new Human("dummy");
        Sector sector = new Sector(1, 1, SectorTypeEnum.DANGEROUS, true);
        sector.setPlayer(human);
        List<Player> players = new ArrayList<Player>();
        players.add(human);
        assertEquals(players, sector.getPlayer());    	
    }

    @Test
    public void testSetPlayer() {
        Human human = new Human("dummy");
        Sector sector = new Sector(1, 1, SectorTypeEnum.DANGEROUS, true);
        sector.setPlayer(human);
        List<Player> players = new ArrayList<Player>();
        players.add(human);
        assertEquals(players, sector.getPlayer());
    }


    @Test
    public void testSetEscapeHatchSectorNotCrossable() {
    	Sector sector = new Sector(3, 5, SectorTypeEnum.ESCAPEHATCH, true);
    	sector.setEscapeHatchSectorNotCrossable();
    	assertFalse(sector.isCrossable());
    }
    
    @Test
    public void testIsNearbyBottomEven(){
        Sector sector = new Sector(4, 4, SectorTypeEnum.VOID, true);
        assertTrue(sector.isNearby(new Sector(4, 5, SectorTypeEnum.VOID, true)));
    }
    
    @Test
    public void testIsNearbyBottomLeftEven(){
        Sector sector = new Sector(4, 4, SectorTypeEnum.VOID, true);
        assertTrue(sector.isNearby(new Sector(3, 5, SectorTypeEnum.VOID, true)));
    }
    
    @Test
    public void testIsNearbyTopLeftEven(){
        Sector sector = new Sector(4,4, SectorTypeEnum.VOID, true);
        assertTrue(sector.isNearby(new Sector(3, 4, SectorTypeEnum.VOID, true)));
    }
    
    @Test
    public void testIsNearbyTopEven(){
        Sector sector = new Sector(4, 4, SectorTypeEnum.VOID, true);
        assertTrue(sector.isNearby(new Sector(4, 3, SectorTypeEnum.VOID, true)));
    }
    @Test
    public void testIsNearbyTopRightEven(){
        Sector sector = new Sector(4, 4, SectorTypeEnum.VOID, true);
        assertTrue(sector.isNearby(new Sector(5, 4, SectorTypeEnum.VOID, true)));
    }
    @Test
    public void testIsNearbyBottomRightEven(){
        Sector sector = new Sector(4, 4, SectorTypeEnum.VOID, true);
        assertTrue(sector.isNearby(new Sector(5, 5, SectorTypeEnum.VOID, true)));
    }
    
    @Test
    public void testIsNearbyBottomOdd(){
        Sector sector = new Sector(3, 3, SectorTypeEnum.VOID, true);
        assertTrue(sector.isNearby(new Sector(3, 4, SectorTypeEnum.VOID, true)));
    }
    
    @Test
    public void testIsNearbyBottomLeftOdd(){
        Sector sector = new Sector(3, 3, SectorTypeEnum.VOID, true);
        assertTrue(sector.isNearby(new Sector(2, 3, SectorTypeEnum.VOID, true)));
    }
    
    @Test
    public void testIsNearbyTopLeftOdd(){
        Sector sector = new Sector(3, 3, SectorTypeEnum.VOID, true);
        assertTrue(sector.isNearby(new Sector(2, 2, SectorTypeEnum.VOID, true)));
    }
    
    @Test
    public void testIsNearbyTopOdd(){
        Sector sector = new Sector(3, 3, SectorTypeEnum.VOID, true);
        assertTrue(sector.isNearby(new Sector(3, 2, SectorTypeEnum.VOID, true)));
    }
    @Test
    public void testIsNearbyTopRightOdd(){
        Sector sector = new Sector(3, 3, SectorTypeEnum.VOID, true);
        assertTrue(sector.isNearby(new Sector(4, 2, SectorTypeEnum.VOID, true)));
    }
    @Test
    public void testIsNearbyBottomRightOdd(){
        Sector sector = new Sector(3, 3, SectorTypeEnum.VOID, true);
        assertTrue(sector.isNearby(new Sector(4, 3, SectorTypeEnum.VOID, true)));
    }
    
    @Test
    public void testIsNotNearby(){
        Sector sector = new Sector(3, 3, SectorTypeEnum.VOID, true);
        assertFalse(sector.isNearby(new Sector(3, 7, SectorTypeEnum.VOID, true)));
    }
    
    @Test
    public void testIsNotNearbyEvenColumnDifferentNumber(){
        Sector sector = new Sector(4, 3, SectorTypeEnum.VOID, true);
        assertFalse(sector.isNearby(new Sector(7, 3, SectorTypeEnum.VOID, true)));
    }
    
    @Test
    public void testIsNotNearbyEvenColumnDifferentNumber2(){
        Sector sector = new Sector(4, 3, SectorTypeEnum.VOID, true);
        assertFalse(sector.isNearby(new Sector(5, 0, SectorTypeEnum.VOID, true)));
    }
    
    @Test
    public void testIsNotNearbyOddColumnDifferentNumber(){
        Sector sector = new Sector(5, 3, SectorTypeEnum.VOID, true);
        assertFalse(sector.isNearby(new Sector(6, 6, SectorTypeEnum.VOID, true)));
    }
    
    @Test
    public void testAddNeighborVoid(){
        Sector sector = new Sector(5, 3, SectorTypeEnum.VOID, true);
        sector.addNeighbors(new Sector(1, 1, SectorTypeEnum.VOID, true));
        List<Sector> list = new ArrayList<Sector>();
        assertEquals(list, sector.getNeighbors());       
    }
    
    @Test
    public void testAddNeighbor(){
        Sector sector = new Sector(5, 3, SectorTypeEnum.DANGEROUS, true);
        Sector sectorExpected = new Sector(1, 1, SectorTypeEnum.DANGEROUS, true);
        sector.addNeighbors(sectorExpected);
        List<Sector> list = new ArrayList<Sector>();
        list.add(sectorExpected);
        assertEquals(list, sector.getNeighbors());       
    }
}
