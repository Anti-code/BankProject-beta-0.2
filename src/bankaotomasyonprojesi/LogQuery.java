/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankaotomasyonprojesi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Mehmet
 */
public class LogQuery extends DBConnection{
    public boolean addLog(Log l){
        try {
            System.out.println("dene");
            System.out.println(l.isStatu()+" "+l.getPersonel_id()+" "+l.getMusteri_no()+" "+l.getHesap_no()+" "+l.getKasa_id()+" "+l.getMiktar()+" "+l.getOlay());
            baglan();
            PreparedStatement ps=baglanti.prepareStatement("INSERT INTO `log`( `statu`, `personel_id`, `musteri_no`, `hesap_no`, `kasa_id`, `miktar`, `olay`, `tarih`) VALUES (?,?,?,?,?,?,?,current_timestamp())");
            ps.setBoolean(1, l.isStatu());
            ps.setInt(2, l.getPersonel_id());
            ps.setInt(3, l.getMusteri_no());
            ps.setInt(4, l.getHesap_no());
            ps.setInt(5, l.getKasa_id());
            ps.setFloat(6, l.getMiktar());
            ps.setString(7, l.getOlay());
            
            System.out.println("Eklendi");
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally{
            baglantiKes();
        }
        return false;
    }
}
