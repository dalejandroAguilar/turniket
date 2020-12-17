package com.rodion.turniket.screens.game.layouts;

import com.badlogic.gdx.utils.Timer;
import com.rodion.turniket.basics.BackgroundedLayout;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.LabelEntity;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.utilities.AssetManagerMaster;
import com.rodion.turniket.utilities.FontManagerMaster;

public class StatusLayout extends Layout {
    private BackgroundedLayout time;
    private BackgroundedLayout steps;
    private LabelEntity labelTime;
    private LabelEntity labelSteps;
    private Timer.Task myTimerTask;
    private int counterTime;

    public StatusLayout(BasicStage basicStage) {
        super(basicStage);
        counterTime = 0;
        setFillParent(false);
        myTimerTask = new Timer.Task() {
            @Override
            public void run() {
                labelTime.setText(counterTime + " sec.");
                counterTime ++;
            }
        };
        startTimer();
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
        time = new BackgroundedLayout(basicStage) {
            @Override
            public void setAssetAddress() {
                setAssetManger(AssetManagerMaster.game);
                setAssetPath("game");
                setAssetName("frame_score");
            }
        };
        labelSteps = new LabelEntity("10", FontManagerMaster.nexaStyle);

        time.prepareAssets();
        time.setFillParent(false);

        steps.setFillParent(false);
        steps.add(labelSteps).expandX().center();
        time.add(labelTime);
        add(time).expand().bottom();
        add(steps).expand().bottom();
        setSteps(0);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        steps.resize(width, height);
        time.resize(width, height);
        labelSteps.resize(width, height);
        labelTime.resize(width, height);
    }

    public void resetTimer(){
        counterTime=0;
        labelTime.setText("0");
    }

    public void setSteps(int steps) {
        labelSteps.setText(steps + " steps");
    }

    public void startTimer() {
        Timer.schedule(myTimerTask, 1f, 1f);
    }
}
