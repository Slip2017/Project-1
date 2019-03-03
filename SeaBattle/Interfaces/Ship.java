package com.brainacad.SeaBattle.Interfaces;
import com.brainacad.SeaBattle.Enums.Condition;


/**
 * @author  Shynkarenko Eduard
 *
 */

public interface Ship {

    public abstract Ship createShip();

    public abstract void setCoordX(int x);
    public abstract void setCoordY(int y);
    public abstract int getCoordX(int i);
    public abstract int getCoordY(int j);
    public abstract int getShipType();

    public  abstract void setCond(Condition cond, int y, int x);
    public  abstract Condition getCond(int k);

    public int getSpaceX(int m);

    public int getSpaceY(int n);



}
