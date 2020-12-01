package com.rodion.turniket.screens.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.ImageEntity;
import com.rodion.turniket.basics.LabelEntity;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.utilities.AssetManagerMaster;
import com.rodion.turniket.utilities.ColorManagerMaster;
import com.rodion.turniket.utilities.FontManagerMaster;

public class LevelTitleBarEntity extends Layout {

    private ImageEntity left;
    private ImageEntity right;
    private ImageEntity center;
    private LabelEntity label;


    public LevelTitleBarEntity(BasicStage basicStage) {
        super(basicStage);
        setFillParent(false);
        left = new ImageEntity(){
            @Override
            public void setAssetAddress() {
                setAssetManager(AssetManagerMaster.game);
                assetPath = "game";
                assetName = "left_title_bar";
            }
        };
        left.prepareAssets();
        left.setColor(ColorManagerMaster.green);

        right = new ImageEntity(){
            @Override
            public void setAssetAddress() {
                setAssetManager(AssetManagerMaster.game);
                assetPath = "game";
                assetName = "right_title_bar";
            }
        };


        right.prepareAssets();
        right.setColor(ColorManagerMaster.green);
        center = new ImageEntity(){
            @Override
            public void setAssetAddress() {
                setAssetManager(AssetManagerMaster.game);
                assetPath = "game";
                assetName = "token";
            }
        };
        center.prepareAssets();



        label = new LabelEntity("Beginer", FontManagerMaster.nexaStyle);

        Stack stack = new Stack();
        Table table = new Table();
        table.add(label).expand().left().pad(10);
        table.setFillParent(true);
        table.setBackground(ColorManagerMaster.greenBg);
//        stack.add(table);
//        stack.add(center);
//        add(left);
//        add(stack).expand();
//        add(center).center();
        add(table);
//        add(right);

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        left.resize(width, height);
        right.resize(width, height);
        label.resize(width, height);
        center.resize(width, height);

    }
}
