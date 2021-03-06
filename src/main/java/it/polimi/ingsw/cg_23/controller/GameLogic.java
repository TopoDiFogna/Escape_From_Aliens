package it.polimi.ingsw.cg_23.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import it.polimi.ingsw.cg_23.model.cards.AttackCard;
import it.polimi.ingsw.cg_23.model.cards.Card;
import it.polimi.ingsw.cg_23.model.cards.DefenseCard;
import it.polimi.ingsw.cg_23.model.cards.GreenCard;
import it.polimi.ingsw.cg_23.model.cards.NoiseInAnySectorCard;
import it.polimi.ingsw.cg_23.model.cards.NoiseInYourSectorCard;
import it.polimi.ingsw.cg_23.model.cards.RedCard;
import it.polimi.ingsw.cg_23.model.map.Sector;
import it.polimi.ingsw.cg_23.model.map.SectorTypeEnum;
import it.polimi.ingsw.cg_23.model.players.Alien;
import it.polimi.ingsw.cg_23.model.players.Human;
import it.polimi.ingsw.cg_23.model.players.Player;
import it.polimi.ingsw.cg_23.model.status.GameState;
import it.polimi.ingsw.cg_23.model.status.Match;
import it.polimi.ingsw.cg_23.network.rmi.RMIBroker;
import it.polimi.ingsw.cg_23.network.socket.SocketBroker;

/**
 * Description of GameLogic.
 * 
 * @author Paolo, Arianna
 */

public class GameLogic{
    
    private static final String ALLESCAPEUSED = "All escape hatches have been used or are unusable!";
    private static final String NOISE = "Noise in sector ";
    private static final String ALLESCAPED = "All Humans have escaped!";
    
    private Match match;
    
    private SocketBroker socketBroker;
    
    private RMIBroker rmiBroker;

    /**
     * The constructor.
     */
    public GameLogic(Match match) {
        
        this.match = match;
    }

    /**
     * Allows to set the socket broker before starting the game.
     * 
     * @param broker the broker to be added
     */
    //     * Done as this to not rewrite tons of code.*
    public void setSocketBroker(SocketBroker broker) {
        this.socketBroker = broker;
    }
    
    /**
     * Allows to set the rmi broker before starting the game.
     * 
     * @param broker the broker to be added
     */
    //     * Done as this to not rewrite tons of code.*
    public void setRMIBroker(RMIBroker broker) {
        this.rmiBroker = broker;
    }

    /**
     * Checks if the player can move in the chosen sector.
     * 
     * @param player who wants to move
     * @param destination where the player wants to move
     * @return true if the player can move to the chosen sector, false otherwise
     */
    public boolean validMove(Player player, Sector destination) {
        // can't enter in
        if (destination.getType() == SectorTypeEnum.VOID || destination.getType() == SectorTypeEnum.HUMAN  || destination.getType() == SectorTypeEnum.ALIEN)
            return false;

        if (player.getCurrentSector().getNeighbors().contains(destination))
         // one step default for the human
            return true;

        if (player.getCanMoveFaster()) {// two step, default for the alien
            for (Sector sector : player.getCurrentSector().getNeighbors()) {
                if (sector.getNeighbors().contains(destination))
                    return true;
            }
        }

        if (player instanceof Alien && ((Alien) player).getHasKilled()) {
         // three step for aliens after killing someone                                                             
            for (Sector sector : player.getCurrentSector().getNeighbors()) {
                for (Sector neighbor : sector.getNeighbors()) {
                    if (neighbor.getNeighbors().contains(destination))
                        return true;
                }
            }
        }
        return false;
    }

    /**
     * Set human the possibility to move 2 sector instead 1.
     */
    public void useAdrenaline(Player player) {
        player.setCanMoveFaster(true);
    }

    /**
     * First of all the method creates an iterator containing the players in the same sector of the attacker. <br>
     * The while statement iterates until the player list has a next element. <br>
     * In the while statement the method check if the attacked player is different from attacker and the attacked hasn't <br>
     * the defense card. If the condition of the if statement is verified he set as dead the attacked player, remove from <br>
     * player list and call a method that removes the dead player from the list of player in the match. <br>
     * If the condition in if statement is not verified, calls another if that check if the player has a defense card and uses it.
     * 
     * @param playerWhoAttack
     */
    public void useAttack(Player playerWhoAttack) {
        List<Player> playerList = (ArrayList<Player>) playerWhoAttack.getCurrentSector().getPlayer();
        Iterator<Player> playerIterator = playerList.iterator();
        while (playerIterator.hasNext()) {
            Player playerAttacked = playerIterator.next();
            if (playerWhoAttack != playerAttacked && !hasCard(playerAttacked, new DefenseCard())) {
                playerAttacked.setDead();
                playerIterator.remove();
                removeAfterDying(playerAttacked);
            } else if (hasCard(playerAttacked, new DefenseCard())){
                //this condition do nothing because useDefence hasn't instruction
                useDefense(playerAttacked);
                discardItemCard(playerAttacked, new DefenseCard());
            }
        }
        discardItemCard(playerWhoAttack, new AttackCard());
    }

