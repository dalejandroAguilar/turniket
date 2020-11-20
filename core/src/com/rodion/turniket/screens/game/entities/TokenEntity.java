package com.rodion.turniket.screens.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.rodion.turniket.basics.BundleAnimationEntity;
import com.rodion.turniket.kernel.Token;
import com.rodion.turniket.kernel.constants.Direction;
import com.rodion.turniket.utilities.AssetManagerMaster;
import com.rodion.turniket.utilities.ScreenScale;

public class TokenEntity extends BundleAnimationEntity {
    private Token token;
    float axisX = 43.04054054f;
    float axisY = 43.04054054f;
    float lastX;
    float lastY;

    public TokenEntity(Token token) {
        super(.02f, 4);
        prepareAssets();
        setOnPlay(false);
        this.token = token;
        switch (token.getColor()) {
            case Cyan:
                setColor(Color.CYAN);
                break;
            case Magenta:
                setColor(Color.MAGENTA);
                break;
            case Red:
                setColor(Color.ORANGE);
                break;
            case Green:
                setColor(Color.GREEN);
                break;
            default:
                setColor(Color.DARK_GRAY);
        }

        this.token.addListener(new Token.Listener() {
            @Override
            public void onMove(final Direction direction, final Token.Status status) {
                System.out.println("token move dir" + direction);
                addAction(
                        Actions.sequence(
                                Actions.parallel(
                                        Actions.run(
                                                new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        setRotation(direction.getAngle() - 90);
                                                        switch (status) {
                                                            case Ok:
                                                                setSelectAnimation(0);
                                                                break;
                                                            case BladeTokenCollision:
                                                                setSelectAnimation(1);
                                                                break;

                                                            case TokenCollision:
                                                                setSelectAnimation(3);
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
                                                setOnPlay(false);
                                                setRotation(0);
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
        assetNames = new String[4][18];
        assetNames[0][0] = "tokena00";
        assetNames[0][1] = "tokena01";
        assetNames[0][2] = "tokena02";
        assetNames[0][3] = "tokena03";
        assetNames[0][4] = "tokena04";
        assetNames[0][5] = "tokena05";
        assetNames[0][6] = "tokena06";
        assetNames[0][7] = "tokena07";
        assetNames[0][8] = "tokena08";
        assetNames[0][9] = "tokena09";
        assetNames[0][10] = "tokena10";
        assetNames[0][11] = "tokena11";
        assetNames[0][12] = "tokena12";
        assetNames[0][13] = "tokena13";
        assetNames[0][14] = "tokena14";
        assetNames[0][15] = "tokena15";
        assetNames[0][16] = "tokena16";
        assetNames[0][17] = "tokena17";

        assetNames[1][0] = "tokena00";
        assetNames[1][1] = "tokena01";
        assetNames[1][2] = "tokena02";
        assetNames[1][3] = "tokena03";
        assetNames[1][4] = "tokena04";
        assetNames[1][5] = "tokena05";
        assetNames[1][6] = "tokenb01";
        assetNames[1][7] = "tokenb01";
        assetNames[1][8] = "tokena05";
        assetNames[1][9] = "tokena04";
        assetNames[1][10] = "tokena05";
        assetNames[1][11] = "tokenb01";
        assetNames[1][12] = "tokena05";
        assetNames[1][13] = "tokena04";
        assetNames[1][14] = "tokena03";
        assetNames[1][15] = "tokena02";
        assetNames[1][16] = "tokena01";
        assetNames[1][17] = "tokena00";

        assetNames[2][0] = "tokena00";
        assetNames[2][1] = "tokena01";
        assetNames[2][2] = "tokena02";
        assetNames[2][3] = "tokena03";
        assetNames[2][4] = "tokena04";
        assetNames[2][5] = "tokena05";
        assetNames[2][6] = "tokenc01";
        assetNames[2][7] = "tokenc02";
        assetNames[2][8] = "tokenc03";
        assetNames[2][9] = "tokenc04";
        assetNames[2][10] = "tokenc02";
        assetNames[2][11] = "tokenc01";
        assetNames[2][12] = "tokena02";
        assetNames[2][13] = "tokena04";
        assetNames[2][14] = "tokena03";
        assetNames[2][15] = "tokena02";
        assetNames[2][16] = "tokena01";
        assetNames[2][17] = "tokena00";

        assetNames[3][0] = "tokena00";
        assetNames[3][1] = "tokena01";
        assetNames[3][2] = "tokena02";
        assetNames[3][3] = "tokena03";
        assetNames[3][4] = "tokena04";
        assetNames[3][5] = "tokena05";
        assetNames[3][6] = "tokena06";
        assetNames[3][7] = "tokena07";
        assetNames[3][8] = "tokend01";
        assetNames[3][9] = "tokend01";
        assetNames[3][10] = "tokena07";
        assetNames[3][11] = "tokena06";
        assetNames[3][12] = "tokena05";
        assetNames[3][13] = "tokena04";
        assetNames[3][14] = "tokena03";
        assetNames[3][15] = "tokena02";
        assetNames[3][16] = "tokena01";
        assetNames[3][17] = "tokena00";
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    @Override
    public void act(float delta) {
        setOriginX(axisX * ScreenScale.getFactorScale().getScale());
        setOriginY(axisY * ScreenScale.getFactorScale().getScale());
        super.act(delta);
        updatePosition();
        lastX = getX();
        lastY = getY();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    public float getLastX() {
        return lastX;
    }

    public float getLastY() {
        return lastY;
    }
}