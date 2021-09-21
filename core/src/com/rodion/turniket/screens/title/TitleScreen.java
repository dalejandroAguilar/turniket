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
    private final ScreenViewport screenViewport = new  ScreenViewport();
    private BlackStage blackStage;


    public TitleScreen(MainGame mainGame) {
        super(mainGame);
        stage = new TitleStage(screenViewport, this){
            @Override
            public void onPlay() {
                super.onPlay();
                TitleScreen.this.onPlay();
            }
        };
        blackStage = new BlackStage(screenViewport, this);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(31.f / 255, 31.f / 255, 31.f / 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        stage.act();
        stage.draw();
        blackStage.act(delta);
        blackStage.show();
    }

    public void onPlay(){

    }

    public void onEnter(){
        blackStage.onHiding();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        ScreenScale.resize(width, height);
        stage.resize(width, height);
        blackStage.resize(width, height);
    }
}
