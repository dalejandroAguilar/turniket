package com.rodion.turniket.kernel;

import com.rodion.turniket.kernel.constants.Direction;
import com.rodion.turniket.kernel.constants.Spin;
import com.rodion.turniket.kernel.constants.TurnId;

import java.util.ArrayList;

public class Turnstile extends Node {
    private ArrayList<Blade> blades;
    private TurnId id;
    private Listener listener;

    public Turnstile(Turnstile t) {
        setPosition(t.getX(), t.getY());
        id = TurnId.get(t.id);
        blades = new ArrayList<>();
        for (int i = 0; i < t.blades.size(); i++) {
            blades.add(new Blade(t.blades.get(i)));
        }
    }

    public void set(Turnstile t) {
        setPosition(t.getX(), t.getY());
        id = TurnId.get(t.id);
        for (int i = 0; i < t.blades.size(); i++) {
            blades.get(i).set(t.blades.get(i));
        }
    }

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
            if (board[halfStepY][halfStepX] != null) {
                for (Blade blade2 : blades) {
                    if (blade2.listener != null)
                        blade2.listener.onRotate(spin, Blade.Status.TokenCollision);
                }
                return false;
            }
        }

        for (Blade blade : blades) {
            Direction direction = blade.getDirection().rotate(spin);
            int stepX = getX() + direction.x;
            int stepY = getY() + direction.y;
//            System.out.println(id);
//            System.out.println(stepX+","+stepY);
//            System.out.println(getX()+","+getY());

            if (board[stepY][stepX] != null) {
                if (((Blade) board[stepY][stepX]).getId() != blade.getId()) {
                    for (Blade blade2 : blades) {
                        if (blade2.listener != null)
                            blade2.listener.onRotate(spin, Blade.Status.BladeCollision);
                    }
                    return false;
                }
            }
        }

        for (Blade blade : blades) {
            Direction direction = blade.getDirection().rotate(spin);
            int stepX = getX() + direction.x;
            int stepY = getY() + direction.y;

            if (blade == board[blade.getY()][blade.getX()])
                board[blade.getY()][blade.getX()] = null;

            blade.setDirection(direction);
            board[stepY][stepX] = blade;
            blade.setPosition(stepX, stepY);
            if (blade.listener != null)
                blade.listener.onRotate(spin, Blade.Status.Ok);
        }
        return true;
    }

    public void addListener(Listener listener) {
        this.listener = listener;
    }

    public TurnId getId() {
        return id;
    }

    public interface Listener {
        public void onRotate(Spin spin, Status status);
    }

    public enum Status {
        Ok(0), BladeCollision(1), TokenCollision(2);
        private int value;

        Status(int value) {
        }
    }
}
