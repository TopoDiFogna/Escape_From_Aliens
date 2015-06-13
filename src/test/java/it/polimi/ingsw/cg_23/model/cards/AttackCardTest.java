package it.polimi.ingsw.cg_23.model.cards;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_23.controller.GameLogic;
import it.polimi.ingsw.cg_23.model.map.Sector;
import it.polimi.ingsw.cg_23.model.map.SectorTypeEnum;
import it.polimi.ingsw.cg_23.model.players.Alien;
import it.polimi.ingsw.cg_23.model.players.Human;
import it.polimi.ingsw.cg_23.model.players.Player;
import it.polimi.ingsw.cg_23.model.status.Match;
import it.polimi.ingsw.cg_23.network.rmi.RMIBroker;
import it.polimi.ingsw.cg_23.network.socket.SocketBroker;

import org.junit.Test;

public class AttackCardTest {

	@Test
	public void testDoActionWithoutDefenseCard() {
		Player player1 = new Human("dummy1");		
		Player player2 = new Alien("dummy2");
		Card card = new AttackCard();
		Match match = new Match("galilei");
		GameLogic controller = new GameLogic(match);
		SocketBroker broker = new SocketBroker("broker");
		RMIBroker rmibroker = new RMIBroker("broker");
        controller.setRMIBroker(rmibroker);
		controller.setSocketBroker(broker);
		Sector sector = new Sector(3, 6, SectorTypeEnum.DANGEROUS, true);
		match.addNewPlayerToList(player1);
		match.addNewPlayerToList(player2);
		player1.setCurrentSector(sector);
		player2.setCurrentSector(sector);	
		sector.setPlayer(player1);
		sector.setPlayer(player2);
		card.doAction(player1, controller);
		assertNotEquals(player2, player1);
		assertFalse(player2.isAlive());
		assertTrue(player1.isAlive());
	}
	
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
		assertNotEquals(player2, player1);
		assertTrue(player2.isAlive());
		assertTrue(player1.isAlive());
	}

	@Test
	public void testAttackCard() {
		Card card = new AttackCard();
		assertNotNull(card);
	}

}