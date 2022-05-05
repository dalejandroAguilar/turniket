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
import com.rodion.turniket.utilities.Multiplatform;
import com.rodion.turniket.utilities.ScreenScale;
import com.rodion.turniket.utilities.SoundManagerMaster;

public class MainGame extends Game {

    public LaunchScreen launchScreen;
    public LoadingScreen loadingScreen;
    public GameScreen gameScreen;
    public LevelScreen levelScreen;
    public TitleScreen titleScreen;
    public Multiplatform multiplatform;


    public MainGame(Multiplatform multiplatform) {
        super();
        this.multiplatform = multiplatform;
    }

    @Override
    public void create() {
        AssetManagerMaster.loadLoading();
        System.out.println("init");
        LevelManagerMaster.init(multiplatform);
        SoundManagerMaster.init();
        ScreenScale.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        loadingScreen = new LoadingScreen(this) {
            @Override
            public void onActive() {
                AssetManagerMaster.loadTitle();
                AssetManagerMaster.loadLevels();
                AssetManagerMaster.loadGame();
                AssetManagerMaster.loadSettings();
                ColorManagerMaster.load();
                FontManagerMaster.loadFonts();
            }

            @Override
            public boolean hasFinishedLoading() {
                return (AssetManagerMaster.game.update() && AssetManagerMaster.title.update() &&
                        AssetManagerMaster.level.update());
            }

            @Override
            public void onExit() {
                super.onExit();
                titleScreen = new TitleScreen(MainGame.this) {
                    @Override
                    public void onPlay() {
                        super.onPlay();
                        levelScreen = new LevelScreen(MainGame.this) {
                            @Override
                            public void onGoToGameScreen() {
                                super.onGoToGameScreen();
//                                System.out.println("onGoToGameScreen ext");
                                gameScreen = new GameScreen(MainGame.this) {
                                    @Override
                                    public void onGoToLevelScreen() {
                                        super.onGoToLevelScreen();
                                        levelScreen.init();
                                        levelScreen.onEnterBackward();
                                        setScreen(levelScreen);
                                    }
                                };
                                gameScreen.init();
                                setScreen(gameScreen);
                                gameScreen.onEnter();
                            }

                            @Override
                            public void onBack() {
                                super.onBack();
                                System.out.println("back");
                                titleScreen.init();
                                setScreen(titleScreen);
                                titleScreen.onEnterBackward();
                            }
                        };
                        levelScreen.init();
                        levelScreen.onEnterForward();
                        setScreen(levelScreen);
                    }
                };
                titleScreen.init();
                titleScreen.onEnterForward();
                setScreen(titleScreen);
//                AssetManagerMaster.game.up;
            }
        };

        launchScreen = new LaunchScreen(this) {
            @Override
            public void onEnd() {
                super.onEnd();
                loadingScreen.onEnter();
                setScreen(loadingScreen);
            }
        };
        setScreen(launchScreen);

//        AssetManagerMaster.loadTitle();
//        AssetManagerMaster.loadLevels();
//        AssetManagerMaster.loadGame();
//        AssetManagerMaster.loadSettings();
//        ColorManagerMaster.load();
//        FontManagerMaster.loadFonts();
//        gameScreen = new GameScreen(MainGame.this) {
//                                    @Override
//                                    public void onGoToLevelScreen() {
//                                        super.onGoToLevelScreen();
//                                        levelScreen.init();
//                                        levelScreen.onEnterBackward();
//                                        setScreen(levelScreen);
//                                    }
//                                };
//        gameScreen.init();
//        setScreen(gameScreen);
//        gameScreen.onEnter();

    }

    @Override
    public void dispose() {
        super.dispose();
        AssetManagerMaster.dispose();
        FontManagerMaster.dispose();
        SoundManagerMaster.dispose();
    }
}
