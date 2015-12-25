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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Mehmet
 */
public class MusteriIslemleriController implements Initializable {

    @FXML
    private TableView<Musteri> musteri_table;
    @FXML
    private TableColumn<Musteri, Integer> musteri_no_column;
    @FXML
    private TableColumn<Musteri, Integer> musteri_tc_column;
    @FXML
    private TableColumn<Musteri, String> musteri_name_column;
    @FXML
    private TableColumn<Musteri, String> musteri_tel_column;
    @FXML
    private TableColumn<Musteri, String> musteri_adres_column;
    @FXML
    private TextField search_field;

    /**
     * Initializes the controller class.
     */
    ObservableList<Musteri> musteri_listesi=FXCollections.observableArrayList();
    musteriQueries mq=new musteriQueries();
    private int kasa_id;
    private int personel_id;
    private Musteri current_selected_musteri;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       musteri_no_column.setCellValueFactory(new PropertyValueFactory<>("musteri_no"));
       musteri_tc_column.setCellValueFactory(new PropertyValueFactory<>("tc"));
       musteri_name_column.setCellValueFactory(new PropertyValueFactory<>("ad"));
       musteri_tel_column.setCellValueFactory(new PropertyValueFactory<>("cep"));
       musteri_adres_column.setCellValueFactory(new PropertyValueFactory<>("adres"));
       musteri_listesi=mq.getAll();
       musteri_table.setOnMouseClicked(new EventHandler<MouseEvent>() {
           @Override
                public void handle(MouseEvent click) {

                            if (click.getClickCount() == 2) {
                                details();
                            }   
                }
        });

                
                                               
