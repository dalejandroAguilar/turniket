package com.rodion.turniket.stages.message;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.rodion.turniket.basics.BasicScreen;
import com.rodion.turniket.basics.BasicStage;

public class MessageStage extends BasicStage {
    MessageLayout messageLayout;
    public MessageStage(Viewport viewport, BasicScreen basicScreen, String[] lines) {
        super(viewport, basicScreen);
        messageLayout = new MessageLayout(this, lines);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        messageLayout.resize(width, height);
    }
}
