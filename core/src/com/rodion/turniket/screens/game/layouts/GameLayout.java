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
    private StatusLayout status;
    private TopMenuLayout topMenu;

    public GameLayout(BasicStage basicStage) {
        super(basicStage);
        setFillParent(true);
        topMenu = new TopMenuLayout(getParentStage());
        levelTitle = new LevelTitleBarEntity(getParentStage());

        levelTitle.setColor(Color.GREEN);
        status = new StatusLayout(getParentStage());

        board = new BoardLayout(getParentStage()){
            @Override
            public void onMove() {
                status.setSteps(board.getSteps());
            }
        };
        bottomMenu = new BottomMenuLayout(getParentStage()){
            @Override
            public void onUndo() {
                board.onUndo();
                status.setSteps(board.getSteps());
            }

            @Override
            public void onRedo() {
                board.onRedo();
                status.setSteps(board.getSteps());
            }

            @Override
            public void onRestart() {
                board.onRestart();
                status.setSteps(board.getSteps());
            }
        };
        add(topMenu).expand().fill().row();
        add(levelTitle).expand().fillX().row();
        add(status).expand().fill().grow().row();
        add(board).expand().row();
        add(bottomMenu).bottom().expand().fillX();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        topMenu.resize(width, height);
        board.resize(width, height);
        bottomMenu.resize(width, height);
        levelTitle.resize(width, height);
        status.resize(width, height);
    }
}
