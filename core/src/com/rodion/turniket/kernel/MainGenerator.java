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
        int maxIt = 1000000;
        int maxTry = 10000;
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
//                    movements.add(0, dir.name());
                    if (nTry++ > maxTry)
                        break;
                }
            }

            PrintStream file = new PrintStream(path + "4map" + String.format("%03d", i+1) + ".dat");
            levelGenerator.print(file);
//            file.println(movements);
            file.close();
        }

    }
}
