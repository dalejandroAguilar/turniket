package com.rodion.turniket.screens.game.stages;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.rodion.turniket.basics.BasicScreen;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.screens.game.layouts.ConfettiLayout;

public class ConfettiStage extends BasicStage {
    ConfettiLayout confetti;
    public ConfettiStage(Viewport viewport, BasicScreen basicScreen) {
        super(viewport, basicScreen);
        confetti = new ConfettiLayout(this){
            @Override
            public void onFinish() {
                super.onFinish();
                ConfettiStage.this.onFinish();
            }
        };
        addActor(confetti);
    }

    public void onThrow(){
        confetti.onThrow();
    }

    public void onFinish(){

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        confetti.resize(width, height);
    }


}
