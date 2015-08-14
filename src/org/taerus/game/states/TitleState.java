package org.taerus.game.states;


import javafx.geometry.VPos;
import javafx.scene.paint.Color;
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

    private Sprite background;
    private Text title;
    private Rectangle rect;
    private Rectangle rect2;

    @Override
    public void onInit(Object... args) {
        state = 0;
        x = 0;
        y = 0;

        background = new Sprite(ResourceManager.get().getImage("background/title"));

        title = new Text("Je suis un titre", (Graphics.getInstance().width()/2), 120, "Comic sans MS", 120, Color.GREEN);
        title.setFontWeight(FontWeight.BOLD);
        title.setAlign(TextAlignment.CENTER);
        title.setBaseLine(VPos.CENTER);
        title.stroke(Color.DARKGREEN, 3);

        rect = new Rectangle(200, 400, 200, 100);
        rect.fill(Color.DARKGRAY);
        rect.stroke();

        rect2 = new Rectangle(10, 10, 50, 50);
        rect2.setRotationPivot(25, 25);
        rect2.setFillColor(Color.CYAN);

        // ajout de rect2 comme fils de rect
        rect.add(rect2);
    }

    @Override
    public void update(double delta) {
        double dt = delta / 1000.;
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
        Graphics.getInstance().clear();

        Transform global = Transform.translate(x, y);

        background.draw();
        rect.draw(global);
        title.draw();
    }

}
