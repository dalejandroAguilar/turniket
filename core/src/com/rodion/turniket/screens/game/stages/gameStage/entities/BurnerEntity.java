package com.rodion.turniket.screens.game.stages.gameStage.entities;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.rodion.turniket.basics.ImageEntity;
import com.rodion.turniket.kernel.constants.Direction;
import com.rodion.turniket.utilities.AssetManagerMaster;

public class BurnerEntity extends ImageEntity {
    private int i;
    private int j;

    public BurnerEntity(int i, int j) {
        prepareAssets();
        this.i = i;
        this.j = j;
        setTouchable(Touchable.enabled);
//        addListener(new ClickListener() {
//
//            @Override
//            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                if (pointer == 0 && button == 0)
//                    return BurnerEntity.this.touchDown();
//                else
//                    return false;
//            }
//
//            @Override
//            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//                if (pointer == 0 && button == 0)
//                    BurnerEntity.this.touchUp();
//            }
//        });
//
        addListener(new ClickListener() {
            float x0;
            float y0;
            float dx;
            float dy;
            float threshold = 20f;

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (pointer == 0) {
                    x0 = x;
                    y0 = y;
                }
                onPressed(BurnerEntity.this);
                System.out.println("touchDown");
                return true;
//                        super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
//                if (isOver())
                if (pointer == 0) {
                    if (Math.abs(dx) > Math.abs(dy)) {
                        if (Math.abs(dx) > threshold) {
                            if (dx > 0) {
                                onUnpressed(BurnerEntity.this);
                                onAction(BurnerEntity.this, Direction.Right);
//                                System.out.println("Right");
                            } else {
                                onUnpressed(BurnerEntity.this);
                                onAction(BurnerEntity.this, Direction.Left);
//                                System.out.println("Left");
                            }
                        }
                    } else if (Math.abs(dy) > threshold) {
                        if (dy > 0) {
                            onUnpressed(BurnerEntity.this);
                            onAction(BurnerEntity.this, Direction.Up);
//                                System.out.println("Up");
                        } else {
                            onUnpressed(BurnerEntity.this);
                            onAction(BurnerEntity.this, Direction.Down);
//                                System.out.println("Down");
                        }
                    }
//                    else
                    dx = 0;
                    dy = 0;
                }
                onUnpressed(BurnerEntity.this);

                System.out.println("touchUp");

            }


            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                super.touchDragged(event, x, y, pointer);
                if (pointer == 0) {
                    dx = x - x0;
                    dy = y - y0;
                }
                System.out.println("touchDragged");
//                onUnpressed(BurnerEntity.this);

            }

//            @Override
//            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
//                super.exit(event, x, y, pointer, toActor);
////                onUnpressed(BurnerEntity.this);
//                System.out.println("exit");
////                invalidate();
////                cancel();
//            }

//            @Override
//            public void cancel() {
//                System.out.println("cancel");
//
//                super.cancel();
//            }
        });
    }

    @Override
    public void setAssetAddress() {
        super.setAssetAddress();
        setAssetManager(AssetManagerMaster.game);
        assetPath = "game";
        assetName = "burnera";
    }

//    public void touchUp() {
//    }
//
//    public boolean touchDown() {
//        return true;
//    }

    public void onAction(BurnerEntity burner, Direction direction) {

    }

    public void onPressed(BurnerEntity burner) {

    }

    public void onUnpressed(BurnerEntity burner) {

    }


    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }
}
