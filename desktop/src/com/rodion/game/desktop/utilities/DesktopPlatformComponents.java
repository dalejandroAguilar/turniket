package com.rodion.game.desktop.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.rodion.turniket.utilities.Multiplatform;

public class DesktopPlatformComponents implements Multiplatform {
    @Override
    public FileHandle openFile(String file) {
        return  Gdx.files.local(file) ; //new File(Gdx.files.path()+"maps\\" +  difficulty.name()); //File("maps\\" + difficulty.name());
//            FileHandle dir = Gdx.files.internal("maps/"+  difficulty.name()) ; //new File(Gdx.files.path()+"maps\\" +  difficulty.name()); //File("maps\\" + difficulty.name());;
    }
}
