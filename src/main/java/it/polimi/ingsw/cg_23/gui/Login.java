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

/**
 * Creates login panel.
 * 
 * @author Arianna
 */
public class Login extends JPanel {

    private static final String NICKNAME = "Enter a Nickname";
    private static final String IP = "Enter IP address";
    
    private static final long serialVersionUID = 1L;
    
    private transient Connection connection;
    
    /**
     * The constructor. <br>
     * Creates 2 JTextFields for nickname and IP address with relatives listener. <br>
     * Creates 2 JComboBox for choice of connection type and map type.
     */
    public Login(){
        
        Font font = new Font("Open Sans", Font.PLAIN, 12);
        setOpaque(false);
        setBounds(0,0,120,150);        
        
        final JTextField nickname = new JTextField(NICKNAME);
        nickname.setPreferredSize(new Dimension(105,22));
        nickname.setFont(font);
        add(nickname);
        
        //This focus listener delete the default text if the field is on focus and rewrite the default text if lost focus
        //and player doesn't write anything.
        nickname.addFocusListener(new FocusListener() {
            
            @Override
            public void focusLost(FocusEvent e) {
                if("".equals(nickname.getText())){
                    nickname.setText(NICKNAME);
                    repaint();
                    revalidate();
                }                
            }
            
            @Override
            public void focusGained(FocusEvent e) {
                if(NICKNAME.equals(nickname.getText())){
                    nickname.setText("");
                    repaint();
                    revalidate();
                }                
            }
        });       
        
        final JTextField ip = new JTextField(IP);
        ip.setPreferredSize(new Dimension(105,22));
        ip.setFont(font);
        add(ip);
        
        //This focus listener delete the default text if the field is on focus and rewrite the default text if lost focus
        //and player doesn't write anything.
        ip.addFocusListener(new FocusListener() {
            
            @Override
            public void focusLost(FocusEvent e) {
                if("".equals(ip.getText())){
                    ip.setText(IP);
                    repaint();
                    revalidate();
                }
                
            }
            
            @Override
            public void focusGained(FocusEvent e) {
                if(IP.equals(ip.getText())){
                    ip.setText("");
                    repaint();
                    revalidate();
                }
            }
        });
        
        String[] mapType = {"Galilei", "Galvani", "Fermi"};
        final JComboBox<String> mapList = new JComboBox<String>(mapType);
        mapList.setPreferredSize(new Dimension(95,22));
        mapList.setFont(font);
        mapList.setSelectedIndex(0);
        add(mapList);        
        
        String[] connectionType = {"Socket", "RMI"};
        final JComboBox<String> connectionList = new JComboBox<String>(connectionType);
        connectionList.setPreferredSize(new Dimension(95,22));
        connectionList.setSelectedIndex(0);
        connectionList.setFont(font);
        add(connectionList);
        
        final JButton start = new JButton();
        start.setText("Start");
        start.setFont(font);
        start.setPreferredSize(new Dimension(65, 22));
        add(start); 
        
        //This listener hides login panel if all field are wrote and calls next gui elements for starting match
        // (like map, chat, cards, end turn and other actions.
        start.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!NICKNAME.equals(nickname.getText()) && !"".equals(nickname.getText()) && !IP.equals(ip.getText()) && !"".equals(ip.getText())){                

                    setVisible(false);
                    StartingTable.initializeChat();
                    
                    if("rmi".equalsIgnoreCase((String)connectionList.getSelectedItem())){
                        connection = new RMIConnection(ip.getText(), nickname.getText(), (String)mapList.getSelectedItem());
                    }
                    else {
                        connection = new SocketConnection(ip.getText(), nickname.getText(), (String)mapList.getSelectedItem());
                    }

                    ChatPanel.setConnection(connection);
                    StartingTable.initializeMap(mapList.getSelectedItem().toString().toLowerCase());
                    StartingTable.initializeMoveAttackNoise(connection);
                    StartingTable.initializeEndTurn(connection);
                    StartingTable.initializeCardsPanel(connection);
                    StartingTable.writeName(nickname.getText());
                    repaint();
                    revalidate();
                }
            }
        });       
    } 
}
