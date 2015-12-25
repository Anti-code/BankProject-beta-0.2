/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankaotomasyonprojesi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Mehmet
 */
public class kasaQueries extends DBConnection{
    public ObservableList<Kasa> getAll(){
        ObservableList<Kasa> kasa_list=FXCollections.observableArrayList();
        try{
           baglan();
           PreparedStatement ps=baglanti.prepareStatement("select * from kasa");
           ResultSet rs=ps.executeQuery();
           rs.next();
           while(rs.next()){
               kasa_list.add(new Kasa(rs.getInt("kasa_id"), rs.getFloat("kasa_para")));
           }
           
           ps.close();
           rs.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            
            baglantiKes();
        }
        //kasa_list.remove(0);
        return kasa_list;
    }
    public Kasa getKasa (int kasa_id){
        try{
           baglan();
           PreparedStatement ps=baglanti.prepareStatement("select * from kasa where kasa_id = ?");
           ps.setInt(1, kasa_id);
           ResultSet rs=ps.executeQuery();
           rs.next();
           Kasa k= new Kasa(rs.getInt("kasa_id"), rs.getFloat("kasa_para"));
           rs.close();
           ps.close();
           return k;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            
            baglantiKes();
        }
        return null;
    }
    public boolean change(int kasa_id, float miktar){
        try{
           baglan();
           PreparedStatement ps=baglanti.prepareStatement("update kasa set kasa_para = kasa_para + ? where kasa_id = ?");
           ps.setFloat(1, miktar);
           ps.setInt(2, kasa_id);
           ps.executeUpdate();
           ps.close();
           return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            
            baglantiKes();
        }
        return false;
    }
    
  //public boolean devret(Kasa devreden, Kasa devrelan)
}
