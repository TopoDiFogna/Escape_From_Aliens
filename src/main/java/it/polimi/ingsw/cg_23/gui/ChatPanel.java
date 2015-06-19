package it.polimi.ingsw.cg_23.gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class ChatPanel extends JPanel {

    public ChatPanel() {
        
        Font font = new Font("Open Sans", Font.PLAIN, 12);
        setOpaque(false);
        setBorder(BorderFactory.createEtchedBorder(new Color(191, 191, 191, 255), new Color(91, 91, 91, 255)));
        setBounds(20,110,235,469);
        
        
        final JTextArea chat = new JTextArea();
        JScrollPane scroll = new JScrollPane(chat);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);
        scroll.setPreferredSize(new Dimension(230, 425));
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        chat.setOpaque(false);
        chat.setFont(font);
        chat.setEditable(false);
        chat.setForeground(new Color(255, 255, 255, 255));
        chat.setLineWrap(true);
        chat.setWrapStyleWord(true);
        add(scroll);
        
        final JTextField textEntered = new JTextField("Click here to chat...");
        textEntered.setFont(font);
        textEntered.setPreferredSize(new Dimension(150,22));
        add(textEntered, BorderLayout.SOUTH);
        
        textEntered.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                if(textEntered.getText().equals("Click here to chat...")){
                    textEntered.setText("");
                    repaint();
                    revalidate();
                }
            }
        });
        
        textEntered.addActionListener(new ActionListener() {            
            @Override
            public void actionPerformed(ActionEvent e) {
                appendMessages(chat, textEntered);
                textEntered.requestFocusInWindow(); 
                repaint();
                revalidate();
            }
        });
        
        
        final JButton send = new JButton("Send");
        send.setFont(font);
        send.setPreferredSize(new Dimension(65, 22));
        add(send, BorderLayout.SOUTH);
        
        send.addActionListener(new ActionListener() {            
            @Override
            public void actionPerformed(ActionEvent e) {
                appendMessages(chat, textEntered);
                
            }
        });
    } 
    
    
    public void appendMessages(JTextArea chat, JTextField textEntered){        
            if(!"Click here to chat...".equals(textEntered.getText()) && !"".equals(textEntered.getText())){
                chat.append("[Gino]: "+textEntered.getText()+"\n");
                textEntered.setText("");                
            }
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
