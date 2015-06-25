package it.polimi.ingsw.cg_23.gui;

/**
 * This class gives method for communication (both rmi and socket)
 * 
 * @author Paolo
 */
public abstract class Connection {

    /**
     * Calls move method in right class according to the connection chosen.
     * 
     * @param letter sector's letter where the player wants move
     * @param number sector's number where the player wants move
     */
    public abstract void move(String letter, String number);
    
    /**
     * Calls move and attack method in right class according to the connection chosen.
     * 
     * @param letter sector's letter where the player wants move and attack
     * @param number sector's number where the player wants move and attack
     */
    public abstract void moveAndAttack(String letter, String number);
    
    /**
     * Calls noise method in right class according to the connection chosen.
     * 
     * @param letter sector's letter where the player wants to make a noise
     * @param number sector's number where the player wants to make a noise
     */
    public abstract void makeNoise(String letter, String number);
    
    /**
     * Calls end turn method in right class according to the connection chosen.
     */
    public abstract void endTurn();
    
    /**
     * Allows the player to chat with other players in the same match.
     * 
     * @param msg message to be sent
     */
    public abstract void chat(String msg);
    
    /**
     * Allows the player to use the card.
     * 
     * @param card card player wants to use
     * @param letter use only in case of spotlight card and indicates the letter where he want to use spotlight
     * @param number use only in case of spotlight card and indicates the number where he want to use spotlight
     */
    public abstract void useCard(String card, String letter, String number);
    
    /**
     * Allows the player to discard a card.
     * 
     * @param card card to be discarded
     */
    public abstract void discardCard(String card);
    
    /**
     * Asks the server for the cards the player has in his hand
     */
    public abstract void getCards();
}
