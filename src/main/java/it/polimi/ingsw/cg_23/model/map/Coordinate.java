package it.polimi.ingsw.cg_23.model.map;

/**
 * The Coordinate of the every sector in the map.
 *  
 * @author Paolo
 */
public class Coordinate {
    /**
     * First part of the coordinate identifier. <br>
     * Letter of the coordinate; we use number so it's simpler to do math calculation.
     */
    private final int letter;

    /**
     * Second part of the coordinate identifier. <br>
     * Number part of the coordinate.
     */
    private final int number;

    /**
     * The constructor.
     */
    public Coordinate(int letter, int number) {
        this.letter=letter;
        this.number=number;
    }
    /**
     * Returns the first identifier of the coordinate.<br>
     * It's codified as an int.
     * 
     * @return letter int that is the second identifier of the coordinate
     */
    public int getLetter() {
        return this.letter;
    }
    
    /**
     * Returns the second identifier of the coordinate.
     * 
     * @return number int that is the second identifier of the coordinate
     */
    public int getNumber() {
        return this.number;
    }
}
