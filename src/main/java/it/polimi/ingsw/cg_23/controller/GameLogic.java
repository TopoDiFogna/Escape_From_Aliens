package it.polimi.ingsw.cg_23.controller;

import it.polimi.ingsw.cg_23.model.map.Sector;
import it.polimi.ingsw.cg_23.model.map.SectorTypeEnum;
import it.polimi.ingsw.cg_23.model.players.Alien;
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
}
