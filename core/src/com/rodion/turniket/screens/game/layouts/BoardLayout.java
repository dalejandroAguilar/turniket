package com.rodion.turniket.screens.game.layouts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
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
//    private GameInput input;
    private InputMultiplexer multiplexer;

    public BoardLayout(BasicStage basicStage) {
        super(basicStage);
        setFillParent(false);

        game = new Game();
        multiplexer = new InputMultiplexer();
//        input = new GameInput() {
//            @Override
//            public void onAction(Direction direction) {
//                board.onAction(direction);
//            }
//        };

        File file = new File("maps/map.dat");
        try {
            game.readFile(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        game.setFromMap();
        board = new BoardEntity(getParentStage()) {
//            Direction direction;


            @Override
            public void onBurnerAction(BurnerEntity burner, Direction direction) {
                Token selectToken = game.getToken(burner.getI(), burner.getJ());
                if (selectToken != null)
                    game.move(selectToken.getColor(), direction);
            }

//            @Override
//            public boolean onTouchDownBurner(BurnerEntity burner) {
//                selectToken = game.getToken(burner.getI(), burner.getJ());
//                return true;
//            }
//
//            @Override
//            public void onTouchUpBurner(BurnerEntity burner) {
//                if (selectToken != null)
//                    game.move(selectToken.getColor(), direction);
//                selectToken = null;
//            }
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
                        if (selectAnimation == 0 && isOnPlay)
                            setPosition(getLastX(), getLastY());
                        else
                            setPosition(x, y);
                    }
                };
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
                        float x;
                        float y;
                        if (getSelectAnimation() % 2 == 0) {
                            x = axis.getX(Align.topLeft);
                            y = axis.getY(Align.topLeft);
                            setPosition(x, y, Align.topLeft);
                        }
                        if (getSelectAnimation() % 2 == 1) {
                            x = axis.getX(Align.topRight);
                            y = axis.getY(Align.topRight);
                            setPosition(x, y, Align.topRight);
                        }
                    }
                };
                blades.add(bladeEntity);
            }
        }
        add(board).center();
//        multiplexer.addProcessor(new GestureDetector(input));
        multiplexer.addProcessor(basicStage);
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        for (BladeEntity bladeEntity : blades)
            bladeEntity.act(delta);
        for (TokenEntity tokenEntity : tokens)
            tokenEntity.act(delta);
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