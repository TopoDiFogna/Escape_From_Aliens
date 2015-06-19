package it.polimi.ingsw.cg_23.gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class ChatPanel extends JPanel {

    public ChatPanel(String name) {
        
        Font font = new Font("Open Sans", Font.PLAIN, 12);
        setOpaque(false);
        setBorder(BorderFactory.createEtchedBorder(new Color(191, 191, 191, 255), new Color(91, 91, 91, 255)));
        setBounds(20,110,235,469);
        
        JTextPane chat = new JTextPane(); 
        JScrollPane scroll = new JScrollPane(chat);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);
        chat.setOpaque(false);
        chat.setFont(font);
        chat.setEditable(false);        
        //chat.setBounds(0, 0, 230, 425);
        chat.setPreferredSize(new Dimension(230, 425));
        chat.setForeground(new Color(191, 191, 191, 200));
        add(scroll);
        
        final JTextField textEntered = new JTextField("Click here to chat...");
        textEntered.setFont(font);
        textEntered.setPreferredSize(new Dimension(150,22));
        //textEntered.setBounds(0, 200, 150, 22);
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
    
    
    /*public void appendTextPane(String input, String name){
        StyleContext style = StyleContext.getDefaultStyleContext();
        AttributeSet attributeSet = style.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, name);

        attributeSet = style.addAttribute(attributeSet, StyleConstants.FontFamily, "Lucida Console");
        attributeSet = style.addAttribute(attributeSet, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int length = chat.getDocument().getLength();
        chat.setCaretPosition(length);
        chat.setCharacterAttributes(attributeSet, false);
        chat.replaceSelection(input+"\n");
    }*/

    
}
