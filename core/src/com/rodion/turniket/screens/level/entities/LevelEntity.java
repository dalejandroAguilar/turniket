package com.rodion.turniket.screens.level.entities;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.ImageEntity;
import com.rodion.turniket.basics.LabelEntity;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.utilities.AssetManagerMaster;
import com.rodion.turniket.utilities.Difficulty;
import com.rodion.turniket.utilities.FontManagerMaster;
import com.rodion.turniket.utilities.Level;
import com.rodion.turniket.utilities.LevelManagerMaster;

public class LevelEntity extends Layout {
    private BoardEntity board;
    private ImageEntity frame;
    private ImageEntity padlock;
    private ImageEntity[] stars;
    private ImageEntity numberFrame;
    private LabelEntity numberLevel;
    private Difficulty difficulty;
    private int index;
    private boolean isUnlocked;

    public LevelEntity(int index, Level level, Difficulty difficulty, BasicStage basicStage) {
        super(basicStage);
        this.difficulty = difficulty;
        this.index = index;
        setFillParent(false);
        Stack stack = new Stack();
        Table starTable = new Table();
        starTable.setFillParent(false);
        Table numberTable = new Table();
        numberTable.setFillParent(false);
        Table lockTable = new Table();
        stars = new ImageEntity[3];
        int nStars = level.getStars();
        isUnlocked = level.isUnlocked();

        for (int i = 0; i < 3; i++) {
            stars[i] = new ImageEntity() {
                @Override
                public void setAssetAddress() {
                    setAssetManager(AssetManagerMaster.level);
                    assetPath = "level";
                    assetName = "little_star";
                }
            };
            stars[i].prepareAssets();
            stars[i].setColor(Color.DARK_GRAY);
            starTable.add(stars[i]).top().expandY();
            starTable.add();
        }

        for (int i = 0; i < nStars; i++)
            stars[i].setColor(Color.YELLOW);

        board = new BoardEntity(level.getMap(), basicStage);
        board.setTouchable(Touchable.disabled);
        frame = new ImageEntity() {
            @Override
            public void setAssetAddress() {
                setAssetManager(AssetManagerMaster.level);
                assetPath = "level";
                assetName = "level_frame";
            }
        };
        frame.prepareAssets();
        frame.setColor(difficulty.getColor());

        numberFrame = new ImageEntity() {
            @Override
            public void setAssetAddress() {
                setAssetManager(AssetManagerMaster.level);
                assetPath = "level";
                assetName = "number_level_frame";
            }

            @Override
            public void updatePosition() {
                setPosition(frame.getAbsX(Align.topLeft), frame.getAbsY(Align.topLeft), Align.topLeft);
            }
        };
        numberFrame.prepareAssets();

        padlock = new ImageEntity()
        {
            @Override
            public void setAssetAddress() {
                setAssetManager(AssetManagerMaster.level);
                assetPath = "level";
                assetName = "padlock";
            }
        };
        padlock.prepareAssets();
        lockTable.add(padlock);

        numberLevel = new LabelEntity(Integer.toString(index + 1), FontManagerMaster.helveticaWhiteStyle) {
            @Override
            public void updatePosition() {
                setPosition(numberFrame.getX(Align.center), numberFrame.getY(Align.center), Align.center);
            }
        };

        numberLevel.setAlignment(Align.center);
        stack.add(frame);
        stack.add(starTable);
        stack.add(board);
        stack.add(lockTable);
        add(stack);


        final Color difficultyColor = difficulty.getColor();

        setTouchable(Touchable.enabled);
        addListener(new ClickListener() {
            boolean isPressed;
            boolean isClicked;

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("pointer " + pointer);
                if (button == 0) {
                    isPressed = true;
                    isClicked = false;
                    frame.addAction(Actions.color(Color.GRAY, .2f));
                    frame.addAction(Actions.rotateTo(2, .2f));
                }
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Touch up pointer " + pointer);
                if (button == 0) {
                    if (isPressed) {
                        onBeginAction();
                        frame.addAction(Actions.sequence(
                                Actions.parallel(
                                        Actions.color(difficultyColor, 0.2f),
                                        Actions.rotateTo(0, 0.2f)
                                ),
                                Actions.run(
                                        new Runnable() {
                                            @Override
                                            public void run() {
                                                onAction();
                                            }
                                        }
                                )
                        ));
                    } else {
                        frame.addAction(Actions.color(difficultyColor, 0.2f));
                        frame.addAction(Actions.rotateTo(0, 0.2f));
                    }
                }
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                super.exit(event, x, y, pointer, toActor);
                frame.addAction(Actions.rotateTo(0, .2f));
                frame.addAction(Actions.color(difficultyColor, .2f));
                isPressed = false;
            }
        });

        setLockedStatus();

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        numberFrame.draw(batch, parentAlpha);
        numberLevel.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        numberFrame.act(delta);
        numberLevel.act(delta);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        board.resize(width, height);
        frame.resize(width, height);
        for (ImageEntity star : stars)
            star.resize(width, height);
        numberFrame.resize(width, height);
        numberLevel.resize(width, height);
        padlock.resize(width, height);
    }

    public void onBeginAction() {

    }

    public void setLockedStatus() {
        if (isUnlocked) {
            padlock.getColor().a=0;
        } else {
            board.hide();
        }
    }

//    public void set(){
//
//    }

    public void onAction() {
        System.out.println("onAction" + index);
        LevelManagerMaster.goToLevel(index);
    }
}
