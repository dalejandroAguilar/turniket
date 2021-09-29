package com.rodion.turniket.screens.game.stages.gameStage.layouts;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.rodion.turniket.basics.AnimatedEntity;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.LabelEntity;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.screens.game.stages.gameStage.entities.LockEntity;
import com.rodion.turniket.utilities.AssetManagerMaster;
import com.rodion.turniket.utilities.ColorManagerMaster;
import com.rodion.turniket.utilities.FontManagerMaster;

public class LockLayout extends Layout {
    private LockEntity lockEntity;
    LabelEntity levelRequirementsLabel;
    LabelEntity starRequirementsLabel;
    private CheckEntity checkEntity1;
    private CheckEntity checkEntity2;

    public LockLayout(BasicStage basicStage) {
        super(basicStage);

        checkEntity1 = new CheckEntity() {
            @Override
            public void onEnd() {
                super.onEnd();
                checkEntity2.setOnPlay(true);
            }
        };
        final Table table1 = new Table();
        final Table table2 = new Table();
        checkEntity1.setColor(ColorManagerMaster.green);
        checkEntity2 = new CheckEntity() {
            @Override
            public void onEnd() {
                super.onEnd();
                table1.addAction(Actions.parallel(
                        Actions.moveBy(500, 0, 0.5f),
                        Actions.fadeOut(0.5f)
                ));
                table2.addAction(Actions.parallel(
                        Actions.moveBy(500, 0, 0.5f),
                        Actions.fadeOut(0.5f)
                ));
                lockEntity.setOnPlay(true);
            }
        };
        checkEntity2.setColor(ColorManagerMaster.green);

        checkEntity1.setOnPlay(false);
//        setDebug(true);


//         checkEntity1.setOnPlay(false);
        lockEntity = new LockEntity() {
            @Override
            public void updatePosition() {
                super.updatePosition();
                updateLockEntityPosition();
            }

            @Override
            public void onEnd() {
                addAction(Actions.parallel(
                        Actions.moveBy(500, 0, 0.5f),
                        Actions.fadeOut(0.5f)
                ));
                setInvalidated(true);
                System.out.println("next");
                LockLayout.this.onEnd();
            }
        };

        levelRequirementsLabel = new LabelEntity("You need complete the previous level",
                FontManagerMaster.nexaStyle);

        starRequirementsLabel = new LabelEntity("You need 41 stars",
                FontManagerMaster.nexaStyle);

        table1.add(checkEntity1).padRight(10);
        table1.add(levelRequirementsLabel);
        table2.add(checkEntity2).padRight(10);
        table2.add(starRequirementsLabel);

        add().expand().fill().row();
        add(table1).row();
        add(table2).row();
        add().expand().fill().row();
        add().expand().fill().row();
        add().expand().fill().row();

        lockEntity.setOnPlay(false);
    }

    public LockEntity getLockEntity() {
        return lockEntity;
    }

    @Override
    public void act(float delta) {
//        lockEntity.updatePosition();
        lockEntity.act(delta);
//        checkEntity1.act(delta);
        super.act(delta);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        lockEntity.resize(width, height);
        starRequirementsLabel.resize(width, height);
        levelRequirementsLabel.resize(width, height);
        checkEntity1.resize(width, height);
        checkEntity2.resize(width, height);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        lockEntity.draw(batch, parentAlpha);
//        checkEntity1.draw(batch, parentAlpha);
    }

    public void updateLockEntityPosition() {

    }

    public void onUnlock() {
//        lockEntity.setOnPlay(true);
        checkEntity1.setOnPlay(true);
    }

    public void onEnd() {

    }

    class CheckEntity extends AnimatedEntity {
        public CheckEntity() {
            super(0.02f);
            prepareAssets();
        }

        @Override
        public void setAssetAddress() {
            setAssetManager(AssetManagerMaster.game);
            assetPath = "game";
            assetNames = new String[11];
            for (int i = 0; i < 9; i++) {
                assetNames[i] = "check0" + (i + 1);
            }
            assetNames[9] = "check10";
            assetNames[10] = "check11";
        }
    }

    @Override
    public void hide() {
        super.hide();
        lockEntity.getColor().a = 0;
    }
}
