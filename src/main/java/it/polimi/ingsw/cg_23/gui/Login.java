package it.polimi.ingsw.cg_23.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login extends JPanel {
    
   
    public Login(){
        
        Font font = new Font("Open Sans", Font.PLAIN, 12);
        setOpaque(false);
        setBounds(0,0,120,150);
        
        
        final JTextField nickname = new JTextField("Enter a Nickname");
        nickname.setPreferredSize(new Dimension(105,22));
        nickname.setFont(font);
        add(nickname);
        
        //This mouse listener delete Enter a Nickname when click on the text field.
        nickname.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                if(nickname.getText().equals("Enter a Nickname")){
                    nickname.setText("");
                    repaint();
                    revalidate();
                }
            }
        });        
        
        
        final JTextField ip = new JTextField("Enter IP address");
        ip.setPreferredSize(new Dimension(105,22));
        ip.setFont(font);
        add(ip);
        
        //This mouse listener delete Enter IP address when click on the text field.
        ip.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                if(ip.getText().equals("Enter IP address")){
                    ip.setText("");
                    repaint();
                    revalidate();
                }
            }
        });
        
        String[] mapType = {"Galilei", "Galvani", "Fermi"};
        final JComboBox mapList = new JComboBox(mapType);;
        mapList.setPreferredSize(new Dimension(95,22));
        mapList.setFont(font);
        mapList.setSelectedIndex(0);
        add(mapList);        
        
        String[] connectionType = {"Socket", "RMI"};
        final JComboBox connectionList = new JComboBox(connectionType);;
        connectionList.setPreferredSize(new Dimension(95,22));
        connectionList.setSelectedIndex(0);
        connectionList.setFont(font);
        add(connectionList);
        
        final JButton start = new JButton();
        start.setText("Start");
        start.setFont(font);
        start.setPreferredSize(new Dimension(65, 22));
        add(start);   
        
       
        
        //This listener saves all fields on start button click, hides login components and calls initializeLoading method.
        start.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(!nickname.getText().equals("Enter a Nickname") && !nickname.getText().equals("")){                    
                    
                                               
                    //StartingTable.initializeLoading();
                    
                    setVisible(false);
                    StartingTable.initializeMap(mapList.getSelectedItem().toString().toLowerCase());
                    StartingTable.initializeMoveAttack();
                    StartingTable.initializeEndTurn();
                    StartingTable.initializeChat(nickname.toString());
                    repaint();
                    revalidate();
                }
            }
        });       
    }
    
    
}
