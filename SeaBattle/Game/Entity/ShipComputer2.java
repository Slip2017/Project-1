package com.brainacad.SeaBattle.Game.Entity;

import com.brainacad.SeaBattle.Enums.Condition;
import com.brainacad.SeaBattle.Interfaces.Ship;

import java.util.Random;

/**
 * Created by Администратор on 24.01.2016.
 */
public class ShipComputer2 implements Ship {

    private int shipType;
    private int i = 0;
    private Condition[] cond;
    private int[] coordX;
    private int[] coordY;
    private int initX;
    private int initY;
    private int endX;
    private int endY;
    private int[] spaceX;
    private int[] spaceY;
    private PlaySpace space;

    public ShipComputer2(int shipType, PlaySpace space) {
        this.shipType = shipType;
        this.spaceX = new int[shipType * 2 + 6];
        this.spaceY = new int[shipType * 2 + 6];
        this.coordX = new int[shipType];
        this.coordY = new int[shipType];
        this.cond = new Condition[shipType];
        this.space = space;
    }

    private int[] findXY() {
        //Ship[] array = space.getObjComputer();
        int result[] = new int[2];
        Random random = new Random();
        int X = random.nextInt(10);
        int Y = random.nextInt(10);

        if (space.getObjComputer(0) == null) {
            result[0] = Y;
            result[1] = X;

        } else {
            outer:
            for (int k = 0; k < space.getObjComputerSize(); ) {

                for (int i = 0; i < space.getObjComputer(k).getShipType(); i++) {
                    if ((X == space.getObjComputer(k).getCoordX(i)) & (Y == space.getObjComputer(k).getCoordY(i))) {
                        X = random.nextInt(10);
                        Y = random.nextInt(10);
                        k = 0;
                        continue outer;
                    }
                }

                for (int j = 0; j < space.getObjComputer(k).getShipType() * 2 + 6; j++) {
                    if ((X == space.getObjComputer(k).getSpaceX(j)) & (Y == space.getObjComputer(k).getSpaceY(j))) {
                        X = random.nextInt(10);
                        Y = random.nextInt(10);
                        k = 0;
                        continue outer;
                    }
                }

                k++;
            }
            result[0] = Y;
            result[1] = X;


        }
            return result;
    }

    private void compareXY() {
        int[] resultXY = new int[4];
        int[] initXY = new int[2];
        int[] endXY = new int[2];
        initXY = findXY();
        endXY = findXY();

        if (((initXY[0] == endXY[0]) & (Math.abs(initXY[1] - endXY[1]) == shipType - 1)) || ((initXY[1] == endXY[1]) & (Math.abs(initXY[0] - endXY[0]) == shipType - 1))) {
            this.initY = initXY[0];
            this.initX = initXY[1];
            this.endY = endXY[0];
            this.endX = endXY[1];
            return;
        } else {
            compareXY();
        }

    }

    @Override
    public Ship createShip() {
        int min = 0;
        int max = 0;
        int m = 0;
        int p = 0;

        compareXY();

        if (initX == endX) {
            min = endY;
            max = initY;
            if (initY < endY) {
                min = initY;
                max = endY;
            }
            for (int i = 1; i < shipType - 1; i++) {
                coordY[i] = min++;
                coordX[i] = initX;
            }

            coordY[0] = min;
            coordY[shipType - 1] = max;
            coordX[0] = initX;
            coordX[shipType - 1] = endX;

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
            int k = coordY[shipType - 1] + 1;

            spaceX[shipType * 2] = initX;
            spaceY[shipType * 2] = n;

            spaceX[shipType * 2 + 1] = initX + 1;
            spaceY[shipType * 2 + 1] = n;

            spaceX[shipType * 2 + 2] = initX - 1;
            spaceY[shipType * 2 + 2] = n;

            spaceX[shipType * 2 + 3] = initX;
            spaceY[shipType * 2 + 3] = k;

            spaceX[shipType * 2 + 4] = initX + 1;
            spaceY[shipType * 2 + 4] = k;

            spaceX[shipType * 2 + 5] = initX - 1;
            spaceY[shipType * 2 + 5] = k;

        } else if (initY == endY) {
            min = endX;
            max = initX;
            if (initX < endX) {
                min = initX;
                max = endX;
            }
            for (int i = 1; i < shipType - 1; i++) {
                coordX[i] = min++;
                coordY[i] = initY;
            }

            coordX[0] = min;
            coordX[shipType - 1] = max;
            coordY[0] = initY;
            coordY[shipType - 1] = initY;

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

            spaceX[shipType * 2] = c;
            spaceY[shipType * 2] = initY;

            spaceX[shipType * 2 + 1] = c;
            spaceY[shipType * 2 + 1] = initY + 1;

            spaceX[shipType * 2 + 2] = c;
            spaceY[shipType * 2 + 2] = initY - 1;

            spaceX[shipType * 2 + 3] = l;
            spaceY[shipType * 2 + 3] = initY;

            spaceX[shipType * 2 + 4] = l;
            spaceY[shipType * 2 + 4] = initY + 1;

            spaceX[shipType * 2 + 5] = l;
            spaceY[shipType * 2 + 5] = initY - 1;
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
        int m = 0;
        for (int i = 0; i < shipType; i++) {
            if (coordX[i] == x & coordY[i] == y) {
                m = i;
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
