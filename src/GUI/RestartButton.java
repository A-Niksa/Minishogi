package GUI;

import javax.swing.*;
import java.awt.*;

public class RestartButton extends JButton {
    GameFrame gameFrame;

    public RestartButton(GameFrame gameFrame) {
        super("Restart");
        this.gameFrame = gameFrame;
        setBackground(Color.WHITE);
    }
}
