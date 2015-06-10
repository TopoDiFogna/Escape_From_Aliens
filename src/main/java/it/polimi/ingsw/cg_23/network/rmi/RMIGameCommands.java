package it.polimi.ingsw.cg_23.network.rmi;

import java.rmi.RemoteException;

import it.polimi.ingsw.cg_23.model.cards.Card;
import it.polimi.ingsw.cg_23.model.cards.SpotlightCard;
import it.polimi.ingsw.cg_23.model.map.Sector;
import it.polimi.ingsw.cg_23.model.players.Human;
import it.polimi.ingsw.cg_23.model.players.Player;
import it.polimi.ingsw.cg_23.model.status.GameState;
import it.polimi.ingsw.cg_23.model.status.Match;
import it.polimi.ingsw.cg_23.network.ServerStatus;

public class RMIGameCommands implements RMIGameCommandsInterface {
    
    private static final String messageError = "Cannot send message to client!";
    private String notInGame = "You are not in a game! Join one first!";    
    private String notStartedYet = "Game has not started yet!";
    private String notYourTurn = "It's not your turn!";
    private String cantUseCard = "You can't use that card!";
    

    public RMIGameCommands() {
        // the constructor is void, no need to instantiate variables
    }
    
    private boolean checkIdIfPresent(String id){
        ServerStatus serverStatus = ServerStatus.getInstance();

        if(!serverStatus.getIdMatchMap().containsKey(id))
            return true;
        return false;
    }

    @Override
    public void movePlayer(RMIClientInterface clientInterface, String id, int letter, int number) {
        
        ServerStatus serverStatus = ServerStatus.getInstance();
        
        Match match = serverStatus.getIdMatchMap().get(id);
        
        try{
            if(checkIdIfPresent(id)){
                clientInterface.dispatchMessage(notInGame);
                return;
            }
            
            if(!(match.getMatchState()==GameState.RUNNING)){
                clientInterface.dispatchMessage(notStartedYet);
                return;
            }
                
            if(!match.getCurrentPlayer().getName().equalsIgnoreCase(id)){
                clientInterface.dispatchMessage(notYourTurn);
                return;
            }
                        
            if(letter<0 || letter>=23 || number <0 || number >=14){
                clientInterface.dispatchMessage("Move sintax: move letter number. The letter can go from A to W, the number from 1 to 14.");
                return;
            }
        } catch (RemoteException e){
            System.err.println(messageError);
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
            System.err.println(messageError);
        }  
    }

    @Override
    public void moveAndAttack(RMIClientInterface clientInterface, String id, int letter, int number) {
        
        ServerStatus serverStatus = ServerStatus.getInstance();

        Match match = serverStatus.getIdMatchMap().get(id);
        
        try{
            if(checkIdIfPresent(id)){
                clientInterface.dispatchMessage(notInGame);
                return;
            }
            
            if(!(match.getMatchState()==GameState.RUNNING)){
                clientInterface.dispatchMessage(notStartedYet);
                return;
            }
                
            if(!match.getCurrentPlayer().getName().equalsIgnoreCase(id)){
                clientInterface.dispatchMessage(notYourTurn);
                return;
            }
                        
            if(letter<0 || letter>=23 || number <0 || number >=14){
                clientInterface.dispatchMessage("Move sintax: move letter number. The letter can go from A to W, the number from 1 to 14.");
                return;
            }
        } catch (RemoteException e){
            System.err.println(messageError);
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
            System.err.println(messageError);
        } 
    }

