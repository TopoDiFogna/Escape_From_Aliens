package it.polimi.ingsw.cg_23.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NoisePanel extends JPanel {

    public NoisePanel() {
        
        Font font = new Font("Open Sans", Font.PLAIN, 12);
        setOpaque(false);
        setBorder(BorderFactory.createEtchedBorder(new Color(191, 191, 191, 255), new Color(91, 91, 91, 255)));
        setBounds(973,250,170,100);
        
        Label textLetter = new Label();
        textLetter.setText("Enter letter");
        textLetter.setFont(font);
        textLetter.setForeground(new Color(191, 191, 191, 255));
        textLetter.setBackground(new Color(21, 25, 31, 10));
        JTextField letter = new JTextField();
        letter.setPreferredSize(new Dimension(30,17));
        letter.setFont(font);
        add(textLetter);
        add(letter);
        
        Label textNumber = new Label();
        textNumber.setText("Enter number");
        textNumber.setFont(font);
        textNumber.setForeground(new Color(191, 191, 191, 255));
        textNumber.setBackground(new Color(21, 25, 31, 10));
        JTextField number = new JTextField();
        number.setPreferredSize(new Dimension(30,17));
        number.setFont(font);
        add(textNumber);
        add(number);        
        
        final JButton noise = new JButton();
        noise.setText("Make Noise");
        noise.setFont(font);
        add(noise);
    }

}
