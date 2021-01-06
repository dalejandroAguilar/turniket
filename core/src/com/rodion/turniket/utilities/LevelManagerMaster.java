package com.rodion.turniket.utilities;

import java.io.File;

public class LevelManagerMaster {
    private static File[] maps;
    private static int bookmark;
    private static int nmaps;

    public static void init() {
        bookmark = 0;
        File dir = new File("maps");
        nmaps = dir.listFiles().length;
        maps = new File[nmaps];
        for (int i = 0; i < nmaps; i++)
            maps[i] = dir.listFiles()[i];
    }

    public static File getPreviousLevel() {
        if (bookmark > 0 && bookmark < nmaps)
            return maps[bookmark - 1];
        else
            return null;
    }

    public static File getNextLevel() {
        if (bookmark >= 0 && bookmark < nmaps - 1)
            return maps[bookmark + 1];
        else
            return null;
    }

    public static File getLevel() {
        if (bookmark >= 0 && bookmark < nmaps)
            return maps[bookmark];
        else
            return null;
    }

    public static boolean foreward() {
        if (bookmark < nmaps - 1) {
            bookmark++;
            return true;
        } else
            return false;
    }

    public static boolean backward() {
        if (bookmark > 0) {
            bookmark--;
            return true;
        } else
            return false;
    }
}
