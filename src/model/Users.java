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
public class Users {
    
   int sifra_korisnika;
   String broj_iskaznice;
   String ime;
   String prezime;
   String email;
   int razinaPrava;

    public Users(int sifra_korisnika, String broj_iskaznice, String ime, String prezime, String email, int razinaPrava) {
        this.sifra_korisnika = sifra_korisnika;
        this.broj_iskaznice = broj_iskaznice;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.razinaPrava = razinaPrava;
    }

    public Users(int sifra_korisnika, String ime, String prezime, String email, int razinaPrava) {
        this.sifra_korisnika = sifra_korisnika;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.razinaPrava = razinaPrava;
    }

    public Users(int sifra_korisnika,String broj_iskaznice, String ime, String prezime) {
        this.sifra_korisnika=sifra_korisnika;
        this.broj_iskaznice = broj_iskaznice;
        this.ime = ime;
        this.prezime = prezime;
    }

    public Users(int sifra_korisnika, String broj_iskaznice, String ime, String prezime, String email) {
        this.sifra_korisnika = sifra_korisnika;
        this.broj_iskaznice = broj_iskaznice;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
    }
    
    

  

    public int getSifra_korisnika() {
        return sifra_korisnika;
    }

    public String getBroj_iskaznice() {
        return broj_iskaznice;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getEmail() {
        return email;
    }

    public int getRazinaPrava() {
        return razinaPrava;
    }

    public void setSifra_korisnika(int sifra_korisnika) {
        this.sifra_korisnika = sifra_korisnika;
    }

    @Override
    public String toString() {
        return prezime+" "+ime;
    }
   
   
    
    
}
