/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankaotomasyonprojesi;

import java.sql.Date;


/**
 *
 * @author Mehmet
 */
public class AnaMenuIslem {
    private int musteri_no,hesap_no;
    private String olay;
    private Date tarih;
    private float bakiye;

    public AnaMenuIslem(int musteri_no, int hesap_no, String olay, Date islem_tarihi, float bakiye) {
        this.musteri_no = musteri_no;
        this.hesap_no = hesap_no;
        this.olay = olay;
        this.tarih = tarih;
        this.bakiye = bakiye;
    }

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

    public float getBakiye() {
        return bakiye;
    }

    public void setBakiye(float bakiye) {
        this.bakiye = bakiye;
    }

    public String getOlay() {
        return olay;
    }

    public void setOlay(String olay) {
        this.olay = olay;
    }

    public Date getTarih() {
        return tarih;
    }

    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }
   
}
