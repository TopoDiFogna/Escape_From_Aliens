package it.polimi.ingsw.cg_23.controller;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_23.model.cards.AdrenalineCard;
import it.polimi.ingsw.cg_23.model.cards.AttackCard;
import it.polimi.ingsw.cg_23.model.cards.Card;
import it.polimi.ingsw.cg_23.model.cards.Deck;
import it.polimi.ingsw.cg_23.model.cards.DeckFactory;
import it.polimi.ingsw.cg_23.model.cards.DefenseCard;
import it.polimi.ingsw.cg_23.model.cards.GreenCard;
import it.polimi.ingsw.cg_23.model.cards.NoiseInAnySectorCard;
import it.polimi.ingsw.cg_23.model.cards.NoiseInYourSectorCard;
import it.polimi.ingsw.cg_23.model.cards.RedCard;
import it.polimi.ingsw.cg_23.model.cards.SedativesCard;
import it.polimi.ingsw.cg_23.model.cards.SilenceCard;
import it.polimi.ingsw.cg_23.model.cards.TeleportCard;
import it.polimi.ingsw.cg_23.model.map.Sector;
import it.polimi.ingsw.cg_23.model.map.SectorTypeEnum;
import it.polimi.ingsw.cg_23.model.players.Alien;
import it.polimi.ingsw.cg_23.model.players.Human;
import it.polimi.ingsw.cg_23.model.players.Player;
import it.polimi.ingsw.cg_23.model.status.Match;
import it.polimi.ingsw.cg_23.network.Broker;

import org.junit.Test;

public class GameLogicTest {

    @Test
    public void testValidMoveHumanInVoidSector() {
        Player player = new Human("Dummy");
        Match match = new Match("galilei");
        GameLogic controller = new GameLogic(match);
        Broker broker = new Broker("broker");
        controller.setBroker(broker);
        Sector sector = new Sector(2, 7, SectorTypeEnum.VOID, false);
        player.setCurrentSector(sector);
        assertFalse(controller.validMove(player, sector));
    }
    
    @Test
    public void testValidMoveHumanInHumanSector() {
        Player player = new Human("Dummy");
        Match match = new Match("galilei");
        GameLogic controller = new GameLogic(match);
        Broker broker = new Broker("broker");
        controller.setBroker(broker);
        Sector sector = new Sector(12, 8, SectorTypeEnum.HUMAN, false);
        player.setCurrentSector(sector);
        assertFalse(controller.validMove(player, sector));
    }
    
    @Test
    public void testValidMoveHumanInAlienSector() {
        Player player = new Human("Dummy");
        Match match = new Match("galilei");
        GameLogic controller = new GameLogic(match);
        Broker broker = new Broker("broker");
        controller.setBroker(broker);
        Sector sector = new Sector(12, 6, SectorTypeEnum.ALIEN, false);
        player.setCurrentSector(sector);
        assertFalse(controller.validMove(player, sector));
    }
    
   @Test
    public void testValidMoveNeighborTrue() {
        Player player = new Human("Dummy");
        Match match = new Match("galilei");
        GameLogic controller = new GameLogic(match);
        Broker broker = new Broker("broker");
        controller.setBroker(broker);
        Sector sector = match.getMap().getSector()[1][7];
        player.setCurrentSector(sector);
        Sector destination = match.getMap().getSector()[2][7];
        assertTrue(controller.validMove(player, destination));
        assertTrue(sector.getNeighbors().contains(destination));
    }
   
   @Test
   public void testValidMoveNeighborFalse() {
       Player player = new Human("Dummy");
       Match match = new Match("galilei");
       GameLogic controller = new GameLogic(match);
       Broker broker = new Broker("broker");
       controller.setBroker(broker);
       Sector sector = match.getMap().getSector()[1][7];
       player.setCurrentSector(sector);
       Sector destination = match.getMap().getSector()[20][5];
       assertFalse(controller.validMove(player, destination));
       assertFalse(sector.getNeighbors().contains(destination));
   }
    
