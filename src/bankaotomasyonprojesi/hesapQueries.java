/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankaotomasyonprojesi;

/**
 *
 * @author Mehmet
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Mehmet
 */
public class hesapQueries extends DBConnection{
    public ObservableList<Hesap> getAll(int musteri_no){
        ObservableList<Hesap> hesap_list=FXCollections.observableArrayList();
        try{
            baglan();
            PreparedStatement ps=baglanti.prepareStatement("select * from hesap where musteri_no = ?");
            ps.setInt(1, musteri_no);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                
                hesap_list.add(new Hesap(rs.getInt("hesap_no"), rs.getString("hesap_tur"),rs.getInt("musteri_no"), rs.getFloat("hesap_bakiye"),
                                rs.getFloat("hesap_vade_tutari"), rs.getFloat("hesap_vade_faiz_orani"), rs.getString("hesap_doviz_cinsi"),
                                rs.getString("hesap_durum"), rs.getDate("hesap_vade_basi"), rs.getDate("hesap_vade_sonu"),
                                rs.getString("hesap_vade_tipi"),rs.getInt("hesap_vade_suresi")));
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
        
        return hesap_list;
    }
    public boolean hesapAc(Hesap h){
        try {
            baglan();
            PreparedStatement ps=baglanti.prepareStatement("insert into hesap() values(?,?,?,?,?,?,?,?,?,?,?,?)");
           // System.out.println(h.getNo()+"\n"+h.getTur()+"\n"+h.getBakiye()+"\n"+h.getDoviz_cinsi()+"\n"+h.getDurum()+"\n"+h.getVade_basi()+"\n"+h.getVade_sonu()+"\n"+h.getVade_tutari()+"\n"+h.getVade_tipi()+"\n"+h.getVade_suresi()+"\n"+h.getFaiz()+"\n"+h.getMusteri_no());
            ps.setInt(1, h.getNo());
            ps.setString(2, h.getTur());
            ps.setFloat(3, h.getBakiye());
            ps.setString(4, h.getDoviz_cinsi());
            ps.setString(5, h.getDurum());
            ps.setDate(6, h.getVade_basi());
            ps.setDate(7,h.getVade_sonu());
            ps.setFloat(8, h.getVade_tutari());
            ps.setString(9, h.getVade_tipi());
            ps.setInt(10, h.getVade_suresi());
            ps.setFloat(11, h.getFaiz());
            ps.setInt(12, h.getMusteri_no());
            
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (Exception e) {
            System.out.println("Hata-"+e.getMessage());
        }
        
        return false;
    }
    public boolean hesapKapat(int hesap_no){
        try {
            baglan();
            PreparedStatement ps=baglanti.prepareStatement("delete from hesap where hesap_no = ?");
            ps.setInt(1, hesap_no);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return false;
    }
    public float getBakiye(int hesap_no){
        float bakiye=0;
        try{
           baglan();
           PreparedStatement ps=baglanti.prepareStatement("select hesap_bakiye from hesap where hesap_no = ?");
           ps.setInt(1, hesap_no);
           ResultSet rs=ps.executeQuery();
           rs.next();
           bakiye=rs.getInt("hesap_bakiye");
           ps.close();
           rs.close();
           return bakiye;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            
            baglantiKes();
        }
        return -1;
    }
    public boolean paraChange(int hesap_no,int kasa_id, float miktar){
        try{
            baglan();
            
            PreparedStatement ps=baglanti.prepareStatement("update hesap set hesap_bakiye = hesap_bakiye+ ? where hesap_no = ?");
            ps.setFloat(1, miktar);
            ps.setInt(2, hesap_no);
            ps.executeUpdate();
            ps.close();
            kasaQueries kq=new kasaQueries();
            kq.change(kasa_id, -miktar);
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
    
  //public boolean hesapDuzenle(){}
    public boolean havale(int veren, int alan, float miktar, int kasa_id){
        try {
            baglan();
            PreparedStatement ps=baglanti.prepareStatement("update hesap set hesap_bakiye = hesap_bakiye - ? where hesap_no = ?");
            ps.setFloat(1, miktar);
            ps.setInt(2, veren);            
            ps.executeUpdate();           
            ps.close();
            ps=baglanti.prepareStatement("update hesap set hesap_bakiye = hesap_bakiye + ? where hesap_no=?");
            ps.setFloat(1, miktar);
            ps.setInt(2, alan);
            ps.executeUpdate();
            ps.close();
            kasaQueries kq=new kasaQueries();
            kq.change(kasa_id, miktar);
            System.out.println("havale yapidi");
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally{
            baglantiKes();
        }
        return false;
    }

    public ObservableList<Hesap> getHesap(int hesap_no) {
        ObservableList<Hesap> hesap_list=FXCollections.observableArrayList();
        try{
            baglan();
            PreparedStatement ps=baglanti.prepareStatement("select * from hesap where hesap_no like ?");
            ps.setInt(1, hesap_no);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                
                hesap_list.add(new Hesap(rs.getInt("hesap_no"), rs.getString("hesap_tur"),rs.getInt("musteri_no"), rs.getFloat("hesap_bakiye"),
                                rs.getFloat("hesap_vade_tutari"), rs.getFloat("hesap_vade_faiz_orani"), rs.getString("hesap_doviz_cinsi"),
                                rs.getString("hesap_durum"), rs.getDate("hesap_vade_basi"), rs.getDate("hesap_vade_sonu"),
                                rs.getString("hesap_vade_tipi"),rs.getInt("hesap_vade_suresi")));
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
        
        return hesap_list;
    }
   }


