package com.rodion.turniket.screens.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.rodion.turniket.MainGame;
import com.rodion.turniket.basics.BasicScreen;
import com.rodion.turniket.utilities.ScreenScale;

public class LevelScreen extends BasicScreen {

    private LevelStage levelStage;
    public LevelScreen(MainGame mainGame) {
        super(mainGame);
        levelStage = new LevelStage(new ScreenViewport(), this);
        levelStage.onInput();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(31.f / 255, 31.f / 255, 31.f / 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        levelStage.act();
        levelStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        ScreenScale.resize(width, height);
        levelStage.resize(width, height);
    }
}
