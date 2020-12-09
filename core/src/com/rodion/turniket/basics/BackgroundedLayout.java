package com.rodion.turniket.basics;

import com.badlogic.gdx.assets.AssetManager;
import com.rodion.turniket.utilities.ScreenScale;

public class BackgroundedLayout extends Layout {
    ImageEntity image;

    public BackgroundedLayout(BasicStage basicStage) {
        super(basicStage);
        image = new ImageEntity() {
            @Override
            public void setAssetAddress() {
                BackgroundedLayout.this.setAssetAddress();
            }
        };
        prepareAssets();
    }

    public void setAssetAddress() {
    }

    public void prepareAssets(){
        image.prepareAssets();
    }

    public void setAssetManger(AssetManager assetManger){
        image.setAssetManager(assetManger);
    }

    public void setAssetPath(String assetPath){
        image.assetPath = assetPath;
    }
    public void setAssetName(String assetName){
        image.assetName = assetName;
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        setBackground(image.textures[ScreenScale.getFactorScale().index]);
    }
}
