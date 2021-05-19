package com.rodion.turniket.screens.game.stages.solverStage;

import com.badlogic.gdx.utils.Align;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.ImageButtonEntity;
import com.rodion.turniket.basics.LabelEntity;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.utilities.AssetManagerMaster;
import com.rodion.turniket.utilities.ColorManagerMaster;
import com.rodion.turniket.utilities.FontManagerMaster;

public class BottomMenuLayout extends Layout {
    private ImageButtonEntity backButton;
    private ImageButtonEntity undoButton;
    private ImageButtonEntity redoButton;
    private LabelEntity stepLabel;

    public BottomMenuLayout(BasicStage basicStage) {
        super(basicStage);
        setFillParent(false);
        stepLabel = new LabelEntity("1/2", FontManagerMaster.nexaStyle);
        stepLabel.setAlignment(Align.center);
        backButton = new ImageButtonEntity() {
            @Override
            public void setAssetAddress() {
                setAssetManager(AssetManagerMaster.game);
                assetPath = "game";
                assetName = "button_previous";
            }

            @Override
            public void onAction() {
                super.onAction();
                onBack();
            }
        };
        backButton.prepareAssets();

        undoButton = new ImageButtonEntity() {
            @Override
            public void setAssetAddress() {
                setAssetManager(AssetManagerMaster.game);
                assetPath = "game";
                assetName = "button_undo";
            }

            @Override
            public void onAction() {
                super.onAction();
                onPrevious();
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
                super.onAction();
                onNext();
            }
        };
        redoButton.prepareAssets();

        add(backButton).left().padBottom(10).padTop(10).expandX();
        add(undoButton).padBottom(10).padTop(10).expandX();
        add(stepLabel).padBottom(10).padTop(10).expandX();
        add(redoButton).padBottom(10).padTop(10).expandX();

        setBackground(ColorManagerMaster.greenBg);

//        setDebug(true);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        backButton.resize(width, height);
        undoButton.resize(width, height);
        redoButton.resize(width, height);
        stepLabel.resize(width, height);
    }


    public void onNext(){
    }

    public void onPrevious(){

    }

    public void onBack(){

    }
}
