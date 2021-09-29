package com.rodion.turniket.basics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.viewport.Viewport;

public class BlackStage extends BasicStage {
    private  Texture blackTexture;
    private Image image;
    public BlackStage(Viewport viewport, BasicScreen screen) {
        super(viewport, screen);
        blackTexture = getTexture();
        image = new Image(new TextureRegion(blackTexture));
        image.setSize(getWidth(), getHeight());
        image.setOrigin(getWidth() / 2, getHeight() / 2);
        image.setColor(Color.BLACK);
        addActor(image);
//        image.debug();
    }

    public static Texture getTexture() {
        Pixmap pixmap;
        try {
            pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        } catch (GdxRuntimeException e) {
            pixmap = new Pixmap(1, 1, Pixmap.Format.RGB565);
        }
        pixmap.setColor(Color.WHITE);
        pixmap.drawRectangle(0, 0, 1, 1);
        return new Texture(pixmap);
    }

    public void resize(int width, int height) {
        super.resize(width, height);
        image.setSize(getWidth(), getHeight());
        image.setOrigin(getWidth() / 2, getHeight() / 2);
//        getViewport().update(width, height, true);

    }

    public void onShowing() {
        addAction(
                Actions.sequence(
                        Actions.fadeIn(.2f),
                        Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                onShow();

                            }
                        })
                )
        );
    }

    public void onHiding() {
        addAction(
                Actions.sequence(
                        Actions.fadeOut(.2f),
                        Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                onHide();
                            }
                        })
                )
        );
    }

    public void onShow() {
        System.out.println("onShow");
    }

    public void onHide() {

    }

    @Override
    public void dispose() {
        super.dispose();
        blackTexture.dispose();
    }
}