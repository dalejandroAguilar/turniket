package com.rodion.turniket.basics;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.rodion.turniket.utilities.ScreenScale;

public class LabelEntity extends Label {

    Label.LabelStyle[] style;

    public LabelEntity(CharSequence text, Label.LabelStyle[] style) {
        super(text, style[FactorScale.Normal.index]);
        this.style = style;
    }

     public void resize(int width, int height) {
        updatePosition();
        System.out.println(ScreenScale.getFactorScale().index);
        setStyle(style[ScreenScale.getFactorScale().index]);
    }

    public void updatePosition(){

    }

}