package com.grebnev.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.grebnev.game.actors.ChessBoard;
import com.grebnev.game.engine.ChessEngine;

/**
 * Created by Grebnev on 23.04.2017.
 */

public class PlayScreen extends BaseGameScreens {
    private ChessBoard board;
    private ChessEngine engine;

    public PlayScreen(final Game game) {

        super(game);
        board = new ChessBoard(550);
        engine = new ChessEngine(board);
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
