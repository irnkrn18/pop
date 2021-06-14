package Model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;



public abstract class ObjRoof implements Cloneable {
    public Shape shape;
    //public boolean isSelected=false;
    public Double w,h;
    public Color c;
    public Double ox,oy;
    public String Name="Фигура";
    public abstract void draw(Pane p);

    public void setParam(Double h,Double w,Color c,Double ox, Double oy){
        this.w=w;
        this.h=h;
        this.c=c;
        this.oy=oy;
        this.ox=ox;
    }

    @Override
    public String toString() {
        return Name;
    }

    @Override
    public ObjRoof clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();   }
        return (ObjRoof) clone;//
    }
}
