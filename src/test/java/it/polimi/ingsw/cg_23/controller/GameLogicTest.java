package it.polimi.ingsw.cg_23.controller;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_23.model.cards.Card;
import it.polimi.ingsw.cg_23.model.cards.SedativesCard;
import it.polimi.ingsw.cg_23.model.cards.TeleportCard;
import it.polimi.ingsw.cg_23.model.players.Human;
import it.polimi.ingsw.cg_23.model.players.Player;
import it.polimi.ingsw.cg_23.model.status.Match;

import org.junit.Test;

public class GameLogicTest {

	/*@Test
	public void testGameLogic() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testPlayGame() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidMove() {
		fail("Not yet implemented");
	}*/

	@Test
	public void testHasCardTrue() {
		Player player = new Human("dummy");
		Card card = new TeleportCard();
		String mapName = "galilei";
		Match match = new Match(mapName);
		GameLogic controller = new GameLogic(match);
		player.getCards().add(card);
		controller.hasCard(player, card);
		assertTrue(controller.hasCard(player, card));
	}
	
	@Test
	public void testHasCardFalse() {
		Player player = new Human("dummy");
		Card card = new SedativesCard();
		String mapName = "galilei";
		Match match = new Match(mapName);
		GameLogic controller = new GameLogic(match);
		controller.hasCard(player, card);
		assertFalse(controller.hasCard(player, card));
	}

	@Test
	public void testUseItemCard() {
		Player player = new Human("dummy");
		Card card = new TeleportCard();
		String mapName = "galilei";
		Match match = new Match(mapName);
		GameLogic controller = new GameLogic(match);
		controller.useItemCard(player, card);
		assertFalse(player.getCards().contains(card));
	}

	/*@Test
	public void testUseOtherCard() {
		fail("Not yet implemented");
	}

	@Test
	public void testDiscardCard() {
		fail("Not yet implemented");
	}

	@Test
	public void testDrawEscapeHatchCard() {
		fail("Not yet implemented");
	}

	@Test
	public void testDrawSectorCard() {
		fail("Not yet implemented");
	}

	@Test
	public void testShuffleSectorDeck() {
		fail("Not yet implemented");
	}

	@Test
	public void testShuffleItemDeck() {
		fail("Not yet implemented");
	}

	@Test
	public void testPickSectorCard() {
		fail("Not yet implemented");
	}

	@Test
	public void testPickItemCard() {
		fail("Not yet implemented");
	}

	@Test
	public void testDrawItemDeck() {
		fail("Not yet implemented");
	}

	@Test
	public void testChoseHowUseItemCard() {
		fail("Not yet implemented");
	}*/

}
