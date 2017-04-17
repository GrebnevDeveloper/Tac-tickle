package com.grebnev.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.grebnev.game.TacTickle;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = TacTickle.WIDTH;
		config.height = TacTickle.HEIGHT;
		config.title = TacTickle.TITLE;
		new LwjglApplication(new TacTickle(), config);
	}
}
