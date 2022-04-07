package GUI.interactions;

import GUI.GameFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventHandler implements ActionListener {
    GameFrame gameFrame;
    Selection selection;

    public EventHandler(GameFrame gameFrame, Selection selection) {
        this.gameFrame = gameFrame;
        this.selection = selection;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("selected column: " + selection.getColumn() + " - " + "selected row: " + selection.getRow());
        gameFrame.addSelection(selection);
    }
}
