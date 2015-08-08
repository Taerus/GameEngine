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

    @Override
    public void onInit(Object... args) {
        progress = 0.;
        loadingTime = 0.;
        loadingDuration = (Double) args[0];

        ResourceManager resources = ResourceManager.getInstance();
        backgroung = resources.loadImage("loading-background", "assets/loading-background.png", false);
    }

    @Override
    public void update(double delta) {
        loadingTime += delta;
        progress = Math.min(100., loadingTime / loadingDuration * 100.);
        if(progress >= 100) {
            states.push(State.TITLE);
        }
    }

    @Override
    public void render(Graphics graphics) {
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
