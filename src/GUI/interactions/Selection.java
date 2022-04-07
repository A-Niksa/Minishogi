package GUI.interactions;

import models.Board;
import models.Game;
import models.Piece;
import models.Player;

public class Selection {
    private int row;
    private int column;
    private TileType tileType;
    private Game game;
    private Board board;
    private Piece piece;

    public Selection(int row, int column, TileType tileType, Game game) {
        this.row = row;
        this.column = column;
        this.tileType = tileType;
        this.game = game;
        board = game.getBoard();
    }

    public String getPosition() {
        if (row == 0) {
            // black hand
            return "00";
        } else if (column == 0) {
            // white hand
            return "00";
        }
        return column + "" + row;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public TileType getTileType() {
        return tileType;
    }

    public Piece getPiece() {
        if (tileType == TileType.BOARD) {
            piece = board.getTile(6 - row, column).getOccupyingPiece();
        } else {
            if (row == 0) {
                Player black = game.getBlack();
                int index = column - 1;
                try {
                    piece = black.getKilledPieces().get(index);
                } catch (IndexOutOfBoundsException e) {
                    piece = null;
                }
            } else if (column == 0) {
                Player white = game.getWhite();
                int index = row - 1;
                try {
                    piece = white.getKilledPieces().get(index);
                } catch (IndexOutOfBoundsException e) {
                    piece = null;
                }
            }
        }
        return piece;
    }
}
