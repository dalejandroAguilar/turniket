package com.rodion.turniket.utilities;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.rodion.turniket.kernel.constants.TokenColor;

import javax.print.DocFlavor;

public class ColorManagerMaster {
    static public Color green, gray, grayTrans, col1, col2, col3, col4;
    static public TextureRegionDrawable greenBg, grayBg, grayTransBg, col1Bg, col2Bg, col3Bg, col4Bg;

    public static void load() {
        green = new Color(118 / 255f, 150 / 255f, 86 / 255f, 1);
//        green = new Color(23 / 255f, 110 / 255f, 70 / 255f, 1);

        Pixmap bgPixmap = new Pixmap(1, 1, Pixmap.Format.RGB565);
        bgPixmap.setColor(green);
        bgPixmap.fill();
        greenBg = new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap)));

        gray = new Color(51 / 255f, 51 / 255f, 51 / 255f, 1);
        bgPixmap.setColor(gray);
        bgPixmap.fill();
        grayBg = new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap)));

        grayTrans = new Color(0.16f, 37 / 255f, 34 / 255f, 0.75f);
        bgPixmap.setColor(grayTrans);
        bgPixmap.fill();
        grayTransBg = new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap)));

        col1 = new Color(23 / 255f, 61 / 255f, 90 / 255f, 1f);
        bgPixmap.setColor(col1);
        bgPixmap.fill();
        col1Bg = new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap)));
        col2 = new Color(12 / 255f, 150 / 255f, 174 / 255f, 1f);
        bgPixmap.setColor(col2);
        bgPixmap.fill();
        col2Bg = new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap)));
        col3 = new Color(243 / 255f, 52 / 255f, 33 / 255f, 1f);
        bgPixmap.setColor(col3);
        bgPixmap.fill();
        col3Bg = new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap)));
        col4 = new Color(239 / 255f, 210 / 255f, 95 / 255f, 1f);
        bgPixmap.setColor(col4);
        bgPixmap.fill();
        col4Bg = new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap)));

    }

    public static Color getColor(TokenColor tokenColor) {
        switch (tokenColor) {
            case Cyan:
                return col1;
            case Magenta:
                return col2;
            case Red:
                return col3;
            case Green:
                return col4;
            case Dummy1:
                return Color.GRAY;
            case Dummy2:
                return Color.GRAY;
            case Dummy3:
                return Color.GRAY;
            case Dummy4:
                return Color.GRAY;
            default:
                return Color.GRAY;
        }
    }

    public static TextureRegionDrawable getColorRegion(Difficulty difficulty) {
        switch(difficulty){
            case Beginner:
                return col1Bg;
            case Intermediate:
                return col2Bg;
            case Expert:
                return col3Bg;
            case Advanced:
                return col4Bg;
            default:
                return grayBg;
        }
    }

    public static Color getColor(Difficulty difficulty) {
        switch(difficulty){
            case Beginner:
                return col1;
            case Intermediate:
                return col2;
            case Expert:
                return col3;
            case Advanced:
                return col4;
            default:
                return gray;
        }
    }
}