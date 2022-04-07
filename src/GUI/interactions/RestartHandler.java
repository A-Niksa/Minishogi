package GUI.interactions;

import GUI.GameFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestartHandler implements ActionListener {
    GameFrame gameFrame;

    public RestartHandler(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println();
        System.out.println("The game has been restarted");
        System.out.println("\n\n\n");
        gameFrame.restart();
    }
}
