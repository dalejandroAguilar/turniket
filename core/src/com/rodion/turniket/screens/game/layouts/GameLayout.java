package com.rodion.turniket.screens.game.layouts;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.ImageEntity;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.screens.game.entities.LevelTitleBarEntity;
import com.rodion.turniket.utilities.AssetManagerMaster;

public class GameLayout extends Layout {
    private BoardLayout board;
    private BottomMenuLayout bottomMenu;
    private LevelTitleBarEntity levelTitle;

    public GameLayout(BasicStage basicStage) {
        super(basicStage);
        setFillParent(true);
        levelTitle = new LevelTitleBarEntity(getParentStage());
        levelTitle.setColor(Color.GREEN);

        board = new BoardLayout(getParentStage());
        bottomMenu = new BottomMenuLayout(getParentStage()){
            @Override
            public void onUndo() {
                board.onUndo();
            }

            @Override
            public void onRedo() {
                board.onRedo();
            }

            @Override
            public void onRestart() {
//                super.onRestart();
                board.onRestart();
            }
        };
        add(levelTitle).expand().fillX().row();
        add(board).expand().row();
        add(bottomMenu).bottom().expand().fillX();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        board.resize(width, height);
        bottomMenu.resize(width, height);
        levelTitle.resize(width, height);
    }
}
