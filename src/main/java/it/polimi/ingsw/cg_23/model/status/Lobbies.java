/*******************************************************************************
 * 2015, All rights reserved.
 *******************************************************************************/
package it.polimi.ingsw.cg_23.model.status;

import it.polimi.ingsw.cg_23.controller.Lobby;
import it.polimi.ingsw.cg_23.model.players.Player;

import java.util.HashSet;
// Start of user code (user defined imports)
import java.util.List;

// End of user code

/**
 * Description of Lobbies.
 * 
 * @author Arianna
 */
public class Lobbies {
	/**
	 * Description of the property mapName.
	 */
	private String mapName = "";
	
	/**
	 * Description of the property lobbyName.
	 */
	private String lobbyName = "";
	
	/**
	 * Description of the property playerList.
	 */
	private HashSet<Player> playerList = new HashSet<Player>();
	
	/**
	 * Description of the property lobbys.
	 */
	public Lobby lobbys = new Lobby();
	
	// Start of user code (user defined attributes for Lobbies)
	
	// End of user code
	
	/**
	 * The constructor.
	 */
	public Lobbies() {
		// Start of user code constructor for Lobbies)
		super();
		// End of user code
	}
	
	/**
	 * Description of the method setLobbyName.
	 * @param lobbyName 
	 */
	public void setLobbyName(String lobbyName) {
		// Start of user code for method setLobbyName
		// End of user code
	}
	 
	/**
	 * Description of the method getLobbyName.
	 * @return 
	 */
	public String getLobbyName() {
		// Start of user code for method getLobbyName
		String getLobbyName = null;
		return getLobbyName;
		// End of user code
	}
	 
	/**
	 * Description of the method setMapName.
	 * @param mapName 
	 */
	public void setMapName(String mapName) {
		// Start of user code for method setMapName
		// End of user code
	}
	 
	/**
	 * Description of the method getMapName.
	 * @return 
	 */
	public String getMapName() {
		// Start of user code for method getMapName
		String getMapName = null;
		return getMapName;
		// End of user code
	}
	 
	/**
	 * Description of the method setPlayerList.
	 * @param playerList 
	 */
	public void setPlayerList(List playerList) {
		// Start of user code for method setPlayerList
		// End of user code
	}
	 
	/**
	 * Description of the method getPlayerList.
	 * @param  
	 */
	public void getPlayerList(List playerList) {
		// Start of user code for method getPlayerList
		// End of user code
	}
	 
	// Start of user code (user defined methods for Lobbies)
	
	// End of user code
	
	/**
	 * Returns lobbys.
	 * @return lobbys 
	 */
	public Lobby getLobby() {
		return this.lobbys;
	}



}
