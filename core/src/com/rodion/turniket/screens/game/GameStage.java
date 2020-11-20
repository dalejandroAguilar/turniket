package com.rodion.turniket.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rodion.turniket.basics.BasicScreen;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.screens.game.layouts.BoardLayout;
import com.rodion.turniket.screens.game.layouts.GameLayout;

public class GameStage extends BasicStage {
    private GameLayout gameLayout;
    public GameStage(Viewport viewport, BasicScreen basicScreen) {
        super(viewport, basicScreen);
        gameLayout = new GameLayout(this);
        addActor(gameLayout);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        gameLayout.resize(width, height);
    }
}
