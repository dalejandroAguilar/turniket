package com.rodion.turniket.screens.game.layouts;

import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.ImageEntity;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.utilities.AssetManagerMaster;

public class BoardLayout extends Layout {
    private ImageEntity backboard;
    private ImageEntity lu;
    private ImageEntity lb;
    private ImageEntity ru;
    private ImageEntity rb;
    private ImageEntity burner;
    public BoardLayout(BasicStage basicStage) {
        super(basicStage);
        Stack stack = new Stack();
        Table table = new Table();
        Table table2 = new Table();
        Table table3 = new Table();

        backboard = new ImageEntity() {
            @Override
            public void setAssetAddress() {
                super.setAssetAddress();
                setAssetManager(AssetManagerMaster.game);
                assetPath = "game";
                assetName = "back-board";
            }
        };
        backboard.prepareAssets();
        lu = new ImageEntity() {
            @Override
            public void setAssetAddress() {
                super.setAssetAddress();
                setAssetManager(AssetManagerMaster.game);
                assetPath = "game";
                assetName = "lu";
            }
        };
        lu.prepareAssets();
        lb = new ImageEntity() {
            @Override
            public void setAssetAddress() {
                super.setAssetAddress();
                setAssetManager(AssetManagerMaster.game);
                assetPath = "game";
                assetName = "lb";
            }
        };
        lb.prepareAssets();
        ru = new ImageEntity() {
            @Override
            public void setAssetAddress() {
                super.setAssetAddress();
                setAssetManager(AssetManagerMaster.game);
                assetPath = "game";
                assetName = "ru";
            }
        };
        ru.prepareAssets();
        rb = new ImageEntity() {
            @Override
            public void setAssetAddress() {
                super.setAssetAddress();
                setAssetManager(AssetManagerMaster.game);
                assetPath = "game";
                assetName = "rb";
            }
        };
        rb.prepareAssets();
        burner = new ImageEntity() {
            @Override
            public void setAssetAddress() {
                super.setAssetAddress();
                setAssetManager(AssetManagerMaster.game);
                assetPath = "game";
                assetName = "burners";
            }
        };
        burner.prepareAssets();
        table.add(lu).left().top().expand();
        table.add(ru).right().top().expand().row();
        table.add(lb).left().bottom().expand();
        table.add(rb).right().bottom().expand();
        stack.add(backboard);
        stack.add(table);
        table2.add(burner);
        stack.add(table2);

        add(stack);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        backboard.resize(width, height);
        lu.resize(width, height);
        lb.resize(width, height);
        ru.resize(width, height);
        rb.resize(width, height);
        burner.resize(width, height);
    }
}
