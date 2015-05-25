package it.polimi.ingsw.cg_23.model.cards;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_23.controller.GameLogic;
import it.polimi.ingsw.cg_23.model.map.Sector;
import it.polimi.ingsw.cg_23.model.map.SectorTypeEnum;
import it.polimi.ingsw.cg_23.model.players.Human;
import it.polimi.ingsw.cg_23.model.players.Player;
import it.polimi.ingsw.cg_23.model.status.Match;

import org.junit.Test;

public class TeleportCardTest {

	@Test
	public void testDoAction() {
		Player player = new Human("Dummy");
		Card card = new TeleportCard();
		String mapName = "galilei";
		Sector sector = new Sector(4, 12, SectorTypeEnum.DANGEROUS, true);
		player.setCurrentSector(sector);
		Match match = new Match(mapName);
		GameLogic controller = new GameLogic(match);
		card.doAction(player, controller);
		assertEquals(match.getMap().getHumanSector(), player.getCurrentSector());
	}

	@Test
	public void testTeleportCard() {
		Card card = new TeleportCard();
		assertNotNull(card);
	}

}
