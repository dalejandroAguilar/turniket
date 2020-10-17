package com.rodion.turniket.kernel;

import com.rodion.turniket.kernel.constants.Direction;
import com.rodion.turniket.kernel.constants.TurnId;

public class Blade extends Node {
    Direction direction;
    TurnId id;

    public Blade(int x, int y, Direction direction, TurnId id) {
        setPosition(x, y);
        this.direction = direction;
        this.id = id;
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
}
