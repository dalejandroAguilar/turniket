package com.rodion.turniket.kernel;

import com.badlogic.gdx.files.FileHandle;
import com.rodion.turniket.kernel.constants.Direction;
import com.rodion.turniket.kernel.constants.TokenColor;
import com.rodion.turniket.kernel.constants.TurnId;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

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
    private Solution solutionRead, solutionWrite;

    public Game() {
        initState = new State();
        initState.board = new Node[5][5];
        initState.turnstiles = new Turnstile[TurnId.values().length];
        initState.tokens = new Token[TokenColor.values().length];
        map = new Character[5][5];
        for (TokenColor color : TokenColor.values()) {
            System.out.println("color: " + color);
            initState.tokens[color.index] = new Token(-1, -1, color);
        }
        for (TurnId id : TurnId.values()) {
            initState.turnstiles[id.index] = new Turnstile(id);
            initState.turnstiles[id.index].setPosition(turnPositions.get(id.index));
        }
        solutionRead = new Solution();
        solutionWrite = new Solution();
    }

    public boolean move(TokenColor color, Direction dir) {
        Token token = state.tokens[color.index];
        int stepX = token.getX() + 2 * dir.x;
        int stepY = token.getY() + 2 * dir.y;
        int halfStepX = token.getX() + dir.x;
        int halfStepY = token.getY() + dir.y;
        if (!(stepY < 5 && stepX < 5 && stepX >= 0 && stepY >= 0))
            return false;
        if (!(halfStepY < 5 && halfStepX < 5 && halfStepX >= 0 && halfStepY >= 0))
            return false;

        Step step = Step.getStep(color, dir, this);

        if (state.board[halfStepY][halfStepX] == null && state.board[stepY][stepX] == null) {
            state.previousState = new State(state);
            state.board[token.getY()][token.getX()] = null;
            state.board[stepY][stepX] = token;
            token.setPosition(stepX, stepY);
            if (token.listener != null)
                token.listener.onMove(dir, Token.Status.Ok);
            if (isWin()) {
                listener.onWin();
            }
            state.setStep(state.getSteps() + 1);
            solutionWrite.writeStep(step);
//            step.print(Sys);

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
                if (state.turnstiles[id.index].rotate(blade.getDirection().spinValue(dir),
                        state.board)) {
                    state.board[stepY][stepX] = token;
                    token.setPosition(stepX, stepY);
                    if (token.listener != null)
                        token.listener.onMove(dir, Token.Status.Ok);
                    state.previousState = dummyPreviousState;
                    if (isWin())
                        listener.onWin();
                    state.setStep(state.getSteps() + 1);
                    solutionWrite.writeStep(step);
//                    step.print();
                    return true;
                } else {
                    state.board[token.getY()][token.getX()] = token;
                    if (token.listener != null)
                        token.listener.onMove(dir, Token.Status.BladeTokenCollision);
                    return false;
                }
            }
        return false;
    }

    public void readFile(FileHandle file) {
        int i = 0;
        String text = file.readString();
        String[] lines = text.split("\n");
        while (i < 5) {
            int j;
            for (j = 0; j < 5; j++) {
                if (j < lines[i].length())
                    map[i][j] = lines[i].charAt(j);
                else
                    map[i][j] = ' ';
            }
            i++;
        }
        System.out.println(lines.length );

        for (int j = 5; j < lines.length; j++){
            if (lines[j].startsWith("Solution")){
                if(lines[j].split(" ").length > 1){
                String[] strSolution = new String[2];
                strSolution[0] = lines[j];
                strSolution[1] = lines[j+1];
                System.out.println(strSolution[0]);
                System.out.println(strSolution[1]);
                solutionRead.readSolution(strSolution);
                }
            }
        }

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

    public void printInit(PrintStream printStream) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (initState.board[i][j] != null) {
                    if (initState.board[i][j].getClass() == Token.class)
                        printStream.print(((Token) initState.board[i][j]).getColor().value);
                    if (initState.board[i][j].getClass() == Blade.class)
                        printStream.print(((Blade) initState.board[i][j]).getId().value);
                } else if (TurnId.get(j, i) != null) {
                    printStream.print(TurnId.get(j, i).index + 1);
                } else
                    printStream.print(" ");
            }
            printStream.println();
        }
        printStream.println("-----");
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
        solutionRead.goBackward();
    }

    @Override
    public void redo() {
        if (state.nextState != null)
            state.set(state.nextState);
        solutionRead.goForward();
    }

    @Override
    public void restart() {
        state.set(initState);
    }

    private boolean isWin() {
        for (int i = 0; i < 4; i++) {
            Token token = getTokens()[i];
            if (token.getX() != -1 && token.getY() != -1)
                if (token.getX() != TokenColor.getTarget(token.getColor()).getX()
                        || token.getY() != TokenColor.getTarget(token.getColor()).getY())
                    return false;
        }
        return true;
    }

    public void removeListener() {
        this.listener = null;
    }

    public void addListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {
        void onWin();
    }

    public int getSteps() {
        return state.getSteps();
    }

    public State getState() {
        return state;
    }

    public void saveSolution(FileHandle fileHandle) throws FileNotFoundException {
        PrintStream printStream = new PrintStream(String.valueOf(fileHandle));
        printInit(printStream);
        solutionWrite.print(printStream);
        solutionRead.readSolution(solutionWrite);
    }

    public void loadSolution() {
//        solutionRead.readSolution(solutionWrite);
        solutionRead.goToBegin();
    }

    public boolean moveFromSolution() {
        Step step = solutionRead.getStep();
        solutionRead.goForward();
        step.print(System.out);
        System.out.println("Color " + step.getTokenColor(state) + " " + step.getDirection());
        move(step.getTokenColor(state), step.getDirection());
        return true;
    }

    public void undoFromSolution() {
        solutionRead.goBackward();
        undo();
    }
}