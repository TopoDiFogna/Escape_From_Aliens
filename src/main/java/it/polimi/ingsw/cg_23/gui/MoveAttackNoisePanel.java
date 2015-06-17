package it.polimi.ingsw.cg_23.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MoveAttackNoisePanel extends JPanel {
    
    public MoveAttackNoisePanel(){
          
        
        Font font = new Font("Open Sans", Font.PLAIN, 12);
        setOpaque(false);
        setBorder(BorderFactory.createEtchedBorder(new Color(191, 191, 191, 255), new Color(91, 91, 91, 255)));
        setBounds(973,110,170,140);
        
        JLabel textLetter = new JLabel();
        textLetter.setText("Enter letter     ");
        textLetter.setFont(font);
        textLetter.setForeground(new Color(255, 255, 255, 255));
        JTextField letter = new JTextField();
        letter.setPreferredSize(new Dimension(30,18));
        letter.setFont(font);
        add(textLetter);
        add(letter);
        
        JLabel textNumber = new JLabel();
        textNumber.setText("Enter number");
        textNumber.setFont(font);
        textNumber.setForeground(new Color(255, 255, 255, 255));
        JTextField number = new JTextField();
        number.setPreferredSize(new Dimension(30,18));
        number.setFont(font);
        add(textNumber);
        add(number);        
        
        final JButton move = new JButton();
        move.setText("Move");
        move.setFont(font);
        move.setPreferredSize(new Dimension(110, 22));
        add(move);  
        
        final JButton attack = new JButton();
        attack.setText("Move&Attack");
        attack.setFont(font);
        attack.setPreferredSize(new Dimension(110, 22));
        add(attack); 
        
        final JButton noise = new JButton();
        noise.setText("Make Noise");
        noise.setFont(font);
        noise.setPreferredSize(new Dimension(110, 22));
        add(noise);
    }
    
}
