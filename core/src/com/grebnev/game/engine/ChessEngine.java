package com.grebnev.game.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.grebnev.game.actors.ChessBoard;
import com.grebnev.game.actors.ChessPiece;
import com.grebnev.game.actors.ChessSquare;

import java.util.ArrayList;

/**
 * Created by Grebnev on 23.04.2017.
 */

public class ChessEngine {
    private static ChessBoard board;
    private static ArrayList<ChessSquare> legalSquares;


    public static ArrayList<ChessSquare> getLegalSquares() {

        return legalSquares;
    }

    public static void setLegalSquares(ChessPiece piece) {

        ChessEngine.legalSquares = calculateLegalSquares(piece);
    }


    public ChessEngine(ChessBoard board) {

        ChessEngine.board = board;
    }

    public static boolean isRookLegalPosition(ChessPiece piece, Vector2 oldIndexPosition, Vector2 newIndexPosition) {

        int minX, maxX, minY, maxY;

        ChessSquare[] horizontalSquares = new ChessSquare[board.getSquares().length];
        ChessSquare[] verticalSquares = board.getSquares()[(int)newIndexPosition.x];

        for(int i = 0; i < board.getSquares().length; i++)
            horizontalSquares[i] = board.getSquares()[i][(int)newIndexPosition.y];


        for(maxX = (int)oldIndexPosition.x + 1; maxX < horizontalSquares.length; maxX++)
            if(board.getSquares()[maxX][(int)newIndexPosition.y].getPiece() != null)
                break;

        for(minX = (int)oldIndexPosition.x - 1; minX >= 0; minX--)
            if(board.getSquares()[minX][(int)newIndexPosition.y].getPiece() != null)
                break;

        for(maxY = (int)oldIndexPosition.y + 1; maxY < verticalSquares.length; maxY++)
            if(board.getSquares()[(int)newIndexPosition.x][maxY].getPiece() != null)
                break;

        for(minY = (int)oldIndexPosition.y - 1; minY >= 0; minY--)
            if(board.getSquares()[(int)newIndexPosition.x][minY].getPiece() != null)
                break;


        if(newIndexPosition.x <= maxX && newIndexPosition.x >= minX &&
                newIndexPosition.y <= maxY && newIndexPosition.y >= minY)
            return true;
        else
            return false;
    }

    public static boolean isBishopLegalPosition(ChessPiece piece, Vector2 oldIndexPosition, Vector2 newIndexPosition) {

        boolean isLegalPosition = false;
        int newX = (int)newIndexPosition.x;
        int newY = (int)newIndexPosition.y;
        int oldX = (int)oldIndexPosition.x;
        int oldY = (int)oldIndexPosition.y;

        if(newX > oldX && newY > oldY) {
            for(; newX > oldX && newY > oldY; newX--, newY--) {

                if(board.getSquares()[newX - 1][newY - 1].getPiece() == null)
                    isLegalPosition = true;
                else if(board.getSquares()[newX - 1][newY - 1].getPiece().equals(piece))
                    isLegalPosition = true;
                else {
                    isLegalPosition = false;
                    break;
                }
            }
        }

        if(newX < oldX && newY < oldY) {
            for(; newX < oldX && newY < oldY; newX++, newY++) {

                if(board.getSquares()[newX + 1][newY + 1].getPiece() == null)
                    isLegalPosition = true;
                else if(board.getSquares()[newX + 1][newY + 1].getPiece().equals(piece))
                    isLegalPosition = true;
                else {
                    isLegalPosition = false;
                    break;
                }
            }
        }

        if(newX > oldX && newY < oldY) {
            for(; newX > oldX && newY < oldY; newX--, newY++) {

                if(board.getSquares()[newX - 1][newY + 1].getPiece() == null)
                    isLegalPosition = true;
                else if(board.getSquares()[newX - 1][newY + 1].getPiece().equals(piece))
                    isLegalPosition = true;
                else {
                    isLegalPosition = false;
                    break;
                }
            }
        }

        if(newX < oldX && newY > oldY) {
            for(; newX < oldX && newY > oldY; newX++, newY--) {

                if(board.getSquares()[newX + 1][newY - 1].getPiece() == null)
                    isLegalPosition = true;
                else if(board.getSquares()[newX + 1][newY - 1].getPiece().equals(piece))
                    isLegalPosition = true;
                else {
                    isLegalPosition = false;
                    break;
                }
            }
        }

        return isLegalPosition;
    }

