package com.rodion.turniket.kernel.levelGenerator;

import com.rodion.turniket.kernel.constants.Direction;
import com.rodion.turniket.kernel.constants.TokenColor;

import java.io.IOException;
import java.io.PrintStream;
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
            InverseGame ig = new InverseGame();
            ig
                        .setMaxNDummyTokens(4)
                        .setMaxNTokens(4)
                        .setMaxNBlades(6);
            ig.generate();
                ig.print(System.out);
            int nTry = 0;
            for (int m = 0; m < maxIt; m++) {
                TokenColor color = TokenColor.random();
                Direction dir = Direction.random();
                boolean isAff = random.nextBoolean();
                Direction dirAff = Direction.random();

                if (ig.reverseMove(color, dir, isAff, dirAff)) {
//                    movements.add(0, dir.name());
                    if (nTry++ > maxTry)
                        break;
                }
            }

            PrintStream file = new PrintStream(path + "4map" + String.format("%03d", i+1)
                    + ".dat");
            ig.print(file);
//            file.println(movements);
            file.close();
        }
    }
}