package com.rodion.turniket.screens.game;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.rodion.turniket.kernel.constants.Direction;

public class GameInput implements GestureDetector.GestureListener {
    public GameInput() {

    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
//        System.out.println("Tap");
//        gameScreen.onAction(CENTER);
        return true;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        if(button==0) {
            if (Math.abs(velocityX) > Math.abs(velocityY))
                if (velocityX > 0)
                    onAction(Direction.Right);
                else
                    onAction(Direction.Left);
            else if (Math.abs(velocityX) < Math.abs(velocityY))
                if (velocityY > 0)
                    onAction(Direction.Down);
                else
                    onAction(Direction.Up);
            else
                return false;
        }
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {
    }

    public void onAction(Direction direction){


    }
}
