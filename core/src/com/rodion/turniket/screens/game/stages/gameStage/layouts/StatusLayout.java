package com.rodion.turniket.screens.game.stages.gameStage.layouts;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.rodion.turniket.basics.BackgroundedLayout;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.ImageEntity;
import com.rodion.turniket.basics.LabelEntity;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.utilities.AssetManagerMaster;
import com.rodion.turniket.utilities.FontManagerMaster;

public class StatusLayout extends Layout {
    private BackgroundedLayout time;
    private BackgroundedLayout steps;
    private LabelEntity labelTime;
    private LabelEntity labelSteps;
    private ImageEntity crown;
    private Timer.Task myTimerTask;
    private LabelEntity labelSolution;

    private int counterTime;

    public StatusLayout(BasicStage basicStage) {
        super(basicStage);
        counterTime = 0;
        setFillParent(false);
        myTimerTask = new Timer.Task() {
            @Override
            public void run() {
                labelTime.setText(counterTime + " sec.");
                counterTime++;
            }
        };
        labelTime = new LabelEntity("0", FontManagerMaster.nexaStyle);
        steps = new BackgroundedLayout(basicStage) {
            @Override
            public void setAssetAddress() {
                setAssetManger(AssetManagerMaster.game);
                setAssetPath("game");
                setAssetName("frame_score");
            }
        };
        steps.prepareAssets();
        steps.setColor(Color.ORANGE);
        steps.setFillParent(false);

        time = new BackgroundedLayout(basicStage) {
            @Override
            public void setAssetAddress() {
                setAssetManger(AssetManagerMaster.game);
                setAssetPath("game");
                setAssetName("frame_score");
            }
        };
        time.prepareAssets();
        time.setColor(Color.ORANGE);
        time.setFillParent(false);
        labelSteps = new LabelEntity("10", FontManagerMaster.nexaStyle);

        crown = new ImageEntity() {
            @Override
            public void setAssetAddress() {
                setAssetManager(AssetManagerMaster.game);
                assetPath = "game";
                assetName = "crown";
            }
        };
        crown.prepareAssets();
        crown.setColor(Color.ORANGE);

        labelSolution = new LabelEntity("Solution", FontManagerMaster.nexaStyle) {
            @Override
            public void updatePosition() {
                super.updatePosition();
                setPosition(
                        crown.getAbsX() + crown.getWidth() * 0.5f,
                        crown.getAbsY() + crown.getHeight() * 0.5f, Align.center
                );
            }
        };


        steps.add(labelSteps).expandX().center();
        time.add(labelTime);
        add(time).bottom();
        add(crown).bottom();
        add(steps).bottom();
        setSteps(0);
//        setDebug(true);
    }

    public void resetTimer() {
        counterTime = 0;
        labelTime.setText("0");
    }

    public void setSteps(int steps) {
        labelSteps.setText(steps + " steps");
    }

    public void stop() {
        myTimerTask.cancel();
    }

    public void onBegin() {
        startTimer();
        time.addAction(Actions.color(Color.BLACK, .3f));
        steps.addAction(Actions.color(Color.BLACK, .3f));
        crown.addAction(Actions.fadeOut(.3f));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        labelSolution.draw(batch, parentAlpha);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        steps.resize(width, height);
        time.resize(width, height);
        labelSteps.resize(width, height);
        labelTime.resize(width, height);
        crown.resize(width, height);
        labelSolution.resize(width, height);
    }

    public void startTimer() {
        Timer.schedule(myTimerTask, 1f, 1f);
    }

    public void onHint() {
        steps.addAction(Actions.fadeOut(.5f));
        time.addAction(Actions.fadeOut(.5f));
        labelSteps.addAction(Actions.fadeOut(.5f));
        labelTime.addAction(Actions.fadeOut(.5f));
        crown.addAction(Actions.fadeOut(.5f));
    }
}
