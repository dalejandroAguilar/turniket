package com.rodion.turniket.utilities;

import com.badlogic.gdx.files.FileHandle;
import com.rodion.turniket.kernel.Solution;
import com.rodion.turniket.stages.settings.SettingsStage;

public class Level {
    private int id;
    private boolean isUnlocked;
    private int stars;
    private Character[][] map;
    private Solution solutionRead;
    private Solution solutionWrite;
    private Requirement requirement;

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
            System.out.println("Solution Load N steps: " + solutionRead.getNSteps());
            return true;
        } else
            return false;
    }

    public boolean saveSolution(Multiplatform multiplatform) {
        String idStr = String.format("%06X", id);
        System.out.println("Saving");
        FileHandle solutionFile = multiplatform.openFile("maps/Solutions/" + idStr + ".dat");

        System.out.println("NSTEPS: " + solutionWrite.getNSteps());

        solutionFile.writeString(solutionWrite.getString(),false);
//        solutionWrite.print(str);
//        solutionFile.writeString(,false);
//        if (solutionFile.exists()) {
//        solutionFile.writeString();
//            String line = solutionFile.readString();
//            solutionRead.readSolution(line);
//            return true;
//        } else
            return false;
    }

//    public void saveSolution(Multiplatform multiplatform, com.rodion.turniket.kernel.Solution solution) {
//
//    }

    public boolean loadStatus(Multiplatform multiplatform) {
        System.out.println(id);
        String idStr = String.format("%06X", id);
        FileHandle progressFile = multiplatform.openFile("maps/Progress/" + idStr + ".dat");
        System.out.println(">>>>>>>>>>>>>>>>>" + "maps/Progress/" + idStr + ".dat");
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

    public boolean saveStatus(Multiplatform multiplatform) {
        String idStr = String.format("%06X", id);
        FileHandle progressFile = multiplatform.openFile("maps/Progress/" + idStr + ".dat");
        progressFile.writeString("status: ", false);
        if (isUnlocked)
            progressFile.writeString("unlocked", true);
        else
            progressFile.writeString("locked", true);
        progressFile.writeString("\n", true);
        progressFile.writeString("stars:3", true);
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

    public int getStars() {
        return stars;
    }

    public boolean isUnlocked() {
        return isUnlocked;
    }

    public Character[][] getMap() {
        return map;
    }

    public void setRequirement(Requirement requirement) {
        this.requirement = requirement;
    }

    public Requirement getRequirement() {
        return requirement;
    }

    public boolean nStarsSatisfied() {
        if (LevelManagerMaster.getNstars() < requirement.getStars())
            return false;
        return true;
    }

    public void restartReadSolver(){
        solutionRead.goToBegin();
    }

}