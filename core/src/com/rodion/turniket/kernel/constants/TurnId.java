package com.rodion.turniket.kernel.constants;

import com.rodion.turniket.kernel.Node;

public enum TurnId {
    One(0), Two(1), Three(2), Four(3);
    public final int index;
    public final char value;

    TurnId(int index) {
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
        switch (id.value) {
            case '1':
                return One;
            case '2':
                return Two;
            case '3':
                return Three;
            case '4':
                return Four;
        }
        return null;
    }

    public static TurnId get(int index) {
        switch (index) {
            case 0:
                return One;
            case 1:
                return Two;
            case 2:
                return Three;
            case 3:
                return Four;
            default:
                return null;
        }
    }

    public static TurnId get(int x, int y) {
        if (x == 1 && y == 1)
            return One;
        if (x == 3 && y == 1)
            return Two;
        if (x == 1 && y == 3)
            return Three;
        if (x == 3 && y == 3)
            return Four;
        return null;
    }

    public static Node getPosition(TurnId id) {
        switch (id) {
            case One:
                return Node.dummy(1, 1);
            case Two:
                return Node.dummy(3, 1);
            case Three:
                return Node.dummy(1, 3);
            case Four:
                return Node.dummy(3, 3);
            default:
                return null;
        }
    }
}