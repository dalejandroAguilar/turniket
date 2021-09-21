package com.rodion.turniket.screens.launch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.rodion.turniket.MainGame;
import com.rodion.turniket.basics.BasicScreen;
import com.rodion.turniket.basics.BlackStage;
import com.rodion.turniket.screens.title.TitleScreen;
import com.rodion.turniket.utilities.ScreenScale;

public class LaunchScreen extends BasicScreen {
    private LaunchStage stage;
    private BlackStage blackStage;
    private final ScreenViewport screenViewport = new  ScreenViewport();


    public LaunchScreen(MainGame mainGame) {
        super(mainGame);
//        screenViewport.set
        stage = new LaunchStage(screenViewport, this);
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
                onEnd();
            }
        };
        blackStage.onHiding();
    }

    public void onEnd(){

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
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
