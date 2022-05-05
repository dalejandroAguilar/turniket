package com.rodion.turniket.utilities;

public class Requirement {
    private boolean previousLevel;
    private int nStars;

    public Requirement() {
        previousLevel = true;
        nStars = 0;
    }

    public void setPreviousLevel(boolean previousLevel) {
        this.previousLevel = previousLevel;
    }

    public void setnStars(int nStars) {
        this.nStars = nStars;
    }

    public boolean isPreviousLevel() {
        return previousLevel;
    }

    public int getStars() {
        return nStars;
    }

    public void setRequirements(boolean previousLevel, int nStars) {
        this.previousLevel = previousLevel;
        this.nStars = nStars;
    }
}
