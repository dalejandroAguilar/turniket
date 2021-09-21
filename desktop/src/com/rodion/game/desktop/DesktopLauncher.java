package com.rodion.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.rodion.turniket.MainGame;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 700;
        config.height = 950;
//        config.fullscreen = true;
        new LwjglApplication(new MainGame(), config);
//        System.exit(0);
    }
}
