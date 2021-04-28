package com.rodion.turniket.screens.game.stages.gameStage;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rodion.turniket.basics.BasicScreen;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.screens.game.stages.gameStage.layouts.GameLayout;

import java.io.File;

public class GameStage extends BasicStage {
    private GameLayout gameLayout;
    public GameStage(FileHandle file, int index, Viewport viewport, BasicScreen basicScreen) {
        super(viewport, basicScreen);
        gameLayout = new GameLayout(file,index,this){
            @Override
            public void onWin() {
                super.onWin();
                GameStage.this.onWin();
//                offInput();
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
        };
        addActor(gameLayout);
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
    }

    public void onReturn(){

    }
}
