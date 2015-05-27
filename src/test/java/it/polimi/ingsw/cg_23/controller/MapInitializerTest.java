package it.polimi.ingsw.cg_23.controller;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_23.controller.parser.XMLParser;
import it.polimi.ingsw.cg_23.model.map.Map;
import it.polimi.ingsw.cg_23.model.map.Sector;
import org.junit.Test;

public class MapInitializerTest {

	@Test
	public void testCreateMap() {
		XMLParser parser = new XMLParser();
		assertNotNull(parser);
	}

	@Test
	public void testCreateNeighbors() {
		Map map = new Map("galilei");
		Sector[][] settori = map.getSector();
		assertNotNull(map);
		assertFalse(settori[10][5].getNeighbors().contains(settori[11][5]));
		assertTrue(settori[10][5].getNeighbors().contains(settori[10][4]));
		assertFalse(settori[10][5].getNeighbors().contains(settori[10][6]));
		assertTrue(settori[10][5].getNeighbors().contains(settori[9][5]));
		
	}
	
	
}
