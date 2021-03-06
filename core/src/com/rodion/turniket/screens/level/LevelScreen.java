package com.rodion.turniket.screens.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
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
    private final ScreenViewport screenViewport = new  ScreenViewport();

    public LevelScreen(MainGame mainGame) {
        super(mainGame);
        final InputMultiplexer multiplexer;
        multiplexer = new InputMultiplexer();

        bookLevel = new BookLevel(screenViewport,  this){
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
                    if(bookLevel.onPrevious()) {
                        multiplexer.clear();
                        multiplexer.addProcessor(uiStage);
                        multiplexer.addProcessor(bookLevel.getPreviousPage());
                    }
                }
            }

            @Override
            public void onNextPage() {
                if (!bookLevel.isOnMoving()) {
                    if(bookLevel.onNext()) {
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
        };

        multiplexer.addProcessor(uiStage);
        multiplexer.addProcessor(bookLevel.getPage());
        Gdx.input.setInputProcessor(multiplexer);
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

    public void onPickLevel(){
//        mainGame.gameScreen.onInput();
//        Gdx.input.setInputProcessor(mainGame.gameScreen);
        System.out.println("Boookmark "+LevelManagerMaster.getBookmark());
//        mainGame.gameScreen.ini();
//        bookLevel.getPage().getLevelLayout().getLevels().get(1).addAction(Actions.moveBy(10,10,0.5f));
        System.out.println(LevelManagerMaster.getNLevels());
        mainGame.setScreen(new GameScreen(mainGame));
    }
}