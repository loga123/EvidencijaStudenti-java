/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Admin
 */
public class StudentNaKolegiju {
    
    int sifra_studenta_na_kolegiju;
    int sifra_kolegija;
    int sifra_korisnika;

    public int getSifra_studenta_na_kolegiju() {
        return sifra_studenta_na_kolegiju;
    }

    public int getSifra_kolegija() {
        return sifra_kolegija;
    }

    public int getSifra_korisnika() {
        return sifra_korisnika;
    }

    public StudentNaKolegiju(int sifra_studenta_na_kolegiju, int sifra_kolegija, int sifra_korisnika) {
        this.sifra_studenta_na_kolegiju = sifra_studenta_na_kolegiju;
        this.sifra_kolegija = sifra_kolegija;
        this.sifra_korisnika = sifra_korisnika;
    }
    
    
    
}
