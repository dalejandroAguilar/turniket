package com.rodion.turniket.stages.message;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rodion.turniket.basics.BasicScreen;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.utilities.DecisionFrame;

public class MessageStage extends BasicStage {
    private MessageLayout messageLayout;
    private BasicStage focusStage;
    private DecisionFrame decisionFrame;

    public MessageStage(Viewport viewport, BasicScreen basicScreen, String[] lines) {
        super(viewport, basicScreen);
        messageLayout = new MessageLayout(this, lines) {
            @Override
            public void onOk() {
                super.onOk();
                decisionFrame.onAffirmativeDecision();
            }

            @Override
            public void onCancel() {
                super.onCancel();
                decisionFrame.onNegativeDecision();
            }
        };
        focusStage = null;
        addActor(messageLayout);
    }

    public MessageStage(Viewport viewport, BasicScreen basicScreen, String[] lines, String okLine,
                        String notLine) {
        super(viewport, basicScreen);
        messageLayout = new MessageLayout(this, lines, okLine, notLine) {
            @Override
            public void onOk() {
                super.onOk();
                decisionFrame.onAffirmativeDecision();
            }

            @Override
            public void onCancel() {
                super.onCancel();
                decisionFrame.onNegativeDecision();
            }
        };
        focusStage = null;
        addActor(messageLayout);
    }

    public void setFocusStage(BasicStage focusStage) {
        this.focusStage = focusStage;
    }

    public void setDecisionFrame(DecisionFrame decisionFrame) {
        this.decisionFrame = decisionFrame;
    }

    public void close() {
        addAction(Actions.fadeOut(0));
    }

    public void onEnter() {
        addAction(Actions.moveBy(0, -50));
        addAction(Actions.parallel(
                Actions.moveBy(0, 50, .3f),
                Actions.fadeIn(.3f)
        ));
        onInput();
    }

    public void onExit() {
//        focusStage.onInput();
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
        messageLayout.resize(width, height);
    }
}
