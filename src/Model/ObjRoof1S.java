package Model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**Класс создания односкатной крыши
 * @autor Щербатых Артур
 * @version 3.0
 */
public class ObjRoof1S extends ObjRoof {

    /**Метод - задание имени объекта
     *
     */
    public ObjRoof1S(){
        Name="Односкатная крыша";
    }

    /**Метод - создание объекта
     * @param p
     */
    @Override
    public void draw(Pane p) {
        Rectangle r=new Rectangle(w,h,c);
        r.setLayoutX(ox);
        r.setLayoutY(oy);
        p.getChildren().add(r);
        shape =r;
    }
}
