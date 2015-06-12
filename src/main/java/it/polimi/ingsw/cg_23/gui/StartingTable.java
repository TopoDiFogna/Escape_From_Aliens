package it.polimi.ingsw.cg_23.gui;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;

public class StartingTable extends JFrame {
    
    private  Image backgroundImage;
    private  Image logoImage;
    private  Image loadingImage;
    private  JLabel backgroundLabel;
    private  JLabel logoLabel;
    private  JLabel loadingLabel;
    private  JLayeredPane layeredPane;
    private final static int LAYER_BACKGROUND = 1;
    private final static int LAYER_LOGO = 2;
    private final static int LAYER_LOGIN = 3;
    private final static int LAYER_LOADING = 3;
    
    /**
     * The constructor. <br>
     * Sets the size of the window, sets the title, not resizable, and close when click X button. <br>
     * Calls loading images method and initializing method.
     */
    public StartingTable(){
        
        setSize(1000, 600 + getInsets().top);        
        setTitle("Esape from the Aliens in the Outer Space");        
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        loadResources();
        initializeBasicTable();
        initializeLogin();
        
    }
    
    private void loadResources(){
        try {
            backgroundImage = ImageIO.read(new File("./img/background_table.png"));
            logoImage = ImageIO.read(new File("./img/logo.png"));
            loadingImage = ImageIO.read(new File("./img/Loading.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void initializeBasicTable(){

        layeredPane = new JLayeredPane();
        setContentPane(layeredPane);
        
        backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
        backgroundLabel.setBounds(0, 0, 1000, 600);
        layeredPane.add(backgroundLabel);
        layeredPane.setLayer(backgroundLabel, LAYER_BACKGROUND);
        
        logoLabel = new JLabel(new ImageIcon(logoImage));
        logoLabel.setBounds(393, 10, 214, 114);
        layeredPane.add(logoLabel);
        layeredPane.setLayer(logoLabel, LAYER_LOGO);
    }
    
    private void initializeLogin() {
        
        Login login = new Login();        
        layeredPane.add(login);
        layeredPane.setLayer(login, LAYER_LOGIN);
        login.setLocation(440, 250);
        login.setVisible(true);
    }
    
    protected void initializeLoading() {
        
        loadingLabel = new JLabel(new ImageIcon(loadingImage));
        loadingLabel.setBounds(400, 200, 400, 300);
        layeredPane.add(loadingLabel);
        layeredPane.setLayer(loadingLabel, LAYER_LOADING);
    }
}
