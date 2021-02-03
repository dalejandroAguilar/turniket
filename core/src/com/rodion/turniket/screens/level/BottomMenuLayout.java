package com.rodion.turniket.screens.level;

import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.ImageButtonEntity;
import com.rodion.turniket.basics.LabelEntity;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.utilities.AssetManagerMaster;
import com.rodion.turniket.utilities.ColorManagerMaster;
import com.rodion.turniket.utilities.FontManagerMaster;

public class BottomMenuLayout extends Layout {
    private ImageButtonEntity previousButton;
    private ImageButtonEntity nextButton;
    private LabelEntity levelStatus;

    public BottomMenuLayout(BasicStage basicStage) {
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

        levelStatus = new LabelEntity("10/11", FontManagerMaster.nexaStyle);



        add(previousButton).left().padBottom(10).padTop(10).expandX();
        add(levelStatus).center().expandX();
        add(nextButton).right().padBottom(10).padTop(10).expandX();

        setBackground(ColorManagerMaster.grayBg);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        previousButton.resize(width, height);
        nextButton.resize(width, height);
    }

    public void onContinue() {
    }

    public void onPlay(){
    }

    public void onNext(){

    }

    public void onPrevious(){

    }

}
