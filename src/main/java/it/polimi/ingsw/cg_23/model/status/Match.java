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
    
    private final GameLogic gameLogic;

    private Player currentPlayer;
    
    private int nUsableEscapeHatch = 4;
    

    /**
     * The constructor.
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

    //TODO da controllare se serve davvero (in caso aggiungere all'uml)
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
