package org.taerus.game.engine;


import com.sun.istack.internal.NotNull;
import org.taerus.game.states.BootState;
import org.taerus.game.states.State;
import org.taerus.game.states.TitleState;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class StateStack implements I {

    private static StateStack ourInstance = new StateStack();

    public static StateStack getInstance() {
        return ourInstance;
    }

    Stack<GameState> states;
    HashMap<String, Integer> tags;
    GameState currentState;

    private StateStack() {
        states = new Stack<>();
        tags = new HashMap<>();
        currentState = null;
    }

    public void push(@NotNull State state, Object... args) {
        GameState newState = null;

        switch (state) {
            case BOOT:
                newState = new BootState();
                break;

            case TITLE:
                newState = new TitleState();
                break;
        }

        states.push(newState.set(args));
    }

    public void push(@NotNull String tag, @NotNull State state, Object... args) {
        push(state, args);
        tags.put(tag, states.size() - 1);
    }

    public void pop() {
        try {
            states.pop();
            int idx = states.size();
            for (Map.Entry<String, Integer> entry : tags.entrySet()) {
                if(entry.getValue() == idx) tags.remove(entry.getKey());
            }
        } catch (EmptyStackException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(double delta) {
        if(!states.isEmpty()) {
            currentState = states.peek();
            if (!currentState.isInitialized()) {
                currentState.init();
            }
            currentState.update(delta);
        } else {
            currentState = null;
        }
    }

    @Override
    public void render() {
        if(!states.isEmpty()) {
            currentState.render();
        }
    }

    public boolean isEmpty() {
        return states.isEmpty();
    }
}
