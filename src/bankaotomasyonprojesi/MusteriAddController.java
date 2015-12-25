/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankaotomasyonprojesi;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import static javafx.scene.input.KeyCode.T;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mehmet
 */
public class MusteriAddController implements Initializable {

    
    @FXML
    private TextField cell_field;
    @FXML
    private TextField officephone_field;
    @FXML
    private TextField tc_field;
    @FXML
    private TextField father_field;
    @FXML
    private TextField mother_field;
    @FXML
    private TextField name_field;
    @FXML
    private TextField housephone_field;
    @FXML
    private TextField last_field;
    @FXML
    private TextField secure_field;
    @FXML
    private TextArea address_field;
    @FXML
    private TextField homeland_field;
    @FXML
    private ComboBox<String> education_box;
    @FXML
    private ComboBox<String> degree_box;
    @FXML
    private ComboBox<String> business_box;
    @FXML
    private ComboBox<String> college_box;
    @FXML
    private DatePicker birthdate_picker;
    @FXML
    private AnchorPane window;
    
    /**
     * Initializes the controller class.
     */
    ObservableList<String> unilist=FXCollections.observableArrayList();
    ObservableList<String> ogrenimlist=FXCollections.observableArrayList();
    ObservableList<String> sektorlist=FXCollections.observableArrayList();
    private int kasa_id;
    private int personel_id;
    private musteriQueries mq=new musteriQueries();
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        String[] ogrenim={"Hiç okula gitmemiş",
                          "Lise mezunu",
                          "Üniversite mezunu",
                          "Yüksek lisans/Doktora mezunu",
                          "Yüksekokul mezunu"};
        
        String[] sektor={"Metal çelik sanayi",
                        "Makina sanayi",
                       " Mobilya sanayi",
                       " İnşaat dekorasyon",
                        "Turizm-seyahat",
                        "Tekstil",
                        "İnşaat",
                        "Ambalaj sanayi",
                        "Moda-giyim",
                        "Taşımacılık-lojistik",
                        "Kımya-boya sanayi",
                        "Otomotiv sanayi",
                        "Elektrik",
                        "Reklam-tanıtım",
                        "Emlak",
                        "Para ve menkul kıymetler",
                        "İnternet hizmetleri",
                        "Egitim",
                        "Endustrıyel",
                        "Güzellik-bakım",
                        "Bilgısayar-yazılım",
                        "Elektronik",
                        "Holdingler",
                        "Alışveriş",
                        "Ev-yaşam",
                        "Lastik-kaucuk sanayi",
                        "Finans-yatırım-sigorta",
                        "Isıtma-sogutma sanayi",
                        "Gıda-içecek",
                        "Orman ürünleri",
                        "Cevre-su",
                        "Plastik sanayi",
                        "Sağlık",
                        "Hizmet",
                        "Güvenlik-savunma",
                        "İthalat-ihracat-ticaret",
                        "Tarım-hayvancılık",
                        "Ofis-kırtasiye",
                        "Deri",
                        "Cam-porselen-seramik",
                        "Basın-yayın",
                        "Fuar",
                        "Toplum kuruluşları",
                        "Takı ve mucevherat",
                        "Jeoloji",
                        "Sanat-kültür",
                        "Kagıt-seluloz sanayi",
                        "Sigorta",
                        "Enerji",
                        "Maden-dogal kaynak",
                        "Spor",
                        "Elektronık sanayi",
                        "Denızcilik",
                        "Diger"};
        
