package it.polimi.ingsw.cg_23.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Login extends JPanel {
    
    public Login(){
        
        Font font = new Font("Open Sans", Font.BOLD, 12);
        Border border = BorderFactory.createLineBorder(new Color(39,115,193,130));
        Color backgroundColor = new Color(50,125,216,80);
        Color textColor = new Color(240,240,255,255);
        setOpaque(false);
        setBounds(0,0,120,130);
        
        JTextField nickname = new JTextField("Nickname");
        nickname.setPreferredSize(new Dimension(90,25));
        nickname.setFont(font);
        nickname.setBackground(backgroundColor);
        nickname.setBorder(border);
        nickname.setForeground(textColor);
        nickname.setOpaque(false);
        add(nickname);
        
        String[] mapType = {"Galilei", "Galvani", "Fermi"};
        JComboBox mapList = new JComboBox(mapType);;
        mapList.setPreferredSize(new Dimension(90,25));
        mapList.setOpaque(false);
        mapList.setSelectedIndex(0);
        mapList.setFont(font);
        mapList.setBackground(backgroundColor);
        mapList.setBorder(border);
        mapList.setForeground(textColor);
        add(mapList);
        
        String[] connectionType = {"Socket", "RMI"};
        JComboBox connectionList = new JComboBox(connectionType);;
        connectionList.setPreferredSize(new Dimension(90,25));
        connectionList.setOpaque(false);
        connectionList.setSelectedIndex(0);
        connectionList.setFont(font);
        connectionList.setBackground(backgroundColor);
        connectionList.setBorder(border);
        connectionList.setForeground(textColor);
        add(connectionList);
        
        JButton start = new JButton();
        start.setText("Start");
        start.setPreferredSize(new Dimension(70,30));
        start.setFont(font);
        start.setBackground(backgroundColor);
        start.setBorder(border);
        start.setForeground(textColor);
        add(start);
    }
}
