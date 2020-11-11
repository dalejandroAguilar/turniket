package com.rodion.turniket.screens.game.layouts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.utils.Align;
import com.rodion.turniket.basics.BasicStage;
import com.rodion.turniket.basics.ImageEntity;
import com.rodion.turniket.basics.Layout;
import com.rodion.turniket.kernel.Blade;
import com.rodion.turniket.kernel.Game;
import com.rodion.turniket.kernel.Token;
import com.rodion.turniket.kernel.Turnstile;
import com.rodion.turniket.kernel.constants.Direction;
import com.rodion.turniket.kernel.constants.TokenColor;
import com.rodion.turniket.screens.game.GameInput;
import com.rodion.turniket.screens.game.entities.BladeEntity;
import com.rodion.turniket.screens.game.entities.BoardEntity;
import com.rodion.turniket.screens.game.entities.BurnerEntity;
import com.rodion.turniket.screens.game.entities.TokenEntity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class BoardLayout extends Layout {
    private Game game;
    private BoardEntity board;
    private ArrayList<TokenEntity> tokens;
    private ArrayList<BladeEntity> blades;
    private GameInput input;
    private InputMultiplexer multiplexer;

    public BoardLayout(BasicStage basicStage) {
        super(basicStage);
        game = new Game();
        multiplexer = new InputMultiplexer();
        input = new GameInput() {
            @Override
            public void onAction(Direction direction) {
                board.onAction(direction);
            }
        };
        File file = new File("maps/map.dat");
        try {
            game.readFile(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        game.setFromMap();
        board = new BoardEntity(getParentStage()) {
            Direction direction;

            @Override
            public void onAction(Direction direction) {
                this.direction = direction;
            }

            @Override
            public void onClickBurner(BurnerEntity burner) {
                super.onClickBurner(burner);
                selectToken = game.getToken(burner.getI(), burner.getJ());
                Token token = game.getToken(burner.getI(), burner.getJ());
                if (token != null)
                    game.move(token.getColor(), direction);

            }
        };
        tokens = new ArrayList<>();
        blades = new ArrayList<>();
        for (Token token : game.getTokens()) {
            if (token.getX() != -1 || token.getY() != -1) {
                TokenEntity tokenEntity = new TokenEntity(token) {
                    @Override
                    public void updatePosition() {
                        super.updatePosition();
                        ImageEntity burner = board.getBurners()[getToken().geti()][getToken().getj()];
                        float x = burner.getAbsX();
                        float y = burner.getAbsY();
                        setPosition(x, y);
                    }
                };
                tokenEntity.getToken().addListener(
                        new Token.Listener() {
                            @Override
                            public void onMove(Direction direction) {
                                System.out.println("onMove");
                            }
                        }
                );
                tokens.add(tokenEntity);
            }
        }
        for (Turnstile turnstile : game.getTurnstiles()) {
            for (Blade blade : turnstile.getBlades()) {
                BladeEntity bladeEntity = new BladeEntity(blade) {
                    @Override
                    public void updatePosition() {
                        super.updatePosition();
                        ImageEntity axis = board.getAxis()[getBlade().getId().index];
                        float x = axis.getX(Align.topLeft);
                        float y = axis.getY(Align.topLeft);
                        setPosition(x, y, Align.topLeft);
                    }
                };
                bladeEntity.prepareAssets();
                blades.add(bladeEntity);
            }
        }
        add(board).center();
        multiplexer.addProcessor(new GestureDetector(input));
        multiplexer.addProcessor(basicStage);
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        for (TokenEntity token : tokens)
            token.draw(batch, parentAlpha);
        for (BladeEntity blade : blades)
            blade.draw(batch, parentAlpha);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        board.resize(width, height);
        for (TokenEntity token : tokens)
            token.resize(width, height);
        for (BladeEntity blade : blades)
            blade.resize(width, height);
    }
}