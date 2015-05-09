/*******************************************************************************
 * 2015, All rights reserved.
 *******************************************************************************/
package Controller;

import Cards.Card;
import Cards.Deck;
import Map.Sector;
import Players.Player;
// Start of user code (user defined imports)

// End of user code

/**
 * Description of PlayerController.
 * 
 * @author Arianna
 */
public class PlayerController {
	/**
	 * Description of the property players.
	 */
	public HashSet<Player> players = new HashSet<Player>();

	// Start of user code (user defined attributes for PlayerController)

	// End of user code

	/**
	 * The constructor.
	 */
	public PlayerController() {
		// Start of user code constructor for PlayerController)
		super();
		// End of user code
	}

	/**
	 * Description of the method move.
	 * @param player 
	 * @param destination 
	 */
	public void move(Player player, Sector destination) {
		// Start of user code for method move
		// End of user code
	}

	/**
	 * Description of the method useCard.
	 * @param player 
	 * @param card 
	 */
	public void useCard(Player player, Card card) {
		// Start of user code for method useCard
		// End of user code
	}

	/**
	 * Description of the method pickCard.
	 * @param player 
	 * @param deck 
	 */
	public void pickCard(Player player, Deck deck) {
		// Start of user code for method pickCard
		// End of user code
	}

	/**
	 * Description of the method discardCard.
	 * @param player 
	 */
	public void discardCard(Player player) {
		// Start of user code for method discardCard
		// End of user code
	}

	/**
	 * Description of the method attack.
	 * @param player 
	 */
	public void attack(Player player) {
		// Start of user code for method attack
		// End of user code
	}

	/**
	 * Description of the method validMove.
	 * @param player 
	 */
	private void validMove(Player player) {
		// Start of user code for method validMove
		// End of user code
	}

	// Start of user code (user defined methods for PlayerController)

	// End of user code
	/**
	 * Returns players.
	 * @return players 
	 */
	public HashSet<Player> getPlayers() {
		return this.players;
	}

}
