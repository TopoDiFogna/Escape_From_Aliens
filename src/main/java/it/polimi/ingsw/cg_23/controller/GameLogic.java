package it.polimi.ingsw.cg_23.controller;

import it.polimi.ingsw.cg_23.model.cards.Card;
import it.polimi.ingsw.cg_23.model.cards.DefenseCard;
import it.polimi.ingsw.cg_23.model.cards.NoiseInYourSectorCard;
import it.polimi.ingsw.cg_23.model.map.Map;
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
        
        if(player instanceof Alien && ((Alien) player).getHasKilled()){//three step
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
    
    
    public void useDefense(){
    	
    }
    
    /**
	 * This method set as true human attribute escaped.
	 * Set this escape hatch sector as unusable.
	 */
	public void useGreen(Human player) {
		player.setEscaped();
		removeAfterWinnig(player);
	}
	
	
	public void useNoiseInAnySector(){
		
	}
	
	/**
	 * This method is auto-called when player pick-up this card. <br>
	 * It get the current sector and check if the card has the item
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
	
	
	public void useSedatives(){
		
	}
	
	
	public void useSilence(){
		
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
	
	
	public void drawSectorCard(){
		//TODO prnde il mazzo, controlla che il mazzo non sia vuoto (se vuoto prende il mazzo degli scarti lo mischia e lo mette giù.
		//se è vuoto esce), pesca, prende la carta e guarda ti che tipo è, guarda se ha l'oggetto, in caso pesca la carta oggetto 
		//(e controlla se può tenerle in mano o deve scartarne una), ora finalmente fa card.doAction()
	}
	
	public void removeAfterDying(Player player){
		//TODO
	}
	
	public void removeAfterWinnig(Player player){
		//TODO
	}
}