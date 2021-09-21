package com.rodion.turniket.screens.loading;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.rodion.turniket.MainGame;
import com.rodion.turniket.basics.BasicScreen;
import com.rodion.turniket.basics.BlackStage;
import com.rodion.turniket.utilities.ScreenScale;

public class LoadingScreen extends BasicScreen {
    private final ScreenViewport screenViewport = new ScreenViewport();
    private LoadingStage stage;
    private BlackStage blackStage;

    public LoadingScreen(MainGame mainGame) {
        super(mainGame);
        stage = new LoadingStage(screenViewport, this);
        blackStage = new BlackStage(screenViewport, this){
            @Override
            public void onHide() {
                super.onHide();
                addAction(Actions.sequence(
                        Actions.delay(2)
                        ,
                        Actions.run(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        blackStage.onShowing();
                                    }
                                }
                        )
                ));
            }

            @Override
            public void onShow() {
                super.onShow();
                onExit();
            }
        };
    }

    public void onEnter(){
        System.out.println("entrando");
        blackStage.onHiding();
    }

    public void onExit(){
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0.f, 0.f / 5, 0.f / 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        stage.act();
        stage.draw();
        blackStage.act(delta);
        blackStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        ScreenScale.resize(width, height);
        stage.resize(width, height);
        blackStage.resize(width, height);
    }
}

