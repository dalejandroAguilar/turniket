package com.rodion.turniket.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class Level {
    private int id;
    private Character[][] map;
    private Solution solutionRead;
    private Solution solutionWrite;

    public Level(FileHandle file) {
        solutionRead = new Solution();
        solutionWrite = new Solution();
        String[] lines = file.readString().split("\n");
//        String idStr = "0x" + lines[0].split(" ")[1].strip();
        String idStr = "0x" + lines[0].split(" ")[1].substring(0,5);

        System.out.println(idStr);
        id = Integer.decode(idStr);
        map = new Character[5][5];
        for (int i = 0; i < 5; i++) {
            String line = lines[i + 1];
            for (int j = 0; j < 5; j++) {
                if (j < line.length())
                    map[i][j] = line.charAt(j);
                else
                    map[i][j] = ' ';
            }
        }
    }

    public boolean loadSolution() {
        String idStr = String.format("%06X", id);
        System.out.println(idStr);
//        if()
        FileHandle solutionFile = Gdx.files.local("maps/Solutions/" + idStr + ".dat");
        if (solutionFile.exists()) {
            String line = solutionFile.readString();
            solutionRead.readSolution(line);
            return true;
        } else
            return false;
    }

    public Solution getSolutionRead() {
        return solutionRead;
    }

    public void setSolutionRead(Solution solutionRead) {
        this.solutionRead = solutionRead;
    }

    public Solution getSolutionWrite() {
        return solutionWrite;
    }

    public void setSolutionWrite(Solution solutionWrite) {
        this.solutionWrite = solutionWrite;
    }

    public int getId() {
        return id;
    }

    public Character[][] getMap() {
        return map;
    }
}