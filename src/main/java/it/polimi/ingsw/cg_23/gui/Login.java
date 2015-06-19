package it.polimi.ingsw.cg_23.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login extends JPanel {

    private static final long serialVersionUID = 1L;
    private Connection connection;
    
    public Login(){
        
        Font font = new Font("Open Sans", Font.PLAIN, 12);
        setOpaque(false);
        setBounds(0,0,120,150);
        
        
        final JTextField nickname = new JTextField("Enter a Nickname");
        nickname.setPreferredSize(new Dimension(105,22));
        nickname.setFont(font);
        add(nickname);
        
        //This mouse listener delete Enter a Nickname when click on the text field.
        nickname.addFocusListener(new FocusListener() {
            
            @Override
            public void focusLost(FocusEvent e) {
                if("".equals(nickname.getText())){
                    nickname.setText("Enter a Nickname");
                    repaint();
                    revalidate();
                }
                
            }
            
            @Override
            public void focusGained(FocusEvent e) {
                if("Enter a Nickname".equals(nickname.getText())){
                    nickname.setText("");
                    repaint();
                    revalidate();
                }
                
            }
        });       
        
        final JTextField ip = new JTextField("Enter IP address");
        ip.setPreferredSize(new Dimension(105,22));
        ip.setFont(font);
        add(ip);
        
        //This mouse listener delete Enter IP address when click on the text field.
        ip.addFocusListener(new FocusListener() {
            
            @Override
            public void focusLost(FocusEvent e) {
                if("".equals(ip.getText())){
                    ip.setText("Enter IP address");
                    repaint();
                    revalidate();
                }
                
            }
            
            @Override
            public void focusGained(FocusEvent e) {
                if(ip.getText().equals("Enter IP address")){
                    ip.setText("");
                    repaint();
                    revalidate();
                }
            }
        });
        
        String[] mapType = {"Galilei", "Galvani", "Fermi"};
        final JComboBox<String> mapList = new JComboBox<String>(mapType);;
        mapList.setPreferredSize(new Dimension(95,22));
        mapList.setFont(font);
        mapList.setSelectedIndex(0);
        add(mapList);        
        
        String[] connectionType = {"Socket", "RMI"};
        final JComboBox<String> connectionList = new JComboBox<String>(connectionType);;
        connectionList.setPreferredSize(new Dimension(95,22));
        connectionList.setSelectedIndex(0);
        connectionList.setFont(font);
        add(connectionList);
        
        final JButton start = new JButton();
        start.setText("Start");
        start.setFont(font);
        start.setPreferredSize(new Dimension(65, 22));
        add(start); 
        
       
        
        //This listener saves all fields on start button click, hides login components and calls initializeLoading method.
        start.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!nickname.getText().equals("Enter a Nickname") && !nickname.getText().equals("") && !ip.getText().equals("Enter IP address") && !ip.getText().equals("")){                

                    setVisible(false);
                    StartingTable.initializeChat();
                    
                    if("rmi".equalsIgnoreCase((String)connectionList.getSelectedItem())){
                        connection = new RMIConnection(ip.getText(), nickname.getText(), (String)mapList.getSelectedItem());
                    }
                    else {
                        connection = new SocketConnection();
                    }

                    ChatPanel.setConnection(connection);
                    StartingTable.initializeMap(mapList.getSelectedItem().toString().toLowerCase());
                    StartingTable.initializeMoveAttackNoise(connection);
                    StartingTable.initializeEndTurn(connection);
                    repaint();
                    revalidate();
                }
            }
        });       
    } 
}
