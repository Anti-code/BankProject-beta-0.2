/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankaotomasyonprojesi;

import java.sql.Date;
import javafx.collections.ObservableList;

/**
 *
 * @author Mehmet
 */
public class Musteri {
    private int musteri_no,tc,guvenlik_no, son_hesap_no;
    private Date dogum_tarihi;
    private String ad, soyad, anne, baba, dogumyeri, son_islem_tarihi, son_yapilan_islem,
            ogrenim=null, universite=null, sektor=null, unvan=null, cep, ev=null, is=null, adres;
    private ObservableList<Hesap> hesapla;
    

    public Musteri( int tc, int guvenlik_no, String ad, String soyad, String anne, String baba, Date dogum_tarihi, String dogumyeri, String ogrenim, String universite, String sektor, String unvan, String cep, String ev, String is, String adres) {
        
        this.tc = tc;
        this.guvenlik_no = guvenlik_no;
        this.ad = ad;
        this.soyad = soyad;
        this.anne = anne;
        this.baba = baba;
        this.dogum_tarihi=dogum_tarihi;
        this.dogumyeri = dogumyeri;
        this.ogrenim = ogrenim;
        this.universite = universite;
        this.sektor = sektor;
        this.unvan = unvan;
        this.cep = cep;
        this.ev = ev;
        this.is = is;
        this.adres = adres;
    }

    public int getTc() {
        return tc;
    }

    public void setTc(int tc) {
        this.tc = tc;
    }

    public int getGuvenlik_no() {
        return guvenlik_no;
    }

    public void setGuvenlik_no(int guvenlik_no) {
        this.guvenlik_no = guvenlik_no;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getAnne() {
        return anne;
    }

    public void setAnne(String anne) {
        this.anne = anne;
    }

    public String getBaba() {
        return baba;
    }

    public void setBaba(String baba) {
        this.baba = baba;
    }

    public String getDogumyeri() {
        return dogumyeri;
    }

    public void setDogumyeri(String dogumyeri) {
        this.dogumyeri = dogumyeri;
    }

    public String getOgrenim() {
        return ogrenim;
    }

    public void setOgrenim(String ogrenim) {
        this.ogrenim = ogrenim;
    }

    public String getUniversite() {
        return universite;
    }

    public void setUniversite(String universite) {
        this.universite = universite;
    }

    public String getSektor() {
        return sektor;
    }

    public void setSektor(String sektor) {
        this.sektor = sektor;
    }

    public String getUnvan() {
        return unvan;
    }

    public void setUnvan(String unvan) {
        this.unvan = unvan;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEv() {
        return ev;
    }

    public void setEv(String ev) {
        this.ev = ev;
    }

    public String getIs() {
        return is;
    }

    public void setIs(String is) {
        this.is = is;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public Date getDogum_tarihi() {
        return dogum_tarihi;
    }

    public void setDogum_tarihi(Date dogum_tarihi) {
        this.dogum_tarihi = dogum_tarihi;
    }

    public int getMusteri_no() {
        return musteri_no;
    }

    public void setMusteri_no(int musteri_no) {
        this.musteri_no = musteri_no;
    }

    public ObservableList<Hesap> getHesapla() {
        return hesapla;
    }

    public void setHesapla(ObservableList<Hesap> hesapla) {
        this.hesapla = hesapla;
    }

    public String getSon_islem_tarihi() {
        return son_islem_tarihi;
    }

    public void setSon_islem_tarihi(String son_islem_tarihi) {
        this.son_islem_tarihi = son_islem_tarihi;
    }

    public String getSon_yapilan_islem() {
        return son_yapilan_islem;
    }

    public void setSon_yapilan_islem(String son_yapilan_islem) {
        this.son_yapilan_islem = son_yapilan_islem;
    }
    
}
