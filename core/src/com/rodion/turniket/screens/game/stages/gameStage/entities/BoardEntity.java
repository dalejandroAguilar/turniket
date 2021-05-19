package com.rodion.turniket.screens.game.stages.gameStage.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.ImageEntity;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.kernel.constants.Direction;
import com.rodion.turniket.kernel.constants.TokenColor;
import com.rodion.turniket.utilities.AssetManagerMaster;

public class BoardEntity extends Layout {
    private ImageEntity backboard;
    private ImageEntity[] targets;
    private ImageEntity grid;
    private BurnerEntity[][] burners;
    private ImageEntity[] axis;

    public BoardEntity(BasicStage basicStage) {
        super(basicStage);
        setFillParent(false);
        Stack stack = new Stack();
        Stack stack2 = new Stack();
        final Table table = new Table();
        Table table2 = new Table();
        Table burnersLayout = new Table();
        Table table3 = new Table();
        burners = new BurnerEntity[3][3];
        axis = new ImageEntity[4];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                burners[i][j] = new BurnerEntity(i, j) {
                    @Override
                    public void onAction(BurnerEntity burner, Direction direction) {
                        super.onAction(burner, direction);
                        onBurnerAction(burner, direction);
                    }
                };
                burnersLayout.add(burners[i][j]).expand();
            }
            burnersLayout.row();
        }

        for (int i = 0; i < 4; i++) {
            final int finalI = i;
            axis[i] = new ImageEntity() {
                @Override
                public void setAssetAddress() {
                    setAssetManager(AssetManagerMaster.game);
                    assetPath = "game";
                    assetName = "axis";
                }

                @Override
                public void updatePosition() {
                    int i = finalI / 2;
                    int j = finalI % 2;
//                    if (i)
                    if (isVisible())
                        setPosition(0.5f * (burners[i][j].getAbsX() +
                                        burners[i + 1][j + 1].getAbsX() +
                                        burners[i + 1][j + 1].getDrawable().getMinWidth()),
                                0.5f * (burners[i][j].getAbsY() + burners[i + 1][j + 1].getAbsY() +
                                        burners[i + 1][j + 1].getDrawable().getMinHeight()),
                                Align.center);
                }
            };
            axis[i].prepareAssets();
        }

        backboard = new ImageEntity() {
            @Override
            public void setAssetAddress() {
                setAssetManager(AssetManagerMaster.game);
                assetPath = "game";
                assetName = "back-board";
            }
        };
        backboard.prepareAssets();

        targets = new ImageEntity[4];

        for (int i = 0; i < 4; i++) {
            final int finalI = i;
            targets[i] = new ImageEntity() {
                @Override
                public void setAssetAddress() {
                    setAssetManager(AssetManagerMaster.game);
                    assetPath = "game";
                    assetName = TokenColor.getTargetAssetName(finalI);
                }
            };
            targets[i].prepareAssets();
            targets[i].setColor(TokenColor.get(i).getColor());
        }

        grid = new ImageEntity() {
            @Override
            public void setAssetAddress() {
                setAssetManager(AssetManagerMaster.game);
                assetPath = "game";
                assetName = "grid";
            }
        };
        grid.prepareAssets();
        table.add(targets[0]).left().top().expand();
        table.add(targets[1]).right().top().expand().row();
        table.add(targets[3]).left().bottom().expand();
        table.add(targets[2]).right().bottom().expand();

        stack.add(backboard);
        stack.add(table);
        table2.add(grid).expand();
        stack2.add(table2);
        stack2.add(burnersLayout);
        table3.add(stack2);
        stack.add(table3);
        add(stack);
    }

    public BurnerEntity[][] getBurners() {
        return burners;
    }

    public ImageEntity[] getAxis() {
        return axis;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        for (ImageEntity axisElement : axis)
            if (axisElement.isVisible())
                axisElement.draw(batch, parentAlpha);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        backboard.resize(width, height);
        for (ImageEntity target : targets)
            target.resize(width, height);

        grid.resize(width, height);
        for (ImageEntity[] burnRow : burners)
            for (ImageEntity burn : burnRow)
                burn.resize(width, height);
        for (ImageEntity axisElement : axis)
            axisElement.resize(width, height);
    }

    public void onBurnerAction(BurnerEntity burner, Direction direction) {
    }

    public void setLockedStatus(boolean lockedStatus) {
        grid.setVisible(!lockedStatus);
        for (ImageEntity[] burnRow : burners)
            for (ImageEntity burn : burnRow)
                burn.setVisible(!lockedStatus);
        for (ImageEntity ax : axis)
            ax.setVisible(!lockedStatus);
        for (ImageEntity target : targets)
            target.setVisible(!lockedStatus);
    }

    public void onSetLokedStatus(boolean lockedStatus) {
        if (lockedStatus) {
            grid.addAction(Actions.fadeOut(.2f));
            for (ImageEntity[] burnRow : burners)
                for (ImageEntity burn : burnRow)
                    burn.addAction(Actions.fadeOut(.2f));
            for (ImageEntity ax : axis)
                ax.addAction(Actions.fadeOut(.2f));
            for (ImageEntity target : targets)
                target.addAction(Actions.fadeOut(.2f));
        } else {
            grid.addAction(Actions.fadeIn(.2f));
            for (ImageEntity[] burnRow : burners)
                for (ImageEntity burn : burnRow)
                    burn.addAction(Actions.fadeIn(.2f));
            for (ImageEntity ax : axis)
                ax.addAction(Actions.fadeIn(.2f));
            for (ImageEntity target : targets)
                target.addAction(Actions.fadeIn(.2f));
        }
    }
}