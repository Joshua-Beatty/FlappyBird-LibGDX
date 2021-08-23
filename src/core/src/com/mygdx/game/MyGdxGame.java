package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class MyGdxGame extends Game {
	SpriteBatch batch;
	Texture img;
	AssetManager manager = new AssetManager();

	@Override
	public void create () {
		setScreen(new TitleScreen(this));
	}

	@Override
	public void render () {
		ScreenUtils.clear(1,0,1,1);
		super.render();
	}
	
	@Override
	public void dispose () {
		manager.dispose();
	}

}
