package com.rodion.turniket.kernel;

import com.rodion.turniket.kernel.constants.Direction;
import com.rodion.turniket.kernel.constants.TokenColor;
import com.rodion.turniket.kernel.constants.TurnId;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Turnstile[] turnstiles;
    private Token[] tokens;
    private Character[][] map;
    private Node[][] board;
    private static ArrayList<Node> turnPositions;

    public Game() {
        board = new Node[5][5];
        turnPositions = new ArrayList<>();
        turnPositions.add(new Node(1, 1));
        turnPositions.add(new Node(3, 1));
        turnPositions.add(new Node(1, 3));
        turnPositions.add(new Node(3, 3));

        turnstiles = new Turnstile[TurnId.values().length];
        tokens = new Token[TokenColor.values().length];
        map = new Character[5][5];
        for (TokenColor color : TokenColor.values())
            tokens[color.index] = new Token(-1, -1, color);
        for (TurnId id : TurnId.values()) {
            turnstiles[id.index] = new Turnstile(id);
            turnstiles[id.index].setPosition(turnPositions.get(id.index));
        }
    }

    public boolean move(TokenColor color, Direction dir) {
        Token token = tokens[color.index];
        int stepX = token.getX() + 2 * dir.x;
        int stepY = token.getY() + 2 * dir.y;
        int halfStepX = token.getX() + dir.x;
        int halfStepY = token.getY() + dir.y;
        if (!(stepY < 5 && stepX < 5 && stepX >= 0 && stepY >= 0))
            return false;
        if (board[halfStepY][halfStepX] == null && board[stepY][stepX] == null) {
            board[token.getY()][token.getX()] = null;
            board[stepY][stepX] = token;
            token.setPosition(stepX, token.getY() + 2 * dir.y);
            return true;
        }
        if (board[halfStepY][halfStepX].getClass() == Blade.class) {
            board[token.getY()][token.getX()] = null;
            Blade blade = (Blade) board[halfStepY][halfStepX];
            TurnId id = blade.getId();
            if (turnstiles[id.index].rotate(blade.getDirection().spinValue(dir), board)) {
                board[stepY][stepX] = token;
                token.setPosition(stepX, stepY);
            } else {
                board[token.getY()][token.getX()] = token;
            }
        }
        return true;
    }

    public void readFile(File file) throws FileNotFoundException {
        Scanner reader = new Scanner(file);
        int i = 0;
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            int j;
            for (j = 0; j < line.length(); j++) {
                map[i][j] = line.charAt(j);
            }
            for (int k = j; k < 5; k++) {
                map[i][j] = ' ';
            }
            i++;
        }
        reader.close();
    }

    public void setFromMap() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                for (TokenColor color : TokenColor.values())
                    if (map[i][j] == color.value) {
                        tokens[color.index].setPosition(j, i);
                        board[i][j] = tokens[color.index];
                    }
                for (TurnId id : TurnId.values())
                    if (map[i][j] == id.value) {
                        if (!turnPositions.contains(Node.dummy(j, i))) {
                            Turnstile turnstile = turnstiles[id.index];
                            Direction direction = Direction.get(j - turnstile.getX(),
                                    i - turnstile.getY());
                            Blade blade = new Blade(j, i, direction, id);
                            turnstile.addBlade(blade);
                            board[i][j] = blade;
                        }
                    }
            }
        }
    }

    public void print() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j] != null) {
                    if (board[i][j].getClass() == Token.class) {
                        System.out.print(((Token) board[i][j]).getColor().value);
                    }
                    if (board[i][j].getClass() == Blade.class) {
                        System.out.print(((Blade) board[i][j]).getId().value);
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
}
