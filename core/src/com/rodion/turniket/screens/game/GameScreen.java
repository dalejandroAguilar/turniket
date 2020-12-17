package com.rodion.turniket.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.rodion.turniket.MainGame;
import com.rodion.turniket.basics.BasicScreen;
import com.rodion.turniket.screens.game.stages.ConfettiStage;
import com.rodion.turniket.screens.game.stages.GameStage;
import com.rodion.turniket.utilities.ScreenScale;

public class GameScreen extends BasicScreen {
    private GameStage gameStage;
    private ConfettiStage confettiStage;
    public GameScreen(MainGame mainGame) {
        super(mainGame);
        gameStage = new GameStage(new ScreenViewport(), this){
            @Override
            public void onWin() {
                super.onWin();
                confettiStage.onThrow();
            }
        };
        confettiStage = new ConfettiStage(new ScreenViewport(), this){
            @Override
            public void onFinish() {
                super.onFinish();
                System.out.println("Finish confetti");
            }
        };
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(31.f/255, 31.f/255, 31.f/255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        gameStage.act();
        gameStage.draw();
        confettiStage.act();
        confettiStage.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        ScreenScale.resize(width, height);
        gameStage.resize(width, height);
        confettiStage.resize(width, height);
    }
}
