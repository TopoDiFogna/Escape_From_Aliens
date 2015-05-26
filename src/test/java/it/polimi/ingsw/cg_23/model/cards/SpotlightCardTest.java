package it.polimi.ingsw.cg_23.model.cards;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_23.controller.GameLogic;
import it.polimi.ingsw.cg_23.model.map.Sector;
import it.polimi.ingsw.cg_23.model.map.SectorTypeEnum;
import it.polimi.ingsw.cg_23.model.players.Human;
import it.polimi.ingsw.cg_23.model.players.Player;
import it.polimi.ingsw.cg_23.model.status.Match;

import org.junit.Test;

public class SpotlightCardTest {

	@Test
	public void testDoAction() {
		Player player = new Human("Dummy");
		Card card = new SpotlightCard();
		Match match = new Match("galilei");
		GameLogic controller = new GameLogic(match);
		Sector sector = new Sector(17, 10, SectorTypeEnum.DANGEROUS, false);
		card.doAction(player, controller);
		//creo la mappa, metto dei giocatori in dei settori vicini e chiamo la carta
		//controllo che dopo aver chiamato la carta mi dica davvero che ci siano dei giocatori in quei settori
	}

	@Test
	public void testSpotlightCard() {
		Card card = new SpotlightCard();
		assertNotNull(card);
	}

}
