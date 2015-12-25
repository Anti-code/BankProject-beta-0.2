/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankaotomasyonprojesi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author Mehmet
 */
//inttc, int guvenlik_no, String ad, String soyad, String anne, String baba, String dogumyeri, String ogrenim, String universite, String sektor, String unvan, String cep, String ev, String is, String adres
public class musteriQueries extends DBConnection{
    public ObservableList<Musteri> getAll(){
        ObservableList<Musteri> musteri_list=FXCollections.observableArrayList();
        try{
            baglan();
            PreparedStatement ps=baglanti.prepareStatement("select * from musteri");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Musteri m=new Musteri(rs.getInt("musteri_tc"),rs.getInt("musteri_guvenlik_no"),
                        rs.getString("musteri_ad"),rs.getString("musteri_soyad"),rs.getString("musteri_anne"),
                        rs.getString("musteri_baba"),rs.getDate("musteri_dogum_tarihi"),rs.getString("musteri_dogum_yeri"),rs.getString("musteri_ogrenim"),
                        rs.getString("musteri_universite"),rs.getString("musteri_sektor"),rs.getString("musteri_unvan"),
                        rs.getString("musteri_cep"),rs.getString("musteri_ev"),rs.getString("musteri_is"),rs.getString("musteri_adres"));
                m.setMusteri_no(rs.getInt("musteri_no"));
                //System.out.println(m.getMusteri_no());
                musteri_list.add(m);
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
        
        return musteri_list;
    }
    public ObservableList<Musteri> getMusteri(int musteri_no){
        Musteri m=null;
        ObservableList<Musteri> musteri_list=FXCollections.observableArrayList();
        try{
            baglan();
            PreparedStatement ps=baglanti.prepareStatement("select * from musteri where musteri_no like ?");
            ps.setString(1,"%"+musteri_no+"%");
            ResultSet rs=ps.executeQuery();
            rs.next();
            while(rs.next()){
                m=new Musteri( rs.getInt("musteri_tc"),rs.getInt("musteri_guvenlik_no"),
                        rs.getString("musteri_ad"),rs.getString("musteri_soyad"),rs.getString("musteri_anne"),
                        rs.getString("musteri_baba"),rs.getDate("musteri_dogum_tarihi"),rs.getString("musteri_dogum_yeri"),rs.getString("musteri_ogrenim"),
                        rs.getString("musteri_universite"),rs.getString("musteri_sektor"),rs.getString("musteri_unvan"),
                        rs.getString("musteri_cep"),rs.getString("musteri_ev"),rs.getString("musteri_is"),rs.getString("musteri_adres"));
                m.setMusteri_no(rs.getInt("musteri_no"));
                musteri_list.add(m);
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
        return musteri_list;
    }
    public ObservableList<Musteri> getMusteri(String musteri_ad){
        ObservableList<Musteri> musteri_list=FXCollections.observableArrayList();
        try{
            baglan();
            PreparedStatement ps=baglanti.prepareStatement("select * from musteri where musteri_ad like ?");
            ps.setString(1,"%" + musteri_ad + "%");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Musteri m=new Musteri( rs.getInt("musteri_tc"),rs.getInt("musteri_guvenlik_no"),
                        rs.getString("musteri_ad"),rs.getString("musteri_soyad"),rs.getString("musteri_anne"),
                        rs.getString("musteri_baba"),rs.getDate("musteri_dogum_tarihi"),rs.getString("musteri_dogum_yeri"),rs.getString("musteri_ogrenim"),
                        rs.getString("musteri_universite"),rs.getString("musteri_sektor"),rs.getString("musteri_unvan"),
                        rs.getString("musteri_cep"),rs.getString("musteri_ev"),rs.getString("musteri_is"),rs.getString("musteri_adres"));
                m.setMusteri_no(rs.getInt("musteri_no"));
                musteri_list.add(m);
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
        return musteri_list;
    }
    
    public int getLast(){
        int id = -2;
        try{
            
            baglan();
            PreparedStatement ps = baglanti.prepareStatement("SELECT MAX(musteri_no) AS id FROM musteri");  
            ResultSet rs = ps.executeQuery();
            rs.next();
            id = rs.getInt("id")+1;
            ps.close();
            rs.close();
            return id;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            baglantiKes();
        }
        return -1;
    }
    public boolean addMusteri(Musteri m){
        try {
            baglan();
            PreparedStatement ps=baglanti.prepareStatement("insert into musteri values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, getLast());//m.getMusteri_no()
            ps.setInt(2, m.getTc());
            ps.setString(3, m.getAd());
            ps.setString(4, m.getSoyad());
            ps.setInt(5, m.getGuvenlik_no());
            ps.setString(6, m.getBaba());
            ps.setString(7, m.getAnne());
            ps.setDate(8,m.getDogum_tarihi());
            ps.setString(9, m.getDogumyeri());
            ps.setString(10, m.getOgrenim());
            ps.setString(11, m.getUniversite());
            ps.setString(12, m.getSektor());
            ps.setString(13, m.getUnvan());
            ps.setString(14, m.getCep());
            ps.setString(15, m.getEv());
            ps.setString(16, m.getIs());
            ps.setString(17, m.getAdres());
            
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return false;
    }
    public boolean deleteMusteri(int musteri_no){
        try {
            baglan();
            PreparedStatement ps=baglanti.prepareStatement("delete from musteri where musteri_no = ?");
            ps.setInt(1, musteri_no);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return false;
    }
    public boolean updateMusteri(Musteri original, Musteri changed){
        try {
            deleteMusteri(original.getMusteri_no());
            addMusteri(changed);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return false;
    }
}
