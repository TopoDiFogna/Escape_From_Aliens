package it.polimi.ingsw.cg_23.gui;

public abstract class Connection {

    public abstract void move(String letter, String number);
    
    public abstract void moveAndAttack();
    
    public abstract void makeNoise();
    
    public abstract void endTurn();
    
    public abstract void chat();

}
