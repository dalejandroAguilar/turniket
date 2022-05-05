package com.rodion.turniket.utilities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.rodion.turniket.kernel.constants.TokenColor;

public enum Difficulty {
    Beginner(0), Intermediate(1), Advanced(2), Expert(3);
    public final int index;

    private Difficulty(int index) {
        this.index = index;
    }

    public Color getColor() {
        switch (this) {
            case Beginner:
                return ColorManagerMaster.green;
            case Intermediate:
                return Color.MAGENTA;
            case Advanced:
                return Color.RED;
            case Expert:
                return Color.ORANGE;
            default:
                return ColorManagerMaster.grayTrans;
        }
    }

    public TextureRegionDrawable getBgColor() {
        switch (this) {
            case Beginner:
                return ColorManagerMaster.greenBg;
            case Intermediate:
                return ColorManagerMaster.grayBg;
            case Advanced:
                return ColorManagerMaster.grayTransBg;
            case Expert:
                return ColorManagerMaster.grayBg;
            default:
                return ColorManagerMaster.grayTransBg;
        }
    }

//    public TokenColor getTokenColor() {
//        switch (this) {
//            case Beginner:
//                return ColorManagerMaster.greenBg;
//            case Intermediate:
//                return ColorManagerMaster.grayBg;
//            case Advanced:
//                return ColorManagerMaster.grayTransBg;
//            case Expert:
//                return ColorManagerMaster.grayBg;
//            default:
//                return ColorManagerMaster.grayTransBg;
//        }
//    }


}
