package com.rodion.turniket.screens.level.stages;


import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.ImageButtonEntity;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.utilities.AssetManagerMaster;

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

        add(previousButton).left().padBottom(10).padTop(10);
        add().expand();
        add(nextButton).right().padBottom(10).padTop(10);
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