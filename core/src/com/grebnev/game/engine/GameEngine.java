package com.grebnev.game.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.grebnev.game.actors.GameBoard;
import com.grebnev.game.actors.GamePiece;
import com.grebnev.game.actors.GameSquare;

import java.util.ArrayList;

/**
 * Created by Grebnev on 23.04.2017.
 */

public class GameEngine {
    private static GameBoard board;
    private static ArrayList<GameSquare> legalSquares;


    public static ArrayList<GameSquare> getLegalSquares() {

        return legalSquares;
    }

    public static void setLegalSquares(GamePiece piece) {

        GameEngine.legalSquares = calculateLegalSquares(piece);
    }


    public GameEngine(GameBoard board) {

        GameEngine.board = board;
    }

    public static boolean isLegalMove(GamePiece piece, GameSquare square) {

        if(legalSquares.contains(square))
            return true;

        else {
            Gdx.app.debug("Game Engine", String.format("INVALID MOVE - returning %s to square %s", piece, piece.getSquareWherePositioned()));
            return false;
        }
    }

    public static void hideLegalSquares() {

        for (GameSquare[] squares : board.getSquares())
            for (GameSquare square : squares)
                square.setColor(Color.WHITE);
    }

    public static void showWhoIsWinner() {
        GameSquare[][] squares = board.getSquares();
        for (int i = 0; i < squares.length - 2; i++) {
            for (int j = 0; j < squares[i].length - 2; j++) {
                if (squares[i][j].getPiece() != null && squares[i][j + 1].getPiece() != null && squares[i][j + 2].getPiece() != null) {
                    if (squares[i][j].getPiece().getPieceColor() == squares[i][j + 1].getPiece().getPieceColor() &&
                            squares[i][j].getPiece().getPieceColor() == squares[i][j + 2].getPiece().getPieceColor()) {
                        squares[i][j].setColor(Color.GREEN);
                        squares[i][j + 1].setColor(Color.GREEN);
                        squares[i][j + 2].setColor(Color.GREEN);
                    }
                }
                if (squares[i][j].getPiece() != null && squares[i + 1][j].getPiece() != null && squares[i + 2][j].getPiece() != null) {
                    if (squares[i][j].getPiece().getPieceColor() == squares[i + 1][j].getPiece().getPieceColor() &&
                            squares[i][j].getPiece().getPieceColor() == squares[i + 2][j].getPiece().getPieceColor()) {
                        squares[i][j].setColor(Color.GREEN);
                        squares[i + 1][j].setColor(Color.GREEN);
                        squares[i + 2][j].setColor(Color.GREEN);
                    }
                }
                if (squares[i][j].getPiece() != null && squares[i + 1][j + 1].getPiece() != null && squares[i + 2][j + 2].getPiece() != null) {
                    if (squares[i][j].getPiece().getPieceColor() == squares[i + 1][j + 1].getPiece().getPieceColor() &&
                            squares[i][j].getPiece().getPieceColor() == squares[i + 2][j + 2].getPiece().getPieceColor()) {
                        squares[i][j].setColor(Color.GREEN);
                        squares[i + 1][j + 1].setColor(Color.GREEN);
                        squares[i + 2][j + 2].setColor(Color.GREEN);
                    }
                }
            }
        }
        for (int i = squares.length - 1; i > 1 ; i--) {
            for (int j = squares[i].length - 1; j > 1; j--) {
                if (squares[i][j].getPiece() != null && squares[i][j - 1].getPiece() != null && squares[i][j - 2].getPiece() != null) {
                    if (squares[i][j].getPiece().getPieceColor() == squares[i][j - 1].getPiece().getPieceColor() &&
                            squares[i][j].getPiece().getPieceColor() == squares[i][j - 2].getPiece().getPieceColor()) {
                        squares[i][j].setColor(Color.GREEN);
                        squares[i][j - 1].setColor(Color.GREEN);
                        squares[i][j - 2].setColor(Color.GREEN);
                    }
                }
                if (squares[i][j].getPiece() != null && squares[i - 1][j].getPiece() != null && squares[i - 2][j].getPiece() != null) {
                    if (squares[i][j].getPiece().getPieceColor() == squares[i - 1][j].getPiece().getPieceColor() &&
                            squares[i][j].getPiece().getPieceColor() == squares[i - 2][j].getPiece().getPieceColor()) {
                        squares[i][j].setColor(Color.GREEN);
                        squares[i - 1][j].setColor(Color.GREEN);
                        squares[i - 2][j].setColor(Color.GREEN);
                    }
                }
            }
        }
        for (int i = squares.length - 1; i > 1 ; i--) {
            for (int j = 0; j < squares[i].length - 2; j++) {
                if (squares[i][j].getPiece() != null && squares[i - 1][j + 1].getPiece() != null && squares[i - 2][j + 2].getPiece() != null) {
                    if (squares[i][j].getPiece().getPieceColor() == squares[i - 1][j + 1].getPiece().getPieceColor() &&
                            squares[i][j].getPiece().getPieceColor() == squares[i - 2][j + 2].getPiece().getPieceColor()) {
                        squares[i][j].setColor(Color.GREEN);
                        squares[i - 1][j + 1].setColor(Color.GREEN);
                        squares[i - 2][j + 2].setColor(Color.GREEN);
                    }
                }
            }
        }
    }

    public static void showLegalSquares() {

        for(GameSquare square : legalSquares)
            square.setColor(Color.RED);
    }

    public static ArrayList<GameSquare> calculateLegalSquares(GamePiece piece) {

        legalSquares = new ArrayList<GameSquare>();

        for (GameSquare[] squares : board.getSquares()) {

            for (GameSquare square : squares) {

                boolean isLegalPosition = false;
                boolean isLegalMove = false;

                Vector2 oldIndexPosition = piece.getSquareWherePositioned().getIndexPosition();
                Vector2 newIndexPosition = square.getIndexPosition();

                int absDiffX = (int)Math.abs(newIndexPosition.x - oldIndexPosition.x);
                int absDiffY = (int)Math.abs(newIndexPosition.y - oldIndexPosition.y);

                if ((absDiffX == 0) && (absDiffY == 1) || (absDiffX == 1) && (absDiffY == 0))
                    isLegalPosition = true;


                if(isLegalPosition && square.getPiece() == null)
                    isLegalMove = true;


                if (isLegalMove)
                    legalSquares.add(square);

            }
        }

        return legalSquares;
    }
}
