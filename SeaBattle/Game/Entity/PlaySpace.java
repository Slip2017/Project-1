package com.brainacad.SeaBattle.Game.Entity;

import com.brainacad.SeaBattle.Enums.Condition;

import com.brainacad.SeaBattle.Interfaces.PlaySpaceInterface;
import com.brainacad.SeaBattle.Interfaces.Ship;

import java.util.Arrays;

import static com.brainacad.SeaBattle.Enums.Condition.*;

/**
 * Created by Администратор on 18.01.2016.
 */
public class PlaySpace implements PlaySpaceInterface {
    private static int k;
    private static int m;
    private int objPlayerSize;
    private int objComputerSize;
    private Condition[][] spacePlayer;
    private Condition[][] spaceComputer;
    private Ship[] objPlayer;
    private Ship[] objComputer;

    public PlaySpace() {
    }

    public PlaySpace(int n) {
        this.spacePlayer = new Condition[n][n];
        this.objPlayer = new Ship[n];

        this.spaceComputer = new Condition[n][n];
        this.objComputer = new Ship[n];

    }

    public void cleanSpace() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.spacePlayer[i][j] = Condition._;
                this.spaceComputer[i][j] = Condition._;

            }
        }
    }


    public void setSpacePlayer( Condition cond, int y, int x) {
        spacePlayer[y][x] = cond;
    }

    public void setSpaceComputer(Condition cond, int y, int x) {
        spaceComputer[y][x] = cond;
    }

    public void setObjPlayer(Ship obj) {
        objPlayer[k] = obj;
        ++k;
        objPlayerSize =k;
    }

    public void setObjComputer(Ship obj) {
        objComputer[m] = obj;
        ++m;
        objComputerSize = m;
    }

    public int getObjComputerSize(){
        return objComputerSize;
    }
    public int getObjPlayerSize(){
        return objPlayerSize;
    }

    public int getObjPlayerLength(){
        return objPlayer.length;
    }

    public Condition getSpacePlayer(int y, int x) {
        return spacePlayer[y][x];
    }

    public Condition getSpaceComputer(int y, int x) {
        return spaceComputer[y][x];
    }

    public Ship[] getObjPlayer() {
        return objPlayer;
    }

    public Ship[] getObjComputer() {
        return objComputer;
    }
    public Ship getObjComputer(int i) {
        return objComputer[i];
    }


    public String printSpace() {
        if (objPlayer[0] == null & objComputer[0] == null) {
            cleanSpace();
        } else {

            for (int k=0; k < objPlayerSize; k++) {
                for (int i = 0; i < objPlayer[k].getShipType(); i++) {

                    if (objPlayer[k].getCond(i) == null) {
                        spacePlayer[objPlayer[k].getCoordY(i)][objPlayer[k].getCoordX(i)] = Condition.O;
                    }

                    if(objPlayer[k].getCond(i) != null){
                        spacePlayer[objPlayer[k].getCoordY(i)][objPlayer[k].getCoordX(i)] = objPlayer[k].getCond(i);
                    }

                    if(objComputer[k].getCond(i)  != null){
                        spaceComputer[objComputer[k].getCoordY(i)][objComputer[k].getCoordX(i)] =objComputer[k].getCond(i);
                    }

                }
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\t| a | b | c | d | e | f | g | h | i | j |             \t| a | b | c | d | e | f | g | h | i | j |\n");

        for (int i = 0; i < 10; i++) {
            stringBuilder.append("______________________________________________         ______________________________________________\n");
            stringBuilder.append(i + 1 + "\t| ");

            for (int j = 0; j < spacePlayer[i].length; j++) {
                stringBuilder.append(spacePlayer[i][j] +" | ");
            }
            stringBuilder.append("         ");

            stringBuilder.append(i + 1 + "\t| ");
            for (int j = 0; j < spaceComputer[i].length; j++) {
                stringBuilder.append(spaceComputer[i][j] + " | ");
            }

            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }


}
