package Model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class Smoke extends ObjRoof {

    public Smoke() {
        Name="Дымоход";
    }

    @Override
    public void draw(Pane p) {
        Rectangle r=new Rectangle(w,h);
        r.setLayoutX(ox);
        r.setLayoutY(oy);
        r.setFill(c);
        p.getChildren().add(r);
        shape =r;
    }
}
