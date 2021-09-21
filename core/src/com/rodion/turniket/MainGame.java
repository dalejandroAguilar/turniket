package com.rodion.turniket;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.rodion.turniket.screens.game.GameScreen;
import com.rodion.turniket.screens.launch.LaunchScreen;
import com.rodion.turniket.screens.level.LevelScreen;
import com.rodion.turniket.screens.loading.LoadingScreen;
import com.rodion.turniket.screens.title.TitleScreen;
import com.rodion.turniket.utilities.AssetManagerMaster;
import com.rodion.turniket.utilities.ColorManagerMaster;
import com.rodion.turniket.utilities.FontManagerMaster;
import com.rodion.turniket.utilities.LevelManagerMaster;
import com.rodion.turniket.utilities.ScreenScale;

public class MainGame extends Game {

    public LaunchScreen launchScreen;
    public LoadingScreen loadingScreen;
    public GameScreen gameScreen;
    public LevelScreen levelScreen;
    public TitleScreen titleScreen;

    @Override
    public void create() {
        System.out.println("init");
        AssetManagerMaster.loadLoading();
        AssetManagerMaster.loadTitle();
        AssetManagerMaster.loadLevels();
        AssetManagerMaster.loadGame();
        ColorManagerMaster.load();
        FontManagerMaster.loadFonts();
        LevelManagerMaster.init();

//
        ScreenScale.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        loadingScreen = new LoadingScreen(this){
            @Override
            public void onExit() {
                super.onExit();
                titleScreen.onEnter();
                setScreen(titleScreen);
            }
        };

        launchScreen = new LaunchScreen(this){
            @Override
            public void onEnd() {
                super.onEnd();
                loadingScreen.onEnter();
                setScreen(loadingScreen);
            }
        };
//
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
                gameScreen.init();
                setScreen(gameScreen);
                gameScreen.onEnter();
            }
        };

        titleScreen = new TitleScreen(this){
            @Override
            public void onPlay() {
                super.onPlay();
                levelScreen.init();
                setScreen(levelScreen);
            }
        };
        setScreen(launchScreen);
//        setScreen(levelScreen);
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetManagerMaster.dispose();
        FontManagerMaster.dispose();
    }
}
