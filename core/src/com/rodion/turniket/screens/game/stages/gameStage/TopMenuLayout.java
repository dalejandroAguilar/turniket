package com.rodion.turniket.screens.game.stages.gameStage;

import com.rodion.turniket.basics.BackgraundedLabelButton;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.utilities.AssetManagerMaster;
import com.rodion.turniket.utilities.ColorManagerMaster;
import com.rodion.turniket.utilities.FontManagerMaster;

public class TopMenuLayout extends Layout {
    private BackgraundedLabelButton backButton;
    private BackgraundedLabelButton settingsButton;
    public TopMenuLayout(BasicStage basicStage) {
        super(basicStage);
        backButton = new BackgraundedLabelButton("Back", FontManagerMaster.helveticaStyle,getParentStage()){
            @Override
            public void setAssetAddress() {
                setAssetManger(AssetManagerMaster.game);
                setAssetPath("game");
                setAssetName("button_back");
            }
        };
        backButton.setFillParent(false);
        settingsButton = new BackgraundedLabelButton("Settings", FontManagerMaster.helveticaStyle,getParentStage()){
            @Override
            public void setAssetAddress() {
                setAssetManger(AssetManagerMaster.game);
                setAssetPath("game");
                setAssetName("button_settings");
            }
        };
        settingsButton.setFillParent(false);

        setFillParent(false);
        add(backButton).expandX().left();
        add(settingsButton).expandX().right();
        setBackground(ColorManagerMaster.grayBg);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        backButton.resize(width, height);
        settingsButton.resize(width, height);
    }
}
