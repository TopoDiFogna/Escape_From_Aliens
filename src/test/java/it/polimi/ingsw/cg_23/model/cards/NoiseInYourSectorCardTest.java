package it.polimi.ingsw.cg_23.model.cards;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_23.controller.GameLogic;
import it.polimi.ingsw.cg_23.model.players.Human;
import it.polimi.ingsw.cg_23.model.players.Player;
import it.polimi.ingsw.cg_23.model.status.Match;

import org.junit.Test;

public class NoiseInYourSectorCardTest {

	@Test
	public void testDoAction() {
		Player player = new Human("dummy");
		Card card = new NoiseInYourSectorCard(false);
		String mapName = "galilei";
		Match match = new Match(mapName);
		GameLogic controller = new GameLogic(match);
		card.doAction(player, controller);
		
	}

	@Test
	public void testNoiseInYourSectorCard() {
		Card card = new NoiseInYourSectorCard(true);
		assertNotNull(card);
	}

	@Test
	public void testHasItem() {
		NoiseInYourSectorCard card = new NoiseInYourSectorCard(false);
		assertFalse(card.hasItem());
	}

}
