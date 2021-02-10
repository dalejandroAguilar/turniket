package com.rodion.turniket.utilities;

public enum Difficulty {
    Beginner(0), Intermediate(1), Advanced(2), Expert(3);
    public final int index;
    private Difficulty(int index) {
        this.index = index;
    }
}
