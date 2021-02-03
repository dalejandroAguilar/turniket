package com.rodion.turniket.screens.game.stages.gameStage.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Align;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.ImageEntity;
import com.rodion.turniket.basics.LabelEntity;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.utilities.AssetManagerMaster;
import com.rodion.turniket.utilities.ColorManagerMaster;
import com.rodion.turniket.utilities.FontManagerMaster;

public class LevelTitleBarEntity extends Layout {

    private ImageEntity left;
    private ImageEntity right;
    private ImageEntity center;
    private LabelEntity levelLabel;
    private LabelEntity number;


    public LevelTitleBarEntity(BasicStage basicStage) {
        super(basicStage);
        setFillParent(false);
        left = new ImageEntity() {
            @Override
            public void setAssetAddress() {
                setAssetManager(AssetManagerMaster.game);
                assetPath = "game";
                assetName = "left_title_bar";
            }
        };
        left.prepareAssets();
        left.setColor(ColorManagerMaster.green);

        right = new ImageEntity() {
            @Override
            public void setAssetAddress() {
                setAssetManager(AssetManagerMaster.game);
                assetPath = "game";
                assetName = "right_title_bar";
            }
        };


        right.prepareAssets();
        right.setColor(ColorManagerMaster.green);
        final Layout table = new Layout(getParentStage());
        levelLabel = new LabelEntity("Beginer", FontManagerMaster.nexaStyle);
        number =  new LabelEntity("11", FontManagerMaster.nexaStyle){
            @Override
            public void updatePosition() {
                super.updatePosition();
                setPosition(center.getX(Align.center),
                        center.getY(Align.center), Align.center);
            }
        };

        center = new ImageEntity() {
            @Override
            public void setAssetAddress() {
                setAssetManager(AssetManagerMaster.game);
                assetPath = "game";
                assetName = "token";
            }

            @Override
            public void updatePosition() {
                super.updatePosition();
                setPosition(table.getAbsPosition(Align.center).x,
                        table.getAbsPosition(Align.center).y, Align.center);
            }
        };
        center.prepareAssets();
        center.setColor(ColorManagerMaster.green);


        table.add(levelLabel).expand().left().pad(5);
        table.setFillParent(false);
        table.setBackground(ColorManagerMaster.greenBg);
        add(table).expandX().fillX();

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        center.draw(batch, parentAlpha);
        number.draw(batch, parentAlpha);
    }


    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        left.resize(width, height);
        right.resize(width, height);
        levelLabel.resize(width, height);
        center.resize(width, height);
        number.resize(width, height);
    }
}
