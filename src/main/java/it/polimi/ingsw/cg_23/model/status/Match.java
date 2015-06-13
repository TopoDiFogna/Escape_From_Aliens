package it.polimi.ingsw.cg_23.model.status;

import it.polimi.ingsw.cg_23.controller.GameLogic;
import it.polimi.ingsw.cg_23.model.cards.Card;
import it.polimi.ingsw.cg_23.model.cards.Deck;
import it.polimi.ingsw.cg_23.model.cards.DeckFactory;
import it.polimi.ingsw.cg_23.model.map.Map;
import it.polimi.ingsw.cg_23.model.players.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

/**
 * The Match is the core of every game. In this class are tracked all the status of a single match.
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
    
    /**
     * Here is saved the satus of the match tanke from GameState enumeration.    
     */
    private GameState matchState;
    
    /**
     * Name of the map played in the match
     */
    private final String name;
    
    /**
     * Gamelogic associated to the match
     */
    private final GameLogic gameLogic;

    /**
     * The player who is currently playing
     */
    private Player currentPlayer;
    
    /**
     * Number of sectors Escape Hatch still usable
     */
    private int nUsableEscapeHatch = 4;
    

    /**
     * The constructor. Sets all the needed variables to start the game.
     */
    public Match(String mapName){
        
        this.name=mapName;
        this.map=new Map(mapName);
        this.gameLogic= new GameLogic(this);
        this.players=new ArrayList<Player>();
        this.sectorDeck=DeckFactory.createDeck(0);
        this.itemDeck=DeckFactory.createDeck(1);
        this.escapeHatchDeck=DeckFactory.createDeck(2);
        Collections.shuffle(sectorDeck);
        Collections.shuffle(itemDeck);
        Collections.shuffle(escapeHatchDeck);
        matchState=GameState.WAITING;
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
     * Returns the game logic binded to this match.
     * 
     * @return the game logic binded to this match
     */
    public GameLogic getGameLogic() {
        return gameLogic;
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

    /**
     * Returns the current state of Escape Hatch discarded card deck.
     * 
     * @return the escape hatch deck
     */
    public Deck<Card> getEscapeHatchDeckDiscarded() {
        return escapeHatchDeckDiscarded;
    }

    /**
     * Returns the current state of Sector discarded card deck.
     * 
     * @return the discarded sector card deck
     */
    public Deck<Card> getSectorDeckDiscarded() {
        return sectorDeckDiscarded;
    }

    /**
     * Returns the current state of item discarded card deck.
     * 
     * @return the discarded item card deck
     */
    public Deck<Card> getItemDeckDiscarded() {
        return itemDeckDiscarded;
    }

    /**
     * Sets a deck where add sector deck cards
     * 
     * @param sectorDeck a deck for sector cards
     */
    public void setSectorDeck(Deck<Card> sectorDeck) {
        this.sectorDeck = sectorDeck;
    }

    /**
     * Sets a deck where add discarded item deck cards
     * 
     * @param sectorDeck a deck for item cards
     */
    public void setItemDeck(Deck<Card> itemDeck) {
        this.itemDeck = itemDeck;
    }

    /**
     * Sets a deck where add discarded escape hatch deck cards
     * 
     * @param sectorDeck an empty deck for escape hatch discarded cards
     */
    public void setEscapeHatchDeckDiscarded(Deck<Card> escapeHatchDeckDiscarded) {
        this.escapeHatchDeckDiscarded = escapeHatchDeckDiscarded;
    }

    /**
     * Sets a deck where add discarded sector deck cards
     * 
     * @param sectorDeck an empty deck for sector discarded cards
     */
    public void setSectorDeckDiscarded(Deck<Card> sectorDeckDiscarded) {
        this.sectorDeckDiscarded = sectorDeckDiscarded;
    }

    /**
     * Sets a deck where add discarded item deck cards
     * 
     * @param sectorDeck an empty deck for item discarded cards
     */
    public void setItemDeckDiscarded(Deck<Card> itemDeckDiscarded) {
        this.itemDeckDiscarded = itemDeckDiscarded;
    }

    /**
     * Sets a deck where add discarded escape hatch deck cards
     * 
     * @param sectorDeck a deck for escape hatch cards
     */
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
    
    /**
     * Adds a player to the list of player in the match.
     * @param player the player to be added
     */
    public void addNewPlayerToList(Player player) {//TODO da controllare se serve davvero (in caso aggiungere all'uml)
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
     * Adds 1 to turnNumber.
     */
    public void nextTurn() {
        turnNumber++;
    }

    /** 
     * @return name of the current map
     */
    public String getName() {
        return name;
    }

    /**
     * @return the enum defining the state of the game
     */
    public GameState getMatchState() {
        return matchState;
    }

    /**
     * Sets the state of the match.
     * 
     * @param matchState
     */
    public void setMatchState(GameState matchState) {
        this.matchState = matchState;
    }

    /**
     * @return the current player playing the turn
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Sets who is the player playing the turn.
     * @param currentPlayer
     */
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * @return the number of usable escape hatch 
     */
    public int getnUsableEscapeHatch() {
        return nUsableEscapeHatch;
    }

    /**
     * Decreases the number of usable escape hatch, after using it.
     */
    public void removeEcapeHatch() {
        this.nUsableEscapeHatch--;
    }
}
