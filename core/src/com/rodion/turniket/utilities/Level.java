package com.rodion.turniket.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import javax.annotation.processing.SupportedSourceVersion;

public class Level {
    private int id;
    private boolean isUnlocked;
    private int stars;
    private Character[][] map;
    private Solution solutionRead;
    private Solution solutionWrite;

    public Level(FileHandle file) {
        stars = 0;
        isUnlocked = false;
        solutionRead = new Solution();
        solutionWrite = new Solution();
        String[] lines = file.readString().split("\n");
//        String idStr = "0x" + lines[0].split(" ")[1].strip();
        String idStr = "0x" + lines[0].split(" ")[1].substring(0, 6);
//        System.out.println(idStr);
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
//        loadStatus();
    }

    public boolean loadSolution(Multiplatform multiplatform) {
        String idStr = String.format("%06X", id);
        System.out.println(idStr);
//        if()
//        FileHandle solutionFile = Gdx.files.local("maps/Solutions/" + idStr + ".dat");
//        FileHandle solutionFile = Gdx.files.internal("maps/Solutions/" + idStr + ".dat");
        FileHandle solutionFile = multiplatform.openFile("maps/Solutions/" + idStr + ".dat");
        if (solutionFile.exists()) {
            String line = solutionFile.readString();
            solutionRead.readSolution(line);
            return true;
        } else
            return false;
    }

    public boolean loadStatus(Multiplatform multiplatform) {
        System.out.println(id);
        String idStr = String.format("%06X", id);
        FileHandle progressFile = multiplatform.openFile("maps/Progress/" + idStr + ".dat");
        System.out.println(">>>>>>>>>>>>>>>>>"+"maps/Progress/" + idStr + ".dat");
        if (progressFile.exists()) {
            String lines[] = progressFile.readString().split(("\n"));
            String lineIsUnlocked = lines[0].split(":")[1];
            String lineStars = lines[1].split(":")[1];
            System.out.println(lineIsUnlocked);
            System.out.println(lineStars);
            stars = Integer.parseInt(lineStars);
            if (lineIsUnlocked.contains("unlocked"))
                isUnlocked = true;
            return true;
        } else {
            System.out.println(">>>>>>>>>>>>>>>>>false");
            return false;
        }
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

    public int getStars(){
        return stars;
    }

    public boolean isUnlocked() {
        return isUnlocked;
    }
    public Character[][] getMap() {
        return map;
    }
}