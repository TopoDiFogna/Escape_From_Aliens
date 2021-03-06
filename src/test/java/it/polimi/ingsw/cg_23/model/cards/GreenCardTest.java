package it.polimi.ingsw.cg_23.model.cards;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_23.controller.GameLogic;
import it.polimi.ingsw.cg_23.model.map.Sector;
import it.polimi.ingsw.cg_23.model.map.SectorTypeEnum;
import it.polimi.ingsw.cg_23.model.players.Human;
import it.polimi.ingsw.cg_23.model.players.Player;
import it.polimi.ingsw.cg_23.model.status.Match;
import it.polimi.ingsw.cg_23.network.rmi.RMIBroker;
import it.polimi.ingsw.cg_23.network.socket.SocketBroker;

import org.junit.Test;

public class GreenCardTest {

	@Test
	public void testDoAction() {
		Player player = new Human("Dummy");
		Card card = new GreenCard();
		Match match = new Match("galilei");
		GameLogic controller = new GameLogic(match);
		SocketBroker broker = new SocketBroker("broker");
		RMIBroker rmibroker = new RMIBroker("broker");
        controller.setRMIBroker(rmibroker);
		controller.setSocketBroker(broker);
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
