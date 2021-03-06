package it.polimi.ingsw.cg_23.model.players;

import it.polimi.ingsw.cg_23.model.cards.Card;
import it.polimi.ingsw.cg_23.model.map.Sector;

import java.util.ArrayList;
import java.util.List;


/**
 * The generic entity who plays the game
 * 
 * @author Paolo
 */

public abstract class Player {
    /**
     * It's true if player has fours cards in his hand, false if not.
     */
    protected boolean hasFourCard = false;
   
    /**
     * 
     */
    protected boolean needSectorNoise = false;

    /**
     * Description of the property hasMoved.
     * maybe not necessary, no purpose
     */
    protected boolean hasMoved = false;

    /**
     *  Tells if the player can move 1 sector faster,
     *  in case of human it becomes true for the turn when the players uses the card adrenaline,
     *  in case of alien become definitely true after the player has killed a human  
     */
    protected boolean canMoveFaster = false;
    
    /**
     *  True if the player is still alive and in-game 
     */
    protected boolean status = true;
    
    /**
     * List of cards a player has in his hand
     */
    protected List<Card> cards = new ArrayList<Card>();
    
    /**
     * The sector where the player currently is.
     */
    protected Sector currentSector;
    
    /**
     * Unique id for every player.
     */
    protected final int playerId;
    
    /**
     * Used to count players; incremental unique number.
     */
    private static int counter=0;
    
    /**
     * Nickname chosen by the player
     */
    protected final String name;

    /**
     * The constructor.
     */
    public Player(String name) {
        this.playerId=counter;
        counter++;
        this.name=name;
    }

    /**
     * Numbers of player
     * 
     * @return an int which count how many players have been created
     */
    public static int getCounter() {
        return counter;
    }

    /**
     * Sets the status of the player as dead after he's killed. alive can be changed only to false. No player can be revived
     * 
     */
    public void setDead() {
        this.status = false;
    }
    
    /**
     * Tells if the player is alive or dead. 
     * By default it's true, becomes false if a player is killed either bay human or alien.
     * 
     * @return the status of the player, either dead or alive
     */
    public boolean isAlive() {
        return this.status;
    }

    /**
     * Returns the sector where the player currently is.
     * Initially it is the home sector of the faction the it depends of what moves has done the player.
     * It can never be a Void Sector.
     * 
     * @return the sector where the player currently is.
     */
    public Sector getCurrentSector() {
        return this.currentSector;
    }
        
    /**
     * Returns the cards the player has in his hand.
     * 
     * @return A list containing the cards the player has in his hand
     */
    public List<Card> getCards() {
        return cards;
    }
    
    /**
     * Sets a new value to attribute sector.
     * It's the destination after a move.
     * 
     * @param sector the destination after a move
     */
    public void setCurrentSector(Sector sector) {
        this.currentSector = sector;
    }

    /**
     *  Tells if the player can move 1 sector faster<br>
     *  in case of human it becomes true for the turn when the players uses the card adrenaline<br>
     *  in case of alien become definetly true after the player has killed a human
     *  
     * @return a boolean: true if the player can move vaster, false otherwise.
     */
    public boolean getCanMoveFaster() {
        return this.canMoveFaster;
    }

    /**
     *  Tells if the player can move 1 sector faster<br>
     *  in case of human it becomes true for the turn when the players uses the card adrenaline<br>
     *  in case of alien become definetly true after the player has killed a human<br>
     *  
     * @param canMoveFaster sets the value for moving faster
     */
    public void setCanMoveFaster(boolean canMoveFaster) {
        this.canMoveFaster = canMoveFaster;
    }
    
    /**
     * The id of the player. It's unique.
     * 
     * @return the unique player id
     */
    public int getPlayerId() {
        return playerId;
    }

    /**
     * Name chosen when joining a game.
     * 
     * @return the player name
     */
    public String getName() {
        return name;
    }

    
    /**
     * It's a flag to indicate if player already moves in his turn.
     * 
     * @return true if player already moves, false if not
     */
    public boolean hasMoved() {
        return hasMoved;
    }

    /**
     * Sets true if player already moves, false if not.
     * 
     * @param hasMoved
     */
    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
    
    /**
     * It's a flag to indicate if player has four cards in his hand.
     * 
     * @return true if player has four cards, false if not
     */
    public boolean hasFourCard() {
        return hasFourCard;
    }

    
    /**
     * Sets a flag to indicate if player already has four cards and he needs to chose what to do.
     * 
     * @param hasFourCard
     */
    public void setHasFourCard(boolean hasFourCard) {
        this.hasFourCard = hasFourCard;
    }

    /**
     * It's a flag to indicate if player has to communicate a sector for noise.
     * 
     * @return true if player has to communicate a sector, false if not
     */
    public boolean needSectorNoise() {
        return needSectorNoise;
    }

    /**
     * Sets a flag to indicate if player has to communicate a sector where make noise.
     * 
     * @param needSectorNoise
     */
    public void setNeedSectorNoise(boolean needSectorNoise) {
        this.needSectorNoise = needSectorNoise;
    }    
     
}