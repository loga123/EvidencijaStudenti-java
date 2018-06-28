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
public class Evidencija {
    
    int sifra_evidencije;
    int sifra_studenta_na_kolegiju;
    String vrsta_predavanja;
    Date datum_evidentiranja;
     int sifra_termina;
     int  prisutnost;

    public Evidencija(int sifra_evidencije, int sifra_studenta_na_kolegiju, String vrsta_predavanja, int sifra_termina, int prisutnost) {
        this.sifra_evidencije = sifra_evidencije;
        this.sifra_studenta_na_kolegiju = sifra_studenta_na_kolegiju;
        this.vrsta_predavanja = vrsta_predavanja;
        this.sifra_termina = sifra_termina;
        this.prisutnost = prisutnost;
    }
     
     

    public Evidencija(int sifra_studenta_na_kolegiju, String vrsta_predavanja, int sifra_termina, int prisutnost) {
        this.sifra_studenta_na_kolegiju = sifra_studenta_na_kolegiju;
        this.vrsta_predavanja = vrsta_predavanja;
        this.sifra_termina = sifra_termina;
        this.prisutnost = prisutnost;
    }
    
   

    public Evidencija(int sifra_studenta_na_kolegiju, String vrsta_predavanja, Date datum_evidentiranja, int prisutnost) {
        this.sifra_studenta_na_kolegiju = sifra_studenta_na_kolegiju;
        this.vrsta_predavanja = vrsta_predavanja;
        this.datum_evidentiranja = datum_evidentiranja;
        this.prisutnost = prisutnost;
    }

    public int getSifra_evidencije() {
        return sifra_evidencije;
    }

    public int getSifra_studenta_na_kolegiju() {
        return sifra_studenta_na_kolegiju;
    }

    public String getVrsta_predavanja() {
        return vrsta_predavanja;
    }

    public Date getDatum_evidentiranja() {
        return datum_evidentiranja;
    }

    public int getSifra_termina() {
        return sifra_termina;
    }

    public int getPrisutnost() {
        return prisutnost;
    }
    
    


    @Override
    public String toString() {
        return ""+ datum_evidentiranja;
    }


    
    
    
}
