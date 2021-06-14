package Model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class WindowTr extends ObjRoof {

    public WindowTr() {
        Name="Окно треугольное";
    }


    @Override
    public void draw(Pane p) {
        Path path=new Path();
        path.getElements().addAll(new MoveTo(w/2,0),
                new LineTo(0,h),
                new LineTo(w,h),
                new ClosePath());
        path.setLayoutX(ox);
        path.setLayoutY(oy);
        path.setFill(c);
        p.getChildren().add(path);
        shape =path;
    }
}
