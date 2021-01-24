package com.rodion.turniket.screens.level;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.rodion.turniket.basics.BasicScreen;
import com.rodion.turniket.basics.BasicStage;

public class LevelStage extends BasicStage {
    private LevelLayout levelLayout;

    public LevelStage(Viewport viewport, BasicScreen basicScreen) {
        super(viewport, basicScreen);

        levelLayout = new LevelLayout(this);
        addActor(levelLayout);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        levelLayout.resize(width, height);
    }
}
