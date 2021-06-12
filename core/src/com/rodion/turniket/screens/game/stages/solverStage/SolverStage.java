package com.rodion.turniket.screens.game.stages.solverStage;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.rodion.turniket.basics.BasicScreen;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.screens.game.stages.gameStage.layouts.SolverLayout;

public class SolverStage extends BasicStage {
    private SolverLayout solverLayout;
    private ConfirmationLayout confirmationLayout;
    public SolverStage(Viewport viewport, BasicScreen basicScreen) {
        super(viewport, basicScreen);
        confirmationLayout = new ConfirmationLayout(this);
        solverLayout = new SolverLayout(this);

        addActor(solverLayout);
        addActor(confirmationLayout);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        confirmationLayout.resize(width, height);
        solverLayout.resize(width, height);
    }
}