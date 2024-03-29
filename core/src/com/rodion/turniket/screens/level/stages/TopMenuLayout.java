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
import com.rodion.turniket.utilities.LevelManagerMaster;
import com.rodion.turniket.utilities.SoundManagerMaster;

import java.io.FileNotFoundException;

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

            @Override
            public void onDown() {
                super.onDown();
                SoundManagerMaster.play("click");
            }



            @Override
            public void onAction() throws FileNotFoundException {
                super.onAction();
                TopMenuLayout.this.onBack();
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

             @Override
            public void onDown() {
                super.onDown();
                SoundManagerMaster.play("click");
            }


            @Override
            public void onAction() throws FileNotFoundException {
                super.onAction();
                TopMenuLayout.this.onSettings();
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

        score = new LabelEntity(LevelManagerMaster.getNstars() +"", FontManagerMaster.nexaStyle);
        score.setColor(Color.WHITE);
        settingsButton.setFillParent(false);
        setFillParent(false);

        add(backButton).left();
        add(star).left().padLeft(20);
        add(score).left().padLeft(20);
        add().expandX().fillX();
        add(settingsButton).right();
        setBackground(ColorManagerMaster.grayBg);
    }

    public void onBack() {

    }

    public void onSettings() {

    }

    public void update(){
        score.setText(LevelManagerMaster.getNstars()+"");
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
