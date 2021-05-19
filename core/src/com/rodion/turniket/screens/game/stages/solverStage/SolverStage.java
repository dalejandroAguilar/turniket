package com.rodion.turniket.screens.game.stages.solverStage;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.rodion.turniket.basics.BasicScreen;
import com.rodion.turniket.basics.BasicStage;

public class SolverStage extends BasicStage {
    private SolverLayout solverLayout;
    public SolverStage(Viewport viewport, BasicScreen basicScreen) {
        super(viewport, basicScreen);
        solverLayout = new SolverLayout(this);
        addActor(solverLayout);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        solverLayout.resize(width, height);
    }
}