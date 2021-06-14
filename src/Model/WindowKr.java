package Model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class WindowKr extends ObjRoof {
    public WindowKr() {
        Name="Окно закругленное";
    }

    @Override
    public void draw(Pane p) {
        Arc ar=new Arc(w/2, h, w/2, h, 0, 180);
        ar.setLayoutX(ox);
        ar.setLayoutY(oy);
        ar.setFill(c);
        p.getChildren().add(ar);
        shape =ar;
    }
}
