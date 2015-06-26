package it.polimi.ingsw.cg_23.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Creates end turn panel.
 * 
 * @author Arianna
 */
public class EndTurnPanel extends JPanel{

    private static final long serialVersionUID = 1L;
    
    /**
     * The constructor. <br>
     * Creates a button to communicate that player ends his turn. <br>
     * 
     * @param connection connection chose between socket and rmi to calls right method to play
     */
    public EndTurnPanel(Connection connection) {        
        
        final Connection connectionType = connection;
       
        
        
        Font font = new Font("Open Sans", Font.PLAIN, 12);
        setOpaque(false);
        setBorder(BorderFactory.createEtchedBorder(new Color(191, 191, 191, 255), new Color(91, 91, 91, 255)));
        setBounds(955,262,215,37);
        
        final JButton end = new JButton();
        end.setText("End Turn");
        end.setFont(font);
        end.setPreferredSize(new Dimension(110, 22));
        add(end);
        end.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                connectionType.endTurn();
                
            }
        });       
    }
}
