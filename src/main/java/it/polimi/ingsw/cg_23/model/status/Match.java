package it.polimi.ingsw.cg_23.model.status;

import java.util.ArrayList;

import it.polimi.ingsw.cg_23.model.cards.Deck;
import it.polimi.ingsw.cg_23.model.cards.EscapeHatchCard;
import it.polimi.ingsw.cg_23.model.cards.ItemCard;
import it.polimi.ingsw.cg_23.model.cards.SectorCard;
import it.polimi.ingsw.cg_23.model.map.Map;
import it.polimi.ingsw.cg_23.model.players.Player;


/**
 * Description of Match.
 * 
 * @author Paolo
 */
public class Match {
       
    /**
     * Description of the property map.
     */
    private final Map map;
    
    /**
     * The deck containing sector cards.
     */
    private Deck<SectorCard> sectorDeck;
    
    /**
     * The deck containing item cards.
     */
    private Deck<ItemCard> itemDeck;
    
    /**
     * The deck containing escape hatch card.
     */
    private Deck<EscapeHatchCard> escapeHatchDeck;
    
    
    /**
     * List of players in this match
     */
    private ArrayList<Player> players;

    /**
     * Description of the property turnNumber.
     */
    private int turnNumber;


    /**
     * The constructor.
     */
    public Match(Map map, Deck<SectorCard> sectorDeck, Deck<ItemCard> itemDeck, Deck<EscapeHatchCard> escapeHatchDeck, ArrayList<Player> players){
        
        this.map=map;
        this.sectorDeck=sectorDeck;
        this.itemDeck=itemDeck;
        this.escapeHatchDeck=escapeHatchDeck;
        this.players=players;
        turnNumber=0;
    }
    
    /**
     * Returns the map.
     * 
     * @return map 
     */
    public Map getMap() {
        return this.map;
    }

    /**
     * Returns the current state of sectorDeck.
     * 
     * @return decks
     */
    public Deck<SectorCard> getSectorDeck() {
        
       return this.sectorDeck;
    }
    
    /**
     * Returns the current state of escapeHatchDeck.
     * 
     * @return escapeHatchDeck
     */
    public Deck<EscapeHatchCard> getEscapeHatchDeck() {
        
        return this.escapeHatchDeck;
    }
    
    /**
     * Returns the current state of itemDeck.
     * 
     * @return itemDeck
     */
    public Deck<ItemCard> getItemDeck() {
        
        return this.itemDeck;
    }

    /**
     * Returns players.
     * 
     * @return players a list containing all players.
     */
    public ArrayList<Player> getPlayers() {
        
        return this.players;
    }

    /**
     * Returns the number of the turn.
     * 
     * @return turnNumber an int indicating the current turn number
     */
    public int getTurnNumber() {
        
        return this.turnNumber;
    }

    /**
     * Adds 1 to turnNumber and returns the value.
     */
    public void nextTurn() {
        turnNumber++;
    }
}
