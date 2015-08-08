package org.taerus.game.engine;


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

    public Image loadImage(String key, String url, Boolean backgroundLoading) {
        Image image = new Image(url, backgroundLoading);
        images.put(key, image);

        return image;
    }

    public Image loadImage(String key, String url) {
        return loadImage(key, url, true);
    }

}
