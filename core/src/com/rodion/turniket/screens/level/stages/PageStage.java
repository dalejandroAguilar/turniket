package com.rodion.turniket.screens.level.stages;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.rodion.turniket.basics.BasicScreen;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.utilities.LevelManagerMaster;

import java.io.File;
import java.util.ArrayList;

public class PageStage extends BasicStage {
    private PageLayout levelLayout;

    public PageStage(int page, Viewport viewport, BasicScreen basicScreen) {
        super(viewport, basicScreen);
        int iniLevel = page * 9;
        int endLevel = Math.min((page+1) * 9, LevelManagerMaster.getNLevels());

        ArrayList<File>  levels = new ArrayList<>();
        for(int i = iniLevel; i < endLevel; i++)
            levels.add(LevelManagerMaster.getLevels()[i]);

        levelLayout = new PageLayout(levels, this);
        addActor(levelLayout);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        levelLayout.resize(width, height);
    }

}
