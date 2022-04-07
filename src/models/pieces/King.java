package models.pieces;

import models.Piece;
import models.Tile;

public class King extends Piece {
    public King(boolean isBlack) {
        super(isBlack);
    }

    @Override
    public char getPieceCharacter() {
        return isBlack ? 'k' : 'K';
    }

    @Override
    public boolean isValidMove(Tile tileFrom, Tile tileTo) {
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

    private int[][] getNeighbours(int currentTileRow, int currentTileColumn) {
        int[][] neighbours = new int[8][2];
        // clockwise starting from west:
        neighbours[0] = new int[] {currentTileRow, currentTileColumn - 1};
        neighbours[1] = new int[] {currentTileRow - 1, currentTileColumn - 1};
        neighbours[2] = new int[] {currentTileRow - 1, currentTileColumn};
        neighbours[3] = new int[] {currentTileRow - 1, currentTileColumn + 1};
        neighbours[4] = new int[] {currentTileRow, currentTileColumn + 1};
        neighbours[5] = new int[] {currentTileRow + 1, currentTileColumn + 1};
        neighbours[6] = new int[] {currentTileRow + 1, currentTileColumn};
        neighbours[7] = new int[] {currentTileRow + 1, currentTileColumn - 1};
        return neighbours;
    }
}
