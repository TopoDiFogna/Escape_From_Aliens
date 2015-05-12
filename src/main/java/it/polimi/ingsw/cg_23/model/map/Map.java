package it.polimi.ingsw.cg_23.model.map;

import it.polimi.ingsw.cg_23.model.map.SectorAlien;
import it.polimi.ingsw.cg_23.model.map.SectorDangerous;
import it.polimi.ingsw.cg_23.model.map.SectorEscapeHatch;
import it.polimi.ingsw.cg_23.model.map.SectorHuman;
import it.polimi.ingsw.cg_23.model.map.SectorSecure;
import it.polimi.ingsw.cg_23.model.map.SectorVoid;

/**
 * Description of Map. The map is where the game is played.
 * It's divided in sectors and has a maximum fixed size.
 *  
 * @author Paolo
 */
public class Map {
    /**
     * Maximum columns in the map.
     */
    private final static int SIZENUMBER = 23;

    /**
     * Maximum rows in the map.
     */
    private final static int SIZELETTER = 14;
    
    /**
     * Description of the property sectorDangerous.
     */
    private final SectorDangerous sectorDangerous = null;

    /**
     * Description of the property sectorSecure.
     */
    private final SectorSecure sectorSecure = null;

    /**
     * Description of the property sectorAlien.
     */
    private final SectorAlien sectorAlien = null;

    /**
     * Description of the property sectorVoid.
     */
    private SectorVoid sectorVoid = null;

    /**
     * Description of the property sectorHuman.
     */
    private final SectorHuman sectorHuman = null;

    /**
     * Description of the property sectorEscapeHatch.
     */
    private final SectorEscapeHatch sectorEscapeHatch = null;

    /**
     * Sectors of the map: rows x columns
     * 
     */
    private Sector[][] sectors = new Sector[SIZENUMBER][SIZELETTER];

    /**
     * The constructor.
     */
    public Map(Sector[][] sectors) {
        this.sectors=sectors;
    }
    /**
     * Returns max rows of the map.
     * 
     * @return SIZENUMBER max rows of the map
     */
    public int getSIZENUMBER() {
        return SIZENUMBER;
    }
    
    /**
     * Returns max colums of the map.
     * 
     * @return SIZELETTER max colums of the map
     */
    public int getSIZELETTER() {
        return SIZELETTER;
    }
    
    /**
     * Returns the map as a array[SIZENUMBER]x[SIZELETTER].
     * 
     * @return sectors
     */
    public Sector[][] getSector() {
        return this.sectors;
    }

    /**
     * Returns sectorDangerous.
     * 
     * @return sectorDangerous
     */
    public SectorDangerous getSectorDangerous() {
        return this.sectorDangerous;
    }

    /**
     * Returns sectorSecure.
     * 
     * @return sectorSecure
     */
    public SectorSecure getSectorSecure() {
        return this.sectorSecure;
    }

    /**
     * Returns sectorAlien.
     * 
     * @return sectorAlien
     */
    public SectorAlien getSectorAlien() {
        return this.sectorAlien;
    }

    /**
     * Returns sectorVoid.
     * 
     * @return sectorVoid
     */
    public SectorVoid getSectorVoid() {
        return this.sectorVoid;
    }

    /**
     * Sets a value to attribute sectorVoid.
     * 
     * @param newSectorVoid
     */
    public void setSectorVoid(SectorVoid newSectorVoid) {
        this.sectorVoid = newSectorVoid;
    }

    /**
     * Returns sectorHuman.
     * 
     * @return sectorHuman
     */
    public SectorHuman getSectorHuman() {
        return this.sectorHuman;
    }

    /**
     * Returns sectorEscapeHatch.
     * 
     * @return sectorEscapeHatch
     */
    public SectorEscapeHatch getSectorEscapeHatch() {
        return this.sectorEscapeHatch;
    }

}
