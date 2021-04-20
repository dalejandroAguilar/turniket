package com.rodion.turniket.screens.level.stages;

import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.ImageButtonEntity;
import com.rodion.turniket.basics.LabelEntity;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.utilities.AssetManagerMaster;
import com.rodion.turniket.utilities.ColorManagerMaster;
import com.rodion.turniket.utilities.FontManagerMaster;
import com.rodion.turniket.utilities.LevelManagerMaster;

public class BottomMenuLayoutFront extends Layout {
    private ImageButtonEntity previousButton;
    private ImageButtonEntity nextButton;
    private LabelEntity levelStatus;

    public BottomMenuLayoutFront(BasicStage basicStage) {
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
                levelStatus.setText((LevelManagerMaster.getPage() + 1) + "/" + LevelManagerMaster.getNpages());
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
                levelStatus.setText((LevelManagerMaster.getPage() + 1) + "/" + LevelManagerMaster.getNpages());
            }
        };
        nextButton.prepareAssets();

        levelStatus = new LabelEntity((LevelManagerMaster.getPage() + 1) + "/" + LevelManagerMaster.getNpages(), FontManagerMaster.nexaStyle);
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

    public void onPlay() {
    }

    public void onNext() {
    }

    public void onPrevious() {

    }

}
