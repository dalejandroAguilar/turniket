package com.rodion.turniket.screens.level.stages;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.rodion.turniket.basics.BasicScreen;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.utilities.Difficulty;
import com.rodion.turniket.utilities.LevelManagerMaster;

import java.io.File;
import java.util.ArrayList;

public class PageStage extends BasicStage {
    private PageLayout levelLayout;

    public PageStage(int page, Viewport viewport, BasicScreen basicScreen) {
        super(viewport, basicScreen);
            levelLayout = new PageLayout(page, LevelManagerMaster.getLevels(page), this){

                @Override
                public void onClose() {
                    super.onClose();
                    PageStage.this.onClose();
                }

                @Override
                public void onPickLevel() {
                    super.onPickLevel();
                    PageStage.this.onPickLevel();
                }
            };
            addActor(levelLayout);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        levelLayout.resize(width, height);
    }

    public void onClose() {
//    addAction();
    }

    public void onPickLevel() {

    }

//    public PageLayout getLevelLayout() {
//        return levelLayout;
//    }
}