        ogrenimlist.addAll(Arrays.asList(ogrenim));
        education_box.setItems(ogrenimlist);
        education_box.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
           // if("Yüksek lisans/Doktora mezunu".equals(education_box.getSelectionModel().getSelectedItem()) || "Üniversite mezunu".equals(education_box.getSelectionModel().getSelectedItem())){
                college_box.setDisable(false);
                String[] universite_url={"ORTADOĞU TEKNİK ÜNİVERSİTESİ",
                    "HACETTEPE ÜNİVERSİTESİ  İSTANBUL ÜNİVERSİTESİ",
                    "İHSAN DOĞRAMACI BİLKENT ÜNİVERSİTESİ",
                    "ANKARA ÜNİVERSİTESİ",
                    "İSTANBUL TEKNİK ÜNİVERSİTESİ",
                    "EBZE TEKNİK ÜNİVESİTESİ" ,
                    "EGE ÜNİVERSİTESİ",
                    "GAZİ ÜNİVERSİTESİ",
                    "SABANCI ÜNİVERSİTESİ",
                    "KOÇ ÜNİVERSİTESİ",
                    "BOĞAZİÇİ ÜNİVERSİTESİ",
                    "ATATÜRK ÜNİVERSİTESİ",
                    "YILDIZ TEKNİK ÜNİVERSİTESİ",
                    "ERCİYES ÜNİVERSİTESİ"};    
                unilist.addAll(Arrays.asList(universite_url));
                college_box.setItems(unilist);

        });
        
        sektorlist.addAll(Arrays.asList(sektor));
        business_box.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
             degree_box.setDisable(false);
                
        });
       
        business_box.setItems(sektorlist);
        //update();
       // System.out.println(m.getMusteri_no());
        
    }    
   
    @FXML
    private void cancelClick(MouseEvent event) {
        Stage stage = (Stage) window.getScene().getWindow();
    // do what you have to do
        stage.close();
    }

    @FXML
    private void saveClick(MouseEvent event) {
        mq.addMusteri(new Musteri(Integer.valueOf(tc_field.getText()), Integer.valueOf(secure_field.getText()), name_field.getText(), last_field.getText(),
                mother_field.getText(), father_field.getText(), Date.valueOf(birthdate_picker.getValue()), homeland_field.getText(),
                education_box.getSelectionModel().getSelectedItem(),college_box.getSelectionModel().getSelectedItem(),
                business_box.getSelectionModel().getSelectedItem(), degree_box.getSelectionModel().getSelectedItem(),
                cell_field.getText(), housephone_field.getText(), officephone_field.getText(), address_field.getText()));
        Stage stage = (Stage) window.getScene().getWindow();
        stage.close();
    }

   

  
    public TextField getName_field() {
        return name_field;
    }

    public void setName_field(TextField name_field) {
        this.name_field = name_field;
    }

    public musteriQueries getMq() {
        return mq;
    }

    public void setMq(musteriQueries mq) {
        this.mq = mq;
    }

    public TextField getCell_field() {
        return cell_field;
    }

    public void setCell_field(TextField cell_field) {
        this.cell_field = cell_field;
    }

    public TextField getOfficephone_field() {
        return officephone_field;
    }

    public void setOfficephone_field(TextField officephone_field) {
        this.officephone_field = officephone_field;
    }

    public TextField getTc_field() {
        return tc_field;
    }

    public void setTc_field(TextField tc_field) {
        this.tc_field = tc_field;
    }

    public TextField getFather_field() {
        return father_field;
    }

    public void setFather_field(TextField father_field) {
        this.father_field = father_field;
    }

    public TextField getMother_field() {
        return mother_field;
    }

    public void setMother_field(TextField mother_field) {
        this.mother_field = mother_field;
    }

    public TextField getHousephone_field() {
        return housephone_field;
    }

    public void setHousephone_field(TextField housephone_field) {
        this.housephone_field = housephone_field;
    }

    public TextField getLast_field() {
        return last_field;
    }

    public void setLast_field(TextField last_field) {
        this.last_field = last_field;
    }

    public TextField getSecure_field() {
        return secure_field;
    }

    public void setSecure_field(TextField secure_field) {
        this.secure_field = secure_field;
    }

    public TextArea getAddress_field() {
        return address_field;
    }

    public void setAddress_field(TextArea address_field) {
        this.address_field = address_field;
    }

    public TextField getHomeland_field() {
        return homeland_field;
    }

    public void setHomeland_field(TextField homeland_field) {
        this.homeland_field = homeland_field;
    }

    public ComboBox<String> getEducation_box() {
        return education_box;
    }

    public void setEducation_box(ComboBox<String> education_box) {
        this.education_box = education_box;
    }

    public ComboBox<String> getDegree_box() {
        return degree_box;
    }

    public void setDegree_box(ComboBox<String> degree_box) {
        this.degree_box = degree_box;
    }

    public ComboBox<String> getBusiness_box() {
        return business_box;
    }

    public void setBusiness_box(ComboBox<String> business_box) {
        this.business_box = business_box;
    }

    public ComboBox<String> getCollege_box() {
        return college_box;
    }

    public void setCollege_box(ComboBox<String> college_box) {
        this.college_box = college_box;
    }

    public DatePicker getBirthdate_picker() {
        return birthdate_picker;
    }

    public void setBirthdate_picker(DatePicker birthdate_picker) {
        this.birthdate_picker = birthdate_picker;
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
