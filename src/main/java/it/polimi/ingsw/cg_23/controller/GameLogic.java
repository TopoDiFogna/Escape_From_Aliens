package it.polimi.ingsw.cg_23.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

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
import it.polimi.ingsw.cg_23.model.status.Match;

/**
 * Description of GameLogic.
 * 
 * @author Paolo
 */

public class GameLogic implements Observer {
	
	private String choice;

    private Match match;

    /**
     * The constructor.
     */
    public GameLogic(Match match) {
        this.match = match;
    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO Auto-generated method stub
        // take player action and processes it

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
        if (destination.getType() == SectorTypeEnum.VOID || destination.getType() == SectorTypeEnum.HUMAN || destination.getType() == SectorTypeEnum.ALIEN) 
            return false;

        if (player.getCurrentSector().getNeighbors().contains(destination))// one step default for the human
        	return true;

        if (player.getCanMoveFaster()) {// two step, default for the alien
            for (Sector sector : player.getCurrentSector().getNeighbors()) {
                if (sector.getNeighbors().contains(destination))
                    return true;
            }
        }

        if (player instanceof Alien && ((Alien) player).getHasKilled()) {// three step, alien after killing someone
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
    	ArrayList<Player> playerList = (ArrayList<Player>) playerWhoAttack.getCurrentSector().getPlayer();
    	Iterator<Player> playerIterator = playerList.iterator();
    	while (playerIterator.hasNext()) {
			Player playerAttacked = (Player) playerIterator.next();
			if (playerWhoAttack != playerAttacked && !hasCard(playerAttacked, new DefenseCard())){
                playerAttacked.setDead();
                playerIterator.remove();
                removeAfterDying(playerAttacked);
			} else if(hasCard(playerAttacked, new DefenseCard()))
            	//questa condizione in realtà non fa niente perchè useDefence non ha nessuna istruzione      
            	useDefense(playerAttacked);
		}
    }

    /**
     * This card can not be used from human, but auto-used when human is attacked, to prevent him to be killed.
     */
    public void useDefense(Player player) {
    	//the method can not be directly called, but auto used.
    }

    /**
     * This method set as true human attribute escaped. Set this escape hatch sector as unusable.
     */
    public void useGreen(Player player) {
        Human human = (Human) player;
        player.getCurrentSector().setEscapeHatchSectorNotCrossable();
        human.setEscaped();
        removeAfterWinning(player);
    }

    /**
     * Asks view to ask user in what sector there is noise.
     */
    public void useNoiseInAnySector(Player player) {
        // TODO bisogna chiedere alla view di chiedere all'utente di passargli
        // il settore di cui vuole comunicare il rumore
    }

    /**
     * This method is auto-called when player pick-up this card. <br>
     * It get the current sector and check if the card has the item.
     */
    public void useNoiseInYourSector(Player player) {
        player.getCurrentSector();
        // TODO notifica view col settore

    }

    /**
     * Set this escape hatch sector as unusable.
     */
    public void useRed(Player player) {
        player.getCurrentSector().setEscapeHatchSectorNotCrossable();
        //TODO bisogna notificare al player che si deve cercare una nuova scialuppa
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
    public void useSilence(Player player) {
        // TODO notifica la view che ha pescato la carta silenzio
    }

    /**
     * This method asks view to ask human player the sector. <br>
     * After that the controller check if in the selected sector and in the nearby there are someone. <br>
     * If this sector aren't empty the model notify the view that communicate the position of this players.
     */
    public void useSpotlight(Player player) {
        int lettera = 0;
        int numero = 0;
        // TODO chiedere al giocatero che settore vuole vedere
        Sector[][] sector = match.getMap().getSector();
        sector[lettera][numero].getPlayer();
        // TODO notifica messaggio
        for (Sector sectors : sector[lettera][numero].getNeighbors()) {
            sectors.getLetter();
            sectors.getNumber();
            sectors.getPlayer();
            // TODO messaggio socket per dire chi è dove
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
     * First of all we check if player has the card he want to use (calling hasCard() method), <br>
     * if he has it calls the method doAction in the card.<br>
     * When the card has finished to do its action, the method calls discardCard to put the card in the right discard <br> 
     * deck and remove it from player hand.
     * 
     * @param player who use the card
     * @param card used
     */
    public void useItemCard(Player player, Card card) {
        if (hasCard(player, card)) {
            card.doAction(player, this);
            discardItemCard(player, card);
        }
    }

    /**
     * This method allow player to use sector or escape hatch card, calling doAction() method in the right card. <br>
     * After that check if is an escape hatch card, putting it in the escape hatch discarded deck, else put it in <br>
     * sector discarded deck. 
     * This method, unlike the useItemCard() method, don't check if player has the card in his hand, because these cards are <br>
     * immediately use.
     * 
     * @param player
     * @param card
     */
    public void useOtherCard(Player player, Card card) {
        card.doAction(player, this);
        if (card instanceof GreenCard || card instanceof RedCard)
            match.getEscapeHatchDeckDiscarded().add(card);
        else
            match.getSectorDeckDiscarded().add(card);
    }

    /**
     * Removes card used by useItemCard() from the player hand and put it in the item discarded deck.
     * 
     * @param player who uses the card
     * @param card used by player
     */
    public void discardItemCard(Player player, Card card) {
        player.getCards().remove(card);
        match.getItemDeckDiscarded().add(card);
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
     * After calls the method for picking up a sector card, removing it from sector deck and <br>
     * returns the card. <br>
     * Now check if this card is <b> instance of </b> NoiseInAnySectorCard or NoiseInYourSectorCard. <br>
     * Casts the type and check calling hasItem() method if the card has item symbol. <br>
     * If yes calls the method for picking up an item card, and if both item deck both item deck discarded aren't <br>
     * empty check if the player already has 3 item card in his hand calling choseHowUseItemCard() method. <br>
     * After all these control, finally the method calls the useOtherCard() method for activate the sector card action.
     * 
     * @param player
     */
    public void drawSectorCard(Player player) {
    	//Check if the sector deck is empty, and if yes calls the shuffle method for shuffle discard deck 
    	//and replacing the empty deck with the shuffled one.
        if (match.getSectorDeck().isEmpty()) {
            shuffleSectorDeck();
        }

        Card sectorCard = pickSectorCard();

        if (sectorCard instanceof NoiseInAnySectorCard) {
            NoiseInAnySectorCard newSectorCard = (NoiseInAnySectorCard) sectorCard;
            if (newSectorCard.hasItem()) {
                Card itemCard = drawItemCard();
                if (itemCard != null)
                    choseHowUseItemCard(player, itemCard, choice);
            }
        } else if (sectorCard instanceof NoiseInYourSectorCard) {
            NoiseInYourSectorCard newSectorCard = (NoiseInYourSectorCard) sectorCard;
            if (newSectorCard.hasItem()) {
                Card itemCard = drawItemCard();
                if (itemCard != null)
                    choseHowUseItemCard(player, itemCard, choice);
            }
        }
        useOtherCard(player, sectorCard);
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
        if (!match.getItemDeckDiscarded().isEmpty()) {
            Collections.shuffle(match.getItemDeckDiscarded());
            this.match.setItemDeck(this.match.getItemDeckDiscarded());
        }
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
     * Adds the new card to the list of player's card. <br>
     * If the list is now 4 elements, asks view to ask player what he want to do. <br>
     * Player can chose to use one of 4 cards (calling useItemCard method) or discard one (calling discardCard method). <br>
     * We set discard as default in the switch statement.
     * 
     * @param player
     * @param itemCard
     */
    public void choseHowUseItemCard(Player player, Card itemCard, String choice) {
        player.getCards().add(itemCard);
        if (player.getCards().size() > 3) {
        	if(choice != null){

	            // TODO chiedere alla view di chiedere al giocatore cosa vuole fare e quale carta vuole usare o buttare
        		//quindi c'è da aggiungere la scelta della carta tra quelle che ha in mano con un for o cacchio ne so!
	            switch (choice) {
	            case "usa":
	                useItemCard(player, itemCard);
	                break;
	            case "butta":
	                discardItemCard(player, itemCard);
	                break;
	            default:
	                discardItemCard(player, itemCard);
	                break;
	            }
            }
        }
    }

    /**
     * The dead player is removed from players list after his death.
     * 
     * @param player
     */
    public void removeAfterDying(Player player) {
    	if(!player.isAlive()){
    		match.getPlayers().remove(player);    		
    	}
        // TODO notifica la view e dice al player che è morto D:
    }

    /**
     * The escaped player is removed from player list and from player list in current sector after his victory.
     * 
     * @param player
     */
    public void removeAfterWinning(Player player) {
    	if(!player.getCurrentSector().isCrossable()){
    		player.getCurrentSector().getPlayer().remove(player);
    		match.getPlayers().remove(player);    		
    	}
    	// TODO notifica la view e dice al player che ha vinto \o/
    }
    
    //TODO javadoc and implementation. anche scelta se vuole usare carta attacco o pescare carta settore
    public void movePlayer(Player player, Sector sector){
    	if(player instanceof Human && ((Human) player).isSedatives()){
    		player.getCurrentSector().getPlayer().remove(player);
    		player.setCurrentSector(sector);
    		sector.getPlayer().add(player);
    	} else if(sector.getType() == SectorTypeEnum.DANGEROUS){
    		player.getCurrentSector().getPlayer().remove(player);
    		player.setCurrentSector(sector);
    		sector.getPlayer().add(player);
    		drawSectorCard(player);
    	} else if(player instanceof Human && sector.getType() == SectorTypeEnum.ESCAPEHATCH){
    		if(sector.isCrossable()){
    			player.getCurrentSector().getPlayer().remove(player);
        		player.setCurrentSector(sector);
        		sector.getPlayer().add(player);
        		drawEscapeHatchCard(player);    			
    		} else {
    			player.getCurrentSector().getPlayer().remove(player);
        		player.setCurrentSector(sector);
        		sector.getPlayer().add(player);
    		}
    	}
    }
    
    public void canAttack(Player player){
    	//TODO anche attack per alieno o da sistemare il nostro useAttack per renderlo generico e fare qua i controlli se umano o alieno
    }
    
}