    @Test
    public void testValidMoveAlien() {
        Player player = new Alien("Dummy");
        Match match = new Match("galilei");
        GameLogic controller = new GameLogic(match);
        Broker broker = new Broker("broker");
        controller.setBroker(broker);
        Sector sector = match.getMap().getSector()[1][7];
        Sector destination = match.getMap().getSector()[3][7];
        player.setCurrentSector(sector);        
        assertTrue(controller.validMove(player, destination));
        for (Sector sectorNeighbors : player.getCurrentSector().getNeighbors()) {
            for (Sector sectorNeighborOfNeighbors : sectorNeighbors.getNeighbors()){
                if(sectorNeighborOfNeighbors.equals(destination))
                    assertTrue(sectorNeighborOfNeighbors.equals(destination));
            }
        }
        assertTrue(player.getCanMoveFaster());
    }

    @Test
    public void testUseSpotlight() {
        Match match = new Match("galilei");
        GameLogic controller = new GameLogic(match);
        Player player1 = new Human("Dummy1");
        Player player2 = new Alien("Dummy2");
        Sector sector1 = new Sector(16, 8, SectorTypeEnum.DANGEROUS, true);
        Sector sector2 = new Sector(15, 9, SectorTypeEnum.SECURE, true);
        player1.setCurrentSector(sector1);
        player2.setCurrentSector(sector2);
        controller.useSpotlight(16, 8);
        assertTrue(player1.getName().equals("Dummy1"));
        assertTrue(player2.getName().equals("Dummy2"));
    }

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

