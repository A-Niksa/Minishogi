package GUI.resources;

public class ImagePathFinder {
    private final String piecesPath;
    private final String imageFormat;

    public ImagePathFinder() {
        piecesPath = "pieces/";
        imageFormat = ".png";
    }

    public String getPath(ImageIdentifier imageIdentifier) {
        switch (imageIdentifier) {
            case ICON:
                return "icon" + imageFormat;
            case BACKGROUND:
                return "background" + imageFormat;
            case PAWN_BLACK:
                return piecesPath + "pBlack" + imageFormat;
            case LANCE_BLACK:
                return piecesPath + "lBlack" + imageFormat;
            case BISHOP_BLACK:
                return piecesPath + "bBlack" + imageFormat;
            case SILVER_GENERAL_BLACK:
                return piecesPath + "sBlack" + imageFormat;
            case GOLD_GENERAL_BLACK:
                return piecesPath + "gBlack" + imageFormat;
            case KING_BLACK:
                return piecesPath + "kBlack" + imageFormat;
            case PAWN_BLACK_PROMOTED:
                return piecesPath + "pBlackP" + imageFormat;
            case LANCE_BLACK_PROMOTED:
                return piecesPath + "lBlackP" + imageFormat;
            case BISHOP_BLACK_PROMOTED:
                return piecesPath + "bBlackP" + imageFormat;
            case SILVER_GENERAL_BLACK_PROMOTED:
                return piecesPath + "sBlackP" + imageFormat;
            case PAWN_WHITE:
                return piecesPath + "pWhite" + imageFormat;
            case LANCE_WHITE:
                return piecesPath + "lWhite" + imageFormat;
            case BISHOP_WHITE:
                return piecesPath + "bWhite" + imageFormat;
            case SILVER_GENERAL_WHITE:
                return piecesPath + "sWhite" + imageFormat;
            case GOLD_GENERAL_WHITE:
                return piecesPath + "gWhite" + imageFormat;
            case KING_WHITE:
                return piecesPath + "kWhite" + imageFormat;
            case PAWN_WHITE_PROMOTED:
                return piecesPath + "pWhiteP" + imageFormat;
            case LANCE_WHITE_PROMOTED:
                return piecesPath + "lWhiteP" + imageFormat;
            case BISHOP_WHITE_PROMOTED:
                return piecesPath + "bWhiteP" + imageFormat;
            case SILVER_GENERAL_WHITE_PROMOTED:
                return piecesPath + "sWhiteP" + imageFormat;
            default:
                return null; // makes catching invalid images easier
        }
    }
}
