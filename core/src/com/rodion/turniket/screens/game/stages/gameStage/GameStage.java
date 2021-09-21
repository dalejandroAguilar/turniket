package com.rodion.turniket.screens.game.stages.gameStage;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rodion.turniket.basics.BasicScreen;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.screens.game.stages.gameStage.layouts.ConfirmationGameLayout;
import com.rodion.turniket.screens.game.stages.gameStage.layouts.ConfirmationSolutionLayout;
import com.rodion.turniket.screens.game.stages.gameStage.layouts.GameLayout;
import com.rodion.turniket.screens.game.stages.gameStage.layouts.LockLayout;
import com.rodion.turniket.screens.game.stages.gameStage.layouts.SolverLayout;
import com.rodion.turniket.utilities.Level;
import com.rodion.turniket.utilities.Solution;

import java.io.FileNotFoundException;

public class GameStage extends BasicStage {
    private GameLayout gameLayout;
    private SolverLayout solverLayout;
    private ConfirmationGameLayout confirmationGameToSolver;
    private ConfirmationSolutionLayout confirmationSolverToGame;
    private LockLayout lockLayout;

    public GameStage(Level level, int index, Viewport viewport, BasicScreen basicScreen) {
        super(viewport, basicScreen);
        gameLayout = new GameLayout(level, index, this) {
            @Override
            public void onWin() {
                super.onWin();
                GameStage.this.onWin();
            }

            @Override
            public void onMoveTry() {
                offInput();
            }

            @Override
            public void onMoveFinish() {
                onInput();
            }

            @Override
            public void onReturn() {
                super.onReturn();
                GameStage.this.onReturn();
            }

            @Override
            public void onSolve() {
                super.onSolve();
                if (gameLayout.isOnBegin())
                    solverLayout.onShow();
                else
                    confirmationGameToSolver.onShow();
//                solverLayout.show();
            }
        };

        lockLayout = new LockLayout(this) {
            @Override
            public void updateLockEntityPosition() {
                super.updateLockEntityPosition();
//                Vector2 boardPosition = gameLayout.getBoard().get(Align.center);
                getLockEntity().setX(gameLayout.getBoard().getX(Align.center), Align.center);
                getLockEntity().setY(gameLayout.getBoard().getY(Align.center), Align.center);
            }

            @Override
            public void onEnd() {
                super.onEnd();
                gameLayout.onUnlock();
            }
        };

        confirmationGameToSolver = new ConfirmationGameLayout(this) {
            @Override
            public void onOk() {
                super.onOk();
                gameLayout.restart();
                solverLayout.show();
            }

            @Override
            public void onCancel() {
                super.onCancel();
            }

        };

        solverLayout = new SolverLayout(this) {
            @Override
            public void onNext() {
                super.onNext();
                gameLayout.moveFromSolver();
            }

            @Override
            public void onPrevious() {
                super.onPrevious();
                gameLayout.undoFromSolver();
            }

            @Override
            public void onBack() {
                super.onBack();
                confirmationSolverToGame.onShow();
            }
        };

        confirmationSolverToGame = new ConfirmationSolutionLayout(this) {
            @Override
            public void onOk() {
                solverLayout.hide();
                super.onOk();
            }

            @Override
            public void onCancel() {
                super.onCancel();
            }
        };

        addActor(gameLayout);
        addActor(solverLayout);
        addActor(confirmationGameToSolver);
        addActor(confirmationSolverToGame);
        addActor(lockLayout);

        solverLayout.hide();
        confirmationGameToSolver.hide();
        confirmationSolverToGame.hide();
        gameLayout.setToPreview();
    }

    public void onWin() {
        System.out.println("Win GAMELAYOUT");
    }


    public void onEnd() {
        gameLayout.onEnd();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        gameLayout.resize(width, height);
        solverLayout.resize(width, height);
        confirmationGameToSolver.resize(width, height);
        confirmationSolverToGame.resize(width, height);
        lockLayout.resize(width, height);
    }

    public void onReturn() {

    }

    public void onSaveSolution() throws FileNotFoundException {
        System.out.println("Save solution game stage");
        gameLayout.onSaveSolution();
    }

    public void onUnlock() {
        lockLayout.onUnlock();
    }
}