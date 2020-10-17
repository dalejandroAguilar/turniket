package com.rodion.turniket.kernel;

import com.rodion.turniket.kernel.constants.Direction;
import com.rodion.turniket.kernel.constants.TokenColor;

public class Token extends Node {
    private TokenColor color;
    public Token(int x, int y, TokenColor color) {
        setPosition(x, y);
        setColor(color);
    }

    public void setColor(TokenColor color) {
        this.color = color;
    }

    public TokenColor getColor() {
        return color;
    }

    public void moveBy(Direction direction) {
        setPosition(getX() + 2 * direction.x, getY() + 2 * direction.y);
    }

}