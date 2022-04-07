package models;

import GUI.GameStatus;
import models.pieces.*;

import java.util.LinkedList;

public class Game {
    private Player black;
    private Player white;
    private Board board;
    private boolean isBlackTurn;
    private boolean gameHasEnded;
    private Player winner;

    public Game() {
        black = new Player(true);
        white = new Player(false);
        board = new Board();
        isBlackTurn = true;
        gameHasEnded = false;
    }

    public void advanceGame(char pieceCharacter, String tileFromString, String tileToString) {
        // tileFromString -> tileFrom String (NOT tile FromString)
        // tileToString is similarly defined

        Player currentPlayer;
        if (isBlackTurn) {
            currentPlayer = black;
        } else {
            currentPlayer = white;
        }

        Tile tileTo = processTileString(tileToString);
        if (tileTo == null) {
            return;
        }

        boolean pieceComesFromNothing = pieceComesFromNothing(tileFromString);
        if (pieceComesFromNothing) {
            Piece pieceFromNothing = currentPlayer.getPieceFromNothing(pieceCharacter);
            if (pieceFromNothing == null) {
                return;
            }

            int tileToRow = tileTo.getRow();
            int tileToColumn = tileTo.getColumn();
            boolean isWithinBoundaries = pieceFromNothing.isWithinBoundaries(tileToRow, tileToColumn);
            boolean hitsOwnPieces = pieceFromNothing.hitsOwnPieces(pieceFromNothing, tileTo);
            if (!isWithinBoundaries || hitsOwnPieces) {
                return;
            }

            boolean willKill = willKill(tileTo, currentPlayer);
            if (willKill) {
                return;
            }

            board.movePiece(pieceFromNothing, tileTo);
            currentPlayer.removeFromKilledPieces(pieceFromNothing);
        } else {
            Tile tileFrom = processTileString(tileFromString);
            if (tileFrom == null) {
                return;
            }

            Piece piece = board.getPiece(tileFrom.getRow(), tileFrom.getColumn());
            if (piece == null || piece.isBlack() != currentPlayer.isBlack()) {
                return;
            }

            boolean isValidMove;
            if (piece instanceof SilverGeneral && piece.isPromoted()) {
                SilverGeneral silverGeneral = (SilverGeneral) piece;
                isValidMove = silverGeneral.isValidMove(tileFrom, tileTo, board);
            } else {
                isValidMove = piece.isValidMove(tileFrom, tileTo);
            }

            boolean hitsOwnPieces = piece.hitsOwnPieces(piece, tileTo);
            boolean jumpsOverPieces = jumpsOverPieces(tileFrom, tileTo);
            if (!isValidMove || hitsOwnPieces || jumpsOverPieces) {
                return;
            }

            checkForKill(tileTo, currentPlayer);
            board.movePiece(tileFrom, tileTo);
        }

        if (!pieceComesFromNothing) {
            checkForPromotion(tileTo, currentPlayer);
        }

        Player potentialWinner = checkForWinners();
        if (potentialWinner != null) {
            setGameHasEnded(true);
            setWinner(potentialWinner);
            return;
        }

        if (currentPlayer == black) {
            isBlackTurn = false;
        } else {
            isBlackTurn = true;
        }
    }

