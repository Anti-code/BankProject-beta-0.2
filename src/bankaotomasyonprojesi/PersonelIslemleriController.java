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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Mehmet
 */
public class PersonelIslemleriController implements Initializable {

    @FXML
    private TableView<Personel> personel_table;
    @FXML
    private TableColumn<Personel, Integer> personel_id;
    @FXML
    private TableColumn<Personel, Integer> personel_tc;
    @FXML
    private TableColumn<Personel, String> personel_ad;
    @FXML
    private TableColumn<Personel, String> personel_tel;
    @FXML
    private TextField search_field;
    ObservableList<Personel> personel_listesi=FXCollections.observableArrayList();
    ObservableList<Kasa> kasa_listesi=FXCollections.observableArrayList();

    personelQueries pq=new personelQueries();
    kasaQueries kq=new kasaQueries();
    private Personel current_selected_personel;
    @FXML
    private ComboBox<String> kasa_box;
    @FXML
    private TableColumn<Personel, Integer> personel_kasa;
    
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        personel_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        personel_kasa.setCellValueFactory(new PropertyValueFactory<>("kasa_id"));
        personel_tc.setCellValueFactory(new PropertyValueFactory<>("tc"));
        personel_ad.setCellValueFactory(new PropertyValueFactory<>("ad"));
        personel_tel.setCellValueFactory(new PropertyValueFactory<>("cep"));
        
        personel_listesi=pq.getAll();
        
        personel_table.setItems(personel_listesi);
        personel_table.setOnMouseClicked((MouseEvent click) -> {
            if (click.getClickCount() == 2) {
                
                detailsButton(click);
            }
        });
        personel_table.setItems(personel_listesi);
        
        kasa_listesi.addAll(kq.getAll());
        kasa_box.getItems().add("Kasa 1");
        kasa_box.getItems().add("Kasa 2");
        kasa_box.getItems().add("Kasa 3");
        kasa_box.getItems().add("Kasa 4");
        kasa_box.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               
                pq.updateKasa(kasa_box.getSelectionModel().getSelectedIndex()+1,personel_table.getSelectionModel().getSelectedItem().getId());
                 
                personel_listesi=pq.getAll();
                personel_table.setItems(personel_listesi);
                System.out.println(kasa_box.getSelectionModel().getSelectedIndex()+1);
                //JOptionPane.showInternalMessageDialog(, "information","information", JOptionPane.INFORMATION_MESSAGE);
            }
        });
            
        
//        
       
    }    
    
    @FXML
    private void searchButton(MouseEvent event) {
         try {
            personel_listesi=(ObservableList<Personel>) pq.getPersonel(Integer.valueOf(search_field.getText()));
            
        } catch (Exception e) {
            personel_listesi=(ObservableList<Personel>)pq.getPersonel(search_field.getText());
            
        }
        finally{
            personel_table.setItems(personel_listesi);
        }
    }
    
    @FXML
    private void ekleButton(MouseEvent event) {
        Parent root2;
        try {
            root2 = FXMLLoader.load(getClass().getResource("personelEkle.fxml"));
            Scene scene2 = new Scene(root2);
            Stage stage2 = new Stage();
            stage2.setScene(scene2);
            stage2.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private Stage duzenleButton(MouseEvent event) throws IOException {
        current_selected_personel=personel_table.getSelectionModel().getSelectedItem();
        //System.out.println(getCurrent_selected_musteri_no());
        //mac.m_no=getCurrent_selected_musteri_no();
        //mac.initialize();
        //mac.getName_field().setText("ASDASD");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("personelEkle.fxml"));

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene((Pane) loader.load()));

            PersonelEkleController controller = 
              loader.<PersonelEkleController>getController();
            
            controller.getTc_field().setText(String.valueOf(current_selected_personel.getTc()));
            controller.getName_field().setText(current_selected_personel.getAd());
           controller.getLast_field().setText(current_selected_personel.getSoyad());
           controller.getSecure_field().setText(String.valueOf(current_selected_personel.getGuvenlik_no()));
            controller.getFather_field().setText(current_selected_personel.getBaba());
           controller.getMother_field().setText(current_selected_personel.getAnne());
           controller.getBirthdate_picker().setValue(current_selected_personel.getDogum_tarihi().toLocalDate());
            controller.getHomeland_field().setText(current_selected_personel.getDogumyeri());
            controller.getEducation_box().setText(current_selected_personel.getOgrenim());
            controller.getCollege_box().setText(current_selected_personel.getUniversite());
            controller.getCell_field().setText(current_selected_personel.getCep());
            controller.getOfficephone_field().setText(current_selected_personel.getIs());
            controller.getHousephone_field().setText(current_selected_personel.getEv());
           controller.getAddress_field().setText(current_selected_personel.getAdres());

            stage.show();
            personel_table.setItems(personel_listesi);
            return stage;

    }

    @FXML
    private void silButton(MouseEvent event) {
        Personel mark=personel_table.getSelectionModel().getSelectedItem();
        
        pq.deletePersonel(mark);
        try {
            searchButton(event);
        personel_table.setItems(personel_listesi);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    private void anamenuButton(MouseEvent event) {
            Stage stage = (Stage) search_field.getScene().getWindow();
        Parent root2;
        try {
            root2 = FXMLLoader.load(getClass().getResource("YoneticiKasaIslemleri.fxml"));
            Scene scene2 = new Scene(root2);
            Stage stage2 = new Stage();
            stage2.setScene(scene2);
            stage2.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        stage.close();
    }
    

    private void detailsButton(MouseEvent event){
        FXMLLoader loader = new FXMLLoader(
              getClass().getResource(
                "details.fxml"
              )
            );

            Stage stage = new Stage(StageStyle.DECORATED);
        try {
            stage.setScene(
                    new Scene(
                            (Pane) loader.load()
                    )
            );
        } catch (IOException ex) {
        }
//int tc, int guvenlik_no, String ad, String soyad, String anne, String baba, Date dogum_tarihi, String dogumyeri, String ogrenim,
//String universite, String sektor, String unvan, String cep, String ev, String is, String adres) {
            DetailsController controller = loader.<DetailsController>getController();
            current_selected_personel=personel_table.getSelectionModel().getSelectedItem();
            String[] t={"Personel id :"+current_selected_personel.getId(),
                     "Personel Tc :"+current_selected_personel.getTc(),
                     "Personel Adi :"+current_selected_personel.getAd(),
                     "Personel Soyadi :"+current_selected_personel.getSoyad(),
                     "Personel Güvenlik Numarası :"+current_selected_personel.getGuvenlik_no(),
                     "Personel Baba Adi: "+current_selected_personel.getBaba(),
                     "Personel Anne Adi: "+current_selected_personel.getAnne(),
                     "Personel Dogum Tarih i:"+String.valueOf(current_selected_personel.getDogum_tarihi()),
                     "Personel Dogum Yeri: "+current_selected_personel.getDogumyeri(),
                     "Personel Ogrenim Durumu :"+current_selected_personel.getOgrenim(),
                     "Personel Universite :"+current_selected_personel.getUniversite(),
                     "Personel Cep Telefonu: "+current_selected_personel.getCep(),
                     "Personel İş Telefonu: "+current_selected_personel.getIs(),
                     "Personel Ev Telefonu: "+current_selected_personel.getEv(),
                     "Personel Adres: "+current_selected_personel.getAdres()};
                     System.out.println(t.length);
            controller.getDetails().getItems().addAll(Arrays.asList(t));
            
            stage.show();
        
    }
    
}
