package com.rodion.turniket.screens.game.stages.previewStage;

import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.screens.game.stages.gameStage.TopMenuLayout;

public class PreviewLayout extends Layout {
    private TopMenuLayout topMenu;
    private BottomMenuLayout bottomMenu;

    public PreviewLayout(BasicStage basicStage)  {
        super(basicStage);
        setFillParent(true);
        topMenu = new TopMenuLayout(basicStage);
        bottomMenu = new BottomMenuLayout(basicStage){
            @Override
            public void onPlay() {
                super.onPlay();
                PreviewLayout.this.onPlay();
            }

            @Override
            public void onPrevious() {
                PreviewLayout.this.onPrevious();
            }

            @Override
            public void onNext() {
                PreviewLayout.this.onNext();
            }

        };

        add(topMenu).expandX().fillX().top().row();
        add().expand().row();
        add(bottomMenu).expandX().fillX().bottom();
//        setDebug(true);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        topMenu.resize(width, height);
        bottomMenu.resize(width, height);
    }

    public void onPlay(){

    }

    public void onPrevious(){

    }

    public void onNext(){

    }
}
