package com.rodion.turniket.kernel.constants;

public enum TurnId {
    One(0), Two(1), Three(2), Four(3);
    public final int index;
    public final char value;

    private TurnId(int index) {
        this.index = index;
        switch (index) {
            case 0:
                value = '1';
                break;
            case 1:
                value = '2';
                break;
            case 2:
                value = '3';
                break;
            case 3:
                value = '4';
                break;
            default:
                value = ' ';
        }
    }

    public static TurnId get(TurnId id) {
        switch (id.value){
            case '1':
                return One;
            case '2':
                return Two;
            case '3':
                return  Three;
            case '4':
                return Four;
        }
        return null;
    }
}
