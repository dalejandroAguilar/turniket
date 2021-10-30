package com.rodion.turniket.stages.settings;

import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.Layout;

public class SettingsLayout extends Layout{
    SettingsFrame popUp;
    public SettingsLayout(BasicStage basicStage) {
        super(basicStage);
        popUp = new SettingsFrame(basicStage){
            @Override
            public void onExit() {
                super.onExit();
                SettingsLayout.this.onExit();
            }

            @Override
            public void onReset() {
                super.onReset();
                SettingsLayout.this.onReset();

            }
        };
        setFillParent(true);
        add(popUp);
        popUp.setFillParent(false);
    }

    public void onExit(){
    }

    public void onReset(){

    }

    public void close(){
        popUp.close();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        popUp.resize(width, height);
    }
}