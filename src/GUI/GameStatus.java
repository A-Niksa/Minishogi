package GUI;

import models.Board;
import models.Game;
import models.Piece;
import models.Player;

import java.util.LinkedList;

public class GameStatus {
    private Board board;
    private LinkedList<Piece> blackPlayerKilledPieces;
    private LinkedList<Piece> whitePlayerKilledPieces;
    private Player winner;
    private boolean gameHasEnded;

    public GameStatus(Game game) {
        board = game.getBoard();
        blackPlayerKilledPieces = game.getBlack().getKilledPieces();
        whitePlayerKilledPieces = game.getWhite().getKilledPieces();
        gameHasEnded = false;
    }

    public void updateGameStatus(Game game) {
        board = game.getBoard();
        blackPlayerKilledPieces = game.getBlack().getKilledPieces();
        whitePlayerKilledPieces = game.getWhite().getKilledPieces();
        gameHasEnded = game.gameHasEnded();
        if (gameHasEnded) {
            winner = game.getWinner();
        }
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public LinkedList<Piece> getBlackPlayerKilledPieces() {
        return blackPlayerKilledPieces;
    }

    public void setBlackPlayerKilledPieces(LinkedList<Piece> blackPlayerKilledPieces) {
        this.blackPlayerKilledPieces = blackPlayerKilledPieces;
    }

    public LinkedList<Piece> getWhitePlayerKilledPieces() {
        return whitePlayerKilledPieces;
    }

    public void setWhitePlayerKilledPieces(LinkedList<Piece> whitePlayerKilledPieces) {
        this.whitePlayerKilledPieces = whitePlayerKilledPieces;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public boolean gameHasEnded() {
        return gameHasEnded;
    }

    public void setGameHasEnded(boolean gameHasEnded) {
        this.gameHasEnded = gameHasEnded;
    }
}
