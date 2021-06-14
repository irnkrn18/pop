package Model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

/**Класс создания компонента "Флюгер"
 * @autor Щербатых Артур
 * @version 3.0
 */
public class Flug extends ObjRoof {
    public Flug() {
        Name="Флюгер";
    }

    /**Метод создания объекта "Флюгер"
     * @param p
     */
    @Override
    public void draw(Pane p) {
        Path path=new Path();
        path.getElements().addAll(new MoveTo(w/2,0),
                new LineTo(0,h/3),
                new LineTo(w,h/3),
                new LineTo(w/2,2*h/3),
                new LineTo(w/2,h),
                new ClosePath());
        path.setLayoutX(ox);
        path.setLayoutY(oy);
        path.setFill(c);
        p.getChildren().add(path);
        shape =path;
    }
}
