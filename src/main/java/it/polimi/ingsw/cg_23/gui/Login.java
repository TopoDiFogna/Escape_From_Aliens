package it.polimi.ingsw.cg_23.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login extends JPanel {
    
    
    public Login(){
        
        Font font = new Font("Open Sans", Font.PLAIN, 12);
        setOpaque(false);
        setBounds(0,0,120,130);
        
        
        final JTextField nickname = new JTextField("Enter a Nickname");
        nickname.setPreferredSize(new Dimension(105,25));
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
        
        
        String[] mapType = {"Galilei", "Galvani", "Fermi"};
        final JComboBox mapList = new JComboBox(mapType);;
        mapList.setPreferredSize(new Dimension(90,25));
        mapList.setFont(font);
        mapList.setSelectedIndex(0);
        add(mapList);
        
        
        String[] connectionType = {"Socket", "RMI"};
        final JComboBox connectionList = new JComboBox(connectionType);;
        connectionList.setPreferredSize(new Dimension(90,25));
        connectionList.setSelectedIndex(0);
        connectionList.setFont(font);
        add(connectionList);
        
        final JButton start = new JButton();
        start.setText("Start");
        start.setFont(font);
        add(start);   
        
        
        
        //This listener saves all fields on start button click, hides login components and calls initializeLoading method.
        start.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(!nickname.getText().equals("Enter a Nickname") && !nickname.getText().equals("")){                    
                    
                    
                    //This listener saves the nickname selected by player into a string variable.
                    nickname.addActionListener(new ActionListener() {            
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String name = nickname.getText();                
                        }
                    });
                    
                    
                    //This listener saves the selected map
                    mapList.addActionListener(new ActionListener() {            
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String map = mapList.getSelectedItem().toString().toLowerCase();
                            map = "join" + map;
                        }
                    });
                    
                    
                  //This listener saves the selected connection
                    connectionList.addActionListener(new ActionListener() {            
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String connection = connectionList.getSelectedItem().toString().toLowerCase();
                        }
                    });
                    
                    StartingTable.initializeLoading();
                    setVisible(false);
                    repaint();
                    revalidate();
                }
            }
        });       
    }
}
