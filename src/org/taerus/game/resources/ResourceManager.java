package org.taerus.game.resources;


import javafx.scene.image.Image;

import java.util.HashMap;

public class ResourceManager {
    private static ResourceManager instance = new ResourceManager();
    public static ResourceManager getInstance() {
        return instance;
    }

    private HashMap<String, Image> images;
    // TODO state dependent resources

    private ResourceManager() {
        images = new HashMap<>();
    }

    public Image loadImage(String key, String url) {
        Image image = new Image(url, true);
        images.put(key, image);

        return image;
    }

}
