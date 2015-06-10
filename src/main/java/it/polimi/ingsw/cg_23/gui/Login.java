package it.polimi.ingsw.cg_23.gui;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Login extends JPanel {
    
    public Login(){
        
        setBounds(0,0,120,120);
        setBackground(new Color(255,255,255,90));
        
        JTextField name = new JTextField();
        add(name);
        
        
        ButtonGroup groupButton = new ButtonGroup();
        
        JRadioButton radioGalilei = new JRadioButton("galilei");
        add(radioGalilei);
        groupButton.add(radioGalilei);
        radioGalilei.setSelected(true);
        radioGalilei.setBackground(new Color(0,0,0,0));
        
        JRadioButton radioGalvani = new JRadioButton("galvani");
        add(radioGalvani);
        groupButton.add(radioGalvani);
        radioGalvani.setBackground(new Color(0,0,0,0));
        
        JRadioButton radioFermi = new JRadioButton("fermi");
        add(radioFermi);
        groupButton.add(radioFermi);
        radioFermi.setBackground(new Color(0,0,0,0));
        
        JButton start = new JButton();
        add(start);
        start.setText("Start");
    }
}
