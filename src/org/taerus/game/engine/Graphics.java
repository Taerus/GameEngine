package org.taerus.game.engine;


import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Graphics {

    private static Graphics instance = null;

    public static Graphics init(int width, int height, Group parent) {
        if (instance == null) {
            instance = new Graphics();
        } else {
            parent.getChildren().remove(instance.canvas);
        }
        Canvas c = new Canvas(width, height);
        instance.canvas = c;
        instance.gc = c.getGraphicsContext2D();
        parent.getChildren().add(c);

        return instance;
    }

    public static Graphics getInstance() {
        return instance;
    }

    private Graphics() {}

    private Canvas canvas;
    private GraphicsContext gc;

    public double width() {
        return canvas.getWidth();
    }

    public double height() {
        return canvas.getHeight();
    }

    public void clear(Paint fillColor) {
        Paint color = gc.getFill();
        gc.setFill(fillColor);
        gc.fillRect(0.0, 0.0, width(), height());
        gc.setFill(color);
    }

    public void clear() {
        clear(Color.WHITE);
    }

    public GraphicsContext getGraphicsContext() {
        return gc;
    }

}
