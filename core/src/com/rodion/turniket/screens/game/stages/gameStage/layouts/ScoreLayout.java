package com.rodion.turniket.screens.game.stages.gameStage.layouts;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.ImageEntity;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.utilities.AssetManagerMaster;
import com.rodion.turniket.utilities.Level;

public class ScoreLayout extends Layout {
    private ImageEntity[] stars;
    private Level level;


    public ScoreLayout(BasicStage basicStage, Level level) {
        super(basicStage);
        this.level = level;

        setFillParent(false);
        int nStars = level.getStars();
        stars = new ImageEntity[3];
        for (int i = 0; i < 3; i++) {
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

        for (int i = 0; i < nStars; i++)
            stars[i].setColor(Color.YELLOW);
    }

    public void onWin() {
        for (int i = 0; i < 3; i++)
            stars[i].addAction(Actions.sequence(
                    Actions.delay(0.4f * i),
                    Actions.parallel(
                            Actions.scaleTo(5f, 5f, 0.2f),
                            Actions.scaleTo(1f, 1f, 0.2f),
                            Actions.color(Color.YELLOW, 0.4f)
                    )
            ));
    }

    public void setToPreview() {
        if (stars[0].getColor().a == 0)
            onShow();
        int nStars = level.getStars();
        for (int i = 0; i < nStars; i++)
            stars[i].addAction(Actions.color(Color.YELLOW, 0.2f));
    }

    public void onPlay() {
        if (stars[0].getColor().a == 0)
            onShow();
        int nStars = level.getStars();
        System.out.println("Nstars " + nStars);
        for (int i = 0; i < nStars; i++)
            stars[i].addAction(Actions.color(Color.GRAY, 0.2f));
//        stars[0].addAction(Actions.color(Color.GRAY, 0.2f));
//        stars[1].addAction(Actions.color(Color.GRAY, 0.2f));
//        stars[2].addAction(Actions.color(Color.DARK_GRAY, 0.2f));
    }

    public void onHint() {
        for (ImageEntity star : stars)
            star.addAction(Actions.fadeOut(.5f));
    }

    @Override
    public void onShow() {
//        super.onShow();
        for (ImageEntity star : stars)
            star.addAction(Actions.fadeIn(.5f));
    }

    @Override
    public void onHide() {
        for (ImageEntity star : stars)
            star.addAction(Actions.fadeOut(.5f));
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        for (ImageEntity star : stars)
            star.resize(width, height);
    }
}