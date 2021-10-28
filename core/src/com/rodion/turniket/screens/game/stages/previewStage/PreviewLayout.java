package com.rodion.turniket.screens.game.stages.previewStage;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.ImageButtonEntity;
import com.rodion.turniket.basics.ImageEntity;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.screens.game.layouts.TopMenuLayout;
import com.rodion.turniket.utilities.AssetManagerMaster;
import com.rodion.turniket.utilities.ColorManagerMaster;

public class PreviewLayout extends Layout {
    private TopMenuLayout topMenu;
    private BottomMenuLayout bottomMenu;
    private ImageEntity previousMark;
    private ImageEntity nextMarK;

    public PreviewLayout(BasicStage basicStage) {
        super(basicStage);
        setFillParent(true);
        topMenu = new TopMenuLayout(basicStage){
            @Override
            public void onReturn() {
                super.onReturn();
                PreviewLayout.this.onReturn();
            }

            @Override
            public void onSettings() {
                super.onSettings();
                PreviewLayout.this.onSettings();
            }
        };
        bottomMenu = new BottomMenuLayout(basicStage);

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
                Actions.moveBy(0, 10, 0.5f),
                Actions.moveBy(0, -10, 0.5f))));

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
                Actions.moveBy(0, 10, 0.5f),
                Actions.moveBy(0, -10, 0.5f))));

        Table table = new Table();
        table.setFillParent(false);
        table.add(previousMark).pad(5).expandX().left();
        table.add(nextMarK).pad(5).expandX().right();

        add(topMenu).expandX().fillX().top().row();
        add(table).expand().fill().row();
        add(bottomMenu).expandX().fillX().bottom();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        topMenu.resize(width, height);
        bottomMenu.resize(width, height);
        previousMark.resize(width, height);
        nextMarK.resize(width, height);
    }

    public void onPlay() {

    }

    public void onPrevious() {

    }

    public void onNext() {

    }

    public void onUnlock() {

    }

    public void onReturn() {

    }

    public void onSettings(){

    }

    public void update(){
        topMenu.update();
    }

    class BottomMenuLayout extends Layout {
        private ImageButtonEntity previousButton;
        private ImageButtonEntity nextButton;
        private ImageButtonEntity playButton;
        private ImageButtonEntity unlockButton;

        public BottomMenuLayout(BasicStage basicStage) {
            super(basicStage);
            setFillParent(false);
            previousButton = new ImageButtonEntity() {
                @Override
                public void setAssetAddress() {
                    setAssetManager(AssetManagerMaster.game);
                    assetPath = "game";
                    assetName = "button_previous";
                }

                @Override
                public void onAction() {
                    super.onAction();
                    onPrevious();
                }
            };
            previousButton.prepareAssets();

            nextButton = new ImageButtonEntity() {
                @Override
                public void setAssetAddress() {
                    setAssetManager(AssetManagerMaster.game);
                    assetPath = "game";
                    assetName = "button_next";
                }

                @Override
                public void onAction() {
                    super.onAction();
                    onNext();
                }
            };
            nextButton.prepareAssets();

            playButton = new ImageButtonEntity() {
                @Override
                public void setAssetAddress() {
                    setAssetManager(AssetManagerMaster.game);
                    assetPath = "game";
                    assetName = "button_play";
                }

                @Override
                public void onAction() {
                    super.onAction();
                    onPlay();
                }
            };
            playButton.prepareAssets();

            unlockButton = new ImageButtonEntity() {
                @Override
                public void setAssetAddress() {
                    setAssetManager(AssetManagerMaster.game);
                    assetPath = "game";
                    assetName = "button_play";
                }

                @Override
                public void onAction() {
                    super.onAction();
                    onUnlock();
                }
            };
            unlockButton.prepareAssets();

            add(previousButton).left().padBottom(10).padTop(10).expandX();
            add(playButton).padBottom(10).padTop(10).expandX();
            add(unlockButton).padBottom(10).padTop(10).expandX();

            add(nextButton).right().padBottom(10).padTop(10).expandX();

            setBackground(ColorManagerMaster.grayBg);
        }

        @Override
        public void resize(int width, int height) {
            super.resize(width, height);
            previousButton.resize(width, height);
            nextButton.resize(width, height);
            playButton.resize(width, height);
            unlockButton.resize(width, height);
        }

        public void onContinue() {
        }

        public void onPlay() {
            PreviewLayout.this.onPlay();
        }

        public void onNext() {
            PreviewLayout.this.onNext();
        }

        public void onPrevious() {
            PreviewLayout.this.onPrevious();
        }

        public void onUnlock() {
            PreviewLayout.this.onUnlock();
        }
    }
}
