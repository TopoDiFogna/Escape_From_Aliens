package it.polimi.ingsw.cg_23.model.cards;

import static org.junit.Assert.*;

import org.hamcrest.Matcher;
import org.hamcrest.core.IsNot;
import org.junit.Test;

public class DeckFactoryTest {

	@Test
	public void testCreateSectorDeck() {
		Deck<SectorCard> created = (Deck<SectorCard>) DeckFactory.createDeck(0);
		Deck<SectorCard> expected = new Deck<SectorCard>();
		expected.add(new SectorCard(SectorCardEnum.SILENCE, false));
		expected.add(new SectorCard(SectorCardEnum.SILENCE, false));
		expected.add(new SectorCard(SectorCardEnum.SILENCE, false));
		expected.add(new SectorCard(SectorCardEnum.SILENCE, false));
		expected.add(new SectorCard(SectorCardEnum.SILENCE, false));
		expected.add(new SectorCard(SectorCardEnum.NOISEINANYSECTOR, true));
		expected.add(new SectorCard(SectorCardEnum.NOISEINANYSECTOR, true));
		expected.add(new SectorCard(SectorCardEnum.NOISEINANYSECTOR, true));
		expected.add(new SectorCard(SectorCardEnum.NOISEINANYSECTOR, true));
		expected.add(new SectorCard(SectorCardEnum.NOISEINANYSECTOR, false));
		expected.add(new SectorCard(SectorCardEnum.NOISEINANYSECTOR, false));
		expected.add(new SectorCard(SectorCardEnum.NOISEINANYSECTOR, false));
		expected.add(new SectorCard(SectorCardEnum.NOISEINANYSECTOR, false));
		expected.add(new SectorCard(SectorCardEnum.NOISEINANYSECTOR, false));
		expected.add(new SectorCard(SectorCardEnum.NOISEINANYSECTOR, false));
		expected.add(new SectorCard(SectorCardEnum.NOISEINYOURSECTOR, true));
		expected.add(new SectorCard(SectorCardEnum.NOISEINYOURSECTOR, true));
		expected.add(new SectorCard(SectorCardEnum.NOISEINYOURSECTOR, true));
		expected.add(new SectorCard(SectorCardEnum.NOISEINYOURSECTOR, true));
		expected.add(new SectorCard(SectorCardEnum.NOISEINYOURSECTOR, false));
		expected.add(new SectorCard(SectorCardEnum.NOISEINYOURSECTOR, false));
		expected.add(new SectorCard(SectorCardEnum.NOISEINYOURSECTOR, false));
		expected.add(new SectorCard(SectorCardEnum.NOISEINYOURSECTOR, false));
		expected.add(new SectorCard(SectorCardEnum.NOISEINYOURSECTOR, false));
		expected.add(new SectorCard(SectorCardEnum.NOISEINYOURSECTOR, false));


	}

}
