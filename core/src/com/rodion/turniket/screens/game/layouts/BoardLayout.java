package com.rodion.turniket.screens.game.layouts;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.ImageEntity;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.kernel.Token;
import com.rodion.turniket.screens.game.entities.TokenEntity;
import com.rodion.turniket.utilities.AssetManagerMaster;

public class BoardLayout extends Layout {
    private ImageEntity backboard;
    private ImageEntity lu;
    private ImageEntity lb;
    private ImageEntity ru;
    private ImageEntity rb;
    private ImageEntity burner;
    private TokenEntity token;

    public BoardLayout(BasicStage basicStage) {
        super(basicStage);
        Stack stack = new Stack();
        Table table = new Table();
        Table table2 = new Table();
        token = new TokenEntity();
        token.setColor(Color.MAGENTA);

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
        lu.setColor(Color.CYAN);

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
        lb.setColor(Color.GREEN);

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
        ru.setColor(Color.PURPLE);

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
        rb.setColor(Color.ORANGE);

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
        token.setPosition(100, 100);
        add(stack);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
//        token.setPosition(100,100);

        token.draw(batch, parentAlpha);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        TokenEntity.boardldpos.setPosition((int) (0.5 * width), (int) (0.5 * height));
        backboard.resize(width, height);
        lu.resize(width, height);
        lb.resize(width, height);
        ru.resize(width, height);
        rb.resize(width, height);
        burner.resize(width, height);
        token.resize(width, height);
    }
}
