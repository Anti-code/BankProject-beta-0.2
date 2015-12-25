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
public class Hesap {
    private int no,musteri_no,vade_suresi=0;
    private float bakiye, vade_tutari=0, faiz=0;
    private String tur, doviz_cinsi=null, durum, vade_tipi=null;
    private Date vade_basi=null, vade_sonu=null;

    public Hesap(int no, String tur, int musteri_no, float bakiye, float vade_tutari, float faiz, String doviz_cinsi, String durum,
            Date vade_basi,Date vade_sonu, String vade_tipi, int vade_suresi) {
        this.no = no;
        this.tur = tur;
        this.musteri_no = musteri_no;
        this.bakiye = bakiye;
        this.vade_tutari = vade_tutari;
        this.faiz = faiz;
        this.doviz_cinsi = doviz_cinsi;
        this.durum = durum;
        this.vade_basi=vade_basi;
        this.vade_sonu=vade_sonu;
        this.vade_tipi = vade_tipi;
        this.vade_suresi=vade_suresi;
    }
    public Hesap(int no, String tur, int musteri_no, float bakiye, String doviz_cinsi, String durum) {
        this.no = no;
        this.tur = tur;
        this.musteri_no = musteri_no;
        this.bakiye = bakiye;
        this.doviz_cinsi = doviz_cinsi;
        this.durum = durum;
    }
    

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public float getBakiye() {
        return bakiye;
    }

    public void setBakiye(float bakiye) {
        this.bakiye = bakiye;
    }

    public float getVade_tutari() {
        return vade_tutari;
    }

    public void setVade_tutari(float vade_tutari) {
        this.vade_tutari = vade_tutari;
    }

    public float getFaiz() {
        return faiz;
    }

    public void setFaiz(float faiz) {
        this.faiz = faiz;
    }

    public String getDoviz_cinsi() {
        return doviz_cinsi;
    }

    public void setDoviz_cinsi(String doviz_cinsi) {
        this.doviz_cinsi = doviz_cinsi;
    }

    public String getDurum() {
        return durum;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }

    public String getVade_tipi() {
        return vade_tipi;
    }

    public void setVade_tipi(String vade_tipi) {
        this.vade_tipi = vade_tipi;
    }

    public String getTur() {
        return tur;
    }

    public void setTur(String tur) {
        this.tur = tur;
    }

    public int getMusteri_no() {
        return musteri_no;
    }

    public void setMusteri_tc(int musteri_no) {
        this.musteri_no = musteri_no;
    }

    public Date getVade_basi() {
        return vade_basi;
    }

    public void setVade_basi(Date vade_basi) {
        this.vade_basi = vade_basi;
    }

    public Date getVade_sonu() {
        return vade_sonu;
    }

    public void setVade_sonu(Date vade_sonu) {
        this.vade_sonu = vade_sonu;
    }

    public int getVade_suresi() {
        return vade_suresi;
    }

    public void setVade_suresi(int vade_suresi) {
        this.vade_suresi = vade_suresi;
    }
    
    
}
