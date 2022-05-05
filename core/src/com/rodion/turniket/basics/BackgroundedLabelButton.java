package com.rodion.turniket.basics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.io.FileNotFoundException;

public class BackgroundedLabelButton extends BackgroundedLayout {
    private LabelEntity labelEntity;

    public BackgroundedLabelButton(CharSequence text, Label.LabelStyle[] style, BasicStage basicStage) {
        super(basicStage);
        labelEntity = new LabelEntity(text, style);
        setFillParent(false);
        addListener(new ClickListener() {
                        boolean isPressed;
                        boolean isClicked;

                        @Override
                        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                            if (button == 0) {
                                onDown();
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
                                    try {
                                        onAction();
                                    } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }

                        //                        @Override
//                        public void touchDragged(InputEvent event, float x, float y, int pointer) {
//                            if (pointer == 0) {
//                                if (!isOver() && isPressed) {
//                                    addAction(Actions.color(Color.WHITE, .2f));
//                                    isPressed = false;
//                                }
//                            }
//                        }
//
                        @Override
                        public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                            super.exit(event, x, y, pointer, toActor);
                            addAction(Actions.color(Color.WHITE, .2f));
                            isPressed = false;
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

    public void onAction() throws FileNotFoundException {
    }

    public void onDown() {

    }

}
