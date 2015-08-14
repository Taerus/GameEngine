package org.taerus.game.engine.resource;


import org.taerus.game.engine.ResourceManager;

import java.util.HashSet;

public class ResourceSet {

    private HashSet<String> notLoadedIds;
    private HashSet<String> loadedIds;

    public ResourceSet() {
        notLoadedIds = new HashSet<>();
        loadedIds = new HashSet<>();
    }

    public ResourceSet add(String key) {
        if(!loadedIds.contains(key) & !notLoadedIds.contains(key)) {
            notLoadedIds.add(key);
        }
        return this;
    }

    public ResourceSet add(String firstKey, String... others) {
        add(firstKey);
        for (String key : others) {
            add(key);
        }
        return this;
    }

    public void load() {
        ResourceManager.get().load(notLoadedIds.toArray(new String[notLoadedIds.size()]));
        loadedIds.addAll(notLoadedIds);
        notLoadedIds.clear();
    }

    public void free() {
        ResourceManager.get().free((String[])loadedIds.toArray(new String[loadedIds.size()]));
        notLoadedIds.addAll(loadedIds);
        loadedIds.clear();
    }

}
