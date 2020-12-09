package com.rodion.turniket.basics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class BackgraundedLabelButton extends BackgroundedLayout {
    private LabelEntity labelEntity;
    public BackgraundedLabelButton(CharSequence text, Label.LabelStyle[] style, BasicStage basicStage) {
        super(basicStage);
        labelEntity = new LabelEntity(text, style);
        addListener(new ClickListener() {
                        boolean isPressed;
                        boolean isClicked;

                        @Override
                        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                            if (button == 0) {
                                isPressed = true;
                                isClicked = false;
                                addAction(Actions.color(Color.GRAY, .2f));
                            }
                            return true;
                        }

                        @Override
                        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                            if (button == 0) {
                                addAction(Actions.color(Color.WHITE, .2f));
                                if (isPressed) {
                                    onAction();
                                }
                            }
                        }

                        @Override
                        public void touchDragged(InputEvent event, float x, float y, int pointer) {
                            if (pointer == 0) {
                                if (!isOver() && isPressed) {
                                    addAction(Actions.color(Color.WHITE, .2f));
                                    isPressed = false;
                                }
                            }
                        }
                    }
        );
        add(labelEntity).expand().fill();

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        labelEntity.resize(width, height);
    }

    public void onAction() {
    }

}
