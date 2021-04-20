package com.rodion.turniket.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.File;
import java.util.ArrayList;

public class LevelManagerMaster {
    public static final int mapsPerPage = 9;
    private static ArrayList<File> maps;
    private static int bookmark;
    private static int pageMark;
    private static int nmapsPerDiff[];
    private static int npagesPerDiff[];
    private static int nmaps;
    private static int npages;

    public static void init() {
        bookmark = 0;
        pageMark = 0;
        npages = 0;
        nmaps = 0;
        npagesPerDiff = new int[Difficulty.values().length];
        nmapsPerDiff = new int[Difficulty.values().length];
//        maps = new File[nmapsPerDiff[difficulty.index]];
        maps = new ArrayList<>();
        for (Difficulty difficulty : Difficulty.values()) {
            File dir = new File(Gdx.files.toString() +  "maps/"+  difficulty.name()); //new File(Gdx.files.path()+"maps\\" +  difficulty.name()); //File("maps\\" + difficulty.name());
            System.out.println("dir aqui" + Gdx.files.toString());
//            FileHandle fileHandle = new FileHandle();
//            fileHandle.lis
            nmapsPerDiff[difficulty.index] = dir.listFiles().length;
//            nmaps += nmapsPerDiff[difficulty.index];
            for (int i = 0; i < nmapsPerDiff[difficulty.index]; i++)
                maps.add(dir.listFiles()[i]);
            npagesPerDiff[difficulty.index] = nmapsPerDiff[difficulty.index] / mapsPerPage + 1;
            npages += npagesPerDiff[difficulty.index];
            System.out.println(difficulty.name() +" " + nmapsPerDiff[difficulty.index]);
        }

        nmaps = maps.size();
//        npages = nmaps / mapsPerPage + 1;

    }

    public static File getPreviousLevel() {
        if (bookmark > 0 && bookmark < nmaps)
            return maps.get(bookmark - 1);
        else
            return null;
    }

    public static File getNextLevel() {
        if (bookmark >= 0 && bookmark < nmaps - 1)
            return maps.get(bookmark + 1);
        else
            return null;
    }

    public static File getLevel() {
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

    public static ArrayList<File> getLevels() {
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

    public static ArrayList<File> getLevels(int page) {


        ArrayList<File> levels = new ArrayList<>();
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

    public static Difficulty getDifficultOfLelve(int index){
        int acumLevel = 0;
        for (Difficulty difficulty : Difficulty.values()) {
            acumLevel += nmapsPerDiff[difficulty.index];
            if (index < acumLevel)
                return difficulty;
        }
        return null;
    }
}
