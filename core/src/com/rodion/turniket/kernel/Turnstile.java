package com.rodion.turniket.kernel;

import com.rodion.turniket.kernel.constants.Direction;
import com.rodion.turniket.kernel.constants.Spin;
import com.rodion.turniket.kernel.constants.TurnId;

import java.util.ArrayList;

public class Turnstile extends Node {
    private ArrayList<Blade> blades;
    private TurnId id;
    public Turnstile(TurnId id) {
        this.id = id;
        blades = new ArrayList<>();
    }

    public void addBlade(Blade blade) {
        blades.add(blade);
    }

    public ArrayList<Blade> getBlades() {
        return blades;
    }

    public void setBlades(ArrayList<Blade> blades) {
        this.blades = blades;
    }

    public boolean rotate(Spin spin, Node[][] board) {
        for (Blade blade : blades) {
            Direction direction = blade.getDirection().rotate(spin);
            int halfStepX = blade.getX() + direction.x;
            int halfStepY = blade.getY() + direction.y;
            int stepX = getX() + direction.x;
            int stepY = getY() + direction.y;
            if (board[halfStepY][halfStepX] != null) {
//                blade.listener.onRotate(spin);
                return false;
            }
            if (board[stepY][stepX] != null) {
                if (((Blade) board[stepY][stepX]).getId() != blade.getId()) {
//                    blade.listener.onRotate(spin);
                    return false;
                }
            }
        }
        for (Blade blade : blades) {
            Direction direction = blade.getDirection().rotate(spin);
            int stepX = getX() + direction.x;
            int stepY = getY() + direction.y;
            blade.setDirection(direction);
            board[blade.getY()][blade.getX()] = null;
            board[stepY][stepX] = blade;
            blade.setPosition(stepX, stepY);
            if(blade.listener != null)
                blade.listener.onRotate(spin);
        }
        return true;
    }
}
