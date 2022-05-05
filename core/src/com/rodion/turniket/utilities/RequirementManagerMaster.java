package com.rodion.turniket.utilities;

import java.lang.reflect.Method;

public class RequirementManagerMaster {
    static private int[] nMapsPerDiff;
    static private int nMaps;
    public RequirementManagerMaster() {
    }

    public static Requirement getRequirement(int n) {
        int indexDifficulty = 0;
        int nMapsAccum = 0;
        for (Difficulty difficulty : Difficulty.values()) {
            nMapsAccum += nMapsPerDiff[difficulty.index];
            if (nMapsAccum > n) {
                indexDifficulty = difficulty.index;
                break;
            }
        }

        int nMapsInDiff = nMapsPerDiff[indexDifficulty];
        int nMapsTillDiff = nMapsAccum - nMapsInDiff;
        int relativeN = n - nMapsTillDiff;
        int nRelativeStars = nMapsInDiff * 3;

        float x =  (float) relativeN / (float) nMapsInDiff;
        int nStarsInLevel = (int) (nRelativeStars * unitF(x));

        int nStarsAcumm = 0;
        for (int i=0; i< indexDifficulty; i++ )
            nStarsAcumm += 3 * nMapsPerDiff[i] * unitF(1);
//        int nStarsBefore = indexDifficulty * 3
//        nMaps
//        float fraction =
//        unitF(n)
        Requirement requirement= new Requirement();
        nStarsInLevel += nStarsAcumm;
        requirement.setRequirements(true, nStarsInLevel);
        return requirement;
    }

    private static float unitF(float x) {
        return x;
    }

    public static int[] getnMapsPerDiff() {
        return nMapsPerDiff;
    }

    public static void setnMapsPerDiff(int[] nMapsPerDiff) {
        RequirementManagerMaster.nMapsPerDiff = nMapsPerDiff;
    }

    public static int getnMaps() {
        return nMaps;
    }

    public static void setnMaps(int nMaps) {
        RequirementManagerMaster.nMaps = nMaps;
    }
}