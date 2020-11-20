package com.rodion.turniket.screens.game.layouts;

import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.Layout;

public class GameLayout extends Layout {
    private BoardLayout boardLayout;

    public GameLayout(BasicStage basicStage) {
        super(basicStage);
        setFillParent(true);
        boardLayout = new BoardLayout(getParentStage());
        add(boardLayout);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        boardLayout.resize(width, height);
    }
}
