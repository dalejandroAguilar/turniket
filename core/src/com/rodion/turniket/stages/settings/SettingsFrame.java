package com.rodion.turniket.stages.settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.rodion.turniket.basics.BackgroundedLabelButton;
import com.rodion.turniket.basics.BackgroundedLayout;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.ImageButtonEntity;
import com.rodion.turniket.basics.LabelEntity;
import com.rodion.turniket.utilities.AssetManagerMaster;
import com.rodion.turniket.utilities.FontManagerMaster;
import com.rodion.turniket.utilities.SoundManagerMaster;

import java.io.FileNotFoundException;

public class SettingsFrame extends BackgroundedLayout {
    private LabelEntity title;
    private LabelEntity soundLabel;
    private LabelEntity musicLabel;
    private BackgroundedLabelButton resetButton;
    private BackgroundedLabelButton proVersionButton;
    private BackgroundedLabelButton moreGameButton;
    private ImageButtonEntity quitButton;
    private SwitchEntity soundSwitch;
    private SwitchEntity musicSwitch;
    private LabelEntity onLabel;
    private LabelEntity offLabel;

    public SettingsFrame(BasicStage basicStage) {
        super(basicStage);
        Table musicTable = new Table();
        prepareAssets();
        title = new LabelEntity("Settings", FontManagerMaster.helveticaWhiteStyle);
        soundLabel = new LabelEntity("Sound:", FontManagerMaster.nexaStyle);
        musicLabel = new LabelEntity("Music:", FontManagerMaster.nexaStyle);
        onLabel = new LabelEntity("On", FontManagerMaster.nexaStyle);
        offLabel = new LabelEntity("Off", FontManagerMaster.nexaStyle);

        final Preferences prefs = Gdx.app.getPreferences("turniket-preferences");

        soundSwitch = new SwitchEntity(prefs.getBoolean("sound", true)){
            @Override
            public void onAction() {
                super.onAction();
                prefs.putBoolean("sound", isOn);
                prefs.flush();
            }
        };
        musicSwitch = new SwitchEntity(prefs.getBoolean("music", true)){
            @Override
            public void onAction() {
                super.onAction();
                prefs.putBoolean("music", isOn);
                prefs.flush();
            }
        };

        quitButton = new ImageButtonEntity() {
            @Override
            public void setAssetAddress() {
                setAssetManager(AssetManagerMaster.settings);
                assetPath = "settings";
                assetName = "quit_button";
            }

            @Override
            public void onDown() {
                SoundManagerMaster.play("click");
                super.onDown();
            }

            @Override
            public void onAction() {
                super.onAction();
                onExit();
            }
        };
        quitButton.prepareAssets();

        resetButton = new BackgroundedLabelButton("Reset Game",
                FontManagerMaster.nexaStyle, basicStage) {
            @Override
            public void setAssetAddress() {
                setAssetManger(AssetManagerMaster.settings);
                setAssetPath("settings");
                setAssetName("large_button");
            }

            @Override
            public void onDown() {
                super.onDown();
                SoundManagerMaster.play("click");
            }

            @Override
            public void onAction() {
                onReset();
            }
        };
        resetButton.setFillParent(false);
//        resetButton.setColor(Color.BLUE);

        proVersionButton = new BackgroundedLabelButton("Pro Version",
                FontManagerMaster.nexaStyle, basicStage) {
            @Override
            public void setAssetAddress() {
                setAssetManger(AssetManagerMaster.settings);
                setAssetPath("settings");
                setAssetName("large_button");
            }

            @Override
            public void onDown() {
                super.onDown();
                SoundManagerMaster.play("click");
            }


            @Override
            public void onAction() throws FileNotFoundException {

            }
        };
        proVersionButton.setFillParent(false);
        moreGameButton = new BackgroundedLabelButton("More games",
                FontManagerMaster.nexaStyle, basicStage) {
            @Override
            public void setAssetAddress() {
                setAssetManger(AssetManagerMaster.settings);
                setAssetPath("settings");
                setAssetName("large_button");
            }

            @Override
            public void onDown() {
                super.onDown();
                SoundManagerMaster.play("click");
            }


            @Override
            public void onAction() throws FileNotFoundException {

            }
        };
//        setDebug(true);
        moreGameButton.setFillParent(false);
        add(quitButton).colspan(3).expand().right().top().pad(15).row();
        add(title).colspan(3).expand().top().row();
        add();
        add(onLabel);
        add(offLabel).row();
        add(soundLabel);
        add(soundSwitch).colspan(2).pad(10).expand().row();
        add(musicLabel);
        add(musicSwitch).colspan(2).pad(10).expand().row();
//        add(musicTable).row();
        add(resetButton).colspan(3).pad(10).expand().row();
        add(proVersionButton).colspan(3).pad(10).expand().row();
        add(moreGameButton).colspan(3).pad(10).expand();

    }

    @Override
    public void setAssetAddress() {
        setAssetManger(AssetManagerMaster.settings);
        setAssetPath("settings");
        setAssetName("settings_frame");
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        quitButton.resize(width, height);
        title.resize(width, height);
        soundLabel.resize(width, height);
        musicLabel.resize(width, height);
        resetButton.resize(width, height);
        proVersionButton.resize(width, height);
        moreGameButton.resize(width, height);
        soundSwitch.resize(width, height);
        musicSwitch.resize(width, height);
        onLabel.resize(width, height);
        offLabel.resize(width, height);
    }

    public void close() {

    }

    public void onExit() {

    }

    public void onReset() {

    }


    class SwitchEntity extends ImageButtonEntity {
        boolean isOn;

        public SwitchEntity(boolean isOn) {
            prepareAssets();
            this.isOn = isOn;
            if(!isOn){
                rotateBy(180);
            }
        }

        public void setAssetAddress() {
            setAssetManager(AssetManagerMaster.settings);
            assetPath = "settings";
            assetName = "switch";
        }

        @Override
        public void onAction() {
            super.onAction();
            setOrigin(Align.center);
            rotateBy(180);
            isOn = !isOn;
//            resize();
        }

        @Override
        public void updatePosition() {
            super.updatePosition();
            setOrigin(Align.center);
        }
    }
}