package it.polimi.ingsw.cg_23.gui;

public abstract class Connection {

    public abstract void move(String letter, String number);
    
    public abstract void moveAndAttack(String letter, String number);
    
    public abstract void makeNoise(String letter, String number);
    
    public abstract void endTurn();
    
    public abstract void chat(String msg);
    
    public abstract void useCard(String card, int letter, int number);
    
    public abstract void discardCard(String card);

}
