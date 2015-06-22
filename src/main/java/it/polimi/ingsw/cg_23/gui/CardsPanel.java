package it.polimi.ingsw.cg_23.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Creates card panel.
 * 
 * @author Arianna
 */
public class CardsPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    
    private Connection connection;
    
    private static Image[] cards;
    private JButton card0;
    private JButton card1;
    private JButton card2;
    private JButton card3;
    private JButton card4;
    private JButton card5;
    
    private String[] options = {"Use", "Discard"};

    private String card;
    
    /**
     * Crates a panel and using the grid layout adds on this panel all cards. <br>
     * All cards are JButtons, and on click of them the method creates a JOptionPane asks what player want to do: <br>
     * Use or Discard this card. On Use button click is called useCard method, On Discard button click is called <br>
     * discardCard method. Spotlight card has a different use card method: useSpotlight.
     * 
     * @param connection connection chose between socket and rmi to calls right method to play
     */
    public CardsPanel(Connection connection) {
        this.connection= connection;
        
        setBorder(BorderFactory.createEtchedBorder(new Color(191, 191, 191, 255), new Color(91, 91, 91, 255)));
        setBounds(955,307,215,302);
        setOpaque(false);
        loadCardsImages();
        
        this.setLayout(new GridLayout(3,2));
              
        card0 = new JButton(new ImageIcon(cards[0]));
        card0.setBorder(BorderFactory.createEmptyBorder());
        card0.setContentAreaFilled(false);
        card0.setEnabled(true);
        add(card0);
        card0.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showOptionDialog(null, "What you wanto to do?", "Use or Discard", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
                card = "adrenaline";
                if(response==JOptionPane.YES_OPTION){
                    useCard();
                }
                else if(response == JOptionPane.NO_OPTION){
                    discardCard();
                }
            }
        });
        
        card1 = new JButton(new ImageIcon(cards[1]));
        card1.setBorder(BorderFactory.createEmptyBorder());
        card1.setContentAreaFilled(false);
        card1.setEnabled(false);
        add(card1);
        card1.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showOptionDialog(null, "What you wanto to do?", "Use or Discard", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
                card = "attack";
                if(response==JOptionPane.YES_OPTION){
                    useCard();
                }
                else if(response == JOptionPane.NO_OPTION){
                    discardCard();
                }
            }
        });
       
        
        card2 = new JButton(new ImageIcon(cards[2]));
        card2.setBorder(BorderFactory.createEmptyBorder());
        card2.setContentAreaFilled(false);
        card2.setEnabled(false);
        add(card2);
        card2.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showOptionDialog(null, "What you wanto to do?", "Use or Discard", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
                card = "defense";
                if(response==JOptionPane.YES_OPTION){
                    useCard();
                }
                else if(response == JOptionPane.NO_OPTION){
                    discardCard();
                }
            }
        });
        
        
        card3 = new JButton(new ImageIcon(cards[3]));
        card3.setBorder(BorderFactory.createEmptyBorder());
        card3.setContentAreaFilled(false);
        card3.setEnabled(false);
        add(card3);
        card3.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showOptionDialog(null, "What you wanto to do?", "Use or Discard", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
                card = "sedatives";
                if(response==JOptionPane.YES_OPTION){
                    useCard();
                }
                else if(response == JOptionPane.NO_OPTION){
                    discardCard();
                }
            }
        });
        
        
        card4 = new JButton(new ImageIcon(cards[4]));
        card4.setBorder(BorderFactory.createEmptyBorder());
        card4.setContentAreaFilled(false);
        card4.setEnabled(true);
        add(card4);
        card4.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showOptionDialog(null, "What you wanto to do?", "Use or Discard", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
                card = "spotlight";
                if(response==JOptionPane.YES_OPTION){
                    useSpotlight();
                }
                else if(response == JOptionPane.NO_OPTION){
                    discardCard();
                }
            }
        });
        
        
        card5 = new JButton(new ImageIcon(cards[5]));
        card5.setBorder(BorderFactory.createEmptyBorder());
        card5.setContentAreaFilled(false);
        card5.setEnabled(false);
        add(card5);
        card5.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showOptionDialog(null, "What you wanto to do?", "Use or Discard", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
                card = "teleport";
                if(response==JOptionPane.YES_OPTION){
                    useCard();
                }
                else if(response == JOptionPane.NO_OPTION){
                    discardCard();
                }
            }
        });
        
    }
    
    /**
     * Loads all ItemCards images and puts them in an array.
     */
    private static void loadCardsImages(){
        cards = new Image[6];
        try {
            for(int cardNumber = 0; cardNumber<6;cardNumber++){
                    cards[cardNumber]= ImageIO.read(new File("./img/"+cardNumber+".png"));
            }
        } catch (IOException e) {
            System.err.println("Unable to load images.");
        }
    }
      
    /**
     * On click of the Use button in the JOptionPane is called this method. <br>
     * Calls the method useCard according the connection choose before.
     */
    private void useCard(){
        connection.useCard(card, -1, -1);
    }
    
    /**
     * On click of the Discard button in the JOptionPane is called this method. <br>
     * Calls the method discardCard according the connection choose before.
     */
    private void discardCard(){
        connection.discardCard(card);
    }
    
    /**
     * Method calls if player want to use a spotlight card. <br>
     * Creates another JOptionPanel to insert in what sector he want to use spotlight card. <br>
     * Parses number and letter insert to integer and on click of the Ok button, calls useCard passing this coordinates.
     */
    private void useSpotlight(){
        JTextField letter = new JTextField(3);
        JTextField number = new JTextField(3);
        
        JPanel panel = new JPanel();
        
        panel.add(new JLabel("Letter: "));
        panel.add(letter);
        panel.add(new JLabel("Number: "));
        panel.add(number);
        
        int result = JOptionPane.showConfirmDialog(null, panel, "", JOptionPane.OK_CANCEL_OPTION);
        
        int letterAsInt = Character.getNumericValue(letter.getText().toLowerCase().charAt(0))-10;
        int numberAsInt;
        try{
            numberAsInt = (Character.getNumericValue(number.getText().toLowerCase().charAt(0))*10)+(Character.getNumericValue(number.getText().toLowerCase().charAt(1)))-1;
        }catch (IndexOutOfBoundsException e){
            numberAsInt = Character.getNumericValue(number.getText().toLowerCase().charAt(0))-1;
        }
        
       
        if(result==JOptionPane.OK_OPTION)
            connection.useCard(card, letterAsInt, numberAsInt);
    }
}
