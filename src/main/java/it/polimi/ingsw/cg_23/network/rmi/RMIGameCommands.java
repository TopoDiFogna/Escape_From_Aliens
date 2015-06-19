package it.polimi.ingsw.cg_23.network.rmi;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg_23.model.cards.AdrenalineCard;
import it.polimi.ingsw.cg_23.model.cards.AttackCard;
import it.polimi.ingsw.cg_23.model.cards.Card;
import it.polimi.ingsw.cg_23.model.cards.DefenseCard;
import it.polimi.ingsw.cg_23.model.cards.SedativesCard;
import it.polimi.ingsw.cg_23.model.cards.SpotlightCard;
import it.polimi.ingsw.cg_23.model.cards.TeleportCard;
import it.polimi.ingsw.cg_23.model.map.Sector;
import it.polimi.ingsw.cg_23.model.players.Human;
import it.polimi.ingsw.cg_23.model.players.Player;
import it.polimi.ingsw.cg_23.model.status.GameState;
import it.polimi.ingsw.cg_23.model.status.Match;
import it.polimi.ingsw.cg_23.network.ServerStatus;

/**
 * This class handles the game commands sent by rmi clients.
 * 
 * @author Paolo
 *
 */
public class RMIGameCommands implements RMIGameCommandsInterface {
    
    /**
     * Generic error message 
     */
    private static final String ERROR_MESSAGE = "Cannot send message to client!";
    
    /**
     * Error if a player is not i a match
     */
    private static final String ERROR_NOTINGAME = "You are not in a game! Join one first!"; 
    
    /**
     * Error if the match has not started yet
     */
    private static final String ERROR_NOTSTARTEDYET = "Game has not started yet!";
    
    /**
     * Error if it's not the player's turn
     */
    private static final String ERROR_NOTYOURTURN = "It's not your turn!";
    
    /**
     * Error if the player can't use the specified card
     */
    private static final String ERROR_CANTUSECARD = "You can't use that card!";
    
    /**
     * Error if the spotlight card is used in a not correct way
     */
    private static final String ERROR_SPOTLIGHTSYNTAX = "Spotlight syntax: use spotlight letter number";
    

    /**
     * Constructor. Is void because we don't have any parameters
     */
    public RMIGameCommands() {
        // the constructor is void, no need to instantiate variables
    }
    
    /**
     * Checks if the specified id is already in a match
     * 
     * @param id the name identifier of a client
     * @return true if name is not in the list, false otherwise
     */
    private boolean checkIdIfPresent(String id){
        ServerStatus serverStatus = ServerStatus.getInstance();

        if(!serverStatus.getIdMatchMap().containsKey(id))
            return true;
        return false;
    }

    /**
     * Moves the player in the specified sector if he can go there
     */
    @Override
    public void movePlayer(RMIClientInterface clientInterface, String id, int letter, int number) {
        
        ServerStatus serverStatus = ServerStatus.getInstance();
        
        Match match = serverStatus.getIdMatchMap().get(id);
        try{
            if(checkIdIfPresent(id)){
                clientInterface.dispatchMessage(ERROR_NOTINGAME);
                return;
            }
            
            if(!(match.getMatchState()==GameState.RUNNING)){
                clientInterface.dispatchMessage(ERROR_NOTSTARTEDYET);
                return;
            }
                
            if(!match.getCurrentPlayer().getName().equalsIgnoreCase(id)){
                clientInterface.dispatchMessage(ERROR_NOTYOURTURN);
                return;
            }
           
            if(letter<0 || letter>=23 || number <0 || number >=14){
                clientInterface.dispatchMessage("Move sintax: move letter number. The letter can go from A to W, the number from 1 to 14.");
                return;
            }
        } catch (RemoteException e){
            System.err.println(ERROR_MESSAGE);
        }
        
        Sector[][] sector = match.getMap().getSector();
        
        try{
            for (Player playerInList : match.getPlayers()) {
                if(playerInList.getName().equals(id)){
                    if(!(playerInList.needSectorNoise() || playerInList.hasFourCard() || playerInList.hasMoved())){
                        if(match.getGameLogic().validMove(playerInList, sector[letter][number])){
                            clientInterface.dispatchMessage(match.getGameLogic().movePlayer(playerInList, sector[letter][number]));
                            clientInterface.dispatchMessage("You moved in sector "+letter+" "+number);
                            break;
                        }
                        else 
                            clientInterface.dispatchMessage("You can't move there!");
                    } else {
                        if(playerInList.needSectorNoise())
                            clientInterface.dispatchMessage("You need to specify a sector where make a noise");
                        if(playerInList.hasFourCard())
                            clientInterface.dispatchMessage("You need to specify what you want to di with the card in excess");
                        if(playerInList.hasMoved())
                            clientInterface.dispatchMessage("You have already moved!");
                    }
                }
            } 
        } catch (RemoteException e){
            System.err.println(ERROR_MESSAGE);
        }  
    }

