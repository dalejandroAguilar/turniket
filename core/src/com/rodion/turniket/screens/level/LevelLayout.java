package com.rodion.turniket.screens.level;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.screens.level.entities.BoardEntity;
import com.rodion.turniket.screens.level.entities.LevelEntity;
import com.rodion.turniket.utilities.LevelManagerMaster;

import java.io.File;

public class LevelLayout extends Layout {
    private TopMenuLayout topMenu;
    private ChangeLevelMenuLayout changeLevelMenu;
    private LevelEntity[][] levels;
    private BottomMenuLayout bottomMenu;
    private LevelsBook levelsBook;

    public LevelLayout(BasicStage basicStage) {
        super(basicStage);
        setFillParent(true);
        Table levelsLayout = new Table();


        levels = new LevelEntity[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                File file = LevelManagerMaster.getLevel();
                levels[i][j] = new LevelEntity(file ,getParentStage());
                levelsLayout.add(levels[i][j]).expand();
            }
            levelsLayout.row();
        }

        levelsBook = new LevelsBook();
        topMenu = new TopMenuLayout(getParentStage());
        changeLevelMenu = new ChangeLevelMenuLayout(getParentStage());
        bottomMenu = new BottomMenuLayout(getParentStage());
        add(topMenu).expandX().fillX().top().row();
        add(changeLevelMenu).expandX().fillX().top().row();
        add(levelsLayout).expand().fill().row();
        add(bottomMenu).expandX().fillX().bottom();
        levelsLayout.moveBy(100,0);
//        setDebug(true);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        topMenu.resize(width, height);
        changeLevelMenu.resize(width, height);
        levelsBook.resize(width,height);
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                levels[i][j].resize(width, height);
        bottomMenu.resize(width, height);

    }
}
