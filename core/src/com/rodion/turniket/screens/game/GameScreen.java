package com.rodion.turniket.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.rodion.turniket.MainGame;
import com.rodion.turniket.basics.BasicScreen;
import com.rodion.turniket.screens.game.stages.BackBufferStage;
import com.rodion.turniket.screens.game.stages.ConfettiStage;
import com.rodion.turniket.screens.game.stages.previewStage.PreviewStage;
import com.rodion.turniket.screens.game.stages.solverStage.SolverStage;
import com.rodion.turniket.screens.game.stages.youWinStage.YouWinStage;
import com.rodion.turniket.screens.level.LevelScreen;
import com.rodion.turniket.utilities.LevelManagerMaster;
import com.rodion.turniket.utilities.ScreenScale;
import com.rodion.turniket.utilities.ScreenShotManager;

import java.io.FileNotFoundException;

import jdk.javadoc.internal.doclets.formats.html.markup.Table;

public class GameScreen extends BasicScreen {
//    private BackBufferStage backBufferStage;
    private PreviewStage preview;
    private BookGame bookGame;
    private ConfettiStage confettiStage;
    private YouWinStage youWinPopUpStage;
    private SolverStage solverStage;
    private final ScreenViewport screenViewport = new ScreenViewport();

    public GameScreen(MainGame mainGame) {
        super(mainGame);

//        backBufferStage = new BackBufferStage(screenViewport, this);
        bookGame = new BookGame(screenViewport, this) {
            @Override
            public void onWin() {
                confettiStage.onThrow();
            }

            @Override
            public void onReturn() {
                super.onReturn();
                onGoToLevelScreen();
//                GameScreen.this.onReturn();
            }
        };

        preview = new PreviewStage(screenViewport, this) {
            @Override
            public void onPlay() {
                preview.hide();
                bookGame.getGame().onInput();
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
                onGoToLevelScreen();
//                GameScreen.this.onReturn();
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
            }

            @Override
            public void onSaveSolution() throws FileNotFoundException {
                super.onSaveSolution();
                bookGame.getGame().onSaveSolution();
            }
        };

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
    }

    public void onReturn() {
//        mainGame.setScreen(new LevelScreen(mainGame));
    }

    public void onEnter() {
//        backBufferStage.onInit();
        bookGame.getGame().addAction(Actions.rotateTo(-90, 0));
        preview.addAction(Actions.rotateTo(-90, 0));
        bookGame.getGame().addAction(Actions.rotateBy(90, .25f));
        preview.addAction(Actions.rotateBy(90, .25f));
        preview.onInput();
        preview.onEnter();
//        Table table = new Table(|);
//        tabl
    }

    public void init() {
        solverStage.hide();
        youWinPopUpStage.hide();
        preview.offInput();
        preview.onInput();
        bookGame.init();
    }

    public void onGoToLevelScreen() {

    }

    @Override
    public void dispose() {
        super.dispose();
//        backBufferStage.dispose();
    }
}