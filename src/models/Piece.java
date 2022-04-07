package models;

public abstract class Piece {
    protected boolean isPromoted;
    protected boolean isBlack;

    protected Piece(boolean isBlack) {
        isPromoted = false;
        this.isBlack = isBlack;
    }

    protected boolean isWithinBoundaries(int tileRow, int tileColumn) {
        return (1 <= tileRow && tileRow <= 5) && (1 <= tileColumn && tileColumn <= 5);
    }

    protected boolean hitsOwnPieces(Piece piece, Tile tileTo) {
        Piece hitPiece = tileTo.getOccupyingPiece();
        if (hitPiece != null) {
            return piece.isBlack() == hitPiece.isBlack();
        }
        return false;
    }

    protected boolean isInArray(int[] array, int[][] superArray) {
        for (int[] ints : superArray) {
            if (array[0] == ints[0] && array[1] == ints[1]) {
                return true;
            }
        }
        return false;
    }

    public abstract char getPieceCharacter();

    protected abstract boolean isValidMove(Tile tileFrom, Tile tileTo);

    public boolean isPromoted() {
        return isPromoted;
    }

    public void setPromoted(boolean promoted) {
        isPromoted = promoted;
    }

    public boolean isBlack() {
        return isBlack;
    }

    public void setBlack(boolean black) {
        isBlack = black;
    }
}
