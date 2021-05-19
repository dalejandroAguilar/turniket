package com.rodion.turniket.screens.game.stages.youWinStage;

import com.rodion.turniket.basics.BackgraundedLabelButton;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.ImageButtonEntity;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.utilities.AssetManagerMaster;
import com.rodion.turniket.utilities.ColorManagerMaster;
import com.rodion.turniket.utilities.FontManagerMaster;

import java.io.FileNotFoundException;

public class BottomMenuLayout extends Layout {
    private BackgraundedLabelButton continueButton;
    private BackgraundedLabelButton saveSolutionButton;

    public BottomMenuLayout(BasicStage basicStage) {
        super(basicStage);
        setFillParent(false);

        continueButton = new BackgraundedLabelButton("Continue",
                FontManagerMaster.nexaStyle,basicStage){
            @Override
            public void setAssetAddress() {
                setAssetManger(AssetManagerMaster.game);
                setAssetPath("game");
                setAssetName("button_label_frame");
            }

            @Override
            public void onAction() {
                onContinue();
            }
        };
        continueButton.setFillParent(false);

        saveSolutionButton = new BackgraundedLabelButton("Save Solution",
                FontManagerMaster.nexaStyle,basicStage){
            @Override
            public void setAssetAddress() {
                setAssetManger(AssetManagerMaster.game);
                setAssetPath("game");
                setAssetName("button_label_frame");
            }

            @Override
            public void onAction() throws FileNotFoundException {
                onSaveSolution();
            }
        };
        saveSolutionButton.setFillParent(false);


        add(continueButton).padBottom(10).padTop(10).expand();
        add(saveSolutionButton).padBottom(10).padTop(10).expand();

        setBackground(ColorManagerMaster.grayBg);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        continueButton.resize(width, height);
        saveSolutionButton.resize(width, height);
    }

    public void onContinue(){

    }

    public void onSaveSolution() throws FileNotFoundException {

    }

}
