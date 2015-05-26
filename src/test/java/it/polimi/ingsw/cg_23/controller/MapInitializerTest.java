package it.polimi.ingsw.cg_23.controller;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_23.controller.parser.XMLParser;
import org.junit.Test;

public class MapInitializerTest {

	@Test
	public void testCreateMap() {
		XMLParser parser = new XMLParser();
		assertNotNull(parser);
	}

	/*@Test
	public void testCreateNeighbors() {
		Match match = new Match("galilei");
		Sector[][] map = match.getMap().getSector();
		MapInitializer.createNeighbors(22, 13, map);
		assertNotNull(map);
		
	}*/
	
	
}
