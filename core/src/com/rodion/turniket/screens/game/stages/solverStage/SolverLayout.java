package com.rodion.turniket.screens.game.stages.solverStage;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.screens.game.layouts.TopMenuLayout;

public class SolverLayout extends Layout {
    private TopMenuLayout topMenu;
    private BottomMenuLayout bottomMenu;

    public SolverLayout(BasicStage basicStage) {
        super(basicStage);
        topMenu = new TopMenuLayout(basicStage);
        bottomMenu = new BottomMenuLayout(basicStage){
            @Override
            public void onBack() {
                super.onBack();
                 onHide();
            }

            @Override
            public void onPrevious() {
                super.onPrevious();
                SolverLayout.this.onPrevious();
            }

            @Override
            public void onNext() {
                super.onNext();
                SolverLayout.this.onNext();
            }
        };

        Table table = new Table();
        table.setFillParent(false);

        add(topMenu).expandX().fillX().top().row();
        add(table).expand().fill().row();
        add(bottomMenu).expandX().fillX().bottom();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        topMenu.resize(width, height);
        bottomMenu.resize(width, height);
    }

    public void onPrevious(){

    }

    public void onNext(){

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
