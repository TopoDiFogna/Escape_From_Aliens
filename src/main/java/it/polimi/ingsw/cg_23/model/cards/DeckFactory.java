package it.polimi.ingsw.cg_23.model.cards;

public class DeckFactory {    

    /**
     * Creates decks based on the parameter taken.
     * In every cases we call the relative methods for creating and adding cards to the right deck.
     * 
     * @param deckType If deckType=0 calls sector factory, if deckType=1 calls item factory, if deckType=2 calls escape hatch factory
     */ 
	 /* public static Deck<? extends Card> createDeck(int deckType) {
        Deck<? extends Card> deck;
    
        switch (deckType) {
            
            case 0:
                deck = createSectorDeck();         
            break;
                       
            case 1:                 
                deck = createItemDeck();
            break;
            
            case 2:
                deck = createEscapeHatchDeck();
            break;
            
            default:
                // TODO dobbiamo gestire il default, con un eccezione o altro.
                deck = new Deck<SectorCard>();
            break;
        }
        
        return deck;
    }
    
    /**
     * When the switch calls this method, it creates all sector cards and adds them to the deck
     * 
     * @return deck
     */
	private static Deck<Card> createSectorDeck(){
        Deck<Card> deck = new Deck<Card>();
        
        /**
         * Creates 5 silence cards and calls add to add cards to the deck.
         */
        for(int i = 0; i < 4; i++) { 
        	SilenceCard card = new SilenceCard(false);
            deck.add(card);
        }
        
        /**
         * Creates 10 noise in any sector cards (4 with item and 6 without) and calls add to add cards to the deck.
         */
        for(int i = 0; i < 9; i++) {
            if(i<=3){
            	NoiseInAnySectorCard card = new NoiseInAnySectorCard(true);
                deck.add(card);
            } else {
            	NoiseInAnySectorCard card = new NoiseInAnySectorCard(false);
                deck.add(card);
            }
        }
        
        /**
         * Creates 10 noise in your sector cards (4 with item and 6 without) and calls add to add cards to the deck.
         */
       for(int i = 0; i < 9; i++) {
    	   if(i<=3){
           	NoiseInYourSectorCard card = new NoiseInYourSectorCard(true);
               deck.add(card);
           } else {
           	NoiseInYourSectorCard card = new NoiseInYourSectorCard(false);
               deck.add(card);
           }
        }
        return deck;
    }
    
    /**
     * When the switch calls this method, it creates all item cards and adds them to the deck.
     * 
     * @return deck
     */
	private static Deck<Card> createItemDeck(){
        Deck<Card> deck = new Deck<Card>();        

        /**
         * Creates 2 attack cards and calls add to add cards to the deck.
         */
        for(int i = 0; i < 1; i++) {
        	AttackCard card = new AttackCard();
            deck.add(card);
        } 
        /**
         * Creates 2 teleport cards and calls add to add cards to the deck.
         */
        for(int i = 0; i < 1; i++) {
            TeleportCard card = new TeleportCard();
            deck.add(card);
        } 
        /**
         * Creates 2 adrenaline cards and calls add to add cards to the deck.
         */
        for(int i = 0; i < 1; i++) {
            AdrenalineCard card = new AdrenalineCard();
            deck.add(card);
        }                 
        /**
         * Creates 2 spotlight cards and calls add to add cards to the deck.
         */
        for(int i = 0; i < 1; i++) {
        	SpotlightCard card = new SpotlightCard();
            deck.add(card);
        } 
        /**
         * Creates 3 sedatives cards and calls add to add cards to the deck.
         */
        for(int i = 0; i < 2; i++) {
        	SedativesCard card = new SedativesCard();
            deck.add(card);
        }                
        /**
         * Creates 1 defence card and calls add to add cards to the deck.
         */
        SedativesCard card = new SedativesCard();
        deck.add(card);
        
        return deck;
    }
    
    /**
     * When the switch calls this method, it creates all escape hatch cards and adds them to the deck.
     * 
     * @return deck
     */
     private static Deck<Card> createEscapeHatchDeck(){
	    	Deck<Card> deck = new Deck<Card>();        

        /**
         * Creates 3 green cards and calls addCard to add cards to the deck.
         */
	        for(int i = 0; i < 2; i++) {
	        	GreenCard card = new GreenCard();
	        	deck.add(card);
	        }                 
        /**
         * Creates 3 red cards and calls addCard to add cards to the deck.
         */
	       for(int i = 0; i < 2; i++) {
	    	   RedCard card = new RedCard();
	    	   deck.add(card);
        }
        return deck;
            
    }
}