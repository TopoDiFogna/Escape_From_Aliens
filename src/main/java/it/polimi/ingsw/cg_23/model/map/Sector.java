package it.polimi.ingsw.cg_23.model.map;

import java.util.ArrayList;

import it.polimi.ingsw.cg_23.model.map.Coordinate;
import it.polimi.ingsw.cg_23.model.players.Player;

/**
 * A sector is where a player is on the map when he's alive and playing.
 * 
 * @author Paolo
 */
public abstract class Sector extends Coordinate {
    
    /**
     * The players who are in the sector is saved here
     */
    private ArrayList<Player> player = null;
    
    /**
     * Tells us if the sector is crossable by the players.
     */
    protected boolean crossable;
    
    /**
     * The type of sector taken from SectorTypeEnum enumeration.
     */
    private final SectorTypeEnum type;

    /**
     * The constructor. Creates the sector specifying type and if it's crossable.
     */
    public Sector(int letter, int number, SectorTypeEnum type, boolean crossable) {
        super(letter, number);
        this.type=type;
        this.crossable=crossable;
    }

    /**
     * This method  gives the players who are in this sector as a list.
     * 
     * @return the players in the sector as a list
     */
    public ArrayList<Player> getPlayer() {
        return player;
    }

    /**
     * Sets the position of a player after he moves.
     * 
     * @param player the player who enters the sector
     */
    public void setPlayer(Player player) {
        this.player.add(player);
    }

    public SectorTypeEnum getType() {
        return type;
    }

    /**
     * Returns a boolean that is true if the sector is crossable by any alive player
     * 
     * @return a boolean. True if the sector is crossable by any alive player, false otherwise
     */
    public boolean isCrossable() {
        return this.crossable;
    }

    /**
     * This method set an escape hatch sector as not crossable if used.<br>
     * Not used for other sectors becase they are always crossable or not crossable since the map is create
     * 
     */
    public void setEscapeHatchSectorNotCrossable() {
        this.crossable=false;
    }
}
