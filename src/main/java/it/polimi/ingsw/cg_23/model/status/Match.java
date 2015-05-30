package it.polimi.ingsw.cg_23.model.status;

import it.polimi.ingsw.cg_23.controller.GameLogic;
import it.polimi.ingsw.cg_23.model.cards.Card;
import it.polimi.ingsw.cg_23.model.cards.Deck;
import it.polimi.ingsw.cg_23.model.cards.DeckFactory;
import it.polimi.ingsw.cg_23.model.map.Map;
import it.polimi.ingsw.cg_23.model.players.Player;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

/**
 * Description of Match.
 * 
 * @author Paolo
 */
public class Match extends Observable{
       
    /**
     * Description of the property map.
     */
    private final Map map;
    
    /**
     * The deck containing sector cards.
     */
    private Deck<Card> sectorDeck;
    
    /**
     * The deck containing item cards.
     */
    private Deck<Card> itemDeck;
    
    /**
     * The deck containing escape hatch card.
     */

    private Deck<Card> escapeHatchDeck;
    
    /**
     * The deck containing discarded sector cards.
     */
    private Deck<Card> sectorDeckDiscarded = DeckFactory.createDeck(3);
    
    /**
     * The deck containing discarded item cards.
     */
    private Deck<Card> itemDeckDiscarded = DeckFactory.createDeck(3);
    
    /**
     * The deck containing discarded item cards.
     */    
    private Deck<Card> escapeHatchDeckDiscarded = DeckFactory.createDeck(3);
    
    /**
     * List of players in this match
     */
    private List<Player> players;

    /**
     * Description of the property turnNumber.
     */
    private int turnNumber;
    
    private GameState matchState;
    
    private final String name;
    


    

    /**
     * The constructor.
     */

    public Match(String mapName){//TODO assign players to right sector
        
        this.name=mapName;
        this.map=new Map(mapName);
        this.players=new ArrayList<Player>();
        this.sectorDeck=DeckFactory.createDeck(0);
        this.itemDeck=DeckFactory.createDeck(1);
        this.escapeHatchDeck=DeckFactory.createDeck(2);
        Collections.shuffle(sectorDeck);
        Collections.shuffle(itemDeck);
        Collections.shuffle(escapeHatchDeck);
        setMatchState(GameState.WAITING);
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
    public Deck<Card> getSectorDeck() {
        
       return this.sectorDeck;
    }
    
    /**
     * Returns the current state of escapeHatchDeck.
     * 
     * @return escapeHatchDeck
     */
    public Deck<Card> getEscapeHatchDeck() {
        
        return this.escapeHatchDeck;
    }
    
    /**
     * Returns the current state of itemDeck.
     * 
     * @return itemDeck
     */
    public Deck<Card> getItemDeck() {
        
        return this.itemDeck;
    }

    public Deck<Card> getEscapeHatchDeckDiscarded() {
        return escapeHatchDeckDiscarded;
    }

    public Deck<Card> getSectorDeckDiscarded() {
        return sectorDeckDiscarded;
    }

    public Deck<Card> getItemDeckDiscarded() {
        return itemDeckDiscarded;
    }

    public void setSectorDeck(Deck<Card> sectorDeck) {
        this.sectorDeck = sectorDeck;
    }

    public void setItemDeck(Deck<Card> itemDeck) {
        this.itemDeck = itemDeck;
    }

    public void setEscapeHatchDeckDiscarded(Deck<Card> escapeHatchDeckDiscarded) {
        this.escapeHatchDeckDiscarded = escapeHatchDeckDiscarded;
    }

    
    public void setSectorDeckDiscarded(Deck<Card> sectorDeckDiscarded) {
        this.sectorDeckDiscarded = sectorDeckDiscarded;
    }

    public void setItemDeckDiscarded(Deck<Card> itemDeckDiscarded) {
        this.itemDeckDiscarded = itemDeckDiscarded;
    }

    public void setEscapeHatchDeck(Deck<Card> escapeHatchDeck) {
        this.escapeHatchDeck = escapeHatchDeck;
    }

    /**
     * Returns players.
     * 
     * @return players a list containing all players.
     */
    public List<Player> getPlayers() {
        
        return this.players;
    }

    public void addNewPlayerToList(Player player) {
        this.players.add(player);
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

    public String getName() {
        return name;
    }

    public GameState getMatchState() {
        return matchState;
    }

    public void setMatchState(GameState matchState) {
        this.matchState = matchState;
    }
}
