package com.rodion.turniket.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.rodion.turniket.basics.FactorScale;
import com.rodion.turniket.basics.ScreenSize;

public class FontManagerMaster {
    static private BitmapFont[] nexaFont;

//            bitmapFont50x, bitmapFont75x, bitmapFont100x, bitmapFont150x, bitmapFont200x, bitmapFont300x;
    static public Label.LabelStyle[] nexaStyle;
//    labelStyle50x, labelStyle75x, labelStyle100x, labelStyle150x, labelStyle200x,labelStyle300x, labelStyle400x;

    public static void loadFonts() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/NexaBold.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = "1234567890abcdefghijklmnopqrstuvwxyzáéíóúABCDEFGHIJKLMNOPQRSTUVWXYZ.,¿?!¡ ";
        nexaFont = new BitmapFont[FactorScale.values().length];
        nexaStyle = new Label.LabelStyle[FactorScale.values().length];

        for (FactorScale factorScale : FactorScale.values()) {
            nexaFont[factorScale.index] = new BitmapFont();
            parameter.size = (int)(28f * factorScale.getScale());

            System.out.println(parameter.size);
            nexaFont[factorScale.index] = generator.generateFont(parameter);
            nexaStyle[factorScale.index] = new Label.LabelStyle(nexaFont[factorScale.index], Color.WHITE);
        }
    }

    public static void dispose() {
        for (FactorScale factorScale : FactorScale.values()) {
            nexaFont[factorScale.index].dispose();
        }
    }

}
