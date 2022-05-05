package com.rodion.turniket.basics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ImageButtonEntity extends ImageEntity {
    public ImageButtonEntity() {
        super();
        setTouchable(Touchable.enabled);
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
                                    onAction();
                                }
                            }
                        }

//                        @Override
//                        public void touchDragged(InputEvent event, float x, float y, int pointer) {
//                            if (pointer == 0) {
////                                System.out.println("dragged");
//                                if (!isOver() && isPressed) {
//                                    addAction(Actions.color(Color.WHITE, .2f));
//                                    isPressed = false;
//                                }
//                            }
//                        }

                        @Override
                        public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                            super.exit(event, x, y, pointer, toActor);
                            addAction(Actions.color(Color.WHITE, .2f));
                            isPressed = false;
                        }
                    }
        );
    }

    public void onAction() {
    }

    public void onDown(){

    }
}
