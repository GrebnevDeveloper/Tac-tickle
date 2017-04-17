package com.grebnev.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Grebnev on 17.04.2017.
 */

public abstract class State {
    protected OrthographicCamera camera;
    protected Vector3 mouse;
    protected GameStateManager stateManager;

    public State(GameStateManager stateManager) {
        this.stateManager = stateManager;
        camera = new OrthographicCamera();
        mouse = new Vector3();
    }

    protected abstract void handleInput();

    protected abstract void update(float deltaTime);

    protected abstract void render(SpriteBatch batch);

    protected abstract void dispose();
}