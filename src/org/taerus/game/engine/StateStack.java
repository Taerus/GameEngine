package org.taerus.game.engine;


import org.taerus.game.states.BootState;
import org.taerus.game.states.State;
import org.taerus.game.states.State.*;

import java.util.HashMap;
import java.util.Stack;

public class StateStack implements I {

    private static StateStack ourInstance = new StateStack();

    public static StateStack getInstance() {
        return ourInstance;
    }

    Stack<GameState> states;
    HashMap<String, Integer> tags;

    private StateStack() {
        states = new Stack<>();
    }

    public boolean add(State state, Object... args) {
        GameState newState = null;

        switch (state) {
            case BOOT:
                newState = new BootState();
        }

        if (newState != null) {
            states.push(newState);
        }

        return newState != null;
    }

    public boolean add(String tag, State state, Object... args) {
        boolean result = add(state, args);

        if (result) tags.put(tag, states.size() - 1);

        return result;
    }

    @Override
    public void update(double delta) {
        states.peek().update(delta);
    }

    @Override
    public void render() {
        states.peek().render();
    }
}
