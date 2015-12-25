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
public class personelQueries extends DBConnection{
    public ObservableList<Personel> getAll(){
        ObservableList<Personel> personel_list=FXCollections.observableArrayList();
        try{
            baglan();
            PreparedStatement ps=baglanti.prepareStatement("select * from personel");
            ResultSet rs=ps.executeQuery();
            // rs.next() 
            while(rs.next()){
            /*   int id,
                int guvenlik_no,
                String ad,
                String soyad, 
                String anne,
                String baba,
                Date dogum_tarihi,
                String dogumyeri,
                String ogrenim,
                String universite, 
                String cep, 
                String ev, 
                String is, 
                String adres, 
                int yonetici,
                int tc
                
            */
            
            if(rs.getInt("personel_id")==500){
                continue;
            }    
                        Personel p;
                        
            personel_list.add(p=new Personel(
                        rs.getInt("personel_guvenlik"),
                        rs.getString("personel_ad"),
                        rs.getString("personel_soyad"),
                        rs.getString("personel_anne"),
                        rs.getString("personel_baba"),
                        rs.getDate("personel_dogum_tarihi"),
                        rs.getString("personel_dogum_yeri"),
                        rs.getString("personel_ogrenim"),
                        rs.getString("personel_universite"),
                        rs.getString("personel_cep"),
                        rs.getString("personel_ev"),
                        rs.getString("personel_is"),
                        rs.getString("personel_adres"),
                        rs.getBoolean("personel_yonetici"),
                        rs.getInt("personel_tc")));
                        p.setId(rs.getInt("personel_id"));
                        p.setKasa_id(rs.getInt("kasa_id"));
            
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
        return personel_list;
    }
    public ObservableList<Personel> getPersonel(String personel_ad){
        ObservableList<Personel> personel_list=FXCollections.observableArrayList();
        try{
            baglan();
            PreparedStatement ps=baglanti.prepareStatement("select * from personel where personel_ad like ?");
            ps.setString(1, "%" + personel_ad + "%");
            ResultSet rs=ps.executeQuery();
            Personel p;
            while(rs.next()){
                personel_list.add(p=new Personel(rs.getInt("personel_guvenlik"),
                        rs.getString("personel_ad"),rs.getString("personel_soyad"),rs.getString("personel_anne"),
                        rs.getString("personel_baba"),rs.getDate("personel_dogum_tarihi"),rs.getString("personel_dogum_yeri"),rs.getString("personel_ogrenim"),
                        rs.getString("personel_universite"),
                        rs.getString("personel_cep"),rs.getString("personel_ev"),rs.getString("personel_is"),rs.getString("personel_adres"),rs.getBoolean("personel_yonetici"),rs.getInt("personel_tc")));
                        p.setKasa_id(rs.getInt("kasa_id"));
                        p.setId(rs.getInt("personel_id"));
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
        return personel_list;
    }
   
    public ObservableList<Personel> getPersonel(int personel_id){
        ObservableList<Personel> personel_list=FXCollections.observableArrayList();
        try{
            baglan();
            PreparedStatement ps=baglanti.prepareStatement("select * from personel where personel_id like ?");
            ps.setString(1,"%" + personel_id + "%");
            ResultSet rs=ps.executeQuery();
            Personel p;
            while(rs.next()){
                personel_list.add(p=new Personel(rs.getInt("personel_guvenlik"),
                        rs.getString("personel_ad"),rs.getString("personel_soyad"),rs.getString("personel_anne"),
                        rs.getString("personel_baba"),rs.getDate("personel_dogum_tarihi"),rs.getString("personel_dogum_yeri"),rs.getString("personel_ogrenim"),
                        rs.getString("personel_universite"),
                        rs.getString("personel_cep"),rs.getString("personel_ev"),rs.getString("personel_is"),rs.getString("personel_adres"),rs.getBoolean("personel_yonetici"),rs.getInt("personel_tc")));
                        p.setKasa_id(rs.getInt("kasa_id"));
                        p.setId(rs.getInt("personel_id"));
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
        return personel_list;
    }
    public boolean addPersonel(Personel p){
        try {
            baglan();
            PreparedStatement ps=baglanti.prepareStatement("INSERT INTO personel(personel_sifre, personel_tc, personel_ad,personel_soyad, personel_guvenlik, personel_baba, personel_anne, personel_dogum_tarihi, personel_dogum_yeri, personel_ogrenim, personel_universite, personel_cep, personel_is, personel_ev,personel_adres, personel_yonetici) VALUES (?,?,?,?,?,?, ?,?,?,?,?,?, ?,?,?,?)");
            ps.setString(1, p.getSifre());
            ps.setInt(2, p.getTc());
            ps.setString(3, p.getAd());
            ps.setString(4, p.getSoyad());
            ps.setInt(5, p.getGuvenlik_no());
            ps.setString(6, p.getBaba());
            ps.setString(7, p.getAnne());
            ps.setDate(8,p.getDogum_tarihi());
            ps.setString(9, p.getDogumyeri());
            ps.setString(10, p.getOgrenim());
            ps.setString(11, p.getUniversite());
            ps.setString(12, p.getCep());
            ps.setString(13, p.getIs());
            ps.setString(14, p.getEv());
            ps.setString(15, p.getAdres());
            ps.setBoolean(16, p.isYonetici());
            ps.executeUpdate();
            System.out.println("personel eklendi");
            ps.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally{
            baglantiKes();
        }
        
        return false;
    }
    public boolean deletePersonel(Personel p){
        try {
            baglan();
            PreparedStatement ps=baglanti.prepareStatement("delete from personel where personel_id = ?");
            ps.setInt(1, p.getId());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return false;
    }
    public boolean updatePersonel(Personel original, Personel changed){
        try {
            deletePersonel(original);
            addPersonel(changed);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public boolean updateKasa(int kasa_id, int personel_id){
        try {
            baglan();
            PreparedStatement ps=baglanti.prepareStatement("update personel set kasa_id = ? where personel_id = ?");
            ps.setInt(1, kasa_id);
            ps.setInt(2, personel_id);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return false;
    }
}
