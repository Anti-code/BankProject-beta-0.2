/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankaotomasyonprojesi;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseDragEvent;
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
public class HesapIslemleriController implements Initializable {

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
    
    /**
     * Initializes the controller class.
     */
    private Musteri m=null;
    private int personel_id;
    private int kasa_id;
    hesapQueries hq= new hesapQueries();
    ObservableList<Hesap> hesap_listesi=FXCollections.observableArrayList();
    @FXML
    private TextField search_field;
    private int selected_item;
    @FXML
    private TextField para_field;
    
    boolean detected=false;
    @FXML
    private Text feedback;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //
        
        
        
    }   
    
    
    @FXML
    private void havaleYap(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("havaleIslemi.fxml"));

                            Stage stage = new Stage(StageStyle.DECORATED);
                            try {
                                stage.setScene( new Scene((Pane) loader.load() ) );
                            }
                            catch (IOException ex) {
                                    System.out.println(ex.getMessage());
                                }

                          HavaleIslemiController controller =loader.<HavaleIslemiController>getController();
                          controller.setHavale_yapan_hesap_no(hesap_table.getSelectionModel().getSelectedItem().getNo());
                          controller.setHavale_yapan_musteri_no(Integer.valueOf(search_field.getText()));
                          controller.setKasa_id(kasa_id);
                          controller.setPersonel_id(personel_id);

                          stage.show();
        
        
    }
    
    @FXML
    private Stage hesapAc(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("yeniHesap.fxml"));

                            Stage stage = new Stage(StageStyle.DECORATED);
                            try {
                                stage.setScene( new Scene((Pane) loader.load() ) );
                            }
                            catch (IOException ex) {
                                    System.out.println(ex.getMessage());
                                }

                          YeniHesapController controller =loader.<YeniHesapController>getController();
                          controller.setMusteri_no(Integer.valueOf(search_field.getText()));
                        //controller.setMusteri_no(hesap_table.getSelectionModel().getSelectedItem().getMusteri_no());
                         // System.out.println("DADA openHesap"+m.getMusteri_no());

                          stage.show();
                          return stage;
        
        
        //not done yet
    }

    @FXML
    private void hesapKapat(MouseEvent event) {
        Hesap kapatilacak_hesap=hesap_table.getSelectionModel().getSelectedItem();
        try {
            
            hesapQueries hq=new hesapQueries();
            hq.hesapKapat(kapatilacak_hesap.getNo());
            tableUpdate();
            LogQuery lq=new LogQuery();
            Log l=new Log();
            l.setHesap_no(kapatilacak_hesap.getNo());
            l.setMusteri_no(Integer.valueOf(search_field.getText()));
            l.setPersonel_id(personel_id);
            l.setOlay("Hesap kapatildi");
            lq.addLog(l);
        } catch (NullPointerException e) {
            feedback.setText("Kapatmak istediğiniz hesanı tablodan seçin.");
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    
    
    private void tableUpdate(){
        /*ams_list.add(new AnaMenuIslem(12, 504, "Para Yatırma", dateFormat.format(date), 5000));
        tableUpdate(ams_list);*/
       int selection=Integer.valueOf(getSearch_field().getText());
       hesap_listesi.clear();
       hesap_listesi.addAll(hq.getAll(selection));
       hesap_no.setCellValueFactory(new PropertyValueFactory<>("no"));
       hesap_turu.setCellValueFactory(new PropertyValueFactory<>("tur"));
       bakiye.setCellValueFactory(new PropertyValueFactory<>("bakiye"));
       doviz_cinsi.setCellValueFactory(new PropertyValueFactory<>("doviz_cinsi"));
       hesap_durumu.setCellValueFactory(new PropertyValueFactory<>("durum"));
       hesap_table.setItems(hesap_listesi);
    }

    

    public Musteri getM() {
        return m;
    }

    public void setM(Musteri m) {
        this.m = m;
    }

    @FXML
    private void searchButton(MouseEvent event) {
        try{
           hesap_listesi=hq.getAll(Integer.valueOf(search_field.getText()));
        tableUpdate(); 
        }
         catch ( NumberFormatException e) {
            feedback.setText("Musteri numarası sadece rakamlardan olusur.");
        }
        
        
    }

    public TextField getSearch_field() {
        return search_field;
    }

    public void setSearch_field(TextField search_field) {
        this.search_field = search_field;
    }

    public int getSelected_item() {
        return selected_item;
    }

    public void setSelected_item(int selected_item) {
        tableUpdate();
        this.selected_item = selected_item;
    }

    @FXML
    private void update(MouseEvent event) {
        if(detected==false){
        hesap_listesi=hq.getAll(Integer.valueOf(search_field.getText()));
        tableUpdate();
        detected=true;
        }
    }
    private void onEnter(ActionEvent event) {
        
        para_field.setVisible(false);
    }
    @FXML
    private void paraYatir(MouseEvent event) {
        
        
            kasaQueries kq=new kasaQueries();
            
        try {
            if(kq.getKasa(kasa_id).getBakiye()>Float.valueOf(para_field.getText())){
                hq.paraChange(hesap_table.getSelectionModel().getSelectedItem().getNo(),kasa_id, Float.valueOf(para_field.getText()));
                tableUpdate();
                LogQuery lq=new LogQuery();
                Log l=new Log();
                l.setHesap_no(hesap_table.getSelectionModel().getSelectedItem().getNo());
                l.setMusteri_no(Integer.valueOf(search_field.getText()));
                l.setPersonel_id(personel_id);
                l.setKasa_id(kasa_id);
                l.setMiktar(Float.valueOf(para_field.getText()));
                l.setOlay("Para yatirildi");
                
            }
        } catch ( NumberFormatException e) {
            feedback.setText("Para girişine sadece rakam girebilirsiniz.");
        }
           
            
            
        
        
    }

    @FXML
    private void paraCek(MouseEvent event) {
       
            kasaQueries kq=new kasaQueries();
            try{
            if(kq.getKasa(kasa_id).getBakiye()>Float.valueOf(para_field.getText())){
                hq.paraChange(hesap_table.getSelectionModel().getSelectedItem().getNo(),kasa_id, -Float.valueOf(para_field.getText()));
                tableUpdate();
                LogQuery lq=new LogQuery();
                Log l=new Log();
                l.setHesap_no(hesap_table.getSelectionModel().getSelectedItem().getNo());
                l.setMusteri_no(Integer.valueOf(search_field.getText()));
                l.setPersonel_id(personel_id);
                l.setKasa_id(kasa_id);
                l.setMiktar(Float.valueOf(para_field.getText()));
                l.setOlay("Para cekildi");
            }
            }
         catch ( NumberFormatException e) {
            feedback.setText("Para girişine sadece rakam girebilirsiniz.");
        }
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
