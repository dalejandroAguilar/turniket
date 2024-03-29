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

    public void set(Token t) {
        setPosition(t.getX(), t.getY());
        setColor(t.getColor());
    }

    public Token(Token t) {
        setPosition(t.getX(), t.getY());
        setColor(t.getColor());
//        listener = t.listener;
//        System.out.println("token null");
        listener = null;

//        final  Listener tlistener = t.listener;

//        listener = new Listener() {
//            @Override
//            public void onMove(Direction direction, Status status) {
//                System.out.println("lisnter. move");
////                 tlistener.onMove(direction, status);
//            }
//        };
//        addListener(listener);
//        listener = t.listener;
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

    public void addListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {
        public void onMove(Direction direction, Status status);
    }

    public enum Status {
        Move(0),MoveAndRotate(1), BladeTokenCollision(2), TokenCollision(3),
        MoveAndFit(4), MoveAndRotateAndFit(5), Nothing(6);
        private int value;
        Status(int value) {
        }
    }

    @Override
    public boolean equals(Object o) {
        Token token = (Token) o;
        if (color == token.color && getX() == token.getX() && getY() == token.getY())
            return true;
        return false;
    }
}