    /**
     * Moves the player and makes him attack in the specified sector if he can go there
     */
    @Override
    public void moveAndAttack(RMIClientInterface clientInterface, String id, int letter, int number) {
        
        ServerStatus serverStatus = ServerStatus.getInstance();

        Match match = serverStatus.getIdMatchMap().get(id);
        
        try{
            if(checkIdIfPresent(id)){
                clientInterface.dispatchMessage(ERROR_NOTINGAME);
                return;
            }
            
            if(!(match.getMatchState()==GameState.RUNNING)){
                clientInterface.dispatchMessage(ERROR_NOTSTARTEDYET);
                return;
            }
                
            if(!match.getCurrentPlayer().getName().equalsIgnoreCase(id)){
                clientInterface.dispatchMessage(ERROR_NOTYOURTURN);
                return;
            }
                        
            if(letter<0 || letter>=23 || number <0 || number >=14){
                clientInterface.dispatchMessage("Move sintax: move letter number. The letter can go from A to W, the number from 1 to 14.");
                return;
            }
        } catch (RemoteException e){
            System.err.println(ERROR_MESSAGE);
        }
        
        Sector[][] sector = match.getMap().getSector();
        
        try{
            for (Player playerInList : match.getPlayers()) {
                if(playerInList.getName().equals(id)){
                    if(!(playerInList.needSectorNoise() || playerInList.hasFourCard() || playerInList.hasMoved())){
                        if(match.getGameLogic().validMove(playerInList, sector[letter][number])){
                            match.getGameLogic().movePlayerAndAttack(playerInList, sector[letter][number]);
                            clientInterface.dispatchMessage("You moved and attacked in sector "+letter+" "+number);
                            break;
                        }
                        else 
                            clientInterface.dispatchMessage("You can't move there!");
                    } else {
                        if(playerInList.needSectorNoise())
                            clientInterface.dispatchMessage("You need to specify a sector where make a noise");
                        if(playerInList.hasFourCard())
                            clientInterface.dispatchMessage("You need to specify what you want to di with the card in excess");
                        if(playerInList.hasMoved())
                            clientInterface.dispatchMessage("You have already moved!");
                    }
                }
            } 
        } catch (RemoteException e){
            System.err.println(ERROR_MESSAGE);
        } 
    }

