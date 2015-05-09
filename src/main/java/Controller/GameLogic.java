/*******************************************************************************
 * 2015, All rights reserved.
 *******************************************************************************/
package Controller;

// Start of user code (user defined imports)

// End of user code

/**
 * Description of GameLogic.
 * 
 * @author Arianna
 */
public class GameLogic {
	/**
	 * Description of the property deckControllers.
	 */
	public HashSet<DeckController> deckControllers = new HashSet<DeckController>();

	/**
	 * Description of the property playerControllers.
	 */
	public HashSet<PlayerController> playerControllers = new HashSet<PlayerController>();

	// Start of user code (user defined attributes for GameLogic)

	// End of user code

	/**
	 * The constructor.
	 */
	public GameLogic() {
		// Start of user code constructor for GameLogic)
		super();
		// End of user code
	}

	// Start of user code (user defined methods for GameLogic)

	// End of user code
	/**
	 * Returns deckControllers.
	 * @return deckControllers 
	 */
	public HashSet<DeckController> getDeckControllers() {
		return this.deckControllers;
	}

	/**
	 * Returns playerControllers.
	 * @return playerControllers 
	 */
	public HashSet<PlayerController> getPlayerControllers() {
		return this.playerControllers;
	}

}