    @Test
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
        player.setCurrentSector(match.getMap().getSector()[0][0]);
        GameLogic controller = new GameLogic(match);
        controller.useItemCard(player, card);
        assertFalse(player.getCards().contains(card));
    }

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

    @Test
    public void testDrawEscapeHatchCardWithGreenCard() {
        Player player = new Human("Dummy");
        Match match = new Match("galilei");
        match.getPlayers().add(player);
        GameLogic controller = new GameLogic(match);
        Broker broker = new Broker("broker");
        controller.setBroker(broker);
        Card card = new GreenCard();
        Deck<Card> deckEscapeHatch = DeckFactory.createDeck(2);
        deckEscapeHatch.add(card);
        Sector sector = new Sector(2, 13, SectorTypeEnum.ESCAPEHATCH, true);
        player.setCurrentSector(sector);
        controller.drawEscapeHatchCard(player);
        assertNotNull(match.getEscapeHatchDeckDiscarded());
        assertFalse(player.getCurrentSector().isCrossable());
    }

    @Test
    public void testDrawEscapeHatchCardWithRedCard() {
        Player player = new Human("Dummy");
        Match match = new Match("galilei");
        match.getPlayers().add(player);
        GameLogic controller = new GameLogic(match);
        Broker broker = new Broker("broker");
        controller.setBroker(broker);
        Card card = new RedCard();
        Deck<Card> deckEscapeHatch = DeckFactory.createDeck(2);
        deckEscapeHatch.add(card);
        Sector sector = new Sector(2, 13, SectorTypeEnum.ESCAPEHATCH, true);
        player.setCurrentSector(sector);
        controller.drawEscapeHatchCard(player);
        assertNotNull(match.getEscapeHatchDeckDiscarded());
        assertFalse(player.getCurrentSector().isCrossable());
    }

    @Test
    public void testDrawSectorCardWithEmptySectorDeck() {
        Player player = new Human("Dummy");
        Match match = new Match("galilei");
        GameLogic controller = new GameLogic(match);
        Broker broker = new Broker("broker");
        controller.setBroker(broker);
        Card card1 = new NoiseInAnySectorCard(true);
        Card card2 = new NoiseInAnySectorCard(false);
        match.getSectorDeck().clear();
        match.getSectorDeckDiscarded().add(0, card1);
        match.getSectorDeckDiscarded().add(card2);
        controller.drawSectorCard(player);
        assertTrue(match.getSectorDeck().contains(card2));
        assertTrue(match.getSectorDeckDiscarded().contains(card1));
    }

    @Test
    public void testDrawSectorCardNoiseAnySectorWithItem() {
        Player player = new Human("Dummy");
        Match match = new Match("galilei");
        GameLogic controller = new GameLogic(match);
        Broker broker = new Broker("broker");
        controller.setBroker(broker);
        Card card = new NoiseInAnySectorCard(true);
        match.getSectorDeck().add(0, card);
        controller.drawSectorCard(player);
        assertFalse(match.getSectorDeck().contains(card));
        assertTrue(player.needSectorNoise());
    }

    @Test
    public void testDrawSectorCardSilence() {
        Player player = new Human("Dummy");
        Match match = new Match("galilei");
        GameLogic controller = new GameLogic(match);
        Broker broker = new Broker("broker");
        controller.setBroker(broker);
        Card card = new SilenceCard(false);
        match.getSectorDeck().add(0, card);
        controller.drawSectorCard(player);
        assertFalse(match.getSectorDeck().contains(card));
        assertTrue(match.getSectorDeckDiscarded().contains(card));
    }

    @Test
    public void testDrawSectorCardNoiseAnySectorWithoutItem() {
        Player player = new Human("Dummy");
        Match match = new Match("galilei");
        GameLogic controller = new GameLogic(match);
        Broker broker = new Broker("broker");
        controller.setBroker(broker);
        Card card = new NoiseInAnySectorCard(false);
        match.getSectorDeck().add(0, card);
        controller.drawSectorCard(player);
        assertFalse(match.getSectorDeck().contains(card));
        assertTrue(player.needSectorNoise());
    }

    @Test
    public void testDrawSectorCardNoiseYourSectorWithItem() {
        Player player = new Human("Dummy");
        Match match = new Match("galilei");
        GameLogic controller = new GameLogic(match);
        Broker broker = new Broker("broker");
        controller.setBroker(broker);
        Card card = new NoiseInYourSectorCard(true);
        player.setCurrentSector(match.getMap().getSector()[0][0]);
        match.getSectorDeck().add(0, card);
        controller.drawSectorCard(player);
        assertFalse(match.getSectorDeck().contains(card));
    }

    @Test
    public void testDrawSectorCardNoiseYourSectorWithoutItem() {
        Player player = new Human("Dummy");
        Match match = new Match("galilei");
        GameLogic controller = new GameLogic(match);
        Broker broker = new Broker("broker");
        controller.setBroker(broker);
        Card card = new NoiseInYourSectorCard(false);
        player.setCurrentSector(match.getMap().getSector()[0][0]);
        match.getSectorDeck().add(0, card);
        controller.drawSectorCard(player);
        assertFalse(match.getSectorDeck().contains(card));
    }

    @Test
    public void testDrawSectorCardNoiseYourSectorWithItemAndAlreadyThreeCardInHand() {
        Player player = new Human("Dummy");
        Match match = new Match("galilei");
        GameLogic controller = new GameLogic(match);
        Broker broker = new Broker("broker");
        controller.setBroker(broker);
        Card card = new NoiseInYourSectorCard(true);
        Card card1 = new AttackCard();
        Card card2 = new SedativesCard();
        Card card3 = new TeleportCard();
        player.getCards().add(card1);
        player.getCards().add(card2);
        player.getCards().add(card3);
        player.setCurrentSector(match.getMap().getSector()[0][0]);
        match.getSectorDeck().add(0, card);
        controller.drawSectorCard(player);
        assertFalse(match.getSectorDeck().contains(card));
        assertTrue(player.hasFourCard());
    }

    @Test
    public void testDrawSectorCardNoiseAnySectorWithItemAndAlreadyThreeCardInHand() {
        Player player = new Human("Dummy");
        Match match = new Match("galilei");
        GameLogic controller = new GameLogic(match);
        Broker broker = new Broker("broker");
        controller.setBroker(broker);
        Card card = new NoiseInAnySectorCard(true);
        Card card1 = new AttackCard();
        Card card2 = new SedativesCard();
        Card card3 = new TeleportCard();
        player.getCards().add(card1);
        player.getCards().add(card2);
        player.getCards().add(card3);
        player.setCurrentSector(match.getMap().getSector()[0][0]);
        match.getSectorDeck().add(0, card);
        controller.drawSectorCard(player);
        assertFalse(match.getSectorDeck().contains(card));
        assertTrue(player.hasFourCard());
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
            count++;
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
        assertTrue(!match.getItemDeckDiscarded().isEmpty());
        controller.shuffleSectorDeck();
        for (Card card : itemDeckDiscarded) {
            count++;
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
        match.getItemDeckDiscarded().clear();
        ;
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
        Card card3 = new DefenseCard();
        Card card4 = new SedativesCard();
        player.getCards().add(card1);
        player.getCards().add(card2);
        player.getCards().add(card3);
        controller.choseHowUseItemCard(player, card4, "use");
        assertTrue(player.getCards().size() > 3);
        assertTrue(match.getItemDeckDiscarded().contains(card4));
    }

    @Test
    public void testChoseHowUseItemCardForDiscardChoice() {
        Player player = new Human("Dummy");
        Match match = new Match("galilei");
        GameLogic controller = new GameLogic(match);
        Card card1 = new AttackCard();
        Card card2 = new AdrenalineCard();
        Card card3 = new DefenseCard();
        Card card4 = new SedativesCard();
        player.getCards().add(card1);
        player.getCards().add(card2);
        player.getCards().add(card3);
        controller.choseHowUseItemCard(player, card4, "discard");
        assertTrue(player.getCards().size() > 3);
        assertTrue(match.getItemDeckDiscarded().contains(card4));
    }

    @Test
    public void testChoseHowUseItemCardForNullChoice() {
        Player player = new Human("Dummy");
        Match match = new Match("galilei");
        GameLogic controller = new GameLogic(match);
        Card card1 = new AttackCard();
        Card card2 = new AdrenalineCard();
        Card card3 = new DefenseCard();
        Card card4 = new SedativesCard();
        player.getCards().add(card1);
        player.getCards().add(card2);
        player.getCards().add(card3);
        controller.choseHowUseItemCard(player, card4, null);
        assertTrue(player.getCards().size() > 3);
    }

    @Test
    public void testChoseHowUseItemCardForNotValidChoice() {
        Player player = new Human("Dummy");
        Match match = new Match("galilei");
        GameLogic controller = new GameLogic(match);
        Card card1 = new AttackCard();
        Card card2 = new AdrenalineCard();
        Card card3 = new DefenseCard();
        Card card4 = new SedativesCard();
        player.getCards().add(card1);
        player.getCards().add(card2);
        player.getCards().add(card3);
        controller.choseHowUseItemCard(player, card4, "other");
        assertTrue(player.getCards().size() > 3);
        assertTrue(match.getItemDeckDiscarded().contains(card4));
    }

    @Test
    public void testRemoveAfterDyingTrue() {
        Player player = new Alien("dummy");
        Match match = new Match("galilei");
        Sector[][] map = match.getMap().getSector();
        GameLogic controller = new GameLogic(match);
        Broker broker = new Broker("broker");
        controller.setBroker(broker);
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
        Broker broker = new Broker("broker");
        controller.setBroker(broker);
        player.setCurrentSector(map[1][1]);
        match.addNewPlayerToList(player);
        controller.removeAfterDying(player);
        assertTrue(player.isAlive());
    }

    @Test
    public void testRemoveAfterWinningTrue() {
        Player player = new Alien("dummy");
        Match match = new Match("galilei");
        Sector sector = new Sector(2, 13, SectorTypeEnum.ESCAPEHATCH, true);
        GameLogic controller = new GameLogic(match);
        Broker broker = new Broker("broker");
        controller.setBroker(broker);
        player.setCurrentSector(sector);
        match.addNewPlayerToList(player);
        player.getCurrentSector().setEscapeHatchSectorNotCrossable();
        controller.removeAfterWinning(player);
        assertFalse(sector.isCrossable());
        assertFalse(player.getCurrentSector().getPlayer().contains(player));
        assertFalse(match.getPlayers().contains(player));
    }

    @Test
    public void testRemoveAfterWinningFalse() {
        Player player = new Alien("dummy");
        Match match = new Match("galilei");
        Sector sector = new Sector(2, 13, SectorTypeEnum.ESCAPEHATCH, true);
        GameLogic controller = new GameLogic(match);
        Broker broker = new Broker("broker");
        controller.setBroker(broker);
        player.setCurrentSector(sector);
        match.addNewPlayerToList(player);
        controller.removeAfterWinning(player);
        assertTrue(sector.isCrossable());
    }

    @Test
    public void testMovePlayerSedatives() {
        Player player = new Human("Dummy");
        Sector sector = new Sector(16, 8, SectorTypeEnum.DANGEROUS, true);
        Match match = new Match("galilei");
        GameLogic controller = new GameLogic(match);
        player.setCurrentSector(sector);
        player.getCurrentSector().getPlayer().add(player);
        ((Human) player).setSedatives(true);
        controller.movePlayer(player, sector);
        assertTrue(player.getCurrentSector().getPlayer().contains(player));
        assertFalse(((Human) player).isSedatives());
    }

    @Test
    public void testMovePlayerInDangerousSector() {
        Player player = new Human("Dummy");
        Sector sector = new Sector(16, 8, SectorTypeEnum.DANGEROUS, true);
        Match match = new Match("galilei");
        GameLogic controller = new GameLogic(match);
        Broker broker = new Broker("broker");
        controller.setBroker(broker);
        Card card = new SilenceCard(false);
        match.getSectorDeck().add(card);
        player.setCurrentSector(sector);
        player.getCurrentSector().getPlayer().add(player);
        controller.movePlayer(player, sector);
        assertTrue(player.getCurrentSector().getPlayer().contains(player));
        assertNotNull(match.getSectorDeckDiscarded());
    }

    @Test
    public void testMovePlayerInEscapeHatch() {
        Player player = new Human("Dummy");
        Sector sector = new Sector(2, 13, SectorTypeEnum.ESCAPEHATCH, true);
        Match match = new Match("galilei");
        GameLogic controller = new GameLogic(match);
        Card card = new GreenCard();
        Broker broker = new Broker("broker");
        controller.setBroker(broker);
        match.getEscapeHatchDeck().add(card);
        player.setCurrentSector(sector);
        player.getCurrentSector().getPlayer().add(player);
        controller.movePlayer(player, sector);
        assertFalse(match.getPlayers().contains(player));
        assertNotNull(match.getEscapeHatchDeckDiscarded());
    }

    @Test
    public void testMovePlayerInSecureSector() {
        Player player = new Human("Dummy");
        Sector sector = new Sector(2, 5, SectorTypeEnum.SECURE, true);
        Match match = new Match("galilei");
        GameLogic controller = new GameLogic(match);
        Broker broker = new Broker("broker");
        controller.setBroker(broker);
        player.setCurrentSector(sector);
        player.getCurrentSector().getPlayer().add(player);
        controller.movePlayer(player, sector);
        assertTrue(player.getCurrentSector().getPlayer().contains(player));
    }

    @Test
    public void testMovePlayerInEscapeHatchNotCrossable() {
        Player player = new Human("Dummy");
        Sector sector = new Sector(2, 13, SectorTypeEnum.ESCAPEHATCH, false);
        Match match = new Match("galilei");
        GameLogic controller = new GameLogic(match);
        Broker broker = new Broker("broker");
        controller.setBroker(broker);
        player.setCurrentSector(sector);
        player.getCurrentSector().getPlayer().add(player);
        controller.movePlayer(player, sector);
        assertTrue(player.getCurrentSector().getPlayer().contains(player));
    }

    @Test
    public void testMovePlayerInEscapeHatchNotCrossableAlien() {
        Player player = new Alien("Dummy");
        Sector sector = new Sector(2, 13, SectorTypeEnum.ESCAPEHATCH, false);
        Match match = new Match("galilei");
        GameLogic controller = new GameLogic(match);
        Broker broker = new Broker("broker");
        controller.setBroker(broker);
        player.setCurrentSector(sector);
        player.getCurrentSector().getPlayer().add(player);
        controller.movePlayer(player, sector);
        assertTrue(player.getCurrentSector().getPlayer().contains(player));
    }

    @Test
    public void testMovePlayerAndAttack() {
        Player player = new Human("Dummy");
        Sector sector = new Sector(16, 8, SectorTypeEnum.DANGEROUS, true);
        Match match = new Match("galilei");
        GameLogic controller = new GameLogic(match);
        player.setCurrentSector(sector);
        player.getCurrentSector().getPlayer().add(player);
        Card card = new SilenceCard(true);
        match.getSectorDeck().clear();
        match.getSectorDeck().add(card);
        controller.movePlayerAndAttack(player, sector);
        assertTrue(player.getCurrentSector().getPlayer().contains(player));
        assertTrue(player.hasMoved());
        assertNotNull(match.getSectorDeckDiscarded());
    }

    /*
     * @Test public void testMakeANoise(){
     * 
     * }
     */

    /*
     * @Test public void testStartGame(){
     * 
     * }
     * 
     * @Test public void testEndTurn(){
     * 
     * }
     */
}