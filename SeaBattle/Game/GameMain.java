package com.brainacad.SeaBattle.Game;


import com.brainacad.SeaBattle.Game.Controller.Controller;
import com.brainacad.SeaBattle.Game.Entity.PlaySpace;
import com.matrix.games.xzero.controller.XZeroController;
import com.matrix.games.xzero.gui.console.Console2;

import java.util.Scanner;

/**
 * @author  Shynkarenko Eduard
 *
 */
public class GameMain {

    public static void main(String[] args) {
        Controller cont = new Controller(new Console(), new PlaySpace(10));
        cont.startGame();

    }
}



