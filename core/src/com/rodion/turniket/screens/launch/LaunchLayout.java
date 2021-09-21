package com.rodion.turniket.screens.launch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.Layout;

public class LaunchLayout extends Layout {
    private Texture logoTexture;
    private Texture poweredTexture;
    private Texture engineTexture;
    public LaunchLayout(BasicStage basicStage) {
        super(basicStage);
        logoTexture = new Texture(Gdx.files.internal("launch/logo.png"));
        Image logoImage = new Image(logoTexture);
        poweredTexture = new Texture(Gdx.files.internal("launch/powered-by-text.png"));
        Image poweredImage = new Image(poweredTexture);
        engineTexture = new Texture(Gdx.files.internal("launch/engine.png"));
        Image engineImage = new Image(engineTexture);
        add(logoImage).expandY().row();
        add(poweredImage).bottom().padBottom(25).row();
        add(engineImage).bottom().padBottom(50);
    }

    @Override
    public void dispose() {
        super.dispose();
        logoTexture.dispose();
        poweredTexture.dispose();
        engineTexture.dispose();
    }
}