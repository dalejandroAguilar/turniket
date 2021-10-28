package com.rodion.turniket.stages.message;

import com.rodion.turniket.basics.BackgroundedLabelButton;
import com.rodion.turniket.basics.BackgroundedLayout;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.LabelEntity;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.utilities.AssetManagerMaster;
import com.rodion.turniket.utilities.ColorManagerMaster;
import com.rodion.turniket.utilities.FontManagerMaster;

public class MessageLayout extends Layout {
    private Message message;
    private BottomMenu bottomMenu;

    public MessageLayout(BasicStage basicStage, String[] lines) {
        super(basicStage);
        message = new Message(basicStage, lines);
        bottomMenu = new BottomMenu(basicStage);
        add().expand().row();
        add().expand().row();
        add().expand().row();
        add(message).expand().row();
        add().expand().row();
        add(bottomMenu).expandX().fillX().bottom();
    }

    public void onOk(){

    }

    public void onCancel(){

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        message.resize(width, height);
        bottomMenu.resize(width, height);
    }

    class Message extends BackgroundedLayout {
        private LabelEntity[] labels;

        public Message(BasicStage basicStage, String[] lines) {
            super(basicStage);
            setFillParent(false);
            int nLines = lines.length;
            labels = new LabelEntity[nLines];

            for (int i = 0; i < nLines; i++) {
                labels[i] = new LabelEntity(lines[i], FontManagerMaster.nexaStyle);
                if (i < nLines - 1)
                    add(labels[i]);
                else
                    add(labels[i]).row();
            }
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
            for (LabelEntity label : labels)
                label.resize(width, height);
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
            MessageLayout.this.onOk();
        }

        public void onCancel() {
            MessageLayout.this.onCancel();
        }
    }
}
