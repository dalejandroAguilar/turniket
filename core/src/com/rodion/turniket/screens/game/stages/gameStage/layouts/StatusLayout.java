package com.rodion.turniket.screens.game.stages.gameStage.layouts;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Timer;
import com.rodion.turniket.basics.BackgroundedLayout;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.ImageEntity;
import com.rodion.turniket.basics.LabelEntity;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.utilities.AssetManagerMaster;
import com.rodion.turniket.utilities.FontManagerMaster;
import com.rodion.turniket.utilities.Level;

public class StatusLayout extends Layout {
    private BackgroundedLayout time;
    private BackgroundedLayout steps;
    private LabelEntity labelTime;
    private LabelEntity labelSteps;
    private ImageEntity crown;
    private Timer.Task timer;
    private int counterTime;

    public StatusLayout(BasicStage basicStage, Level level) {
        super(basicStage);
        counterTime = 0;
        setFillParent(false);
        timer = new Timer.Task() {
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

        steps.add(labelSteps).expandX().center();
        time.add(labelTime);
        add(time).bottom();
        add(crown).bottom();
        add(steps).bottom();
        setSteps(0);
        setToPreview();
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
        timer.cancel();
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
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        steps.resize(width, height);
        time.resize(width, height);
        labelSteps.resize(width, height);
        labelTime.resize(width, height);
        crown.resize(width, height);
    }

    public void startTimer() {
        Timer.schedule(timer, 1f, 1f);
    }

    public void onHint() {
        steps.addAction(Actions.fadeOut(.5f));
        time.addAction(Actions.fadeOut(.5f));
        labelSteps.addAction(Actions.fadeOut(.5f));
        labelTime.addAction(Actions.fadeOut(.5f));
        crown.addAction(Actions.fadeOut(.5f));
    }

//    public void onSetToActive() {
//        steps.addAction(Actions.color(Color.BLACK));
//        time.addAction(Actions.color(Color.BLACK));
//        crown.getColor().a = 0;
//        labelSteps.getColor().a = 1; // labels of level
//        labelTime.getColor().a = 1;  // labels of level
//    }


    public void setToPreview() {
        steps.setColor(Color.ORANGE);
        time.setColor(Color.ORANGE);
        if(steps.getColor().a != 1){
            labelSteps.addAction(Actions.fadeIn(0.2f));
            labelTime.addAction(Actions.fadeIn(0.2f));
        }
        crown.getColor().a = 1;
        timer.cancel();
    }

//    @Override
//    public void show() {
//        steps.addAction(Actions.fadeIn(0));
//        time.addAction(Actions.fadeIn(0));
//        crown.addAction(Actions.fadeIn(0));
//    }



    public void onPlay() {
//        show();
        System.out.println("alfa<<<<<<<<<<<<<<<<"+steps.getColor().a);
        if(steps.getColor().a != 0) {
            steps.addAction(Actions.color(Color.BLACK, .2f));
            time.addAction(Actions.color(Color.BLACK, .2f));
        }
        else {
//            steps.setColor(Color.BLACK);
//            time.setColor(Color.BLACK);
            steps.addAction(Actions.fadeIn(.2f));
            time.addAction(Actions.fadeIn(.2f));

        }

        if(crown.getColor().a!=0)
            crown.addAction(Actions.fadeOut(.2f));

        labelSteps.addAction(Actions.fadeIn(.2f));
        labelTime.addAction(Actions.fadeIn(.2f));
        timer.cancel();
        timer.run();

//        crown.getColor().a = 0;

//        labelSteps.getColor().a = 1; // labels of level
//        labelTime.getColor().a = 1;  // labels of level
//        startTimer();
    }



}