    @Override
    public void useCard(RMIClientInterface clientInterface, String id, Card card, int letter, int number) {
        
        ServerStatus serverStatus = ServerStatus.getInstance();

        Match match = serverStatus.getIdMatchMap().get(id);
        
        try{
            if(checkIdIfPresent(id)){
                clientInterface.dispatchMessage(notInGame);
                return;
            }
            
            if(!(match.getMatchState()==GameState.RUNNING)){
                clientInterface.dispatchMessage(notStartedYet);
                return;
            }
                
            if(!match.getCurrentPlayer().getName().equalsIgnoreCase(id)){
                clientInterface.dispatchMessage(notYourTurn);
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
            System.err.println(messageError);
        }
        
        try{
            for (Player playerInList : match.getPlayers()) {
                if(playerInList.getName().equals(id)){
                    if(card.getClass()!= SpotlightCard.class && match.getGameLogic().hasCard(playerInList, card)){
                        match.getGameLogic().useItemCard(playerInList, card);
                        clientInterface.dispatchMessage("You used the Adrenaline card!");//TODO card to string
                        return;
                    }
                    else if(card.getClass()== SpotlightCard.class && match.getGameLogic().hasCard(playerInList, card)){
                        match.getGameLogic().useSpotlight(letter, number);
                        clientInterface.dispatchMessage("You used the Spotlight card!");
                        return;
                    }
                    else {
                        clientInterface.dispatchMessage(cantUseCard);
                        return;
                    }
                }  
            }
        } catch (RemoteException e){
            System.err.println(messageError);
        }
    }

    @Override
    public void makeNoise(RMIClientInterface clientInterface, String id, int letter, int number) {
        
        ServerStatus serverStatus = ServerStatus.getInstance();

        Match match = serverStatus.getIdMatchMap().get(id);
        
        try{
            if(checkIdIfPresent(id)){
                clientInterface.dispatchMessage(notInGame);
                return;
            }
            
            if(!(match.getMatchState()==GameState.RUNNING)){
                clientInterface.dispatchMessage(notStartedYet);
                return;
            }
                
            if(!match.getCurrentPlayer().getName().equalsIgnoreCase(id)){
                clientInterface.dispatchMessage(notYourTurn);
                return;
            }
        } catch (RemoteException e){
            System.err.println(messageError);
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
            System.err.println(messageError);
        }
        
    }

    @Override
    public void discardCard(RMIClientInterface clientInterface, String id, Card card) {
        
        ServerStatus serverStatus = ServerStatus.getInstance();

        Match match = serverStatus.getIdMatchMap().get(id);
        
        try{
            if(checkIdIfPresent(id)){
                clientInterface.dispatchMessage(notInGame);
                return;
            }
            
            if(!(match.getMatchState()==GameState.RUNNING)){
                clientInterface.dispatchMessage(notStartedYet);
                return;
            }
                
            if(!match.getCurrentPlayer().getName().equalsIgnoreCase(id)){
                clientInterface.dispatchMessage(notYourTurn);
                return;
            }
        } catch (RemoteException e){
            System.err.println(messageError);
        }
        
        try {
            for (Player playerInList : match.getPlayers()) {
                if(playerInList.getName().equals(id)){
                    if(match.getGameLogic().hasCard(playerInList, card)){
                        match.getGameLogic().discardItemCard(playerInList, card);
                    }
                }
            }
            clientInterface.dispatchMessage("You discarded the Adrenaline card!");
            
        } catch (RemoteException e){
            System.err.println(messageError);
        }
        
    }

    @Override
    public void endTurn(RMIClientInterface clientInterface, String id) {
        
        ServerStatus serverStatus = ServerStatus.getInstance();

        Match match = serverStatus.getIdMatchMap().get(id);
        
        try{
            if(checkIdIfPresent(id)){
                clientInterface.dispatchMessage(notInGame);
                return;
            }
            
            if(!(match.getMatchState()==GameState.RUNNING)){
                clientInterface.dispatchMessage(notStartedYet);
                return;
            }
                
            if(!match.getCurrentPlayer().getName().equalsIgnoreCase(id)){
                clientInterface.dispatchMessage(notYourTurn);
                return;
            }
        } catch (RemoteException e){
            System.err.println(messageError);
        }
        
        try {
            for (Player playerInList : match.getPlayers()) {
                if(playerInList.getName().equals(id)){
                    if(playerInList.hasMoved()){
                        match.getGameLogic().endTurn();
                        clientInterface.dispatchMessage("Your turn has ended");
                    }
                    else
                        clientInterface.dispatchMessage("You need to move before ending your turn!");
                }
            }
        } catch (RemoteException e){
            System.err.println(messageError);
        }
        
    }


}
