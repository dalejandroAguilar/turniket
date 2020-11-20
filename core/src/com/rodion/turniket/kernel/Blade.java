package com.rodion.turniket.kernel;

import com.rodion.turniket.kernel.constants.Direction;
import com.rodion.turniket.kernel.constants.Spin;
import com.rodion.turniket.kernel.constants.TurnId;

public class Blade extends Node {
    Direction direction;
    TurnId id;
    Listener listener;

    public Blade(int x, int y, Direction direction, TurnId id) {
        setPosition(x, y);
        this.direction = direction;
        this.id = id;
        listener = null;
    }

    public Direction getDirection() {
        return direction;
    }

    public TurnId getId() {
        return id;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void addListener(Listener listener){
        this.listener = listener;
    }

    public interface Listener{
        public void onRotate(Spin spin, Status status);
    }

    public enum Status{
        Ok(0), BladeCollision(1), TokenCollision(2);
        private int value;
        Status(int value){}
    }
}
