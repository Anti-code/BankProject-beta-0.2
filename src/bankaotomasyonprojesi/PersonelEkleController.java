/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankaotomasyonprojesi;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Mehmet
 */
public class PersonelEkleController implements Initializable {

    @FXML
    private TextField college_box;
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
    private TextField education_box;
    @FXML
    private TextField housephone_field;
    @FXML
    private TextField homeland_field;
    @FXML
    private TextField last_field;
    @FXML
    private TextField secure_field;
    @FXML
    private TextArea address_field;
    private TextField id_field;
    @FXML
    private DatePicker birthdate_picker;
    private int personel_id;
    private personelQueries pq=new personelQueries();
    @FXML
    private TextField sifre_field;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          
        
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

    public TextField getName_field() {
        return name_field;
    }

    public void setName_field(TextField name_field) {
        this.name_field = name_field;
    }

    

    public TextField getHousephone_field() {
        return housephone_field;
    }

    public void setHousephone_field(TextField housephone_field) {
        this.housephone_field = housephone_field;
    }

    public TextField getHomeland_field() {
        return homeland_field;
    }

    public void setHomeland_field(TextField homeland_field) {
        this.homeland_field = homeland_field;
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

    public TextField getId_field() {
        return id_field;
    }

    public void setId_field(TextField id_field) {
        this.id_field = id_field;
    }

    public DatePicker getBirthdate_picker() {
        return birthdate_picker;
    }

    public void setBirthdate_picker(DatePicker birthdate_picker) {
        this.birthdate_picker = birthdate_picker;
    }
        

    @FXML
    private void cancelButton(MouseEvent event) {
        
    }

    @FXML
    private void saveButton(MouseEvent event) {
        if(personel_id==0){
            Personel p=new Personel(Integer.valueOf(secure_field.getText()),name_field.getText(), last_field.getText(), mother_field.getText(), father_field.getText(), Date.valueOf(birthdate_picker.getValue()), homeland_field.getText(), education_box.getText(), college_box.getText(), cell_field.getText(), homeland_field.getText(), officephone_field.getText(), address_field.getText(), false, Integer.valueOf(tc_field.getText()));
            p.setSifre(sifre_field.getText());
            pq.addPersonel(p);
        
        }
    }

    public personelQueries getPq() {
        return pq;
    }

    public void setPq(personelQueries pq) {
        this.pq = pq;
    }

    public TextField getCollege_box() {
        return college_box;
    }

    public void setCollege_box(TextField college_box) {
        this.college_box = college_box;
    }

    public TextField getEducation_box() {
        return education_box;
    }

    public void setEducation_box(TextField education_box) {
        this.education_box = education_box;
    }

    public int getPersonel_id() {
        return personel_id;
    }

    public void setPersonel_id(int personel_id) {
        this.personel_id = personel_id;
    }

    public TextField getSifre_field() {
        return sifre_field;
    }

    public void setSifre_field(TextField sifre_field) {
        this.sifre_field = sifre_field;
    }

   
}
