package com.rodion.turniket.kernel;

import com.rodion.turniket.kernel.constants.Direction;
import com.rodion.turniket.kernel.constants.TokenColor;

import java.io.PrintStream;

public class Step {
    private TokenColor tokenColor;
    private Direction direction;

    private int start;
    private int end;

    public static Step getStep(TokenColor tokenColor, Direction direction, Game game) {
        int startX = game.getState().tokens[tokenColor.index].getX();
        int startY = game.getState().tokens[tokenColor.index].getY();
        int start = (3 * startY) / 2 + startX / 2 + 1;
        int end = start + direction.x + direction.y * 3;
        return new Step(start, end);
    }

    public Step(TokenColor tokenColor, Direction direction) {
        this.tokenColor = tokenColor;
        this.direction = direction;
    }

    public Step(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public void print(PrintStream stream) {
        stream.print(start + ">" + end);
    }

    public static Step string2Step(String str) {
        int start = Integer.parseInt(str.substring(0, 1));
        int end = Integer.parseInt(str.substring(2, 3));
        return new Step(start, end);
    }

    public TokenColor getTokenColor(State state) {
        int startX = (start - 1) % 3 * 2;
        int startY = (start - 1) / 3 * 2;
        Token token = (Token) state.board[startY][startX];
        return token.getColor();
    }

    public Direction getDirection() {
        int startX = (start - 1) % 3 ;
        int startY = (start - 1) / 3 ;
        int endX = (end - 1) % 3 ;
        int endY = (end - 1) / 3;
        return Direction.get(endX - startX, endY - startY);
    }

            public String getString(){
            String str = start + ">" + end;
            return str;
        }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
