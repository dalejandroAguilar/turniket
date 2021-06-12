package com.rodion.turniket.kernel;

import com.rodion.turniket.kernel.constants.Direction;
import com.rodion.turniket.kernel.constants.TokenColor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;

public class Main {
//    private static Random random = new Random();

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Hola mundo");

        for (int i = 0; i<200; i++) {
            System.out.println("id: " + LevelGenerator.getRandomHex());
        }

//        int maxIt = 100000;
//        int maxTry = 1000;
//        int nTry = 0;
//        Game game = new Game();
//        File file = new File("maps/map.dat");
//        LevelGenerator levelGenerator = new LevelGenerator();
//        levelGenerator
//                .setMaxNDummyTokens(4)
//                .setMaxNTokens(4)
//                .setMaxNBlades(6);
//        levelGenerator.generate();
//
//        levelGenerator.print(System.out);
//        for (int i = 0; i < maxIt; i++) {
//
//
//            TokenColor color = TokenColor.random();
//            Direction dir = Direction.random();
//            boolean isAff = random.nextBoolean();
//            Direction dirAff = Direction.random();
////            System.out.println(color + " " + dir + " " + isAff + " " + dirAff);
//
//            if (levelGenerator.reverseMove(color, dir, isAff, dirAff))
//                if (nTry++ > maxTry)
//                    break;

//            if (levelGenerator.reverseMove(color, dir, isAff, dirAff))
//                levelGenerator.print();
//            else
//                System.out.println("false");
//        }
//        System.out.println(nTry);
//        levelGenerator.print(System.out);


//        game.readFile(file);
//        game.setFromMap();
//        game.print();
//        game.move(TokenColor.Green, Direction.Left);
//        game.print();
//        game.move(TokenColor.Green, Direction.Down);
//        game.print();
//        game.move(TokenColor.Green, Direction.Right);
//        game.print();
//        game.move(TokenColor.Green, Direction.Down);
//        game.print();
//        game.move(TokenColor.Dummy1, Direction.Right);
//        game.print();
//        game.move(TokenColor.Dummy1, Direction.Down);
//        game.print();
//        game.move(TokenColor.Dummy1, Direction.Up);
//        game.print();
//                    System.out.println("Working Directory = " + System.getProperty("user.dir"));

    }
}
