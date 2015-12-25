/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankaotomasyonprojesi;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.LocalDateTimeStringConverter;

/**
 * FXML Controller class
 *
 * @author Mehmet
 */
public class AnamenuController implements Initializable {

    @FXML
    private TableView<AnaMenuIslem> musteri_table;
    @FXML
    private Text siramatik;
    @FXML
    private Text bitis_saati;
    @FXML
    private Text kasa_bakiyesi;
    @FXML
    private TableColumn<AnaMenuIslem, Integer> musteri_no;
    @FXML
    private TableColumn<AnaMenuIslem, Integer> hesap_no;
    @FXML
    private TableColumn<AnaMenuIslem,String> yapilan_islem;
    @FXML
    private TableColumn<AnaMenuIslem, Float> bakiye;
    @FXML
    private TableColumn<AnaMenuIslem, String> tarih_saat;//Date

    /**
     * Initializes the controller class.
     */
    
    private int secilen_musteri_no;
    final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    final Date date = new Date();
    personelQueries pq=new personelQueries();
    kasaQueries kq=new kasaQueries();
    private int personel_id;
    private boolean start=false;
    private Personel calisan;
    static ObservableList<AnaMenuIslem> ams_list=FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    private void tableUpdate(ObservableList<AnaMenuIslem > islem_listesi){
        /*ams_list.add(new AnaMenuIslem(12, 504, "Para Yatırma", dateFormat.format(date), 5000));
        tableUpdate(ams_list);*/
       musteri_no.setCellValueFactory(new PropertyValueFactory<>("musteri_no"));
       hesap_no.setCellValueFactory(new PropertyValueFactory<>("hesap_no"));
       yapilan_islem.setCellValueFactory(new PropertyValueFactory<>("yapilan_islem"));
       bakiye.setCellValueFactory(new PropertyValueFactory<>("bakiye"));
       tarih_saat.setCellValueFactory(new PropertyValueFactory<>("islem_tarihi"));
       musteri_table.setItems(islem_listesi);
    }
    
   
    @FXML
    private void musteriIslemleri(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource(
              "musteriIslemleri.fxml"
            )
          );

          Stage stage = new Stage(StageStyle.DECORATED);
          stage.setScene(
            new Scene(
              (Pane) loader.load()
            )
          );

          MusteriIslemleriController mic=loader.<MusteriIslemleriController>getController();
          mic.setKasa_id(calisan.getKasa_id());
          mic.setPersonel_id(calisan.getId());


          stage.show();
         Stage stage2 = (Stage) bitis_saati.getScene().getWindow();
        stage2.close();
    }

    @FXML
    private void raporButton(MouseEvent event) {
    }

    @FXML  //Not done yet.
    private void devirIslemleri(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource(
              "kasaIslemleri.fxml"
            )
          );

          Stage stage = new Stage(StageStyle.DECORATED);
          stage.setScene(
            new Scene(
              (Pane) loader.load()
            )
          );

          KasaIslemleriController kic=loader.<KasaIslemleriController>getController();
          kic.setKasa_id(calisan.getKasa_id());
          kic.setPersonel_id(calisan.getId());


          stage.show();
    }

    @FXML
    private void siramatikButton(MouseEvent event) {
        siramatik.setText(String.valueOf(Integer.valueOf(siramatik.getText())+1));
    }

    public int getSecilen_musteri_no() {
        return secilen_musteri_no;
    }

    private void selectMusteri(MouseEvent event) {
        
        secilen_musteri_no=musteri_table.getSelectionModel().getSelectedItem().getHesap_no();
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
        
        Stage stage = (Stage) kasa_bakiyesi.getScene().getWindow();
        stage.close();
    }

    

    public int getPersonel_id() {
        return personel_id;
    }

    public void setPersonel_id(int personel_id) {
        this.personel_id = personel_id;
    }

    @FXML
    private void onDetect(MouseEvent event) {
        if(start==false){
            calisan=pq.getPersonel(getPersonel_id()).get(0);
            kasa_bakiyesi.setText(String.valueOf(kq.getKasa(calisan.getKasa_id()).getBakiye()));
            start=true;
        }
    }


    
}
