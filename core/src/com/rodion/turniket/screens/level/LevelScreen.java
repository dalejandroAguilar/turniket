package com.rodion.turniket.screens.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.rodion.turniket.MainGame;
import com.rodion.turniket.basics.BasicScreen;
import com.rodion.turniket.screens.game.GameScreen;
import com.rodion.turniket.screens.level.stages.PageStage;
import com.rodion.turniket.screens.level.stages.UILevelStage;
import com.rodion.turniket.utilities.LevelManagerMaster;
import com.rodion.turniket.utilities.ScreenScale;

public class LevelScreen extends BasicScreen {
    private UILevelStage uiStage;
    private BookLevel bookLevel;
    private final ScreenViewport screenViewport = new ScreenViewport();

    public LevelScreen(MainGame mainGame) {
        super(mainGame);
        init();
    }

    public void init() {
        final InputMultiplexer multiplexer;
        multiplexer = new InputMultiplexer();

        bookLevel = new BookLevel(screenViewport, this) {
            @Override
            public void onClose() {
                super.onClose();
                LevelScreen.this.onClose();
            }

            @Override
            public void onPickLevel() {
                super.onPickLevel();
                LevelScreen.this.onPickLevel();
            }

        };
        uiStage = new UILevelStage(screenViewport, this) {
            @Override
            public void onPreviousPage() {
                if (!bookLevel.isOnMoving()) {
                    if (bookLevel.onPrevious()) {
                        multiplexer.clear();
                        multiplexer.addProcessor(uiStage);
                        multiplexer.addProcessor(bookLevel.getPreviousPage());
                    }
                }
            }

            @Override
            public void onNextPage() {
                if (!bookLevel.isOnMoving()) {
                    if (bookLevel.onNext()) {
                        multiplexer.clear();
                        multiplexer.addProcessor(uiStage);
                        multiplexer.addProcessor(bookLevel.getNextPage());
                    }
                }
            }

            @Override
            public void onPreviousDifficulty() {
                System.out.println("onPreviousDifficulty");
            }

            @Override
            public void onNextDifficulty() {
                System.out.println("onNextDifficulty");
            }

            @Override
            public void onBack() {
                super.onBack();
                uiStage.addAction(Actions.fadeOut(.2f));
                bookLevel.getPage().addAction(Actions.fadeOut(.2f));
                addAction(Actions.sequence(
                        Actions.delay(.2f)
                        ,
                        Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                                LevelScreen.this.onBack();
                            }
                        })
                ));
            }

            @Override
            public void onSettings() {
                super.onSettings();
                LevelScreen.this.onSettings();
            }
        };

        multiplexer.addProcessor(uiStage);
        multiplexer.addProcessor(bookLevel.getPage());
        Gdx.input.setInputProcessor(multiplexer);
    }

    public void onEnterForward() {
        uiStage.addAction(Actions.rotateTo(-90, 0));
        bookLevel.getPage().addAction(Actions.rotateTo(-90, 0));
        uiStage.addAction(Actions.rotateBy(90, .2f));
        bookLevel.getPage().addAction(Actions.rotateBy(90, .2f));
    }

    public void onEnterBackward() {
        uiStage.addAction(Actions.rotateTo(90, 0));
        bookLevel.getPage().addAction(Actions.rotateTo(90, 0));
        uiStage.addAction(Actions.rotateBy(-90, .2f));
        bookLevel.getPage().addAction(Actions.rotateBy(-90, .2f));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(31.f / 255, 31.f / 255, 31.f / 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        bookLevel.render(delta);
        uiStage.act();
        uiStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        ScreenScale.resize(width, height);
        uiStage.resize(width, height);
        bookLevel.resize(width, height);
    }

    public void onClose() {
        uiStage.addAction(Actions.fadeOut(.2f));
    }

    public void onPickLevel() {
//        bookLevel.onExit();
        onGoToGameScreen();
//        mainGame.gameScreen.onEnter();
//        bookGame.getGame().addAction(Actions.rotateTo(-100));
//        preview.addAction(Actions.rotateTo(-100));
    }

    public void onGoToGameScreen() {
        System.out.println("on Go to game screen");
    }

    public void onBack(){

    }

    public void onSettings(){

    }
}