    public static boolean isPawnLegalPosition(ChessPiece piece, ChessSquare square, Vector2 oldIndexPosition, Vector2 newIndexPosition) {

        int diffX = (int)(newIndexPosition.x - oldIndexPosition.x);
        int diffY = (int)(newIndexPosition.y - oldIndexPosition.y);

        switch (piece.getPieceColor()) {

            case DARK:
                if ((square.getPiece() == null &&
                        diffX == 0 			   &&
                        (diffY == -1 ||
                                (diffY == -2 && oldIndexPosition.y == 6 && board.getSquares()[(int)newIndexPosition.x][(int)newIndexPosition.y + 1].getPiece() == null))) ||
                        ((diffX == -1 && diffY == -1) || (diffX == 1 && diffY == -1)) && square.getPiece() != null)
                    return true;
                else
                    return false;

            case LIGHT:
                if ((square.getPiece() == null &&
                        diffX == 0 			   &&
                        (diffY == 1 ||
                                (diffY == 2 && oldIndexPosition.y == 1 && board.getSquares()[(int)newIndexPosition.x][(int)newIndexPosition.y - 1].getPiece() == null))) ||
                        ((diffX == -1 && diffY == 1) || (diffX == 1 && diffY == 1)) && square.getPiece() != null)
                    return true;
                else
                    return false;

            default:
                return false;
        }
    }

    public static boolean isLegalMove(ChessPiece piece, ChessSquare square) {

        if(legalSquares.contains(square))
            return true;

        else {
            Gdx.app.debug("Game Engine", String.format("INVALID MOVE - returning %s to square %s", piece, piece.getSquareWherePositioned()));
            return false;
        }
    }

    public static void hideLegalSquares() {

        for (ChessSquare[] squares : board.getSquares())
            for (ChessSquare square : squares)
                square.setColor(Color.WHITE);
    }

    public static void showWhoIsWinner() {
        ChessSquare[][] squares = board.getSquares();
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

        for(ChessSquare square : legalSquares)
            square.setColor(Color.RED);
    }

    public static ArrayList<ChessSquare> calculateLegalSquares(ChessPiece piece) {

        legalSquares = new ArrayList<ChessSquare>();

        for (ChessSquare[] squares : board.getSquares()) {

            for (ChessSquare square : squares) {

                boolean isLegalPosition = false;
                boolean isLegalMove = false;

                Vector2 oldIndexPosition = piece.getSquareWherePositioned().getIndexPosition();
                Vector2 newIndexPosition = square.getIndexPosition();

                int absDiffX = (int)Math.abs(newIndexPosition.x - oldIndexPosition.x);
                int absDiffY = (int)Math.abs(newIndexPosition.y - oldIndexPosition.y);

                switch (piece.getPieceKind()) {

                    case KING:
                        if ((absDiffX == 0 || absDiffX == 1) && (absDiffY == 0 || absDiffY == 1))
                            isLegalPosition = true;
                        break;

                    case QUEEN:
                        if (absDiffX == absDiffY)
                            isLegalPosition = isBishopLegalPosition(piece, oldIndexPosition, newIndexPosition);
                        else if (absDiffX == 0 || absDiffY == 0)
                            isLegalPosition = isRookLegalPosition(piece, oldIndexPosition, newIndexPosition);
                        break;

                    case ROOK:
                        if ((absDiffX == 0) && (absDiffY == 1) || (absDiffX == 1) && (absDiffY == 0))
                            isLegalPosition = true;
                        break;

                    case BISHOP:
                        if (absDiffX == absDiffY)
                            isLegalPosition = isBishopLegalPosition(piece, oldIndexPosition, newIndexPosition);
                        break;

                    case KNIGHT:
                        if (absDiffX == 2 && absDiffY == 1 || absDiffX == 1 && absDiffY == 2)
                            isLegalPosition = true;
                        break;

                    case PAWN: isLegalPosition = isPawnLegalPosition(piece, square, oldIndexPosition, newIndexPosition);
                        break;

                    default: isLegalPosition = false;
                        break;
                }


                if(square.getPiece() != null) {

                    //if(square.getPiece().getPieceColor() != piece.getPieceColor() && isLegalPosition)
                        //isLegalMove = true;
                }

                else if(isLegalPosition)
                    isLegalMove = true;


                if (isLegalMove)
                    legalSquares.add(square);

            }
        }

        return legalSquares;
    }
}
