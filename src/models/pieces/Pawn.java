package models.pieces;

import models.Piece;
import models.Tile;

public class Pawn extends Piece {
    public Pawn(boolean isBlack) {
        super(isBlack);
    }

    @Override
    public char getPieceCharacter() {
        return isBlack ? 'p' : 'P';
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

        int[][] neighbours = getNeighbours(tileFromRow, tileFromColumn);
        boolean willGoToNeighbour = isInArray(new int[] {tileToRow, tileToColumn}, neighbours);
        return willGoToNeighbour;
    }

    private int[][] getNeighbours(int currentTileRow, int currentTileColumn) { // for GoldGeneral
        int[][] neighbours = new int[6][2];
        if (isBlack) {
            // clockwise starting from west:
            neighbours[0] = new int[] {currentTileRow, currentTileColumn - 1};
            neighbours[1] = new int[] {currentTileRow - 1, currentTileColumn};
            neighbours[2] = new int[] {currentTileRow, currentTileColumn + 1};
            neighbours[3] = new int[] {currentTileRow + 1, currentTileColumn + 1};
            neighbours[4] = new int[] {currentTileRow + 1, currentTileColumn};
            neighbours[5] = new int[] {currentTileRow + 1, currentTileColumn - 1};
        } else {
            // clockwise starting from west:
            neighbours[0] = new int[] {currentTileRow, currentTileColumn - 1};
            neighbours[1] = new int[] {currentTileRow - 1, currentTileColumn - 1};
            neighbours[2] = new int[] {currentTileRow - 1, currentTileColumn};
            neighbours[3] = new int[] {currentTileRow - 1, currentTileColumn + 1};
            neighbours[4] = new int[] {currentTileRow, currentTileColumn + 1};
            neighbours[5] = new int[] {currentTileRow + 1, currentTileColumn};
        }
        return neighbours;
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

        if (isBlack) {
            return tileFromColumn == tileToColumn && tileToRow - 1 == tileFromRow;
        } else {
            return tileFromColumn == tileToColumn && tileFromRow - 1 == tileToRow;
        }
    }
}
