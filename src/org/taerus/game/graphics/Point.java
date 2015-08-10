package org.taerus.game.graphics;


public class Point {

    public final double x;
    public final double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
        this(0., 0.);
    }

    public Point add(Point point) {
        return new Point(this.x + point.x, this.y + point.y);
    }

    public Point add(double x, double y) {
        return new Point(this.x + x, this.y + y);
    }

    public Point sub(Point point) {
        return new Point(this.x - point.x, this.y - point.y);
    }

    public Point sub(double x, double y) {
        return new Point(this.x - x, this.y - y);
    }

    public Point mul(double f) {
        return new Point(this.x * f, this.y * f);
    }

    public Point mul(double fx, double fy) {
        return new Point(this.x * fx, this.y * fy);
    }

}
