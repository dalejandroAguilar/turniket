package com.rodion.turniket.stages.settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rodion.turniket.basics.BasicScreen;
import com.rodion.turniket.basics.BasicStage;

public class SettingsStage extends BasicStage {
    private SettingsLayout layout;
    private BasicStage focusStage;

    public SettingsStage(Viewport viewport, BasicScreen basicScreen) {
        super(viewport, basicScreen);
        layout = new SettingsLayout(this) {
            @Override
            public void onExit() {
                super.onExit();
                SettingsStage.this.onExit();
            }
        };
//        layout.debug();
        addActor(layout);
        focusStage = null;
    }

    public void init() {
        Gdx.input.setInputProcessor(this);
    }

    public void close() {
//        layout.close();
        addAction(Actions.fadeOut(0));
    }

    public void onEnter(BasicStage focusStage) {
        this.focusStage = focusStage;
        addAction(Actions.moveBy(0, -50));
        addAction(Actions.parallel(
                Actions.moveBy(0, 50, .3f),
                Actions.fadeIn(.3f)
        ));
        init();
    }

    public void onExit() {
        focusStage.onInput();
        addAction(
                Actions.sequence(
                        Actions.parallel(
                                Actions.moveBy(0, -50, .3f),
                                Actions.fadeOut(.3f)
                        ),
                        Actions.moveBy(0, 50, 0)
                ));
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        layout.resize(width, height);
    }
}
