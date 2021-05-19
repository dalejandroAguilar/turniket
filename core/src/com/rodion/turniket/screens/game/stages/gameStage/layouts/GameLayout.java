package com.rodion.turniket.screens.game.stages.gameStage.layouts;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Align;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.LabelEntity;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.kernel.Token;
import com.rodion.turniket.kernel.constants.Direction;
import com.rodion.turniket.kernel.constants.TokenColor;
import com.rodion.turniket.screens.game.layouts.TopMenuLayout;
import com.rodion.turniket.screens.game.stages.gameStage.entities.LevelTitleBarEntity;
import com.rodion.turniket.screens.game.stages.gameStage.entities.LockEntity;
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
    private LockEntity lockEntity;
    private LabelEntity requirementsLabel;

    private boolean lockedStatus;

    public GameLayout(FileHandle file, int index, BasicStage basicStage) {
        super(basicStage);
        setFillParent(true);

        lockedStatus = true;
        topMenu = new TopMenuLayout(getParentStage()) {
            @Override
            public void onReturn() {
                super.onReturn();
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
                board.onRestart();
                status.setSteps(board.getSteps());
                status.resetTimer();
            }

            @Override
            public void onHint() {
                super.onHint();
                GameLayout.this.onHint();

            }
        };

        lockEntity = new LockEntity() {
            @Override
            public void updatePosition() {
                super.updatePosition();
                setX(board.getX(Align.center), Align.center);
                setY(board.getY(Align.center), Align.center);
            }

            @Override
            public void onEnd() {
                lockEntity.addAction(Actions.moveBy(500, 0, 0.4f));
                setInvalidated(true);
                System.out.println("next");
            }
        };

        requirementsLabel = new LabelEntity("Requirementes: 10 stars.",
                FontManagerMaster.nexaStyle) {
            @Override
            public void updatePosition() {
                super.updatePosition();
                requirementsLabel.setX(board.getX(Align.center), Align.center);
                requirementsLabel.setY(status.getY(Align.center), Align.center);
            }
        };

        add(topMenu).expandX().fillX().row();
        add(levelTitle).padTop(40)
                .padBottom(40).expandX().fillX().row();
        add(score).expandX().fillX().row();
        add(status).padBottom(20).expandX().fillX().grow().row();
        add(board).expandX().fillX().row();
        add(bottomMenu).bottom().expand().fillX();

//        add(lockEntity);
//        lockEntity.setOnPlay(true);

        board.setLockedStatus(false);

//        if(lockedStatus){
//            board.setVisible(false);
//        }

    }

    public void onWin() {
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        lockEntity.draw(batch, parentAlpha);
//        requirementsLabel.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        lockEntity.act(delta);
    }

    public void onBegin() {
        status.onBegin();
        score.onBegin();
        lockEntity.addAction(Actions.fadeOut(.2f));
    }

    public void onEnd() {
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
        lockEntity.resize(width, height);
        requirementsLabel.resize(width, height);
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

    public void onHint() {
        board.getGame().loadSolution();
        status.onHint();
        score.onHint();
    }

    public boolean isLocked() {
        return lockedStatus;
    }

    public void setLockedStatus(boolean lockedStatus) {
        this.lockedStatus = lockedStatus;
    }

    public BoardLayout getBoard() {
        return board;
    }

    public void onSaveSolution() throws FileNotFoundException {
        System.out.println("Save solution game layout");
        board.getGame().saveSolution(LevelManagerMaster.getLevel());
    }

}
