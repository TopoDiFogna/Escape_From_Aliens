package it.polimi.ingsw.cg_23.model.cards;

public class DeckFactory {    

    /**
     * Creates decks based on the parameter taken.
     * In every cases we call the relative methods for creating and adding cards to the right deck.
     * 
     * @param deckType If deckType=0 calls sector factory, if deckType=1 calls item factory, if deckType=2 calls escape hatch factory
     */ 
    public static Deck<Card> createDeck(int deckType) {
        Deck<Card> deck;
                
        switch (deckType) {
            
            case 0:
                deckType = 0;
                deck = createSectorDeck();         
            break;
                       
            case 1:  
                deckType = 1;
                deck = createItemDeck();
            break;
            
            case 2:
                deckType = 2;
                deck = createEscapeHatchDeck();                
            break;
            
            default:
                // TODO dobbiamo gestire il default, con un eccezione o altro.
                deck = new SectorDeck<SectorCard>();
            break;
        }
        
        return deck;
    }
    
    /**
     * When the switch calls this method, it creates all sector cards and adds them to the deck
     * 
     * @return deck
     */
    private static SectorDeck<SectorCard> createSectorDeck(){
        SectorDeck<SectorCard> deck = new SectorDeck<SectorCard>();
        
        /**
         * Creates 5 silence cards and calls add to add cards to the deck.
         */
        for(int i = 0; i < 5; i++) {            
            deck.add(new SectorCard(SectorCardEnum.SILENCE, false));
        }
        
        /**
         * Creates 10 noise in any sector cards (4 with item and 6 without) and calls add to add cards to the deck.
         */
        for(int i = 0; i < 10; i++) {
            if(i<=3){                
                deck.add(new SectorCard(SectorCardEnum.NOISEINANYSECTOR, true));
            } else {                
                deck.add(new SectorCard(SectorCardEnum.NOISEINANYSECTOR, false));
            }
        }
        
        /**
         * Creates 10 noise in your sector cards (4 with item and 6 without) and calls add to add cards to the deck.
         */
        for(int i = 0; i < 10; i++) {
            if(i<=3){
                deck.add(new SectorCard(SectorCardEnum.NOISEINYOURSECTOR, true));
            } else {               
                deck.add(new SectorCard(SectorCardEnum.NOISEINYOURSECTOR, false));
            }
        }
        return deck;
    }
    
    /**
     * When the switch calls this method, it creates all item cards and adds them to the deck.
     * 
     * @return deck
     */
    private static ItemDeck<ItemCard> createItemDeck(){
        ItemDeck<ItemCard> deck = new ItemDeck<ItemCard>();        

        /**
         * Creates 2 attack cards and calls add to add cards to the deck.
         */
        for(int i = 0; i < 2; i++) {
            deck.add(new ItemCard(ItemCardEnum.ATTACK));
        } 
        /**
         * Creates 2 teleport cards and calls add to add cards to the deck.
         */
        for(int i = 0; i < 2; i++) {
            deck.add(new ItemCard(ItemCardEnum.TELEPORT));
        } 
        /**
         * Creates 2 adrenaline cards and calls add to add cards to the deck.
         */
        for(int i = 0; i < 2; i++) {
            deck.add(new ItemCard(ItemCardEnum.ADRENALINE));
        }                 
        /**
         * Creates 2 spotlight cards and calls add to add cards to the deck.
         */
        for(int i = 0; i < 2; i++) {
            deck.add(new ItemCard(ItemCardEnum.SPOTLIGHT));
        } 
        /**
         * Creates 3 sedatives cards and calls add to add cards to the deck.
         */
        for(int i = 0; i < 3; i++) {
            deck.add(new ItemCard(ItemCardEnum.SEDATIVES));
        }                
        /**
         * Creates 1 defence card and calls add to add cards to the deck.
         */
        deck.add(new ItemCard(ItemCardEnum.DEFENCE));
        
        return deck;
    }
    
    /**
     * When the switch calls this method, it creates all escape hatch cards and adds them to the deck.
     * 
     * @return deck
     */
    private static EscapeHatchDeck<EscapeHatchCard> createEscapeHatchDeck(){
        EscapeHatchDeck<EscapeHatchCard> deck = new EscapeHatchDeck<EscapeHatchCard>();        

        /**
         * Creates 3 green cards and calls addCard to add cards to the deck.
         */
        for(int i = 0; i < 3; i++) {
            deck.add(new EscapeHatchCard(EscapeHatchCardEnum.GREEN));
        }                 
        /**
         * Creates 3 red cards and calls addCard to add cards to the deck.
         */
        for(int i = 0; i < 3; i++) {
            deck.add(new EscapeHatchCard(EscapeHatchCardEnum.RED));
        }
        return deck;
            
    }
}