package it.polimi.ingsw.cg_23.model.cards;

public class DeckFactory {    

    /**
     * Creates decks based on the parameter taken.
     * In every cases we create a type of deck and his cards.
     * 
     * @param deckType If deckType=0 calls sector factory, if deckType=1 calls item factory, if deckType=2 calls escape hatch factory
     */ 
    public static Deck<Card> createDeck(int deckType) {
        Deck<Card> deck;
        
        switch (deckType) {
            
            case 0:
                deckType = 0;
                deck = new SectorDeck<SectorCard>();
                
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
            break;
                

            
            case 1:  
                deckType = 1;
                deck = new ItemDeck<ItemCard>();
                
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
                for(int i = 0; i < 1; i++) {
                    deck.add(new ItemCard(ItemCardEnum.DEFENCE));
                }
            break;
            
            case 2:
                deckType = 2;
                deck = new EscapeHatchDeck<EscapeHatchCard>();
                
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
                
            break;
            
            default:
                // TODO dobbiamo gestire il default, con un eccezione o altro.
                deck = new SectorDeck<SectorCard>();
            break;
        }
        
        return deck;
    }
}