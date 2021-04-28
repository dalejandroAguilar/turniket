package com.rodion.turniket.screens.level.stages;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.screens.level.entities.LevelEntity;
import com.rodion.turniket.utilities.Difficulty;
import com.rodion.turniket.utilities.LevelManagerMaster;

import java.io.File;
import java.util.ArrayList;

public class PageLayout extends Layout {
    private TopMenuLayout topMenu;
    private DifficultyMenuLayoutBack changeLevelMenu;
    private ArrayList<LevelEntity> levels;
    private BottomMenuLayoutBack bottomMenu;

    public PageLayout(int page, ArrayList<FileHandle> files, BasicStage basicStage) {
        super(basicStage);
        setFillParent(true);
        Table levelsLayout = new Table();

        levels = new ArrayList<>();
        for(int i = 0; i < files.size(); i++){
            if( i % 3==0 && i>0)
                levelsLayout.row();
            int index = LevelManagerMaster.getIniForPage(page) + i;
            levels.add(
                    new LevelEntity(index, files.get(i), LevelManagerMaster.getDifficulty(page)
                            , getParentStage()) {
                        @Override
                        public void onAction() {
                            super.onAction();
                            onPickLevel();
                        }
                    }
            );
            levelsLayout.add(levels.get(i)).expand();
        }

        topMenu = new TopMenuLayout(getParentStage());
        changeLevelMenu = new DifficultyMenuLayoutBack(getParentStage(), LevelManagerMaster.getDifficulty(page));
        bottomMenu = new BottomMenuLayoutBack(getParentStage());
        add(topMenu).expandX().fillX().top().row();
        add(changeLevelMenu).expandX().fillX().top().row();
        add(levelsLayout).expand().fill().row();
        add(bottomMenu).expandX().fillX().bottom();
//        levelsLayout.addAction(Actions.scaleBy(2,2,1));
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        topMenu.resize(width, height);
        changeLevelMenu.resize(width, height);
        for (LevelEntity level : levels)
           level.resize(width, height);
        bottomMenu.resize(width, height);
    }

    public void onPickLevel(){
        System.out.println("onPick");
//         for (LevelEntity level : levels ) {
//            level.addAction(Actions.moveBy(1000,-1000,0.5f));
//        }
    }

    public ArrayList<LevelEntity> getLevels() {
        return levels;
    }
}