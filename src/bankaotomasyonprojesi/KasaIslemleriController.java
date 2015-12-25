/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankaotomasyonprojesi;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Mehmet
 */
public class KasaIslemleriController implements Initializable {

    @FXML
    private ListView<String> devir_list;
    @FXML
    private TextField tutar_field;
    @FXML
    private ComboBox<String> devralan_box;
    @FXML
    private Text personel_ad_soyad;
    @FXML
    private Text bakiye_text;
    private int kasa_id;
    private int personel_id;
    private Personel p;
    Kasa k;
    /**
     * Initializes the controller class.
     */
    kasaQueries kq=new kasaQueries();
    personelQueries pq=new personelQueries();
    private boolean detected=false;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        devralan_box.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                
            }
        });
        
    }    
    
    @FXML
    private void devretButton(MouseEvent event) {
        Float miktar=Float.valueOf(tutar_field.getText());
        int dkasa_id =Integer.valueOf(devralan_box.getSelectionModel().getSelectedItem().substring(devralan_box.getSelectionModel().getSelectedItem().length()-1));
        kq.change(kasa_id, -miktar);
        kq.change(dkasa_id,miktar);
        bakiye_text.setText(String.valueOf(kq.getKasa(kasa_id).getBakiye()));
        devir_list.getItems().add("Kasa "+kasa_id+"'den Kasa "+dkasa_id+"ye  "+miktar+" devredildi.");
        LogQuery lq=new LogQuery();
        Log l=new Log();
        l.setKasa_id(kasa_id);
        l.setMiktar(miktar);
        l.setPersonel_id(personel_id);
        l.setOlay(dkasa_id+" Numarali Kasaya Devir Islemi");
        //System.out.println(l.getHesap_no()+l.getKasa_id()+l.getMiktar()+l.getMusteri_no()+l.getPersonel_id()+l.getOlay()+);
        lq.addLog(l);
    }

    

    @FXML
    private void quitButton(MouseEvent event) {
        Stage stage = (Stage) bakiye_text.getScene().getWindow();
        stage.close();
    }

    public int getKasa_id() {
        return kasa_id;
    }

    public void setKasa_id(int kasa_id) {
        this.kasa_id = kasa_id;
    }

    public int getPersonel_id() {
        return personel_id;
    }

    public void setPersonel_id(int personel_id) {
        this.personel_id = personel_id;
    }

    @FXML
    private void detect(MouseEvent event) {
        if(detected==false){
            
            personel_id=getPersonel_id();
            p=pq.getPersonel(personel_id).get(0);
            k=kq.getKasa(p.getKasa_id());
            System.out.println(k.getBakiye());
            for(Personel pf : pq.getAll()){
                if(pf.getId()==p.getId()){
                    continue;
                }
                if(pf.getKasa_id()==0){
                    continue;
                }
                
                devralan_box.getItems().add(pf.getAd()+" "+pf.getSoyad() + " | Kasa "+String.valueOf(pf.getKasa_id()));
                
            }
            bakiye_text.setText(String.valueOf(k.getBakiye()));
            detected=true;
        }
    }
    
}
