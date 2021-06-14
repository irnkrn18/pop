package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import Model.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public Label Plb;
    public ColorPicker BrushCol;
    public Pane Pn;
    public RadioButton rbTR1;
    public RadioButton rbTR2;
    public RadioButton rbTR4;
    public TextField H;
    public TextField W;
    public Button AddRoof;
    public TextField oy;
    public TextField ox;
    public AnchorPane SPCol;
    public AnchorPane SPRuler;
    public AnchorPane SPDec;
    public AnchorPane SPRoof;
    public AnchorPane SPAdd;
    public TitledPane dfd;
    public TitledPane TPDec;
    public TitledPane TPRul;
    public TitledPane TPRoof;
    public AnchorPane SPPer;
    public AnchorPane SPMs;
    public Button BDecFL;
    public Button BDecSM;
    public Button BDecZC;
    public Button BDecTR;
    public AnchorPane SPCp;
    public ColorPicker Col;
    public Label LabelName;
    double X=0,Y=0;
    boolean isAdd=false,isPlace=false;
    double H1,W1,ox1,oy1;
    ArrayList<ObjRoof> Roof_Lis =new ArrayList<ObjRoof>();
    ObjRoof temp=null;
    int Item=-1,backItem=-1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        System.out.println();
       //zn();
    }

    public void Mc(MouseEvent mouseEvent) 
    {
        if(SPMs.getStyle().equals("-fx-background-color: Coral;"))
            McFocus(mouseEvent);
        if(SPAdd.getStyle().equals("-fx-background-color: Coral;") && Item!=-1)
            McAdd(mouseEvent);
        else if(SPPer.getStyle().equals("-fx-background-color: Coral;"))
            McPer(mouseEvent);
        else if(SPCp.getStyle().equals("-fx-background-color: Coral;"))
            McCp(mouseEvent);
    }
    private void McFocus(MouseEvent mouseEvent)
    {
        System.out.println(Roof_Lis.size());
        for (int i=Roof_Lis.size()-1;i>=0;i--) {
            var t=Roof_Lis.get(i);
            if(X>t.ox && X<t.ox+t.w && Y>t.oy && Y<t.oy+t.h)
            {
            temp=t;
            UpdateFields();
            break;
            }
        }
    }
    private void McAdd(MouseEvent mouseEvent)
    {
        if(mouseEvent.getButton()== MouseButton.PRIMARY) {
            if (isAdd) {
             Roof_Lis.add(temp);
             isAdd = false;

            } else {
             isAdd = true;
             H1 = Y;
             W1 = X;
         }
     }else if(mouseEvent.getButton()== MouseButton.SECONDARY){
         Clr();
         ClearFields();
         for (var r:Roof_Lis) {
             r.draw(Pn);
         }
         isAdd = false;
     }
 }
    private void McPer(MouseEvent mouseEvent){
        if(mouseEvent.getButton()== MouseButton.PRIMARY) {
            if (isPlace) {
              //  Roof_Lis.add(temp);
                isPlace = false;
            } else {
                for (int i=Roof_Lis.size()-1;i>=0;i--) {
                    temp=Roof_Lis.get(i);
                    if(X>temp.ox && X<temp.ox+temp.w && Y>temp.oy && Y<temp.oy+temp.h)
                    {
                       // Roof_Lis.get(i).isSelected=true;
                        // Roof_Lis.get(i).shape.opacityProperty().set(0.5);
                        temp.shape.opacityProperty().set(0.5);
                        H1 = Y;
                        W1 = X;
                        oy1=temp.oy;
                        ox1=temp.ox;
                        isPlace = true;
                        break;
                    }
                }
            }
        }else if(mouseEvent.getButton()== MouseButton.SECONDARY){
            Clr();
            temp.ox=ox1;
            temp.oy=oy1;
            UpdateFields();
            for (var r:Roof_Lis) {
                r.draw(Pn);
            }
            isPlace = false;
        }
    }
    private void McCp(MouseEvent mouseEvent)  {
        if(mouseEvent.getButton()== MouseButton.PRIMARY) {
            if (isPlace) {
                //  Roof_Lis.add(temp);
                isPlace = false;
            } else {
                for (int i=Roof_Lis.size()-1;i>=0;i--) {
                    temp=  Roof_Lis.get(i);
                    if(X>temp.ox && X<temp.ox+temp.w && Y>temp.oy && Y<temp.oy+temp.h)
                    {
                        temp=  temp.clone();
                        Roof_Lis.add(temp);
                        temp.shape.opacityProperty().set(0.5);
                        H1 = Y;
                        W1 = X;
                        oy1=temp.oy;
                        ox1=temp.ox;
                        isPlace = true;
                        break;
                    }
                }
            }
        }else if(mouseEvent.getButton()== MouseButton.SECONDARY){
            Clr();
            ClearFields();
            Roof_Lis.remove(temp);
            for (var r:Roof_Lis) {
                r.draw(Pn);
            }
            isPlace = false;
        }
    }
    public void Cl(ActionEvent actionEvent) {
        Roof_Lis.clear();
       Clr();
    }

    public void Clr() {
        for(int i=0;i< Pn.getChildren().size();i++)
        {
            var pn = Pn.getChildren().get(i);
            System.out.println( pn.getClass());
            if(pn.getClass()!=javafx.scene.control.Label.class) //edit to remove children
            {
                Pn.getChildren().remove(pn);
                i--;
            }
        }
    }

    public void Mm(MouseEvent mouseEvent) {
        X=mouseEvent.getX();
        Y=mouseEvent.getY();
        Plb.setText(""+X+"\n"+Y);
        if(isAdd)
        {
            AddElement();
            UpdateFields();
        }
        else if (isPlace)
        {
            temp.ox=ox1+X-W1;
            temp.oy=oy1+Y-H1;
            Clr();
            for (ObjRoof r:Roof_Lis) {
                r.draw(Pn);
            }
            UpdateFields();
        }
    }
    public void AddElement(){
        Double h= (H1<Y)?Y-H1:H1-Y;
        Double w= (W1<X)?X-W1:W1-X;
        Double ho= Math.min(H1, Y);
        Double wo= Math.min(W1, X);
         Factory F=new Factory();
        temp=F.F(Item,h,w,BrushCol.getValue(),wo,ho);
        Clr();
        for (ObjRoof r:Roof_Lis) {
            r.draw(Pn);
        }
        temp.draw(Pn);
    }

    public void AddRoof(ActionEvent actionEvent) {
        //zn();
        if(rbTR1.isSelected()){Item=4;}
       else if(rbTR2.isSelected()){Item=5;}
        else if(rbTR4.isSelected()){Item=6;}
        CLSPAdd(null);
       // else {System.out.println("Ошибка выбора типа крыши");}

    }

    public void Help(ActionEvent actionEvent) {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Программа разработана в целях обучения \n Студент разработчик: Щербатых Артур \n Контакты: Art@mail.org 89009992455");
        alert.setTitle("О программе");
        alert.show();
    }

    public void CLSPCol(MouseEvent mouseEvent) {
        if(!SPCol.getStyle().equals("-fx-background-color: Coral;"))
        {
            SPCol.setStyle("-fx-background-color: Coral;");
            BrushCol.show();
            dfd.setExpanded(true);
        }
        else {
            SPCol.setStyle("");
            BrushCol.hide();
            dfd.setExpanded(false);
         }

    }

    public void ClSPRuler(MouseEvent mouseEvent) {
        if(!SPRuler.getStyle().equals("-fx-background-color: Coral;"))
        {
            SPRuler.setStyle("-fx-background-color: Coral;");
            TPRul.setExpanded(true);
        }
        else {
            SPRuler.setStyle("");
            TPRul.setExpanded(false);
        }
    }

    public void CLSPDec(MouseEvent mouseEvent) {
        if(!SPDec.getStyle().equals("-fx-background-color: Coral;"))
        {
            SPDec.setStyle("-fx-background-color: Coral;");
            TPDec.setExpanded(true);
        }
        else {
            SPDec.setStyle("");
            TPDec.setExpanded(false);
        }
    }

    public void CLSPRoof(MouseEvent mouseEvent) {
        if(!SPRoof.getStyle().equals("-fx-background-color: Coral;"))
        {
            SPRoof.setStyle("-fx-background-color: Coral;");
            TPRoof.setExpanded(true);
        }
        else {
            SPRoof.setStyle("");
            TPRoof.setExpanded(false);
        }
    }

    public void CLSPPer(MouseEvent mouseEvent) {
        SPCp.setStyle("");
        SPAdd.setStyle("");
        SPMs.setStyle("");
        SPPer.setStyle("-fx-background-color: Coral;");
        backItem=Item!=-1?Item:backItem;
        Item=-1;
        isAdd=false;//?
        isPlace=false;//?
    }

    public void CLSPMous(MouseEvent mouseEvent) {
        SPCp.setStyle("");
        SPAdd.setStyle("");
        SPMs.setStyle("-fx-background-color: Coral;");
        SPPer.setStyle("");
        backItem=Item!=-1?Item:backItem;
        Item=-1;
        isAdd=false;//?
        isPlace=false;//?
    }

    public void CLSPCopy(MouseEvent mouseEvent) {
        SPCp.setStyle("-fx-background-color: Coral;");
        SPAdd.setStyle("");
        SPMs.setStyle("");
        SPPer.setStyle("");
        backItem=Item!=-1?Item:backItem;
        Item=-1;
    }

    public void CLSPAdd(MouseEvent mouseEvent) {
        Item= (Item==-1)?backItem:Item;
        SPAdd.setStyle("-fx-background-color: Coral;");
        SPCp.setStyle("");
        SPMs.setStyle("");
        SPPer.setStyle("");
    }

    public void tr(ActionEvent actionEvent) {
        Item=0;
        CLSPAdd(null);
        BDecFL.setStyle("");
        BDecSM.setStyle("");
        BDecTR.setStyle("-fx-background-color: Coral;");
        BDecZC.setStyle("");
        //((Button) actionEvent.getSource()).setStyle("-fx-background-color: Coral;");
    }

    public void kr(ActionEvent actionEvent) {
        Item=1;
        CLSPAdd(null);
        BDecFL.setStyle("");
        BDecSM.setStyle("");
        BDecTR.setStyle("");
        BDecZC.setStyle("-fx-background-color: Coral;");
    }

    public void flu(ActionEvent actionEvent) {
        Item=2;
        CLSPAdd(null);
        BDecFL.setStyle("-fx-background-color: Coral;");
        BDecSM.setStyle("");
        BDecTR.setStyle("");
        BDecZC.setStyle("");
    }

    public void smoke(ActionEvent actionEvent) {
        Item=3;
        CLSPAdd(null);
        BDecFL.setStyle("");
        BDecSM.setStyle("-fx-background-color: Coral;");
        BDecTR.setStyle("");
        BDecZC.setStyle("");
    }

    private void UpdateFields()
    {
        LabelName.setText(temp.Name);
        H.setText(temp.h.toString());
        W.setText(temp.w.toString());
        ox.setText(temp.ox.toString());
        oy.setText(temp.oy.toString());
        Col.setValue(temp.c);
    }
    public void UpdateZnach(ActionEvent actionEvent) {
        temp.h=Double.parseDouble(H.getText());
        temp.w=Double.parseDouble(W.getText());
        temp.ox=Double.parseDouble(ox.getText());
        temp.oy=Double.parseDouble(oy.getText());
        temp.c=Col.getValue();
        Clr();
        for (ObjRoof r:Roof_Lis) {
            r.draw(Pn);
        }
    }

    public void Close(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void ClearFields() {
        temp.Name="Пусто";
        temp.h=0.;
        temp.w=0.;
        temp.ox=0.;
        temp.oy=0.;
        temp.c= Color.WHITE;
        UpdateFields();
    }

    public void Dropfocusobj(ActionEvent actionEvent) {
        ClearFields();
        Roof_Lis.remove(temp);
        Clr();
        for (ObjRoof r:Roof_Lis) {
            r.draw(Pn);
        }
    }

    public void Droplastobj(ActionEvent actionEvent) {
        Roof_Lis.remove(Roof_Lis.size()-1);
        Clr();
        for (ObjRoof r:Roof_Lis) {
            r.draw(Pn);
        }
    }



   ReadWriteFile rwf=new ReadWriteFile(null);

    public void LoadRoof(ActionEvent actionEvent) {
        Roof_Lis=rwf.Load();
        Clr();
        for (ObjRoof r:Roof_Lis) {
            r.draw(Pn);
        }
//        fileChooser.setTitle("Open Resource File");
//        fileChooser.getExtensionFilters().addAll(
//                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
//                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
//                new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
//                new FileChooser.ExtensionFilter("All Files", "*.*"));
//        File selectedFile = fileChooser.showOpenDialog(mainStage);
//        if (selectedFile != null) {
//            mainStage.display(selectedFile);
//        }
    }

    public void SaveRoof(ActionEvent actionEvent) {
        rwf.Save(Roof_Lis);
    }
}
