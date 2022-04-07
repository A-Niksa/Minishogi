package GUI;

import models.Player;

import javax.swing.*;

public class DynamicLabel extends JLabel implements DynamicComponent {
    public DynamicLabel() {
        super("Game Status: Ongoing", JLabel.LEFT);
    }

    @Override
    public void update(GameStatus gameStatus) {
        boolean gameHasEnded = gameStatus.gameHasEnded();
        if (gameHasEnded) {
            Player winner = gameStatus.getWinner();
            if (winner.isBlack()) {
                setText("Game Status: Black wins!");
            } else {
                setText("Game Status: White wins!");
            }
        }
    }
}
