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

public class AttackCardTest {

	@Test
	public void testDoAction() {
		Player player1 = new Human("dummy1");		
		Player player2 = new Alien("dummy2");
		Card card = new AttackCard();
		String mapName = "galilei";
		Match match = new Match(mapName);
		GameLogic controller = new GameLogic(match);
		Sector sector = new Sector(3, 6, SectorTypeEnum.DANGEROUS, true);
		player1.setCurrentSector(sector);
		player2.setCurrentSector(sector);		
		card.doAction(player1, controller);
		assertFalse(player1.getCurrentSector().getPlayer().contains(player2));
	}

	@Test
	public void testAttackCard() {
		Card card = new AttackCard();
		assertNotNull(card);
	}

}