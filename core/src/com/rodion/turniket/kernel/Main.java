package com.rodion.turniket.kernel;

import com.rodion.turniket.kernel.constants.Direction;
import com.rodion.turniket.kernel.constants.TokenColor;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Game game = new Game();
        File file = new File("maps/map.dat");
        game.readFile(file);
        game.setFromMap();
        game.print();
        game.move(TokenColor.Green, Direction.Left);
        game.print();
        game.move(TokenColor.Green, Direction.Down);
        game.print();
        game.move(TokenColor.Green, Direction.Right);
        game.print();
        game.move(TokenColor.Green, Direction.Down);
        game.print();
        game.move(TokenColor.Dummy1, Direction.Right);
        game.print();
        game.move(TokenColor.Dummy1, Direction.Down);
        game.print();
        game.move(TokenColor.Dummy1, Direction.Up);
        game.print();
    }
}
