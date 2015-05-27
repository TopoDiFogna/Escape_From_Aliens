package it.polimi.ingsw.cg_23.controller;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_23.model.cards.AdrenalineCard;
import it.polimi.ingsw.cg_23.model.cards.AttackCard;
import it.polimi.ingsw.cg_23.model.cards.Card;
import it.polimi.ingsw.cg_23.model.cards.Deck;
import it.polimi.ingsw.cg_23.model.cards.DeckFactory;
import it.polimi.ingsw.cg_23.model.cards.DefenseCard;
import it.polimi.ingsw.cg_23.model.cards.NoiseInAnySectorCard;
import it.polimi.ingsw.cg_23.model.cards.NoiseInYourSectorCard;
import it.polimi.ingsw.cg_23.model.cards.SedativesCard;
import it.polimi.ingsw.cg_23.model.cards.SilenceCard;
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
		Match match = new Match("galilei");
		GameLogic controller = new GameLogic(match);
		player.getCards().add(card);
		controller.hasCard(player, card);
		assertTrue(controller.hasCard(player, card));
	}
	
	@Test //TODO da controllare... non so se davvero controlla il false perchè in game logic resta giallo
	public void testHasCardFalse() {
		Player player = new Human("dummy");
		Card card = new SilenceCard(true);
		Match match = new Match("galilei");
		GameLogic controller = new GameLogic(match);
		controller.hasCard(player, card);
		assertFalse(controller.hasCard(player, card));
	}

	@Test
	public void testUseItemCard() {
		Player player = new Human("dummy");
		Card card = new TeleportCard();
		Match match = new Match("galilei");
		GameLogic controller = new GameLogic(match);
		controller.useItemCard(player, card);
		assertFalse(player.getCards().contains(card));
	}

	/*@Test
	public void testUseOtherCard() {
		Player player = new Human("Dummy");
		Match match = new Match("galilei");
		GameLogic controller = new GameLogic(match);
		match.getPlayers().add(player);
		Card card = new GreenCard();
		controller.useOtherCard(player, card);
		assertFalse(match.getPlayers().contains(player));
	}*/

	@Test
	public void testDiscardCard() {
		Player player = new Human("dummy");
		Card card = new SedativesCard();
		Match match = new Match("galilei");
		GameLogic controller = new GameLogic(match);
		player.getCards().add(card);
		controller.discardItemCard(player, card);
		assertFalse(player.getCards().contains(card));
		assertTrue(match.getItemDeckDiscarded().contains(card));
	}

	/*@Test
	public void testDrawEscapeHatchCard() {
		Player player = new Human("Dummy");
		Card card = new GreenCard();
		Match match = new Match("galilei");
		GameLogic controller = new GameLogic(match);
		Deck<Card> escapeHatchDeck = DeckFactory.createDeck(2);
		match.setEscapeHatchDeck(escapeHatchDeck);
		match.getEscapeHatchDeck().add(card);
		Deck<Card> escapeHatchDeckDiscarded = DeckFactory.createDeck(3);
		match.setEscapeHatchDeckDiscarded(escapeHatchDeckDiscarded);
		controller.drawEscapeHatchCard(player);
		assertFalse(match.getPlayers().contains(player));
	}*/

	@Test
	public void testDrawSectorCardWithEmptySectorDeck() {
		Player player = new Human("Dummy");
		Match match = new Match("galilei");
		GameLogic controller = new GameLogic(match);
		Card card = new NoiseInAnySectorCard(true);
		match.getSectorDeck().clear();
		match.getSectorDeckDiscarded().add(0, card);
		controller.drawSectorCard(player);
		//TODO right assertion
	}
	
	@Test
	public void testDrawSectorCardNoiseAnySectorWithItem() {
		Player player = new Human("Dummy");
		Match match = new Match("galilei");
		GameLogic controller = new GameLogic(match);
		Card card = new NoiseInAnySectorCard(true);
		match.getSectorDeck().add(0, card);
		controller.drawSectorCard(player);
		assertFalse(match.getSectorDeck().contains(card));
	}
	
	@Test
	public void testDrawSectorCardNoiseAnySectorWithoutItem() {
		Player player = new Human("Dummy");
		Match match = new Match("galilei");
		GameLogic controller = new GameLogic(match);
		Card card = new NoiseInAnySectorCard(false);
		match.getSectorDeck().add(0, card);
		controller.drawSectorCard(player);
		assertFalse(match.getSectorDeck().contains(card));
	}
	
	@Test
	public void testDrawSectorCardYourAnySectorWithItem() {
		Player player = new Human("Dummy");
		Match match = new Match("galilei");
		GameLogic controller = new GameLogic(match);
		Card card = new NoiseInYourSectorCard(true);
		match.getSectorDeck().add(0, card);
		controller.drawSectorCard(player);
		assertFalse(match.getSectorDeck().contains(card));
	}
	
	@Test
	public void testDrawSectorCardNoiseYourSectorWithoutItem() {
		Player player = new Human("Dummy");
		Match match = new Match("galilei");
		GameLogic controller = new GameLogic(match);
		Card card = new NoiseInYourSectorCard(false);
		match.getSectorDeck().add(0, card);
		controller.drawSectorCard(player);
		assertFalse(match.getSectorDeck().contains(card));
	}

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
	public void testShuffleItemDeckIfConditionTrue() {
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
		assertTrue(!match.getItemDeckDiscarded().isEmpty());
		controller.shuffleSectorDeck();
		for (Card card : itemDeckDiscarded) {
			count ++;
			assertTrue(card instanceof AttackCard);
		}
		assertEquals(3, count);
	}

	@Test
	public void testPickSectorCard() {
		Match match = new Match("galilei");
		GameLogic controller = new GameLogic(match);
		Card card = controller.pickSectorCard();
		assertFalse(match.getSectorDeck().contains(card));
	}

	@Test
	public void testPickItemCard() {
		Match match = new Match("galilei");
		GameLogic controller = new GameLogic(match);
		Card card = controller.pickItemCard();
		assertFalse(match.getItemDeck().contains(card));
	}

	@Test
	public void testDrawItemCardWithItemDeckNotEmpty() {
		Match match = new Match("galilei");
		GameLogic controller = new GameLogic(match);
		Card card = new AttackCard();
		match.getItemDeck().add(card);
		Card itemCard = controller.drawItemCard();
		assertNotNull(itemCard);
	}
	
	@Test
	public void testDrawItemCardWithItemDeckEmpty() {
		Match match = new Match("galilei");
		GameLogic controller = new GameLogic(match);
		Card card = new AttackCard();
		match.getItemDeck().clear();
		match.getItemDeckDiscarded().add(card);
		Card itemCard = controller.drawItemCard();
		assertNotNull(itemCard);
	}
	
	@Test
	public void testDrawItemCardWithItemDeckAndItemDeckDiscardedEmpty() {
		Match match = new Match("galilei");
		GameLogic controller = new GameLogic(match);
		match.getItemDeck().clear();
		match.getItemDeckDiscarded().clear();;
		Card itemCard = controller.drawItemCard();
		assertNull(itemCard);
	}

	@Test
	public void testChoseHowUseItemCardForUseChoice() {
		Player player = new Human("Dummy");
		Match match = new Match("galilei");
		GameLogic controller = new GameLogic(match);
		Card card1 = new AttackCard();
		Card card2 = new AdrenalineCard();
		Card card3 = new SedativesCard();
		Card card4 = new DefenseCard();
		player.getCards().add(card1);
		player.getCards().add(card2);
		player.getCards().add(card3);
		controller.choseHowUseItemCard(player, card4, "usa");
		assertTrue(player.getCards().size()<4);
		assertTrue(match.getItemDeckDiscarded().contains(card4));
	}
	
	@Test
	public void testChoseHowUseItemCardForDiscardChoice() {
		Player player = new Human("Dummy");
		Match match = new Match("galilei");
		GameLogic controller = new GameLogic(match);
		Card card1 = new AttackCard();
		Card card2 = new AdrenalineCard();
		Card card3 = new SedativesCard();
		Card card4 = new DefenseCard();
		player.getCards().add(card1);
		player.getCards().add(card2);
		player.getCards().add(card3);
		controller.choseHowUseItemCard(player, card4, "butta");
		assertTrue(player.getCards().size()<4);
		assertTrue(match.getItemDeckDiscarded().contains(card4));
	}
	
	@Test
	public void testChoseHowUseItemCardForNullChoice() {
		Player player = new Human("Dummy");
		Match match = new Match("galilei");
		GameLogic controller = new GameLogic(match);
		Card card1 = new AttackCard();
		Card card2 = new AdrenalineCard();
		Card card3 = new SedativesCard();
		Card card4 = new DefenseCard();
		player.getCards().add(card1);
		player.getCards().add(card2);
		player.getCards().add(card3);
		controller.choseHowUseItemCard(player, card4, null);
		assertTrue(player.getCards().size()>3);
	}
	
	@Test
	public void testChoseHowUseItemCardForNotValidChoice() {
		Player player = new Human("Dummy");
		Match match = new Match("galilei");
		GameLogic controller = new GameLogic(match);
		Card card1 = new AttackCard();
		Card card2 = new AdrenalineCard();
		Card card3 = new SedativesCard();
		Card card4 = new DefenseCard();
		player.getCards().add(card1);
		player.getCards().add(card2);
		player.getCards().add(card3);
		controller.choseHowUseItemCard(player, card4, "altro");
		assertTrue(player.getCards().size()<4);
		assertTrue(match.getItemDeckDiscarded().contains(card4));
	}


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
	
	/*@Test
	public void testMovePlayer(){
		
	}
	
	@Test
	public void testCanAttack(){
		
	}*/
}