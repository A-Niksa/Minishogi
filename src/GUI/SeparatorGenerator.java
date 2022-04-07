package GUI;

import javax.swing.*;
import java.awt.*;

public class SeparatorGenerator {
    public static void generateSeparators(GridBagConstraints gridBagConstraints, JPanel panel) {
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridx = 3;
        int[] gridSizes = {5, 35, 5, 3, 100, 3, 100, 3, 100, 3, 100, 3, 100, 3, 5, 35, 5};

        for (int i = 0; i < 16; i++) {
            gridBagConstraints.gridy = i;
            panel.add(new SeparatingSpace(new Dimension(16, gridSizes[i])), gridBagConstraints);
        }

        gridBagConstraints.gridx = 15;
        for (int i = 0; i < 16; i++) {
            gridBagConstraints.gridy = i;
            panel.add(new SeparatingSpace(new Dimension(16, gridSizes[i])), gridBagConstraints);
        }

        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridx = 7;
        for (int i = 0; i < 16; i++) {
            gridBagConstraints.gridy = i;
            panel.add(new SeparatingSpace(new Dimension(3, gridSizes[i])), gridBagConstraints);
        }

        gridBagConstraints.gridx = 9;
        for (int i = 0; i < 16; i++) {
            gridBagConstraints.gridy = i;
            panel.add(new SeparatingSpace(new Dimension(3, gridSizes[i])), gridBagConstraints);
        }

        gridBagConstraints.gridx = 11;
        for (int i = 0; i < 16; i++) {
            gridBagConstraints.gridy = i;
            panel.add(new SeparatingSpace(new Dimension(3, gridSizes[i])), gridBagConstraints);
        }

        gridBagConstraints.gridx = 13;
        for (int i = 0; i < 16; i++) {
            gridBagConstraints.gridy = i;
            panel.add(new SeparatingSpace(new Dimension(3, gridSizes[i])), gridBagConstraints);
        }
    }
}