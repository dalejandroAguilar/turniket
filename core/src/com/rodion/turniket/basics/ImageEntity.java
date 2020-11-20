package com.rodion.turniket.basics;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.rodion.turniket.utilities.ScreenScale;

public class ImageEntity extends Image {
    protected TextureRegionDrawable[] textures;
    protected String assetPath;
    protected String assetName;
    protected AssetManager assetManager;

    public ImageEntity() {

    }

    public void prepareAssets() {
        setAssetAddress();
        textures = new TextureRegionDrawable[FactorScale.getN()];
        TextureAtlas atlas;
        for (FactorScale factorScale : FactorScale.values()) {
            atlas = assetManager.get(assetPath + "/" + factorScale.value + "x/pack.atlas",
                    TextureAtlas.class);
            textures[factorScale.index] = new TextureRegionDrawable(atlas.findRegion(assetName));
        }
    }

    public void setAssetAddress() {

    }

    public void resize(int width, int height) {
        updatePosition();
        setDrawable(textures[ScreenScale.getFactorScale().index]);
        setSize(getDrawable().getMinWidth(), getDrawable().getMinHeight());
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public void setAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public Vector2 getAbsPosition() {
        return localToStageCoordinates(new Vector2(0, 0));
    }

    public Vector2 getAbsPosition(int align) {
        return new Vector2(getX(align), getY(align));
    }

    public float getAbsX() {
        return getAbsPosition().x;
    }

    public float getAbsX(int align) {
        return getAbsPosition(align).x;
    }

    public float getAbsY() {
        return getAbsPosition().y;
    }

    public float getAbsY(int align) {
        return getAbsPosition(align).y;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        updatePosition();
        super.draw(batch, parentAlpha);
    }

    public void updatePosition(){

    }



}