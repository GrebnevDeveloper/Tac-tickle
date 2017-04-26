package com.grebnev.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by Grebnev on 23.04.2017.
 */

public class GameBoard extends Stage {
    private GameSquare[][] squares;

    /**
     *
     * @return {@link GameSquare}[][]<br />
     * the ChessBoard's squares as a NxN array
     *
     */
    public GameSquare[][] getSquares() {

        return squares;
    }


    /**
     * Creates a new playable Chessboard.
     *
     * @param size The side length of the ChessBoard (and of the inherited Stage Viewport)
     *
     * @author Tommaso Scalici
     */
    public GameBoard(int size) {

        int k = size / 8;
        GameSquare bgTable = new GameSquare(new Vector2(0, 0), new Vector2(0, 0), k, PieceColor.BG_TABLE);
        addActor(bgTable);
        squares = new GameSquare[4][5];

        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {

                if (i == 0 && j == 0)
                    squares[i][j] = new GameSquare(new Vector2(i, j), new Vector2(i  * k, j * k), k, PieceColor.DARK);
                else
                    squares[i][j] = new GameSquare(new Vector2(i, j), new Vector2(i * k, j * k), k, PieceColor.LIGHT);

                addActor(squares[i][j]);
            }
        }

        InitializePieces();

    }

    private void InitializePieces() {

        try {

            for (int i = 0; i < squares.length; i++) {

                for (int j = 0; j < squares[i].length; j++) {

                    GamePiece piece = null;

                    if (j == 0 && i % 2 == 0 || j == 4 && i % 2 != 0) {
                        piece = new GamePiece(PieceColor.LIGHT, PieceKind.ROOK, this, squares[i][j]);
                    }

                    if (j == 0 && i % 2 != 0 || j == 4 && i % 2 == 0) {
                        piece = new GamePiece(PieceColor.DARK, PieceKind.ROOK, this, squares[i][j]);
                    }

                    if(piece != null)
                        addActor(piece);
                    squares[i][j].setPiece(piece);

                }
            }

        }

        catch (Exception ex) {

            Gdx.app.error(getClass().getSimpleName(), "Could not initialize pieces on the board.", ex);
        }
    }
}
