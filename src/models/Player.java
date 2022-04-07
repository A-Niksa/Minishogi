package models;

import java.util.LinkedList;

public class Player {
    private boolean isBlack;
    private LinkedList<Piece> killedPieces;

    public Player(boolean isBlack) {
        this.isBlack = isBlack;
        killedPieces = new LinkedList<>();
    }

    public void printKilledPieces() {
        Piece killedPiece;
        for (int i = 0; i < killedPieces.size(); i++) {
            killedPiece = killedPieces.get(i);
            System.out.print(killedPiece.getPieceCharacter());
        }
        System.out.println();
    }

    public void addToKilledPieces(Piece piece) {
        boolean wasBlack = piece.isBlack();
        piece.setBlack(!wasBlack);
        piece.setPromoted(false);
        killedPieces.add(piece);
    }

    public void removeFromKilledPieces(Piece piece) {
        for (int i = 0; i < killedPieces.size(); i++) {
            if (killedPieces.get(i) == piece) {
                killedPieces.remove(i);
                return;
            }
        }
    }

    public Piece getPieceFromNothing(char pieceCharacter) {
        Piece killedPiece;
        for (int i = 0; i < killedPieces.size(); i++) {
            killedPiece = killedPieces.get(i);
            if (pieceCharacter == killedPiece.getPieceCharacter()) {
                return killedPiece;
            }
        }
        return null;
    }

    public LinkedList<Piece> getKilledPieces() {
        return killedPieces;
    }

    public boolean isBlack() {
        return isBlack;
    }

    public void setBlack(boolean black) {
        isBlack = black;
    }
}
