package it.polimi.ingsw.cg_23.model.cards;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_23.controller.GameLogic;
import it.polimi.ingsw.cg_23.model.map.Sector;
import it.polimi.ingsw.cg_23.model.map.SectorTypeEnum;
import it.polimi.ingsw.cg_23.model.players.Human;
import it.polimi.ingsw.cg_23.model.players.Player;
import it.polimi.ingsw.cg_23.model.status.Match;

import org.junit.Test;

public class GreenCardTest {

	@Test
	public void testDoAction() {
		Player player = new Human("Dummy");
		Card card = new GreenCard();
		String mapName = "galilei";
		Match match = new Match(mapName);
		GameLogic controller = new GameLogic(match);
		Sector sector = new Sector(2, 2, SectorTypeEnum.ESCAPEHATCH, true);
		player.setCurrentSector(sector);
		card.doAction(player, controller);
		assertFalse(player.getCurrentSector().isCrossable());
		assertFalse(player.getCurrentSector().getPlayer().contains(player));
	}

	@Test
	public void testGreenCard() {
		Card card = new GreenCard();
		assertNotNull(card);
	}

}
