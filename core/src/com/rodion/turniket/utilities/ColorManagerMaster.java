package com.rodion.turniket.utilities;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ColorManagerMaster {
    static public Color green, gray, grayTrans;
    static public TextureRegionDrawable greenBg, grayBg, grayTransBg;
    public static void load(){
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
    }
}