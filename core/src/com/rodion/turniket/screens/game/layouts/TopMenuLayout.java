package com.rodion.turniket.screens.game.layouts;

import com.badlogic.gdx.graphics.Color;
import com.rodion.turniket.basics.BackgraundedLabelButton;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.ImageEntity;
import com.rodion.turniket.basics.LabelEntity;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.utilities.AssetManagerMaster;
import com.rodion.turniket.utilities.ColorManagerMaster;
import com.rodion.turniket.utilities.FontManagerMaster;

public class TopMenuLayout extends Layout {
    private BackgraundedLabelButton backButton;
    private BackgraundedLabelButton settingsButton;
    private LabelEntity score;
    private ImageEntity star;

    public TopMenuLayout(BasicStage basicStage) {
        super(basicStage);
        backButton = new BackgraundedLabelButton("Back", FontManagerMaster.helveticaStyle, getParentStage()) {
            @Override
            public void setAssetAddress() {
                setAssetManger(AssetManagerMaster.game);
                setAssetPath("game");
                setAssetName("button_back");
            }

            @Override
            public void onAction() {
                super.onAction();
                onReturn();
            }
        };
        backButton.setFillParent(false);
        settingsButton = new BackgraundedLabelButton("Settings", FontManagerMaster.helveticaStyle, getParentStage()) {
            @Override
            public void setAssetAddress() {
                setAssetManger(AssetManagerMaster.game);
                setAssetPath("game");
                setAssetName("button_settings");
            }
        };
        star = new ImageEntity() {
            @Override
            public void setAssetAddress() {
                setAssetManager(AssetManagerMaster.game);
                assetPath = "game";
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

    public void onReturn(){

    }
}
