package com.rodion.turniket.screens.title;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rodion.turniket.basics.BasicScreen;
import com.rodion.turniket.basics.BasicStage;

public class TitleStage extends BasicStage {
    private TitleLayout titleLayout;
    public TitleStage(Viewport viewport, BasicScreen basicScreen) {
        super(viewport, basicScreen);
        Gdx.input.setInputProcessor(this);
        titleLayout = new TitleLayout(this){
            @Override
            public void onPlay() {
                TitleStage.this.onPlay();
            }
        };
        addActor(titleLayout);
    }

    public void onPlay(){

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        titleLayout.resize(width, height);
    }
}
