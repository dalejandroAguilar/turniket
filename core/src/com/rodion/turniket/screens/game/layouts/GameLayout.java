package com.rodion.turniket.screens.game.layouts;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.utilities.ColorManagerMaster;

public class GameLayout extends Layout {
    private BoardLayout board;
    private BottomMenuLayout bottomMenu;

    public GameLayout(BasicStage basicStage) {
        super(basicStage);
        setFillParent(true);
        board = new BoardLayout(getParentStage());
        bottomMenu = new BottomMenuLayout(getParentStage());
        add(board).expand().row();
        add(bottomMenu).bottom().expandX().fillX();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        board.resize(width, height);
        bottomMenu.resize(width, height);
    }
}
