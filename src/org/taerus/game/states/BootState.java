package org.taerus.game.states;


import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import org.taerus.game.engine.GameState;
import org.taerus.game.engine.Graphics;
import org.taerus.game.engine.ResourceManager;

public class BootState extends GameState {

    private double progress;
    private double loadingTime;
    private double loadingDuration;

    private Image backgroung;

    private boolean loadingSimulation;

    @Override
    public void onInit(Object... args) {
        progress = 0.;
        loadingTime = 0.;

        loadingSimulation = args.length > 0;
        loadingDuration = loadingSimulation ? (Double) args[0] : 0.;

        ResourceManager resources = ResourceManager.get();
        resources.add("background/loading", "assets/loading-background.png")
                .add("background/title", "assets/title-background.jpg");

        resources.createResourceSet("boot")
                .add("background/loading")
                .load();

        resources.createResourceSet("title")
                .add("background/title")
                .load();

        backgroung = resources.getImage("background/loading");
    }

    @Override
    public void update(double delta) {
        if (loadingSimulation) {
            loadingTime += delta;
            progress = Math.min(100., loadingTime / loadingDuration * 100.);
            if(progress >= 100) {
                states.push(State.TITLE);
            }
        } else {
            if(ResourceManager.get().getImage("background/title").getProgress() == 1) {
                states.pop();
                states.push(State.TITLE);
                ResourceManager.get().selectResourceSet("boot").free();
                ResourceManager.get().selectResourceSet("title").load();
            }
        }
    }

    @Override
    public void render() {
        Graphics graphics = Graphics.getInstance();

        double centerX = graphics.width()/2;
        double centerY = graphics.height()/2;

        GraphicsContext gc = graphics.getGraphicsContext();

        gc.drawImage(backgroung, 0, 0);

        gc.setFill(Color.RED);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.BOTTOM);
        gc.setFont(Font.font("Arial", 60));
        gc.fillText(String.format("Loading %3.0f%%", progress), centerX, centerY);

        gc.setLineWidth(2);
        gc.setFill(Color.RED);
        gc.setStroke(Color.RED);
        gc.fillRect(centerX-200, centerY+20, 4.0* progress, 30);
        gc.strokeRect(centerX - 200, centerY + 20, 400, 30);
    }

}
