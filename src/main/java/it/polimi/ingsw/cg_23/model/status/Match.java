package it.polimi.ingsw.cg_23.model.status;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import it.polimi.ingsw.cg_23.controller.MapInitializer;
import it.polimi.ingsw.cg_23.model.cards.Deck;
import it.polimi.ingsw.cg_23.model.cards.DeckFactory;
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
    private Deck<EscapeHatchCard> escapeHatchDeckDiscarded = (Deck<EscapeHatchCard>) DeckFactory.createDeck(3);
    
    /**
     * The deck containing sector cards.
     */
    private Deck<SectorCard> sectorDeckDiscarded = (Deck<SectorCard>) new DeckFactory().createDeck(3);
    
    /**
     * The deck containing item cards.
     */
    private Deck<ItemCard> itemDeckDiscarded = (Deck<ItemCard>) new DeckFactory().createDeck(3);
    
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

    private HashMap<Player, Socket> socketMap = new HashMap<Player, Socket>();
    
    private GameState matchState;
    
    private String name;
    

    /**
     * The constructor.
     */
    public Match(String mapName, Player player, Socket socket){//TODO assign players to right sector
        
        this.name=mapName;
        this.map=new Map(mapName);
        this.sectorDeck=(Deck<SectorCard>) DeckFactory.createDeck(0);
        this.itemDeck=(Deck<ItemCard>) DeckFactory.createDeck(1);
        this.escapeHatchDeck=(Deck<EscapeHatchCard>) DeckFactory.createDeck(2);
        this.players=players;
        Collections.shuffle(sectorDeck);
        Collections.shuffle(itemDeck);
        Collections.shuffle(escapeHatchDeck);
        addPlayer(player, socket);
        matchState = GameState.WAITING;
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

    public Deck<EscapeHatchCard> getEscapeHatchDeckDiscarded() {
        return escapeHatchDeckDiscarded;
    }

    public Deck<SectorCard> getSectorDeckDiscarded() {
        return sectorDeckDiscarded;
    }

    public Deck<ItemCard> getItemDeckDiscarded() {
        return itemDeckDiscarded;
    }

    public void setSectorDeck(Deck<SectorCard> sectorDeck) {
        this.sectorDeck = sectorDeck;
    }

    public void setItemDeck(Deck<ItemCard> itemDeck) {
        this.itemDeck = itemDeck;
    }

    public void setEscapeHatchDeckDiscarded(
            Deck<EscapeHatchCard> escapeHatchDeckDiscarded) {
        this.escapeHatchDeckDiscarded = escapeHatchDeckDiscarded;
    }

    public void setSectorDeckDiscarded(Deck<SectorCard> sectorDeckDiscarded) {
        this.sectorDeckDiscarded = sectorDeckDiscarded;
    }

    public void setItemDeckDiscarded(Deck<ItemCard> itemDeckDiscarded) {
        this.itemDeckDiscarded = itemDeckDiscarded;
    }

    public void setEscapeHatchDeck(Deck<EscapeHatchCard> escapeHatchDeck) {
        this.escapeHatchDeck = escapeHatchDeck;
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
    
    public void addPlayer(Player player, Socket socket){
        socketMap.put(player, socket);
    }
}
