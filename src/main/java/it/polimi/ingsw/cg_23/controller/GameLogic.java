package it.polimi.ingsw.cg_23.controller;

import java.util.Collections;

import it.polimi.ingsw.cg_23.model.cards.Card;
import it.polimi.ingsw.cg_23.model.cards.Deck;
import it.polimi.ingsw.cg_23.model.cards.DefenseCard;
import it.polimi.ingsw.cg_23.model.cards.NoiseInAnySectorCard;
import it.polimi.ingsw.cg_23.model.cards.NoiseInYourSectorCard;
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
public class GameLogic {

    private Match match;

    /**
     * The constructor.
     */
    public GameLogic(Match match) {
        this.match = match;
    }

    /**
     * Checks if the player can move in the chosen sector.
     * 
     * @param player who wants to move
     * @param destination where the player wants to move
     * @return true if the player can move to the chosen sector, false otherwise
     */
    public boolean mossaValida(Player player, Sector destination) {
        
        if (destination.getType() == SectorTypeEnum.VOID || destination.getType() == SectorTypeEnum.HUMAN || destination.getType() == SectorTypeEnum.ALIEN) //can't enter in
            return false;
        
        if (player.getCurrentSector().getNeighbors().contains(destination))//one step, default for the human
            return true;
        
        if (player.getCanMoveFaster()){//two step, default for the alien
            for (Sector sector : player.getCurrentSector().getNeighbors()) {
                if (sector.getNeighbors().contains(destination))
                    return true;
            }
        }
        
        if(player instanceof Alien && ((Alien) player).getHasKilled()){//three step, alien after killing someone
            for (Sector sector : player.getCurrentSector().getNeighbors()){
                for (Sector neighbor : sector.getNeighbors()) {
                    if(neighbor.getNeighbors().contains(destination))
                        return true;
                }
            }
        }
        return false;
    }

    
    /**
     * This boolean method is true if the selected player has a specific card, is false if he hasn't. <br>
     * We control if player has in his hand the card, using ArrayList contains() method.
     * 
     * @param player
     * @param card player want to use
     * @return a boolean (true if he has card, false if he hasn't)
     */
    public boolean hasCard(Player player, Card card) {
        
        for (Card cards : player.getCards()) {
            if(cards.getClass().equals(card.getClass()))
                return true;
        }
    	return false;
    }
    
    /**
     * This method is only for Item card, that the player must have to use them.<br>
     * First of all we check if player has the card he want to use, if he has it calls the method doAction in the card.<br>
     * When the card has finished to do its action, the method calls discardCard to put the card in the discardDeck and delete it from player hand. <br>
     * Uses the card selected by player and removes it from the player hand.
     * 
     * @param player who use the card
     * @param card used
     */
    public void useItemCard(Player player, Card card){
        if(hasCard(player, card)){
        	card.doAction(player, this);
        	player.discardCard(card);
        }
    }
    
    /**
     * Is the same method of useItemCard, but it doesn't check if the player has the card, because he only picks-up, uses and discards card immediatly.
     * 
     * @param player
     * @param card
     */
    public void useOtherCard(Player player, Card card){
        card.doAction(player, this);
        player.discardCard(card);
    }
    
    /**
	 * Set human the possibility to move 2 sector instead 1.
	 */
    public void useAdrenaline(Player player){
    	player.setCanMoveFaster(true);
    }
    
    /**
	 * For each player in the current sector check if they have defense card and if they aren't the player who use the card, and set dead the other.
	 * useAttack method is the same for humans and aliens.
	 */
    public void useAttack(Player player){
    	for (Player players : player.getCurrentSector().getPlayer()) {
			if(players!=player && !players.getCards().contains(new DefenseCard())){
				players.setDead();
				removeAfterDying(player);
			}
		}
    }   
    
    /**
	 * This card can not be used from human, but auto-used when human is attacked.
	 */
    public void useDefense(Player player){
    	
    }
    
