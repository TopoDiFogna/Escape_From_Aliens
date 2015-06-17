package it.polimi.ingsw.cg_23.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EndTurnPanel extends JPanel{

    private int turn = 0;
    
    public EndTurnPanel() {        
        
        Font font = new Font("Open Sans", Font.PLAIN, 12);
        setOpaque(false);
        setBorder(BorderFactory.createEtchedBorder(new Color(191, 191, 191, 255), new Color(91, 91, 91, 255)));
        setBounds(973,258,170,58);
        
        JLabel turnNumber = new JLabel();
        turnNumber.setText("Turn Number: "+turn);
        turnNumber.setFont(font);
        turnNumber.setForeground(new Color(255, 255, 255, 255));
        add(turnNumber);
        
        final JButton end = new JButton();
        end.setText("End Turn");
        end.setFont(font);
        end.setPreferredSize(new Dimension(90, 22));
        add(end);
       
    }

}