    /**
     * Makes the client use a specified card
     */
    @Override
    public void useCard(RMIClientInterface clientInterface, String id, String cardUsed, int letter, int number) {
        
        ServerStatus serverStatus = ServerStatus.getInstance();

        Match match = serverStatus.getIdMatchMap().get(id);
        
        try{
            if(checkIdIfPresent(id)){
                clientInterface.dispatchMessage(ERROR_NOTINGAME);
                return;
            }
            
            if(!(match.getMatchState()==GameState.RUNNING)){
                clientInterface.dispatchMessage(ERROR_NOTSTARTEDYET);
                return;
            }
                
            if(!match.getCurrentPlayer().getName().equalsIgnoreCase(id)){
                clientInterface.dispatchMessage(ERROR_NOTYOURTURN);
                return;
            }
            
            for (Player playerInList : match.getPlayers()) {
                if(playerInList.getName().equals(id)){
                    if(!(playerInList instanceof Human)){
                        clientInterface.dispatchMessage("You are an alien! You can't use Item Cards!");
                        return;
                    }
                    if(playerInList.needSectorNoise()){
                        clientInterface.dispatchMessage("You need to specify a sector where make a noise");
                        return;
                    }
                }
            }
                        
        } catch (RemoteException e){
            System.err.println(ERROR_MESSAGE);
        }
        
        try{
            switch (cardUsed) {
            
            case "adrenaline":
                for (Player playerInList : match.getPlayers()) {
                    if(playerInList.getName().equals(id)){
                        Card card = new AdrenalineCard();
                        if(match.getGameLogic().hasCard(playerInList, card)){
                            match.getGameLogic().useItemCard(playerInList, card);
                            clientInterface.dispatchMessage("You used the Adrenaline card!");
                        }
                        else 
                            clientInterface.dispatchMessage(ERROR_CANTUSECARD);
                    }  
                }
                
                break;
           
            case "attack":            
                clientInterface.dispatchMessage("You can not use the Attack card!");
                break;
            
            case "defense":
                clientInterface.dispatchMessage("You can not use the Defense card!");
                break;
                
            case "sedatives":
                for (Player playerInList : match.getPlayers()) {
                    if(playerInList.getName().equals(id)){
                        Card card = new SedativesCard();
                        if(match.getGameLogic().hasCard(playerInList, card)) {
                            match.getGameLogic().useItemCard(playerInList, card);
                            clientInterface.dispatchMessage("You used the Sedatives card!");
                        }
                        else 
                            clientInterface.dispatchMessage(ERROR_CANTUSECARD);
                    }
                }
                break;
                
            case "spotlight":
                
                if(letter == 0 || number == 0){
                    clientInterface.dispatchMessage(ERROR_SPOTLIGHTSYNTAX);
                    return;
                }
                                
                if(letter<0 || letter>=23 || number <0 || number >=14){
                    clientInterface.dispatchMessage(ERROR_SPOTLIGHTSYNTAX);
                    return;
                }
                
                for (Player playerInList : match.getPlayers()) {
                    if(playerInList.getName().equals(id)){
                        Card card = new SpotlightCard();
                        if(match.getGameLogic().hasCard(playerInList, card)) {
                            match.getGameLogic().useSpotlight(letter, number);
                            clientInterface.dispatchMessage("You used the Spotlight card!");
                        }
                        else
                            clientInterface.dispatchMessage(ERROR_CANTUSECARD);
                    }
                }
                
                break;
                
            case "teleport":
                for (Player playerInList : match.getPlayers()) {
                    if(playerInList.getName().equals(id)){
                        Card card = new TeleportCard();
                        if(match.getGameLogic().hasCard(playerInList, card)) {
                            match.getGameLogic().useItemCard(playerInList, card);
                            clientInterface.dispatchMessage("You used the Teleport card!");
                        }
                        else
                            clientInterface.dispatchMessage(ERROR_CANTUSECARD);
                    }
                }
                break;

            default:
                clientInterface.dispatchMessage("Use sintax: use cardname. Available card names are: Adrenaline, Attack, Sedatives, Spotlight, Teleport");
                break;
            }
        } catch (RemoteException e){
            System.err.println(ERROR_MESSAGE);
        }
    }

    /**
     * Make a noise in a sector when the player needs to do that and letter and number of the sector are specified 
     */
    @Override
    public void makeNoise(RMIClientInterface clientInterface, String id, int letter, int number) {
        
        ServerStatus serverStatus = ServerStatus.getInstance();

        Match match = serverStatus.getIdMatchMap().get(id);
        
        try{
            if(checkIdIfPresent(id)){
                clientInterface.dispatchMessage(ERROR_NOTINGAME);
                return;
            }
            
            if(!(match.getMatchState()==GameState.RUNNING)){
                clientInterface.dispatchMessage(ERROR_NOTSTARTEDYET);
                return;
            }
                
            if(!match.getCurrentPlayer().getName().equalsIgnoreCase(id)){
                clientInterface.dispatchMessage(ERROR_NOTYOURTURN);
                return;
            }
        } catch (RemoteException e){
            System.err.println(ERROR_MESSAGE);
        }
        
        try{
            for (Player playerInList : match.getPlayers()) {
                if(playerInList.getName().equals(id))
                    if(playerInList.needSectorNoise()){
                        match.getGameLogic().makeANoise(letter, number);
                        playerInList.setNeedSectorNoise(false);
                    } else {
                        clientInterface.dispatchMessage("No need to make a noise");
                        return;
                    }
            }
            
            clientInterface.dispatchMessage("Noise done");
            
        } catch (RemoteException e){
            System.err.println(ERROR_MESSAGE);
        }
        
    }

