package com.rodion.turniket.screens.game.stages.gameStage.layouts;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Align;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.ImageEntity;
import com.rodion.turniket.basics.LabelEntity;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.kernel.Node;
import com.rodion.turniket.screens.game.layouts.TopMenuLayout;
import com.rodion.turniket.screens.game.stages.gameStage.entities.LevelTitleBarEntity;
import com.rodion.turniket.screens.game.stages.gameStage.entities.LockEntity;
import com.rodion.turniket.utilities.ColorManagerMaster;
import com.rodion.turniket.utilities.FontManagerMaster;
import com.rodion.turniket.utilities.LevelManagerMaster;

import java.io.FileNotFoundException;

public class GameLayout extends Layout {
    private TopMenuLayout topMenu;
    private ScoreLayout score;
    private BoardLayout board;
    private BottomMenuLayout bottomMenu;
    private LevelTitleBarEntity levelTitle;
    private StatusLayout status;
    private boolean lockedStatus;

    public GameLayout(FileHandle file, int index, BasicStage basicStage) {
        super(basicStage);
        setFillParent(true);

        lockedStatus = false;
        topMenu = new TopMenuLayout(getParentStage()) {
            @Override
            public void onReturn() {
                super.onReturn();
                GameLayout.this.onReturn();
            }
        };

        levelTitle = new LevelTitleBarEntity(index, getParentStage());
        score = new ScoreLayout(getParentStage());
        status = new StatusLayout(getParentStage());

        board = new BoardLayout(file, getParentStage()) {
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
                GameLayout.this.onSolve();

            }
        };
        add(topMenu).expandX().fillX().row();
        add(levelTitle).padTop(40)
                .padBottom(40).expandX().fillX().row();
        add(score).expandX().fillX().row();
        add(status).padBottom(20).expandX().fillX().grow().row();
        add(board).expandX().fillX().row();
        add(bottomMenu).bottom().expand().fillX();

        lockedStatus = true;

//        setX(board.getX(Align.center), Align.center);
//        setY(board.getY(Align.center), Align.center);

        background(ColorManagerMaster.grayBg);



    }

    public void onWin() {
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

    public void onSolve() {
        board.getGame().loadSolution();
        status.onHint();
        score.onHint();
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
        board.getGame().saveSolution(LevelManagerMaster.getLevel());
    }

    public void setToPreview(){
        if (lockedStatus) {
            status.hide();
            score.hide();
//            lockEntity.getColor().a = 1;
            board.setToLock();
        }
        else {
            status.setToPreview();
            score.setToPreview();
//            lockEntity.getColor().a = 0;
            board.setToUnlock();
        }
    }

    public void onUnlock(){
        board.onUnlock();
        score.show();
        status.show();
    }
}