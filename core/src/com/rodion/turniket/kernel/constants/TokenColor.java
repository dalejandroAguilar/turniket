package com.rodion.turniket.kernel.constants;

public enum TokenColor {
    Cyan(0), Magenta(1), Red(2), Green(3), Dummy1(4), Dummy2(5),
    Dummy3(6), Dummy4(7);
    public final int index;
    public final char value;

    private TokenColor(int index) {
        this.index = index;
        switch (index) {
            case 0:
                value = 'C';
                break;
            case 1:
                value = 'M';
                break;
            case 2:
                value = 'R';
                break;
            case 3:
                value = 'G';
                break;
            case 4:
                value = 'w';
                break;
            case 5:
                value = 'x';
                break;
            case 6:
                value = 'y';
                break;
            case 7:
                value = 'z';
                break;
            default:
                value = ' ';
        }
    }

}
