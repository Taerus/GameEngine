package org.taerus.game.engine;


import java.util.Objects;

public abstract class GameState implements I {

    protected static StateStack states = StateStack.getInstance();
    private boolean initialized;
    private Object[] args;

    public GameState() {
        this.initialized = false;
        this.args = null;
    }

    public GameState set(Object... args) {
        this.args = args;
        return this;
    }

    public final void init(Objects... args) {
        onInit(this.args);
        initialized = true;
    }

    public abstract void onInit(Object... args);

    public boolean isInitialized() {
        return initialized;
    }
}
