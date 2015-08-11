package org.taerus.game.graphics;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Transform;
import org.taerus.game.engine.Graphics;

import java.util.ArrayList;
import java.util.List;

public abstract class Drawable {

    private Point position;
    private Point anchor;
    private double angle;
    private Point rotationPivot;
    private List<Drawable> children;

    public Drawable(Point position, Point anchor) {
        this.position = position;
        this.anchor = anchor;
        this.angle = 0.;
        this.rotationPivot = anchor;
        children = new ArrayList<>();
    }

    public Drawable(Point position) {
        this(position, new Point());
    }

    public Drawable() {
        this(new Point(), new Point());
    }

    public Transform getTransform() {
        Point p = position.sub(anchor);
        Transform translate = Transform.translate(p.x, p.y);
        Transform rotate = Transform.rotate(angle, getRotationPivot().x, getRotationPivot().y);
        return translate.createConcatenation(rotate);
    }

    public final void draw(GraphicsContext gc, Transform transform) {
        Transform tr = transform.createConcatenation(getTransform());
        gc.save();
        gc.setTransform(new Affine(tr));
        onDraw(gc);
        for(Drawable child : children) {
            child.draw(gc, tr);
        }
        gc.restore();
    }

    public final void draw(GraphicsContext gc) {
        draw(gc, new Affine());
    }

    public final void draw(Transform transform) {
        draw(Graphics.getInstance().getGraphicsContext(), transform);
    }

    public final void draw() {
        draw(Graphics.getInstance().getGraphicsContext(), new Affine());
    }

    protected abstract void onDraw(GraphicsContext gc);

    public final void add(Drawable child) {
        children.add(child);
    }

    public final void remove(Drawable child) {
        children.remove(child);
    }

    public Point getPosition() {
        return position;
    }

    public double getX() {
        return position.x;
    }

    public double getY() {
        return position.y;
    }

    public Drawable setPosition(Point position) {
        this.position = position;
        return this;
    }

    public void setPosition(double x, double y) {
        this.position = new Point(x, y);
    }

    public Drawable move(Point mov) {
        this.position = this.position.add(mov);
        return this;
    }

    public Drawable move(double x, double y) {
        this.position = this.position.add(x, y);
        return this;
    }

    public Point getAnchor() {
        return anchor;
    }

    public Drawable setAnchor(Point anchor) {
        this.anchor = anchor;
        return this;
    }

    public Drawable setAnchor(double x, double y) {
        this.anchor = new Point(x, y);
        return this;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void rotate(double angle) {
        this.angle += angle;
    }

    public Point getRotationPivot() {
        return rotationPivot;
    }

    public void setRotationPivot(Point rotationPivot) {
        this.rotationPivot = rotationPivot;
    }

    public void setRotationPivot(double x, double y) {
        this.rotationPivot = new Point(x, y);
    }
}