    /**
     * Makes the player discard a card. This can occur when the player has more than 3 cards in his hand
     */
    @Override
    public void discardCard(RMIClientInterface clientInterface, String id, String cardDiscarded) {
        
        ServerStatus serverStatus = ServerStatus.getInstance();

        Match match = serverStatus.getIdMatchMap().get(id);
        
        try{
            if(checkIdIfPresent(id)){
                clientInterface.dispatchMessage(ERROR_NOTINGAME);
                return;
            }
            
            if(!(match.getMatchState()==GameState.RUNNING)){
                clientInterface.dispatchMessage(ERROR_NOTSTARTEDYET);
                return;
            }
                
            if(!match.getCurrentPlayer().getName().equalsIgnoreCase(id)){
                clientInterface.dispatchMessage(ERROR_NOTYOURTURN);
                return;
            }
        } catch (RemoteException e){
            System.err.println(ERROR_MESSAGE);
        }
        
        try {
            switch (cardDiscarded) {
            
            case "adrenaline":
                for (Player playerInList : match.getPlayers()) {
                    if(playerInList.getName().equals(id)){
                        Card card = new AdrenalineCard();
                        if(match.getGameLogic().hasCard(playerInList, card)){
                            match.getGameLogic().discardItemCard(playerInList, card);
                            clientInterface.dispatchMessage("You discarded the Adrenaline card!");
                        }
                        else
                            clientInterface.dispatchMessage("You don't have an Adrenlaine card!");
                    }
                }
                break;
                
            case "attack":
                for (Player playerInList : match.getPlayers()) {
                    if(playerInList.getName().equals(id)){
                        Card card = new AttackCard();
                        if(match.getGameLogic().hasCard(playerInList, card)){
                            match.getGameLogic().discardItemCard(playerInList, card);
                            clientInterface.dispatchMessage("You discarded the Attack card!");
                        }
                        else
                            clientInterface.dispatchMessage("You don't have an Attack card!");
                    }
                }
                
                break;
            
            case "defense":
                for (Player playerInList : match.getPlayers()) {
                    if(playerInList.getName().equals(id)){
                        Card card = new DefenseCard();
                        if(match.getGameLogic().hasCard(playerInList, card)){
                            match.getGameLogic().discardItemCard(playerInList, card);
                            clientInterface.dispatchMessage("You discarded the Defense card!");
                        }
                        else
                            clientInterface.dispatchMessage("You don't have an Attack card!");
                    }
                }

                break;
                
            case "sedatives":
                for (Player playerInList : match.getPlayers()) {
                    if(playerInList.getName().equals(id)){
                        Card card = new SedativesCard();
                        if(match.getGameLogic().hasCard(playerInList, card)){
                            match.getGameLogic().discardItemCard(playerInList, card);
                            clientInterface.dispatchMessage("You discarded the Sedatives card!");
                        }
                        else
                            clientInterface.dispatchMessage("You don't have a Defence card!");
                    }
                }
                break;
            
            case "spotlight":
                for (Player playerInList : match.getPlayers()) {
                    if(playerInList.getName().equals(id)){
                        Card card = new SpotlightCard();
                        if(match.getGameLogic().hasCard(playerInList, card)){
                            match.getGameLogic().discardItemCard(playerInList, card);
                            clientInterface.dispatchMessage("You discarded the Spotlight card!");
                        }
                        else
                            clientInterface.dispatchMessage("You don't have a Defence card!");
                    }
                }
                break;
                
            case "teleport":
                for (Player playerInList : match.getPlayers()) {
                    if(playerInList.getName().equals(id)){
                        Card card = new TeleportCard();
                        if(match.getGameLogic().hasCard(playerInList, card)){
                            match.getGameLogic().discardItemCard(playerInList, card);
                            clientInterface.dispatchMessage("You discarded the Teleport card!");
                        }
                        else
                            clientInterface.dispatchMessage("You don't have a Defence card!");
                    }
                }
                break;
                
            default:
                clientInterface.dispatchMessage("That card doens't exist!");
            }
            
        } catch (RemoteException e){
            System.err.println(ERROR_MESSAGE);
        }
        
    }

