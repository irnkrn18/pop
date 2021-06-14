package Model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;



public class ObjRoof2S extends ObjRoof {

    public ObjRoof2S(){
        Name="Двускатная крыша";
    }
    @Override
    public void draw(Pane p) {
        Rectangle r=new Rectangle(w,h,c);
        r.setLayoutX(ox);
        r.setLayoutY(oy);
        p.getChildren().add(r);
        shape =r;
    }

}