    /**
     * This card can not be used from human, but auto-used when human is attacked, to prevent him to be killed.
     */
    public void useDefense(Player player) {
        // the method can not be directly called, but auto used.
    }

    /**
     * This method set as true human attribute escaped. Set this escape hatch sector as unusable.
     */
    public void useGreen(Player player) {
        Human human = (Human) player;
        player.getCurrentSector().setEscapeHatchSectorNotCrossable();
        human.setEscaped();
        match.removeEcapeHatch();
        removeAfterWinning(player);
        if(match.getnUsableEscapeHatch()==0){
            socketBroker.publish(ALLESCAPEUSED);
            rmiBroker.publish(ALLESCAPEUSED);
            endGame();
        }
        int numberOfHumans=0;
        for (Player playerInGame : match.getPlayers()) {
            if(playerInGame instanceof Human)
                numberOfHumans++;
        }
        if(numberOfHumans == 0){
            socketBroker.publish(ALLESCAPED);
            rmiBroker.publish(ALLESCAPED);
            endGame();
        }
    }

    /**
     * Asks view to ask user in what sector there is noise.
     */
    public void useNoiseInAnySector(Player player) {
        //Nothing to do
    }

    /**
     * This method is auto-called when player pick-up this card. <br>
     * It get the current sector and check if the card has the item.
     */
    public void useNoiseInYourSector(Player player) {
        char letter=(char) ((player.getCurrentSector().getLetter())+96);
        int number=player.getCurrentSector().getNumber();
        socketBroker.publish(NOISE+letter+" "+number);
        rmiBroker.publish(NOISE+letter+" "+number);

    }

    /**
     * Set this escape hatch sector as unusable.
     */
    public void useRed(Player player) {
        player.getCurrentSector().setEscapeHatchSectorNotCrossable();
        char letter=(char) (player.getCurrentSector().getLetter()+97);
        int number=player.getCurrentSector().getNumber();
        socketBroker.publish("The Escape Hatch "+letter+" "+number+" is broken!");
        rmiBroker.publish("The Escape Hatch "+letter+" "+number+" is broken!");
        if(match.getnUsableEscapeHatch()==0){
            socketBroker.publish(ALLESCAPEUSED);
            rmiBroker.publish(ALLESCAPEUSED);
            endGame();
        }
    }

    /**
     * This method prevents human to pick-up card when in dangerous sector.
     */
    public void useSedatives(Player player) {
        ((Human) player).setSedatives(true);
    }

    /**
     * This method is auto-called when player pick-up this card. <br>
     * The action simply notify the view to tells other players "silence".
     */
    public void useSilence() {
        socketBroker.publish("Silence!");
        rmiBroker.publish("Silence!");
    }

    /**
     * This method asks view to ask human player the sector. <br>
     * After that the controller check if in the selected sector and in the nearby there are someone. <br>
     * If this sector aren't empty the model notify the view that communicate the position of this players.
     * 
     * @param letter letter of sector chosen by player 
     * @param number number of sector chosen by player 
     */
    public void useSpotlight(int letter, int number) {
        Sector[][] sector = match.getMap().getSector();
        char letterAsChar = (char) (letter+97);
        
        for (Player players : sector[letter][number].getPlayer()) {
            String name = players.getName();
            socketBroker.publish(""+name+" is in sector "+letterAsChar+" "+(number+1));
            rmiBroker.publish(""+name+" is in sector "+letterAsChar+" "+(number+1));
        }
        
        for (Sector sectors : sector[letter][number].getNeighbors()) {
            
            char neighborLetter=(char) (sectors.getLetter()+96);
            int neighborNumber = sectors.getNumber()-1;

            for (Player players : sectors.getPlayer()) {
                String name = players.getName();
                socketBroker.publish(""+name+" is in sector "+neighborLetter+" "+(neighborNumber+1));
                rmiBroker.publish(""+name+" is in sector "+neighborLetter+" "+(neighborNumber+1));
            }
        }
    }

