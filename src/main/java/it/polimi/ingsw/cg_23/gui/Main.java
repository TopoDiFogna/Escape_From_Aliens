package it.polimi.ingsw.cg_23.gui;

import javax.swing.SwingUtilities;

public class Main {

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
