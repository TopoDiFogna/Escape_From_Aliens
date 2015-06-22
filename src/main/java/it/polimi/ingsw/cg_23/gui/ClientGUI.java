package it.polimi.ingsw.cg_23.gui;

import javax.swing.SwingUtilities;

/**
 * Client gui class to start gui.
 * 
 * @author Arianna
 */
public class ClientGUI {

    /**
     * Starting class to launch gui.
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                StartingTable gameTable = new StartingTable();
                gameTable.setVisible(true);
            }
        });

    }

}
