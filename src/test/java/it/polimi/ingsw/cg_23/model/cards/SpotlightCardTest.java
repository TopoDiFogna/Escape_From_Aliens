package it.polimi.ingsw.cg_23.model.cards;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_23.controller.GameLogic;
import it.polimi.ingsw.cg_23.model.players.Human;
import it.polimi.ingsw.cg_23.model.players.Player;
import it.polimi.ingsw.cg_23.model.status.Match;

import org.junit.Test;

public class SpotlightCardTest {

	@Test
	public void testDoAction() {
		Player player1 = new Human("Dummy1");
		
		/*Player player2 = new Alien("Dummy2");
		Player player3 = new Alien("Dummy3");*/
		
		Card card = new SpotlightCard();
		Match match = new Match("galilei");
		GameLogic controller = new GameLogic(match);
		/*Sector sector1 = new Sector(17, 10, SectorTypeEnum.DANGEROUS, true);
		Sector sector2 = new Sector(18, 9, SectorTypeEnum.DANGEROUS, true);
		Sector sector3 = new Sector(16, 10, SectorTypeEnum.DANGEROUS, true);*/
		/*player1.setCurrentSector(sector1);
		player2.setCurrentSector(sector2);
		player3.setCurrentSector(sector3);*/
		
		card.doAction(player1, controller);
		
		//TODO è da ritestare meglio quando prenderò in input il settore dal giocatore
		//creo la mappa, metto dei giocatori in dei settori vicini e chiamo la carta
		//controllo che dopo aver chiamato la carta mi dica davvero che ci siano dei giocatori in quei settori
	}

	@Test
	public void testSpotlightCard() {
		Card card = new SpotlightCard();
		assertNotNull(card);
	}

}
