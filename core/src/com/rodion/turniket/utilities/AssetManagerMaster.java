package com.rodion.turniket.utilities;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class AssetManagerMaster {
    public final static AssetManager loading = new AssetManager();
    public final static AssetManager title = new AssetManager();
    public final static AssetManager game = new AssetManager();
    public final static AssetManager level = new AssetManager();
    public final static AssetManager settings = new AssetManager();

    public static void loadLoading(){
        loading.load("loading/raster/pack.atlas", TextureAtlas.class);
        loading.finishLoading();
    }

    public static void loadTitle(){
        load(title,"title");
        title.finishLoading();
    }

    public static void loadGame() {
        load(game,"game");
        game.finishLoading();
    }

    public static void loadLevels(){
        load(level,"level");
        level.finishLoading();
    }

    public static void loadSettings(){
        load(settings, "settings");
        settings.finishLoading();
    }

    private static void load(AssetManager assetManager, String path) {
        assetManager.load(path + "/0.5x/pack.atlas", TextureAtlas.class);
        assetManager.load(path + "/0.75x/pack.atlas", TextureAtlas.class);
        assetManager.load(path + "/1x/pack.atlas", TextureAtlas.class);
        assetManager.load(path + "/1.5x/pack.atlas", TextureAtlas.class);
        assetManager.load(path + "/2x/pack.atlas", TextureAtlas.class);
        assetManager.load(path + "/3x/pack.atlas", TextureAtlas.class);
        assetManager.load(path + "/raster/pack.atlas", TextureAtlas.class);
    }

    public static void dispose(){
        loading.dispose();
        game.dispose();
        level.dispose();
        title.dispose();
        settings.dispose();
    }
}