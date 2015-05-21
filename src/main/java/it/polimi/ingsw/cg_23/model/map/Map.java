package it.polimi.ingsw.cg_23.model.map;

import it.polimi.ingsw.cg_23.controller.MapInitializer;

/**
 * The map is where the game is played.
 * It's divided in sectors and has a maximum fixed size defined by SIZENUMBER and SIZELETTER.
 *  
 * @author Paolo
 */
public class Map {
    /**
     * Maximum columns in the map.
     */
    private final static int SIZENUMBER = 14;

    /**
     * Maximum rows in the map.
     */
    private final static int SIZELETTER = 23;

    /**
     * Sectors of the map: rows x columns
     * 
     */
    private Sector[][] sectors = new Sector[SIZENUMBER][SIZELETTER];

    /**
     * The Constructor. Not really used to create the map. we use another class for that.<br>
     * It calls a parser to pars the XML file that contain the map scheme which returns the map.
     * 
     */
    
    public Map(String name) {
        
        sectors=MapInitializer.createMap(name, SIZELETTER, SIZENUMBER);
        //MapController.createNeighbors(SIZELETTER, SIZENUMBER, sectors);//TODO implementation
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
}
