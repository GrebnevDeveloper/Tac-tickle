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
}
