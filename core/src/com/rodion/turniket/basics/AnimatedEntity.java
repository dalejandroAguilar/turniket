package com.rodion.turniket.basics;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.rodion.turniket.utilities.ScreenScale;

public class AnimatedEntity extends Image {
    private TextureRegionDrawable[][] textures;
    private TextureRegionDrawable[] frames;
    private String[] assetNames;
    private AssetManager assetManager;
    private Animation animation;
    private float elapsedTime;
    private float frameDuration;
    private boolean isOnPlay;
    private float lastX;
    private float lastY;
    protected String assetPath;

    public AnimatedEntity(float keyDuration) {
        elapsedTime = 0;
        this.frameDuration = keyDuration;
        isOnPlay = false;
    }

    public void prepareAssets() {
        setAssetAddress();
        textures = new TextureRegionDrawable[FactorScale.getN()][assetNames.length];
        frames = new TextureRegionDrawable[assetNames.length];
        TextureAtlas atlas;
        for (FactorScale factorScale : FactorScale.values()) {
            atlas = assetManager.get(assetPath + "/" + factorScale.value + "x/pack.atlas",
                    TextureAtlas.class);
            for (int i = 0; i < assetNames.length; i++)
                textures[factorScale.index][i] =
                        new TextureRegionDrawable(atlas.findRegion(assetNames[i]));
        }
        for(int i =0; i<frames.length; i++)
            frames[i] = textures[FactorScale.Normal.index][i];
        animation = new Animation(frameDuration, frames);
    }

    public void setAssetAddress() {
    }

    public void resize(int width, int height) {
        if(!isOnPlay){
            updatePosition();
            lastX = getX();
            lastY = getY();
        }
        else
            setPosition(lastX,lastY);
        for(int i =0; i<frames.length; i++)
            frames[i] = textures[ScreenScale.getFactorScale().index][i];
        setDrawable((Drawable) animation.getKeyFrame(elapsedTime,false));
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
        if(animation.isAnimationFinished(elapsedTime)){
            elapsedTime =0;
            isOnPlay =false;
            setDrawable((Drawable) animation.getKeyFrame(elapsedTime,false));

        }
        if (isOnPlay) {
            elapsedTime += delta;
            setDrawable((Drawable) animation.getKeyFrame(elapsedTime,false));
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(!isOnPlay){
            updatePosition();
            lastX = getX();
            lastY = getY();
        }
        else
            setPosition(lastX,lastY);
        setDrawable((Drawable) animation.getKeyFrame(elapsedTime,false));
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
        isOnPlay = onPlay;
    }

    public float getDuration(){
        return animation.getAnimationDuration();
    }
}
