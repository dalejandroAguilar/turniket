package com.rodion.turniket.screens.game.stages.gameStage.layouts;

import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.ImageButtonEntity;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.utilities.AssetManagerMaster;
import com.rodion.turniket.utilities.ColorManagerMaster;
import com.rodion.turniket.utilities.SoundManagerMaster;

public class BottomMenuLayout extends Layout {
    private ImageButtonEntity redoButton;
    private ImageButtonEntity restartButton;
    private ImageButtonEntity hintButton;
    private ImageButtonEntity undoButton;

    public BottomMenuLayout(BasicStage basicStage) {
        super(basicStage);
        setFillParent(false);
        hintButton = new ImageButtonEntity() {
            @Override
            public void setAssetAddress() {
                setAssetManager(AssetManagerMaster.game);
                assetPath = "game";
                assetName = "button_help";
            }

            @Override
            public void onAction() {
                super.onAction();
                onHint();
            }

            @Override
            public void onDown() {
                super.onDown();
                SoundManagerMaster.play("click");
            }
        };
        hintButton.prepareAssets();

        restartButton = new ImageButtonEntity() {
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

            @Override
            public void onDown() {
                super.onDown();
                SoundManagerMaster.play("click");
            }
        };
        restartButton.prepareAssets();

        undoButton = new ImageButtonEntity() {
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

            @Override
            public void onDown() {
                super.onDown();
                SoundManagerMaster.play("click");
            }
        };
        undoButton.prepareAssets();

        redoButton = new ImageButtonEntity() {
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

            @Override
            public void onDown() {
                super.onDown();
                SoundManagerMaster.play("click");
            }
        };
        redoButton.prepareAssets();

        add().expandX();
        add(hintButton).left().padBottom(10).padTop(10).expandX();
        add(restartButton).padBottom(10).padTop(10).expandX();
        add(undoButton).padBottom(10).padTop(10).expandX();
        add(redoButton).padBottom(10).padTop(10).expandX();
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

    public void onUndo() {
    }

    public void onRedo() {
    }

    public void onRestart() {
    }

    public void onHint() {

    }
}
