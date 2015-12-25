/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankaotomasyonprojesi;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Mehmet
 */
public class YeniHesapController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField vade_tutari;
    @FXML
    private TextField faiz_orani;
    @FXML
    private TextField vade_tipi;
    @FXML
    private TextField vade_suresi;
    @FXML
    private DatePicker vade_sonu;
    @FXML
    private DatePicker vade_basi;
    
    @FXML
    private CheckBox vadeli_check;
    
    @FXML
    private TextField hesap_no_field;
    
    @FXML
    private ComboBox<String> para_birimi;
    
    @FXML
    private TableView<Hesap> hesap_table;
    
    @FXML
    private TableColumn<Hesap, String> hesap_no;
    @FXML
    private TableColumn<Hesap, String> hesap_turu;
    @FXML
    private TableColumn<Hesap, Float> bakiye;
    @FXML
    private TableColumn<Hesap, String> doviz_cinsi;
    @FXML
    private TableColumn<Hesap, String> hesap_durumu;
    
    
    ObservableList<Hesap> hesap_listesi=FXCollections.observableArrayList();
    private int musteri_no;
    private int personel_id;
    private int kasa_id;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vadeliCheck();
        para_birimi.setItems(paraBirimleri());
        hesap_listesi.addAll();
        hesap_no.setCellValueFactory(new PropertyValueFactory<>("no"));
        hesap_turu.setCellValueFactory(new PropertyValueFactory<>("tur"));
        doviz_cinsi.setCellValueFactory(new PropertyValueFactory<>("doviz_cinsi"));
        hesap_durumu.setCellValueFactory(new PropertyValueFactory<>("durum"));
        hesap_table.setItems(hesap_listesi);
        
        
    
    }    
    
    public ObservableList<String> paraBirimleri(){
        ObservableList<String> birimler=FXCollections.observableArrayList();
        birimler.add("TL");
        birimler.add("DOLAR");
        birimler.add("EURO");
        return birimler;
    }
    @FXML
    private void kaydetButton(MouseEvent event) {
        hesapQueries hq=new hesapQueries();
        Hesap hesap;
        
            if(vadeli_check.isSelected()){
            /*Ziraat Vadeli Türk Lirası Mevduat Hesabı; Türk Lirası ( ) cinsinden 1-730  gün arası vadeli olarak açtırılabilir
            Vade bitiminden önce para çekilmek istendiğinde, faiz uygulaması işlemeye devam eder: Yatırdığınız anapara, vadesiz mevduat faiz getirisiyle birlikte ödenir.
            Vadeli Türk Lirası Mevduat Hesabınız; vade bitiminde aksine bir talimat vermediğiniz sürece, otomatik olarak bir önceki vade süresi kadar yenilenir.*/
            
            hesap=new Hesap(Integer.valueOf(String.valueOf(getMusteri_no())+hesap_no_field.getText()), "vadeli", musteri_no, 0,Float.valueOf(vade_tutari.getText()),
                    Float.valueOf(faiz_orani.getText()), para_birimi.getSelectionModel().getSelectedItem(), "aktif",
                    Date.valueOf(vade_basi.getValue()), Date.valueOf(vade_sonu.getValue()), vade_tipi.getText(), Integer.valueOf(vade_suresi.getText()));
            System.out.println(hesap_no_field.getText()+ "  vadeli  "+ musteri_no+"   "+ 0 +"   " + vade_tutari.getText()+ "   "+
                    Float.valueOf(faiz_orani.getText())+"  "+ para_birimi.getSelectionModel().getSelectedItem()+"   "+ "   aktif   "+
                    Date.valueOf(vade_basi.getValue())+"    "+ Date.valueOf(vade_sonu.getValue())+"    " +vade_tipi.getText()+"   "+ vade_suresi.getText());
            boolean state=hq.hesapAc(hesap);
            if(state==false){
                hesap_no_field.setText("");
                hesap_no_field.setPromptText("Hesap numarası önceden alınmış");
            }
        }
        else{
            //int no, String tur, int musteri_no, float bakiye, float faiz, String doviz_cinsi, String durum
            hesap=new Hesap(Integer.valueOf(hesap_no_field.getText()), "vadesiz", musteri_no, 0, para_birimi.getSelectionModel().getSelectedItem(), "aktif");
            System.out.println(musteri_no);
            hq.hesapAc(hesap);
        }
        
       
    } 

    @FXML
    private void vadeliCheck() {
        vadeli_check.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(vadeli_check.isSelected()==true){
                    vade_basi.setDisable(false);
                    vade_sonu.setDisable(false);
                    vade_tutari.setDisable(false);
                    vade_tipi.setDisable(false);
                    vade_suresi.setDisable(false);
                    faiz_orani.setDisable(false);
                    


                //DateQ dq=new DateQ();
                //dq.setDate(13,java.sql.Date.valueOf(ld));
                }
                else{
                    vade_basi.setDisable(true);
                    vade_sonu.setDisable(true);
                    vade_tutari.setDisable(true);
                    vade_tipi.setDisable(true);
                    vade_suresi.setDisable(true);
                    faiz_orani.setDisable(true);
                }
                    }
        });
        
    }

    public int getMusteri_no() {
        return musteri_no;
    }

    public void setMusteri_no(int musteri_no) {
        this.musteri_no = musteri_no;
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
   
}
