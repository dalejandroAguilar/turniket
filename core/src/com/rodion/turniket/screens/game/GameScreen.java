package com.rodion.turniket.screens.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.rodion.turniket.MainGame;
import com.rodion.turniket.basics.BasicScreen;
import com.rodion.turniket.screens.game.stages.ConfettiStage;
import com.rodion.turniket.screens.game.stages.previewStage.PreviewStage;
import com.rodion.turniket.screens.game.stages.solverStage.SolverStage;
import com.rodion.turniket.screens.game.stages.youWinStage.YouWinStage;
import com.rodion.turniket.screens.level.LevelScreen;
import com.rodion.turniket.utilities.LevelManagerMaster;
import com.rodion.turniket.utilities.ScreenScale;

import java.io.FileNotFoundException;

public class GameScreen extends BasicScreen {
    private PreviewStage preview;
    private BookGame bookGame;
    private ConfettiStage confettiStage;
    private YouWinStage youWinPopUpStage;
    private SolverStage solverStage;
    private final ScreenViewport screenViewport = new ScreenViewport();

    public GameScreen(MainGame mainGame) {
        super(mainGame);

        bookGame = new BookGame(screenViewport, this) {
            @Override
            public void onWin() {
//                System.out.println("onWin MainGame");
                confettiStage.onThrow();
            }

            @Override
            public void onReturn() {
                super.onReturn();
                GameScreen.this.onReturn();
            }
        };

        preview = new PreviewStage(screenViewport, this) {
            @Override
            public void onPlay() {
                preview.hide();
                bookGame.getGame().onInput();
                bookGame.getGame().onBegin();
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

        solverStage.hide();
        youWinPopUpStage.hide();
        preview.offInput();
        preview.onInput();
    }

    private void onNext() {
        if (!bookGame.isOnMoving())
            bookGame.onNext();
    }

    public void onInput(){
        preview.onInput();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(31.f / 255, 31.f / 255, 31.f / 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
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
        mainGame.setScreen(new LevelScreen(mainGame));
    }
}