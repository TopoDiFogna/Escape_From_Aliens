package it.polimi.ingsw.cg_23.model.cards;

import static org.junit.Assert.*;

import java.net.Socket;

import it.polimi.ingsw.cg_23.controller.GameLogic;
import it.polimi.ingsw.cg_23.model.players.Human;
import it.polimi.ingsw.cg_23.model.players.Player;
import it.polimi.ingsw.cg_23.model.status.Match;

import org.junit.Test;

public class AdrenalineCardTest {

	/*@Test
	public void testDoAction() {
		Player player = new Human("dummy");
		Card card = new AdrenalineCard();
		String mapName = "galilei";
		Socket socket = new Socket();
		Match match = new Match(mapName, player, socket);
		GameLogic controller = new GameLogic(match);
		card.doAction(player, controller);
		assertTrue(player.getCanMoveFaster());
		
	}*/

	@Test
	public void testAdrenalineCard() {
		Card card = new AdrenalineCard();
		assertNotNull(card);
	}

}
