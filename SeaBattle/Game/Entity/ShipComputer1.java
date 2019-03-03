package com.brainacad.SeaBattle.Game.Entity;

import com.brainacad.SeaBattle.Enums.Condition;
import com.brainacad.SeaBattle.Interfaces.Ship;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Администратор on 24.01.2016.
 */
public class ShipComputer1 implements Ship {
    private int shipType;
    private int i = 0;
    private Condition cond;
    private int coordX;
    private int coordY;
    private int[] spaceX;
    private int[] spaceY;
    private PlaySpace space;

    public ShipComputer1(int shipType, PlaySpace space) {
        this.shipType = shipType;
        this.spaceX = new int[shipType * 2 + 6];
        this.spaceY = new int[shipType * 2 + 6];
        this.space = space;
    }

    private int[] findXY() {
        //Ship[] array = space.getObjComputer();
        int[] result = new int[2];
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

    @Override
    public Ship createShip() {
        int p = 0;
        int[] XY = new int[2];
        XY = findXY();
        coordY = XY[0];
        coordX = XY[1];

        for (int i = 0; i < shipType; i++) {
            p = coordY;
            for (int j = i * 2; j < (i * 2) + 2; j++) {
                if (j % 2 == 0) {
                    spaceX[j] = coordX + 1;
                    spaceY[j] = p;
                } else {
                    spaceX[j] = coordX - 1;
                    spaceY[j] = p;
                }
            }
        }

        int n = coordY - 1;
        int m = coordY + 1;

        spaceX[shipType * 2] = coordX;
        spaceY[shipType * 2] = n;

        spaceX[shipType * 2 + 1] = coordX + 1;
        spaceY[shipType * 2 + 1] = n;

        spaceX[shipType * 2 + 2] = coordX - 1;
        spaceY[shipType * 2 + 2] = n;

        spaceX[shipType * 2 + 3] = coordX;
        spaceY[shipType * 2 + 3] = m;

        spaceX[shipType * 2 + 4] = coordX + 1;
        spaceY[shipType * 2 + 4] = m;

        spaceX[shipType * 2 + 5] = coordX - 1;
        spaceY[shipType * 2 + 5] = m;

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


/*class TestComputer1 {
    public static void main(String[] args) {
        ShipComputer1 ob = new ShipComputer1(1, new PlaySpace(10));
        ob.createShip();
        System.out.println("Y "+ob.coordY);
        System.out.println("X "+ob.coordX);

        System.out.println("Y "+Arrays.toString(ob.spaceY));
        System.out.println("X "+ Arrays.toString(ob.spaceX));
    }
}*/