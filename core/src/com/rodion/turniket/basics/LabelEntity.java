package com.rodion.turniket.basics;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.rodion.turniket.utilities.ScreenScale;

public class LabelEntity extends Label {

    Label.LabelStyle[] style;
    static final Vector2 dummyVector = new Vector2(0, 0);


    public LabelEntity(CharSequence text, Label.LabelStyle[] style) {
        super(text, style[FactorScale.Normal.index]);
        this.style = style;
        setAlignment(Align.center);
    }

     public void resize(int width, int height) {
        updatePosition();
        System.out.println(ScreenScale.getFactorScale().index);
        setStyle(style[ScreenScale.getFactorScale().index]);
    }

    public void updatePosition(){


    }

    @Override

    public void draw(Batch batch, float parentAlpha) {
        updatePosition();
        super.draw(batch, parentAlpha);
    }

    public Vector2 getAbsPosition(int align) {
        dummyVector.set(getX(align), getY(align));
        return localToStageCoordinates(dummyVector);
    }
}