     musteri_table.setItems(musteri_listesi);
}
  
    
    @FXML
    private void searchButton(MouseEvent event) {
        try {
            musteri_listesi=(ObservableList<Musteri>) mq.getMusteri(Integer.valueOf(search_field.getText()));
            
        } catch (Exception e) {
            musteri_listesi=(ObservableList<Musteri>)mq.getMusteri(search_field.getText());
            
        }
        finally{
            musteri_table.setItems(musteri_listesi);
        }
    }

    
    
    @FXML //not done jet
    private Stage duzenleButton(MouseEvent event) throws IOException {
        current_selected_musteri=musteri_table.getSelectionModel().getSelectedItem();
        //System.out.println(getCurrent_selected_musteri_no());
        //mac.m_no=getCurrent_selected_musteri_no();
        //mac.initialize();
        //mac.getName_field().setText("ASDASD");
        FXMLLoader loader = new FXMLLoader(
              getClass().getResource(
                "MusteriAdd.fxml"
              )
            );

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(
              new Scene(
                (Pane) loader.load()
              )
            );

            MusteriAddController controller = 
              loader.<MusteriAddController>getController();
            
            controller.getTc_field().setText(String.valueOf(current_selected_musteri.getTc()));
            controller.getName_field().setText(current_selected_musteri.getAd());
           controller.getLast_field().setText(current_selected_musteri.getSoyad());
           controller.getSecure_field().setText(String.valueOf(current_selected_musteri.getGuvenlik_no()));
            controller.getFather_field().setText(current_selected_musteri.getBaba());
           controller.getMother_field().setText(current_selected_musteri.getAnne());
           controller.getBirthdate_picker().setValue(current_selected_musteri.getDogum_tarihi().toLocalDate());
            controller.getHomeland_field().setText(current_selected_musteri.getDogumyeri());
            controller.getEducation_box().getSelectionModel().select(current_selected_musteri.getOgrenim());
            controller.getCollege_box().getSelectionModel().select(current_selected_musteri.getUniversite());
           controller.getBusiness_box().getSelectionModel().select(current_selected_musteri.getSektor());
            controller.getDegree_box().getSelectionModel().select(current_selected_musteri.getUnvan());
            controller.getCell_field().setText(current_selected_musteri.getCep());
            controller.getOfficephone_field().setText(current_selected_musteri.getIs());
            controller.getHousephone_field().setText(current_selected_musteri.getEv());
           controller.getAddress_field().setText(current_selected_musteri.getAdres());
           controller.setKasa_id(kasa_id);
           controller.setPersonel_id(personel_id);
            stage.show();
            musteri_table.setItems(musteri_listesi);
            return stage;

    }

    @FXML
    private void silButton(MouseEvent event) {
        int mark=musteri_table.getSelectionModel().getSelectedItem().getMusteri_no();
        
        mq.deleteMusteri(mark);
        try {
            searchButton(event);
        musteri_table.setItems(musteri_listesi);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    private void anaMenuButton(MouseEvent event) {
        
        Parent root2;
        try {
            root2 = FXMLLoader.load(getClass().getResource("anamenu.fxml"));
            Scene scene2 = new Scene(root2);
            Stage stage2 = new Stage();
            stage2.setScene(scene2);
            stage2.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Stage stage = (Stage) search_field.getScene().getWindow();
        stage.close();
    }
    private void details(){
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
            Logger.getLogger(MusteriIslemleriController.class.getName()).log(Level.SEVERE, null, ex);
        }
//int tc, int guvenlik_no, String ad, String soyad, String anne, String baba, Date dogum_tarihi, String dogumyeri, String ogrenim,
//String universite, String sektor, String unvan, String cep, String ev, String is, String adres) {
            DetailsController controller = loader.<DetailsController>getController();
            current_selected_musteri=musteri_table.getSelectionModel().getSelectedItem();
            String[] t={"Musteri no :"+current_selected_musteri.getMusteri_no(),
                     "Musteri Tc :"+current_selected_musteri.getTc(),
                     "Musteri Adi :"+current_selected_musteri.getAd(),
                     "Musteri Soyadi :"+current_selected_musteri.getSoyad(),
                     "Musteri Güvenlik Numarası :"+current_selected_musteri.getGuvenlik_no(),
                     "Musteri Baba Adi: "+current_selected_musteri.getBaba(),
                     "Musteri Anne Adi: "+current_selected_musteri.getAnne(),
                     "Musteri Dogum Tarih i:"+String.valueOf(current_selected_musteri.getDogum_tarihi()),
                     "Musteri Dogum Yeri: "+current_selected_musteri.getDogumyeri(),
                     "Musteri Ogrenim Durumu :"+current_selected_musteri.getOgrenim(),
                     "Musteri Universite :"+current_selected_musteri.getUniversite(),
                     "Musteri Sektor: "+current_selected_musteri.getSektor(),
                     "Musteri Unvan: "+current_selected_musteri.getUnvan(),
                     "Musteri Cep Telefonu: "+current_selected_musteri.getCep(),
                     "Musteri İş Telefonu: "+current_selected_musteri.getIs(),
                     "Musteri Ev Telefonu: "+current_selected_musteri.getEv(),
                     "Musteri Adres: "+current_selected_musteri.getAdres()};
                     System.out.println(t.length);
            controller.getDetails().getItems().addAll(Arrays.asList(t));
            
            stage.show();
        
    }
    

    
    public void setCurrent_selected_musteri(Musteri current_selected_musteri) {
        this.current_selected_musteri = current_selected_musteri;
    }

    @FXML
    private void ekleButtonEvent(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MusteriAdd.fxml"));

                            Stage stage = new Stage(StageStyle.DECORATED);
                            try {
                                stage.setScene( new Scene((Pane) loader.load() ) );
                            }
                            catch (IOException ex) {
                                    System.out.println(ex.getMessage());
                                }

                          HesapIslemleriController controller =loader.<HesapIslemleriController>getController();
                          controller.getSearch_field().setText(String.valueOf(musteri_table.getSelectionModel().getSelectedItem().getMusteri_no()));
                          controller.setKasa_id(kasa_id);
                          controller.setPersonel_id(personel_id);
                          stage.show();
    }

    @FXML
    private void hesaplarButton(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hesapIslemleri.fxml"));

                            Stage stage = new Stage(StageStyle.DECORATED);
                            try {
                                stage.setScene( new Scene((Pane) loader.load() ) );
                            }
                            catch (IOException ex) {
                                    System.out.println(ex.getMessage());
                                }

                          HesapIslemleriController controller =loader.<HesapIslemleriController>getController();
                          controller.getSearch_field().setText(String.valueOf(musteri_table.getSelectionModel().getSelectedItem().getMusteri_no()));
                          controller.setKasa_id(kasa_id);
                          controller.setPersonel_id(personel_id);
                          stage.show();
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
    
}
