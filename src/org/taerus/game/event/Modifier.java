package org.taerus.game.event;


public enum Modifier {

    CTRL("CTRL"), ALT("ALT"), SHIFT("SHIFT");

    public final String name;

    Modifier(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
