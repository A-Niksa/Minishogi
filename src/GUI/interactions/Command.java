package GUI.interactions;

public class Command {
    private Selection tileFromSelection;
    private Selection tileToSelection;

    public Command(Selection tileFromSelection, Selection tileToSelection) {
        this.tileFromSelection = tileFromSelection;
        this.tileToSelection = tileToSelection;
    }

    public Selection getTileFromSelection() {
        return tileFromSelection;
    }

    public Selection getTileToSelection() {
        return tileToSelection;
    }
}
