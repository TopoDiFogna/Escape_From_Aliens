package it.polimi.ingsw.cg_23.model.cards;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_23.controller.GameLogic;
import it.polimi.ingsw.cg_23.model.map.Sector;
import it.polimi.ingsw.cg_23.model.map.SectorTypeEnum;
import it.polimi.ingsw.cg_23.model.players.Human;
import it.polimi.ingsw.cg_23.model.players.Player;
import it.polimi.ingsw.cg_23.model.status.Match;
import it.polimi.ingsw.cg_23.network.Broker;

import org.junit.Test;

public class RedCardTest {

	@Test
	public void testDoAction() {
		Player player = new Human("dummy");
		Card card = new RedCard();
		String mapName = "galilei";
		Match match = new Match(mapName);
		GameLogic controller = new GameLogic(match);
		Broker broker = new Broker("broker");
        controller.setBroker(broker);
		Sector sector = new Sector(2, 2, SectorTypeEnum.ESCAPEHATCH, true);
		player.setCurrentSector(sector);
		card.doAction(player, controller);
		assertFalse(player.getCurrentSector().isCrossable());
	}

	@Test
	public void testRedCard() {
		Card card = new RedCard();
		assertNotNull(card);
	}

}
