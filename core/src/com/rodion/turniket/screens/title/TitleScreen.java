package com.rodion.turniket.screens.title;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.rodion.turniket.MainGame;
import com.rodion.turniket.basics.BasicScreen;
import com.rodion.turniket.basics.BlackStage;
import com.rodion.turniket.stages.settings.SettingsStage;
import com.rodion.turniket.utilities.ScreenScale;
import com.rodion.turniket.utilities.SoundManagerMaster;

public class TitleScreen extends BasicScreen {
    private TitleStage stage;
    private final ScreenViewport screenViewport = new ScreenViewport();
    private BlackStage blackStage;
    private SettingsStage settingsStage;

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

            @Override
            public void onSettings() {
                super.onSettings();
                settingsStage.onEnter(stage);
            }
        };
        blackStage = new BlackStage(screenViewport, this);
        settingsStage = new SettingsStage(screenViewport, this);
//        settingsStage.init();
//        settingsStage.close();
    }

    public void init(){
        stage.addAction(Actions.fadeIn(0f));
        Gdx.input.setInputProcessor(stage);
//        settingsStage.init();
        settingsStage.close();
//        settingsStage.resetConfirmationMessage.close();
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
        settingsStage.act();
        settingsStage.draw();
    }

    public void onPlay() {

    }

    public void onEnterForward() {
        System.out.println("EnterTitle");
        Gdx.input.setInputProcessor(stage);
        blackStage.onHiding();
        SoundManagerMaster.playMusic("menu");
    }

    public void onEnterBackward() {
        init();
        stage.addAction(Actions.rotateTo(90, 0));
        stage.addAction(Actions.rotateBy(-90, .2f));
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        ScreenScale.resize(width, height);
        stage.resize(width, height);
        blackStage.resize(width, height);
        settingsStage.resize(width, height);
    }
}