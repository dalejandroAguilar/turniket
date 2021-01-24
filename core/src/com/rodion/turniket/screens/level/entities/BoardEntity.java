package com.rodion.turniket.screens.level.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.ImageEntity;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.kernel.Blade;
import com.rodion.turniket.kernel.Game;
import com.rodion.turniket.kernel.Turnstile;
import com.rodion.turniket.utilities.AssetManagerMaster;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class BoardEntity extends Layout {
    private ImageEntity backboard;
    private ImageEntity grid;
    private ImageEntity[][] tokens;
    private ImageEntity[] axis;
    private ArrayList<BladeEntity> blades;
    private Game game;

    public BoardEntity(File file, BasicStage basicStage) {
        super(basicStage);
        setFillParent(false);
        Stack stack = new Stack();
        Stack stack2 = new Stack();
        Table table = new Table();
        Table table2 = new Table();
        Table table3 = new Table();
        Table tokensLayout = new Table();
        blades = new ArrayList<>();
        game = new Game();
        try {
            game.readFile(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        game.setFromMap();
        axis = new ImageEntity[4];
        backboard = new ImageEntity() {
            @Override
            public void setAssetAddress() {
                setAssetManager(AssetManagerMaster.level);
                assetPath = "level";
                assetName = "back_board";
            }
        };
        backboard.prepareAssets();

        grid = new ImageEntity() {
            @Override
            public void setAssetAddress() {
                setAssetManager(AssetManagerMaster.level);
                assetPath = "level";
                assetName = "grid";
            }
        };
        grid.prepareAssets();

        tokens = new ImageEntity[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tokens[i][j] = new ImageEntity() {
                    public void setAssetAddress() {
                        setAssetManager(AssetManagerMaster.level);
                        assetPath = "level";
                        assetName = "token";
                    }

                };
                tokens[i][j].prepareAssets();
                tokensLayout.add(tokens[i][j]).expand();

                if (game.getToken(i, j) != null)
                    switch (game.getToken(i, j).getColor()) {
                        case Cyan:
                            tokens[i][j].setColor(Color.CYAN);
                            break;
                        case Magenta:
                            tokens[i][j].setColor(Color.MAGENTA);
                            break;
                        case Red:
                            tokens[i][j].setColor(Color.ORANGE);
                            break;
                        case Green:
                            tokens[i][j].setColor(Color.GREEN);
                            break;
                        default:
                            tokens[i][j].setColor(Color.DARK_GRAY);
                    }
            }
            tokensLayout.row();
        }

        for (int i = 0; i < 4; i++) {
            final int finalI = i;
            axis[i] = new ImageEntity() {
                @Override
                public void setAssetAddress() {
                    setAssetManager(AssetManagerMaster.level);
                    assetPath = "level";
                    assetName = "axis";
                }

                @Override
                public void updatePosition() {
                    int i = finalI / 2;
                    int j = finalI % 2;
                    axis[finalI].setPosition(0.5f * (tokens[i][j].getAbsX() + tokens[i + 1][j + 1].getAbsX() + tokens[i + 1][j + 1].getDrawable().getMinWidth()),
                            0.5f * (tokens[i][j].getAbsY() + tokens[i + 1][j + 1].getAbsY() + tokens[i + 1][j + 1].getDrawable().getMinHeight()),
                            Align.center);
                }
            };
            axis[i].prepareAssets();
        }

        for (Turnstile turnstile : game.getTurnstiles()) {
            for (final Blade blade : turnstile.getBlades()) {
                BladeEntity bladeEntity = new BladeEntity(blade) {
                    @Override
                    public void updatePosition() {
                        super.updatePosition();
                        ImageEntity axis1 = axis[blade.getId().index];
                        float x;
                        float y;
                        x = axis1.getX(Align.topLeft);
                        y = axis1.getY(Align.topLeft);
                        setPosition(x, y, Align.topLeft);
                    }
                };
                blades.add(bladeEntity);
            }
        }
//        stack.add(frame);
        table.add(backboard);
        stack.add(table);
        table2.add(grid).expand();
        stack2.add(table2);
        stack2.add(tokensLayout);
        table3.add(stack2);
        stack.add(table3);
        add(stack);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        for (ImageEntity axisElement : axis)
            axisElement.draw(batch, parentAlpha);
        for (ImageEntity blade : blades)
            blade.draw(batch, parentAlpha);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        backboard.resize(width, height);
        grid.resize(width, height);
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                tokens[i][j].resize(width, height);
            }
        for (ImageEntity axisElement : axis)
            axisElement.resize(width, height);
        for (ImageEntity blade : blades)
            blade.resize(width, height);
    }
}
