package com.rodion.turniket.screens.game.stages.gameStage.layouts;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
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
import com.rodion.turniket.screens.game.stages.gameStage.entities.BladeEntity;
import com.rodion.turniket.screens.game.stages.gameStage.entities.BoardEntity;
import com.rodion.turniket.screens.game.stages.gameStage.entities.BurnerEntity;
import com.rodion.turniket.screens.game.stages.gameStage.entities.TokenEntity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class BoardLayout extends Layout {
    private Game game;
    private BoardEntity board;
    private ArrayList<TokenEntity> tokens;
    private ArrayList<BladeEntity> blades;

    public BoardLayout(FileHandle file, BasicStage basicStage) {
        super(basicStage);
        setFillParent(false);
        game = new Game();
        game.readFile(file);
        game.setFromMap();

        board = new BoardEntity(getParentStage()) {
            @Override
            public void onBurnerAction(BurnerEntity burner, Direction direction) {
                Token selectToken = game.getToken(burner.getI(), burner.getJ());
                if (selectToken != null) {
//                    TokenEntity tokenEntity = tokens.get(selectToken.getColor().index);
//                    tokenEntity.addAction(Actions.scaleBy(2,2,1));
                    move(selectToken.getColor(), direction);
                }
            }

            @Override
            public void onBurnerPressed(BurnerEntity burner) {
                super.onBurnerPressed(burner);
                Token token = game.getToken(burner.getI(), burner.getJ());
                if (token != null) {
                    TokenEntity tokenEntity = getTokenEntity(token);
                    tokenEntity.addAction(Actions.color(Color.WHITE,0.25f));
                }
            }

            @Override
            public void onBurnerUnpressed(BurnerEntity burner) {
                super.onBurnerUnpressed(burner);
                Token token = game.getToken(burner.getI(), burner.getJ());
                if (token != null) {
                    TokenEntity tokenEntity = getTokenEntity(token);
                    tokenEntity.addAction(Actions.color(token.getColor().getColor(), 0.25f));
                }
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
                        if (selectAnimation == 0 && isOnPlay)
                            setPosition(getLastX(), getLastY());
                        else
                            setPosition(x, y);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        onMoveFinish();
                    }
                };
                tokens.add(tokenEntity);
            }
        }


        for (Turnstile turnstile : game.getTurnstiles()) {
            ImageEntity axis = board.getAxis()[turnstile.getId().index];
            final ImageEntity finalAxis = axis;
            for (Blade blade : turnstile.getBlades()) {
                BladeEntity bladeEntity = new BladeEntity(blade) {
                    @Override
                    public void updatePosition() {
                        super.updatePosition();
//
                        float x;
                        float y;
                        if (getSelectAnimation() % 2 == 0) {
                            x = finalAxis.getX(Align.topLeft);
                            y = finalAxis.getY(Align.topLeft);
                            setPosition(x, y, Align.topLeft);
                        }
                        if (getSelectAnimation() % 2 == 1) {
                            x = finalAxis.getX(Align.topRight);
                            y = finalAxis.getY(Align.topRight);
                            setPosition(x, y, Align.topRight);
                        }
                    }
                };
                blades.add(bladeEntity);
            }
        }
        add(board).center();
        System.out.println("add listener");
//         game.removeListener();
        game.addListener(new Game.Listener() {
            @Override
            public void onWin() {
//                System.out.p
                BoardLayout.this.onWin();
            }
        });

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        for (BladeEntity bladeEntity : blades)
            bladeEntity.act(delta);
        for (TokenEntity tokenEntity : tokens)
            tokenEntity.act(delta);

        for (Turnstile turnstile : game.getTurnstiles()) {
            ImageEntity axis = board.getAxis()[turnstile.getId().index];
            if (turnstile.getBlades().size() == 0)
                axis.setVisible(false);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        for (TokenEntity token : tokens)
            if (token.isVisible())
                token.draw(batch, parentAlpha);
        for (BladeEntity blade : blades)
            if (blade.isVisible())
                blade.draw(batch, parentAlpha);
//        board.draw(batch, parentAlpha);
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

    public void onUndo() {
        game.undo();
        for (BladeEntity blade : blades)
            blade.updateRotation();

    }

    public void onRedo() {
        game.redo();
        for (BladeEntity blade : blades)
            blade.updateRotation();
    }

    public void onRestart() {
        game.restart();
        for (BladeEntity blade : blades)
            blade.updateRotation();
    }

    public void move(TokenColor tokenColor, Direction direction) {
//        TokenEntity selectToken = tokens.get(tokenColor.index);
//        if (selectToken != null) {
        if (game.move(tokenColor, direction)) {
            onMoveTry();
            onMove();
        }
//        }
    }

    public void moveFromSolver() {

    }

    public void onMove() {

    }

    public void onMoveTry() {

    }

    public void onMoveFinish() {

    }

    public int getSteps() {
        return game.getSteps();
    }

    public void onWin() {

    }


    public Game getGame() {
        return game;
    }

    public void moveFromSolution() {
        if (game.moveFromSolution()) {
            onMoveTry();
            onMove();
        }
    }

    public void undoFromSolution() {
        game.undoFromSolution();
        for (BladeEntity blade : blades)
            blade.updateRotation();
    }

    public boolean isOnBegin() {
        return game.isOnBegin();
    }

    public void setToLock() {
        for (TokenEntity token : tokens)
            token.getColor().a = 0;
        for (BladeEntity blade : blades)
            blade.getColor().a = 0;
        board.setToLock();

    }

    public void setToUnlock() {

    }

    public void onUnlock() {
        board.onUnlock();
        for (TokenEntity token : tokens)
            token.addAction(Actions.fadeIn(0.3f));
        for (BladeEntity blade : blades)
            blade.addAction(Actions.fadeIn(0.3f));
    }

    public BoardEntity getBoard() {
        return board;
    }

    private TokenEntity getTokenEntity(Token token) {
        for (TokenEntity tokenEntity : tokens)
            if (tokenEntity.getToken() == token)
                return tokenEntity;
        return null;
    }
}