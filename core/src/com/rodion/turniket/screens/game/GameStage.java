package com.rodion.turniket.screens.game;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.rodion.turniket.basics.BasicScreen;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.screens.game.layouts.BoardLayout;

public class GameStage extends BasicStage {
    private BoardLayout boardLayout;
    public GameStage(Viewport viewport, BasicScreen basicScreen) {
        super(viewport, basicScreen);
        boardLayout = new BoardLayout(this);
        addActor(boardLayout);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        boardLayout.resize(width, height);
    }
}
