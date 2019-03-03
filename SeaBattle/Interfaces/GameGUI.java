package com.brainacad.SeaBattle.Interfaces;

import com.brainacad.SeaBattle.Enums.Direction;

/**
 * @author  Shynkarenko Eduard
 *
 */

public interface GameGUI {
        int getShipType();
        int[] getInitShipCoord();
        Direction  getShipDirection();
        int[] doShot(PlaySpaceInterface space);

}

