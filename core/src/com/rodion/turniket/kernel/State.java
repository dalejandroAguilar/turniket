package com.rodion.turniket.kernel;

public class State {
    public Turnstile[] turnstiles;
    public Token[] tokens;
    public Node[][] board;
    public State nextState;
    public State previousState;
    private int step;

    public State() {
        step = 0;
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

        previousState = s.previousState;
        nextState = s.nextState;
        step = s.step;
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
        step = s.step;
    }

    public int getSteps(){
        return step;
    }
    public void setStep(int step) {
        this.step = step;
    }
}