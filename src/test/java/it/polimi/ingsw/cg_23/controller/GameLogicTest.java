package it.polimi.ingsw.cg_23.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;

import it.polimi.ingsw.cg_23.model.cards.AttackCard;
import it.polimi.ingsw.cg_23.model.cards.Card;
import it.polimi.ingsw.cg_23.model.cards.Deck;
import it.polimi.ingsw.cg_23.model.cards.DeckFactory;
import it.polimi.ingsw.cg_23.model.cards.NoiseInAnySectorCard;
import it.polimi.ingsw.cg_23.model.cards.NoiseInYourSectorCard;
import it.polimi.ingsw.cg_23.model.cards.SedativesCard;
import it.polimi.ingsw.cg_23.model.cards.TeleportCard;
import it.polimi.ingsw.cg_23.model.map.Sector;
import it.polimi.ingsw.cg_23.model.map.SectorTypeEnum;
import it.polimi.ingsw.cg_23.model.players.Alien;
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
	}*/

	@Test
	public void testDiscardCard() {
		Player player = new Human("dummy");
		Card card = new SedativesCard();
		Match match = new Match("galilei");
		GameLogic controller = new GameLogic(match);
		player.getCards().add(card);
		controller.discardCard(player, card);
		assertFalse(player.getCards().contains(card));
		assertTrue(match.getItemDeckDiscarded().contains(card));
	}

	/*@Test
	public void testDrawEscapeHatchCard() {
		fail("Not yet implemented");
	}

	@Test
	public void testDrawSectorCard() {
		fail("Not yet implemented");
	}*/

	@Test
	public void testShuffleSectorDeck() {
		int count = 0;
		Card card1 = new NoiseInAnySectorCard(false);
		Card card2 = new NoiseInAnySectorCard(false);
		Card card3 = new NoiseInAnySectorCard(true);
		Match match = new Match("galilei");
		GameLogic controller = new GameLogic(match);
		Deck<Card> sectorDeckDiscarded = DeckFactory.createDeck(3);
		match.setSectorDeckDiscarded(sectorDeckDiscarded);
		match.getSectorDeckDiscarded().add(card1);
		match.getSectorDeckDiscarded().add(card2);
		match.getSectorDeckDiscarded().add(card3);
		controller.shuffleSectorDeck();
		for (Card card : sectorDeckDiscarded) {
			count ++;
			assertTrue(card instanceof NoiseInAnySectorCard);
		}
		assertEquals(3, count);
	}

	@Test
	public void testShuffleItemDeck() {
		int count = 0;
		Card card1 = new AttackCard();
		Card card2 = new AttackCard();
		Card card3 = new AttackCard();
		Match match = new Match("galilei");
		GameLogic controller = new GameLogic(match);
		Deck<Card> itemDeckDiscarded = DeckFactory.createDeck(3);
		match.setItemDeckDiscarded(itemDeckDiscarded);
		match.getItemDeckDiscarded().add(card1);
		match.getItemDeckDiscarded().add(card2);
		match.getItemDeckDiscarded().add(card3);
		controller.shuffleSectorDeck();
		for (Card card : itemDeckDiscarded) {
			count ++;
			assertTrue(card instanceof AttackCard);
		}
		assertEquals(3, count);
	}

	/*@Test
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

	@Test
	public void testRemoveAfterDyingTrue() {
		Player player = new Alien("dummy");
		Match match = new Match("galilei");
		Sector[][] map = match.getMap().getSector();
		GameLogic controller = new GameLogic(match);
		player.setCurrentSector(map[1][1]);
		match.addNewPlayerToList(player);
		player.setDead();
		controller.removeAfterDying(player);
		assertFalse(player.isAlive());
		assertFalse(player.getCurrentSector().getPlayer().contains(player));
		assertFalse(match.getPlayers().contains(player));
	}
	
	@Test
	public void testRemoveAfterDyingFalse() {
		Player player = new Alien("dummy");
		Match match = new Match("galilei");
		Sector[][] map = match.getMap().getSector();
		GameLogic controller = new GameLogic(match);
		player.setCurrentSector(map[1][1]);
		match.addNewPlayerToList(player);
		controller.removeAfterDying(player);
		assertTrue(player.isAlive());
	}
	
	@Test
	public void testRemoveAfterWinningTrue(){
		Player player = new Alien("dummy");
		Match match = new Match("galilei");
		Sector sector = new Sector(2, 13, SectorTypeEnum.ESCAPEHATCH, true);
		GameLogic controller = new GameLogic(match);
		player.setCurrentSector(sector);
		match.addNewPlayerToList(player);
		player.getCurrentSector().setEscapeHatchSectorNotCrossable();
		controller.removeAfterWinning(player);
		assertFalse(sector.isCrossable());
		assertFalse(player.getCurrentSector().getPlayer().contains(player));
		assertFalse(match.getPlayers().contains(player));
	}
	
	@Test
	public void testRemoveAfterWinningFalse(){
		Player player = new Alien("dummy");
		Match match = new Match("galilei");
		Sector sector = new Sector(2, 13, SectorTypeEnum.ESCAPEHATCH, true);
		GameLogic controller = new GameLogic(match);
		player.setCurrentSector(sector);
		match.addNewPlayerToList(player);
		controller.removeAfterWinning(player);
		assertTrue(sector.isCrossable());
	}
}