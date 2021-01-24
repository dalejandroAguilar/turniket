package com.rodion.turniket.screens.game.stages.gameStage;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.rodion.turniket.basics.BasicScreen;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.screens.game.stages.gameStage.layouts.GameLayout;

import java.io.File;

public class GameStage extends BasicStage {
    private GameLayout gameLayout;
    public GameStage(File file, Viewport viewport, BasicScreen basicScreen) {
        super(viewport, basicScreen);
        gameLayout = new GameLayout(file,this){
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

        };
        addActor(gameLayout);
    }

    public void onWin(){
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
}
