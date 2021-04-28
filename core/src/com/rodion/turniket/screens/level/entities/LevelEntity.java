package com.rodion.turniket.screens.level.entities;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.rodion.turniket.basics.BackgroundedLayout;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.ImageEntity;
import com.rodion.turniket.basics.LabelEntity;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.utilities.AssetManagerMaster;
import com.rodion.turniket.utilities.ColorManagerMaster;
import com.rodion.turniket.utilities.Difficulty;
import com.rodion.turniket.utilities.FontManagerMaster;
import com.rodion.turniket.utilities.LevelManagerMaster;

import java.io.File;

public class LevelEntity extends Layout {
    private BoardEntity board;
    private ImageEntity frame;
    private ImageEntity[] stars;
    private ImageEntity numberFrame;
    private LabelEntity numberLevel;
    private Difficulty difficulty;
    private int index;

    public LevelEntity(int index, FileHandle file, Difficulty difficulty, BasicStage basicStage) {
        super(basicStage);
        this.difficulty = difficulty;
        this.index = index;
        setFillParent(false);
        Stack stack = new Stack();
        Table starTable = new Table();
        starTable.setFillParent(false);
        Table numberTable = new Table();
        numberTable.setFillParent(false);
        stars = new ImageEntity[3];

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
            stars[i].setColor(Color.YELLOW);
            starTable.add(stars[i]).top().expandY();
            starTable.add();
        }

        board = new BoardEntity(file, basicStage);
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
        add(stack);


        final  Color difficultyColor = difficulty.getColor();

        setTouchable(Touchable.enabled);
        addListener(new ClickListener() {
            boolean isPressed;
            boolean isClicked;

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (button == 0) {
                    isPressed = true;
                    isClicked = false;
                    frame.addAction(Actions.color(Color.GRAY, .2f));
                }
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (button == 0) {
                    frame.addAction(Actions.color(difficultyColor, .2f));
                    if (isPressed) {
                        onAction();
                    }
                }
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                if (pointer == 0) {
//                                System.out.println("dragged");
                    if (!isOver() && isPressed) {
                        frame.addAction(Actions.color(difficultyColor, .2f));
                        isPressed = false;
                    }
                }
            }
        });
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
    }

    public void onAction() {
        System.out.println("onAction" + index);
        LevelManagerMaster.goToLevel(index);
    }
}
