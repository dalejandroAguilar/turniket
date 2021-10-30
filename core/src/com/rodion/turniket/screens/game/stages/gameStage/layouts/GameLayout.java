package com.rodion.turniket.screens.game.stages.gameStage.layouts;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.screens.game.layouts.TopMenuLayout;
import com.rodion.turniket.screens.game.stages.gameStage.entities.LevelTitleBarEntity;
import com.rodion.turniket.utilities.ColorManagerMaster;
import com.rodion.turniket.utilities.Level;
import com.rodion.turniket.utilities.Solution;


import java.io.FileNotFoundException;

public class GameLayout extends Layout {
    private TopMenuLayout topMenu;
    private ScoreLayout score;
    private BoardLayout board;
    private BottomMenuLayout bottomMenu;
    private LevelTitleBarEntity levelTitle;
    private StatusLayout status;
    private boolean lockedStatus;

    public GameLayout(final Level level, int index, BasicStage basicStage) {
        super(basicStage);
        setFillParent(true);
        lockedStatus = !level.isUnlocked();
        topMenu = new TopMenuLayout(getParentStage()) {
            @Override
            public void onReturn() {
                super.onReturn();
                GameLayout.this.onReturn();
            }

            @Override
            public void onSettings() {
                super.onSettings();
                GameLayout.this.onSettings();
            }
        };

        levelTitle = new LevelTitleBarEntity(index, getParentStage());
        score = new ScoreLayout(getParentStage(), level);
        status = new StatusLayout(getParentStage(), level);

        board = new BoardLayout(level, getParentStage()) {
            @Override
            public void onMove() {
                super.onMove();
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
                score.onWin();
            }

            @Override
            public void moveFromSolution() {
                super.moveFromSolution();
                level.loadSolution(getParentStage().getParentScreen().getMainGame().multiplatform);
                System.out.println(level.getSolutionRead().getNSteps());
                Solution.Step step = level.getSolutionRead().getStep();
                getGame().move(step.getTokenColor(getGame().getState()), step.getDirection());
                onMoveTry();
                onMove();
                level.getSolutionRead().goForward();
            }

            @Override
            public void undoFromSolution() {
                level.getSolutionRead().goBackward();
                super.undoFromSolution();
            }
        };
        bottomMenu = new BottomMenuLayout(getParentStage()) {
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
                GameLayout.this.restart();
            }

            @Override
            public void onHint() {
                super.onHint();
                GameLayout.this.onHint();
            }

        };
        add(topMenu).expandX().fillX().row();
        add(levelTitle).padTop(40)
                .padBottom(40).expandX().fillX().row();
        add(score).expandX().fillX().row();
        add(status).padBottom(20).expandX().fillX().grow().row();
        add(board).expandX().fillX().row();
        add(bottomMenu).bottom().expand().fillX();

//        lockedStatus = false;

//        setX(board.getX(Align.center), Align.center);
//        setY(board.getY(Align.center), Align.center);

        background(ColorManagerMaster.grayBg);
    }


    public void onWin() {
        topMenu.onIncreasing(3);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
//        lockEntity.draw(batch, parentAlpha);
//        requirementsLabel.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
//        lockEntity.act(delta);
    }

    public void onEnd() {
        status.stop();
    }

    public boolean isOnBegin() {
        return board.isOnBegin();
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
//        lockEntity.resize(width, height);
    }

    public void moveFromSolver() {
        board.moveFromSolution();
    }

    public void undoFromSolver() {
        board.undoFromSolution();
    }

    public void onMoveTry() {
    }

    public void onMoveFinish() {
    }

    public void onReturn() {

    }

    public void onSettings() {

    }

    public void onHint() {

    }

    public void onSetSolve() {
        board.getGame().loadSolution();
        restart();
        status.onHint();
        score.onHint();
    }

    public void onSetGame() {
        restart();
        status.onPlay();
        score.onPlay();
    }

    public void restart() {
        board.onRestart();
        status.setSteps(board.getSteps());
        status.resetTimer();
    }

    public BoardLayout getBoard() {
        return board;
    }

    public void onSaveSolution() throws FileNotFoundException {
        System.out.println("Save solution game layout");
//        board.getGame().saveSolution(LevelManagerMaster.getLevel());
    }

    public void setToPreview() {
        if (lockedStatus) {
            status.hide();
            score.hide();
            board.setToLock();
        } else {
            status.setToPreview();
            score.setToPreview();
            board.setToUnlock();
        }
    }

    public void onPlay() {
        status.onPlay();
        score.onPlay();
    }

    public void onUnlock() {
        board.onUnlock();
        score.show();
        status.show();
    }

    @Override
    public void onShow() {
//        super.onShow();

    }

    @Override
    public void onHide() {
//        super.onHide();
    }

    @Override
    public void hide() {
//        super.hide();
    }


    @Override
    public void show() {
//        super.show();
    }

    public boolean isLockedStatus() {
        return lockedStatus;
    }

    public void update(){
        topMenu.update();
    }
}