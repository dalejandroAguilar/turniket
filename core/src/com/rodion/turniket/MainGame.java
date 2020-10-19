package com.rodion.turniket;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rodion.turniket.screens.game.GameScreen;
import com.rodion.turniket.utilities.AssetManagerMaster;

public class MainGame extends Game {
	private GameScreen gameScreen;

	@Override
	public void create() {
		AssetManagerMaster.loadGame();
		gameScreen = new GameScreen(this);
		setScreen(gameScreen);
	}
}
