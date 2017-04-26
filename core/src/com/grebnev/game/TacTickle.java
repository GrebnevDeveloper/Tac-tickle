package com.grebnev.game;

import com.badlogic.gdx.Game;
import com.grebnev.game.screens.MainMenuScreen;

public class TacTickle extends Game {
	@Override
	public void create() {

		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render() {

		super.render();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {

	}
//	public static final int WIDTH = 600;
//	public static final int HEIGHT = 720;
//
//	public static final String TITLE = "Tac-Tickle";
//
//	private GameStateManager stateManager;
//
//	private SpriteBatch batch;
//
//	@Override
//	public void create () {
//		batch = new SpriteBatch();
//		stateManager = new GameStateManager();
//		Gdx.gl.glClearColor(1, 0, 0, 1);
//        stateManager.push(new MenuState(stateManager));
//	}
//
//	@Override
//	public void render () {
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		stateManager.update(Gdx.graphics.getDeltaTime());
//		stateManager.render(batch);
//	}
//
//	@Override
//	public void dispose () {
//		batch.dispose();
//	}
}
