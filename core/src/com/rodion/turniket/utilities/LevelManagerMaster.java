package com.rodion.turniket.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.File;
import java.util.ArrayList;

public class LevelManagerMaster {
    public static final int mapsPerPage = 9;
    private static ArrayList<Level> maps;
    private static int bookmark;
    private static int pageMark;
    private static int[] nmapsPerDiff;
    private static int[] npagesPerDiff;
    private static int nmaps;
    private static int npages;
    private static int nstars;

    public static void init(Multiplatform multiplatform) {
        bookmark = 0;
        pageMark = 0;
        npages = 0;
        nmaps = 0;
        nstars = 0;
        npagesPerDiff = new int[Difficulty.values().length];
        nmapsPerDiff = new int[Difficulty.values().length];
        maps = new ArrayList<>();
        for (Difficulty difficulty : Difficulty.values()) {
//            FileHandle dir = Gdx.files.local("maps/"+  difficulty.name()) ; //new File(Gdx.files.path()+"maps\\" +  difficulty.name()); //File("maps\\" + difficulty.name());
            FileHandle dir = multiplatform.openFile("maps/"+  difficulty.name());
//                    Gdx.files.internal() ; //new File(Gdx.files.path()+"maps\\" +  difficulty.name()); //File("maps\\" + difficulty.name());
            nmapsPerDiff[difficulty.index] = dir.list().length;
            for (int i = 0; i < nmapsPerDiff[difficulty.index]; i++) {
                System.out.println(i);
                maps.add(new Level(dir.list()[i]));
                Level level = maps.get(i);
                level.loadSolution(multiplatform);
                level.loadStatus(multiplatform);
                nstars += level.getStars();
            }
            npagesPerDiff[difficulty.index] = nmapsPerDiff[difficulty.index] / mapsPerPage + 1;
            npages += npagesPerDiff[difficulty.index];
            System.out.println(difficulty.name() +" " + nmapsPerDiff[difficulty.index]);
        }
        nmaps = maps.size();
    }

    public static Level getPreviousLevel() {
        if (bookmark > 0 && bookmark < nmaps)
            return maps.get(bookmark - 1);
        else
            return null;
    }

    public static Level getNextLevel() {
        if (bookmark >= 0 && bookmark < nmaps - 1)
            return maps.get(bookmark + 1);
        else
            return null;
    }

    public static Level getLevel() {
        if (bookmark >= 0 && bookmark < nmaps)
            return maps.get(bookmark);
        else
            return null;
    }

    public static boolean goForwardLevel() {
        if (bookmark < nmaps - 1) {
            bookmark++;
            return true;
        } else
            return false;
    }

    public static boolean goBackwardLevel() {
        if (bookmark > 0) {
            bookmark--;
            return true;
        } else
            return false;
    }

    public static ArrayList<Level> getLevels() {
        return maps;
    }

    public static int getNLevels() {
        return maps.size();
    }

    public static int getPreviousPage() {
        if (pageMark > 0 && pageMark < npages)
            return pageMark - 1;
        else
            return -2;
    }

    public static int getNextPage() {
        if (pageMark >= 0 && pageMark < npages - 1)
            return pageMark + 1;
        else
            return -2;
    }

    public static int getPage() {
        if (pageMark >= 0 && pageMark < npages)
            return pageMark;
        else
            return -2;
    }

    public static boolean goForwardPage() {
        if (pageMark < npages - 1) {
            pageMark++;
            System.out.println(pageMark);
            return true;
        } else
            return false;
    }

    public static boolean goBackwardPage() {
        if (pageMark > 0) {
            pageMark--;
            System.out.println(pageMark);
            return true;
        } else
            return false;
    }

    public static Difficulty getDifficulty(int page) {
        int acumPage = 0;
        for (Difficulty difficulty : Difficulty.values()) {
            acumPage += npagesPerDiff[difficulty.index];
            if (page < acumPage)
                return difficulty;
        }
        return null;
    }

    public static int getIniForPage(int page){
        int nmapsaccum = 0;
        int npagesaccum = 0;
        Difficulty difficulty = getDifficulty(page);
        for (int i = 0; i < difficulty.index; i++) {
            nmapsaccum += nmapsPerDiff[i];
            npagesaccum += npagesPerDiff[i];
        }
        return nmapsaccum + (page - npagesaccum) * mapsPerPage;
    }

    public static ArrayList<Level> getLevels(int page) {
        ArrayList<Level> levels = new ArrayList<>();
        Difficulty difficulty = getDifficulty(page);
        int nmapsaccum = 0;
        int npagesaccum = 0;
        for (int i = 0; i < difficulty.index; i++) {
            nmapsaccum += nmapsPerDiff[i];
            npagesaccum += npagesPerDiff[i];
        }
        int iniLevel = nmapsaccum + (page - npagesaccum) * mapsPerPage;
        int endLevel = Math.min(iniLevel + mapsPerPage, nmapsaccum + nmapsPerDiff[difficulty.index]);

        System.out.println("iniLevel " + iniLevel);
        if(iniLevel < 0)
            return null;

        for (int i = iniLevel; i < endLevel; i++)
            levels.add(getLevels().get(i));
        return levels;

    }

    public static int getNpages(){
        return npages;
    }

    public static void goToLevel(int index){
        bookmark = index;
    }

    public static int getBookmark() {
        return bookmark;
    }

    public static void setBookmark(int bookmark) {
        LevelManagerMaster.bookmark = bookmark;
    }

    public static int getNstars() {
        return nstars;
    }

    public static void setNstars(int nstars) {
        LevelManagerMaster.nstars = nstars;
    }

    public static Difficulty getDifficultOfLevel(int index){
        int acumLevel = 0;
        for (Difficulty difficulty : Difficulty.values()) {
            acumLevel += nmapsPerDiff[difficulty.index];
            if (index < acumLevel)
                return difficulty;
        }
        return null;
    }


}