    /**
     * Makes the player end his turn
     */
    @Override
    public void endTurn(RMIClientInterface clientInterface, String id) {
        
        ServerStatus serverStatus = ServerStatus.getInstance();

        Match match = serverStatus.getIdMatchMap().get(id);
        
        try{
            if(checkIdIfPresent(id)){
                clientInterface.dispatchMessage(ERROR_NOTINGAME);
                return;
            }
            
            if(!(match.getMatchState()==GameState.RUNNING)){
                clientInterface.dispatchMessage(ERROR_NOTSTARTEDYET);
                return;
            }
                
            if(!match.getCurrentPlayer().getName().equalsIgnoreCase(id)){
                clientInterface.dispatchMessage(ERROR_NOTYOURTURN);
                return;
            }
        } catch (RemoteException e){
            System.err.println(ERROR_MESSAGE);
        }
        
        try {
            for (Player playerInList : match.getPlayers()) {
                if(playerInList.getName().equals(id)){
                    if(playerInList.needSectorNoise()){
                        clientInterface.dispatchMessage("You need to specify a sector where make a noise");
                        return;
                    }
                    if(playerInList.hasFourCard()){
                        clientInterface.dispatchMessage("You have four cards! You must discard one");
                        return;
                    }
                    if(playerInList.hasMoved()){
                        match.getGameLogic().endTurn();
                        clientInterface.dispatchMessage("Your turn has ended");
                    }
                    else
                        clientInterface.dispatchMessage("You need to move before ending your turn!");
                }
            }
        } catch (RemoteException e){
            System.err.println(ERROR_MESSAGE);
        }
    }

    @Override
    public void getCards(RMIClientInterface clientInterface, String id) throws RemoteException {
        ServerStatus serverStatus = ServerStatus.getInstance();
        
        Match match = serverStatus.getIdMatchMap().get(id);
        
        try{
            if(checkIdIfPresent(id)){
                clientInterface.dispatchMessage(ERROR_NOTINGAME);
                return;
            }
            
            if(!(match.getMatchState()==GameState.RUNNING)){
                clientInterface.dispatchMessage(ERROR_NOTSTARTEDYET);
                return;
            }
        } catch (RemoteException e){
            System.err.println(ERROR_MESSAGE);
        }
        
        List<Card> cards = new ArrayList<Card>();
        
        for (Player playerInList : match.getPlayers()) {
            if(playerInList.getName().equals(id) && !playerInList.getCards().isEmpty()){
                cards=playerInList.getCards();
            }
            else
                clientInterface.dispatchMessage("You don't have any card");
        }
        
        for(Card playerCard : cards){
            clientInterface.dispatchMessage(playerCard.toString());
        }       
    }

    /**
     * Makes the player send a message to all players in the match
     */
    @Override
    public void chat(RMIClientInterface clientInterface, String id, String msg) throws RemoteException {
        ServerStatus serverStatus = ServerStatus.getInstance();
        
        Match match = serverStatus.getIdMatchMap().get(id);
        
        try{
            if(checkIdIfPresent(id)){
                clientInterface.dispatchMessage(ERROR_NOTINGAME);
                return;
            }

        } catch (RemoteException e){
            System.err.println(ERROR_MESSAGE);
        }
        
        match.getGameLogic().chat(id, msg);
        
    }
}
