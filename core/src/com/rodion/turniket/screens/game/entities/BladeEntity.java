package com.rodion.turniket.screens.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable;
import com.rodion.turniket.basics.ImageEntity;
import com.rodion.turniket.kernel.Blade;
import com.rodion.turniket.kernel.Node;
import com.rodion.turniket.kernel.constants.Spin;
import com.rodion.turniket.screens.game.layouts.BoardLayout;
import com.rodion.turniket.utilities.AssetManagerMaster;
import com.rodion.turniket.utilities.ScreenScale;

public class BladeEntity extends ImageEntity {
    private Blade blade;
    float axisx = 17.46713562f;
    float axisy = 117.67586389f;

    public BladeEntity(Blade blade) {
        this.blade = blade;
        setRotation(blade.getDirection().getAngle());
        blade.addListener(new Blade.Listener() {
            @Override
            public void onRotate(Spin spin) {
                System.out.println("hola");
                setRotation(getBlade().getDirection().getAngle());
            }
        });
    }

    @Override
    public void setAssetAddress() {
        super.setAssetAddress();
        setAssetManager(AssetManagerMaster.game);
        assetPath = "game";
        assetName = "blade";
    }

    public Blade getBlade() {
        return blade;
    }

    public void onRotates(Spin spin) {
        System.out.println("rotating");
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setOriginX(axisx * ScreenScale.getFactorScale().getScale());
        setOriginY(axisy * ScreenScale.getFactorScale().getScale());
        super.draw(batch, parentAlpha);
    }

}
