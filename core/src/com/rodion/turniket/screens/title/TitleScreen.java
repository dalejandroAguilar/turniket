package com.rodion.turniket.screens.title;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.rodion.turniket.MainGame;
import com.rodion.turniket.basics.BasicScreen;
import com.rodion.turniket.basics.BlackStage;
import com.rodion.turniket.utilities.ScreenScale;

public class TitleScreen extends BasicScreen {
    private TitleStage stage;
    private final ScreenViewport screenViewport = new ScreenViewport();
    private BlackStage blackStage;

    public TitleScreen(MainGame mainGame) {
        super(mainGame);
        stage = new TitleStage(screenViewport, this) {
            @Override
            public void onPlay() {
                super.onPlay();
                stage.addAction(Actions.sequence(
                        Actions.fadeOut(.2f),
                        Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                TitleScreen.this.onPlay();
                            }
                        })
                        )
                );

            }
        };
        blackStage = new BlackStage(screenViewport, this);
    }

    public void init(){
        stage.addAction(Actions.fadeIn(0f));
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(31.f / 255, 31.f / 255, 31.f / 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        stage.act();
        stage.draw();
        blackStage.act(delta);
        blackStage.draw();
    }

    public void onPlay() {

    }

    public void onEnterForward() {
        System.out.println("EnterTitle");
        Gdx.input.setInputProcessor(stage);
        blackStage.onHiding();
    }

    public void onEnterBackward() {
        stage.addAction(Actions.rotateTo(90, 0));
        stage.addAction(Actions.rotateBy(-90, .2f));
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        ScreenScale.resize(width, height);
        stage.resize(width, height);
        blackStage.resize(width, height);
    }
}