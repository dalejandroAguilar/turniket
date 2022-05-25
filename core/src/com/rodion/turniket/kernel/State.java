package com.rodion.turniket.kernel;

import com.rodion.turniket.kernel.constants.TurnId;

import java.io.PrintStream;

public class State {
    public Turnstile[] turnstiles;
    public Token[] tokens;
    public Node[][] board;
    public State nextState;
    public State previousState;
    private int nSteps;
    public Step step;

    public State() {
        nSteps = 0;
    }

    public State(State s) {
        board = new Node[s.board.length][s.board[0].length];
        turnstiles = new Turnstile[s.turnstiles.length];
        for (int i = 0; i < turnstiles.length; i++) {
            turnstiles[i] = new Turnstile(s.turnstiles[i]);
            for (Blade blade : turnstiles[i].getBlades())
                board[blade.getY()][blade.getX()] = blade;
        }
        tokens = new Token[s.tokens.length];

        for (int i = 0; i < tokens.length; i++) {
            tokens[i] = new Token(s.tokens[i]);
            if (tokens[i].getX() != -1 || tokens[i].getY() != -1)
                board[tokens[i].getY()][tokens[i].getX()] = tokens[i];
        }

//        step = new Step();
        step = s.step;
        previousState = s.previousState;
        nextState = s.nextState;
        nSteps = s.nSteps;
    }

    public void set(State s) {
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++)
                board[i][j] = null;

        for (int i = 0; i < turnstiles.length; i++) {
            turnstiles[i].set(s.turnstiles[i]);
            for (Blade blade : turnstiles[i].getBlades())
                board[blade.getY()][blade.getX()] = blade;
        }
        for (int i = 0; i < tokens.length; i++) {
            tokens[i].set(s.tokens[i]);
            if (tokens[i].getX() != -1 || tokens[i].getY() != -1)
                board[tokens[i].getY()][tokens[i].getX()] = tokens[i];
        }
        previousState = s.previousState;
        nextState = s.nextState;
        nSteps = s.nSteps;
    }

    public int getSteps(){
        return nSteps;
    }
    public void setnSteps(int nSteps) {
        this.nSteps = nSteps;
    }

    public void print(PrintStream printStream) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j] != null) {
                    if (board[i][j].getClass() == Token.class)
                        printStream.print(((Token) board[i][j]).getColor().value);
                    if (board[i][j].getClass() == Blade.class)
                        printStream.print(((Blade) board[i][j]).getId().value);
                } else if (TurnId.get(j, i) != null) {
                    printStream.print(TurnId.get(j, i).index + 1);
                } else
                    printStream.print(" ");
            }
                   printStream.println();
        }
        printStream.println("-----");
    }

    @Override
    public boolean equals(Object o) {
        State state = (State) o;
        for (int i = 0; i < turnstiles.length; i++) {
            Turnstile turnstileA = turnstiles[i];
            Turnstile turnstileB = state.turnstiles[i];

            if (!turnstileA.equals(turnstileB)) {
                return false;
            }
        }
        for (int i = 0; i < tokens.length; i++) {
            Token tokenA = tokens[i];
            Token tokenB = state.tokens[i];
            if (!tokenA.equals(tokenB))
                return false;
        }
        return true;
    }

//    public Step getStep() {
//        return step;
//    }
//
//    public void setStep(Step step) {
//        this.step = step;
//    }
}