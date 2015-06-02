package it.polimi.ingsw.cg_23.model.status;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_23.controller.GameLogic;
import it.polimi.ingsw.cg_23.model.cards.Card;
import it.polimi.ingsw.cg_23.model.cards.Deck;
import it.polimi.ingsw.cg_23.model.cards.DeckFactory;

import org.junit.Test;

public class MatchTest {

   @Test
    public void testGetGameLogic() {
       Match match = new Match("galilei");
       GameLogic controller = new GameLogic(match);
       match.getGameLogic();
       assertNotNull(controller);
    }

    @Test
    public void testSetEscapeHatchDeckDiscarded() {
        Match match = new Match("galilei");
        Deck<Card> deck = DeckFactory.createDeck(3);
        match.setEscapeHatchDeckDiscarded(deck);
        assertNotNull(deck);
    }

    @Test
    public void testSetEscapeHatchDeck() {
        Match match = new Match("galilei");
        Deck<Card> deck = DeckFactory.createDeck(2);
        match.setEscapeHatchDeck(deck);
        assertNotNull(deck);
    }

    @Test
    public void testGetName() {
        Match match = new Match("galilei");
        String name = match.getName();
        assertEquals(name, "galilei");
    }

}