    /**
     * The human moves to the starting human sector.
     */
    public void useTeleport(Player player) {
        Sector sector = match.getMap().getHumanSector();
        player.getCurrentSector().getPlayer().remove(player);
        player.setCurrentSector(sector);
        sector.getPlayer().add(player);
    }

    /**
     * This boolean method is true if the selected player has a specific card, is false if he hasn't. <br>
     * We check if the card player want to use is the same type of a card he has in his hand.
     * 
     * @param player
     * @param card player want to use
     * @return a boolean (true if he has card, false if he hasn't)
     */
    public boolean hasCard(Player player, Card card) {

        for (Card cards : player.getCards()) {
            if (cards.getClass().equals(card.getClass()))
                return true;
        }
        return false;
    }

    /**
     * This method is only for Item card, that the player must have to use them.<br>
     * Before calling this method, is already done the control if the player has the card he want to use. <br>
     * When the card has finished to do its action, the method calls discardCard to put the card in the discard <br>
     * deck and remove it from player hand.
     * 
     * @param player who use the card
     * @param card used
     */
    public void useItemCard(Player player, Card card) {
        card.doAction(player, this);
        discardItemCard(player, card);
    }

    /**
     * This method allow player to use sector or escape hatch card, calling doAction() method in the right card. <br>
     * After that check if is an escape hatch card, put it in the escape hatch discarded deck, else put it in <br>
     * sector discarded deck. 
     * 
     * @param player
     * @param card
     */
    public void useOtherCard(Player player, Card card) {
        card.doAction(player, this);
        if ((card instanceof GreenCard) || (card instanceof RedCard))
            match.getEscapeHatchDeckDiscarded().add(card);
        else
            match.getSectorDeckDiscarded().add(card);
    }

    /**
     * Removes card used by useItemCard() from the player hand and put it in the item discarded deck. <br>
     * It's also set false setHasFourCard (because this method can be called when the player chose what he <br>
     * want to do with the exceed card.
     * 
     * @param player who uses the card
     * @param card used by player
     */
    public void discardItemCard(Player player, Card card) {
        for (Card playerCard : player.getCards()) {
            if(playerCard.getClass()==card.getClass()){
                player.getCards().remove(playerCard);
                match.getItemDeckDiscarded().add(playerCard);
                player.setHasFourCard(false);
                break;
            }
        }
    }

    /**
     * When a player enter in an escape hatch sector he calls this method to draw an escape hatch card. <br>
     * Takes the first card of the array list, removing from the top of the deck. <br>
     * After that calls the method for using this card and discard it.
     * 
     * @param player
     */
    public void drawEscapeHatchCard(Player player) {
        Card escapeHatchCard = match.getEscapeHatchDeck().get(0);
        match.getEscapeHatchDeck().remove(0);
        useOtherCard(player, escapeHatchCard);
    }

    /**
     * When player enter in a dangerous sector he calls this method for draw sector card. <br>
     * First of all this method check if sector deck is empty, if yes shuffle the discarded deck <br>
     * and replace the empty one with the shuffled one. <br>
     * After calls the method for picking up a sector card, removing it from sector deck and returns the card. <br>
     * Now check if this card is <b> instance of </b> NoiseInAnySectorCard or NoiseInYourSectorCard. <br>
     * Casts the type and check calling hasItem() method if the card has item symbol. <br>
     * If yes calls the method for picking up an item card, and if both item deck both item deck discarded aren't <br>
     * empty check if the player already has 3 item card in his hand calling choseHowUseItemCard() method. <br>
     * After all these control, finally the method calls the useOtherCard() method for activate the sector card action.
     * 
     * @param player
     */
    public String drawSectorCard(Player player) {
        // Check if the sector deck is empty, and if yes calls the shuffle
        // method for shuffle discard deck
        // and replacing the empty deck with the shuffled one.
        if (match.getSectorDeck().isEmpty()) {
            shuffleSectorDeck();
        }

        Card sectorCard = pickSectorCard();

        if (sectorCard instanceof NoiseInAnySectorCard) {
            NoiseInAnySectorCard newSectorCard = (NoiseInAnySectorCard) sectorCard;
            if (newSectorCard.hasItem()) {
                Card itemCard = drawItemCard();
                if (itemCard != null){
                    player.getCards().add(itemCard);
                    if(player.getCards().size()>3){
                        player.setHasFourCard(true);
                    }
                }    
            }
        } else if (sectorCard instanceof NoiseInYourSectorCard) {
            NoiseInYourSectorCard newSectorCard = (NoiseInYourSectorCard) sectorCard;
            if (newSectorCard.hasItem()) {
                Card itemCard = drawItemCard();
                if (itemCard != null){
                    player.getCards().add(itemCard);
                    if(player.getCards().size()>3){
                        player.setHasFourCard(true);
                    }
                }
            }
        }
        useOtherCard(player, sectorCard);
        if(sectorCard instanceof NoiseInAnySectorCard){
            player.setNeedSectorNoise(true);
            return "You drawn a noise in ANY sector card. In which sector do you want a noise?";
        }
        return "";
    }

