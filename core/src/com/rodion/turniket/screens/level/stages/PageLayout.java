package com.rodion.turniket.screens.level.stages;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.screens.level.entities.LevelEntity;

import java.io.File;
import java.util.ArrayList;

public class PageLayout extends Layout {
    private TopMenuLayout topMenu;
    private DifficultyMenuLayoutBack changeLevelMenu;
    private ArrayList<LevelEntity> levels;
    private BottomMenuLayout bottomMenu;

    public PageLayout(ArrayList<File> files, BasicStage basicStage) {
        super(basicStage);
        setFillParent(true);
        Table levelsLayout = new Table();

        levels = new ArrayList<>();
        for(int i = 0; i < files.size(); i++){
            if( i%3==0 && i>0)
                levelsLayout.row();
            levels.add(new LevelEntity(files.get(i) ,getParentStage()));
            levelsLayout.add(levels.get(i)).expand();
        }

        topMenu = new TopMenuLayout(getParentStage());
        changeLevelMenu = new DifficultyMenuLayoutBack(getParentStage());
        bottomMenu = new BottomMenuLayout(getParentStage());
        add(topMenu).expandX().fillX().top().row();
        add(changeLevelMenu).expandX().fillX().top().row();
        add(levelsLayout).expand().fill().row();
        add(bottomMenu).expandX().fillX().bottom();
        levelsLayout.moveBy(100,0);
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
}
