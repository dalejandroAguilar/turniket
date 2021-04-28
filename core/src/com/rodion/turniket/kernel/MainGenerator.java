package com.rodion.turniket.kernel;

import com.rodion.turniket.kernel.constants.Direction;
import com.rodion.turniket.kernel.constants.TokenColor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class MainGenerator {
    private static Random random = new Random();

    public static void main(String[] args) throws IOException {
        int maxIt = 100000;
        int maxTry = 1000;
        ArrayList<String> movements = new ArrayList<>();
        String path = "C:\\Users\\Daniel\\Documents\\Android_Studio_Projects\\turniket\\" +
                "android\\assets\\maps\\Beginner\\";


        for (int i = 0; i < 100; i++) {
            LevelGenerator levelGenerator = new LevelGenerator();
            levelGenerator
                        .setMaxNDummyTokens(4)
                        .setMaxNTokens(4)
                        .setMaxNBlades(6);
            levelGenerator.generate();
                levelGenerator.print(System.out);
            int nTry = 0;
            for (int m = 0; m < maxIt; m++) {
                TokenColor color = TokenColor.random();
                Direction dir = Direction.random();
                boolean isAff = random.nextBoolean();
                Direction dirAff = Direction.random();

                if (levelGenerator.reverseMove(color, dir, isAff, dirAff)) {
//                System.out.println(dir);
                    movements.add(0, dir.name());
                    if (nTry++ > maxTry)
                        break;
                }
            }

//            System.out.println("Working Directory = " + System.getProperty("user.dir"));
            PrintStream file = new PrintStream(path + "map" + String.format("%03d", i) + ".dat");
            levelGenerator.print(file);
            file.println(movements);
            file.close();

        }
//
//        PrintStream file = new PrintStream("hola.txt");
//        file.println(2);
//        file.close();
    }
}
