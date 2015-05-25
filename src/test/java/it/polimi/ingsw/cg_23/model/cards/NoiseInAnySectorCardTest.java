package it.polimi.ingsw.cg_23.model.cards;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_23.controller.GameLogic;
import it.polimi.ingsw.cg_23.model.players.Human;
import it.polimi.ingsw.cg_23.model.players.Player;
import it.polimi.ingsw.cg_23.model.status.Match;

import org.junit.Test;

public class NoiseInAnySectorCardTest {

	@Test
	public void testDoAction() {
		Player player = new Human("dummy");
		Card card = new NoiseInAnySectorCard(false);
		String mapName = "galilei";
		Match match = new Match(mapName);
		GameLogic controller = new GameLogic(match);
		card.doAction(player, controller);
		assertNotNull(card);
	}

	@Test
	public void testNoiseInAnySectorCard() {
		Card card = new NoiseInAnySectorCard(true);
		assertNotNull(card);
	}

	@Test
	public void testHasItem() {
		NoiseInAnySectorCard card = new NoiseInAnySectorCard(true);
		assertTrue(card.hasItem());
	}

}