    /**
     * This method shuffles cards sector discarded, and replace the old deck with the new one.
     */
    public void shuffleSectorDeck() {
        Collections.shuffle(match.getSectorDeckDiscarded());
        this.match.setSectorDeck(this.match.getSectorDeckDiscarded());
    }

    /**
     * Checks if discard deck is not empty. <br>
     * If it isn't empty shuffles cards item discarded, and replace the old deck with the new one. <br>
     */
    public void shuffleItemDeck() {
        Collections.shuffle(match.getItemDeckDiscarded());
        this.match.setItemDeck(this.match.getItemDeckDiscarded());
    }

    /**
     * This method get the first element in the sector deck array, removing it from the top of the deck.
     * 
     * @return sectorCard now picked up from sector deck
     */
    public Card pickSectorCard() {
        Card sectorCard = match.getSectorDeck().get(0);
        match.getSectorDeck().remove(0);
        return sectorCard;
    }

    /**
     * This method get the first element in the item deck array, removing it from the top of the deck.
     * 
     * @return itemCard now picked up from item deck
     */
    public Card pickItemCard() {
        Card itemCard = match.getItemDeck().get(0);
        match.getItemDeck().remove(0);
        return itemCard;
    }

    /**
     * After controlling if the sector card has item, the player must pick it up. <br>
     * Before picking up, this method check if the item deck isn't empty and in this case it calls <br>
     * pickItemCard(). Else if, instead, item deck is empty and deck item cards deck discarded itn's empty, <br>
     * the method calls the shuffle method and now pickItemCard(). Else set itemCard as null and return it.
     * 
     *
     * @return itemCard picked up in pickItemCard() method
     */
    public Card drawItemCard() {
        Card itemCard;
        if (!match.getItemDeck().isEmpty()) {
            itemCard = pickItemCard();
        } else if (!match.getItemDeckDiscarded().isEmpty()) {
            shuffleItemDeck();
            itemCard = pickItemCard();
        } else {
            itemCard = null;
        }
        return itemCard;
    }

    /**
     * The dead player is removed from players list after his death.
     * 
     * @param player
     */
    public void removeAfterDying(Player player) {
        if (!player.isAlive()) {
            match.getPlayers().remove(player);
        }
        socketBroker.publish("Player "+player.getName()+" has died!");
        rmiBroker.publish("Player "+player.getName()+" has died!");
    }

    /**
     * The escaped player is removed from player list and from player list in current sector after his victory.
     * 
     * @param player
     */
    public void removeAfterWinning(Player player) {
        if (!player.getCurrentSector().isCrossable()) {
            player.getCurrentSector().getPlayer().remove(player);
            match.getPlayers().remove(player);
        }
        socketBroker.publish("Player "+player.getName()+" has escaped!");
        rmiBroker.publish("Player "+player.getName()+" has escaped!");
    }

    /**
     * This method calls moveAction method that moves in a different sector the player. <br>
     * If who move is a human and has used sedatives, move player then set as false isSedatives. <br>
     * If the player is moving on a dangerous sector, he moves and draws the sector card. <br>
     * If the player is moving is a human and is moving on an escape hatch sector he moves and draws escape hatch card. <br>
     * If the destination sector is a secure sector, only move. <br>
     *  
     * @param player player who wants to move
     * @param sector sector of destination
     * @return response in base of he does.
     */
    public String movePlayer(Player player, Sector sector) {
        String response = ""; 
        if (player instanceof Human && ((Human) player).isSedatives()) {
            moveActions(player, sector);
            ((Human) player).setSedatives(false);
        } else if (sector.getType() == SectorTypeEnum.DANGEROUS) {
            moveActions(player, sector);
            response = drawSectorCard(player);
        } else if (player instanceof Human && sector.getType() == SectorTypeEnum.ESCAPEHATCH) {
            if (sector.isCrossable()) {
                moveActions(player, sector);
                drawEscapeHatchCard(player);
            } else {
                moveActions(player, sector);
            }
        }
        else if(sector.getType()== SectorTypeEnum.SECURE)
            moveActions(player, sector);
        return response;
    }
    
