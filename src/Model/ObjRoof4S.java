package Model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class ObjRoof4S extends ObjRoof {

    public ObjRoof4S() {
        Name="Четырехскатная крыша";
    }
    @Override
    public void draw(Pane p) {
        Path path=new Path();
        path.getElements().addAll(new MoveTo(0,h),
                new LineTo(w/4,0),
                new LineTo(3*w/4,0),
                new LineTo(w,h),
                new ClosePath());
        path.setLayoutX(ox);
        path.setLayoutY(oy);
        path.setFill(c);
        p.getChildren().add(path);
        shape =path;
    }
}
