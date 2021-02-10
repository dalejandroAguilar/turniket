package com.rodion.turniket.screens.level.stages;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.rodion.turniket.basics.BasicScreen;
import com.rodion.turniket.basics.BasicStage;

public class UILevelStage extends BasicStage {
    private UILevelLayout uiLevelLayout;

    public UILevelStage(Viewport viewport, BasicScreen basicScreen) {
        super(viewport, basicScreen);
        uiLevelLayout = new UILevelLayout(this) {
            @Override
            public void onPreviousDifficulty() {
                UILevelStage.this.onPreviousDifficulty();
            }

            @Override

            public void onNextDifficulty() {
                UILevelStage.this.onNextDifficulty();
            }

            @Override

            public void onPreviousPage() {
                UILevelStage.this.onPreviousPage();
            }

            @Override

            public void onNextPage() {
                UILevelStage.this.onNextPage();
            }
        };
        addActor(uiLevelLayout);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        uiLevelLayout.resize(width, height);
    }

    public void onPreviousDifficulty() {
    }

    public void onNextDifficulty() {
    }

    public void onPreviousPage() {
    }

    public void onNextPage() {
    }
}
