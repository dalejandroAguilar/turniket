package com.rodion.turniket.screens.level.stages;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.ImageButtonEntity;
import com.rodion.turniket.basics.LabelEntity;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.utilities.AssetManagerMaster;
import com.rodion.turniket.utilities.ColorManagerMaster;
import com.rodion.turniket.utilities.FontManagerMaster;

public class DifficultyMenuLayoutBack extends Layout {

    private ImageButtonEntity previousButton;
    private ImageButtonEntity nextButton;
    private LabelEntity level;

    public DifficultyMenuLayoutBack(BasicStage basicStage) {
        super(basicStage);
        setFillParent(false);
//        setDebug(true);
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
        };
        nextButton.prepareAssets();

        level = new LabelEntity("Begginer", FontManagerMaster.nexaStyle);
        Table levelFrame = new Table();
        levelFrame.add(level);
        levelFrame.setBackground(ColorManagerMaster.greenBg);
        add(previousButton).left().padBottom(10).padTop(10);
        add(levelFrame).expandX().fillX();
        add(nextButton).right().padBottom(10).padTop(10);
        previousButton.setVisible(false);
        nextButton.setVisible(false);
        setBackground(ColorManagerMaster.grayBg);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        previousButton.resize(width, height);
        level.resize(width, height);
        nextButton.resize(width, height);
    }

    public void onNext() {

    }

    public void onPrevious() {

    }
}