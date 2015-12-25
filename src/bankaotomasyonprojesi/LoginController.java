/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankaotomasyonprojesi;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Mehmet
 */
public class LoginController implements Initializable {

    @FXML
    private TextField personel_id;
    @FXML
    private PasswordField personel_sifre;
    private DBConnection db=new DBConnection();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public Stage openPersonel() throws IOException {
          FXMLLoader loader = new FXMLLoader(
            getClass().getResource(
              "anamenu.fxml"
            )
          );

          Stage stage = new Stage(StageStyle.DECORATED);
          stage.setScene(
            new Scene(
              (Pane) loader.load()
            )
          );

          AnamenuController amc=loader.<AnamenuController>getController();
          amc.setPersonel_id(Integer.valueOf(personel_id.getText()));


          stage.show();
         Stage stage2 = (Stage) personel_id.getScene().getWindow();
        stage2.close();
          return stage;
        }

    private void openYonetici(){
        
        
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("YoneticiKasaIslemleri.fxml"));
            Scene scene = new Scene(root);
            Stage stage2 = new Stage();
            stage2.setScene(scene);
            stage2.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Stage stage = (Stage) personel_id.getScene().getWindow();
        stage.close();
    }
    
    
    @FXML
    private void loginButton(MouseEvent event) {
        DBConnection db=new DBConnection();
        try {
            db.baglan();
            PreparedStatement ps=db.baglanti.prepareStatement("select personel_sifre, personel_yonetici from personel where personel_id = ?"); 
            ps.setInt(1, Integer.valueOf(personel_id.getText()));
            ResultSet rs=ps.executeQuery();
            rs.next();
            final boolean statu=rs.getBoolean("personel_yonetici");
            final String password = rs.getString("personel_sifre");
            rs.close();
            ps.close();
            if(personel_sifre.getText() == null ? password == null : personel_sifre.getText().equals(password)){
               if(statu==true){
                   openYonetici();
                   
               }
               else{
                   openPersonel();
               }
               
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally{
            
            db.baglantiKes();
        }
    }

    
}
