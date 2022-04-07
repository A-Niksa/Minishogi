package models.pieces;

import models.Board;
import models.Piece;
import models.Tile;

public class SilverGeneral extends Piece {
    public SilverGeneral(boolean isBlack) {
        super(isBlack);
    }

    @Override
    public char getPieceCharacter() {
        return isBlack ? 's' : 'S';
    }

    @Override
    public boolean isValidMove(Tile tileFrom, Tile tileTo) {
        return isValidNormalMove(tileFrom, tileTo);
    }

    public boolean isValidMove(Tile tileFrom, Tile tileTo, Board board) {
        if (isPromoted) {
            return isValidPromotedMove(tileFrom, tileTo, board);
        } else {
            return isValidNormalMove(tileFrom, tileTo);
        }
    }

    public boolean isValidPromotedMove(Tile tileFrom, Tile tileTo, Board board) {
        int tileFromRow = tileFrom.getRow();
        int tileFromColumn = tileFrom.getColumn();
        int tileToRow = tileTo.getRow();
        int tileToColumn = tileTo.getColumn();

        boolean tileFromIsWithinBoundaries = isWithinBoundaries(tileFromRow, tileFromColumn);
        boolean tileToIsWithinBoundaries = isWithinBoundaries(tileToRow, tileToColumn);
        if (!tileFromIsWithinBoundaries || !tileToIsWithinBoundaries) {
            return false;
        }

        boolean isValidPerpendicularMove = tileFromColumn == tileToColumn || tileFromRow == tileToRow;

        boolean isValidDiagonalMove = Math.abs(tileFromRow - tileToRow) == Math.abs(tileFromColumn - tileToColumn);

        int numberOfMoves = countMoves(tileFrom, tileTo, isValidPerpendicularMove, isValidDiagonalMove);
        boolean hasValidNumberOfMoves = numberOfMoves <= 2;

        boolean doesNotJumpOverPieces = !jumpsOverPieces(tileFrom, tileTo, numberOfMoves, isValidPerpendicularMove, isValidDiagonalMove, board);

        return doesNotJumpOverPieces && hasValidNumberOfMoves && (isValidPerpendicularMove || isValidDiagonalMove);
        // in reality, the 3rd and 4th conditions are not necessary since they uniquely determine the 2nd condition
        // however, they were added for clarity of the code
    }

    private boolean jumpsOverPieces(Tile tileFrom, Tile tileTo, int numberOfMoves, boolean isPerpendicular, boolean isDiagonal, Board board) {
        if (numberOfMoves == 1) {
            return false;
        } else {
            int tileFromRow = tileFrom.getRow();
            int tileFromColumn = tileFrom.getColumn();
            int tileToRow = tileTo.getRow();
            int tileToColumn = tileTo.getColumn();
            Tile[][] boardArray = board.getBoardArray();

            if (isPerpendicular) {
                if (tileFromRow == tileToRow) {
                    if (tileFromColumn > tileToColumn) {
                        return boardArray[tileFromRow - 1][tileFromColumn - 2].isOccupied();
                    } else {
                        return boardArray[tileFromRow - 1][tileFromColumn].isOccupied();
                    }
                } else {
                    if (tileFromRow > tileToRow) {
                        return boardArray[tileFromRow - 2][tileFromColumn - 1].isOccupied();
                    } else {
                        return boardArray[tileFromRow][tileToColumn -1].isOccupied();
                    }
                }
            } else if (isDiagonal) {
                if (tileFromRow < tileToRow) {
                    if (tileFromColumn < tileToColumn) {
                        return boardArray[tileFromRow][tileFromColumn].isOccupied();
                    } else {
                        return boardArray[tileFromRow][tileFromColumn - 2].isOccupied();
                    }
                } else {
                    if (tileFromColumn < tileToColumn) {
                        return boardArray[tileFromRow - 2][tileFromColumn].isOccupied();
                    } else {
                        return boardArray[tileFromRow - 2][tileFromColumn - 2].isOccupied();
                    }
                }
            } else {
                return true; // dummy condition
            }
        }
    }

    private int countMoves(Tile tileFrom, Tile tileTo, boolean isPerpendicular, boolean isDiagonal) {
        int tileFromRow = tileFrom.getRow();
        int tileFromColumn = tileFrom.getColumn();
        int tileToRow = tileTo.getRow();
        int tileToColumn = tileTo.getColumn();

        if (isPerpendicular) {
            if (tileFromRow == tileToRow) {
                return Math.abs(tileFromColumn - tileToColumn);
            } else {
                return Math.abs(tileFromRow - tileToRow);
            }
        } else if (isDiagonal) {
            return Math.abs(tileFromRow - tileToRow); // no different from Math.abs(tileFromColumn - tileToColumn)
        } else {
            return Integer.MAX_VALUE; // to bypass the '<= 2' condition
        }
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

        int[][] neighbours = getNeighbours(tileFromRow, tileFromColumn);
        boolean willGoToNeighbour = isInArray(new int[] {tileToRow, tileToColumn}, neighbours);
        return willGoToNeighbour;
    }

    private int[][] getNeighbours(int currentTileRow, int currentTileColumn) { // for GoldGeneral
        int[][] neighbours = new int[5][2];
        if (isBlack) {
            // clockwise starting from north-west:
            neighbours[0] = new int[] {currentTileRow - 1, currentTileColumn - 1};
            neighbours[1] = new int[] {currentTileRow - 1, currentTileColumn + 1};
            neighbours[2] = new int[] {currentTileRow + 1, currentTileColumn + 1};
            neighbours[3] = new int[] {currentTileRow + 1, currentTileColumn};
            neighbours[4] = new int[] {currentTileRow + 1, currentTileColumn - 1};
        } else {
            // clockwise starting from north-west:
            neighbours[0] = new int[] {currentTileRow - 1, currentTileColumn - 1};
            neighbours[1] = new int[] {currentTileRow - 1, currentTileColumn};
            neighbours[2] = new int[] {currentTileRow - 1, currentTileColumn + 1};
            neighbours[3] = new int[] {currentTileRow + 1, currentTileColumn + 1};
            neighbours[4] = new int[] {currentTileRow + 1, currentTileColumn - 1};
        }
        return neighbours;
    }
}
