package com.rodion.turniket.screens.level.stages;

import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.Layout;

public class UILevelLayout extends Layout {
    private TopMenuLayout topMenu;
    private DifficultyMenuLayoutFront difficultyMenu;
    private BottomMenuLayoutFront bottomMenu;

    public UILevelLayout(BasicStage basicStage) {
        super(basicStage);
        setFillParent(true);
        topMenu = new TopMenuLayout(basicStage) {
            @Override
            public void onBack() {
                super.onBack();
                UILevelLayout.this.onBack();
            }

            @Override
            public void onSettings() {
                super.onSettings();
                UILevelLayout.this.onSettings();
            }
        };
        difficultyMenu = new DifficultyMenuLayoutFront(basicStage) {
            @Override
            public void onPrevious() {
                onPreviousDifficulty();
            }

            @Override
            public void onNext() {
                onNextDifficulty();
            }
        };
        bottomMenu = new BottomMenuLayoutFront(basicStage) {
            @Override
            public void onPrevious() {
                onPreviousPage();
            }

            @Override
            public void onNext() {
                onNextPage();
            }
        };
        add(topMenu).top().expandX().fillX().row();
        add(difficultyMenu).top().expandX().fillX().row();
        add().expand().row();
        add(bottomMenu).bottom().expandX().fillX().row();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        topMenu.resize(width, height);
        difficultyMenu.resize(width, height);
        bottomMenu.resize(width, height);
    }

    public void onPreviousDifficulty() {
    }

    public void onNextDifficulty() {
    }

    public void onPreviousPage() {
    }

    public void onNextPage() {
    }

    public void onBack() {

    }

    public void onSettings() {

    }

    public void update(){
        topMenu.update();
    }
}

