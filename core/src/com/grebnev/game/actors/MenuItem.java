package com.grebnev.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Grebnev on 23.04.2017.
 */

public class MenuItem extends Actor {
    private MenuType type;
    private Texture texture;
    private Texture bgTable = new Texture(Gdx.files.internal("bg_table.jpg"));
    private Texture bgMenu = new Texture(Gdx.files.internal("bg_menu.jpg"));

    public MenuItem(MenuType type) {

        this.type = type;

        switch (type) {
            case BG_TABLE:
                texture = new Texture(Gdx.files.internal("bg_table.jpg"));
                break;
            case LOCAL_GAME:
                texture = new Texture(Gdx.files.internal("data/local_game.png"));
                break;
            case MULTIPLAYER_GAME:
                texture = new Texture(Gdx.files.internal("data/multi_game.png"));
                break;
            case EXIT_GAME:
                texture = new Texture(Gdx.files.internal("data/exit_game.png"));
                break;
            default:
                texture = null;
                break;
        }

        setName(this.getClass().getSimpleName());
        setSize(texture.getWidth(), texture.getHeight());
    }

    public MenuType getMenuType() {
        return type;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        super.draw(batch, parentAlpha);
        //batch.draw(bgTable, 0, 0);
        //batch.draw(bgMenu, getX(), getY());
        batch.draw(texture, getX(), getY());
    }
}
