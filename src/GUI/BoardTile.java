package GUI;

import models.Piece;
import models.Tile;

import java.awt.*;

public class BoardTile extends ShogiTile implements DynamicComponent {
    private int row;
    private int column;

    public BoardTile(Dimension dimension, int row, int column) {
        super(dimension);
        this.row = row;
        this.column = column;
    }

    @Override
    public void update(GameStatus gameStatus) {
        Tile[][] boardArray = gameStatus.getBoard().getBoardArray();
        Piece piece = boardArray[5 - row][column - 1].getOccupyingPiece();
        char pieceCharacter;
        if (piece != null) {
            pieceCharacter = piece.getPieceCharacter();
            setPieceImage(getImageIdentifier(piece, pieceCharacter));
        } else {
            resetPieceImage();
        }
    }
}