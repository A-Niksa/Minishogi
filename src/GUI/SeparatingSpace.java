package GUI;

import javax.swing.*;
import java.awt.*;

public class SeparatingSpace extends JLabel {
    public SeparatingSpace(Dimension dimension) {
        super("", JLabel.CENTER);
        setPreferredSize(dimension);
        setOpaque(false);
    }
}
