package com.rodion.turniket.screens.game.entities;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.rodion.turniket.basics.ImageEntity;
import com.rodion.turniket.utilities.AssetManagerMaster;

public class BurnerEntity extends ImageEntity {
    private int i;
    private int j;

    public BurnerEntity(int i, int j) {
        prepareAssets();
        this.i = i;
        this.j = j;
        setTouchable(Touchable.enabled);
        addListener(new ClickListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (pointer == 0 && button == 0)
                    return BurnerEntity.this.touchDown();
                else
                    return false;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (pointer == 0 && button == 0)
                    BurnerEntity.this.touchUp();
            }
        });
    }

    @Override
    public void setAssetAddress() {
        super.setAssetAddress();
        setAssetManager(AssetManagerMaster.game);
        assetPath = "game";
        assetName = "burner";
    }

    public void touchUp() {
    }

    public boolean touchDown() {
        return true;
    }


    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }
}
