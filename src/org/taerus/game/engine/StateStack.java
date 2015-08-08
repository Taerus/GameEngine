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

    private StateStack() {
        states = new Stack<>();
        tags = new HashMap<>();
    }

    public boolean push(@NotNull State state, Object... args) {
        GameState newState = null;

        switch (state) {
            case BOOT:
                newState = new BootState();
                break;

            case TITLE:
                newState = new TitleState();
                break;
        }

        if (newState != null) {
            states.push(newState.set(args));
        }

        return newState != null;
    }

    public boolean push(String tag, State state, Object... args) {
        boolean result = push(state, args);

        if (result) tags.put(tag, states.size() - 1);

        return result;
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
            GameState state = states.peek();
            if (!state.isInitialized()) {
                state.init();
            }
            state.update(delta);
        }
    }

    @Override
    public void render(Graphics graphics) {
        if(!states.isEmpty()) {
            states.peek().render(graphics);
        }
    }

    public boolean isEmpty() {
        return states.isEmpty();
    }
}
