package GUI.resources;

import java.awt.*;

public class ImageManager {
    private static ImageManager imageManager = new ImageManager();

    private final String RESOURCES_PATH = "src/GUI/assets/";
    private final ImagePathFinder imagePathFinder;
    private final ImageCache imageCache;

    public ImageManager() {
        imagePathFinder = new ImagePathFinder();
        imageCache = new ImageCache();
    }

    public static Image getImage(ImageIdentifier imageIdentifier) {
        return imageManager.getImageByManager(imageIdentifier);
    }

    public Image getImageByManager(ImageIdentifier imageIdentifier) {
        return imageCache.getImage(imageIdentifier,
                identifier -> Toolkit.getDefaultToolkit().getImage(getFullPath(identifier))
                );
    }

    private String getFullPath(ImageIdentifier imageIdentifier) {
        return RESOURCES_PATH + imagePathFinder.getPath(imageIdentifier);
    }
}
