package org.taerus.game.graphics;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.taerus.game.engine.ResourceManager;

public class Sprite extends Drawable {

    private Image image;
    private double height;
    private double width;

    public Sprite(Point position, Point anchor, Image image) {
        super(position, anchor);
        setImage(image);
    }

    public Sprite(Point position, Image image) {
        super(position);
        setImage(image);
    }

    public Sprite(Image image) {
        setImage(image);
    }

    public Sprite(Point position, Point anchor, String key) {
        super(position, anchor);
        setImage(key);
    }

    public Sprite(Point position, String key) {
        super(position);
        setImage(key);
    }

    public Sprite(String key) {
        setImage(key);
    }

    @Override
    protected void onDraw(GraphicsContext gc) {
        gc.drawImage(image, 0, 0);
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
        resetSize();
    }

    public void setImage(String key) {
        this.image = ResourceManager.get().getImage(key);
        resetSize();
    }

    public void resetSize() {
        width = image.getWidth();
        height = image.getHeight();
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }
}
