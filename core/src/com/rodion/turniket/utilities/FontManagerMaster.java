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
    static public Label.LabelStyle[] nexaStyle;
    static private BitmapFont[] helveticaFont;
    static public Label.LabelStyle[] helveticaStyle;
    static private BitmapFont[] helveticaWhiteFont;
    static public Label.LabelStyle[] helveticaWhiteStyle;

    public static void loadFonts() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/NexaBold.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = "1234567890abcdefghijklmnopqrstuvwxyzáéíóúABCDEFGHIJKLMNOPQRSTUVWXYZ.,¿?!¡/: \u2605";

        nexaFont = new BitmapFont[FactorScale.values().length];
        nexaStyle = new Label.LabelStyle[FactorScale.values().length];
        for (FactorScale factorScale : FactorScale.values()) {
            nexaFont[factorScale.index] = new BitmapFont();
            parameter.size = (int)(28f * factorScale.getScale());
            nexaFont[factorScale.index] = generator.generateFont(parameter);
            nexaStyle[factorScale.index] = new Label.LabelStyle(nexaFont[factorScale.index], Color.WHITE);
        }

        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Helvetica.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = "1234567890abcdefghijklmnopqrstuvwxyzáéíóúABCDEFGHIJKLMNOPQRSTUVWXYZ.,¿?!¡:/ \u2605";
        parameter.color=Color.BLACK;

        helveticaFont = new BitmapFont[FactorScale.values().length];
        helveticaStyle = new Label.LabelStyle[FactorScale.values().length];
        for (FactorScale factorScale : FactorScale.values()) {
            helveticaFont[factorScale.index] = new BitmapFont();
            parameter.size = (int)(22f * factorScale.getScale());
            helveticaFont[factorScale.index] = generator.generateFont(parameter);
            helveticaStyle[factorScale.index] = new Label.LabelStyle(helveticaFont[factorScale.index], Color.WHITE);
        }

        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Helvetica.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = "1234567890abcdefghijklmnopqrstuvwxyzáéíóúABCDEFGHIJKLMNOPQRSTUVWXYZ.,¿?!¡/: \u2605";
        parameter.color=Color.WHITE;
        helveticaWhiteFont = new BitmapFont[FactorScale.values().length];
        helveticaWhiteStyle = new Label.LabelStyle[FactorScale.values().length];
        for (FactorScale factorScale : FactorScale.values()) {
            helveticaWhiteFont[factorScale.index] = new BitmapFont();
            parameter.size = (int)(22f * factorScale.getScale());
            helveticaWhiteFont[factorScale.index] = generator.generateFont(parameter);
            helveticaWhiteStyle[factorScale.index] = new Label.LabelStyle(helveticaWhiteFont[factorScale.index], Color.WHITE);
        }
    }

    public static void dispose() {
        for (FactorScale factorScale : FactorScale.values()) {
            nexaFont[factorScale.index].dispose();
            helveticaFont[factorScale.index].dispose();
            helveticaWhiteFont[factorScale.index].dispose();
        }
    }
}
