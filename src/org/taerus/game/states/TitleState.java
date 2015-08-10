package org.taerus.game.states;


import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Transform;
import org.taerus.game.engine.GameState;
import org.taerus.game.engine.Graphics;
import org.taerus.game.engine.ResourceManager;
import org.taerus.game.graphics.Rectangle;
import org.taerus.game.graphics.Sprite;
import org.taerus.game.graphics.Text;

public class TitleState extends GameState {

    private int state;
    private double x;
    private double y;
    private double angle;

    private Sprite background;
    private Text title;
    private Rectangle rect;
    private Rectangle rect2;

    @Override
    public void onInit(Object... args) {
        state = 0;
        x = 0;
        y = 0;
        angle = 0;

        background = new Sprite(ResourceManager.getInstance().getImage("background/title"));

        title = new Text("Je suis un titre", (Graphics.getInstance().width()/2), 120, "Comic sans MS", 120, Color.GREEN);
        title.setFontWeight(FontWeight.BOLD);
        title.stroke(Color.DARKGREEN, 3);

        rect = new Rectangle(200, 400, 200, 100);
        rect.fill(Color.DARKGRAY);
        rect.stroke();

        rect2 = new Rectangle(10, 10, 50, 50);
        rect2.setRotationPivot(25, 25);
        rect2.setFillColor(Color.CYAN);
        rect.add(rect2);
    }

    @Override
    public void update(double delta) {
        double dt = delta / 1000.;
        angle += dt * 45.;
        switch (state) {
            case 0:
                y += dt*100;
                if(y > 200) state = 1;
                break;
            case 1:
                y -= dt*100;
                if(y < 0) state = 0;
                break;
        }
        rect2.rotate(dt * 180);
    }

    @Override
    public void render() {
        Graphics graphics = Graphics.getInstance();

//        graphics.clear(Color.LIGHTBLUE);

        GraphicsContext gc = graphics.getGraphicsContext();

        Transform global = Transform.translate(x, y);


        background.draw();
        rect.draw(global);

//        Transform tr1 = Transform.rotate(angle, graphics.width() / 2, 120);
//        gc.setTransform(new Affine(tr1.createConcatenation(global)));

//        gc.setFill(Color.GREEN);
//        gc.setStroke(Color.DARKGREEN);
//        gc.setLineWidth(3);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
//        gc.setFont(Font.font("Comic sans MS", FontWeight.BOLD, 120));
//        gc.fillText("Je suis un titre", graphics.width() / 2, 120);
//        gc.strokeText("Je suis un titre", graphics.width() / 2, 120);
        title.draw();
    }

}
