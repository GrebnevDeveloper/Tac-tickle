package com.grebnev.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.grebnev.game.states.GameStateManager;
import com.grebnev.game.states.MenuState;

public class TacTickle extends ApplicationAdapter {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 720;

	public static final String TITLE = "Tac-Tickle";

	private GameStateManager stateManager;

	private SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		stateManager = new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
        stateManager.push(new MenuState(stateManager));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stateManager.update(Gdx.graphics.getDeltaTime());
		stateManager.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
