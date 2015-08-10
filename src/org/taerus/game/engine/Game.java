package org.taerus.game.engine;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.taerus.game.states.State;

public class Game extends Application {

    ResourceManager resources;
    StateStack states;

    public static void main(String[] args) {
        launch(args);
    }

    public Game() {
        resources = ResourceManager.getInstance();
        states = StateStack.getInstance();

        states.push(State.BOOT, 1000.);
//        states.push(State.BOOT);
//        states.push(State.TITLE);
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {

        Group root = new Group();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        Graphics.init(1280, 720, root);


        final GameLoop mainLoop = new GameLoop(60) {
            @Override
            public void update(double delta) {
                if(states.isEmpty()) {
                    primaryStage.close();
                    stop();
                }
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
