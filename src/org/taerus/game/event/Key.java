package org.taerus.game.event;


import java.util.HashMap;

public enum Key {
    Z("Z", "z"),
    Q("Q", "q"),
    S("S", "s"),
    D("D", "d"),
    SPACE("SPACE", " ");

    public final String name;
    public final String text;

    Key(String name, String text) {
        this.name = name;
        this.text = text;
    }

    @Override
    public String toString() {
        return name;
    }

    private static HashMap<String, Key> keys;
    static {
        Key[] values = Key.values();
        keys = new HashMap<>(values.length);
        for (Key key : values) {
            keys.put(key.name, key);
        }
    }

    static Key get(String keyName) {
        return keys.get(keyName.toUpperCase());
    }

}
