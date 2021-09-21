package com.rodion.turniket.basics;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class AnimatedRasterEntity extends Image {
    private TextureRegionDrawable[] textures;
    protected String assetNames[];
    private AssetManager assetManager;
    private Animation animation;
    private float elapsedTime;
    private float frameDuration;
    private boolean isOnPlay;
    private float lastX;
    private float lastY;
    protected String assetPath;
    private boolean isLooping;

    public AnimatedRasterEntity(float keyDuration) {
        isLooping = false;
        elapsedTime = 0;
        this.frameDuration = keyDuration;
        isOnPlay = false;

    }

    public void prepareAssets() {
        setAssetAddress();

        int nframes = assetNames.length;
        textures = new TextureRegionDrawable[nframes];
        TextureAtlas atlas;
        atlas = assetManager.get(assetPath + "/raster/pack.atlas", TextureAtlas.class);
        for (int i = 0; i < nframes; i++)
            textures[i] = new TextureRegionDrawable(atlas.findRegion(assetNames[i]));
        animation = new Animation(frameDuration, textures);
    }

    public void setAssetAddress() {
    }

    public void setAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(!isLooping) {
            if (animation.isAnimationFinished(elapsedTime)) {
                elapsedTime = 0;
                onFinish();
            }
            if (isOnPlay)
                elapsedTime += delta;
            setDrawable((Drawable) animation.getKeyFrame(elapsedTime, false));
        }
        else {
            elapsedTime += delta;
            setDrawable((Drawable) animation.getKeyFrame(elapsedTime, true));
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setDrawable((Drawable) animation.getKeyFrame(elapsedTime, true));
        super.draw(batch, parentAlpha);
    }

    public void setOnPlay(boolean onPlay) {
        isOnPlay = onPlay;
    }

    public void setLoop(boolean isLooping){
            this.isLooping = isLooping;
        if(isLooping) {
            animation.setPlayMode(Animation.PlayMode.LOOP);
        }
        else {
            animation.setPlayMode(Animation.PlayMode.NORMAL);
        }

    }

    public void onFinish(){

    }

}
