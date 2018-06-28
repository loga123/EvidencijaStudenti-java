/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class Termin {
    
    int sifra_termina;
    Date datum;
    int sifra_kolegija;
    String vrijemeOd;
    String vrijemeDo;

 
    public Termin(int sifra_termina, Date datum, int sifra_kolegija) {
        this.sifra_termina = sifra_termina;
        this.datum = datum;
        this.sifra_kolegija = sifra_kolegija;
    }

    public Termin(int sifra_termina, Date datum, int sifra_kolegija, String vrijemeOd, String vrijemeDo) {
        this.sifra_termina = sifra_termina;
        this.datum = datum;
        this.sifra_kolegija = sifra_kolegija;
        this.vrijemeOd = vrijemeOd;
        this.vrijemeDo = vrijemeDo;
    }

    public int getSifra_termina() {
        return sifra_termina;
    }

    public String getVrijemeOd() {
        return vrijemeOd;
    }

    public String getVrijemeDo() {
        return vrijemeDo;
    }

    public Date getDatum() {
        return datum;
    }

    public int getSifra_kolegija() {
        return sifra_kolegija;
    }

    @Override
    public String toString() {
        return "" + datum;
    }
    
    
    
    
    
}
