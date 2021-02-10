package com.rodion.turniket.screens.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.rodion.turniket.MainGame;
import com.rodion.turniket.basics.BasicScreen;
import com.rodion.turniket.screens.level.stages.PageStage;
import com.rodion.turniket.screens.level.stages.UILevelStage;
import com.rodion.turniket.utilities.ScreenScale;

public class LevelScreen extends BasicScreen {

    private PageStage page;
    private PageStage previousPage;
    private PageStage nextPage;
    private PageStage previousLevel;
    private PageStage nextLevel;
    private UILevelStage uiStage;

    public LevelScreen(MainGame mainGame) {
        super(mainGame);
        final InputMultiplexer multiplexer;
        multiplexer = new InputMultiplexer();
        uiStage = new UILevelStage(new ScreenViewport(), this) {
            @Override
            public void onPreviousPage() {
                System.out.println("onPreviousPage");
            }

            @Override
            public void onNextPage() {
                System.out.println("onNextPage");
            }

            @Override
            public void onPreviousDifficulty() {
                System.out.println("onPreviousDifficulty");
            }

            @Override
            public void onNextDifficulty() {
                System.out.println("onNextDifficulty");
            }
        };
        page = new PageStage(1, new ScreenViewport(), this) {

        };
        multiplexer.addProcessor(uiStage);
        multiplexer.addProcessor(page);
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(31.f / 255, 31.f / 255, 31.f / 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        page.act();
        page.draw();
        uiStage.act();
        uiStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        ScreenScale.resize(width, height);
        page.resize(width, height);
        uiStage.resize(width, height);
    }
}