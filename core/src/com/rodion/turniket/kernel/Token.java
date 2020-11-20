package com.rodion.turniket.kernel;

import com.rodion.turniket.kernel.constants.Direction;
import com.rodion.turniket.kernel.constants.TokenColor;

public class
Token extends Node {
    private TokenColor color;
    Listener listener;

    public Token(int x, int y, TokenColor color) {
        setPosition(x, y);
        setColor(color);
        listener = null;
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


    public int getj() {
        if (super.getX() == -1)
            return -1;
        return super.getX() / 2;
    }

    public int geti() {
       if (super.getY() == -1)
            return -1;
        return super.getY() / 2;
    }

      public void addListener(Listener listener){
        this.listener = listener;
    }

    public interface Listener{
        public void onMove(Direction direction, Status status);
    }

    public enum Status{
        Ok(0), BladeTokenCollision(1), TokenCollision(2);
        private int value;
        Status(int value){}
    }
}