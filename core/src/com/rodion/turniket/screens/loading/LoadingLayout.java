package com.rodion.turniket.screens.loading;

import com.rodion.turniket.basics.AnimatedRasterEntity;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.utilities.AssetManagerMaster;

public class LoadingLayout extends Layout {
    private AnimatedRasterEntity turnstile;
    public LoadingLayout(BasicStage basicStage) {
        super(basicStage);
        turnstile = new AnimatedRasterEntity(0.1f){
            @Override
            public void setAssetAddress() {
                setAssetManager(AssetManagerMaster.loading);
                assetPath = "loading";
                assetNames = new String[9];
                for (int i = 0; i < 9; i++) {
                    assetNames[i] = "turbine0" + i;
                }
            }
            @Override
            public void onFinish() {
                setOnPlay(false);
//                ConfettiLayout.this.onFinish();
            }
        };
        turnstile.prepareAssets();
        turnstile.setOnPlay(true);
        turnstile.setLoop(true);
        add(turnstile);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
