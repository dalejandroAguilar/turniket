package com.rodion.turniket.stages.settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rodion.turniket.basics.BasicScreen;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.stages.message.MessageStage;
import com.rodion.turniket.utilities.DecisionFrame;

public class SettingsStage extends BasicStage  implements DecisionFrame {
    private SettingsLayout layout;
    private BasicStage focusStage;
    public MessageStage resetConfirmationMessage;

    public SettingsStage(Viewport viewport, BasicScreen basicScreen) {
        super(viewport, basicScreen);
        layout = new SettingsLayout(this) {
            @Override
            public void onExit() {
                super.onExit();
                SettingsStage.this.onExit();
            }

            @Override
            public void onReset() {
                super.onReset();
                resetConfirmationMessage.onEnter();
            }
        };
        addActor(layout);
        focusStage = null;
        String[] lines = {"Sure you want to reset"};
        resetConfirmationMessage = new MessageStage(viewport,basicScreen,lines);
        resetConfirmationMessage.setFocusStage(this);
        resetConfirmationMessage.setDecisionFrame(this);
        resetConfirmationMessage.close();
    }

    public void init() {
        Gdx.input.setInputProcessor(this);
    }

    public void close() {
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

    @Override
    public void draw() {
        super.draw();
        resetConfirmationMessage.draw();

    }

    @Override
    public void act() {
        super.act();
        resetConfirmationMessage.act();
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
        resetConfirmationMessage.resize(width, height);
    }

    @Override
    public void onAffirmativeDecision() {
        resetConfirmationMessage.onExit();
        onInput();
    }

    @Override
    public void onNegativeDecision() {
        resetConfirmationMessage.onExit();
        onInput();
    }
}
