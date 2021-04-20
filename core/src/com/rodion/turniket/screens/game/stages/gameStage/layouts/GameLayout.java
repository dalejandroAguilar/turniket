package com.rodion.turniket.screens.game.stages.gameStage.layouts;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.screens.game.layouts.TopMenuLayout;
import com.rodion.turniket.screens.game.stages.gameStage.entities.LevelTitleBarEntity;

import java.io.File;

public class GameLayout extends Layout {
    private TopMenuLayout topMenu;
    private ScoreLayout score;
    private BoardLayout board;
    private BottomMenuLayout bottomMenu;
    private LevelTitleBarEntity levelTitle;
    private StatusLayout status;

    public GameLayout(File file, int index, BasicStage basicStage) {
        super(basicStage);
        setFillParent(true);
        topMenu = new TopMenuLayout(getParentStage());

        levelTitle = new LevelTitleBarEntity(index, getParentStage());

//        levelTitle.setColor(Color.RED);

        score = new ScoreLayout(getParentStage());
        status = new StatusLayout(getParentStage());

        board = new BoardLayout(file, getParentStage()){
            @Override
            public void onMove() {
                status.setSteps(board.getSteps());
            }

            @Override
            public void onMoveTry() {
                GameLayout.this.onMoveTry();
            }

            @Override
            public void onMoveFinish() {
                GameLayout.this.onMoveFinish();
            }

            @Override
            public void onWin() {
                super.onWin();
                GameLayout.this.onWin();
                status.stop();
            }
        };
        bottomMenu = new BottomMenuLayout(getParentStage()){
            @Override
            public void onUndo() {
                board.onUndo();
                status.setSteps(board.getSteps());
            }

            @Override
            public void onRedo() {
                board.onRedo();
                status.setSteps(board.getSteps());
            }

            @Override
            public void onRestart() {
                board.onRestart();
                status.setSteps(board.getSteps());
                status.resetTimer();
            }
        };
        add(topMenu).expandX().fillX().row();
        add(levelTitle).padTop(20).padBottom(20).expandX().fillX().row();
        add(score).expandX().fillX().row();
        add(status).padBottom(20).expandX().fillX().grow().row();
        add(board).expandX().fillX().row();
        add(bottomMenu).bottom().expand().fillX();
    }

    public void onWin(){
    }


    public void onBegin(){
        status.onBegin();
        score.onBegin();
    }

    public void onEnd(){
        status.stop();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        topMenu.resize(width, height);
        board.resize(width, height);
        bottomMenu.resize(width, height);
        levelTitle.resize(width, height);
        status.resize(width, height);
        score.resize(width, height);
    }

    public void onMoveTry() {

    }

     public void onMoveFinish() {

    }
}
