// Arsha Niksa
// Student Number: *********
// 04/01/2022

import models.Board;
import models.Game;
import models.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game();
        Board board = game.getBoard();
        Player black = game.getBlack();
        Player white = game.getWhite();

        boolean gameHasEnded = false;
        String[] userInput;
        char pieceCharacter;
        while(!gameHasEnded) {
            userInput = scanner.nextLine().split("\\s+");
            if (userInput.length == 1 && userInput[0].equals("0")) {
                System.exit(0);
            }
            if (userInput.length != 3) {
                continue;
            }

            pieceCharacter = userInput[0].charAt(0);
            game.advanceGame(pieceCharacter, userInput[1], userInput[2]);
            gameHasEnded = game.gameHasEnded();

            printStatus(board, black, white);
        }

        Player winner = game.getWinner();
        if (winner.isBlack()) {
            System.out.println("black wins!");
        } else {
            System.out.println("white wins!");
        }
    }

    private static void printStatus(Board board, Player black, Player white) {
        board.printBoard();
        black.printKilledPieces();
        white.printKilledPieces();
    }
}
