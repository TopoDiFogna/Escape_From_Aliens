/*******************************************************************************
 * 2015, All rights reserved.
 *******************************************************************************/
package Map;

import Map.Coordinate;
import Players.Player;
import java.util.HashSet;
// Start of user code (user defined imports)

// End of user code

/**
 * Description of Sector.
 * 
 * @author Arianna
 */
public abstract class Sector extends Coordinate {
	/**
	 * Description of the property sectors.
	 */
	public HashSet<Sector> sectors = new HashSet<Sector>();

	/**
	 * Description of the property player.
	 */
	private HashSet<Player> player = new HashSet<Player>();

	/**
	 * Description of the property crossable.
	 */
	protected Boolean crossable = Boolean.FALSE;

	/**
	 * Description of the property sectors.
	 */
	public HashSet<Sector> sectors = new HashSet<Sector>();

	/**
	 * Description of the property players.
	 */
	public HashSet<Player> players = new HashSet<Player>();

	// Start of user code (user defined attributes for Sector)

	// End of user code

	/**
	 * The constructor.
	 */
	public Sector() {
		// Start of user code constructor for Sector)
		super();
		// End of user code
	}

	/**
	 * Description of the method getPlayer.
	 * @return 
	 */
	public Player getPlayer() {
		// Start of user code for method getPlayer
		Player getPlayer = null;
		return getPlayer;
		// End of user code
	}

	/**
	 * Description of the method setPlayer.
	 * @param player 
	 */
	public void setPlayer(Player player) {
		// Start of user code for method setPlayer
		// End of user code
	}

	/**
	 * Description of the method isCrossable.
	 * @return 
	 */
	public Boolean isCrossable() {
		// Start of user code for method isCrossable
		Boolean isCrossable = Boolean.FALSE;
		return isCrossable;
		// End of user code
	}

	/**
	 * Description of the method setCrossable.
	 * @param value 
	 */
	public void setCrossable(boolean value) {
		// Start of user code for method setCrossable
		// End of user code
	}

	// Start of user code (user defined methods for Sector)

	// End of user code
	/**
	 * Returns sectors.
	 * @return sectors 
	 */
	public HashSet<Sector> getSectors() {
		return this.sectors;
	}

	/**
	 * Returns player.
	 * @return player 
	 */
	public HashSet<Player> getPlayer() {
		return this.player;
	}

	/**
	 * Returns crossable.
	 * @return crossable 
	 */
	public Boolean getCrossable() {
		return this.crossable;
	}

	/**
	 * Sets a value to attribute crossable. 
	 * @param newCrossable 
	 */
	public void setCrossable(Boolean newCrossable) {
		this.crossable = newCrossable;
	}

	/**
	 * Returns sectors.
	 * @return sectors 
	 */
	public HashSet<Sector> getSectors() {
		return this.sectors;
	}

	/**
	 * Returns players.
	 * @return players 
	 */
	public HashSet<Player> getPlayers() {
		return this.players;
	}

}
