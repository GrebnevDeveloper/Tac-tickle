package com.grebnev.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.grebnev.game.actors.GameBoard;
import com.grebnev.game.engine.GameEngine;

/**
 * Created by Grebnev on 23.04.2017.
 */

public class PlayScreen extends BaseGameScreens {
    private GameBoard board;
    private GameEngine engine;

    public PlayScreen(final Game game) {

        super(game);
        board = new GameBoard((Gdx.graphics.getWidth() + Gdx.graphics.getHeight() - 30) / 2);
        engine = new GameEngine(board);
        inputMultiplexer.addProcessor(board);
    }


    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        board.act(delta);
        board.draw();
        super.render(delta);
    }
}
