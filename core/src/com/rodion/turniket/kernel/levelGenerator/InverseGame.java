package com.rodion.turniket.kernel.levelGenerator;

import com.rodion.turniket.kernel.Blade;
import com.rodion.turniket.kernel.Node;
import com.rodion.turniket.kernel.State;
import com.rodion.turniket.kernel.Step;
import com.rodion.turniket.kernel.Token;
import com.rodion.turniket.kernel.Turnstile;
import com.rodion.turniket.kernel.constants.Direction;
import com.rodion.turniket.kernel.constants.TokenColor;
import com.rodion.turniket.kernel.constants.TurnId;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class InverseGame {
    private State state;
    private State initState;
    private static Random random = new Random();
    private int maxNDummyTokens;
    private int maxNTokens;
    private int maxNBlades;
    private Character[][] map;
    final private static ArrayList<Node> turnPositions = new ArrayList<>(
            Arrays.asList(new Node(1, 1), new Node(3, 1),
                    new Node(1, 3), new Node(3, 3)));

    public InverseGame() {
        map = new Character[5][5];
        initState = new State();
        initState.board = new Node[5][5];
        initState.turnstiles = new Turnstile[TurnId.values().length];
        initState.tokens = new Token[TokenColor.values().length];
        maxNDummyTokens = 4;
        maxNTokens = 4;
        maxNBlades = 12;

        for (TokenColor color : TokenColor.values()) {
            initState.tokens[color.index] = new Token(-1, -1, color);
        }

        for (TurnId id : TurnId.values()) {
            initState.turnstiles[id.index] = new Turnstile(id);
            initState.turnstiles[id.index].setPosition(TurnId.getPosition(id));
        }
    }

    public InverseGame setMaxNDummyTokens(int maxNDummyTokens) {
        this.maxNDummyTokens = maxNDummyTokens;
        return this;
    }

    public InverseGame setMaxNTokens(int maxNTokens) {
        this.maxNTokens = maxNTokens;
        return this;
    }

    public InverseGame setMaxNBlades(int maxNBlades) {
        this.maxNBlades = maxNBlades;
        return this;
    }

    public void generate() {
        int nTokens = 0;
        for (TokenColor color : TokenColor.values()) {
            if (color.index > 3 || nTokens > maxNTokens)
                break;
            if (random.nextBoolean()) {
                Token token = initState.tokens[color.index];
                token.setPosition(TokenColor.getTarget(color));
                initState.board[token.getY()][token.getX()] = token;
                    nTokens++;
            }
        }
        ArrayList<TurnId> stack = new ArrayList();
        int nBlades = 0;
        outer:
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++) {
                stack.clear();
                stack.add(null);
                for (Direction direction : Direction.values()) {
                    TurnId id = TurnId.get(j + direction.x, i + direction.y);
                    if (id != null)
                        stack.add(id);
                }
                TurnId selection = stack.get(random.nextInt(stack.size()));
                if (selection != null) {
                    if (++nBlades > maxNBlades)
                        break outer;
                    Blade blade = new Blade();
                    blade.setPosition(j, i);
                    blade.setId(selection);
                    blade.setDirection(Direction.get(blade.getX() -
                            initState.turnstiles[selection.index].getX(), blade.getY() -
                            initState.turnstiles[selection.index].getY()));
                    initState.turnstiles[selection.index].addBlade(blade);
                    initState.board[i][j] = blade;
                }
            }

        int indexColor = 4;
        outer:
        for (int i = 0; i < 5; i += 2) {
            for (int j = 0; j < 5; j += 2) {
                if (indexColor > 4 + maxNDummyTokens - 1)
                    break outer;
                if (initState.board[i][j] != null) {
                    if (random.nextBoolean()) {
                        initState.tokens[indexColor].setPosition(j, i);
                        initState.board[i][j] = initState.tokens[indexColor];
                        indexColor++;
                    }
                }
            }
        }
        state = new State(initState);
    }

    public boolean reverseMove(TokenColor color, Direction dir, boolean isAff, Direction dirAff) {
        Token token = state.tokens[color.index];
        int x = token.getX();
        int y = token.getY();
        if (x == -1 || y == -1)
            return false;
        int stepX = x + 2 * dir.x;
        int stepY = y + 2 * dir.y;
        int halfStepX = x + dir.x;
        int halfStepY = y + dir.y;

        if (!(stepY < 5 && stepX < 5 && stepX >= 0 && stepY >= 0))
            return false;

        if (state.board[halfStepY][halfStepX] == null && state.board[stepY][stepX] == null) {
            state.previousState = new State(state);
            state.board[y][x] = null;
            state.board[stepY][stepX] = token;
            token.setPosition(stepX, stepY);
            if (isAff) {
                int affX = x + dirAff.x;
                int affY = y + dirAff.y;
                if (affY < 5 && affX < 5 && affX >= 0 && affY >= 0)
                    if (state.board[affY][affX] != null) {
                        Direction oppDir = dirAff.getOpposite();
                        Blade blade = (Blade) state.board[affY][affX];
                        if (blade.getDirection() == dir.getOpposite()) {
                            TurnId id = blade.getId();
                            state.turnstiles[id.index].rotate(blade.getDirection().spinValue(oppDir),
                                    state.board);
                        }
                    }
            }
            state.step = new Step(color,dir.getOpposite());
            state.setnSteps(state.getSteps() + 1);
            crop();
            return true;
        }

        if (state.board[halfStepY][halfStepX] != null && isAff) {
            int affX = x + dirAff.x;
            int affY = y + dirAff.y;
            if (affY < 5 && affX < 5 && affX >= 0 && affY >= 0)
                if (state.board[affY][affX] != null) {
                    State dummyPreviousState = new State(state);
                    Blade blade = (Blade) state.board[halfStepY][halfStepX];
                    TurnId id = blade.getId();
                    Blade bladeAff = (Blade) state.board[affY][affX];
                    TurnId idAff = bladeAff.getId();
                    if (blade.getDirection() == dir.getOpposite()) {
                        if (id == idAff) {
                            state.board[y][x] = null;
                            if (state.turnstiles[id.index].rotate(blade.getDirection().spinValue(dir),
                                    state.board)) {
                                token.setPosition(stepX, stepY);
                                state.board[stepY][stepX] = token;
                                state.previousState = dummyPreviousState;
                                state.step = new Step(color,dir.getOpposite());
                                crop();
                                return true;
                            } else {
                                state.board[y][x] = token;
                            }
                        }
                    }
                }
        }
        return false;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void print(PrintStream printStream) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (state.board[i][j] != null) {
                    if (state.board[i][j].getClass() == Token.class)
                        printStream.print(((Token) state.board[i][j]).getColor().value);
                    if (state.board[i][j].getClass() == Blade.class)
                        printStream.print(((Blade) state.board[i][j]).getId().value);
                } else if (TurnId.get(j, i) != null) {
                    printStream.print(TurnId.get(j, i).index + 1);
                } else
                    printStream.print(" ");
            }
            printStream.println();
        }
        printStream.println("-----");
    }

    public void readFile(String[] map) {
        int i = 0;
        while (i < 5) {
            int j;
            for (j = 0; j < 5; j++) {
                if (j < map[i].length())
                    this.map[i][j] = map[i].charAt(j);
                else
                    this.map[i][j] = ' ';
            }
            i++;
        }
    }

    public void setMap(Character[][] map) {
        this.map = map;
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

    public void crop() {
        State previousState = new State(state);
        while (!previousState.equals(initState)){
            previousState = previousState.previousState;
            if (state.equals(previousState)){
                state = previousState;
                return;
            }
        }
    }
    public void printSolution(PrintStream printStream) {
        State previousState = new State(state);
        int i = 0 ;
//        previousState.print(printStream);
//        previousState.step.print2ln(printStream);
        do{
            previousState.print(printStream);
            previousState.step.print2ln(printStream);
            previousState = previousState.previousState;
//            System.out.println(i++);
        } while (!previousState.equals(initState));
//        previousState.step.print2ln(printStream);
        initState.print(printStream);
        printStream.println();
    }
}


