package com.grebnev.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Grebnev on 23.04.2017.
 */

public abstract class BaseGameScreens implements Screen {
    final Game game;
    BitmapFont font;
    InputMultiplexer inputMultiplexer;
    Vector2 screenSize;


    public InputMultiplexer getInputMultiplexer() {

        return inputMultiplexer;
    }

    public Vector2 getScreenSize() {
        return screenSize;
    }


    public BaseGameScreens(final Game game) {

        this.game = game;

        screenSize = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        font = new BitmapFont();
        inputMultiplexer = new InputMultiplexer();

        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

        font.dispose();
    }
}
