package com.rodion.turniket.basics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;


public class BasicStage extends Stage {
    protected BasicScreen basicScreen;
    private boolean isVisible;

    public BasicStage(Viewport viewport, BasicScreen basicScreen) {
        super(viewport);
        this.basicScreen = basicScreen;
        isVisible = true;
    }

    public BasicStage(Viewport viewport, Batch batch, BasicScreen basicScreen) {
        super(viewport, batch);
        this.basicScreen = basicScreen;
    }

    public BasicScreen getParentScreen() {
        return basicScreen;
    }

    public void setParentScreen(BasicScreen basicScreen) {
        this.basicScreen = basicScreen;
    }

    public void show() {
        isVisible = true;
    }

    public void hide() {
        isVisible = false;
    }

    @Override
    public void act() {
        if (isVisible)
            super.act();
    }

    @Override
    public void act(float delta) {
        if (isVisible)
            super.act(delta);
    }

    @Override
    public void draw() {
        if (isVisible)
            super.draw();
    }

    public void onInput() {
        Gdx.input.setInputProcessor(this);
    }

    public void offInput() {
        Gdx.input.setInputProcessor(null);
    }

    public void resize(int width, int height) {
        getViewport().update(width, height, true);
    }


}