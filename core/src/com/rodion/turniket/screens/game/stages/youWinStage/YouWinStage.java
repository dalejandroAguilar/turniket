package com.rodion.turniket.screens.game.stages.youWinStage;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.rodion.turniket.basics.BasicScreen;
import com.rodion.turniket.basics.BasicStage;

public class YouWinStage extends BasicStage {
    private YouWinLayout popUp;
    public YouWinStage(Viewport viewport, BasicScreen basicScreen) {
        super(viewport, basicScreen);
        popUp = new YouWinLayout(this);
        addActor(popUp);
    }

    public void showUp(){
        popUp.showUp();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        popUp.resize(width, height);
    }
}
