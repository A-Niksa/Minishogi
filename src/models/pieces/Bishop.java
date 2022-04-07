package models.pieces;

import models.Piece;
import models.Tile;

public class Bishop extends Piece {
    public Bishop(boolean isBlack) {
        super(isBlack);
    }

    @Override
    public char getPieceCharacter() {
        return isBlack ? 'b' : 'B';
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

        int difference = Math.abs(tileFromRow - tileToRow);
        boolean isValidDiagonalMove = Math.abs(tileFromColumn - tileToColumn) == difference;

        boolean isValidUpwardsMove = tileFromColumn == tileToColumn && Math.abs(tileFromRow - tileToRow) == 1;
        boolean isValidSidewardsMove = tileFromRow == tileToRow && Math.abs(tileFromColumn - tileToColumn) == 1;
        boolean isValidPerpendicularMove = isValidUpwardsMove || isValidSidewardsMove;

        return isValidDiagonalMove || isValidPerpendicularMove;
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

        int verticalDifference = Math.abs(tileFromRow - tileToRow);
        int horizontalDifference = Math.abs(tileFromColumn - tileToColumn);
        return goesForward && verticalDifference == horizontalDifference;
    }
}
