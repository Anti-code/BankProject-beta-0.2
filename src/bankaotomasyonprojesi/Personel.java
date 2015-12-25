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
public class Personel {
    private int id,guvenlik_no,tc,kasa_id,table_index;
    private String ad, soyad, anne, baba, dogumyeri, cep, adres,
            ogrenim=null, universite=null, ev=null, is=null,sifre=null;
    private boolean yonetici;
    private Date dogum_tarihi;
    public Personel(
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
                boolean yonetici,
                int tc) {
        this.guvenlik_no = guvenlik_no;
        this.ad = ad;
        this.soyad = soyad;
        this.anne = anne;
        this.baba = baba;
        this.dogum_tarihi=dogum_tarihi;
        this.dogumyeri = dogumyeri;
        this.cep = cep;
        this.adres = adres;
        this.ogrenim = ogrenim;
        this.universite = universite;
        this.ev = ev;
        this.is = is;
        this.yonetici=yonetici;
        this.tc=tc;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
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

    public Date getDogum_tarihi() {
        return dogum_tarihi;
    }

    public void setDogum_tarihi(Date dogum_tarihi) {
        this.dogum_tarihi = dogum_tarihi;
    }

    public int getTc() {
        return tc;
    }

    public void setTc(int tc) {
        this.tc = tc;
    }

    public int getKasa_id() {
        return kasa_id;
    }

    public void setKasa_id(int kasa_id) {
        this.kasa_id = kasa_id;
    }

    public int getTable_index() {
        return table_index;
    }

    public void setTable_index(int table_index) {
        this.table_index = table_index;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public boolean isYonetici() {
        return yonetici;
    }

    public void setYonetici(boolean yonetici) {
        this.yonetici = yonetici;
    }

   
    
}
