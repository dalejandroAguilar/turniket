package com.rodion.turniket.screens.level.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.rodion.turniket.basics.ImageEntity;
import com.rodion.turniket.kernel.Blade;
import com.rodion.turniket.utilities.AssetManagerMaster;
import com.rodion.turniket.utilities.ScreenScale;

public class BladeEntity extends ImageEntity {
    private float axisX;
    private float axisY;
    private Blade blade;

    public BladeEntity(Blade blade) {
        super();
        this.blade = blade;
        prepareAssets();
        setAxisPosition(5.3f, 29.4189659725f);
        setRotation(blade.getDirection().getAngle());

    }

    @Override
    public void setAssetAddress() {
        setAssetManager(AssetManagerMaster.level);
        assetPath = "level";
        assetName = "blade";
    }


    public Blade getBlade() {
        return blade;
    }

    @Override
    public void act(float delta) {
        setOriginX(axisX * ScreenScale.getFactorScale().getScale());
        setOriginY(axisY * ScreenScale.getFactorScale().getScale());
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setOriginX(axisX * ScreenScale.getFactorScale().getScale());
        setOriginY(axisY * ScreenScale.getFactorScale().getScale());
        super.draw(batch, parentAlpha);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    public void setAxisPosition(float x, float y) {
        axisX = x;
        axisY = y;
    }
}
