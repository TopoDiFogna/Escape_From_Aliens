package it.polimi.ingsw.cg_23.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EndTurnPanel extends JPanel{

    private static final long serialVersionUID = 1L;
    private int turn = 0;
    
    public EndTurnPanel(Connection connection) {        
        
        final Connection connectionType = connection;
        
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
        end.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                connectionType.endTurn();
                
            }
        });
       
    }

}
