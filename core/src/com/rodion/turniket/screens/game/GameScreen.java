package com.rodion.turniket.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.rodion.turniket.MainGame;
import com.rodion.turniket.basics.BasicScreen;
import com.rodion.turniket.screens.game.stages.ConfettiStage;
import com.rodion.turniket.screens.game.stages.previewStage.PreviewStage;
import com.rodion.turniket.screens.game.stages.solverStage.SolverStage;
import com.rodion.turniket.screens.game.stages.youWinStage.YouWinStage;
import com.rodion.turniket.stages.message.MessageStage;
import com.rodion.turniket.stages.settings.SettingsStage;
import com.rodion.turniket.utilities.DecisionFrame;
import com.rodion.turniket.utilities.LevelManagerMaster;
import com.rodion.turniket.utilities.ScreenScale;
import com.rodion.turniket.utilities.SoundManagerMaster;

import java.io.FileNotFoundException;

//TODO: Erase youWinPopUpStage

public class GameScreen extends BasicScreen {
    private final ScreenViewport screenViewport = new ScreenViewport();
    private PreviewStage preview;
    private BookGame bookGame;
    private ConfettiStage confettiStage;
    private YouWinStage youWinPopUpStage;
    private MessageStage youWinMessage;
    private SolverStage solverStage;
    private SettingsStage settingsStage;
    private MessageStage confirmationToReturn;

    public GameScreen(final MainGame mainGame) {
        super(mainGame);
        bookGame = new BookGame(screenViewport, this) {
            @Override
            public void onWin() {
                Gdx.input.setInputProcessor(null);
                confettiStage.onThrow();
                getGame().saveStatus(getMainGame().multiplatform);
            }

            @Override
            public void onReturn() {
                confirmationToReturn.onEnter();

//                super.onReturn();
//                onGoToLevelScreen();
            }

            public void onSettings() {
                super.onSettings();
                settingsStage.onEnter(getGame());
            }

        };

        preview = new PreviewStage(screenViewport, this) {
            @Override
            public void onPlay() {
                preview.hide();
                bookGame.getGame().onInput();
                bookGame.onPlay();
                SoundManagerMaster.stopMusic("menu");
            }

            @Override
            public void onUnlock() {
                super.onUnlock();
                bookGame.onUnlock();
            }

            @Override
            public void onPrevious() {
                if (!bookGame.isOnMoving())
                    bookGame.onPrevious();
            }

            @Override
            public void onNext() {
                GameScreen.this.onNext();
            }

            @Override
            public void onReturn() {
                super.onReturn();
                bookGame.getGame().addAction(Actions.fadeOut(0.2f));
                preview.addAction(Actions.fadeOut(0.2f));

                addAction(Actions.sequence(
                        Actions.fadeOut(0.2f),
                        Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                onGoToLevelScreen();
                            }
                        })
                ));
            }

            @Override
            public void onSettings() {
                super.onSettings();
                settingsStage.onEnter(this);
            }
        };

        confettiStage = new ConfettiStage(screenViewport, this) {
            @Override
            public void onFinish() {
                super.onFinish();
                youWinMessage.onEnter();
//                youWinPopUpStage.showUp();
//                youWinPopUpStage.onInput();
            }
        };

        youWinPopUpStage = new YouWinStage(screenViewport, this) {
            @Override
            public void onContinue() {
                super.onContinue();
                System.out.println("Continue");
                onNext();
//                if(bookGame.areRequirementsSatisfied() && !bookGame.getGame().isUnlocked()){
//                }
//                if(LevelManagerMaster.getNstars() > bookGame.getGame().)
                preview.show();
                preview.onInput();
                bookGame.update();
                System.out.println("OnUnlock");
                bookGame.onUnlock();
            }

            @Override
            public void onSaveSolution() throws FileNotFoundException {
                super.onSaveSolution();
//                bookGame.getGame().onSaveSolution();
            }
        };

        String[] youWinLines = {"You Win"};

        youWinMessage = new MessageStage(screenViewport, this, youWinLines, "Continue",
                "Save Solution");

        youWinMessage.setDecisionFrame(new DecisionFrame() {
            @Override
            public void onAffirmativeDecision() {
                youWinMessage.onExit();
                onNext();
                preview.show();
                preview.onInput();
                bookGame.update();
//                bookGame.getNextGame().onUnlock();
            }

            @Override
            public void onNegativeDecision() {
                /////////////////// ON-SAVE-SOLUTION
//                youWinMessage.onExit();

                bookGame.getGame().saveSolution(mainGame.multiplatform);
            }
        });
        youWinMessage.close();

        settingsStage = new SettingsStage(screenViewport, this);
//        settingsStage.close();

        solverStage = new SolverStage(screenViewport, this) {
            @Override
            public void onReturn() {
                super.onReturn();
                System.out.println("onBack back back");
                confirmationToReturn.onEnter();
            }
        };
        String[] levelMessageLine = {"Sure to go to exit", "Your progress will lose."};
        confirmationToReturn = new MessageStage(screenViewport, this, levelMessageLine);
        confirmationToReturn.setDecisionFrame(new DecisionFrame() {
            @Override
            public void onAffirmativeDecision() {
                confirmationToReturn.onExit();
//                bookGame.onInput();
                bookGame.onReturn();
                onGoToLevelScreen();
            }

            @Override
            public void onNegativeDecision() {
                confirmationToReturn.onExit();
                bookGame.onInput();
            }
        });
        confirmationToReturn.close();

    }

    private void onNext() {
        if (!bookGame.isOnMoving()) {
            bookGame.onNext();
        }
    }

    public void onInput() {
        preview.onInput();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(31.f / 255, 31.f / 255, 31.f / 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

//        backBufferStage.act();
//        backBufferStage.draw();

        bookGame.render(delta);
        confettiStage.act();
        confettiStage.draw();
        youWinPopUpStage.act();
        youWinPopUpStage.draw();
        preview.act();
        preview.draw();
        solverStage.act();
        solverStage.draw();
        settingsStage.act();
        settingsStage.draw();
        confirmationToReturn.act();
        confirmationToReturn.draw();
        youWinMessage.act();
        youWinMessage.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        ScreenScale.resize(width, height);
        confettiStage.resize(width, height);
        youWinPopUpStage.resize(width, height);
        preview.resize(width, height);
        bookGame.resize(width, height);
        solverStage.resize(width, height);
        settingsStage.resize(width, height);
        confirmationToReturn.resize(width, height);
        youWinMessage.resize(width, height);
    }

    public void onReturn() {
//        mainGame.setScreen(new LevelScreen(mainGame));
    }

    public void onEnter() {
        bookGame.getGame().addAction(Actions.rotateTo(-90, 0));
        preview.addAction(Actions.rotateTo(-90, 0));
        bookGame.getGame().addAction(Actions.rotateBy(90, .2f));
        preview.addAction(Actions.rotateBy(90, .2f));
        preview.onInput();
        preview.onEnter();
//        settingsStage.init();
    }

    public void init() {
        solverStage.hide();
        youWinPopUpStage.hide();
        preview.onInput();
        bookGame.init();
        settingsStage.close();
    }

    public void onGoToLevelScreen() {

    }

    @Override
    public void dispose() {
        super.dispose();
    }
}