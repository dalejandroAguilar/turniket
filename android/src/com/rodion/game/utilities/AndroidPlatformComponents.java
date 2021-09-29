package com.rodion.game.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.rodion.turniket.utilities.Multiplatform;

public class AndroidPlatformComponents implements Multiplatform {
    @Override
    public FileHandle openFile(String file) {
//        return  Gdx.files.local(file) ; //new File(Gdx.files.path()+"maps\\" +  difficulty.name()); //File("maps\\" + difficulty.name());
            return  Gdx.files.internal(file) ; //new File(Gdx.files.path()+"maps\\" +  difficulty.name());
    }
}
