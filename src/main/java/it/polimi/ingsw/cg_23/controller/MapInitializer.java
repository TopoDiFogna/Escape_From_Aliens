package it.polimi.ingsw.cg_23.controller;

import it.polimi.ingsw.cg_23.controller.parser.XMLParser;
import it.polimi.ingsw.cg_23.model.map.Sector;

public class MapInitializer {
    
    private MapInitializer(){
        //no need to create an object of this type
    }

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
        for(int letter=0;letter<sizeLetter; letter++){
            for(int number=0;number<sizeNumber;number++){
                
                if(number>0 && number<sizeNumber-1){
                    map[letter][number].addNeighbors(map[letter][number-1]);
                    map[letter][number].addNeighbors(map[letter][number+1]);
                }
                
                if(number==0) 
                    map[letter][number].addNeighbors(map[letter][number+1]);
                
                if(number==sizeNumber-1) 
                    map[letter][number].addNeighbors(map[letter][number-1]);

                if(letter%2 == 1){//if the column is odd
                    if(letter != 0 && number != 0 && number!=sizeNumber-1 && letter!=sizeLetter-1){//sector with all neighbors
                        map[letter][number].addNeighbors(map[letter+1][number]);
                        map[letter][number].addNeighbors(map[letter+1][number+1]);  
                        map[letter][number].addNeighbors(map[letter-1][number]);
                        map[letter][number].addNeighbors(map[letter-1][number+1]);
                    }
                    else if(number == 0){//top sector
                        map[letter][number].addNeighbors(map[letter+1][number]);
                        map[letter][number].addNeighbors(map[letter+1][number+1]);
                        map[letter][number].addNeighbors(map[letter-1][number]);
                        map[letter][number].addNeighbors(map[letter-1][number+1]);
                    }
                    else if(number == sizeNumber-1){//bottom sector
                        map[letter][number].addNeighbors(map[letter-1][number]);
                        map[letter][number].addNeighbors(map[letter+1][number]);
                    }
                }
                
                if(letter%2 == 0){//if the column is even
                    if(letter != 0 && number != 0 && number!=sizeNumber-1 && letter!=sizeLetter-1){//sector with all neighbors
                        map[letter][number].addNeighbors(map[letter+1][number-1]);
                        map[letter][number].addNeighbors(map[letter+1][number]);
                        map[letter][number].addNeighbors(map[letter-1][number-1]);
                        map[letter][number].addNeighbors(map[letter-1][number]);
                
                    }
                    else if(letter != 0 && letter != sizeLetter-1 && number == 0){//top sector
                        map[letter][number].addNeighbors(map[letter-1][number]);
                        map[letter][number].addNeighbors(map[letter+1][number]);
                    }
                    else if(letter != 0 && letter != sizeLetter-1 && number == sizeNumber-1){//bottom sector
                        map[letter][number].addNeighbors(map[letter+1][number-1]);
                        map[letter][number].addNeighbors(map[letter+1][number]);
                        map[letter][number].addNeighbors(map[letter-1][number-1]);
                        map[letter][number].addNeighbors(map[letter-1][number]);
                    }
                    
                    else if(letter == 0 && number!=0 && number != sizeNumber-1){//left side
                        map[letter][number].addNeighbors(map[letter+1][number-1]); 
                        map[letter][number].addNeighbors(map[letter+1][number]); 
                    }      
                    else if(letter == sizeLetter-1 && number!=0 && number != sizeNumber-1){//right side
                        map[letter][number].addNeighbors(map[letter-1][number-1]); 
                        map[letter][number].addNeighbors(map[letter-1][number]); 
                    } 
                    else if(letter == 0 && number == 0){//top left sector
                        map[letter][number].addNeighbors(map[letter+1][number]);                       
                    }
                    else if(letter == sizeLetter-1 && number == 0){//top right sector
                        map[letter][number].addNeighbors(map[letter-1][number]);                       
                    }
                    else if(letter == 0 && number == sizeNumber-1){//bottom left sector
                        map[letter][number].addNeighbors(map[letter+1][number-1]);
                        map[letter][number].addNeighbors(map[letter+1][number]);
                    }
                    else if(letter == sizeLetter-1 && number == sizeNumber-1){//bottom right sector
                        map[letter][number].addNeighbors(map[letter-1][number-1]);
                        map[letter][number].addNeighbors(map[letter-1][number]);
                    }
                }
            }
        }
    }
    
    

}
