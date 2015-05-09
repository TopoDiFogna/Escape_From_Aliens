/*******************************************************************************
 * 2015, All rights reserved.
 *******************************************************************************/
package Status;

import Players.Player;
import java.util.HashSet;
// Start of user code (user defined imports)

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
	public HashSet<Lobby> lobbys = new HashSet<Lobby>();
	
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
	public void setLobbyName(EString lobbyName) {
		// Start of user code for method setLobbyName
		// End of user code
	}
	 
	/**
	 * Description of the method getLobbyName.
	 * @return 
	 */
	public EString getLobbyName() {
		// Start of user code for method getLobbyName
		EString getLobbyName = null;
		return getLobbyName;
		// End of user code
	}
	 
	/**
	 * Description of the method setMapName.
	 * @param mapName 
	 */
	public void setMapName(EString mapName) {
		// Start of user code for method setMapName
		// End of user code
	}
	 
	/**
	 * Description of the method getMapName.
	 * @return 
	 */
	public EString getMapName() {
		// Start of user code for method getMapName
		EString getMapName = null;
		return getMapName;
		// End of user code
	}
	 
	/**
	 * Description of the method setPlayerList.
	 * @param playerList 
	 */
	public void setPlayerList(EEList playerList) {
		// Start of user code for method setPlayerList
		// End of user code
	}
	 
	/**
	 * Description of the method getPlayerList.
	 * @param  
	 */
	public void getPlayerList(EEList ) {
		// Start of user code for method getPlayerList
		// End of user code
	}
	 
	// Start of user code (user defined methods for Lobbies)
	
	// End of user code
	/**
	 * Returns mapName.
	 * @return mapName 
	 */
	public String getMapName() {
		return this.mapName;
	}
	
	/**
	 * Sets a value to attribute mapName. 
	 * @param newMapName 
	 */
	public void setMapName(String newMapName) {
	    this.mapName = newMapName;
	}

	/**
	 * Returns lobbyName.
	 * @return lobbyName 
	 */
	public String getLobbyName() {
		return this.lobbyName;
	}
	
	/**
	 * Sets a value to attribute lobbyName. 
	 * @param newLobbyName 
	 */
	public void setLobbyName(String newLobbyName) {
	    this.lobbyName = newLobbyName;
	}

	/**
	 * Returns playerList.
	 * @return playerList 
	 */
	public HashSet<Player> getPlayerList() {
		return this.playerList;
	}

	/**
	 * Returns lobbys.
	 * @return lobbys 
	 */
	public HashSet<Lobby> getLobbys() {
		return this.lobbys;
	}



}
