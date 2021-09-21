package com.rodion.turniket.screens.launch;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.rodion.turniket.basics.BasicScreen;
import com.rodion.turniket.basics.BasicStage;

public class LaunchStage extends BasicStage {
    private LaunchLayout launchLayout;
    public LaunchStage(Viewport viewport, BasicScreen basicScreen) {
        super(viewport, basicScreen);
        launchLayout = new LaunchLayout(this);
        addActor(launchLayout);
    }
}
