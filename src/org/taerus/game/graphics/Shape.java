package org.taerus.game.graphics;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public abstract class Shape extends Drawable {

    private Paint fillColor;
    private Paint lineColor;
    private double lineWidth;
    private boolean filled;
    private boolean stroke;

    public Shape(Point position, Point anchor, Paint fillColor) {
        this(position, fillColor);
        setAnchor(anchor);
    }

    public Shape(Point position, Paint fillColor) {
        super(position);
        this.fillColor = fillColor;
        this.lineColor = Color.BLACK;
        this.lineWidth = 1.;
        this.filled = true;
        this.stroke = false;
    }

    public void fill(Paint fillColor) {
        this.fillColor = fillColor;
        this.filled = true;
    }

    public void fill() {
        this.filled = true;
    }

    public void stroke(Paint lineColor, double lineWidth) {
        this.lineColor = lineColor;
        this.lineWidth = lineWidth;
        this.stroke = true;
    }

    public void stroke(Paint lineColor) {
        this.lineColor = lineColor;
        this.stroke = true;
    }

    public void stroke(double lineWidth) {
        this.lineWidth = lineWidth;
        this.stroke = true;
    }

    public void stroke() {
        stroke = true;
    }

    public Paint getFillColor() {
        return fillColor;
    }

    public void setFillColor(Paint fillColor) {
        this.fillColor = fillColor;
    }

    public Paint getLineColor() {
        return lineColor;
    }

    public void setLineColor(Paint lineColor) {
        this.lineColor = lineColor;
    }

    public double getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(double lineWidth) {
        this.lineWidth = lineWidth;
    }

    public boolean isFilled() {
        return filled;
    }

    public boolean isStroke() {
        return stroke;
    }

    public abstract double getWidth();

    public abstract void setWidth(double width);

    public abstract double getHeight();

    public abstract void setHeight(double height);

}