    /**
	 * This method set as true human attribute escaped.
	 * Set this escape hatch sector as unusable.
	 */
	public void useGreen(Player player) {
		Human human = (Human) player;
		human.setEscaped();
		removeAfterWinning(player);
	}
	
	/**
	 * Asks view to ask user in what sector there is noise.
	 */
	public void useNoiseInAnySector(Player player){
		//TODO bisogna chiedere alla view di chiedere all'utente di passargli il settore di cui vuole comunicare il rumore
	}
	
	/**
	 * This method is auto-called when player pick-up this card. <br>
	 * It get the current sector and check if the card has the item.
	 */
	public void useNoiseInYourSector(Player player){
		player.getCurrentSector();
		//TODO notifica view col settore
		
	}
	
	/**
	 * Set this escape hatch sector as unusable.
	 */
	public void useRed(Player player){
		player.getCurrentSector().setEscapeHatchSectorNotCrossable();
	}
	
	/**
	 * This method prevents human to pick-up card when in dangerous sector.
	 */
	public void useSedatives(Player player){
		// TODO nel controller è come se passassi su un settore sicuro (dopo il controllo del tipo di settore di destinazione si 
				//fa un IF: per controllare se prima è stata usata questa carta 
	}
	
	/**
	 * This method is auto-called when player pick-up this card. <br>
	 * The action simply notify the view to tells other players "silence".
	 */
	public void useSilence(Player player){
		//TODO notifica la view che ha pescato la carta silenzio 
	}	

	/**
	 * This method asks view to ask human player the sector. <br>
	 * After that the controller check if in the selected sector and in the nearby there are someone. <br>
	 * If this sector aren't empty the model notify the view that communicate the position of this players. 
	 */
	public void useSpotlight(Player player){
		int lettera=0;
		int numero=0;
		//TODO per prendere il settore che il giocatore vuole vedere
		Sector[][] sector = match.getMap().getSector();
		sector[lettera][numero].getPlayer();
		//TODO notifica messaggio
		for (Sector sectors : sector[lettera][numero].getNeighbors()) {
			sectors.getLetter();
			sectors.getNumber();
			sectors.getPlayer();
			//TODO messaggio socket per dire chi è dove
		}
	}
	
	/**
	 * The human moves to the starting human sector.
	 */
	public void useTeleport(Player player){
		player.setCurrentSector(match.getMap().getHumanSector());
	}	
	
	
	public void drawSectorCard(Player player){
		Card itemCard = null;
		Deck<Card> sectorDeck = match.getSectorDeck();
		Deck<Card> itemDeck = match.getItemDeck();
		Card sectorCard = null;
		int i = 0;
		
		/**
		 * If sector deck is empty, shuffles sector deck discarded and put the new deck in sector deck.
		 */
		if(match.getSectorDeck().isEmpty()){
			shuffleSectorDeck();
		}
		
		/**
		 * i is the index of the last position of the arraylist sector deck. <br>
		 * Player pick-up the last card of the deck and remove it from the deck.
		 */
		pickSectorCard(sectorDeck, sectorCard, i);
		
		/**
		 * If the drawn card is the type NoiseInAnySectorCard, cast the card, and do control on item and deck item.
		 */
		if(sectorCard instanceof NoiseInAnySectorCard) {
			
			NoiseInAnySectorCard newSectorCard = (NoiseInAnySectorCard) sectorCard;			
			/**
			 * If the card hasItem and the ItemDeck isn't empty, draws an item card and adds it in player hand. <br>
			 * If the item deck is empty, shuffles it, draws and add the card.
			 */
			if((newSectorCard.hasItem()) && (!match.getItemDeck().isEmpty())){
				pickItemCard(itemDeck, itemCard, i);
			} else if((newSectorCard.hasItem()) && (match.getItemDeck().isEmpty())){
				shuffleItemDeck();
				pickItemCard(itemDeck, itemCard, i);
			}
			/**
			 * If the drawn card is the type NoiseInYourSectorCard, cast the card, and do control on item and deck item.
			 */
		} else if (sectorCard instanceof NoiseInYourSectorCard) {
			
			NoiseInYourSectorCard newSectorCard = (NoiseInYourSectorCard) sectorCard;
			/**
			 * If the card hasItem and the ItemDeck isn't empty, draws an item card and adds it in player hand. <br>
			 * If the item deck is empty, shuffles it, draws and add the card.
			 */
			if((newSectorCard.hasItem()) && (!match.getItemDeck().isEmpty())){
				pickItemCard(itemDeck, itemCard, i);
			} else if((newSectorCard.hasItem()) && (match.getItemDeck().isEmpty())){
				shuffleItemDeck();
				pickItemCard(itemDeck, itemCard, i);
			}		
		}
		
		/**
		 * Checks if player already has 3 cards. If yes ask player what he want to do: use or discard one of 4 cards 
		 */
		countItemCard(player, itemCard);
		
		/**
		 * Finally calls method for use sector card.
		 */
		useOtherCard(player, sectorCard);
	}
	
	
	/**
	 * This method shuffles cards sector discarded, and replace the old deck with the new one.
	 */
	public void shuffleSectorDeck(){
		Collections.shuffle(match.getSectorDeckDiscarded());
		this.match.setSectorDeck(this.match.getSectorDeckDiscarded());
	}
	
