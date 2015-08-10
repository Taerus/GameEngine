package org.taerus.game.graphics;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Rectangle extends Shape {

    private double width;
    private double height;

    public Rectangle(double x, double y, double width, double height, Paint fillColor) {
        super(new Point(x, y), fillColor);

        this.width = width;
        this.height = height;
    }

    public Rectangle(double x, double y, double width, double height) {
        this(x,y, width, height, Color.BLACK);
    }

    @Override
    protected void onDraw(GraphicsContext gc) {
        if (isFilled()) {
            gc.setFill(getFillColor());
            gc.fillRect(0., 0., width, height);
        }

        if (isStroke()) {
            gc.setStroke(getLineColor());
            gc.strokeRect(0., 0., width, height);
        }
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public void setHeight(double height) {
        this.height = height;
    }

}
