package GUI;

import models.Piece;

import java.awt.*;
import java.util.LinkedList;

public class HandTile extends ShogiTile implements DynamicComponent {
    private int index;
    private boolean isBlack;

    public HandTile(Dimension dimension, boolean isBlack, int index) {
        super(dimension);
        this.isBlack = isBlack;
        this.index = index;
    }

    @Override
    public void update(GameStatus gameStatus) {
        LinkedList<Piece> killedPieces;
        if (isBlack) {
            killedPieces = gameStatus.getBlackPlayerKilledPieces();
        } else {
            killedPieces = gameStatus.getWhitePlayerKilledPieces();
        }
        try {
            Piece killedPiece = killedPieces.get(index);
            char killedPieceCharacter = killedPiece.getPieceCharacter();
            setPieceImage(getImageIdentifier(killedPiece, killedPieceCharacter));
        } catch (IndexOutOfBoundsException e) {
            resetPieceImage();
        }
    }
}
