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
public class Kolegij {
    
    int sifra_kolegija;
    String naziv;
    int sifra_profesora;

    public Kolegij(int sifra_kolegija, String naziv) {
        this.sifra_kolegija = sifra_kolegija;
        this.naziv = naziv;
    }
    
       public Kolegij(String naziv) {
        this.naziv = naziv;
    }
    

    public int getSifra_kolegija() {
        return sifra_kolegija;
    }

    public String getNaziv() {
        return naziv;
    }

    public int getSifra_profesora() {
        return sifra_profesora;
    }

 
    @Override
    public String toString() {
        return naziv;
    }
    
    
    
}
