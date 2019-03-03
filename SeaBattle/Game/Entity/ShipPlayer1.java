package com.brainacad.SeaBattle.Game.Entity;

import com.brainacad.SeaBattle.Enums.Condition;
import com.brainacad.SeaBattle.Interfaces.Ship;

import java.util.Arrays;

/**
 * Created by Администратор on 24.01.2016.
 */
public  class ShipPlayer1 implements Ship {
    private int shipType;
    private int i=0;
    private Condition cond;
    private int coordX;
    private int coordY;
    private int[] spaceX;
    private int[] spaceY;

    public ShipPlayer1(int shipType, int y, int x){
        this.shipType = shipType;
        this.coordY = y;
        this.coordX = x;
        this.spaceX = new int [shipType*2+6];
        this.spaceY = new int [shipType*2+6];
    }

    @Override
    public  Ship createShip() {

        int m=0;

            for (int i = 0; i < shipType; i++) {
                m = coordY;
                for (int j = i * 2; j < (i * 2) + 2; j++) {
                    if (j % 2 == 0) {
                        spaceX[j] = coordX + 1;
                        spaceY[j] = m;
                    } else {
                        spaceX[j] = coordX - 1;
                        spaceY[j] = m;
                    }
                }
            }

            int n = coordY - 1;
            int k = coordY + 1;

            spaceX[shipType*2] = coordX;
            spaceY[shipType*2] = n;

            spaceX[shipType*2+1] = coordX + 1;
            spaceY[shipType*2+1] = n;

            spaceX[shipType*2+2] = coordX - 1;
            spaceY[shipType*2+2] = n;

            spaceX[shipType*2+3] = coordX;
            spaceY[shipType*2+3] = k;

            spaceX[shipType*2+4] = coordX + 1;
            spaceY[shipType*2+4] = k;

            spaceX[shipType*2+5] = coordX - 1;
            spaceY[shipType*2+5] = k;


        return this;
    }

    @Override
    public void setCoordX(int x) {
        coordX = x;

    }

    @Override
    public void setCoordY(int y) {
        coordY = y;
    }

    public void setShipType(int shipType) {

        this.shipType = shipType;
    }

    @Override
    public int getCoordX(int j) {
        return coordX;
    }

    @Override
    public int getCoordY(int j) {
        return coordY;
    }

    @Override
    public int getShipType() {
        return shipType;
    }

    @Override
    public void setCond(Condition cond, int y, int x) {
        this.cond = cond;
    }

    @Override
    public Condition getCond(int k) {
        return cond;
    }

    public int getSpaceX(int m) {
        return spaceX[m];
    }

    public int getSpaceY(int n) {
        return spaceY[n];
    }
}


