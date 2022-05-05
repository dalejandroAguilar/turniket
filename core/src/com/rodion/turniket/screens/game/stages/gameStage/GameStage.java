package com.rodion.turniket.screens.game.stages.gameStage;

import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rodion.turniket.basics.BasicScreen;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.screens.game.stages.gameStage.layouts.GameLayout;
import com.rodion.turniket.screens.game.stages.gameStage.layouts.LockLayout;
import com.rodion.turniket.screens.game.stages.gameStage.layouts.SolverLayout;
import com.rodion.turniket.stages.message.MessageStage;
import com.rodion.turniket.utilities.DecisionFrame;
import com.rodion.turniket.utilities.Level;
import com.rodion.turniket.utilities.Multiplatform;

import java.io.FileNotFoundException;

public class GameStage extends BasicStage {
    private GameLayout gameLayout;
    private SolverLayout solverLayout;
    private MessageStage confirmationToSolver;
    private MessageStage confirmationToGame;
    private LockLayout lockLayout;

    public GameStage(final Level level, int index, Viewport viewport, BasicScreen basicScreen) {
        super(viewport, basicScreen);

        gameLayout = new GameLayout(level, index, this) {
            @Override
            public void onWin() {
                super.onWin();
                GameStage.this.onWin();
//                level.saveStatus(Mult)
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
            public void onSettings() {
                super.onSettings();
                GameStage.this.onSettings();
            }

            @Override
            public void onHint() {
                gameLayout.restartSolver();
                if (gameLayout.isOnBegin())
                    solverLayout.onShow();
                else {
                    confirmationToSolver.onEnter();
                }
            }

            @Override
            public void setToPreview() {
                super.setToPreview();
                if (!isLockedStatus())
                    lockLayout.hide();
            }

        };

        lockLayout = new LockLayout(this, level) {
            @Override
            public void updateLockEntityPosition() {
                super.updateLockEntityPosition();
                getLockEntity().setX(gameLayout.getBoard().getX(Align.center), Align.center);
                getLockEntity().setY(gameLayout.getBoard().getY(Align.center), Align.center);
            }

            @Override
            public void onEnd() {
                super.onEnd();
                gameLayout.onUnlock();
            }
        };

        solverLayout = new SolverLayout(this) {
            boolean isWin = false;
            @Override
            public void onNext() {
                if(!isWin) {
                    super.onNext();
                    gameLayout.moveFromSolver();
                    isWin = gameLayout.isWin;
                }
            }

            @Override
            public void onPrevious() {
                if(!isWin) {
                    super.onPrevious();
                    gameLayout.undoFromSolver();
                    isWin = gameLayout.isWin;
                }
            }

            @Override
            public void onBack() {
                super.onBack();
//                confirmationSolverToGame.onShow();
                confirmationToGame.onEnter();
            }

            @Override
            public void onSettings() {
                super.onSettings();
                GameStage.this.onSettings();
            }

            @Override
            public void onReturn() {
                super.onReturn();
                GameStage.this.onReturn();
            }
        };

        String[] solverMessageLine = {"Sure to go to solver", "Your progress will lose."};
        confirmationToSolver = new MessageStage(viewport, basicScreen, solverMessageLine);
        confirmationToSolver.setDecisionFrame(
                new DecisionFrame() {
                    @Override
                    public void onAffirmativeDecision() {
                        confirmationToSolver.onExit();
                        gameLayout.restart();
                        gameLayout.onSetSolve();
                        solverLayout.show();
                        onInput();
                    }

                    @Override
                    public void onNegativeDecision() {
                        confirmationToSolver.onExit();
                        onInput();
                    }
                }
        );
        confirmationToSolver.close();

        String[] gameMessageLine = {"Sure to go to game", "Your progress will lose."};
        confirmationToGame = new MessageStage(viewport, basicScreen, gameMessageLine);
        confirmationToGame.setDecisionFrame(new DecisionFrame() {
            @Override
            public void onAffirmativeDecision() {
                solverLayout.hide();
                confirmationToGame.onExit();
                gameLayout.onSetGame();
                onInput();
            }

            @Override
            public void onNegativeDecision() {
                confirmationToGame.onExit();
                onInput();
            }
        });
        confirmationToGame.close();
        addActor(gameLayout);
        addActor(solverLayout);
        addActor(lockLayout);

        solverLayout.hide();
        gameLayout.setToPreview();
    }

    public void onPlay() {
        gameLayout.onPlay();
    }

    public void onWin() {

    }


    public void onEnd() {
        gameLayout.onEnd();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        gameLayout.resize(width, height);
        solverLayout.resize(width, height);
        confirmationToSolver.resize(width, height);
        confirmationToGame.resize(width, height);
        lockLayout.resize(width, height);
    }

    public void onReturn() {

    }

    public void onSettings() {

    }

    @Override
    public void draw() {
        super.draw();
        confirmationToSolver.draw();
        confirmationToGame.draw();

    }

    @Override
    public void act() {
        super.act();
        confirmationToSolver.act();
        confirmationToGame.act();
    }

//    public void onSaveSolution() throws FileNotFoundException {
//        System.out.println("Save solution game stage");
//        gameLayout.onSaveSolution();
//    }

    public boolean saveSolution(Multiplatform multiplatform){
        return gameLayout.saveSolution(multiplatform);
    }

    public boolean saveStatus(Multiplatform multiplatform){
        return gameLayout.saveStatus(multiplatform);
    }

    public void onUnlock() {
        lockLayout.onUnlock();
    }

    public boolean nStarsSatisfied(){
        return gameLayout.nStarsSatisfied();
    }
    public void update() {
        gameLayout.update();
    }

    public boolean isUnlocked(){
        return !gameLayout.isLockedStatus();
    }
}