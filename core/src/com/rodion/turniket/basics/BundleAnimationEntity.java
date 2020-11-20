package com.rodion.turniket.basics;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.rodion.turniket.utilities.ScreenScale;

public class BundleAnimationEntity extends Image {
    protected TextureRegionDrawable[][][] textures;
    protected TextureRegionDrawable[][] frames;
    protected String assetPath;
    protected String[][] assetNames;
    protected AssetManager assetManager;
    protected Animation[] animations;
    protected float elapsedTime;
    protected float frameDuration;
    protected boolean isOnPlay;
//    protected float lastX;
//    protected float lastY;
    protected int nAnimations;
    protected int selectAnimation;

    public BundleAnimationEntity(float frameDuration, int nAnimations) {
        elapsedTime = 0;
        selectAnimation = 0;
        this.frameDuration = frameDuration;
        isOnPlay = false;
        this.nAnimations = nAnimations;
    }

    public void prepareAssets() {
        setAssetAddress();

//        int nAnimations;
        int framesLength = assetNames[0].length;
        System.out.println(framesLength);

        textures = new TextureRegionDrawable[nAnimations][FactorScale.getN()][framesLength];
        frames = new TextureRegionDrawable[nAnimations][framesLength];
        animations = new Animation[nAnimations];

        TextureAtlas atlas;
        for (FactorScale factorScale : FactorScale.values()) {
            atlas = assetManager.get(assetPath + "/" + factorScale.value + "x/pack.atlas",
                    TextureAtlas.class);
            for (int i = 0; i < nAnimations; i++) {
                for (int j = 0; j < framesLength; j++)
                    textures[i][factorScale.index][j] =
                            new TextureRegionDrawable(atlas.findRegion(assetNames[i][j]));
            }
        }

        for (int i = 0; i < nAnimations; i++)
            for (int j = 0; j < framesLength; j++)
                frames[i][j] = textures[i][FactorScale.Normal.index][j];

        for (int i = 0; i < nAnimations; i++)
            animations[i] = new Animation(frameDuration, frames[i]);
    }

    public void setAssetAddress() {
    }

    public void resize(int width, int height) {
        updatePosition();

        for (int i = 0; i < nAnimations; i++)
            for (int j = 0; j < frames[0].length; j++)
                frames[i][j] = textures[i][ScreenScale.getFactorScale().index][j];

        setDrawable((Drawable) animations[selectAnimation].getKeyFrame(elapsedTime, false));
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
    public void act(float delta) {
        super.act(delta);
//        updatePosition();
        Animation animation = animations[selectAnimation];
        if (animation.isAnimationFinished(elapsedTime)) {
            elapsedTime = 0;
            isOnPlay = false;
            setDrawable((Drawable) animations[selectAnimation].getKeyFrame(elapsedTime, false));

        }
        if (isOnPlay) {
            elapsedTime += delta;
            setDrawable((Drawable) animations[selectAnimation].getKeyFrame(elapsedTime, false));
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        updatePosition();
        setDrawable((Drawable) animations[selectAnimation].getKeyFrame(elapsedTime, false));
        super.draw(batch, parentAlpha);
    }

    public void updatePosition() {

    }

    public float getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(float elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public void setOnPlay(boolean onPlay) {
//        updatePosition();
        isOnPlay = onPlay;
    }

    public float getDuration() {
        return animations[selectAnimation].getAnimationDuration();
    }

    public int getSelectAnimation() {
        return selectAnimation;
    }

    public void setSelectAnimation(int selectAnimation) {
        this.selectAnimation = selectAnimation;
    }
}
