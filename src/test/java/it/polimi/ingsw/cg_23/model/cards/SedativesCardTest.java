package it.polimi.ingsw.cg_23.model.cards;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_23.controller.GameLogic;
import it.polimi.ingsw.cg_23.model.players.Human;
import it.polimi.ingsw.cg_23.model.players.Player;
import it.polimi.ingsw.cg_23.model.status.Match;

import org.junit.Test;

public class SedativesCardTest {

	@Test
	public void testDoAction() {
		Player player = new Human("dummy");
		Card card = new SedativesCard();
		String mapName = "galilei";
		Match match = new Match(mapName);
		GameLogic controller = new GameLogic(match);
		card.doAction(player, controller);
		assertTrue(((Human) player).isSedatives());
		
	}

	@Test
	public void testSedativesCard() {
		Card card = new SedativesCard();
		assertNotNull(card);
	}

}
