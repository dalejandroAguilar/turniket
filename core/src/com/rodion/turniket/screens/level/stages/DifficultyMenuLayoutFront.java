package com.rodion.turniket.screens.level.stages;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.ImageButtonEntity;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.utilities.AssetManagerMaster;
import com.rodion.turniket.utilities.ColorManagerMaster;
import com.rodion.turniket.utilities.SoundManagerMaster;

public class DifficultyMenuLayoutFront extends Layout {

    private ImageButtonEntity previousButton;
    private ImageButtonEntity nextButton;

    public DifficultyMenuLayoutFront(BasicStage basicStage) {
        super(basicStage);
        setFillParent(false);
        previousButton = new ImageButtonEntity() {
            @Override
            public void setAssetAddress() {
                setAssetManager(AssetManagerMaster.level);
                assetPath = "level";
                assetName = "button_previous";
            }

            @Override
            public void onAction() {
                super.onAction();
                onPrevious();
            }

             @Override
            public void onDown() {
                super.onDown();
                SoundManagerMaster.play("click");
            }

        };
        previousButton.prepareAssets();

        nextButton = new ImageButtonEntity() {
            @Override
            public void setAssetAddress() {
                setAssetManager(AssetManagerMaster.level);
                assetPath = "level";
                assetName = "button_next";
            }

            @Override
            public void onAction() {
                super.onAction();
                onNext();
            }

             @Override
            public void onDown() {
                super.onDown();
                SoundManagerMaster.play("click");
            }

        };
        nextButton.prepareAssets();

        Table table1 = new Table();
        Table table2 = new Table();
        table1.setBackground(ColorManagerMaster.grayBg);
        table2.setBackground(ColorManagerMaster.grayBg);

        table1.add(previousButton);
        table2.add(nextButton);

        add(table1).left().padBottom(10).padTop(10);
        add().expand();
        add(table2).right().padBottom(10).padTop(10);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        previousButton.resize(width, height);
        nextButton.resize(width, height);
    }

    public void onNext() {
    }

    public void onPrevious() {
    }
}