package com.brainacad.SeaBattle.Game.Controller;

import com.brainacad.SeaBattle.Enums.Condition;
import com.brainacad.SeaBattle.Enums.Direction;
import com.brainacad.SeaBattle.Game.Console;
import com.brainacad.SeaBattle.Game.Entity.*;
import com.brainacad.SeaBattle.Interfaces.GameController;
import com.brainacad.SeaBattle.Interfaces.GameGUI;
import com.brainacad.SeaBattle.Interfaces.PlaySpaceInterface;
import com.brainacad.SeaBattle.Interfaces.Ship;

import java.util.Arrays;
import java.util.Random;

/**
 * @author  Shynkarenko Eduard
 *
 */
public class Controller implements GameController {


    private GameGUI console;
    private PlaySpace space;

    public Controller() {
    }

    public Controller(GameGUI console, PlaySpace space) {
        this.console = console;
        this.space = space;
    }

    @Override
    public void startGame() {
        setShips();
        nextTurn(true);
    }

    public void setShips() {

        System.out.println(space.printSpace());
        for (int i = 0; i < space.getObjPlayerLength(); i++) {

            int shipType = console.getShipType();
            int initXY[];
             initXY = console.getInitShipCoord();
            Direction dir = null;

            if (shipType != 1) {
                dir = console.getShipDirection();
            }

            switch (shipType) {
                case 4:
                    space.setObjPlayer(new ShipPlayer4(shipType, initXY[0], initXY[1], dir).createShip());
                    space.setObjComputer(new ShipComputer4(shipType, space).createShip());
                    break;
                case 3:
                    space.setObjPlayer(new ShipPlayer3(shipType, initXY[0], initXY[1], dir).createShip());
                    space.setObjComputer(new ShipComputer3(shipType, space).createShip());
                    break;
                case 2:
                    space.setObjPlayer(new ShipPlayer2(shipType, initXY[0], initXY[1], dir).createShip());
                    space.setObjComputer(new ShipComputer2(shipType, space).createShip());
                    break;
                case 1:
                    space.setObjPlayer(new ShipPlayer1(shipType, initXY[0], initXY[1]).createShip());
                    space.setObjComputer(new ShipComputer1(shipType, space).createShip());
                    break;
            }
            System.out.println(space.printSpace());
        }
    }


    public void nextTurn(boolean choice) {
        int[] shot1 = new int[2];
        int[] shot2 = new int[2];

        while (!(isComputerWins() | isPlayerWins())) {

            if (choice == true) {
                shot1 = playerShot();
                if (checkConditionComputer(shot1[0], shot1[1])) {
                    choice = true;
                } else {
                    choice = false;
                }
                continue;
            } else {
                shot2 = computerShot();
                if (checkConditionPlayer(shot2[0], shot2[1])) {
                    choice = false;
                }else {
                    choice = true;
                }
                continue;
            }
        }

        for (int i = 0; i < 10; i++)
            System.out.println("\n");

        System.out.println(space.printSpace());

        if(isPlayerWins()){
            System.out.println("Player wins");
        }else{
            System.out.println("Computer wins");
        }
    }

    public int[] playerShot() {
        int[] shots = console.doShot(space);
        return shots;
    }

    public int[] computerShot() {
        int[] shots = new int[2];
        Random random = new Random();

        int X = random.nextInt(10);
        int Y = random.nextInt(10);

        shots[0] = Y;
        shots[1] = X;
        return shots;
    }

    public boolean checkConditionComputer(int y, int x) {
        Ship[] array = space.getObjComputer();
        for (Ship obj : array) {
            for (int i = 0; i < obj.getShipType(); i++) {
                if (obj.getCoordX(i) == x & obj.getCoordY(i) == y) {
                    obj.setCond(Condition.D, y, x);
                    return true;
                }
            }
        }
        space.setSpaceComputer(Condition.M, y, x);
        return false;
    }

    public boolean checkConditionPlayer(int y, int x) {
        Ship[] array = space.getObjPlayer();
        for (Ship obj : array) {
            for (int i = 0; i < obj.getShipType(); i++) {
                if (obj.getCoordX(i) == x & obj.getCoordY(i) == y) {
                    obj.setCond(Condition.D, y, x);
                    return true;
                }
            }
        }

        space.setSpacePlayer(Condition.M, y, x);
        return false;

    }

    public boolean isComputerWins() {
        Ship[] array = space.getObjPlayer();
        for (Ship obj : array) {
            for (int i = 0; i < obj.getShipType(); i++) {
                if (obj.getCond(i) != Condition.D) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isPlayerWins() {
        Ship[] array = space.getObjComputer();
        for (Ship obj : array) {
            for (int i = 0; i < obj.getShipType(); i++) {
                if (obj.getCond(i) != Condition.D) {
                    return false;
                }
            }
        }

        return true;
    }




}

