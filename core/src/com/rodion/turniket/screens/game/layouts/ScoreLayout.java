package com.rodion.turniket.screens.game.layouts;

import com.badlogic.gdx.graphics.Color;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.ImageEntity;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.utilities.AssetManagerMaster;

public class ScoreLayout extends Layout {
    private ImageEntity[] stars;

    public ScoreLayout(BasicStage basicStage) {
        super(basicStage);
        setFillParent(false);
        stars = new ImageEntity[3];
        for (int i = 0; i<3; i++){
            stars[i] = new ImageEntity() {
                @Override
                public void setAssetAddress() {
                    setAssetManager(AssetManagerMaster.game);
                    assetPath = "game";
                    assetName = "star";
                }
            };
            stars[i].prepareAssets();
            stars[i].setColor(Color.DARK_GRAY);
            add(stars[i]).pad(10);
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        for (ImageEntity star : stars)
            star.resize(width, height);
    }
}
