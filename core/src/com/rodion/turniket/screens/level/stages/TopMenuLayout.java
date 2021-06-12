package com.rodion.turniket.screens.level.stages;

import com.badlogic.gdx.graphics.Color;
import com.rodion.turniket.basics.BackgroundedLabelButton;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.ImageEntity;
import com.rodion.turniket.basics.LabelEntity;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.utilities.AssetManagerMaster;
import com.rodion.turniket.utilities.ColorManagerMaster;
import com.rodion.turniket.utilities.FontManagerMaster;

public class TopMenuLayout extends Layout {
    private BackgroundedLabelButton backButton;
    private BackgroundedLabelButton settingsButton;
    private LabelEntity score;
    private ImageEntity star;

    public TopMenuLayout(BasicStage basicStage) {
        super(basicStage);
        backButton = new BackgroundedLabelButton("Back", FontManagerMaster.helveticaStyle, getParentStage()) {
            @Override
            public void setAssetAddress() {
                setAssetManger(AssetManagerMaster.level);
                setAssetPath("level");
                setAssetName("button_back");
            }
        };
        backButton.setFillParent(false);
        settingsButton = new BackgroundedLabelButton("Settings", FontManagerMaster.helveticaStyle, getParentStage()) {
            @Override
            public void setAssetAddress() {
                setAssetManger(AssetManagerMaster.level);
                setAssetPath("level");
                setAssetName("button_settings");
            }
        };
        settingsButton.setFillParent(false);

        star = new ImageEntity() {
            @Override
            public void setAssetAddress() {
                setAssetManager(AssetManagerMaster.level);
                assetPath = "level";
                assetName = "little_star";
            }
        };
        star.prepareAssets();
        star.setColor(Color.YELLOW);

        score = new LabelEntity("10", FontManagerMaster.nexaStyle);
        score.setColor(Color.WHITE);
        settingsButton.setFillParent(false);
        setFillParent(false);

        add(backButton).left();
        add(star).left();
        add(score).left();
        add().expandX().fillX();
        add(settingsButton).right();
        setBackground(ColorManagerMaster.grayBg);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        star.resize(width, height);
        score.resize(width, height);
        backButton.resize(width, height);
        settingsButton.resize(width, height);
    }
}
