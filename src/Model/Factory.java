package Model;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**Класс рисования компонентов
 * @autor Щербатых Артур
 * @version 3.0
 */
public class Factory {

    ArrayList<ObjRoof> decor;

    /**Метод - Создание нового объекта
     *
     */
    public Factory(){
        decor =new ArrayList<ObjRoof>();
        decor.add(new WindowTr());
        decor.add(new WindowKr());
        decor.add(new Flug());
        decor.add(new Smoke());
        decor.add(new ObjRoof1S());
        decor.add(new ObjRoof2S());
        decor.add(new ObjRoof4S());
    }

    /**Метод - добавление компонента
     * @param i
     * @param h
     * @param w
     * @param c
     * @param ox
     * @param oy
     * @return
     */
    public ObjRoof F(int i, Double h, Double w, Color c, Double ox, Double oy)
    {
        if(i>6 || i<0)return null;
        decor.get(i).setParam(h,w,c,ox,oy);
        return decor.get(i).clone();

    }

    /**Метод - редактирование размера
     * @param s
     * @return
     */
    public int NumObjRoof(String s){
        for (int i=0;i<decor.size();i++) {
            if(decor.get(i).getClass().toString().equals(s))
            {
                return i;
            }
        }
        return -1;
    }
}
