/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankaotomasyonprojesi;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Mehmet
 */
public class HavaleIslemiController implements Initializable {

    @FXML
    private TextField havale_musteri_field;
    @FXML
    private TableView<Hesap> hesap_table;
    @FXML
    private TableColumn<Hesap, Integer> hesap_no;
    @FXML
    private TableColumn<Hesap, String> hesap_turu;
    @FXML
    private TableColumn<Hesap, Float> bakiye;
    @FXML
    private TableColumn<Hesap, String> doviz_cinsi;
    @FXML
    private TableColumn<Hesap, String> hesap_durumu;
    @FXML
    private Text havale_yapan_musteri;
    hesapQueries hq= new hesapQueries();
    ObservableList<Hesap> hesap_listesi=FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    private int havale_yapan_musteri_no;
    private int havale_yapan_hesap_no;
    private int personel_id;
    private int kasa_id;
    private boolean detected=false;
    @FXML
    private TextField miktar;
    @FXML
    private Text feedback;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hesap_listesi.clear();
       hesap_no.setCellValueFactory(new PropertyValueFactory<>("no"));
       hesap_turu.setCellValueFactory(new PropertyValueFactory<>("tur"));
       bakiye.setCellValueFactory(new PropertyValueFactory<>("bakiye"));
       doviz_cinsi.setCellValueFactory(new PropertyValueFactory<>("doviz_cinsi"));
       hesap_durumu.setCellValueFactory(new PropertyValueFactory<>("durum"));
       hesap_table.setItems(hesap_listesi);
    }    

    @FXML
    private void havaleYap(MouseEvent event) {
       
       // System.out.println(hesap_table.getSelectionModel().getSelectedItem().getNo());
       try{
           hq.havale(havale_yapan_hesap_no, hesap_table.getSelectionModel().getSelectedItem().getNo(), Float.valueOf(miktar.getText()), kasa_id);
       hesap_listesi=hq.getAll(Integer.valueOf(havale_musteri_field.getText()));
       hesap_table.setItems(hesap_listesi);
       feedback.setText("");
       }
       catch(NullPointerException ex){
           feedback.setText("Havale yapılacak hesap seçilmedi.");
       }
       catch(NumberFormatException ex2){
           feedback.setText("Geçerli para miktarı girilmedi.");
       }
       
    }

    public int getHavale_yapan_musteri_no() {
        return havale_yapan_musteri_no;
    }

    public void setHavale_yapan_musteri_no(int havale_yapan_musteri_no) {
        this.havale_yapan_musteri_no = havale_yapan_musteri_no;
    }

    public int getHavale_yapan_hesap_no() {
        return havale_yapan_hesap_no;
    }

    public void setHavale_yapan_hesap_no(int havale_yapan_hesap_no) {
        this.havale_yapan_hesap_no = havale_yapan_hesap_no;
    }

    public int getPersonel_id() {
        return personel_id;
    }

    public void setPersonel_id(int personel_id) {
        this.personel_id = personel_id;
    }

    public int getKasa_id() {
        return kasa_id;
    }

    public void setKasa_id(int kasa_id) {
        this.kasa_id = kasa_id;
    }

    @FXML
    private void detect(MouseEvent event) {
        if(detected==false){
        havale_yapan_hesap_no=getHavale_yapan_hesap_no();
        havale_yapan_musteri_no=getHavale_yapan_musteri_no();
        personel_id=getPersonel_id();
        kasa_id=getKasa_id();
        havale_yapan_musteri.setText("Havale Yapan Musteri No: "+getHavale_yapan_musteri_no()+"\nHavale Yapan Hesap No: "+getHavale_yapan_hesap_no()+"\nBakiye: "+hq.getBakiye(getHavale_yapan_hesap_no()));
       
        detected=true;
        }
    }

    @FXML
    private void searchButton(MouseEvent event) {
        try{
           hesap_listesi.clear();
        hesap_listesi.addAll(hq.getAll(Integer.valueOf(havale_musteri_field.getText())));
          hesap_table.setItems(hesap_listesi);
        }
         catch ( NumberFormatException e) {
           // feedback.setText("Musteri numarası sadece rakamlardan oluşur");
             feedback.setText("Geçerli müsteri numaarası girilmedi.");
        }
       
    }

    
    
}
