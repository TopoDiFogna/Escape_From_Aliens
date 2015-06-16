package it.polimi.ingsw.cg_23.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class EndTurnPanel extends JPanel{

    private int turn = 0;
    
    public EndTurnPanel() {        
        
        Font font = new Font("Open Sans", Font.PLAIN, 12);
        setOpaque(false);
        setBorder(BorderFactory.createEtchedBorder(new Color(191, 191, 191, 255), new Color(91, 91, 91, 255)));
        setBounds(973,360,170,70);
        
        Label turnNumber = new Label();
        turnNumber.setText("Turn Number: "+turn);
        turnNumber.setFont(font);
        turnNumber.setForeground(new Color(191, 191, 191, 255));
        turnNumber.setBackground(new Color(21, 25, 31, 10));
        add(turnNumber);
        
        final JButton end = new JButton();
        end.setText("End Turn");
        end.setFont(font);
        add(end);
        end.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                repaint();
            }
        });
    }

}
