package it.polimi.ingsw.cg_23.model.cards;

import static org.junit.Assert.*;

import org.junit.Test;

public class DeckFactoryTest {
	Deck<Card> expected = null;

	@Test
	public void testCreateDeck() {
		new DeckFactory();
		Deck<Card> actual = DeckFactory.createDeck(0);
		Deck<Card> expected ;
		assertEquals(expected.lastIndexOf(expected), actual.lastIndexOf(actual));
		for(int i=0; i <= 4; i++){
			assertEquals(expected, actual);
		}
	}
	
	private boolean comparison(){
		
	}
	/*
	private Deck<Card> createSectorDeckTest(){
        expected.add(new SilenceCard(false));
        expected.add(new SilenceCard(false));
        expected.add(new SilenceCard(false));
        expected.add(new SilenceCard(false));
        expected.add(new SilenceCard(false));
        expected.add(new NoiseInAnySectorCard(true));
        expected.add(new NoiseInAnySectorCard(true));
        expected.add(new NoiseInAnySectorCard(true));
        expected.add(new NoiseInAnySectorCard(true));
        expected.add(new NoiseInAnySectorCard(false));
        expected.add(new NoiseInAnySectorCard(false)); 
        expected.add(new NoiseInAnySectorCard(false));
        expected.add(new NoiseInAnySectorCard(false));
        expected.add(new NoiseInAnySectorCard(false));
        expected.add(new NoiseInAnySectorCard(false));
        expected.add(new NoiseInYourSectorCard(true));
        expected.add(new NoiseInYourSectorCard(true));
        expected.add(new NoiseInYourSectorCard(true));
        expected.add(new NoiseInYourSectorCard(true));
        expected.add(new NoiseInYourSectorCard(false));
        expected.add(new NoiseInYourSectorCard(false));
        expected.add(new NoiseInYourSectorCard(false));
        expected.add(new NoiseInYourSectorCard(false));
        expected.add(new NoiseInYourSectorCard(false));
        expected.add(new NoiseInYourSectorCard(false));		
		return expected;
	}
*/	
}
