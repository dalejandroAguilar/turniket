package com.rodion.turniket.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.rodion.turniket.MainGame;
import com.rodion.turniket.basics.BasicScreen;
import com.rodion.turniket.screens.game.stages.ConfettiStage;
import com.rodion.turniket.screens.game.stages.previewStage.PreviewStage;
import com.rodion.turniket.screens.game.stages.solverStage.SolverStage;
import com.rodion.turniket.screens.game.stages.youWinStage.YouWinStage;
import com.rodion.turniket.stages.settings.SettingsStage;
import com.rodion.turniket.utilities.ScreenScale;

import java.io.FileNotFoundException;

public class GameScreen extends BasicScreen {
    private final ScreenViewport screenViewport = new ScreenViewport();
    private PreviewStage preview;
    private BookGame bookGame;
    private ConfettiStage confettiStage;
    private YouWinStage youWinPopUpStage;
    private SolverStage solverStage;
    private SettingsStage settingsStage;

    public GameScreen(MainGame mainGame) {
        super(mainGame);
        bookGame = new BookGame(screenViewport, this) {
            @Override
            public void onWin() {
                confettiStage.onThrow();
            }

            @Override
            public void onReturn() {
                super.onReturn();
                onGoToLevelScreen();
            }

            public void onSettings() {
                super.onSettings();
                settingsStage.onEnter(getGame());
            }

        };

        preview = new PreviewStage(screenViewport, this) {
            @Override
            public void onPlay() {
                preview.hide();
                bookGame.getGame().onInput();
                bookGame.onPlay();
            }

            @Override
            public void onUnlock() {
                super.onUnlock();
                bookGame.onUnlock();
            }

            @Override
            public void onPrevious() {
                if (!bookGame.isOnMoving())
                    bookGame.onPrevious();
            }

            @Override
            public void onNext() {
                GameScreen.this.onNext();
            }

            @Override
            public void onReturn() {
                super.onReturn();
                bookGame.getGame().addAction(Actions.fadeOut(0.2f));
                preview.addAction(Actions.fadeOut(0.2f));

                addAction(Actions.sequence(
                        Actions.fadeOut(0.2f),
                        Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                 onGoToLevelScreen();
                            }
                        })
                ));
            }

            @Override
            public void onSettings() {
                super.onSettings();
                settingsStage.onEnter(this);
            }
        };

        confettiStage = new ConfettiStage(screenViewport, this) {
            @Override
            public void onFinish() {
                super.onFinish();
                youWinPopUpStage.showUp();
                youWinPopUpStage.onInput();
            }
        };

        youWinPopUpStage = new YouWinStage(screenViewport, this) {
            @Override
            public void onContinue() {
                super.onContinue();
                onNext();
                preview.show();
                preview.onInput();
                bookGame.update();
            }

            @Override
            public void onSaveSolution() throws FileNotFoundException {
                super.onSaveSolution();
                bookGame.getGame().onSaveSolution();
            }
        };

        settingsStage = new SettingsStage(screenViewport,this);
//        settingsStage.close();

        solverStage = new SolverStage(screenViewport, this);

    }

    private void onNext() {
        if (!bookGame.isOnMoving())
            bookGame.onNext();
    }

    public void onInput() {
        preview.onInput();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(31.f / 255, 31.f / 255, 31.f / 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

//        backBufferStage.act();
//        backBufferStage.draw();
        bookGame.render(delta);
        confettiStage.act();
        confettiStage.draw();
        youWinPopUpStage.act();
        youWinPopUpStage.draw();
        preview.act();
        preview.draw();
        solverStage.act();
        solverStage.draw();
        settingsStage.act();
        settingsStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        ScreenScale.resize(width, height);
        confettiStage.resize(width, height);
        youWinPopUpStage.resize(width, height);
        preview.resize(width, height);
        bookGame.resize(width, height);
        solverStage.resize(width, height);
        settingsStage.resize(width, height);
    }

    public void onReturn() {
//        mainGame.setScreen(new LevelScreen(mainGame));
    }

    public void onEnter() {
        bookGame.getGame().addAction(Actions.rotateTo(-90, 0));
        preview.addAction(Actions.rotateTo(-90, 0));
        bookGame.getGame().addAction(Actions.rotateBy(90, .2f));
        preview.addAction(Actions.rotateBy(90, .2f));
        preview.onInput();
        preview.onEnter();
//        settingsStage.init();

    }

    public void init() {
        solverStage.hide();
        youWinPopUpStage.hide();
        preview.onInput();
        bookGame.init();
//        settingsStage.init();
        settingsStage.close();

    }

    public void onGoToLevelScreen() {

    }

    @Override
    public void dispose() {
        super.dispose();
//        backBufferStage.dispose();
    }
}