    /**
     * When a player chose he wants to attack, he must not draw a sector card if he arrives in a <br>
     * dangerous sector, but only calling the attack method (the same for alien and human).
     * 
     * @param player player wants to attack
     * @param sector sector of destination of move and of attack
     */
    public void movePlayerAndAttack(Player player, Sector sector){
        int nHuman = 0;
        
        moveActions(player, sector);
        useAttack(player);
        
        for (Player playerInGame : match.getPlayers()) {
            if(playerInGame instanceof Human)
                nHuman++; 
        }
        if(nHuman==0){
            socketBroker.publish("All remaining humans are dead!");
            rmiBroker.publish("All remaining humans are dead!");
            endGame();
        }
    }
    
    /**
     * This is the method that really move the player in a destination. <br>
     * Remove the player from the start sector player list, set the current player sector <br>
     * with the coordinates of the destination. Adds the player to the list of players of the destination sector. <br> 
     * After the move set as true the boolean setHasMove.
     * 
     * @param player player who wants to move
     * @param sector sector of destination
     */
    private void moveActions(Player player, Sector sector){
        player.getCurrentSector().getPlayer().remove(player);
        player.setCurrentSector(sector);
        sector.getPlayer().add(player);
        player.setHasMoved(true);
    }

    /**
     * This method is called in ClientHandler class. <br>
     * It converts into a letter the first coordinate and adds 1 to number. <br>
     * After calls the broker to publish the message of noise.
     * 
     * @param letter
     * @param number
     */
    public void makeANoise(int letter, int number) {
        char noiseLetter = (char) (letter+97);
        number++;
        socketBroker.publish(NOISE+noiseLetter+" "+number);
        rmiBroker.publish(NOISE+noiseLetter+" "+number);
        
    }
    
    /**
     * This method gives the possibility to start the game. <br>
     * First of all it shuffles the players list (for create a turn sequence). <br>
     * Sets the starting sector as human sector if player is a human, and alien sector if <br>
     * player is an alien. Sets the current player reading the first player of the list. <br>
     * Sets the game state as running and notify who is the first player.
     */
    public void startGame(){
        Collections.shuffle(match.getPlayers());
        
        for (Player player : match.getPlayers()) {
            if(player instanceof Alien){
                player.setCurrentSector(match.getMap().getAlienSector());
                player.getCurrentSector().getPlayer().add(player);
            } else {
                player.setCurrentSector(match.getMap().getHumanSector());
                player.getCurrentSector().getPlayer().add(player);
            }
        }        
        match.setCurrentPlayer(match.getPlayers().get(0));
        match.nextTurn();
        match.setMatchState(GameState.RUNNING);
        socketBroker.publish("Game started. The first player is: "+match.getCurrentPlayer().getName());
        rmiBroker.publish("Game started. The first player is: "+match.getCurrentPlayer().getName());
    }
    
    /**
     * This method reset hasMoved to false. Set the next player in list as current player. <br>
     * If the current player is the last of the list, set as current player the first player of the list. <br>
     * Call nextTurn for increment the turn number. Check if the turn is arrived to 40, and in this case <br>
     * call endGame! 
     */
    public void endTurn(){
        match.getCurrentPlayer().setHasMoved(false);
        int currentPlayerIndex = 0;
        for (Player player : match.getPlayers()) {
            if(match.getCurrentPlayer().getName().equals(player.getName())){
                currentPlayerIndex = match.getPlayers().indexOf(player);
                break;
            }
        }
        if(match.getPlayers().size() > currentPlayerIndex+1){
            match.setCurrentPlayer(match.getPlayers().get(currentPlayerIndex+1));
        } else {
            match.setCurrentPlayer(match.getPlayers().get(0));
            match.nextTurn();
        }
        socketBroker.publish("Next player is: "+match.getCurrentPlayer().getName());
        rmiBroker.publish("Next player is: "+match.getCurrentPlayer().getName());
        if(match.getTurnNumber()==40){
            socketBroker.publish("Turn 39 has ended! Too late! Remaining humans are killed by aliens!");
            rmiBroker.publish("Turn 39 has ended! Too late! Remaining humans are killed by aliens!");
            endGame();
        }
    }
    
    /**
     * This method set the game state to ended and send a message.
     */
    private void endGame(){
        match.setMatchState(GameState.ENDED);
        socketBroker.publish("The game has ended!");
        rmiBroker.publish("The game has ended!");
    }

    /**
     * Sends a chat message to all players
     * 
     * @param id the player who sends the message
     * @param msg the message sent
     */
    public void chat(String id, String msg){
        if(!"".equals(msg)){
            rmiBroker.publish("[CHAT]" + id + ": " + msg);
            socketBroker.publish("[CHAT]" + id + ": " + msg);
        }
    }
    
}