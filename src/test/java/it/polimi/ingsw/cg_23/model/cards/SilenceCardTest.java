package it.polimi.ingsw.cg_23.model.cards;

import static org.junit.Assert.*;

import org.junit.Test;

public class SilenceCardTest {

	@Test
	public void testDoAction() {
		fail("Not yet implemented");
	}

	@Test
	public void testSilenceCard() {
		Card card = new SilenceCard(false);
		assertNotNull(card);
	}

	@Test
	public void testHasItem() {
		SilenceCard card = new SilenceCard(false);
		assertFalse(card.HasItem());
	}

}
