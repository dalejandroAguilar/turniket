package com.rodion.turniket.screens.game.stages.youWinStage;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.rodion.turniket.basics.BasicScreen;
import com.rodion.turniket.basics.BasicStage;

import java.io.FileNotFoundException;

public class YouWinStage extends BasicStage {
    private YouWinLayout popUp;
    public YouWinStage(Viewport viewport, BasicScreen basicScreen) {
        super(viewport, basicScreen);
        popUp = new YouWinLayout(this) {
            @Override
            public void onContinue() {
                super.onContinue();
                YouWinStage.this.onContinue();
            }

            @Override
            public void onSaveSolution() throws FileNotFoundException {
                super.onSaveSolution();
                YouWinStage.this.onSaveSolution();
            }
        };
        addActor(popUp);
    }

    public void showUp(){
        show();
        popUp.showUp();
    }

    public void onContinue(){
        popUp.onHide();
        hide();
    }

    public void onSaveSolution() throws FileNotFoundException {

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        popUp.resize(width, height);
    }

}
