package org.taerus.game.graphics;


import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class Text extends Shape {

    private String text;
    private String fontFamily;
    private FontPosture fontPosture;
    private FontWeight fontWeight;
    private TextAlignment align;
    private VPos baseLine;
    private double fontSize;

    public Text(String text, Point position, String family, double size, Paint fillColor) {
        super(position, fillColor);
        this.text = text;
        this.fontFamily = family;
        this.fontPosture = FontPosture.REGULAR;
        this.fontWeight = FontWeight.NORMAL;
        this.align = TextAlignment.LEFT;
        this.baseLine = VPos.TOP;
        this.fontSize = size;
    }

    public Text(String text, double x, double y, String family, double size, Paint fillColor) {
        this(text, new Point(x, y), family, size, fillColor);
    }

    @Override
    protected void onDraw(GraphicsContext gc) {
        gc.setFont(Font.font(fontFamily, fontWeight, fontPosture, fontSize));
        gc.setTextAlign(align);
        gc.setTextBaseline(baseLine);
        if (isFilled()) {
            gc.setFill(getFillColor());
            gc.fillText(text, 0., 0.);
        }

        if (isStroke()) {
            gc.setStroke(getLineColor());
            gc.setLineWidth(getLineWidth());
            gc.strokeText(text, 0., 0.);
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    public FontPosture getFontPosture() {
        return fontPosture;
    }

    public void setFontPosture(FontPosture fontPosture) {
        this.fontPosture = fontPosture;
    }

    public FontWeight getFontWeight() {
        return fontWeight;
    }

    public void setFontWeight(FontWeight fontWeight) {
        this.fontWeight = fontWeight;
    }

    public TextAlignment getAlign() {
        return align;
    }

    public void setAlign(TextAlignment align) {
        this.align = align;
    }

    public VPos getBaseLine() {
        return baseLine;
    }

    public void setBaseLine(VPos baseLine) {
        this.baseLine = baseLine;
    }

    public double getFontSize() {
        return fontSize;
    }

    public void setFontSize(double fontSize) {
        this.fontSize = fontSize;
    }

    @Override
    public double getWidth() {
        // TODO getWidth
        return 0;
    }

    @Override
    public void setWidth(double width) {
        // TODO setWidth
    }

    @Override
    public double getHeight() {
        // TODO getHeight
        return 0;
    }

    @Override
    public void setHeight(double height) {
        // TODO setHeight
    }
}