	/**
	 * Checks if discard deck is not empty. <br>
	 * If it isn't empty shuffles cards item discarded, and replace the old deck with the new one. <br>
	 * It it is empty tells the view the item cards are finished.
	 */
	public void shuffleItemDeck(){
		if(!match.getItemDeckDiscarded().isEmpty()){
			Collections.shuffle(match.getItemDeckDiscarded());
			this.match.setItemDeck(this.match.getItemDeckDiscarded());
		} else {
			//TODO messaggio dalla view che informa che non ci sono più carte item disponibili
		}
	}
	
	/**
	 * Adds the new card to the list of player's card. <br>
	 * If the list is now 4 elements, asks view to ask player what he want to do. <br>
	 * If he want can use one of 4 cards (calling useItemCard method) or if he want to discard one (calling discardCard method).
	 * @param player
	 * @param itemCard
	 */
	public void countItemCard(Player player, Card itemCard){
		player.getCards().add(itemCard);

		if(player.getCards().size() >= 3){
			int choice=0;
			//TODO chiedere alla view di chiedere al giocatore cosa vuole fare
			//da controllare lo switch, perchè secondo me non passa la carta giusta da usare
			switch (choice) {
				case 0:
					useItemCard(player, itemCard);
				case 1:
					player.discardCard(itemCard);
				default:
					//TODO la view deve notificare l'errore di scelta
				break;
			}
		}
	}
	
	/**
	 * i is the index of the last position of the arraylist sector deck. <br>
	 * Player pick-up the last card of the deck and remove it from the deck.
	 */
	public void pickSectorCard(Deck<Card> sectorDeck, Card sectorCard, int i){
		i = match.getSectorDeck().lastIndexOf(sectorDeck);
		sectorCard = match.getSectorDeck().get(i);
		match.getSectorDeck().remove(i);
	}
	
	/**
	 * i is the index of the last position of the arraylist item deck. <br>
	 * Player pick-up the last card of the deck and remove it from the deck.
	 */
	public void pickItemCard(Deck<Card> itemDeck, Card itemCard, int i){
		i = match.getItemDeck().lastIndexOf(itemCard);
		itemCard = match.getItemDeck().get(i);
		match.getItemDeck().remove(i);
	}
	
	/**
	 * The dead player is removed from players list after his death. 
	 * @param player
	 */
	public void removeAfterDying(Player player){
		match.getPlayers().remove(player);
		//notifica la view e dice al player che è morto D:
	}
	
	/**
	 * The escaped player is removed from player list after his victory.
	 * @param player
	 */
	public void removeAfterWinning(Player player){
		match.getPlayers().remove(player);
		//notifica la view e dice al player che ha vinto \o/
	}
	
}