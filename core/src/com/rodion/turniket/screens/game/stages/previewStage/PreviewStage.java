package com.rodion.turniket.screens.game.stages.previewStage;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rodion.turniket.basics.BasicScreen;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.ImageEntity;
import com.rodion.turniket.utilities.ColorManagerMaster;
import com.rodion.turniket.utilities.ScreenShotManager;

import org.w3c.dom.Text;

public class PreviewStage extends BasicStage {
    private PreviewLayout previewLayout;
    public PreviewStage(Viewport viewport, BasicScreen basicScreen) {
        super(viewport, basicScreen);
        previewLayout = new PreviewLayout(this){
            @Override
            public void onPlay() {
                PreviewStage.this.onPlay();
            }

            @Override
            public void onPrevious() {
                super.onPrevious();
                PreviewStage.this.onPrevious();
            }

            @Override
            public void onNext() {
                super.onNext();
                PreviewStage.this.onNext();
            }

            @Override
            public void onUnlock() {
                super.onUnlock();
                PreviewStage.this.onUnlock();
            }

            @Override
            public void onReturn() {
                super.onReturn();
                PreviewStage.this.onReturn();
            }
        };
        addActor(previewLayout);

    }

    public void showUp(){
//        previewStage.showUp();
    }

    public void onContinue(){
    }

    public void onPlay(){

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        previewLayout.resize(width, height);
    }

    public void onPrevious(){

    }

    public void onNext(){

    }

    public void onUnlock(){

    }

    public void onReturn(){

    }

    public void onEnter(){

    }
}