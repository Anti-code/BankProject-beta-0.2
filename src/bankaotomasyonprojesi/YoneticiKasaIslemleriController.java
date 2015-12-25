/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankaotomasyonprojesi;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Mehmet
 */
public class YoneticiKasaIslemleriController implements Initializable {

    
    /**
     * Initializes the controller class.
     */
    
   
    
    
    @FXML
    private ComboBox<String> select_kasa_box;
    @FXML
    private Text bakiye4;
    @FXML
    private Text bakiye3;
    @FXML
    private Text bakiye2;
    @FXML
    private Text bakiye1;
    @FXML
    private TextField nakit_field;
    
    int secilen;
    kasaQueries kq=new kasaQueries();
    ObservableList<Kasa> kasalar=FXCollections.observableArrayList();
    @FXML
    private Button eksilt_button;
    @FXML
    private Button ekle_button;
    @FXML
    private Rectangle kasa2_rek;
    @FXML
    private Rectangle kasa3_rek;
    @FXML
    private Rectangle kasa1_rek;
    @FXML
    private Text personel1;
    @FXML
    private Text personel2;
    @FXML
    private Text personel3;
    @FXML
    private Text personel4;
    @FXML
    private Rectangle kasa4_rek;
    
    private Personel personel;
    @Override  //done
    public void initialize(URL url, ResourceBundle rb) {
        
        
       
       update();
        for(Kasa k : kasalar){
            select_kasa_box.getItems().add("Kasa "+String.valueOf(k.getId()));
        }
        
    select_kasa_box.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                ekle_button.setDisable(false);
                eksilt_button.setDisable(false);
                nakit_field.setDisable(false);
                
            }
        });
       
        
    }    
    
    
    public void update(){
        kasalar=kq.getAll();
        bakiye1.setText(String.valueOf(kasalar.get(0).getBakiye()));
        bakiye2.setText(String.valueOf(kasalar.get(1).getBakiye()));
        bakiye3.setText(String.valueOf(kasalar.get(2).getBakiye()));
        bakiye4.setText(String.valueOf(kasalar.get(3).getBakiye()));
        
        
    }
    @FXML
    private void ekle(MouseEvent event) {
        if(kasalar.get(secilen).isDurum()==true){
            System.out.println(secilen+" numaralı kasaya "+nakit_field.getText()+" eklendi");
            kq.change(kasalar.get(secilen-1).getId(), Float.valueOf(nakit_field.getText()));
            update();
        }
    }

    @FXML
    private void eksilt(MouseEvent event) {
        if(kasalar.get(secilen).isDurum()==true){
            System.out.println(secilen+" numaralı kasadan "+nakit_field.getText()+" eksiltildi");
            kq.change(kasalar.get(secilen-1).getId(), -Float.valueOf(nakit_field.getText()));
            update();
        }
    }


    @FXML
    private void ac(MouseEvent event) {
        switch(secilen){
            case 1:kasa1_rek.setFill(Color.web("85FFBB"));kasalar.get(secilen).setDurum(true);break;
            case 2:kasa2_rek.setFill(Color.web("85FFBB"));kasalar.get(secilen).setDurum(true);break;
            case 3:kasa3_rek.setFill(Color.web("85FFBB"));kasalar.get(secilen).setDurum(true);break;
            case 4:kasa3_rek.setFill(Color.web("85FFBB"));kasalar.get(secilen).setDurum(true);break;
        }
    }

    @FXML
    private void kapat(MouseEvent event) {
        switch(secilen){
            case 1:kasa1_rek.setFill(Color.web("F38080"));kasalar.get(secilen).setDurum(false);break;
            case 2:kasa2_rek.setFill(Color.web("F38080"));kasalar.get(secilen).setDurum(false);break;
            case 3:kasa3_rek.setFill(Color.web("F38080"));kasalar.get(secilen).setDurum(false);break;
            case 4:kasa3_rek.setFill(Color.web("F38080"));kasalar.get(secilen).setDurum(false);break;
        }
    }

    @FXML
    private void getIndex(ActionEvent event) {
        secilen=select_kasa_box.getSelectionModel().getSelectedIndex()+1;
        System.out.println("Kasa"+secilen+"  secildi");
    }

    
    @FXML
    private void raporButton(MouseEvent event) {
    }


    @FXML
    private Stage personelIslemleriButton(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("personelIslemleri.fxml"));

                            Stage stage = new Stage(StageStyle.DECORATED);
                            try {
                                stage.setScene( new Scene((Pane) loader.load() ) );
                            }
                            catch (IOException ex) {
                                    System.out.println(ex.getMessage());
                                }

                          PersonelIslemleriController controller =loader.<PersonelIslemleriController>getController();
                         
                          System.out.println("Personel Islemleri");

                          stage.show();
                          return stage;
        
    }

    public Text getPersonel1() {
        return personel1;
    }

    public void setPersonel1(Text personel1) {
        this.personel1 = personel1;
    }

    public Text getPersonel2() {
        return personel2;
    }

    public void setPersonel2(Text personel2) {
        this.personel2 = personel2;
    }

    public Text getPersonel3() {
        return personel3;
    }

    public void setPersonel3(Text personel3) {
        this.personel3 = personel3;
    }

    public Text getPersonel4() {
        return personel4;
    }

    public void setPersonel4(Text personel4) {
        this.personel4 = personel4;
    }

    public Personel getPersonel() {
        return personel;
    }

    public void setPersonel(Personel personel) {
        this.personel = personel;
    }

    @FXML
    private void girisButton(MouseEvent event) {
         Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene scene = new Scene(root);
            Stage stage2 = new Stage();
            stage2.setScene(scene);
            stage2.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        Stage stage = (Stage) bakiye1.getScene().getWindow();
        stage.close();
    }
    


    

    
}
