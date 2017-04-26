package com.grebnev.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.grebnev.game.engine.GameEngine;

/**
 * Created by Grebnev on 23.04.2017.
 */

public class GamePiece extends Actor {

    private PieceColor color;
    private PieceKind kind;
    private GameSquare squareWherePositioned;
    private Texture texture;


    public PieceColor getPieceColor() {

        return color;
    }

    public PieceKind getPieceKind() {

        return kind;
    }

    public GameSquare getSquareWherePositioned() {

        return squareWherePositioned;
    }

    public void setSquareWherePositioned(GameSquare squareWherePositioned) {

        this.squareWherePositioned = squareWherePositioned;
        setPosition(squareWherePositioned.getX(), squareWherePositioned.getY());
    }


    public GamePiece(PieceColor color, PieceKind kind, final GameBoard board, GameSquare squareWherePositioned) {

        this.color = color;
        this.kind = kind;
        this.squareWherePositioned = squareWherePositioned;

        switch (color) {
            case DARK:
                texture = new Texture(Gdx.files.internal("black_player.png"));
                break;

            case LIGHT:
                texture = new Texture(Gdx.files.internal("white_player.png"));
                break;
        }

        setName(color.toString() + " " + kind.toString());
        setBounds(squareWherePositioned.getX(), squareWherePositioned.getY(), texture.getWidth(), texture.getHeight());

        addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                GamePiece piece = (GamePiece) event.getListenerActor();
                GameEngine.setLegalSquares(piece);
                GameEngine.showLegalSquares();
                Gdx.app.debug("Input", String.format("%s %s %s in the square %s",
                        piece.getClass().getSimpleName(), piece,
                        event.getType().name(), getSquareWherePositioned().getName()));
                return true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {

                GamePiece piece = (GamePiece) event.getListenerActor();

                setPosition(event.getStageX() - piece.getWidth() / 2, event.getStageY() - piece.getHeight() / 2);

                super.touchDragged(event, x, y, pointer);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                GamePiece piece = (GamePiece) event.getListenerActor();
                GameEngine.hideLegalSquares();

                for(GameSquare[] squareArr : board.getSquares()) {
                    for(GameSquare square : squareArr) {

                        Rectangle squareRect = new Rectangle(square.getX(), square.getY(), square.getWidth(), square.getHeight());

                        if(squareRect.contains(piece.getX() + piece.getWidth() / 2, piece.getY() + piece.getHeight() / 2)) {

                            Gdx.app.debug("Input", String.format("%s %s %s in the square %s",
                                    piece.getClass().getSimpleName(), piece,
                                    event.getType().name(), square));


                            if(GameEngine.isLegalMove(piece, square)) {			 	// If the move is legal...

                                getSquareWherePositioned().setPiece(null); 			// Set to null the piece-reference of the square left by the piece that were moved.
                                setSquareWherePositioned(square);				 	// Set the square-reference of the piece, to the square where the piece were moved.

                                if(getSquareWherePositioned().getPiece() != null) { // If in the new square there was an opponent piece...
                                    getSquareWherePositioned().getPiece().remove(); // ... remove the piece from the stage...

                                }

                                getSquareWherePositioned().setPiece(piece);	  // ... and set the piece-reference of the square to the new piece.
                            }
                            else
                                setSquareWherePositioned(piece.squareWherePositioned); // Else, come back to the original position
                        }
                    }
                }

                GameEngine.showWhoIsWinner();

                super.touchUp(event, x, y, pointer, button);
            }
        });

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        super.draw(batch, parentAlpha);
        batch.draw(texture, getX() + 2, getY() + 2, getWidth(), getHeight());

    }

}
