package com.rodion.turniket.basics;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.rodion.turniket.utilities.ScreenScale;

public class ImageEntity extends Image {
    protected TextureRegionDrawable textures[];
    protected String assetPath;
    protected String assetName;
    protected AssetManager assetManager;

    public ImageEntity() {
    }

    public void prepareAssets() {
        setAssetAddress();
        textures = new TextureRegionDrawable[FactorScale.getN()];
        TextureAtlas atlas;
        for (FactorScale factorScale: FactorScale.values()){
            atlas = assetManager.get(assetPath + "/"+factorScale.value+"x/pack.atlas",
                TextureAtlas.class);
            textures[factorScale.index ] = new TextureRegionDrawable(atlas.findRegion(assetName));
        }
    }

    public void setAssetAddress() {

    }

    public void resize(int width, int height) {
        setDrawable(textures[ScreenScale.getFac().index]);
        setSize(getImageWidth(),getImageHeight());
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public void setAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
    }
}