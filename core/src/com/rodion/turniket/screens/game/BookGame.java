package com.rodion.turniket.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.rodion.turniket.basics.BasicScreen;
import com.rodion.turniket.screens.game.stages.gameStage.GameStage;
import com.rodion.turniket.utilities.LevelManagerMaster;

public class BookGame {
    private boolean onMoving;
    private GameStage previousGame;
    private GameStage game;
    private GameStage nextGame;
    private ScreenViewport screenViewport;
    private BasicScreen basicScreen;

    public BookGame(ScreenViewport screenViewport, BasicScreen basicScreen) {
        onMoving = false;
        this.screenViewport = screenViewport;
        this.basicScreen = basicScreen;
        System.out.println(LevelManagerMaster.getBookmark());
        System.out.println(LevelManagerMaster.getNLevels());


        if (LevelManagerMaster.getPreviousLevel() != null)
            previousGame = new GameStage(LevelManagerMaster.getPreviousLevel(),
                    LevelManagerMaster.getBookmark() - 1, screenViewport, basicScreen) {
                @Override
                public void onWin() {
                    BookGame.this.onWin();
                }

                @Override
                public void onReturn() {
                    super.onReturn();
                    BookGame.this.onReturn();
                }
            };
        else
            previousGame = null;

        game = new GameStage(LevelManagerMaster.getLevel(),
                LevelManagerMaster.getBookmark(), screenViewport, basicScreen) {
            @Override
            public void onWin() {
                BookGame.this.onWin();
            }
            @Override
            public void onReturn() {
                super.onReturn();
                BookGame.this.onReturn();
            }
        };

        nextGame = new GameStage(LevelManagerMaster.getNextLevel(),
                LevelManagerMaster.getBookmark() + 1, screenViewport, basicScreen) {
            @Override
            public void onWin() {
                BookGame.this.onWin();
            }
            @Override
            public void onReturn() {
                super.onReturn();
                BookGame.this.onReturn();
            }
        };
    }

    public void onPrevious() {
        if (LevelManagerMaster.goBackwardLevel()) {
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
                                previousGame = new GameStage(LevelManagerMaster.getPreviousLevel(),
                                        LevelManagerMaster.getBookmark() - 1,
                                        screenViewport, basicScreen) {
                                    @Override
                                    public void onWin() {
                                        BookGame.this.onWin();
                                    }
                                    @Override
                                    public void onReturn() {
                                        super.onReturn();
                                        BookGame.this.onReturn();
                                    }
                                };
                                previousGame.resize(Gdx.graphics.getWidth(),
                                        Gdx.graphics.getHeight());
                                previousGame.addAction(Actions.moveBy(-Gdx.graphics.getWidth(),
                                        0));
                            }
                            onMoving = false;
                        }
                    })));
        }
    }

    public void onNext() {
        if (LevelManagerMaster.goForwardLevel()) {
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
                                nextGame = new GameStage(LevelManagerMaster.getNextLevel(),
                                        LevelManagerMaster.getBookmark() + 1,
                                        screenViewport, basicScreen) {
                                    @Override
                                    public void onWin() {
                                        BookGame.this.onWin();
                                    }
                                    @Override
                                    public void onReturn() {
                                        super.onReturn();
                                        BookGame.this.onReturn();
                                    }
                                };
                                nextGame.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                                nextGame.addAction(Actions.moveBy(Gdx.graphics.getWidth(),
                                        0));
                            }
                            onMoving = false;
                        }
                    })));
        }
    }

    public void onWin() {

    }

    public void render(float delta) {
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
    }

    public void resize(int width, int height) {
        if (previousGame != null)
            previousGame.resize(width, height);
        game.resize(width, height);
        if (nextGame != null)
            nextGame.resize(width, height);
        if (!onMoving) {
            if (previousGame != null)
                previousGame.addAction(Actions.moveBy(-Gdx.graphics.getWidth(), 0));
            if (nextGame != null)
                nextGame.addAction(Actions.moveBy(Gdx.graphics.getWidth(), 0));
        }
    }

    public GameStage getPreviousGame() {
        return previousGame;
    }

    public GameStage getGame() {
        return game;
    }

    public GameStage getNextGame() {
        return nextGame;
    }

    public boolean isOnMoving() {
        return onMoving;
    }

    public void onReturn(){

    }
}