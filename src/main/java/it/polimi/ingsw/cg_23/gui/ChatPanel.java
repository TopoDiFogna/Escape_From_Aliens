package it.polimi.ingsw.cg_23.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChatPanel extends JPanel {

    public ChatPanel() {
        
        Font font = new Font("Open Sans", Font.PLAIN, 12);
        setOpaque(false);
        setBorder(BorderFactory.createEtchedBorder(new Color(191, 191, 191, 255), new Color(91, 91, 91, 255)));
        setBounds(20,110,235,469);
        
        JLabel chat = new JLabel();
        chat.setFont(font);
        chat.setBounds(2, 2, 230, 425);
        chat.setPreferredSize(new Dimension(230, 425));
        chat.setForeground(new Color(191, 191, 191, 200));
        add(chat);
        
        final JTextField textEntered = new JTextField("Click here to chat...");
        textEntered.setFont(font);
        textEntered.setPreferredSize(new Dimension(150,22));
        chat.setBounds(0, 200, 145, 22);
        add(textEntered, BorderLayout.SOUTH);
        
        textEntered.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                if(textEntered.getText().equals("Click here to chat...")){
                    textEntered.setText("");
                    repaint();
                    revalidate();
                }
            }
        }); 
        
        JButton send = new JButton("Send");
        send.setFont(font);
        send.setPreferredSize(new Dimension(65, 22));
        add(send, BorderLayout.SOUTH);
        
    }

}
