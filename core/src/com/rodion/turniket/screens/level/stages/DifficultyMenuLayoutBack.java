package com.rodion.turniket.screens.level.stages;

import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.ImageButtonEntity;
import com.rodion.turniket.basics.LabelEntity;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.utilities.AssetManagerMaster;
import com.rodion.turniket.utilities.ColorManagerMaster;
import com.rodion.turniket.utilities.Difficulty;
import com.rodion.turniket.utilities.FontManagerMaster;

public class DifficultyMenuLayoutBack extends Layout {

    private ImageButtonEntity previousButton;
    private ImageButtonEntity nextButton;
    private LabelEntity level;
    private Difficulty difficulty;

    public DifficultyMenuLayoutBack(BasicStage basicStage, Difficulty difficulty) {
        super(basicStage);
        this.difficulty = difficulty;
        setFillParent(false);
        previousButton = new ImageButtonEntity() {
            @Override
            public void setAssetAddress() {
                setAssetManager(AssetManagerMaster.level);
                assetPath = "level";
                assetName = "button_previous";
            }
        };
        previousButton.prepareAssets();

        nextButton = new ImageButtonEntity() {
            @Override
            public void setAssetAddress() {
                setAssetManager(AssetManagerMaster.level);
                assetPath = "level";
                assetName = "button_next";
            }

        };
        nextButton.prepareAssets();

        level = new LabelEntity(difficulty.name(), FontManagerMaster.nexaStyle);
        Table levelFrame = new Table();
        levelFrame.add(level);
        levelFrame.setBackground(difficulty.getBgColor());
        setHeight(previousButton.getHeight());
        add(previousButton).left().padBottom(10).padTop(10);
        add(levelFrame).expandX().fillX();
        add(nextButton).right().padBottom(10).padTop(10);

//        Table table = new Table();
//        table.add(previousButton);
//        Stack stack = new Stack();
//        stack.layout();
//        stack.add(levelFrame);
//        stack.add(table);
//        Table stackTable = new Table();
//        stackTable.add(stack);
//        add(stackTable).padBottom(10).padTop(10).expand().fillX();

        previousButton.setVisible(false);
        nextButton.setVisible(false);
        setBackground(ColorManagerMaster.grayBg);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
//        previousButton.setWidth(0);
        previousButton.resize(width, height);
        level.resize(width, height);
        nextButton.resize(width, height);
    }

    public void onNext() {

    }

    public void onPrevious() {

    }
}