    private boolean jumpsOverPieces(Tile tileFrom, Tile tileTo) {
        int tileFromRow = tileFrom.getRow();
        int tileFromColumn = tileFrom.getColumn();
        int tileToRow = tileTo.getRow();
        int tileToColumn = tileTo.getColumn();
        Tile[][] boardArray = board.getBoardArray();

        boolean isPerpendicular = isPerpendicular(tileFrom, tileTo); // valid moves in minishogi are only diagonal/perpendicular
        // note that if the move is not valid, it would be flagged anyway

        if (isPerpendicular) {
            if (tileFromRow == tileToRow) {
                int startingColumn = Math.min(tileFromColumn, tileToColumn);
                int endingColumn = Math.max(tileFromColumn, tileToColumn);
                for (int i = startingColumn; i < endingColumn - 1; i++) {
                    if (boardArray[tileFromRow - 1][i].isOccupied()) {
                        return true;
                    }
                }
            } else {
                int startingRow = Math.min(tileFromRow, tileToRow);
                int endingRow = Math.max(tileFromRow, tileToRow);
                for (int i = startingRow; i < endingRow - 1; i++) {
                    if (boardArray[i][tileFromColumn - 1].isOccupied()) {
                        return true;
                    }
                }
            }
        } else { // otherwise, assumed to be diagonal
            int startingRow = Math.min(tileFromRow, tileToRow);
            int endingRow = Math.max(tileFromRow, tileToRow);
            int startingColumn = Math.min(tileFromColumn, tileToColumn);

            if (tileFromRow < tileToRow ^ tileFromColumn < tileToColumn) {
                for (int i = 0; i < endingRow - startingRow - 1; i++) {
                    if (boardArray[startingRow - i][startingColumn + i].isOccupied()) {
                        return true;
                    }
                }
            } else {
                for (int i = 0; i < endingRow - startingRow - 1; i++) {
                    if (boardArray[startingRow + i][startingColumn + i].isOccupied()) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean isPerpendicular(Tile tileFrom, Tile tileTo) {
        int tileFromRow = tileFrom.getRow();
        int tileFromColumn = tileFrom.getColumn();
        int tileToRow = tileTo.getRow();
        int tileToColumn = tileTo.getColumn();

        return tileFromRow == tileToRow || tileFromColumn == tileToColumn;
    }

    private void checkForPromotion(Tile tileTo, Player currentPlayer) {
        int tileToRow = tileTo.getRow();
        int tileToColumn = tileTo.getColumn();
        boolean isBlack = currentPlayer.isBlack();

        if (isBlack) {
            if (tileToRow == 4 || tileToRow == 5) {
                Piece piece = board.getPiece(tileToRow, tileToColumn);
                if (pieceCanBePromoted(piece)) {
                    piece.setPromoted(true);
                }
            }
        } else {
            if (tileToRow == 1 || tileToRow == 2) {
                Piece piece = board.getPiece(tileToRow, tileToColumn);
                if (pieceCanBePromoted(piece)) {
                    piece.setPromoted(true);
                }
            }
        }
    }

    private boolean pieceCanBePromoted(Piece piece) {
        return piece instanceof Pawn || piece instanceof Lance || piece instanceof Bishop || piece instanceof SilverGeneral;
    }

    private boolean willKill(Tile tileTo, Player currentPlayer) {
        Piece killedPiece = getKilledPiece(tileTo, currentPlayer);
        return killedPiece != null;
    }

    private void checkForKill(Tile tileTo, Player currentPlayer) {
        Piece killedPiece = getKilledPiece(tileTo, currentPlayer);
        if (killedPiece != null) {
            currentPlayer.addToKilledPieces(killedPiece);
            board.removeFromBoard(tileTo);
        }
    }

    private boolean pieceComesFromNothing(String tileString) {
        return tileString.charAt(0) == '0' && tileString.charAt(1) == '0';
    }

    private Piece getKilledPiece(Tile tileTo, Player currentPlayer) {
        int tileToRow = tileTo.getRow();
        int tileToColumn = tileTo.getColumn();
        Piece potentiallyKilledPiece = board.getPiece(tileToRow, tileToColumn);
        if (potentiallyKilledPiece != null && (potentiallyKilledPiece.isBlack() ^ currentPlayer.isBlack())) {
            return potentiallyKilledPiece;
        } else {
            return null;
        }
    }

    private Tile processTileString(String tileString) {
        if (tileString.length() == 2) {
            int row = tileString.charAt(1) - '0';
            row = 6 - row;
            int column = tileString.charAt(0) - '0';
            return board.getTile(row, column);
        }
        return null;
    }

    private Player checkForWinners() {
        Tile[][] boardArray = board.getBoardArray();
        Piece piece;
        LinkedList<Piece> kings = new LinkedList<>();
        for (int i = 0; i < boardArray.length; i++) {
            for (int j = 0; j < boardArray[0].length; j++) {
                piece = boardArray[i][j].getOccupyingPiece();
                if (piece instanceof King) {
                    kings.add(piece);
                }
            }
        }

        Piece aliveKing;
        if (kings.size() == 2) {
            return null;
        } else {
            aliveKing = kings.get(0);
            if (aliveKing.isBlack) {
                return black;
            } else {
                return white;
            }
        }
    }

    public Player getBlack() {
        return black;
    }

    public void setBlack(Player black) {
        this.black = black;
    }

    public Player getWhite() {
        return white;
    }

    public void setWhite(Player white) {
        this.white = white;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public boolean isBlackTurn() {
        return isBlackTurn;
    }

    public void setBlackTurn(boolean blackTurn) {
        isBlackTurn = blackTurn;
    }

    public boolean gameHasEnded() {
        return gameHasEnded;
    }

    public void setGameHasEnded(boolean gameHasEnded) {
        this.gameHasEnded = gameHasEnded;
    }

    public Player getWinner() {
        return winner;
    }

    private void setWinner(Player winner) {
        this.winner = winner;
    }
}