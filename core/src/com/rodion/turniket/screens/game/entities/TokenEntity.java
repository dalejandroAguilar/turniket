package com.rodion.turniket.screens.game.entities;

import com.rodion.turniket.basics.ImageEntity;
import com.rodion.turniket.kernel.Node;
import com.rodion.turniket.kernel.Token;
import com.rodion.turniket.utilities.AssetManagerMaster;

public class TokenEntity extends ImageEntity {
    public static Node boardldpos = new Node();
    public static float bordersize;
    public static float pad;

    private Token token;
    public TokenEntity() {
        prepareAssets();
    }

    @Override
    public void setAssetAddress() {
        super.setAssetAddress();
        setAssetManager(AssetManagerMaster.game);
        assetPath = "game";
        assetName = "token";
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        setPosition(boardldpos.getX(), boardldpos.getY());
    }
}
