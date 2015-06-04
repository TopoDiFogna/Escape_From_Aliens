package it.polimi.ingsw.cg_23.controller.parser;

import it.polimi.ingsw.cg_23.model.map.Sector;
import it.polimi.ingsw.cg_23.model.map.SectorTypeEnum;

import java.io.IOException;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class XMLParser extends DefaultHandler{
    
    private static final Logger LOGGER = Logger.getLogger("EscapeFromAliensLogger");
    
    /**
     * The XML file converted to DOM
     */
    private Document dom;
    
    /**
     * Map codified as a two-dimensional array
     */
    private Sector[][] map;
    
    /**
     * Constructor of the parser. It's empty. no need to instantiate things.
     * 
     */
    public XMLParser(){
        //Just empty to create an object and call methods on that object
    }
    
    
    /**
     * Parse and returns the map as array of 
     * The xml must have the form (angular brackets removed):<br>
     * ?xml version="1.0" encoding="UTF-8"<br>
     * and then must contain a strict structure of tag:<br>
     *  map<br>
     *  ..sector<br>
     *  ....type void /type<br>
     *  ....letter 0 /letter(in number format)<br>
     *  ....number 0 /number<br>
     *  ....crossable false /crossable <br>
     *  ../sector<br>
     * /map<br>
     * 
     *  
     * @param name name of the map, must correspond to the name of the file
     * @param letterSize maximum number of rows of the map
     * @param numberSize maximum number of columns of the map
     * @return
     */
    public Sector[][] createMap(String name, int letterSize, int numberSize){
        
        map = new Sector[letterSize][numberSize];
        
        parseXmlFile(name+".xml");
        parseDocument();
        
        return map;
    }
    
    
    /**
     * Parses the XML file and calls getSector to create sector, then adds the sector to map array
     * 
     */
    private void parseDocument(){
        
        //get the root element
        Element docEle = dom.getDocumentElement();

        //get a nodelist of elements from the converted XML file
        NodeList nodeList = docEle.getElementsByTagName("sector");
        if(nodeList != null && nodeList.getLength() > 0) {
            for(int i=0; i < nodeList.getLength(); i++) {

                //get the employee element
                Element element = (Element)nodeList.item(i);

                //get the Employee object
                Sector sector = getSector(element);

                //add it to the map array
                map[sector.getLetter()-1][sector.getNumber()-1]=sector;
            }
        }
    }
    
    /**
     * Take a Sector element and read the values in, create
     * a Sector object and return it
     */
    private Sector getSector(Element sectorElement) {

        //for each <Sector> element get values of
        //letter ,number, type and crossable
        
        int letter = getIntValue(sectorElement,"letter");
        int number = getIntValue(sectorElement,"number");
        SectorTypeEnum type = getSectorTypeValue(sectorElement,"type");
        boolean crossable = getBooleanValue(sectorElement,"crossable");

        //Create a new Sector with the value read from the xml nodes
        return new Sector(letter, number, type, crossable);
    }


    /**
     * I take a xml element and the tag name, look for the tag and get
     * the text content
     * i.e for <employee><name>John</name></employee> xml snippet if
     * the Element points to employee node and tagName is 'name' I will return John
     * 
     * @param element the element of the xml file
     * @param tagName name of the tag needed
     * @return the value contained in the tag of the element as a String
     */
    private String getTextValue(Element element, String tagName) {
        String textValue = null;
        NodeList nodeList = element.getElementsByTagName(tagName);
        if(nodeList != null && nodeList.getLength() > 0) {
            Element el = (Element)nodeList.item(0);
            textValue = el.getFirstChild().getNodeValue();
        }

        return textValue;
    }


    /**
     * Calls getTextValue and returns a int value
     */
    private int getIntValue(Element ele, String tagName) {
        //TODO in production application you would catch the exception
        return Integer.parseInt(getTextValue(ele,tagName));
    }
    
    /**
     * Calls getTextValue and returns a boolean value
     */
    private boolean getBooleanValue(Element ele, String tagName) {
        //TODO in production application you would catch the exception
        return Boolean.parseBoolean(getTextValue(ele,tagName));
    }
    
    /**
     * Calls getTextValue and returns a SectorTypeEnum value
     */
    private SectorTypeEnum getSectorTypeValue(Element ele, String tagName) {
        //TODO in production application you would catch the exception
        String value = getTextValue(ele, tagName).toUpperCase();
        return SectorTypeEnum.valueOf(value);
    }



    /**
     * Parse the xml file to check if it's correct.
     * 
     * @param filename
     */
    private void parseXmlFile(String filename){
        //get the factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            //Using factory get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();

            //parse using builder to get DOM representation of the XML file
            dom = db.parse(filename);


        }catch(ParserConfigurationException pce) {
            LOGGER.throwing("XLMParser", "parseXmlFile", pce);
        }catch(SAXException se) {
            LOGGER.throwing("XLMParser", "parseXmlFile", se);
        }catch(IOException ioe) {
            LOGGER.throwing("XLMParser", "parseXmlFile", ioe);
        }
    }  
}
