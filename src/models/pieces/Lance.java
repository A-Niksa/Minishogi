package models.pieces;

import models.Piece;
import models.Tile;

public class Lance extends Piece {
    public Lance(boolean isBlack) {
        super(isBlack);
    }

    @Override
    public char getPieceCharacter() {
        return isBlack ? 'l' : 'L';
    }

    @Override
    public boolean isValidMove(Tile tileFrom, Tile tileTo) {
        if (isPromoted) {
            return isValidPromotedMove(tileFrom, tileTo);
        } else {
            return isValidNormalMove(tileFrom, tileTo);
        }
    }

    public boolean isValidPromotedMove(Tile tileFrom, Tile tileTo) {
        int tileFromRow = tileFrom.getRow();
        int tileFromColumn = tileFrom.getColumn();
        int tileToRow = tileTo.getRow();
        int tileToColumn = tileTo.getColumn();

        boolean tileFromIsWithinBoundaries = isWithinBoundaries(tileFromRow, tileFromColumn);
        boolean tileToIsWithinBoundaries = isWithinBoundaries(tileToRow, tileToColumn);
        if (!tileFromIsWithinBoundaries || !tileToIsWithinBoundaries) {
            return false;
        }

        return tileFromColumn == tileToColumn || tileFromRow == tileToRow;
    }

    public boolean isValidNormalMove(Tile tileFrom, Tile tileTo) {
        int tileFromRow = tileFrom.getRow();
        int tileFromColumn = tileFrom.getColumn();
        int tileToRow = tileTo.getRow();
        int tileToColumn = tileTo.getColumn();

        boolean tileFromIsWithinBoundaries = isWithinBoundaries(tileFromRow, tileFromColumn);
        boolean tileToIsWithinBoundaries = isWithinBoundaries(tileToRow, tileToColumn);
        if (!tileFromIsWithinBoundaries || !tileToIsWithinBoundaries) {
            return false;
        }

        boolean goesForward;
        if (isBlack) {
            goesForward = tileFromRow < tileToRow;
        } else {
            goesForward = tileFromRow > tileToRow;
        }

        return goesForward && tileFromColumn == tileToColumn;
    }
}