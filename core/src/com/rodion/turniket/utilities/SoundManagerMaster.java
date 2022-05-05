package com.rodion.turniket.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.util.Random;

public class SoundManagerMaster {
    static Sound clickSound;
    static Sound moveSound;
    static Sound bladeTokenCollisionSound;
    static Sound moveAndRotateSound;
    static Sound tokenCollisionSound;
    static Sound moveAndFitSound;
    static Music menuMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/safari_camp.mp3"));

    private static Random random = new Random();

    //    static Sound
    static final Preferences prefs = Gdx.app.getPreferences("turniket-preferences");

    public static void init() {
        clickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/sound-371.ogg"));
        moveSound = Gdx.audio.newSound(Gdx.files.internal("sounds/table.wav"));
        bladeTokenCollisionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/sound-370.ogg"));
        moveAndRotateSound = Gdx.audio.newSound(Gdx.files.internal("sounds/pass.mp3"));
        tokenCollisionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/sound-81.ogg"));
        moveAndFitSound = Gdx.audio.newSound(Gdx.files.internal("sounds/completed.wav"));
    }

    public static void play(String name) {
        if (prefs.getBoolean("sound", true)) {
            if (name.equals("click"))
                clickSound.play();
            if (name.equals("move"))
                moveSound.play(1.0f, 0.8f + random.nextFloat() * (1.2f - 0.8f), 1.f);
            if (name.equals("bladeTokenCollision"))
                bladeTokenCollisionSound.play(1.0f, 0.8f + random.nextFloat() * (1.2f - 0.8f), 1.f);
            if (name.equals("moveAndRotate"))
                moveAndRotateSound.play(1.0f, 0.8f + random.nextFloat() * (1.2f - 0.8f), 1.f);
            if (name.equals("tokenCollision"))
                tokenCollisionSound.play(1.0f, 0.8f + random.nextFloat() * (1.2f - 0.8f), 1.f);
            if (name.equals("moveAndFit"))
                moveAndFitSound.play();
        }


    }

    public static void playMusic(String name){
            menuMusic.setLooping(true);
        if(name.equals("menu")) {
            menuMusic.play();
        }
    }

    public static void stopMusic(String name){
        if(name.equals("menu")) {
            menuMusic.stop();
        }
    }

    public static void dispose() {
        clickSound.dispose();
        moveSound.dispose();
        bladeTokenCollisionSound.dispose();
        moveAndRotateSound.dispose();
        tokenCollisionSound.dispose();
        moveAndFitSound.dispose();
        menuMusic.dispose();
    }
}
