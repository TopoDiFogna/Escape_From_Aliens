package it.polimi.ingsw.cg_23.gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.DefaultCaret;

public class ChatPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private static JTextArea chat;
    private static Connection connection;
    
    public ChatPanel() {
        
        Font font = new Font("Open Sans", Font.PLAIN, 12);
        setOpaque(false);
        setBorder(BorderFactory.createEtchedBorder(new Color(191, 191, 191, 255), new Color(91, 91, 91, 255)));
        setBounds(20,110,235,469);
        
        
        chat = new JTextArea();
        JScrollPane scroll = new JScrollPane(chat);
        DefaultCaret caret = (DefaultCaret)chat.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
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
        
        textEntered.addFocusListener(new FocusListener() {
            
            @Override
            public void focusLost(FocusEvent e) {
                if("".equals(textEntered.getText())){
                    textEntered.setText("Click here to chat...");
                    repaint();
                    revalidate();
                }
                
            }
            
            @Override
            public void focusGained(FocusEvent e) {
                if("Click here to chat...".equals(textEntered.getText())){
                    textEntered.setText("");
                    repaint();
                    revalidate();
                }
                
            }
        });
        
        textEntered.addActionListener(new ActionListener() {            
            @Override
            public void actionPerformed(ActionEvent e) {
                connection.chat(textEntered.getText());
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
                connection.chat(textEntered.getText());
                textEntered.setText("");
                
            }
        });
    } 
    
    
    public static void appendMessages(String textEntered){        
            if(!"Click here to chat...".equals(textEntered) && !"".equals(textEntered)){
                chat.append(textEntered+System.lineSeparator());                
            }
    }


    public static void setConnection(Connection connection) {
        ChatPanel.connection = connection;
    }    
}
