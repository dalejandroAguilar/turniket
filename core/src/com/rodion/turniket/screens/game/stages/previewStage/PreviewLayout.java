package com.rodion.turniket.screens.game.stages.previewStage;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.ImageEntity;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.screens.game.layouts.TopMenuLayout;
import com.rodion.turniket.utilities.AssetManagerMaster;

public class PreviewLayout extends Layout {
    private TopMenuLayout topMenu;
    private BottomMenuLayout bottomMenu;
    private ImageEntity previousMark;
    private ImageEntity nextMarK;

    public PreviewLayout(BasicStage basicStage)  {
        super(basicStage);
        setFillParent(true);
        topMenu = new TopMenuLayout(basicStage);
        bottomMenu = new BottomMenuLayout(basicStage){
            @Override
            public void onPlay() {
                super.onPlay();
                PreviewLayout.this.onPlay();
            }

            @Override
            public void onPrevious() {
                PreviewLayout.this.onPrevious();
            }

            @Override
            public void onNext() {
                PreviewLayout.this.onNext();
            }

        };

        previousMark = new ImageEntity() {
            @Override
            public void setAssetAddress() {
                setAssetManager(AssetManagerMaster.game);
                assetPath = "game";
                assetName = "previous_mark";
            }
        };
        previousMark.prepareAssets();
        previousMark.addAction(Actions.forever(Actions.sequence(
                Actions.moveBy(0,10,0.5f),
                Actions.moveBy(0,-10,0.5f))));

        nextMarK = new ImageEntity() {
            @Override
            public void setAssetAddress() {
                setAssetManager(AssetManagerMaster.game);
                assetPath = "game";
                assetName = "next_mark";
            }
        };
        nextMarK.prepareAssets();
        nextMarK.addAction(Actions.forever(Actions.sequence(
                Actions.moveBy(0,10,0.5f),
                Actions.moveBy(0,-10,0.5f))));

        Table table = new Table();
        table.setFillParent(false);
        table.add(previousMark).pad(5).expandX().left();
        table.add(nextMarK).pad(5).expandX().right();

        add(topMenu).expandX().fillX().top().row();
        add(table).expand().fill().row();
        add(bottomMenu).expandX().fillX().bottom();
//        setDebug(true);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        topMenu.resize(width, height);
        bottomMenu.resize(width, height);
        previousMark.resize(width, height);
        nextMarK.resize(width, height);
    }

    public void onPlay(){

    }

    public void onPrevious(){

    }

    public void onNext(){

    }
}
