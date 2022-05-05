package com.rodion.turniket.screens.game.layouts;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.rodion.turniket.basics.BackgroundedLabelButton;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.ImageEntity;
import com.rodion.turniket.basics.LabelEntity;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.utilities.AssetManagerMaster;
import com.rodion.turniket.utilities.ColorManagerMaster;
import com.rodion.turniket.utilities.FontManagerMaster;
import com.rodion.turniket.utilities.Level;
import com.rodion.turniket.utilities.LevelManagerMaster;
import com.rodion.turniket.utilities.SoundManagerMaster;

import java.io.FileNotFoundException;

public class TopMenuLayout extends Layout {
    private BackgroundedLabelButton backButton;
    private BackgroundedLabelButton settingsButton;
    private LabelEntity score;
    private ImageEntity star;
    private int nstars;

    public TopMenuLayout(BasicStage basicStage) {
        super(basicStage);
        nstars = LevelManagerMaster.getNstars();
        backButton = new BackgroundedLabelButton("Back", FontManagerMaster.helveticaStyle,
                getParentStage()) {
            @Override
            public void setAssetAddress() {
                setAssetManger(AssetManagerMaster.game);
                setAssetPath("game");
                setAssetName("button_back");
            }

            @Override
            public void onAction() throws FileNotFoundException {
                super.onAction();
                onReturn();
            }

            @Override
            public void onDown() {
                super.onDown();
                SoundManagerMaster.play("click");
            }
        };
        backButton.setFillParent(false);
        settingsButton = new BackgroundedLabelButton("Settings", FontManagerMaster.helveticaStyle, getParentStage()) {
            @Override
            public void setAssetAddress() {
                setAssetManger(AssetManagerMaster.game);
                setAssetPath("game");
                setAssetName("button_settings");
            }

            @Override
            public void onAction() throws FileNotFoundException {
                super.onAction();
                onSettings();
            }

            @Override
            public void onDown() {
                super.onDown();
                SoundManagerMaster.play("click");
            }
        };
        star = new ImageEntity() {
            @Override
            public void setAssetAddress() {
                setAssetManager(AssetManagerMaster.game);
                assetPath = "game";
                assetName = "little_star";
            }
        };
        star.prepareAssets();
        star.setColor(Color.YELLOW);

        score = new LabelEntity(nstars + "", FontManagerMaster.nexaStyle);
        score.setColor(Color.WHITE);
        settingsButton.setFillParent(false);
        setFillParent(false);
        add(backButton).left();
        add(star).left().padLeft(20);
        add(score).left().padLeft(20);
        add().expandX().fillX();
        add(settingsButton).right();
        setBackground(ColorManagerMaster.grayBg);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        star.resize(width, height);
        score.resize(width, height);
        backButton.resize(width, height);
        settingsButton.resize(width, height);
    }

    public void onReturn() {

    }

    public void onSettings() {

    }

    public void onIncreasing(int amount) {

        for (int i = 0; i < amount; i++) {
            final int ii = i;
            star.addAction(
                    Actions.sequence(
                            Actions.delay(0.4f * i),
                            Actions.parallel(
                                    Actions.scaleTo(2, 2, 0.2f),
                                    Actions.scaleTo(1, 1, 0.2f),
                                    Actions.run(new Runnable() {
                                        @Override
                                        public void run() {
                                            score.setText((nstars + ii + 1) + " ");
                                        }
                                    })
                            )
                    )
            );
        }
        LevelManagerMaster.setNstars(LevelManagerMaster.getNstars() + amount);
        addAction(Actions.sequence(
                Actions.delay(0.4f * (amount)),
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        update();

                    }
                })

        ));
//            sequenceAction.addAction();
//            Ac
//        addAction(Actions.sequence(
//
//        ));
    }

    public void update() {
        nstars = LevelManagerMaster.getNstars();
        score.setText(nstars + "");
    }
}