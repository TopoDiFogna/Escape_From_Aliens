package it.polimi.ingsw.cg_23.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Creates the gui window.
 * 
 * @author Arianna
 */
public class StartingTable extends JFrame {
    
    private static final long serialVersionUID = 1L;
    
    private static final String ERR = "Unable to load images.";
    
    private transient Image backgroundImage;
    private transient Image logoImage;
    private static Image mapImage;
    private static Image image1;
    private static Image image2;
    
    private JLabel backgroundLabel;
    private JLabel logoLabel;
    private static JLabel mapLabel;
    private JLabel image1Label;
    private JLabel image2Label;
    
    private static JLayeredPane layeredPane;
    
    private static final int LAYER_BACKGROUND = 1;
    private static final int LAYER_LOGO = 2;
    private static final int LAYER_LOGIN = 3;
    private static final int LAYER_GAME = 4;
    
    /**
     * The constructor. <br>
     * Sets the size of the window, sets the title, not resizable, and close when click X button. <br>
     * Calls loading images method and initializing login, basic table and images.
     */
    public StartingTable(){
        
        setSize(1200, 700 + getInsets().top);        
        setTitle("Esape from the Aliens in the Outer Space");        
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        loadResources();
        initializeBasicTable();
        initializeLogin();
        initializeImages();
    }
    
    /**
     * Loads logo and background images and catch an error if unable to load them.
     */
    private void loadResources(){
        try {
            backgroundImage = ImageIO.read(new File("./img/background_table.png"));
            logoImage = ImageIO.read(new File("./img/logo.png"));
        } catch (IOException e) {
            System.err.println(ERR);
        }
    }    
    
    /**
     * Creates a layered pane, add background on it (with right layer) and add the logo image.
     */
    private void initializeBasicTable(){

        layeredPane = new JLayeredPane();
        setContentPane(layeredPane);
        
        backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        backgroundLabel.setBounds(0, 0, 1200, 700);
        layeredPane.add(backgroundLabel);
        layeredPane.setLayer(backgroundLabel, LAYER_BACKGROUND);
        
        logoLabel = new JLabel(new ImageIcon(logoImage));
        logoLabel.setBounds(531, 20, 138, 74);
        layeredPane.add(logoLabel);
        layeredPane.setLayer(logoLabel, LAYER_LOGO);
    }
    
    /**
     * Creates a new login, adds it on the background, sets a location and visibility.
     */
    private void initializeLogin() {
        
        Login login = new Login();        
        layeredPane.add(login);
        layeredPane.setLayer(login, LAYER_LOGIN);
        login.setLocation(540, 230);
        login.setVisible(true);
    }
    
    /**
     * Adds the background images on the background(with right layer), sets the visibility and the position.
     */
    private void initializeImages(){
        loadImages();
        image1Label = new JLabel(new ImageIcon(image1));
        image1Label.setBounds(10, 150, 300, 429);
        layeredPane.add(image1Label);
        layeredPane.setLayer(image1Label, LAYER_LOGIN);
        image1Label.setVisible(true);
        
        image2Label = new JLabel(new ImageIcon(image2));
        image2Label.setBounds(890, 150, 300, 429);
        layeredPane.add(image2Label);
        layeredPane.setLayer(image2Label, LAYER_LOGIN);
        image2Label.setVisible(true);       
    }
    
    /**
     * Loads images to add on background and catch an error if unable to load them.
     */
    private static void loadImages(){
        try {
            image1 = ImageIO.read(new File("./img/image1.png"));
            image2 = ImageIO.read(new File("./img/image2.png"));
        } catch (IOException e) {
            System.err.println(ERR);
        }
    }   
    
    /**
     * Calls method to load image. Adds the image on right layer on background, and sets visibility.
     * 
     * @param map map player want to play
     */
    protected static void initializeMap(String map){
        loadMap(map);
        mapLabel = new JLabel(new ImageIcon(mapImage));
        mapLabel.setBounds(275, 140, 650, 469);
        layeredPane.add(mapLabel);
        layeredPane.setLayer(mapLabel, LAYER_GAME);        
        mapLabel.setVisible(true);
    }
    
    /**
     * Loads map image according with parameter passed and catch an error if unable to load it.
     * 
     * @param map map must load for this match
     */
    private static void loadMap(String map){
        try {
            mapImage = ImageIO.read(new File("./img/"+map+".png"));
        } catch (IOException e) {
            System.err.println(ERR);
        }
    }

    /**
     * Creates new action panel, sets right layer and sets visibility.
     * 
     * @param connection connection chose between socket and rmi
     */
    protected static void initializeMoveAttackNoise(Connection connection) {
        MoveAttackNoisePanel actionPanel = new MoveAttackNoisePanel(connection);
        layeredPane.add(actionPanel);
        layeredPane.setLayer(actionPanel, LAYER_GAME); 
        actionPanel.setVisible(true);
    }
    
    /**
     * Creates new end turn panel, sets right layer and sets visibility.
     * 
     * @param connection connection chose between socket and rmi
     */
    protected static void initializeEndTurn(Connection connection) {
        EndTurnPanel endTurnPanel = new EndTurnPanel(connection);
        layeredPane.add(endTurnPanel);
        layeredPane.setLayer(endTurnPanel, LAYER_GAME); 
        endTurnPanel.setVisible(true);
    }

    /**
     * Creates new chat panel, sets right layer and sets visibility.
     */
    protected static void initializeChat() {
        ChatPanel chat = new ChatPanel();
        layeredPane.add(chat);
        layeredPane.setLayer(chat, LAYER_GAME);
        chat.setVisible(true);
    }
    
    /**
     * Creates new cards panel, sets right layer and sets visibility.
     * 
     * @param connection connection chose between socket and rmi
     */
    protected static void initializeCardsPanel(Connection connection){
        CardsPanel cards = new CardsPanel(connection);
        layeredPane.add(cards);
        layeredPane.setLayer(cards, LAYER_GAME);
        cards.setVisible(true);
    }    
    
    protected static void writeName(String nickname){
        Font font = new Font("Open Sans", Font.PLAIN, 12);
        JLabel name = new JLabel(nickname.toUpperCase());
        name.setBounds(20, 115, 100, 22);
        name.setFont(font);
        name.setForeground(new Color(255, 255, 255, 255));
        layeredPane.add(name);
        layeredPane.setLayer(name, LAYER_GAME);
        name.setVisible(true);
    }
}
