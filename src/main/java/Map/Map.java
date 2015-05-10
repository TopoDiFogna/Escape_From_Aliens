/*******************************************************************************
 * 2015, All rights reserved.
 *******************************************************************************/
package Map;

import Map.SectorAlien;
import Map.SectorDangerous;
import Map.SectorEscapeHatch;
import Map.SectorHuman;
import Map.SectorSecure;
import Map.SectorVoid;
// Start of user code (user defined imports)

// End of user code

/**
 * Description of Map.
 * 
 * @author Arianna
 */
public class Map {
    /**
     * Description of the property SIZENUMBER.
     */
    private final static int SIZENUMBER = 0;

    /**
     * Description of the property sectorDangerous.
     */
    private final SectorDangerous sectorDangerous = null;

    /**
     * Description of the property SIZELETTER.
     */
    private final static int SIZELETTER = 0;

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

    private Sector sectors;

    // Start of user code (user defined attributes for Map)

    // End of user code

    /**
     * The constructor.
     */
    public Map() {
        // Start of user code constructor for Map)
        super();
        // End of user code
    }

    // Start of user code (user defined methods for Map)

    // End of user code
    /**
     * Returns SIZENUMBER.
     * 
     * @return SIZENUMBER
     */
    public static int getSIZENUMBER() {
        return SIZENUMBER;
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
     * Returns SIZELETTER.
     * 
     * @return SIZELETTER
     */
    public static int getSIZELETTER() {
        return SIZELETTER;
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
     * Returns sectors.
     * 
     * @return sectors
     */
    public Sector getSector() {
        return this.sectors;
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
