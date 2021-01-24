package com.rodion.turniket.screens.level.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.rodion.turniket.basics.BackgroundedLayout;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.ImageEntity;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.utilities.AssetManagerMaster;
import com.rodion.turniket.utilities.ColorManagerMaster;

import java.io.File;

public class LevelEntity extends Layout {
    private BoardEntity board;
    private ImageEntity frame;
    private ImageEntity[] stars;
    private BackgroundedLayout numberFrame;

    public LevelEntity(File file, BasicStage basicStage) {
        super(basicStage);
        setFillParent(false);
        Stack stack = new Stack();
        Table starTable = new Table();
        starTable.setFillParent(false);
        Table numberTable = new Table();
        numberTable.setFillParent(false);


        stars = new ImageEntity[3];

        for (int i = 0; i < 3; i++) {
            stars[i] = new ImageEntity() {
                @Override
                public void setAssetAddress() {
                    setAssetManager(AssetManagerMaster.level);
                    assetPath = "level";
                    assetName = "little_star";
                }
            };
            stars[i].prepareAssets();
            starTable.add(stars[i]).top().expandY();
            starTable.add();
        }
//        table.setDebug(true);

        board = new BoardEntity(file, basicStage);
        frame = new ImageEntity() {
            @Override
            public void setAssetAddress() {
                setAssetManager(AssetManagerMaster.level);
                assetPath = "level";
                assetName = "level_frame";
            }
        };
        frame.prepareAssets();
        frame.setColor(ColorManagerMaster.green);

        numberFrame = new BackgroundedLayout(basicStage) {
            @Override
            public void setAssetAddress() {
                setAssetManger(AssetManagerMaster.level);
                setAssetPath("level");
                setAssetName("number_level_frame");
            }
        };
        numberFrame.prepareAssets();
        numberTable.setDebug(true);
        numberTable.add(numberFrame).top().left().expand();

        stack.add(frame);
        stack.add(starTable);
        stack.add(board);
        add(stack);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        board.resize(width, height);
        frame.resize(width, height);
        for(ImageEntity star : stars)
            star.resize(width, height);
        numberFrame.resize(width, height);
    }
}
