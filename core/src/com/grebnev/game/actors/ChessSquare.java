package com.grebnev.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 * Created by Grebnev on 23.04.2017.
 */

public class ChessSquare extends Actor {
    private final char alphabet[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
    private final Texture lightSquareTile = new Texture(Gdx.files.internal("data/square_light_tile.png"));
    private final Texture darkSquareTile = new Texture(Gdx.files.internal("data/square_dark_tile.png"));
    private final Texture bgTable = new Texture(Gdx.files.internal("bg_table.jpg"));
    private final Texture lightSquare = new Texture(Gdx.files.internal("light_internal.png"));
    private final Vector2 indexPosition, pixelPosition;
    private final ChessColor color;
    private ChessPiece piece;


    public Vector2 getIndexPosition() {

        return indexPosition;
    }

    public Vector2 getPixelPosition() {

        return pixelPosition;
    }

    public ChessPiece getPiece() {

        return piece;
    }

    public void setPiece(ChessPiece piece) {

        this.piece = piece;
    }


    public ChessSquare(Vector2 indexPosition, Vector2 pixelPosition, int size, ChessColor color) {

        this.indexPosition = indexPosition;
        this.pixelPosition = pixelPosition;
        this.color = color;

        setBounds(pixelPosition.x + 110, pixelPosition.y + 150, size, size);
        //setName(alphabet[(int)indexPosition.x] + String.valueOf((int)indexPosition.y + 1));

        addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                ChessSquare squareTouched = (ChessSquare)event.getListenerActor();
                Vector2 stageCoords = squareTouched.localToStageCoordinates(new Vector2(x, y));
                Gdx.app.debug("Input",
                        String.format("%s %s %s at position (Relative - X:%.2f, Y:%.2f) (Absolute - X:%.2f, Y:%.2f)",
                                event.getListenerActor().getClass().getSimpleName(), event.getListenerActor(), event.getType().name(), x, y, stageCoords.x, stageCoords.y));

                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                ChessSquare squareTouched = (ChessSquare)event.getListenerActor();
                Vector2 stageCoords = squareTouched.localToStageCoordinates(new Vector2(x, y));
                Gdx.app.debug("Input",
                        String.format("%s %s %s at position (Relative - X:%.2f, Y:%.2f) (Absolute - X:%.2f, Y:%.2f)",
                                event.getListenerActor().getClass().getSimpleName(), event.getListenerActor(), event.getType().name(), x, y, stageCoords.x, stageCoords.y));

                super.touchUp(event, x, y, pointer, button);
            }
        });
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {

        batch.setColor(getColor());

        if (color == ChessColor.BG_TABLE) {
            batch.draw(bgTable, 0, 0);
        }

        if (color == ChessColor.DARK) {
            //batch.draw(bgTable, 0, 0);
            batch.draw(lightSquare, getX(), getY(), getWidth(), getHeight());
        }
        else if (color == ChessColor.LIGHT)
            batch.draw(lightSquare, getX(), getY(), getWidth(), getHeight());

        batch.setColor(Color.WHITE);

        super.draw(batch, parentAlpha);
    }
}
