package com.rodion.turniket.screens.game.stages.youWinStage;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.rodion.turniket.basics.BackgroundedLayout;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.LabelEntity;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.utilities.AssetManagerMaster;
import com.rodion.turniket.utilities.ColorManagerMaster;
import com.rodion.turniket.utilities.FontManagerMaster;

public class YouWinLayout extends Layout {
    private LabelEntity label;
    private BackgroundedLayout popUpLayout;
    private BottomMenuLayout bottomMenu;


    public YouWinLayout(BasicStage basicStage) {
        super(basicStage);
        bottomMenu = new BottomMenuLayout(basicStage);
        popUpLayout = new BackgroundedLayout (basicStage){
             @Override
             public void setAssetAddress() {
                setAssetManger(AssetManagerMaster.game);
                setAssetPath("game");
                setAssetName("popup_frame");
            }
        };
        popUpLayout.prepareAssets();
        Color temp = popUpLayout.getColor();
        temp.a = 0;
        popUpLayout.setColor(temp);
        popUpLayout.setFillParent(false);
        label = new LabelEntity("Congrats!", FontManagerMaster.nexaStyle);
        popUpLayout.add(label);
        add(popUpLayout).expand().row();
        bottomMenu.setVisible(false);
        add(bottomMenu).expand().fillX().bottom();
    }

    public void showUp(){
        popUpLayout.addAction(Actions.fadeIn(0.5f));
        bottomMenu.setVisible(true);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        label.resize(width, height);
        popUpLayout.resize(width, height);
        label.resize(width, height);
        bottomMenu.resize(width, height);
    }
}
