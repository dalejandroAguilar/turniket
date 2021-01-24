package com.rodion.turniket.screens.game.layouts;

import com.rodion.turniket.basics.AnimatedRasterEntity;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.utilities.AssetManagerMaster;

public class ConfettiLayout extends Layout {
    private AnimatedRasterEntity confettiEntity;

    public ConfettiLayout(BasicStage basicStage) {
        super(basicStage);
        setFillParent(true);
        confettiEntity = new AnimatedRasterEntity(0.06f) {
            @Override
            public void setAssetAddress() {
                setAssetManager(AssetManagerMaster.game);
                assetPath = "game";
                assetNames = new String[24];
                for (int i = 0; i < 10; i++) {
                    assetNames[i] = "confetti0" + i;
                }
                for (int i = 10; i < 24; i++) {
                    assetNames[i] = "confetti" + i;
                }
            }

            @Override
            public void onFinish() {
                setOnPlay(false);
                ConfettiLayout.this.onFinish();
            }
        };
        confettiEntity.prepareAssets();
        confettiEntity.setOnPlay(false);
        add(confettiEntity);
    }

    public void onThrow() {
        confettiEntity.setOnPlay(true);
    }

    public void onFinish() {

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

}
