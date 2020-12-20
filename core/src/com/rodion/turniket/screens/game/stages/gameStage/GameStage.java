package com.rodion.turniket.screens.game.stages.gameStage;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.rodion.turniket.basics.BasicScreen;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.screens.game.stages.gameStage.layouts.GameLayout;

public class GameStage extends BasicStage {
    private GameLayout gameLayout;
    public GameStage(Viewport viewport, BasicScreen basicScreen) {
        super(viewport, basicScreen);
        gameLayout = new GameLayout(this){
            @Override
            public void onWin() {
                super.onWin();
                GameStage.this.onWin();
            }
        };
        addActor(gameLayout);
    }

    public void onWin(){

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        gameLayout.resize(width, height);
    }


}
