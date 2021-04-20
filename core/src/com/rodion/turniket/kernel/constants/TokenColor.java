package com.rodion.turniket.kernel.constants;

import com.rodion.turniket.kernel.Node;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public enum TokenColor {
    Cyan(0), Magenta(1), Red(2), Green(3), Dummy1(4), Dummy2(5),
    Dummy3(6), Dummy4(7);
    public final int index;
    public final char value;
    private static final Random RANDOM = new Random();

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

    public static TokenColor get(int x, int y) {
        if (x == 0 && y == 0)
            return Cyan;
        if (x == 4 && y == 0)
            return Magenta;
        if (x == 4 && y == 4)
            return Red;
        if (x == 0 && y == 4)
            return Green;
        return null;
    }

    public static Node getTarget(TokenColor tokenColor) {
        switch (tokenColor) {
            case Cyan:
                return Node.dummy(0, 0);
            case Magenta:
                return Node.dummy(4, 0);

            case Red:
                return Node.dummy(4, 4);

            case Green:
                return Node.dummy(0, 4);
            default:
                return null;
        }
    }

    public static TokenColor random() {
        List< TokenColor> values = Arrays.asList(values());
        return values.get(RANDOM.nextInt(values.size()));
    }
}
