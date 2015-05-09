/*******************************************************************************
 * 2015, All rights reserved.
 *******************************************************************************/
package Status;

import Cards.Deck;
import Map.Map;
// Start of user code (user defined imports)

// End of user code

/**
 * Description of Match.
 * 
 * @author Arianna
 */
public class Match {
	/**
	 * Description of the property decks.
	 */
	public HashSet<Deck> decks = new HashSet<Deck>();

	/**
	 * Description of the property maps.
	 */
	public HashSet<Map> maps = new HashSet<Map>();

	/**
	 * Description of the property sectorDeck.
	 */
	private Deck sectorDeck = null;

	/**
	 * Description of the property gameInitializers.
	 */
	public HashSet<GameInitializer> gameInitializers = new HashSet<GameInitializer>();

	/**
	 * Description of the property players.
	 */
	public HashSet<Player> players = new HashSet<Player>();

	/**
	 * Description of the property escapeHatchDeck.
	 */
	private Deck escapeHatchDeck = null;

	/**
	 * Description of the property turnNumber.
	 */
	private int turnNumber = 0;

	/**
	 * Description of the property map.
	 */
	private Map map = null;

	/**
	 * Description of the property itemDeck.
	 */
	private Deck itemDeck = null;

	// Start of user code (user defined attributes for Match)

	// End of user code

	/**
	 * The constructor.
	 */
	public Match() {
		// Start of user code constructor for Match)
		super();
		// End of user code
	}

	// Start of user code (user defined methods for Match)

	// End of user code
	/**
	 * Returns decks.
	 * @return decks 
	 */
	public HashSet<Deck> getDecks() {
		return this.decks;
	}

	/**
	 * Returns maps.
	 * @return maps 
	 */
	public HashSet<Map> getMaps() {
		return this.maps;
	}

	/**
	 * Returns sectorDeck.
	 * @return sectorDeck 
	 */
	public Deck getSectorDeck() {
		return this.sectorDeck;
	}

	/**
	 * Sets a value to attribute sectorDeck. 
	 * @param newSectorDeck 
	 */
	public void setSectorDeck(Deck newSectorDeck) {
		this.sectorDeck = newSectorDeck;
	}

	/**
	 * Returns gameInitializers.
	 * @return gameInitializers 
	 */
	public HashSet<GameInitializer> getGameInitializers() {
		return this.gameInitializers;
	}

	/**
	 * Returns players.
	 * @return players 
	 */
	public HashSet<Player> getPlayers() {
		return this.players;
	}

	/**
	 * Returns escapeHatchDeck.
	 * @return escapeHatchDeck 
	 */
	public Deck getEscapeHatchDeck() {
		return this.escapeHatchDeck;
	}

	/**
	 * Sets a value to attribute escapeHatchDeck. 
	 * @param newEscapeHatchDeck 
	 */
	public void setEscapeHatchDeck(Deck newEscapeHatchDeck) {
		this.escapeHatchDeck = newEscapeHatchDeck;
	}

	/**
	 * Returns turnNumber.
	 * @return turnNumber 
	 */
	public int getTurnNumber() {
		return this.turnNumber;
	}

	/**
	 * Sets a value to attribute turnNumber. 
	 * @param newTurnNumber 
	 */
	public void setTurnNumber(int newTurnNumber) {
		this.turnNumber = newTurnNumber;
	}

	/**
	 * Returns map.
	 * @return map 
	 */
	public Map getMap() {
		return this.map;
	}

	/**
	 * Sets a value to attribute map. 
	 * @param newMap 
	 */
	public void setMap(Map newMap) {
		this.map = newMap;
	}

	/**
	 * Returns itemDeck.
	 * @return itemDeck 
	 */
	public Deck getItemDeck() {
		return this.itemDeck;
	}

	/**
	 * Sets a value to attribute itemDeck. 
	 * @param newItemDeck 
	 */
	public void setItemDeck(Deck newItemDeck) {
		this.itemDeck = newItemDeck;
	}

}
