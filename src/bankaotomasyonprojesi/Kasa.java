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
public class Kasa {
    private int id, personel_id;
    private float bakiye;
    private boolean durum=true;
    
    public Kasa(int id, float bakiye) {
        this.id = id;
        this.bakiye = bakiye;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getBakiye() {
        return bakiye;
    }

    public void setBakiye(float bakiye) {
        this.bakiye = bakiye;
    }

    public boolean isDurum() {
        return durum;
    }

    public void setDurum(boolean durum) {
        this.durum = durum;
    }

    public int getPersonel_id() {
        return personel_id;
    }

    public void setPersonel_id(int personel_id) {
        this.personel_id = personel_id;
    }


    
}
