package GUI;

import GUI.interactions.*;
import GUI.resources.ImageIdentifier;
import GUI.resources.ImageManager;
import models.Board;
import models.Game;
import models.Piece;
import models.Player;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class GameFrame extends JFrame {
    private static final int PANEL_WIDTH = 760;
    private static final int PANEL_HEIGHT = 608;
    private static final int FRAME_WIDTH = PANEL_WIDTH + 10;
    private static final int FRAME_HEIGHT = PANEL_HEIGHT + 35;
    private static final int TILE_SIZE = 100;
    private static final int BOARD_X = 121;
    private static final int BOARD_Y = 48;
    private static final int BORDER_SIZE = 3;

    private JPanel pane;
    private JPanel panel;
    private JLabel background;
    private DynamicLabel gameStatusNotifier;
    private RestartButton restartButton;

    private LinkedList<BoardTile> boardTiles;
    private LinkedList<HandTile> blackHandTiles;
    private LinkedList<HandTile> whiteHandTiles;

    private LinkedList<DynamicComponent> dynamicComponents;

    private Queue<Selection> selections;

    private LinkedList<JButton> clickableComponents; // except for the restart button

    private Game game;
    private GameStatus gameStatus;

    public GameFrame(Game game) {
        this.game = game;
        gameStatus = new GameStatus(game);

        setFrame();
        initializeComponents();
        alignComponents();

        updateComponents(gameStatus);

        repaintFrame();
    }

    private void setFrame() {
        setVisible(true);
        setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Minishogi");
        setIconImage(ImageManager.getImage(ImageIdentifier.ICON));
    }

    private void initializeComponents() {
        pane = new JPanel();
        setContentPane(pane);

        panel = new JPanel();
        panel.setOpaque(false);

        background = new JLabel(new ImageIcon(ImageManager.getImage(ImageIdentifier.BACKGROUND)));

        Dimension shogiTileDimension = new Dimension(TILE_SIZE, TILE_SIZE);

        boardTiles = new LinkedList<>();
        BoardTile boardTile;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                boardTile = new BoardTile(shogiTileDimension, i+1, j+1);
                boardTiles.add(boardTile);
            }
        }

        blackHandTiles = new LinkedList<>();
        HandTile blackHandTile;
        for (int i = 0; i < 5; i++) {
            blackHandTile = new HandTile(shogiTileDimension, true, i);
            blackHandTiles.add(blackHandTile);
        }

        whiteHandTiles = new LinkedList<>();
        HandTile whiteHandTile;
        for (int i = 0; i < 5; i++) {
            whiteHandTile = new HandTile(shogiTileDimension, false, i);
            whiteHandTiles.add(whiteHandTile);
        }

        gameStatusNotifier = new DynamicLabel();

        selections = new LinkedList<>();

        dynamicComponents = new LinkedList<>();

        clickableComponents = new LinkedList<>();

        restartButton = new RestartButton(this);

        addEventHandlers();

        addDynamicComponents();

        addClickableComponents();
    }

    private void alignComponents() {
        pane.setLayout(null);

        pane.add(panel);

        pane.add(gameStatusNotifier);
        gameStatusNotifier.setBounds(15, 5, 200, 35);

        pane.add(background);
        background.setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);

        pane.setLayout(null);

        panel.setLayout(new GridBagLayout());
        panel.setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.CENTER;

        SeparatorGenerator.generateSeparators(gridBagConstraints, panel);

        int tileX = 6;
        int tileY = 12;
        BoardTile boardTile;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                boardTile = boardTiles.get(i * 5 + j);

                gridBagConstraints.gridwidth = 1;
                gridBagConstraints.gridheight = 1;
                gridBagConstraints.gridx = tileX;
                gridBagConstraints.gridy = tileY;

                panel.add(boardTile, gridBagConstraints);

                tileX += 2;
            }
            tileX = 6;
            tileY -= 2;
        }

        tileX = 18;
        tileY = 4;
        HandTile blackHandTile;
        for (int i = 0; i < 5; i++) {
            blackHandTile = blackHandTiles.get(i);

            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.gridheight = 1;
            gridBagConstraints.gridx = tileX;
            gridBagConstraints.gridy = tileY;

            panel.add(blackHandTile, gridBagConstraints);

            tileY += 2;
        }

        tileX = 2;
        tileY = 12;
        HandTile whiteHandTile;
        for (int i = 0; i < 5; i++) {
            whiteHandTile = whiteHandTiles.get(i);

            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.gridheight = 1;
            gridBagConstraints.gridx = tileX;
            gridBagConstraints.gridy = tileY;

            panel.add(whiteHandTile, gridBagConstraints);

            tileY -= 2;
        }

        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridx = 18;
        gridBagConstraints.gridy = 1;
        panel.add(restartButton, gridBagConstraints);
    }

    private void addClickableComponents() {
        clickableComponents.addAll(boardTiles);
        clickableComponents.addAll(blackHandTiles);
        clickableComponents.addAll(whiteHandTiles);
    }

    private void addEventHandlers() {
        int row, column;

        for (int i = 0; i < 25; i++) {
            row = i / 5 + 1;
            column = i % 5 + 1;
            boardTiles.get(i).addActionListener(new EventHandler(this,
                    new Selection(row, column, TileType.BOARD, game)));
        }

        for (int i = 0; i < 5; i++) {
            blackHandTiles.get(i).addActionListener(new EventHandler(this,
                    new Selection(0, i+1, TileType.HAND, game))); // left hand: row = 0 by convention
        }

        for (int i = 0; i < 5; i++) {
            whiteHandTiles.get(i).addActionListener(new EventHandler(this,
                    new Selection(i+1, 0, TileType.HAND, game))); // right hand: column = 0 by convention
        }

        restartButton.addActionListener(new RestartHandler(this));
    }

    private void addDynamicComponents() {
        dynamicComponents.addAll(boardTiles);
        dynamicComponents.addAll(blackHandTiles);
        dynamicComponents.addAll(whiteHandTiles);

        dynamicComponents.add(gameStatusNotifier);
    }

    private void updateComponents(GameStatus gameStatus) {
        for (DynamicComponent dynamicComponent : dynamicComponents) {
            dynamicComponent.update(gameStatus);
        }
        repaintFrame();

        if (gameStatus.gameHasEnded()) {
            for (int i = 0; i < clickableComponents.size(); i++) {
                clickableComponents.get(i).setEnabled(false);
            }
        }
    }

    public void addSelection(Selection selection) {
        selections.add(selection);
        if (selections.size() > 1) {
            Selection tileFromSelection = selections.poll();
            Selection tileToSelection = selections.poll();
            Command command = new Command(tileFromSelection, tileToSelection);
            String[] processedCommand = processCommandAsString(command);
            char pieceCharacter = processedCommand[0].charAt(0);
            game.advanceGame(pieceCharacter, processedCommand[1], processedCommand[2]);
            gameStatus.updateGameStatus(game);
            updateComponents(gameStatus);

            printConsoleLog(processedCommand);
        }
    }

    public void printConsoleLog(String[] processedCommand) {
        System.out.println();
        System.out.println(processedCommand[0] + " " + processedCommand[1] + " " + processedCommand[2]);
        Board board = game.getBoard();
        Player black = game.getBlack();
        Player white = game.getWhite();
        board.printBoard();
        black.printKilledPieces();
        white.printKilledPieces();
        System.out.println();
    }

    private String[] processCommandAsString(Command command) {
        String[] processedCommand = new String[3];
        Selection tileFromSelection = command.getTileFromSelection();
        Selection tileToSelection = command.getTileToSelection();
        Piece piece = tileFromSelection.getPiece();

        if (piece == null) {
            processedCommand[0] = "-";
        } else {
            processedCommand[0] = "" + tileFromSelection.getPiece().getPieceCharacter();
        }

        processedCommand[1] = tileFromSelection.getPosition();
        processedCommand[2] = tileToSelection.getPosition();

        return processedCommand;
    }

    private void repaintFrame() {
        revalidate();
        repaint();
    }

    public void restart() {
        dispose();
        Game game = new Game();
        GameFrame gameFrame = new GameFrame(game);
    }
}