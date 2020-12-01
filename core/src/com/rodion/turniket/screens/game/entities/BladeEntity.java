package com.rodion.turniket.screens.game.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.rodion.turniket.basics.BundleAnimationEntity;
import com.rodion.turniket.kernel.Blade;
import com.rodion.turniket.kernel.constants.Spin;
import com.rodion.turniket.utilities.AssetManagerMaster;
import com.rodion.turniket.utilities.ScreenScale;

public class BladeEntity extends BundleAnimationEntity {
    private Blade blade;
    private float axisX;
    private float axisY;

    public BladeEntity(Blade blade) {
        super(.02f, 6);
        prepareAssets();
        this.blade = blade;
        setRotation(blade.getDirection().getAngle());
        setAxisPosition(117.67586389f, 117.67586389f);
        setSelectAnimation(1);
        blade.addListener(new Blade.Listener() {
            @Override
            public void onRotate(final Spin spin, final Blade.Status status) {

                final Spin spinfinal = spin;
                addAction(
                        Actions.sequence(
                                Actions.parallel(
                                        Actions.run(
                                                new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        switch (status) {
                                                            case Ok:
                                                                if (spinfinal == Spin.CounterClokwise) {
                                                                    setAxisPosition(17.46713562f, 117.67586389f);
                                                                    setSelectAnimation(0);
                                                                } else {
                                                                    setAxisPosition(117.67586389f, 117.67586389f);
                                                                    setSelectAnimation(1);
                                                                }
                                                                break;
                                                            case TokenCollision:
                                                                if (spinfinal == Spin.CounterClokwise) {
                                                                    setAxisPosition(17.46713562f, 117.67586389f);

                                                                    setSelectAnimation(2);
                                                                } else {
                                                                    setAxisPosition(117.67586389f, 117.67586389f);
                                                                    setSelectAnimation(3);
                                                                }
                                                                break;
                                                            case BladeCollision:
                                                                if (spinfinal == Spin.CounterClokwise) {
                                                                    setAxisPosition(17.46713562f, 117.67586389f);
                                                                    setSelectAnimation(4);
                                                                } else {
                                                                    setAxisPosition(117.67586389f, 117.67586389f);
                                                                    setSelectAnimation(5);
                                                                }
                                                                break;
                                                        }
                                                        setOnPlay(true);
                                                    }
                                                }
                                        ),
                                        Actions.delay(getDuration())
                                ),
                                Actions.run(
                                        new Runnable() {
                                            @Override
                                            public void run() {
                                                if (status == Blade.Status.Ok)
                                                    updateRotation();
                                                setOnPlay(false);
                                            }

                                        }
                                )
                        )
                );
            }
        });
    }

    @Override
    public void setAssetAddress() {
        super.setAssetAddress();
        setAssetManager(AssetManagerMaster.game);
        assetPath = "game";
        assetNames = new String[6][18];
        assetNames[0][0] = "bladea01";
        assetNames[0][1] = "bladea01";
        assetNames[0][2] = "bladea01";
        assetNames[0][3] = "bladea01";
        assetNames[0][4] = "bladea01";
        assetNames[0][5] = "bladea02";
        assetNames[0][6] = "bladea03";
        assetNames[0][7] = "bladea04";
        assetNames[0][8] = "bladea05";
        assetNames[0][9] = "bladea06";
        assetNames[0][10] = "bladea07";
        assetNames[0][11] = "bladea08";
        assetNames[0][12] = "bladea09";
        assetNames[0][13] = "bladea10";
        assetNames[0][14] = "bladea11";
        assetNames[0][15] = "bladea12";
        assetNames[0][16] = "bladea13";
        assetNames[0][17] = "bladea14";

        assetNames[1][0] = "bladea01m";
        assetNames[1][1] = "bladea01m";
        assetNames[1][2] = "bladea01m";
        assetNames[1][3] = "bladea01m";
        assetNames[1][4] = "bladea01m";
        assetNames[1][5] = "bladea02m";
        assetNames[1][6] = "bladea03m";
        assetNames[1][7] = "bladea04m";
        assetNames[1][8] = "bladea05m";
        assetNames[1][9] = "bladea06m";
        assetNames[1][10] = "bladea07m";
        assetNames[1][11] = "bladea08m";
        assetNames[1][12] = "bladea09m";
        assetNames[1][13] = "bladea10m";
        assetNames[1][14] = "bladea11m";
        assetNames[1][15] = "bladea12m";
        assetNames[1][16] = "bladea13m";
        assetNames[1][17] = "bladea14m";

        assetNames[2][0] = "bladea01";
        assetNames[2][1] = "bladea01";
        assetNames[2][2] = "bladea01";
        assetNames[2][3] = "bladea01";
        assetNames[2][4] = "bladea01";
        assetNames[2][5] = "bladea02";
        assetNames[2][6] = "bladeb01";
        assetNames[2][7] = "bladeb01";
        assetNames[2][8] = "bladea02";
        assetNames[2][9] = "bladea01";
        assetNames[2][10] = "bladea02";
        assetNames[2][11] = "bladeb01";
        assetNames[2][12] = "bladea02";
        assetNames[2][13] = "bladea01";
        assetNames[2][14] = "bladeb02";
        assetNames[2][15] = "bladeb03";
        assetNames[2][16] = "bladeb04";
        assetNames[2][17] = "bladea01";

        assetNames[3][0] = "bladea01m";
        assetNames[3][1] = "bladea01m";
        assetNames[3][2] = "bladea01m";
        assetNames[3][3] = "bladea01m";
        assetNames[3][4] = "bladea01m";
        assetNames[3][5] = "bladea02m";
        assetNames[3][6] = "bladeb01m";
        assetNames[3][7] = "bladeb01m";
        assetNames[3][8] = "bladea02m";
        assetNames[3][9] = "bladea01m";
        assetNames[3][10] = "bladea02m";
        assetNames[3][11] = "bladeb01m";
        assetNames[3][12] = "bladea02m";
        assetNames[3][13] = "bladea01m";
        assetNames[3][14] = "bladeb02m";
        assetNames[3][15] = "bladeb03m";
        assetNames[3][16] = "bladeb04m";
        assetNames[3][17] = "bladea01m";

        assetNames[4][0]  = "bladea01";
        assetNames[4][1]  = "bladea01";
        assetNames[4][2]  = "bladea01";
        assetNames[4][3]  = "bladea01";
        assetNames[4][4]  = "bladea01";
        assetNames[4][5]  = "bladea02";
        assetNames[4][6]  = "bladea03";
        assetNames[4][7]  = "bladea04";
        assetNames[4][8]  = "bladea05";
        assetNames[4][9]  = "bladec01";
        assetNames[4][10] = "bladea05";
        assetNames[4][11] = "bladea04";
        assetNames[4][12] = "bladea03";
        assetNames[4][13] = "bladea02";
        assetNames[4][14] = "bladeb02";
        assetNames[4][15] = "bladeb03";
        assetNames[4][16] = "bladeb04";
        assetNames[4][17] = "bladea01";

        assetNames[5][0]  = "bladea01m";
        assetNames[5][1]  = "bladea01m";
        assetNames[5][2]  = "bladea01m";
        assetNames[5][3]  = "bladea01m";
        assetNames[5][4]  = "bladea01m";
        assetNames[5][5]  = "bladea02m";
        assetNames[5][6]  = "bladea03m";
        assetNames[5][7]  = "bladea04m";
        assetNames[5][8]  = "bladea05m";
        assetNames[5][9]  = "bladec01m";
        assetNames[5][10] = "bladea05m";
        assetNames[5][11] = "bladea04m";
        assetNames[5][12] = "bladea03m";
        assetNames[5][13] = "bladea02m";
        assetNames[5][14] = "bladeb02m";
        assetNames[5][15] = "bladeb03m";
        assetNames[5][16] = "bladeb04m";
        assetNames[5][17] = "bladea01m";
    }

    public Blade getBlade() {
        return blade;
    }

    @Override
    public void act(float delta) {
        setOriginX(axisX * ScreenScale.getFactorScale().getScale());
        setOriginY(axisY * ScreenScale.getFactorScale().getScale());
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setOriginX(axisX * ScreenScale.getFactorScale().getScale());
        setOriginY(axisY * ScreenScale.getFactorScale().getScale());
        super.draw(batch, parentAlpha);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    public void setAxisPosition(float x, float y) {
        axisX = x;
        axisY = y;
    }

    public void updateRotation(){
        setRotation(getBlade().getDirection().getAngle());
    }
}