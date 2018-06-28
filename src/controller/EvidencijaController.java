/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import javax.swing.JOptionPane;
import model.Evidencija;
import model.StudentNaKolegiju;

/**
 *
 * @author Admin
 */
public class EvidencijaController {
    
     static boolean spremanjeEvidencije(Evidencija zapis) {

     try{
            try (Connection uspostaviKonekciju = SQLController.kreirajKonekciju()) {
                
                String sql = "INSERT INTO evidencija(sifra_studenta_na_kolegiju,datum_evidentiranja,prisutnost,vrsta_predavanja,sifra_termina,created_at,updated_at) VALUES " + "(?,?,?,?,?,?,?);";
                
                
                Calendar calendar = Calendar.getInstance();
                java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
                java.util.Date date = new java.util.Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime()); 
                
                
                PreparedStatement stmt = uspostaviKonekciju.prepareStatement(sql);
                stmt.setInt(1,zapis.getSifra_studenta_na_kolegiju());
                stmt.setDate(2, sqlDate);
                stmt.setInt(3,zapis.getPrisutnost());
                stmt.setString(4,zapis.getVrsta_predavanja());
                stmt.setInt(5,zapis.getSifra_termina());
                stmt.setTimestamp(6,ourJavaTimestampObject);
                stmt.setTimestamp(7,ourJavaTimestampObject);
                stmt.executeUpdate(); 
                stmt.close();
                
                //JOptionPane.showMessageDialog(null,"Evidencija zabiljezena: ","Obavijest",JOptionPane.PLAIN_MESSAGE);
                System.out.println("DEBUG|EvidencijaController|spremanjeEvidencije|Evidencija|SPREMLJEN");
            }catch(SQLException e){
                 System.out.println("DEBUG|EvidencijaController|spremanjeEvidencije|GreskaSQL"+e.toString());
                //JOptionPane.showMessageDialog(null,"SQL greška\n"+e,"Greška kod rada s bazom podataka",JOptionPane.PLAIN_MESSAGE);
                return false;
            }
                
            }catch(Exception greska){
                System.out.println("DEBUG|EvidencijaController|spremanjeEvidencije|Greska"+greska.toString());
                //JOptionPane.showMessageDialog(null,"Greška\n"+greska,"GREŠKA!!!",JOptionPane.ERROR_MESSAGE);
                return false;
            }
        return true;

     }
     
     public boolean storeEvidencija(Evidencija zapis) throws Exception{
        boolean spremljeno = spremanjeEvidencije(zapis);
        return spremljeno; 
    }
     
     //AKO JE STUDENT ZABILJEZEN U EVIDENCIJI DOHVATI GA
        static Evidencija dohvatiEvidencijuStudenta(String brojIskaznice,int sifraKolegija,int sifraTermina) throws SQLException {
            Evidencija dohvaceni ;

                try (Connection uspostaviKonekciju = SQLController.kreirajKonekciju()) {

                   String dohvatiStuNaKol ="SELECT evidencija.* FROM evidencija\n"
                           + "LEFT OUTER JOIN student_na_kolegiju ON student_na_kolegiju.sifra_studenta_na_kolegiju =evidencija.sifra_studenta_na_kolegiju\n"
                           + "LEFT OUTER JOIN users ON student_na_kolegiju.sifra_korisnika=users.sifra_korisnika\n"
                           + "WHERE users.broj_iskaznice=?\n"
                           + "AND student_na_kolegiju.sifra_kolegija=?\n"
                           + "AND evidencija.sifra_termina=?;";
                   
                   PreparedStatement stmtEvidencija = uspostaviKonekciju.prepareStatement(dohvatiStuNaKol);
                   stmtEvidencija.setString(1, brojIskaznice);
                   stmtEvidencija.setInt(2, sifraKolegija);
                   stmtEvidencija.setInt(3, sifraTermina);
                   ResultSet rs = stmtEvidencija.executeQuery();
                   rs.first();
                   
                   int sifra_evidencije =rs.getInt("sifra_evidencije");
                    int sifra_studenta_na_kolegiju = rs.getInt("sifra_studenta_na_kolegiju");
                    String vrsta_predavanja =rs.getString("vrsta_predavanja");
                    int sifra_termina = rs.getInt("sifra_termina");
                    int prisutnost = rs.getInt("prisutnost");

                    dohvaceni = new Evidencija(sifra_evidencije,sifra_studenta_na_kolegiju,vrsta_predavanja,sifra_termina,prisutnost);
                    System.out.println("DEBUG|EvidencijaController|dohvatiEvidencijuStudenta|DOHVACENA| "+dohvaceni);
                    

               }
                return dohvaceni;
    }
        
        public Evidencija dohvatiEvidencijuZaStudenta(String brojIskaznice, int sifraKolegija,int sifraTermina) throws Exception{
            Evidencija aevidencija = dohvatiEvidencijuStudenta(brojIskaznice,sifraKolegija,sifraTermina);
            return aevidencija;
    }
        
        //UPDATE EVIDENCIJE
        static boolean updatePrisutnost(int sifra_evidencije){
        
         try{
                try (Connection uspostaviKonekciju = SQLController.kreirajKonekciju()) {

                 
 
                    String sql = "UPDATE evidencija SET prisutnost=? WHERE sifra_evidencije = ?;";
                    System.out.println("DEBUG|EvidencijaController|updatePrisutnost|SQL|" + sql);
  
                    PreparedStatement stmt = uspostaviKonekciju.prepareStatement(sql);
                    stmt.setInt(1,1);
                    stmt.setInt(2,sifra_evidencije);
                    stmt.executeUpdate();

                    //JOptionPane.showMessageDialog(null,"Evidencija studenta promjenjena.","Obavijest",JOptionPane.PLAIN_MESSAGE);
                    System.out.println("DEBUG|EvidencijaController|updatePrisutnost|Evidencija studenta promjenjena");
                    
                }catch(SQLException e){
                    System.out.println("DEBUG|EvidencijaController|updatePrisutnost|GreskaSQL"+e);
                    //JOptionPane.showMessageDialog(null,"SQL greška\n"+e,"Greška kod spremanja u bazu",JOptionPane.PLAIN_MESSAGE);
                    return false;
                }
                
            }catch(Exception greska){
                System.out.println("DEBUG|EvidencijaController|updatePrisutnost|Greska"+greska);
                JOptionPane.showMessageDialog(null,"Greška\n"+greska,"GREŠKA!!!",JOptionPane.ERROR_MESSAGE);
                return false;
            }
        return true;
        
        }
        
        public boolean azurirajPrisutnost(int sifra_evidencije){
        
            boolean azurirano = updatePrisutnost(sifra_evidencije);
            return azurirano;
        }
}
