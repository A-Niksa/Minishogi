package models;

import models.pieces.*;

public class Board {
    Tile[][] boardArray;

    Board() {
        boardArray = new Tile[5][5];
        setBoard();
    }

    private void setBoard() {
        setBlackPieces();
        setWhitePieces();
        setEmptyTiles();
    }

    private void setWhitePieces() {
        Pawn pawn = new Pawn(false);
        boardArray[3][0] = new Tile(pawn, true, 4, 1);
        Lance lance = new Lance(false);
        boardArray[4][4] = new Tile(lance, true, 5, 5);
        Bishop bishop = new Bishop(false);
        boardArray[4][3] = new Tile(bishop, true, 5, 4);
        SilverGeneral silverGeneral = new SilverGeneral(false);
        boardArray[4][2] = new Tile(silverGeneral, true, 5, 3);
        GoldGeneral goldGeneral = new GoldGeneral(false);
        boardArray[4][1] = new Tile(goldGeneral, true, 5, 2);
        King king = new King(false);
        boardArray[4][0] = new Tile(king, true, 5, 1);
    }

    private void setBlackPieces() {
        Pawn pawn = new Pawn(true);
        boardArray[1][4] = new Tile(pawn, true, 2, 5);
        Lance lance = new Lance(true);
        boardArray[0][0] = new Tile(lance, true, 1, 1);
        Bishop bishop = new Bishop(true);
        boardArray[0][1] = new Tile(bishop, true, 1, 2);
        SilverGeneral silverGeneral = new SilverGeneral(true);
        boardArray[0][2] = new Tile(silverGeneral, true, 1, 3);
        GoldGeneral goldGeneral = new GoldGeneral(true);
        boardArray[0][3] = new Tile(goldGeneral, true, 1, 4);
        King king = new King(true);
        boardArray[0][4] = new Tile(king, true, 1, 5);
    }

    private void setEmptyTiles() {
        for (int i = 0; i < boardArray.length; i++) {
            for (int j = 0; j < boardArray[0].length; j++) {
                if (boardArray[i][j] == null) {
                    boardArray[i][j] = new Tile(i + 1, j + 1);
                }
            }
        }
    }

    public void movePiece(Tile tileFrom, Tile tileTo) {
        int tileFromRow = tileFrom.getRow();
        int tileFromColumn = tileFrom.getColumn();
        int tileToRow = tileTo.getRow();
        int tileToColumn = tileTo.getColumn();
        Piece piece = tileFrom.getOccupyingPiece();

        boardArray[tileFromRow - 1][tileFromColumn - 1] = new Tile(tileFromRow, tileFromColumn);
        boardArray[tileToRow - 1][tileToColumn - 1] = new Tile(piece, true, tileToRow, tileToColumn);
    }

    public void movePiece(Piece piece, Tile tileTo) {
        int tileToRow = tileTo.getRow();
        int tileToColumn = tileTo.getColumn();

        boardArray[tileToRow - 1][tileToColumn - 1] = new Tile(piece, true, tileToRow, tileToColumn);
    }

    public void removeFromBoard(Tile tile) {
        int row = tile.getRow();
        int column = tile.getColumn();
        boardArray[row - 1][column - 1] = new Tile(row, column);
    }

    public void printBoard() {
        boolean isOccupied;
        Piece piece;
        for (int i = boardArray.length - 1; 0 <= i; i--) {
            for (int j = 0; j < boardArray[0].length; j++) {
                isOccupied = boardArray[i][j].isOccupied();
                if (isOccupied) {
                    piece = boardArray[i][j].getOccupyingPiece();
                    System.out.print(piece.getPieceCharacter());
                } else {
                    System.out.print('-');
                }
            }
        }
        System.out.println();
    }

    public Tile getTile(int row, int column) {
        if (1 <= row && row <= 5 && 1 <= column && column <= 5) {
            return boardArray[row - 1][column - 1];
        }
        return null;
    }

    public Piece getPiece(char pieceCharacter) {
        Piece potentialPiece;
        for (int i = 0; i < boardArray.length; i++) {
            for (int j = 0; j < boardArray[0].length; j++) {
                potentialPiece = boardArray[i][j].getOccupyingPiece();
                if (potentialPiece != null && potentialPiece.getPieceCharacter() == pieceCharacter){
                    return potentialPiece;
                }
            }
        }
        return null;
    }

    public Piece getPiece(int row, int column) {
        if (1 <= row && row <= 5 && 1 <= column && column <= 5) {
            Tile tile = boardArray[row - 1][column - 1];
            return tile.getOccupyingPiece();
        }
        return null;
    }

    public Tile[][] getBoardArray() {
        return boardArray;
    }
}