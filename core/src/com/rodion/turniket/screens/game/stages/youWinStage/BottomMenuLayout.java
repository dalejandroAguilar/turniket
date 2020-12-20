package com.rodion.turniket.screens.game.stages.youWinStage;

import com.rodion.turniket.basics.BackgraundedLabelButton;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.ImageButtonEntity;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.utilities.AssetManagerMaster;
import com.rodion.turniket.utilities.ColorManagerMaster;
import com.rodion.turniket.utilities.FontManagerMaster;

public class BottomMenuLayout extends Layout {
    private ImageButtonEntity redoButton;
    private ImageButtonEntity restartButton;
    private ImageButtonEntity hintButton;
    private ImageButtonEntity undoButton;
    private BackgraundedLabelButton continueButton;

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

            @Override
            public void onAction() {
                onRestart();
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

            @Override
            public void onAction() {
                onUndo();
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

            @Override
            public void onAction() {
                onRedo();
            }
        };

        redoButton.prepareAssets();

        continueButton = new BackgraundedLabelButton("Continue", FontManagerMaster.nexaStyle,basicStage){
            @Override
            public void setAssetAddress() {
                setAssetManger(AssetManagerMaster.game);
                setAssetPath("game");
                setAssetName("button_label_frame");
            }
        };
        continueButton.setFillParent(false);

        add(continueButton).padBottom(10).padTop(10).expand();
        setBackground(ColorManagerMaster.grayBg);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        hintButton.resize(width, height);
        restartButton.resize(width, height);
        undoButton.resize(width, height);
        redoButton.resize(width, height);
        continueButton.resize(width, height);
    }

    public void onUndo(){

    }

    public void onRedo(){
    }


    public void onRestart(){
    }
}