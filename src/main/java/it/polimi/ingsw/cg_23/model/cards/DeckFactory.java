package it.polimi.ingsw.cg_23.model.cards;

/**
 * This class creates the deck, putting the right number and type of card in every deck
 * 
 * @author Arianna
 *
 */
public class DeckFactory {

    /**
     * Constructor
     */
    private DeckFactory() {
        // no need to create an object
    }

    /**
     * Creates decks based on the parameter taken. <br>
     * In every cases we call the relative methods for creating and adding cards to the right deck. <br>
     * In the default case of the switch we create an empty deck, used for cards discarded.
     * 
     * @param deckType If deckType=0 calls sector factory, if deckType=1 calls item <br>
     * factory, if deckType=2 calls escape hatch factory
     */
    public static Deck<Card> createDeck(int deckType) {
        Deck<Card> deck;

        switch (deckType) {

        case 0:
            deck = createSectorDeck();
            return deck;

        case 1:
            deck = createItemDeck();
            return deck;

        case 2:
            deck = createEscapeHatchDeck();
            return deck;

        default:
            deck = new Deck<Card>();
            return deck;
        }
    }

    /**
     * When the switch calls this method, it creates all sector cards and adds them to the deck
     * 
     * @return deck
     */
    private static Deck<Card> createSectorDeck() {
        Deck<Card> deck = new Deck<Card>();

        /**
         * Creates 5 silence cards and calls add to add cards to the deck.
         */
        for (int i = 0; i < 5; i++) {
            SilenceCard card = new SilenceCard(false);
            deck.add(card);
        }

        /**
         * Creates 10 noise in any sector cards (4 with item and 6 without) and calls add to add cards to the deck.
         */
        for (int i = 0; i < 10; i++) {
            if (i <= 3) {
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
        for (int i = 0; i < 10; i++) {
            if (i <= 3) {
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
    private static Deck<Card> createItemDeck() {
        Deck<Card> deck = new Deck<Card>();

        /**
         * Creates 2 attack cards and calls add to add cards to the deck.
         */
        for (int i = 0; i < 2; i++) {
            AttackCard card = new AttackCard();
            deck.add(card);
        }
        /**
         * Creates 2 teleport cards and calls add to add cards to the deck.
         */
        for (int i = 0; i < 2; i++) {
            TeleportCard card = new TeleportCard();
            deck.add(card);
        }
        /**
         * Creates 2 adrenaline cards and calls add to add cards to the deck.
         */
        for (int i = 0; i < 2; i++) {
            AdrenalineCard card = new AdrenalineCard();
            deck.add(card);
        }
        /**
         * Creates 2 spotlight cards and calls add to add cards to the deck.
         */
        for (int i = 0; i < 2; i++) {
            SpotlightCard card = new SpotlightCard();
            deck.add(card);
        }
        /**
         * Creates 3 sedatives cards and calls add to add cards to the deck.
         */
        for (int i = 0; i < 3; i++) {
            SedativesCard card = new SedativesCard();
            deck.add(card);
        }
        /**
         * Creates 1 defense card and calls add to add cards to the deck.
         */
        DefenseCard card = new DefenseCard();
        deck.add(card);

        return deck;
    }

    /**
     * When the switch calls this method, it creates all escape hatch cards and adds them to the deck.
     * 
     * @return deck
     */
    private static Deck<Card> createEscapeHatchDeck() {
        Deck<Card> deck = new Deck<Card>();

        /**
         * Creates 3 green cards and calls addCard to add cards to the deck.
         */
        for (int i = 0; i < 3; i++) {
            GreenCard card = new GreenCard();
            deck.add(card);
        }
        /**
         * Creates 3 red cards and calls addCard to add cards to the deck.
         */
        for (int i = 0; i < 3; i++) {
            RedCard card = new RedCard();
            deck.add(card);
        }
        return deck;

    }
}