package com.rodion.turniket.screens.title;

import com.rodion.turniket.basics.BackgroundedLabelButton;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.ImageEntity;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.utilities.AssetManagerMaster;
import com.rodion.turniket.utilities.FontManagerMaster;
import com.rodion.turniket.utilities.SoundManagerMaster;

import java.io.FileNotFoundException;

public class TitleLayout extends Layout {
    private ImageEntity title;
    private BackgroundedLabelButton playButton;
    private BackgroundedLabelButton settingsButton;

    public TitleLayout(BasicStage basicStage) {
        super(basicStage);
        setFillParent(true);
        title = new ImageEntity() {
            @Override
            public void setAssetAddress() {
                setAssetManager(AssetManagerMaster.title);
                assetPath = "title";
                assetName = "gametitle";
            }
        };
        title.prepareAssets();

        playButton = new BackgroundedLabelButton("Play", FontManagerMaster.nexaStyle, getParentStage()) {
            @Override
            public void setAssetAddress() {
                setAssetManger(AssetManagerMaster.title);
                setAssetPath("title");
                setAssetName("button_label_frame");
            }

            @Override
            public void onDown() {
                super.onDown();
                SoundManagerMaster.play("click");
            }

            @Override
            public void onAction() throws FileNotFoundException {
                super.onAction();
                onPlay();
            }
        };
        playButton.prepareAssets();

        settingsButton = new BackgroundedLabelButton("Settings", FontManagerMaster.nexaStyle, getParentStage()) {
            @Override
            public void setAssetAddress() {
                setAssetManger(AssetManagerMaster.title);
                setAssetPath("title");
                setAssetName("button_label_frame");
            }

            @Override
            public void onDown() {
                super.onDown();
                SoundManagerMaster.play("click");
            }

            @Override
            public void onAction() throws FileNotFoundException {
                super.onAction();
                onSettings();
            }
        };
        settingsButton.prepareAssets();
        add(title).padTop(50).expand().top().row();
        add(playButton).expand().row();
        add(settingsButton).expand();
    }


    public void onPlay() {

    }

    public void onSettings() {

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        title.resize(width, height);
        playButton.resize(width, height);
        settingsButton.resize(width, height);
    }
}
