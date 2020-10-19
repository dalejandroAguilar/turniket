package com.rodion.turniket.utilities;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class AssetManagerMaster {
    public final static AssetManager game = new AssetManager();

    public static void loadGame() {
        load(game,"game");
        game.finishLoading();
    }

    private static void load(AssetManager assetManager, String path) {
        assetManager.load(path + "/0.5x/pack.atlas", TextureAtlas.class);
        assetManager.load(path + "/0.75x/pack.atlas", TextureAtlas.class);
        assetManager.load(path + "/1x/pack.atlas", TextureAtlas.class);
        assetManager.load(path + "/1.5x/pack.atlas", TextureAtlas.class);
        assetManager.load(path + "/2x/pack.atlas", TextureAtlas.class);
        assetManager.load(path + "/3x/pack.atlas", TextureAtlas.class);
    }

    public static void dispose(){
        game.dispose();
    }
}