package org.taerus.game.states;


import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import org.taerus.game.engine.GameState;
import org.taerus.game.engine.Graphics;

public class TitleState extends GameState {

    @Override
    public void onInit(Object... args) {

    }

    @Override
    public void update(double delta) {

    }

    @Override
    public void render(Graphics graphics) {
        graphics.clear(Color.LIGHTBLUE);

        GraphicsContext gc = graphics.getGraphicsContext();

        gc.setFill(Color.GREEN);
        gc.setStroke(Color.DARKGREEN);
        gc.setLineWidth(3);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.TOP);
        gc.setFont(Font.font("Comic sans MS", FontWeight.BOLD, 120));
        gc.fillText("Mon Super Jeu", graphics.width() / 2, 100);
        gc.strokeText("Mon Super Jeu", graphics.width() / 2, 100);
    }

}
