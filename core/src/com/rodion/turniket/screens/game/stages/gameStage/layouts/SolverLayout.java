package com.rodion.turniket.screens.game.stages.gameStage.layouts;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.screens.game.layouts.TopMenuLayout;
import com.rodion.turniket.screens.game.stages.solverStage.BottomMenuLayout;

public class SolverLayout extends Layout {
    private TopMenuLayout topMenu;
    private com.rodion.turniket.screens.game.stages.solverStage.BottomMenuLayout bottomMenu;

    public SolverLayout(BasicStage basicStage) {
        super(basicStage);
        topMenu = new TopMenuLayout(basicStage);
        bottomMenu = new BottomMenuLayout(basicStage){
            @Override
            public void onBack() {
                super.onBack();
                onHide();
            }
        };

        Table table = new Table();
        table.setFillParent(false);
//        table.add(previousMark).pad(5).expandX().left();
//        table.add(nextMarK).pad(5).expandX().right();

//        add(topMenu).expandX().fillX().top().row();
        add(table).expand().fill().row();
        add(bottomMenu).expandX().fillX().bottom();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
//        topMenu.resize(width, height);
        bottomMenu.resize(width, height);
    }

    public void onHide(){
        addAction(Actions.sequence(Actions.fadeOut(0.5f),
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        setVisible(false);
                    }
                })
                )
        );
    }

    public void onShow(){
        setVisible(true);
        addAction(Actions.fadeIn(0.5f));
    }
}
