package com.rodion.turniket.basics;

public enum FactorScale {
    UltraSmall(0.5f,"0.5",0),Small(0.75f, "0.75",1),
    Normal(1,"1",2), Big(1.5f,"1.5",3),
    Huge(2,"2",4), UltraHuge(3,"3",5);
    public final float scale;
    public final String value;
    public final int index;

    FactorScale(float scale, String value, int index){
        this.scale = scale;
        this.value = value;
        this.index = index;
    }

    public float getScale() {
        return scale;
    }

    public static FactorScale get(int index) {
        switch (index){
            case 0:
                return UltraSmall;
            case 1:
                return Small;
            case 2:
                return Normal;
            case 3:
                return Big;
            case 4:
                return Huge;
            case 5:
                return UltraHuge;
        }
        return null;
    }

    public static int getN() {
        return UltraHuge.index + 1;
    }

}