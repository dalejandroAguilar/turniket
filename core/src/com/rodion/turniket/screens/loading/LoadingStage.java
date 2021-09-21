package com.rodion.turniket.screens.loading;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.rodion.turniket.basics.BasicScreen;
import com.rodion.turniket.basics.BasicStage;

public class LoadingStage extends BasicStage {
    private LoadingLayout loadingLayout;

    public LoadingStage(Viewport viewport, BasicScreen basicScreen) {
        super(viewport, basicScreen);
        loadingLayout = new LoadingLayout(this);
        addActor(loadingLayout);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        loadingLayout.resize(width, height);
    }

    @Override
    public void dispose() {
        super.dispose();
        loadingLayout.dispose();
    }
}
