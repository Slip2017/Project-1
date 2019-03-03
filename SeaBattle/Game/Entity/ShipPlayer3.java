package com.brainacad.SeaBattle.Game.Entity;

import com.brainacad.SeaBattle.Enums.Condition;
import com.brainacad.SeaBattle.Enums.Direction;
import com.brainacad.SeaBattle.Interfaces.Ship;

/**
 * Created by Администратор on 24.01.2016.
 */
public  class ShipPlayer3 implements Ship {

    private int shipType;
    private int i=0;
    private Direction dir;
    private Condition[] cond;
    private int[] coordX;
    private int[] coordY;
    private int initX;
    private int initY;
    private int endX;
    private int endY;
    private int[] spaceX;
    private int[] spaceY;

    public ShipPlayer3(int shipType, int y, int x, Direction dir){
        this.shipType = shipType;
        this.dir = dir;
        this.cond = new Condition[shipType];
        this.coordX = new int[shipType];
        this.coordY = new int[shipType];
        this.coordY[0] = y;
        this.coordX[0] = x;
        this.initY = y;
        this.initX = x;
        this.spaceX = new int [shipType*2+6];
        this.spaceY = new int [shipType*2+6];

    }

    @Override
    public  Ship createShip() {
        int min=0;
        int max=0;
        int m=0;
        int p=0;

        if (dir == Direction.n) {

            for (int i = shipType-1; i>=0 ; i--) {
                coordY[i] = initY--;
                coordX[i] = initX;
            }

            endY=coordY[shipType-1];
            endX=coordX[shipType-1];
            initY = coordY[0];
            initX = coordX[0];

        } else if (dir == Direction.s) {

            for (int i = 0; i <shipType; i++) {
                coordY[i] = initY++;
                coordX[i] = initX;
            }

            endY =coordY[shipType-1];
            endX = coordX[shipType-1];

        } else if (dir == Direction.w) {

            for (int i = shipType-1; i>=0 ; i--) {
                coordY[i] = initY;
                coordX[i] = initX--;
            }
            endY=coordY[shipType-1];
            endX=coordX[shipType-1];
            initY = coordY[0];
            initX = coordX[0];

        } else if (dir == Direction.e) {
            for (int i = 0; i < shipType; i++) {
                coordY[i] = initY;
                coordX[i] = initX++;
            }
            endY =coordY[shipType-1];
            endX = coordX[shipType-1];
        }

        if(initX==endX){

            for (int i = 0; i < shipType; i++) {
                m = coordY[i];
                for (int j = i * 2; j < (i * 2) + 2; j++) {
                    if (j % 2 == 0) {
                        spaceX[j] = initX + 1;
                        spaceY[j] = m;
                    } else {
                        spaceX[j] = initX - 1;
                        spaceY[j] = m;
                    }
                }
            }

            int n = coordY[0] - 1;
            int k = coordY[shipType-1] + 1;

            spaceX[shipType*2] = initX;
            spaceY[shipType*2] = n;

            spaceX[shipType*2+1] = initX + 1;
            spaceY[shipType*2+1] = n;

            spaceX[shipType*2+2] = initX - 1;
            spaceY[shipType*2+2] = n;

            spaceX[shipType*2+3] = initX;
            spaceY[shipType*2+3] = k;

            spaceX[shipType*2+4] = initX + 1;
            spaceY[shipType*2+4] = k;

            spaceX[shipType*2+5] = initX - 1;
            spaceY[shipType*2+5] = k;

        } else if(initY==endY){


            for (int i = 0; i < shipType; i++) {
                p = coordX[i];
                for (int j = i * 2; j < (i * 2) + 2; j++) {
                    if (j % 2 == 0) {
                        spaceX[j] = p;
                        spaceY[j] = initY + 1;

                    } else {
                        spaceX[j] = p;
                        spaceY[j] = initY - 1;

                    }
                }
            }

            int c = coordX[0] - 1;
            int l = coordX[shipType - 1] + 1;

            spaceX[shipType*2] = c;
            spaceY[shipType*2] = initY;

            spaceX[shipType*2+1] = c;
            spaceY[shipType*2+1] = initY + 1;

            spaceX[shipType*2+2] = c;
            spaceY[shipType*2+2] = initY - 1;

            spaceX[shipType*2+3] = l;
            spaceY[shipType*2+3] = initY;

            spaceX[shipType*2+4] = l;
            spaceY[shipType*2+4] = initY + 1;

            spaceX[shipType*2+5] = l;
            spaceY[shipType*2+5] = initY - 1;
        }

        return this;
    }


    @Override
    public void setCoordX(int x) {
        coordX[i++] = x;

    }

    @Override
    public void setCoordY(int y) {
        coordY[i++] = y;
    }

    public void setShipType(int shipType) {

        this.shipType = shipType;
    }

    @Override
    public int getCoordX(int j) {
        return coordX[j];
    }

    @Override
    public int getCoordY(int j) {
        return coordY[j];
    }

    @Override
    public int getShipType() {
        return shipType;
    }



    @Override
    public void setCond(Condition cond, int y, int x) {
        int m=0;
        for(int i=0; i < shipType; i++){
            if( coordX[i]==x & coordY[i] == y){
                m=i;
                break;
            }
        }

        this.cond[m] = cond;
    }


    @Override
    public Condition getCond(int k) {
        return cond[k];
    }

    public int getSpaceX(int m) {
        return spaceX[m];
    }

    public int getSpaceY(int n) {
        return spaceY[n];
    }
}
