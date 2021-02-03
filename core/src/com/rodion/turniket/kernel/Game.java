package com.rodion.turniket.kernel;

import com.rodion.turniket.kernel.constants.Direction;
import com.rodion.turniket.kernel.constants.TokenColor;
import com.rodion.turniket.kernel.constants.TurnId;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Game implements Command {
    private State state;
    private State initState;
    private Character[][] map;
    final private static ArrayList<Node> turnPositions = new ArrayList<>(
            Arrays.asList(new Node(1, 1), new Node(3, 1),
                    new Node(1, 3), new Node(3, 3)));
    final private static Node[] tokenTargets = {new Node(0, 0), new Node(4, 0),
            new Node(4, 4), new Node(0, 4)};
    private Listener listener;

    public Game() {
        initState = new State();
        initState.board = new Node[5][5];
        initState.turnstiles = new Turnstile[TurnId.values().length];
        initState.tokens = new Token[TokenColor.values().length];
        map = new Character[5][5];
        for (TokenColor color : TokenColor.values())
            initState.tokens[color.index] = new Token(-1, -1, color);
        for (TurnId id : TurnId.values()) {
            initState.turnstiles[id.index] = new Turnstile(id);
            initState.turnstiles[id.index].setPosition(turnPositions.get(id.index));
        }
    }

    public boolean move(TokenColor color, Direction dir) {
        Token token = state.tokens[color.index];
        int stepX = token.getX() + 2 * dir.x;
        int stepY = token.getY() + 2 * dir.y;
        int halfStepX = token.getX() + dir.x;
        int halfStepY = token.getY() + dir.y;
        if (!(stepY < 5 && stepX < 5 && stepX >= 0 && stepY >= 0))
            return false;
        if (state.board[halfStepY][halfStepX] == null && state.board[stepY][stepX] == null) {
            state.previousState = new State(state);
            state.board[token.getY()][token.getX()] = null;
            state.board[stepY][stepX] = token;
            token.setPosition(stepX, token.getY() + 2 * dir.y);
            token.listener.onMove(dir, Token.Status.Ok);
            if (isWin())
                listener.onWin();
            state.setStep(state.getSteps()+1);
            return true;
        }
        if (state.board[stepY][stepX] != null && state.board[halfStepY][halfStepX] == null) {
            token.listener.onMove(dir, Token.Status.TokenCollision);
            return false;
        }

        if (state.board[halfStepY][halfStepX] != null)
            if (state.board[halfStepY][halfStepX].getClass() == Blade.class) {
                State dummyPreviousState = new State(state);
                state.board[token.getY()][token.getX()] = null;
                Blade blade = (Blade) state.board[halfStepY][halfStepX];
                TurnId id = blade.getId();
                if (state.turnstiles[id.index].rotate(blade.getDirection().spinValue(dir), state.board)) {
                    state.board[stepY][stepX] = token;
                    token.setPosition(stepX, stepY);
                    token.listener.onMove(dir, Token.Status.Ok);
                    state.previousState = dummyPreviousState;
                    if (isWin())
                        listener.onWin();
                    state.setStep(state.getSteps()+1);
                    return true;
                } else {
                    state.board[token.getY()][token.getX()] = token;
                    token.listener.onMove(dir, Token.Status.BladeTokenCollision);
                    return false;
                }
            }
        return false;
    }

    public void readFile(File file) throws FileNotFoundException {
        Scanner reader = new Scanner(file);
        int i = 0;
        while (i < 5) {
            String line = reader.nextLine();
            int j;
            for (j = 0; j < 5; j++) {
                if (j < line.length())
                    map[i][j] = line.charAt(j);
                else
                    map[i][j] = ' ';
            }
            i++;
        }
        reader.close();
    }

    public void setFromMap() {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++) {
                for (TokenColor color : TokenColor.values())
                    if (map[i][j] == color.value) {
                        initState.tokens[color.index].setPosition(j, i);
                        initState.board[i][j] = initState.tokens[color.index];
                    }
                for (TurnId id : TurnId.values())
                    if (map[i][j] == id.value)
                        if (!turnPositions.contains(Node.dummy(j, i))) {
                            Turnstile turnstile = initState.turnstiles[id.index];
                            Direction direction = Direction.get(j - turnstile.getX(),
                                    i - turnstile.getY());
                            Blade blade = new Blade(j, i, direction, id);
                            turnstile.addBlade(blade);
                            initState.board[i][j] = blade;
                        }
            }
        state = new State(initState);
    }

    public void print() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (state.board[i][j] != null) {
                    if (state.board[i][j].getClass() == Token.class) {
                        System.out.print(((Token) state.board[i][j]).getColor().value);
                    }
                    if (state.board[i][j].getClass() == Blade.class) {
                        System.out.print(((Blade) state.board[i][j]).getId().value);
                    }
                } else if (turnPositions.contains(Node.dummy(j, i))) {
                    System.out.print(turnPositions.indexOf(Node.getDummyNode()) + 1);
                } else
                    System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println("-----");
    }

    public Token[] getTokens() {
        return state.tokens;
    }

    public Turnstile[] getTurnstiles() {
        return state.turnstiles;
    }

    public Token getToken(int i, int j) {
        return (Token) state.board[2 * i][2 * j];
    }

    @Override
    public void undo() {
        if (state.previousState != null) {
            State dummyState = new State(state);
            state.set(state.previousState);
            if (state.nextState == null)
                state.nextState = new State(dummyState);
            else
                state.nextState.set(dummyState);
        }
    }

    @Override
    public void redo() {
        if (state.nextState != null)
            state.set(state.nextState);
    }

    @Override
    public void restart() {
        state.set(initState);
    }

    private boolean isWin() {
        for (int i = 0; i<4; i++) {
            Token token = getTokens()[i];
            if (token.getX() != -1 || token.getY() != -1)
                if (token.getX() != tokenTargets[i].getX() || token.getY() != tokenTargets[i].getY())
                    return false;
        }
        return true;
    }

    public void addListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {
        void onWin();
    }

    public int getSteps(){
        return state.getSteps();
    }
}