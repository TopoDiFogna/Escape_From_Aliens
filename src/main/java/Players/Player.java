/*******************************************************************************
 * 2015, All rights reserved.
 *******************************************************************************/
package Players;

import Cards.Card;
import Map.Sector;
import java.util.HashSet;
// Start of user code (user defined imports)

// End of user code

/**
 * Description of Player.
 * 
 * @author Arianna
 */
public abstract class Player {
	/**
	 * Description of the property hasMoved.
	 */
	private boolean hasMoved = false;

	/**
	 * Description of the property activePlayer.
	 */
	protected boolean activePlayer = false;

	/**
	 * Description of the property cards.
	 */
	protected HashSet<Card> cards = new HashSet<Card>();

	/**
	 * Description of the property sectors.
	 */
	public Sector sectors = null;

	/**
	 * Description of the property playerControllers.
	 */
	public HashSet<PlayerController> playerControllers = new HashSet<PlayerController>();

	/**
	 * Description of the property alive.
	 */
	protected boolean alive = false;

	/**
	 * Description of the property canMoveFaster.
	 */
	protected Boolean canMoveFaster = Boolean.FALSE;

	// Start of user code (user defined attributes for Player)

	// End of user code

	/**
	 * The constructor.
	 */
	public Player() {
		// Start of user code constructor for Player)
		super();
		// End of user code
	}

	/**
	 * Description of the method isAlive.
	 * @return 
	 */
	public boolean isAlive() {
		// Start of user code for method isAlive
		boolean isAlive = false;
		return isAlive;
		// End of user code
	}

	/**
	 * Description of the method setAlive.
	 * @return 
	 */
	public boolean setAlive() {
		// Start of user code for method setAlive
		boolean setAlive = false;
		return setAlive;
		// End of user code
	}

	/**
	 * Description of the method getSector.
	 * @return 
	 */
	public Sector getSector() {
		// Start of user code for method getSector
		Sector getSector = null;
		return getSector;
		// End of user code
	}

	// Start of user code (user defined methods for Player)

	// End of user code
	/**
	 * Returns hasMoved.
	 * @return hasMoved 
	 */
	public boolean getHasMoved() {
		return this.hasMoved;
	}

	/**
	 * Sets a value to attribute hasMoved. 
	 * @param newHasMoved 
	 */
	public void setHasMoved(boolean newHasMoved) {
		this.hasMoved = newHasMoved;
	}

	/**
	 * Returns activePlayer.
	 * @return activePlayer 
	 */
	public boolean getActivePlayer() {
		return this.activePlayer;
	}

	/**
	 * Sets a value to attribute activePlayer. 
	 * @param newActivePlayer 
	 */
	public void setActivePlayer(boolean newActivePlayer) {
		this.activePlayer = newActivePlayer;
	}

	/**
	 * Returns cards.
	 * @return cards 
	 */
	public HashSet<Card> getCards() {
		return this.cards;
	}

	/**
	 * Returns sectors.
	 * @return sectors 
	 */
	public Sector getSectors() {
		return this.sectors;
	}

	/**
	 * Sets a value to attribute sectors. 
	 * @param newSectors 
	 */
	public void setSectors(Sector newSectors) {
		this.sectors = newSectors;
	}

	/**
	 * Returns playerControllers.
	 * @return playerControllers 
	 */
	public HashSet<PlayerController> getPlayerControllers() {
		return this.playerControllers;
	}

	/**
	 * Returns alive.
	 * @return alive 
	 */
	public boolean getAlive() {
		return this.alive;
	}

	/**
	 * Sets a value to attribute alive. 
	 * @param newAlive 
	 */
	public void setAlive(boolean newAlive) {
		this.alive = newAlive;
	}

	/**
	 * Returns canMoveFaster.
	 * @return canMoveFaster 
	 */
	public Boolean getCanMoveFaster() {
		return this.canMoveFaster;
	}

	/**
	 * Sets a value to attribute canMoveFaster. 
	 * @param newCanMoveFaster 
	 */
	public void setCanMoveFaster(Boolean newCanMoveFaster) {
		this.canMoveFaster = newCanMoveFaster;
	}

}
