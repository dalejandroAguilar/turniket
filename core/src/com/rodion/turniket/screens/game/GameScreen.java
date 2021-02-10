package com.rodion.turniket.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.rodion.turniket.MainGame;
import com.rodion.turniket.basics.BasicScreen;
import com.rodion.turniket.screens.game.stages.ConfettiStage;
import com.rodion.turniket.screens.game.stages.gameStage.GameStage;
import com.rodion.turniket.screens.game.stages.previewStage.PreviewStage;
import com.rodion.turniket.screens.game.stages.youWinStage.YouWinStage;
import com.rodion.turniket.utilities.LevelManagerMaster;
import com.rodion.turniket.utilities.ScreenScale;

public class GameScreen extends BasicScreen {
    private PreviewStage preview;
    private GameStage previousGame;
    private GameStage game;
    private GameStage nextGame;
    private ConfettiStage confettiStage;
    private YouWinStage youWinPopUpStage;
    private boolean onMoving;
    private final ScreenViewport screenViewport = new ScreenViewport();

    public GameScreen(MainGame mainGame) {
        super(mainGame);
        LevelManagerMaster.foreward();
        onMoving = false;
        preview = new PreviewStage(screenViewport, this) {
            @Override
            public void onPlay() {
                preview.hide();
                game.onInput();
                game.onBegin();
            }

            @Override
            public void onPrevious() {
                if (!onMoving)
                    if (LevelManagerMaster.backward()) {
                        onMoving = true;
                        previousGame.addAction(Actions.moveTo(0, 0, 0.5f));
                        game.addAction(Actions.sequence(
                                Actions.moveBy(Gdx.graphics.getWidth(), 0, 0.5f),
                                Actions.run(new Runnable() {
                                    @Override
                                    public void run() {
                                        nextGame = null;
                                        nextGame = game;
                                        game = null;
                                        game = previousGame;
                                        previousGame = null;
                                        if (LevelManagerMaster.getPreviousLevel() != null) {
                                            previousGame = new GameStage(LevelManagerMaster.getPreviousLevel(), screenViewport, GameScreen.this) {
                                                @Override
                                                public void onWin() {
                                                    super.onWin();
                                                    confettiStage.onThrow();
                                                }
                                            };
                                            previousGame.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                                            previousGame.addAction(Actions.moveBy(-Gdx.graphics.getWidth(), 0));
                                            previousGame.offInput();
                                            preview.onInput();
                                        }
                                        onMoving = false;
                                    }
                                })));
                    }
            }

            @Override
            public void onNext() {
                    GameScreen.this.onNext();
            }
        };

        preview.onInput();
        previousGame = new GameStage(LevelManagerMaster.getPreviousLevel(), screenViewport, this) {
            @Override
            public void onWin() {
                super.onWin();
                confettiStage.onThrow();
            }
        };

        game = new GameStage(LevelManagerMaster.getLevel(), screenViewport, this) {
            @Override
            public void onWin() {
                super.onWin();
                confettiStage.onThrow();
            }
        };

        nextGame = new GameStage(LevelManagerMaster.getNextLevel(), screenViewport, this) {
            @Override
            public void onWin() {
                super.onWin();
                confettiStage.onThrow();
            }
        };

        confettiStage = new ConfettiStage(screenViewport, this) {
            @Override
            public void onFinish() {
                super.onFinish();
                youWinPopUpStage.showUp();
                youWinPopUpStage.onInput();
            }
        };

        youWinPopUpStage = new YouWinStage(screenViewport, this) {
            @Override
            public void onContinue() {
                super.onContinue();
                onNext();
                preview.show();
                preview.onInput();
            }
        };
        youWinPopUpStage.hide();
        preview.offInput();
        preview.onInput();
    }

    private void onNext() {
        if (!onMoving)
            if (LevelManagerMaster.foreward()) {
                onMoving = true;
                nextGame.addAction(Actions.moveTo(0, 0, 0.5f));
                game.addAction(Actions.sequence(
                        Actions.moveBy(-Gdx.graphics.getWidth(), 0, 0.5f),
                        Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                previousGame = null;
                                previousGame = game;
                                game = null;
                                game = nextGame;
                                nextGame = null;
                                if (LevelManagerMaster.getNextLevel() != null) {
                                    System.out.println(LevelManagerMaster.getNextLevel());
                                    nextGame = new GameStage(LevelManagerMaster.getNextLevel(), screenViewport, GameScreen.this) {
                                        @Override
                                        public void onWin() {
                                            super.onWin();
                                            confettiStage.onThrow();
                                        }
                                    };
                                    nextGame.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                                    nextGame.addAction(Actions.moveBy(Gdx.graphics.getWidth(), 0));
                                    preview.onInput();
                                }
                                onMoving = false;
                                preview.onInput();
                            }
                        })));
            }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(31.f / 255, 31.f / 255, 31.f / 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        if (previousGame != null) {
            previousGame.act();
            previousGame.draw();
        }
        game.act();
        game.draw();
        if (nextGame != null) {
            nextGame.act();
            nextGame.draw();
        }
        confettiStage.act();
        confettiStage.draw();
        youWinPopUpStage.act();
        youWinPopUpStage.draw();
        preview.act();
        preview.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        ScreenScale.resize(width, height);
        if (previousGame != null)
            previousGame.resize(width, height);
        game.resize(width, height);
        if (nextGame != null)
            nextGame.resize(width, height);
        confettiStage.resize(width, height);
        youWinPopUpStage.resize(width, height);
        preview.resize(width, height);
        if (!onMoving) {
            if (previousGame != null)
                previousGame.addAction(Actions.moveBy(-Gdx.graphics.getWidth(), 0));
            if (nextGame != null)
                nextGame.addAction(Actions.moveBy(Gdx.graphics.getWidth(), 0));
        }
    }
}
