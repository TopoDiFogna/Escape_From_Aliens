package it.polimi.ingsw.cg_23.controller;

import it.polimi.ingsw.cg_23.controller.parser.XMLParser;
import it.polimi.ingsw.cg_23.model.map.Sector;

public class MapInitializer {

    /**
     * Creates the map calling the Xml parser
     * 
     * @param name name of the map, is the filename without .xml
     * @param sizeLetter maximum number of columns 
     * @param sizeNumber maximum number of rows
     * @return
     */ 
    public static Sector[][] createMap(String name, int sizeLetter, int sizeNumber){
        XMLParser parser = new XMLParser();
        return parser.createMap(name, sizeLetter, sizeNumber);
    }
    
    
    /**
     * Creates the list of sectors near the current sector.
     * 
     * @param sizeLetter maximum number of columns in the map
     * @param sizeNumber maximum number of rows in the map
     * @param map used to retrieve sectors
     */
    public static void createNeighbors(int sizeLetter, int sizeNumber, Sector[][] map){
        for(int letter=1;letter<=sizeLetter; letter++){
            for(int number=1;number<=sizeNumber;number++){
                
                if(number>1 && number<sizeNumber){
                    map[letter][number].addNeighbor(map[letter][number-1]);
                    map[letter][number].addNeighbor(map[letter][number+1]);
                }
                
                if(number==1) map[letter][number].addNeighbor(map[letter][number+1]);
                
                if(number==sizeNumber) map[letter][number].addNeighbor(map[letter][number-1]);

                //if the column is even
                if(letter%2 == 0){ //sector with all neighbors
                    if(letter != 1 && number != sizeNumber){
                        map[letter][number].addNeighbor(map[letter+1][number]);
                        map[letter][number].addNeighbor(map[letter+1][number+1]);  
                        map[letter][number].addNeighbor(map[letter-1][number]);
                        map[letter][number].addNeighbor(map[letter-1][number+1]);
                    }
                    else if(number == 1){//sector with no top sector
                        map[letter][number].addNeighbor(map[letter+1][number]);
                        map[letter][number].addNeighbor(map[letter+1][number+1]);
                        map[letter][number].addNeighbor(map[letter-1][number]);
                        map[letter][number].addNeighbor(map[letter-1][number+1]);
                    }
                    else if(number == sizeNumber){
                        map[letter][number].addNeighbor(map[letter-1][number]);
                        map[letter][number].addNeighbor(map[letter+1][number]);
                    }
                }
                
                if(letter%2 == 1){//if the column is odd
                    if(letter != 1 && number != 1){//sector with all neighbor
                        map[letter][number].addNeighbor(map[letter+1][number-1]);
                        map[letter][number].addNeighbor(map[letter+1][number]);
                        map[letter][number].addNeighbor(map[letter-1][number-1]);
                        map[letter][number].addNeighbor(map[letter-1][number]);
                
                    }
                    else if(letter != 1 && letter != sizeLetter && number == 1){//top sector
                        map[letter][number].addNeighbor(map[letter-1][number]);
                        map[letter][number].addNeighbor(map[letter+1][number]);
                    }
                    else if(letter != 1 && letter != sizeLetter && number == sizeNumber){//bottom sector
                        map[letter][number].addNeighbor(map[letter+1][number-1]);
                        map[letter][number].addNeighbor(map[letter+1][number]);
                        map[letter][number].addNeighbor(map[letter-1][number-1]);
                        map[letter][number].addNeighbor(map[letter-1][number]);
                    }
                    
                    else if(letter == 1 && number == 1){//top left sector
                        map[letter][number].addNeighbor(map[letter+1][number]);                       
                    }
                    else if(letter == sizeLetter && number == 1){//top right sector
                        map[letter][number].addNeighbor(map[letter-1][number]);                       
                    }
                    else if(letter == 1 && number == sizeNumber){//bottom left sector
                        map[letter][number].addNeighbor(map[letter+1][number-1]);
                        map[letter][number].addNeighbor(map[letter+1][number]);
                    }
                    else if(letter == sizeLetter && number == sizeNumber){//bottom right sector
                        map[letter][number].addNeighbor(map[letter-1][number-1]);
                        map[letter][number].addNeighbor(map[letter-1][number]);
                    }
                }
            }
        }
    }
    
    

}
