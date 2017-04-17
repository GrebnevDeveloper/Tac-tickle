package com.grebnev.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.grebnev.game.TacTickle;

/**
 * Created by Grebnev on 17.04.2017.
 */

public class MenuState extends State {

    private Texture bgTable;
    private Texture bgMenu;

    public MenuState(GameStateManager stateManager) {
        super(stateManager);
        bgTable = new Texture("bg_table.jpg");
        bgMenu = new Texture("bg_menu.jpg");
    }

    @Override
    protected void handleInput() {

    }

    @Override
    protected void update(float deltaTime) {

    }

    @Override
    protected void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(bgTable, 0, 0, TacTickle.WIDTH, TacTickle.HEIGHT);
        batch.draw(bgMenu, (TacTickle.WIDTH / 2) - (bgMenu.getWidth() / 2) , (TacTickle.HEIGHT / 4));
        batch.end();
    }

    @Override
    protected void dispose() {
        bgTable.dispose();
        bgMenu.dispose();
    }
}
