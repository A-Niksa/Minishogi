package models;

public class Tile {
    private Piece occupyingPiece;
    private boolean isOccupied;
    private int row;
    private int column;

    Tile(Piece occupyingPiece, boolean isOccupied, int row, int column) {
        this.occupyingPiece = occupyingPiece;
        this.isOccupied = isOccupied;
        this.row = row;
        this.column = column;
    }

    Tile(int row, int column) {
        isOccupied = false;
        this.row = row;
        this.column = column;
    }

    public Piece getOccupyingPiece() {
        return occupyingPiece;
    }

    public void setOccupyingPiece(Piece occupyingPiece) {
        this.occupyingPiece = occupyingPiece;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
