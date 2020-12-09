package com.rodion.turniket.basics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;

public class Layout extends Table implements Disposable {
    protected BasicStage basicStage;
    static final Vector2 dummyVector = new Vector2(0, 0);
    public Layout(BasicStage basicStage){
        this.basicStage = basicStage;
        setFillParent(true);
    }

    @Override
    public void dispose() {

    }

    public void resize(int width, int height){

    }

    public Vector2 getAbsPosition() {
        dummyVector.set(0,0);
        return localToStageCoordinates(dummyVector);
    }

    public Vector2 getAbsPosition(int align) {
        dummyVector.set(getX(align),getY(align));
        return localToStageCoordinates(dummyVector);
    }

    public BasicStage getParentStage() {
        return basicStage;
    }

    public void setParentStage(BasicStage basicStage) {
        this.basicStage = basicStage;
    }
}
