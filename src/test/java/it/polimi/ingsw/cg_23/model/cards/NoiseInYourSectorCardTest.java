package it.polimi.ingsw.cg_23.model.cards;

import static org.junit.Assert.*;

import org.junit.Test;

public class NoiseInYourSectorCardTest {

	@Test
	public void testDoAction() {
		fail("Not yet implemented");
	}

	@Test
	public void testNoiseInYourSectorCard() {
		Card card = new NoiseInYourSectorCard(true);
		assertNotNull(card);
	}

	@Test
	public void testHasItem() {
		NoiseInYourSectorCard card = new NoiseInYourSectorCard(false);
		assertFalse(card.hasItem());
	}

}
