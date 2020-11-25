package com.rodion.turniket.screens.game.layouts;

import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.ImageButtonEntity;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.utilities.AssetManagerMaster;
import com.rodion.turniket.utilities.ColorManagerMaster;

public class BottomMenuLayout extends Layout {
    private ImageButtonEntity redoButton;
    private ImageButtonEntity restartButton;
    private ImageButtonEntity hintButton;
    private ImageButtonEntity undoButton;
    public BottomMenuLayout(BasicStage basicStage) {
        super(basicStage);
        setFillParent(false);
        hintButton = new ImageButtonEntity(){
            @Override
            public void setAssetAddress() {
                setAssetManager(AssetManagerMaster.game);
                assetPath = "game";
                assetName = "button_help";
            }
        };
        hintButton.prepareAssets();

        restartButton = new ImageButtonEntity(){
            @Override
            public void setAssetAddress() {
                setAssetManager(AssetManagerMaster.game);
                assetPath = "game";
                assetName = "button_restart";
            }
        };
        restartButton.prepareAssets();

        undoButton = new ImageButtonEntity(){
            @Override
            public void setAssetAddress() {
                setAssetManager(AssetManagerMaster.game);
                assetPath = "game";
                assetName = "button_undo";
            }
        };
        undoButton.prepareAssets();

        redoButton = new ImageButtonEntity(){
            @Override
            public void setAssetAddress() {
                setAssetManager(AssetManagerMaster.game);
                assetPath = "game";
                assetName = "button_redo";
            }
        };
        redoButton.prepareAssets();

        add().expandX();
        add(hintButton).left().expandX();
        add(restartButton).expandX();
        add(undoButton).expandX();
        add(redoButton).expandX();
        add().expandX();
        setBackground(ColorManagerMaster.grayBg);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        hintButton.resize(width, height);
        restartButton.resize(width, height);
        undoButton.resize(width, height);
        redoButton.resize(width, height);
    }
}
