package com.rodion.turniket.screens.game.stages.gameStage;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rodion.turniket.basics.BasicScreen;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.kernel.constants.Direction;
import com.rodion.turniket.kernel.constants.TokenColor;
import com.rodion.turniket.screens.game.stages.gameStage.layouts.GameLayout;
import com.rodion.turniket.screens.game.stages.solverStage.SolverLayout;
//import com.rodion.turniket.screens.game.stages.gameStage.layouts.SolverLayout;

import java.io.File;
import java.io.FileNotFoundException;

public class GameStage extends BasicStage {
    private GameLayout gameLayout;
    private SolverLayout solverLayout;
    public GameStage(FileHandle file, int index, Viewport viewport, BasicScreen basicScreen) {
        super(viewport, basicScreen);
        gameLayout = new GameLayout(file,index,this){
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
            public void onHint() {
                super.onHint();
                solverLayout.onShow();
            }
        };
        solverLayout = new SolverLayout(this){
            @Override
            public void onNext() {
                super.onNext();
                System.out.println("onNext gamestage");
                gameLayout.moveFromSolver();
            }

            @Override
            public void onPrevious() {
                super.onPrevious();
                System.out.println("onPrevious gamestage");
                gameLayout.undoFromSolver();
//                gameLayout.getBoard().onUndo();
//                if (gameLayout.getBoard().getGame().move(TokenColor.Magenta, Direction.Up)) {
//                    gameLayout.getBoard().onMoveTry();
//                    gameLayout.getBoard().onMove();
//                }
            }
        };

        addActor(gameLayout);
        addActor(solverLayout);
        solverLayout.onHide();
    }

    public void onWin(){
        System.out.println("Win GAMELAYOUT");
    }

    public void onBegin(){
        gameLayout.onBegin();
    }

    public void onEnd(){
        gameLayout.onEnd();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        gameLayout.resize(width, height);
        solverLayout.resize(width, height);
    }

    public void onReturn(){

    }

    public void onSaveSolution() throws FileNotFoundException {
        System.out.println("Save solution game stage");
        gameLayout.onSaveSolution();
    }


}
