package com.rodion.turniket.screens.game.stages.gameStage.layouts;

import com.rodion.turniket.basics.BackgroundedLabelButton;
import com.rodion.turniket.basics.BackgroundedLayout;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.LabelEntity;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.utilities.AssetManagerMaster;
import com.rodion.turniket.utilities.ColorManagerMaster;
import com.rodion.turniket.utilities.FontManagerMaster;

public class ConfirmationSolutionLayout extends Layout {
    private PopUpLayout popUpLayout;
    private BottomMenu bottomMenu;

    public ConfirmationSolutionLayout(BasicStage basicStage) {
        super(basicStage);
        popUpLayout = new PopUpLayout(basicStage);
        bottomMenu = new BottomMenu(basicStage);
        add().expand().row();
        add().expand().row();
        add().expand().row();

        add(popUpLayout).expand().row();
        add().expand().row();

        add(bottomMenu).expandX().fillX().bottom();
    }

    public void onOk() {
        onHide();
    }

    public void onCancel() {
        onHide();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        popUpLayout.resize(width, height);
        bottomMenu.resize(width, height);
    }

    class PopUpLayout extends BackgroundedLayout {
        private LabelEntity line1;
        private LabelEntity line2;

        public PopUpLayout(BasicStage basicStage) {
            super(basicStage);
            setFillParent(false);
            line1 = new LabelEntity("Are you sure to leave from solution?", FontManagerMaster.nexaStyle);
            line2 = new LabelEntity("your progress dissappear", FontManagerMaster.nexaStyle);
            add(line1).row();
            add(line2);
        }

       @Override
        public void setAssetAddress() {
            setAssetManger(AssetManagerMaster.game);
            setAssetPath("game");
            setAssetName("popup_frame");
        }

        @Override
        public void resize(int width, int height) {
            super.resize(width, height);
            line1.resize(width, height);
            line2.resize(width, height);
        }
    }

    class BottomMenu extends Layout {
        private BackgroundedLabelButton okButton;
        private BackgroundedLabelButton cancelButton;

        public BottomMenu(BasicStage basicStage) {
            super(basicStage);
            setFillParent(false);
            setBackground(ColorManagerMaster.grayBg);
            okButton = new BackgroundedLabelButton("Ok",
                    FontManagerMaster.nexaStyle, basicStage) {
                @Override
                public void setAssetAddress() {
                    setAssetManger(AssetManagerMaster.game);
                    setAssetPath("game");
                    setAssetName("button_label_frame");
                }

                @Override
                public void onAction() {
                    onOk();
                }
            };
            cancelButton = new BackgroundedLabelButton("Cancel",
                    FontManagerMaster.nexaStyle, basicStage) {
                @Override
                public void setAssetAddress() {
                    setAssetManger(AssetManagerMaster.game);
                    setAssetPath("game");
                    setAssetName("button_label_frame");
                }

                @Override
                public void onAction() {
                    onCancel();
                }
            };
            add(okButton).padBottom(10).padTop(10).expand();
            add(cancelButton).padBottom(10).padTop(10).expand();
        }

        @Override
        public void resize(int width, int height) {
            super.resize(width, height);
            okButton.resize(width, height);
            cancelButton.resize(width, height);
        }

        public void onOk() {
            ConfirmationSolutionLayout.this.onOk();
        }

        public void onCancel() {
            ConfirmationSolutionLayout.this.onCancel();
        }
    }
}
