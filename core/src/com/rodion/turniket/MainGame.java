package com.rodion.turniket;

import com.badlogic.gdx.ApplicationAdapter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rodion.turniket.screens.game.GameScreen;
import com.rodion.turniket.screens.level.LevelScreen;
import com.rodion.turniket.utilities.AssetManagerMaster;
import com.rodion.turniket.utilities.ColorManagerMaster;
import com.rodion.turniket.utilities.FontManagerMaster;
import com.rodion.turniket.utilities.LevelManagerMaster;
import com.rodion.turniket.utilities.ScreenScale;
import com.rodion.turniket.utilities.ScreenShotManager;

public class MainGame extends Game {
    public GameScreen gameScreen;
    public LevelScreen levelScreen;

    @Override
    public void create() {
        System.out.println("init");
        AssetManagerMaster.loadGame();
        AssetManagerMaster.loadLevels();
        ColorManagerMaster.load();
        FontManagerMaster.loadFonts();
        LevelManagerMaster.init();

        ScreenScale.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        gameScreen = new GameScreen(this){
            @Override
            public void onGoToLevelScreen() {
                super.onGoToLevelScreen();
                levelScreen.init();
                setScreen(levelScreen);
//                levelScreen.onEnter();
            }

        };
        levelScreen = new LevelScreen(this) {
            @Override
            public void onGoToGameScreen() {
                super.onGoToGameScreen();
                System.out.println("onGoToGameScreen ext");
//                ScreenShotManager.takeSS();
                gameScreen.init();
                setScreen(gameScreen);
                gameScreen.onEnter();
            }
        };
        setScreen(levelScreen);
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetManagerMaster.dispose();
        FontManagerMaster.dispose();
    }
}
