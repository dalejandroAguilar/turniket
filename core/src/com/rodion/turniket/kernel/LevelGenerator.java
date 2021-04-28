package com.rodion.turniket.kernel;

import com.rodion.turniket.kernel.constants.Direction;
import com.rodion.turniket.kernel.constants.TokenColor;
import com.rodion.turniket.kernel.constants.TurnId;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class LevelGenerator {
    public Turnstile[] turnstiles;
    public Token[] tokens;
    public Node[][] board;
    private static Random random = new Random();
    private int maxNDummyTokens;
    private int maxNTokens;
    private int maxNBlades;

    public LevelGenerator() {
        maxNDummyTokens = 4;
        maxNTokens = 4;
        maxNBlades = 12;
        board = new Node[5][5];
        turnstiles = new Turnstile[TurnId.values().length];
        tokens = new Token[TokenColor.values().length];
        for (TokenColor color : TokenColor.values()) {
            tokens[color.index] = new Token(-1, -1, color);
        }

        for (TurnId id : TurnId.values()) {
            turnstiles[id.index] = new Turnstile(id);
            turnstiles[id.index].setPosition(TurnId.getPosition(id));
        }
    }

    public LevelGenerator setMaxNDummyTokens(int maxNDummyTokens) {
        this.maxNDummyTokens = maxNDummyTokens;
        return this;
    }

    public LevelGenerator setMaxNTokens(int maxNTokens) {
        this.maxNTokens = maxNTokens;
        return this;
    }

    public LevelGenerator setMaxNBlades(int maxNBlades) {
        this.maxNBlades = maxNBlades;
        return this;
    }

    public void generate() {
        int nTokens = 0;
        for (TokenColor color : TokenColor.values()) {
            if (color.index > 3 || nTokens > maxNTokens)
                break;
            if (random.nextBoolean()) {
                tokens[color.index].setPosition(TokenColor.getTarget(color));
                board[tokens[color.index].getY()][tokens[color.index].getX()]
                        = tokens[color.index];
                nTokens++;
            }
        }

        ArrayList<TurnId> stack = new ArrayList();

        int nBlades = 0;
        outer:
        for (int i = 0; i < 5; i++) {
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
                            turnstiles[selection.index].getX(), blade.getY() -
                            turnstiles[selection.index].getY()));
                    turnstiles[selection.index].addBlade(blade);
                    board[i][j] = blade;
                }
            }
        }

        int indexColor = 4;
        outer:
        for (int i = 0; i < 5; i+=2) {
            for (int j = 0; j < 5; j+=2) {
                if (indexColor > 4 + maxNDummyTokens - 1)
                    break outer;
                if (board[i][j] != null) {
                    if (random.nextBoolean()) {
                        tokens[indexColor].setPosition(j, i);
                        board[i][j] = tokens[indexColor];
                        indexColor++;
                    }
                }
            }
        }
    }

    public boolean reverseMove(TokenColor color, Direction dir, boolean isAff, Direction dirAff) {
        Token token = tokens[color.index];
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

        if (board[halfStepY][halfStepX] == null && board[stepY][stepX] == null) {
            board[y][x] = null;
            board[stepY][stepX] = token;
            token.setPosition(stepX, stepY);
            if (isAff) {
                int affX = x + dirAff.x;
                int affY = y + dirAff.y;
                if (affY < 5 && affX < 5 && affX >= 0 && affY >= 0)
                    if (board[affY][affX] != null) {
                        Direction oppDir = dirAff.getOpposite();
                        Blade blade = (Blade) board[affY][affX];
                        if(blade.getDirection() == dir.getOpposite()) {
                            TurnId id = blade.getId();
                            turnstiles[id.index].rotate(blade.getDirection().spinValue(oppDir), board);
                        }
                    }
            }
            return true;
        }

        if (board[halfStepY][halfStepX] != null && isAff) {
            int affX = x + dirAff.x;
            int affY = y + dirAff.y;
            if (affY < 5 && affX < 5 && affX >= 0 && affY >= 0)
                if (board[affY][affX] != null) {
                    Blade blade = (Blade) board[halfStepY][halfStepX];
                    TurnId id = blade.getId();
                    Blade bladeAff = (Blade) board[affY][affX];

                    TurnId idAff = bladeAff.getId();
                    if (id == idAff) {
                        board[y][x] = null;
                        if (turnstiles[id.index].rotate(blade.getDirection().spinValue(dir), board)) {
                            token.setPosition(stepX, stepY);
                            board[stepY][stepX] = token;
                            return true;
                        } else {
                            board[y][x] = token;
                        }
                    }
                }
        }
        return false;
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
}