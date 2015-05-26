package it.polimi.ingsw.cg_23.model.cards;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_23.controller.GameLogic;
import it.polimi.ingsw.cg_23.model.map.Sector;
import it.polimi.ingsw.cg_23.model.map.SectorTypeEnum;
import it.polimi.ingsw.cg_23.model.players.Alien;
import it.polimi.ingsw.cg_23.model.players.Human;
import it.polimi.ingsw.cg_23.model.players.Player;
import it.polimi.ingsw.cg_23.model.status.Match;

import org.junit.Test;

public class DefenseCardTest {


	@Test
	public void testDoActionWithDefenseCard() {
		Player player1 = new Human("dummy1");		
		Player player2 = new Alien("dummy2");
		Card cardAttack = new AttackCard();
		Card cardDefense = new DefenseCard();
		Match match = new Match("galilei");
		GameLogic controller = new GameLogic(match);
		Sector sector = new Sector(3, 6, SectorTypeEnum.DANGEROUS, true);
		match.addNewPlayerToList(player1);
		match.addNewPlayerToList(player2);
		player1.setCurrentSector(sector);
		player2.setCurrentSector(sector);	
		sector.setPlayer(player1);
		sector.setPlayer(player2);
		player2.getCards().add(cardDefense);
		cardAttack.doAction(player1, controller);
		cardDefense.doAction(player2, controller);
		assertNotEquals(player2, player1);
		assertTrue(player2.isAlive());
		assertTrue(player1.isAlive());
	}
/*	public void testDoAction() {
		Player player1 = new Human("dummy1");
		Player player2 = new Human("dummy2");
		Card card1 = new AttackCard();
		Card card2 = new DefenseCard();
		player1.getCards().add(card1);
		player2.getCards().add(card2);
		String mapName = "galilei";
		Match match = new Match(mapName);
		GameLogic controller = new GameLogic(match);
		Sector sector = new Sector(3, 6, SectorTypeEnum.DANGEROUS, true);
		player1.setCurrentSector(sector);
		player2.setCurrentSector(sector);	
		card1.doAction(player1, controller);
		assertTrue(player2.isAlive());
		//assertTrue(sector.getPlayer().contains(player2));
	}*/
		
	@Test
	public void testDefenseCard() {
		Card card = new DefenseCard();
		assertNotNull(card);
	}

}
