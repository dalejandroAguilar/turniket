package com.rodion.turniket.screens.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.rodion.turniket.basics.ImageEntity;
import com.rodion.turniket.kernel.Node;
import com.rodion.turniket.kernel.Token;
import com.rodion.turniket.kernel.constants.Direction;
import com.rodion.turniket.utilities.AssetManagerMaster;

public class TokenEntity extends ImageEntity {
    private Token token;

    public TokenEntity(Token token) {
        prepareAssets();
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
    }

    @Override
    public void setAssetAddress() {
        super.setAssetAddress();
        setAssetManager(AssetManagerMaster.game);
        assetPath = "game";
        assetName = "token";
    }


    public Token getToken() {
        return token;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setBounds(0, 0, getDrawable().getMinWidth(), getDrawable().getMinHeight());
        super.draw(batch, parentAlpha);
    }

    public void setToken(Token token) {
        this.token = token;
    }


}
