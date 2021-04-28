package com.rodion.turniket.screens.game.stages.gameStage.layouts;

import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.LabelEntity;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.screens.game.stages.gameStage.entities.LockEntity;

public class LockLayout extends Layout {
    private LockEntity lockEntity;
    LabelEntity labelEntity;
    public LockLayout(BasicStage basicStage) {
        super(basicStage);
        lockEntity = new LockEntity();

    }


    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        lockEntity.resize(width, height);
    }
}
