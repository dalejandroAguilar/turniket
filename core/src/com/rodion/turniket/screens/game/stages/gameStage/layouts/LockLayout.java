package com.rodion.turniket.screens.game.stages.gameStage.layouts;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RelativeTemporalAction;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.rodion.turniket.basics.AnimatedEntity;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.LabelEntity;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.screens.game.stages.gameStage.entities.LockEntity;
import com.rodion.turniket.utilities.AssetManagerMaster;
import com.rodion.turniket.utilities.ColorManagerMaster;
import com.rodion.turniket.utilities.FontManagerMaster;
import com.rodion.turniket.utilities.Level;
import com.rodion.turniket.utilities.LevelManagerMaster;
import com.rodion.turniket.utilities.Requirement;

public class LockLayout extends Layout {
    private LockEntity lockEntity;
    LabelEntity levelRequirementsLabel;
    LabelEntity starRequirementsLabel;
    private CheckEntity checkBefore;
    private CheckEntity checkStars;
    private Level level;
    private boolean debug;

    public LockLayout(BasicStage basicStage, final Level level) {
        super(basicStage);
        debug = true;
        this.level = level;
        final Table table1 = new Table();
        final Table table2 = new Table();
        if (!debug) {
            checkBefore = new CheckEntity() {
                @Override
                public void onEnd() {
                    super.onEnd();
                    isChecked = true;
                    if (!starsCheck() && level.nStarsSatisfied()) {
                        checkStars.setOnPlay(true);
                    }
                    if (starsCheck()) {
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
                }
            };
            checkStars = new CheckEntity() {
                @Override
                public void onEnd() {
                    super.onEnd();
                    isChecked = true;
                    if (beforeLevelCheck()) {
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
                }
            };
        } else {
            checkBefore = new CheckEntity() {
                @Override
                public void onEnd() {
                    super.onEnd();
                    checkStars.setOnPlay(true);
                }
            };
            checkStars = new CheckEntity() {
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
        }

        checkBefore.setColor(ColorManagerMaster.green);

        checkStars.setColor(ColorManagerMaster.green);

        checkBefore.setOnPlay(false);
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

        int nStars = level.getRequirement().getStars();

        starRequirementsLabel = new LabelEntity("You need " + nStars + " stars",
                FontManagerMaster.nexaStyle);

        table1.add(checkBefore).padRight(10);
        table1.add(levelRequirementsLabel);
        table2.add(checkStars).padRight(10);
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
        checkBefore.resize(width, height);
        checkStars.resize(width, height);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        lockEntity.draw(batch, parentAlpha);
//        checkEntity1.draw(batch, parentAlpha);
    }

    public void updateLockEntityPosition() {

    }

//    public boolean onTryToUnlock() {
//        if (!level.isUnlocked()) {
//            if (!beforeLevelCheck()) {
//                if (LevelManagerMaster.getPreviousLevel().isUnlocked()) {
//
//                }
//            }
//        }
//        return true;
//    }

    public void onUnlock() {
////        lockEntity.setOnPlay(true);
//        System.out.println(LevelManagerMaster.getNstars());
//        System.out.println(level.getRequirement().getStars());
//        System.out.println(level.nStarsSatisfied());

        if (!debug) {
            if (!LevelManagerMaster.getLevel().isUnlocked())
                if (!beforeLevelCheck() && LevelManagerMaster.getPreviousLevel().isUnlocked()) {
                    checkBefore.setOnPlay(true);
                } else {
                    if (!starsCheck() && level.nStarsSatisfied())
                        checkStars.setOnPlay(true);
                }
        } else {
            System.out.println("CHECK BEFORE");
            checkBefore.setOnPlay(true);
        }
    }

    public void onEnd() {

    }

    @Override
    public void hide() {
        super.hide();
        lockEntity.getColor().a = 0;
    }

    class CheckEntity extends AnimatedEntity {
        public boolean isChecked;

        public CheckEntity() {
            super(0.02f);
            prepareAssets();
            isChecked = false;
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

    public boolean beforeLevelCheck() {
        return checkBefore.isChecked;
    }

    public boolean starsCheck() {
        return checkStars.isChecked;
    }
}