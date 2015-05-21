package it.polimi.ingsw.cg_23.model.players;

import it.polimi.ingsw.cg_23.model.cards.Card;
import it.polimi.ingsw.cg_23.model.cards.Deck;
import it.polimi.ingsw.cg_23.model.map.Sector;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * The generic entity who plays the game
 * 
 * @author Paolo
 */

public abstract class Player {
    /**
     * Description of the property hasMoved.
     * maybe not necessary, no purpose
     */
    protected boolean hasMoved = false;

    /**
     *  Tells if the player can move 1 sector faster,
     *  in case of human it becomes true for the turn when the players uses the card adrenaline,
     *  in case of alien become definetly true after the player has killed a human  
     */
    protected boolean canMoveFaster = false;
    
    /**
     *  True if the player is still alive and in-game 
     */
    protected boolean status = true;
    
    /**
     * List of cards a player has in his hand
     */
    protected ArrayList<Card> cards = new ArrayList<Card>();
    
    /**
     * The sector where the player currently is.
     */
    protected Sector currentSector = null;
    
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
     * Tells if the player is alive or dead. 
     * By default it's true, becomes false if a player is killed either bay human or alien.
     * 
     * @return the status of the player, either dead or alive
     */
    public boolean isAlive() {
        return this.status;
    }

    /**
     * Sets the status of the player as dead after he's killed. alive can be changed only to false. No player can be revived
     * 
     */
    public void setDead() {
        this.status = false;
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
    public ArrayList<Card> getCards() {
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
     * Name choosen when joining a game.
     * 
     * @return the player name
     */
    public String getName() {
        return name;
    }
    
    
    /**
     * Fisrt adds the card to the player hand, then removes the card from the corrisponding deck.
     * 
     * @param deck from where the card is drawn
     */
    public void drawCard(Deck<Card> deck){
        Iterator<Card> iterator  = deck.iterator();
        cards.add(iterator.next());
        iterator.remove();
    }
     /**
      * Allow the player to discard card
      * 
      * @param card the card to be discarded
      * @return the card the player has discarded to be added to the discardDeck
      */
    public Card discardCard(Card card){
        cards.remove(card);
        return card;
    }
    
    /**
     * Uses the card selected by player and removes it from the player hand
     * 
     * @param card card to be used
     */
    public void useCard(Card card){
        //card.action();
        discardCard(card);
    }
}
