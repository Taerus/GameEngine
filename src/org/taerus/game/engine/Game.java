package org.taerus.game.engine;


import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.taerus.game.resources.ResourceManager;
import org.taerus.game.states.State;

import java.util.Stack;

public class Game extends Application {

    ResourceManager resources;
    StateStack states;

    public static void main(String[] args) {
        launch(args);
    }

    public Game() {
        resources = ResourceManager.getInstance();
        states = StateStack.getInstance();

        states.add(State.BOOT);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        GameLoop mainLoop = new GameLoop(60) {
            @Override
            public void update(double delta) {
                states.update(delta);
            }

            @Override
            public void render() {
                states.render();
            }
        };

        mainLoop.start();
        primaryStage.show();

    }
}
