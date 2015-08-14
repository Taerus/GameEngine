package org.taerus.game.engine;


import javafx.scene.image.Image;
import org.taerus.game.engine.resource.ResourceSet;

import java.util.HashMap;

public class ResourceManager {
    private static ResourceManager instance = new ResourceManager();
    public static ResourceManager get() {
        return instance;
    }

    private HashMap<String, String> filenames;
    private HashMap<String, Integer> resourcesUsage;
    private HashMap<String, Image> images;
    private HashMap<String, ResourceSet> resourceSetMap;

    private ResourceManager() {
        filenames = new HashMap<>();
        resourcesUsage = new HashMap<>();
        images = new HashMap<>();
        resourceSetMap = new HashMap<>();
    }

    public ResourceManager add(String key, String filename) {
        if (filenames.containsKey(key) && !filenames.get(key).equals(filename)) {
            // TODO error
        } else {
            filenames.put(key, filename);
        }
        return this;
    }

    public void load(String... keys) {
        for (String key : keys) {
            String filename = filenames.get(key);
            int count = resourcesUsage.getOrDefault(filename, 0);
            if (count == 0) {
                // TODO load resource
                /////////////////////////////////////
                loadImage(filename);
                /////////////////////////////////////
            }
            resourcesUsage.put(filename, ++count);
        }
    }

    public void free(String... keys) {
        for (String key : keys) {
            String filename = filenames.get(key);
            int count = resourcesUsage.getOrDefault(filename, 0);
            if (count > 0) {
                // TODO free resource
                /////////////////////////////////////
                images.remove(filename);
                /////////////////////////////////////
                count--;
            }
            resourcesUsage.put(filename, count);
        }
    }

    public ResourceSet createResourceSet(String id) {
        if (resourceSetMap.containsKey(id)) {
            // TODO error: this resource set already exits
        }

        ResourceSet resourceSet = new ResourceSet();
        resourceSetMap.put(id, resourceSet);

        return resourceSet;
    }

    public void loadResourceSet(String id) {
        selectResourceSet(id).load();
    }

    public void freeResourceSet(String id) {
        selectResourceSet(id).free();
    }

    public void removeResourceSet(String id) {
        selectResourceSet(id).free();
        resourceSetMap.remove(id);
    }

    public ResourceSet selectResourceSet(String id) {
        ResourceSet resourceSet;
        try {
            resourceSet = resourceSetMap.get(id);
        } catch (Exception e) {
            throw new RuntimeException("There is no resource set associated to the identifier : " + id);
        }
        return resourceSet;
    }

    public Image loadImage(String url, Boolean backgroundLoading) {
        Image image = new Image(url, backgroundLoading);
        images.put(url, image);

        return image;
    }

    public Image loadImage(String url) {
        return loadImage(url, true);
    }

    public Image getImage(String key) {
        return images.get(filenames.get(key));
    }

}
