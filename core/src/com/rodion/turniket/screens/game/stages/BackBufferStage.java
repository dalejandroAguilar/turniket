package com.rodion.turniket.screens.game.stages;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rodion.turniket.basics.BasicScreen;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.utilities.ScreenShotManager;

public class BackBufferStage extends BasicStage {
    private Pixmap pixmap;
    public BackBufferStage(Viewport viewport, BasicScreen basicScreen) {
        super(viewport, basicScreen);
    }

    public void onInit(){
        pixmap = ScreenShotManager.getSS();
//        Sprite sprite = new Sprite(pixmap);
//        sprite.flip(false,true);
        Image bg = new Image(new Texture(pixmap));
        Table table = new Table();
        table.setFillParent(false);
        table.add(bg);
        addActor(bg);
    }

    public BackBufferStage(Viewport viewport, Batch batch, BasicScreen basicScreen) {
        super(viewport, batch, basicScreen);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void draw() {
        super.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
        pixmap.dispose();
    }
}
