package org.taerus.game.event;


import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.*;

import java.util.EnumSet;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Events {

    private static Events instance = null;

    public static Events init(Scene scene) {
        instance = new Events(scene);

        return instance;
    }

    public static Events getInstance() {
        return instance;
    }

    private Queue<Event> eventQueue;

    private Events(Scene scene) {
        this.eventQueue = new ConcurrentLinkedQueue<>();
        scene.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                EnumSet<Modifier> modifiers = EnumSet.noneOf(Modifier.class);
                if (event.isControlDown()) modifiers.add(Modifier.CTRL);
                if (event.isAltDown()) modifiers.add(Modifier.ALT);
                if (event.isShiftDown()) modifiers.add(Modifier.SHIFT);
                eventQueue.add(new KeyEvent(EventType.KEY_PRESSED, Key.get(event.getText()), modifiers));
                System.out.println(eventQueue.peek() + " pressed");
            }
        });
        scene.setOnKeyReleased(new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                EnumSet<Modifier> modifiers = EnumSet.noneOf(Modifier.class);
                if (event.isControlDown()) modifiers.add(Modifier.CTRL);
                if (event.isAltDown()) modifiers.add(Modifier.ALT);
                if (event.isShiftDown()) modifiers.add(Modifier.SHIFT);
                eventQueue.add(new KeyEvent(EventType.KEY_RELEASED, Key.get(event.getText()), modifiers));
                System.out.println(eventQueue.peek() + " released");
            }
        });
    }

}
