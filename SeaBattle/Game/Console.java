package com.brainacad.SeaBattle.Game;

import com.brainacad.SeaBattle.Enums.Direction;
import com.brainacad.SeaBattle.Game.Entity.PlaySpace;

import static com.brainacad.SeaBattle.Game.Entity.PlaySpace.*;

import com.brainacad.SeaBattle.Interfaces.GameGUI;
import com.brainacad.SeaBattle.Interfaces.PlaySpaceInterface;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author  Shynkarenko Eduard
 *
 */
public class Console implements GameGUI {



    public static int convertToInt(String x) {
        char[] ch = new char[1];
        ch = x.toCharArray();
        int a = ((int) ch[0]) - 97;
        return a;
    }

    public int getShipType() {
        Scanner scan1 = new Scanner(System.in);
        System.out.println("Введите тип корабля 4, 3, 2, 1");

        return scan1.nextInt();
    }


    public int[] getInitShipCoord() {
        Scanner scan2 = new Scanner(System.in);
        boolean a = true;
        int[] coord = new int[2];
        System.out.println("Введите начальные координаты корабля");
        System.out.println("цифра");
        while (a) {
            try {
                a = false;
                coord[0] = scan2.nextInt() - 1;

            } catch (InputMismatchException e) {
                System.out.println("НЕВЕРНЫЙ ФОРМАТ ДАННЫХ!!! ПОВТОРИТЕ ВВОД");
                Scanner scan3 = new Scanner(System.in);
                try {
                    coord[0] = scan3.nextInt() - 1;
                } catch (InputMismatchException e1) {
                    a = true;
                    continue;
                }

            }
        }
        Scanner scan4 = new Scanner(System.in);
        System.out.println("символ поля a...j");
        coord[1] = convertToInt(scan4.next());

        return coord;
    }

    public Direction getShipDirection() {
        String str;
        Direction dir = null;
        System.out.println("Введите направление n - север, s - юг, w - запад, e - восток");
        Scanner scan1 = new Scanner(System.in);
        str = scan1.next();

        switch (str) {
            case "n":
                dir = Direction.n;
                break;
            case "s":
                dir = Direction.s;
                break;
            case "w":
                dir = Direction.w;
                break;
            case "e":
                dir = Direction.e;
                break;
            default:
                break;
        }

        return dir;
    }


    public int[] doShot(PlaySpaceInterface space) {
        boolean b=true;
        Scanner scan1 = new Scanner(System.in);
        int[] result = new int[2];
        for (int i = 0; i < 10; i++)
            System.out.println("\n");

        System.out.println(space.printSpace());
        System.out.println("Введите координаты выстрела");
        System.out.println("цифра:");

        while (b) {
            try {
                b = false;
                result[0] = scan1.nextInt() - 1;

            } catch (InputMismatchException e) {
                System.out.println("НЕВЕРНЫЙ ФОРМАТ ДАННЫХ!!! ПОВТОРИТЕ ВВОД");
                Scanner scan3 = new Scanner(System.in);
                try {
                    result[0] = scan3.nextInt() - 1;
                } catch (InputMismatchException e1) {
                    b = true;
                    continue;
                }
            }
        }
        Scanner scan4 = new Scanner(System.in);
        System.out.println("символ поля a...j");
        result[1] = convertToInt(scan4.next());
        return result;
    }


}

