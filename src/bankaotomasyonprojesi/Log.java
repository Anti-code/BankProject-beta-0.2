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
public class Log {
    private int personel_id,musteri_no=-1,hesap_no=-1,kasa_id=-1;
    private float miktar=0;
    private boolean statu=false;
    private String olay;

    public int getMusteri_no() {
        return musteri_no;
    }

    public void setMusteri_no(int musteri_no) {
        this.musteri_no = musteri_no;
    }

    public int getHesap_no() {
        return hesap_no;
    }

    public void setHesap_no(int hesap_no) {
        this.hesap_no = hesap_no;
    }

    public int getKasa_id() {
        return kasa_id;
    }

    public void setKasa_id(int kasa_id) {
        this.kasa_id = kasa_id;
    }

    public float getMiktar() {
        return miktar;
    }

    public void setMiktar(float miktar) {
        this.miktar = miktar;
    }

    public String getOlay() {
        return olay;
    }

    public void setOlay(String olay) {
        this.olay = olay;
    }

    public boolean isStatu() {
        return statu;
    }

    public void setStatu(boolean statu) {
        this.statu = statu;
    }

    public int getPersonel_id() {
        return personel_id;
    }

    public void setPersonel_id(int personel_id) {
        this.personel_id = personel_id;
    }
    
    
}
