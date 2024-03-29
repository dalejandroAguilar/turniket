package com.rodion.turniket.screens.game.stages.gameStage.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.rodion.turniket.basics.BundleAnimationEntity;
import com.rodion.turniket.kernel.Token;
import com.rodion.turniket.kernel.constants.Direction;
import com.rodion.turniket.utilities.AssetManagerMaster;
import com.rodion.turniket.utilities.ColorManagerMaster;
import com.rodion.turniket.utilities.ScreenScale;
import com.rodion.turniket.utilities.SoundManagerMaster;

import java.util.Random;

public class TokenEntity extends BundleAnimationEntity {
    private Token token;
    private float axisX = 43.04054054f;
    private float axisY = 43.04054054f;
    private float lastX;
    private float lastY;

    public TokenEntity(Token token) {
        super(.02f, 4);
        prepareAssets();
        setOnPlay(false);
        this.token = token;
//        switch (token.getColor()) {
//            case Cyan:
//                setColor(Color.CYAN);
//                break;
//            case Magenta:
//                setColor(Color.MAGENTA);
//                break;
//            case Red:
//                setColor(Color.ORANGE);
//                break;
//            case Green:
//                setColor(Color.GREEN);
//                break;
//            default:
//                setColor(Color.DARK_GRAY);
//        }

        setColor(ColorManagerMaster.getColor(token.getColor()));

        this.token.addListener(new Token.Listener() {
            @Override
            public void onMove(final Direction direction, final Token.Status status) {
                setRotation(direction.getAngle() - 90);
                switch (status) {
                    case Move:
                        setSelectAnimation(0);
//                        sound2.play(1.0f, 0.8f + random.nextFloat() * (1.2f - 0.8f), 1.f);
                        SoundManagerMaster.play("move");
//                        System.out.println("Move");
                        setOnPlay(true);
                        break;

                    case BladeTokenCollision:
                        setSelectAnimation(1);
//                        sound4.play(3f, 0.9f + random.nextFloat() * (1.1f - 0.9f), 1.f);
                        SoundManagerMaster.play("bladeTokenCollision");
//                        System.out.println("bladeTokenCollision");
                        setOnPlay(true);
                        break;

                    case MoveAndRotate:
                        setSelectAnimation(0);
//                        sound.play(3f, 0.8f + random.nextFloat() * (1.2f - 0.8f), 1.f);
//                        System.out.println("MoveAndRotate");
                        SoundManagerMaster.play("moveAndRotate");
                        setOnPlay(true);
                        break;

                    case TokenCollision:
                        setSelectAnimation(3);
//                        sound3.play(3f, 0.9f + random.nextFloat() * (1.1f - 0.9f), 1.f);
//                        System.out.println("TokenCollision");
                        SoundManagerMaster.play("tokenCollision");
                        setOnPlay(true);
                        break;

                    case MoveAndFit:
                        setSelectAnimation(0);
//                        sound3.play(1.0f, 0.8f + random.nextFloat() * (1.2f - 0.8f), 1.f);
//                        System.out.println("Move");
                        SoundManagerMaster.play("moveAndFit");
                        setOnPlay(true);
                        break;

                    case Nothing:
                        System.out.println("Default");
                        onFinish();

                }

            }
        });

//        sound = Gdx.audio.newSound(Gdx.files.internal("sounds/pass.mp3"));
//        sound2 = Gdx.audio.newSound(Gdx.files.internal("sounds/table.wav"));
//        sound3 = Gdx.audio.newSound(Gdx.files.internal("sounds/sound-81.ogg"));
//        sound4 = Gdx.audio.newSound(Gdx.files.internal("sounds/sound-370.ogg"));

    }

    @Override
    public void onFinish() {
        setOnPlay(false);
        setRotation(0);
    }

    @Override
    public void setAssetAddress() {
        super.setAssetAddress();
        setAssetManager(AssetManagerMaster.game);
        assetPath = "game";
        assetNames = new String[4][17];
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
//        assetNames[0][17] = "tokena17";

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
//        assetNames[1][17] = "tokena00";

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
//        assetNames[2][17] = "tokena00";

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