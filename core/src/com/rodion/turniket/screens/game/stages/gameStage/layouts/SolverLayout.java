package com.rodion.turniket.screens.game.stages.gameStage.layouts;


import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Align;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.ImageButtonEntity;
import com.rodion.turniket.basics.LabelEntity;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.screens.game.layouts.TopMenuLayout;
import com.rodion.turniket.utilities.AssetManagerMaster;
import com.rodion.turniket.utilities.ColorManagerMaster;
import com.rodion.turniket.utilities.FontManagerMaster;

public class SolverLayout extends Layout {
    private TopMenuLayout topMenu;
    private BottomMenuLayout bottomMenu;
    private LabelEntity solutionLabel;

    public SolverLayout(BasicStage basicStage) {
        super(basicStage);
        topMenu = new TopMenuLayout(basicStage){
            @Override
            public void onReturn() {
                super.onReturn();
                SolverLayout.this.onReturn();
                System.out.println("onReturn");
            }

            @Override
            public void onSettings() {
                super.onSettings();
                SolverLayout.this.onSettings();
            }
        };
        bottomMenu = new BottomMenuLayout(basicStage);
        solutionLabel = new LabelEntity("Solution", FontManagerMaster.nexaStyle);
        add(topMenu).expandX().fillX().top().row();
        add().expand().fill().row();
        add(solutionLabel).expand().row();
        add().expand().fill().row();
        add().expand().fill().row();
        add().expand().fill().row();
        add(bottomMenu).expandX().fillX().bottom();
//        setDebug(true);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        topMenu.resize(width, height);
        bottomMenu.resize(width, height);
    }

    public void onPrevious() {

    }

    public void onNext() {

    }

    public void onBack() {
//        hide();
    }
    public void onReturn(){

    }
    public void  onSettings(){

    }

    @Override
    public void act(float delta) {
        updateStatusPosition();
        super.act(delta);
    }

    public void updateStatusPosition() {

    }


    class BottomMenuLayout extends Layout {
        private ImageButtonEntity backButton;
        private ImageButtonEntity undoButton;
        private ImageButtonEntity redoButton;
        private LabelEntity stepLabel;

        public BottomMenuLayout(BasicStage basicStage) {
            super(basicStage);
            setFillParent(false);
            stepLabel = new LabelEntity("1/2", FontManagerMaster.nexaStyle);
            stepLabel.setAlignment(Align.center);
            backButton = new ImageButtonEntity() {
                @Override
                public void setAssetAddress() {
                    setAssetManager(AssetManagerMaster.game);
                    assetPath = "game";
                    assetName = "button_previous";
                }

                @Override
                public void onAction() {
                    super.onAction();
                    onBack();
                }
            };
            backButton.prepareAssets();

            undoButton = new ImageButtonEntity() {
                @Override
                public void setAssetAddress() {
                    setAssetManager(AssetManagerMaster.game);
                    assetPath = "game";
                    assetName = "button_undo";
                }

                @Override
                public void onAction() {
                    super.onAction();
                    onPrevious();
                }
            };
            undoButton.prepareAssets();

            redoButton = new ImageButtonEntity() {
                @Override
                public void setAssetAddress() {
                    setAssetManager(AssetManagerMaster.game);
                    assetPath = "game";
                    assetName = "button_redo";
                }

                @Override
                public void onAction() {
                    super.onAction();
                    onNext();
                }
            };
            redoButton.prepareAssets();

            add(backButton).left().padBottom(10).padTop(10).expandX();
            add(undoButton).padBottom(10).padTop(10).expandX();
            add(stepLabel).padBottom(10).padTop(10).expandX();
            add(redoButton).padBottom(10).padTop(10).expandX();
            setBackground(ColorManagerMaster.greenBg);
        }

        @Override
        public void resize(int width, int height) {
            super.resize(width, height);
            backButton.resize(width, height);
            undoButton.resize(width, height);
            redoButton.resize(width, height);
            stepLabel.resize(width, height);
            solutionLabel.resize(width, height);
        }

        @Override
        public void hide() {
            super.hide();
            System.out.println("onhide");
//            solutionLabel.setVisible(false);
            solutionLabel.getColor().a = 0;

        }

        @Override
        public void show() {
            super.show();
            solutionLabel.getColor().a = 1;
            solutionLabel.setAlpha(1);
//            solutionLabel.setVisible(true);
        }

        @Override
        public void onHide() {
            super.onHide();
            solutionLabel.setAlpha(0);

            solutionLabel.addAction(Actions.fadeOut(0.3f));
            solutionLabel.addAction(Actions.moveBy(10, 10, 1f));
        }

        @Override
        public void onShow() {
            super.onShow();
            solutionLabel.addAction(Actions.fadeIn(0.3f));
        }

        public void onNext() {
            SolverLayout.this.onNext();
        }

        public void onPrevious() {
            SolverLayout.this.onPrevious();
        }

        public void onBack() {
            SolverLayout.this.onBack();
        }

    }
}