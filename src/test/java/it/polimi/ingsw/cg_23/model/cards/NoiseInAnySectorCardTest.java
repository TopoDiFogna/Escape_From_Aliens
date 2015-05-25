package it.polimi.ingsw.cg_23.model.cards;

import static org.junit.Assert.*;

import org.junit.Test;

public class NoiseInAnySectorCardTest {

	@Test
	public void testDoAction() {
		fail("Not yet implemented");
	}

	@Test
	public void testNoiseInAnySectorCard() {
		Card card = new NoiseInAnySectorCard(true);
		assertNotNull(card);
	}

	@Test
	public void testHasItem() {
		NoiseInAnySectorCard card = new NoiseInAnySectorCard(true);
		assertTrue(card.hasItem());
	}

}
