package GUI;

import GUI.resources.ImageIdentifier;
import GUI.resources.ImageManager;
import models.Piece;

import javax.swing.*;
import java.awt.*;

public class ShogiTile extends JButton {
    private Piece piece;
    private final Dimension dimension;
    private Image pieceImage;
    private int pieceWidth;
    private int pieceHeight;
    private final int TILE_SIZE;

    public ShogiTile(Dimension dimension) {
        this.dimension = dimension;
        TILE_SIZE = dimension.width;
        setLayout(null);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusable(false);
        setPreferredSize(dimension);
    }

    protected ImageIdentifier getImageIdentifier(Piece piece, char pieceCharacter) {
        if (!piece.isPromoted()) {
            switch (pieceCharacter) {
                case 'p':
                    return ImageIdentifier.PAWN_BLACK;
                case 'l':
                    return ImageIdentifier.LANCE_BLACK;
                case 'b':
                    return ImageIdentifier.BISHOP_BLACK;
                case 's':
                    return ImageIdentifier.SILVER_GENERAL_BLACK;
                case 'g':
                    return ImageIdentifier.GOLD_GENERAL_BLACK;
                case 'k':
                    return ImageIdentifier.KING_BLACK;
                case 'P':
                    return ImageIdentifier.PAWN_WHITE;
                case 'L':
                    return ImageIdentifier.LANCE_WHITE;
                case 'B':
                    return ImageIdentifier.BISHOP_WHITE;
                case 'S':
                    return ImageIdentifier.SILVER_GENERAL_WHITE;
                case 'G':
                    return ImageIdentifier.GOLD_GENERAL_WHITE;
                case 'K':
                    return ImageIdentifier.KING_WHITE;
                default:
                    return null;
            }
        } else {
            switch (pieceCharacter) {
                case 'p':
                    return ImageIdentifier.PAWN_BLACK_PROMOTED;
                case 'l':
                    return ImageIdentifier.LANCE_BLACK_PROMOTED;
                case 'b':
                    return ImageIdentifier.BISHOP_BLACK_PROMOTED;
                case 's':
                    return ImageIdentifier.SILVER_GENERAL_BLACK_PROMOTED;
                case 'P':
                    return ImageIdentifier.PAWN_WHITE_PROMOTED;
                case 'L':
                    return ImageIdentifier.LANCE_WHITE_PROMOTED;
                case 'B':
                    return ImageIdentifier.BISHOP_WHITE_PROMOTED;
                case 'S':
                    return ImageIdentifier.SILVER_GENERAL_WHITE_PROMOTED;
                default:
                    return null;
            }
        }
    }

    public void resetPieceImage() {
        removeAll();
    }

    public void setPieceImage(ImageIdentifier imageIdentifier) {
        resetPieceImage();
        pieceImage = ImageManager.getImage(imageIdentifier);
        ImageIcon imageIcon = new ImageIcon(pieceImage);
        pieceWidth = imageIcon.getIconWidth();
        pieceHeight = imageIcon.getIconHeight();
        JLabel label = new JLabel(imageIcon);
        add(label);
        label.setBounds((TILE_SIZE - pieceWidth) / 2, (TILE_SIZE - pieceHeight) / 2, pieceWidth, pieceHeight);
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
