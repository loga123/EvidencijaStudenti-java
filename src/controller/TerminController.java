/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import javax.swing.JOptionPane;
import model.Kolegij;
import model.Termin;

/**
 *
 * @author Admin
 */
public class TerminController {
    
        private String odabranaVrstaPredavanja;

        //Dohvacanje temrina iz baze po ID-u kolegija
        public static Collection dohvatiTermineKolegija(int id) throws Exception{

           ArrayList<Termin> lista = new ArrayList<>();
            try{
                Connection uspostaviKonekciju = SQLController.kreirajKonekciju();

                String sql =("SELECT * FROM termin WHERE sifra_kolegija=? ORDER BY datum;");

                PreparedStatement stmt = uspostaviKonekciju.prepareStatement(sql);
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()){

                    Date datum= rs.getDate("datum");
                    int sifra= rs.getInt("sifra_termina");
                    int sifraKolegija= rs.getInt("sifra_kolegija");
                    String vrijemePocetka= rs.getString("vrijeme_pocetka");
                    String vrijemeKraja= rs.getString("vrijeme_kraja");
                    lista.add(new Termin(sifra,datum,sifraKolegija,vrijemePocetka,vrijemeKraja));

                }
            }catch(SQLException greska){
                System.out.println("DEBUG|TerminController|dohvatiTermineKolegija|Exception|SQL|" + greska.toString());
                JOptionPane.showMessageDialog(null,"SQL greška"+greska,"Greška kod rada s bazom",JOptionPane.PLAIN_MESSAGE);

            }

            return lista;
        }
    
        public Collection listaTerminaPoKolegiju(int id) throws Exception{
            return dohvatiTermineKolegija(id);
        }
        
        public String getOdabranaVrstaPredavanja() {
            return odabranaVrstaPredavanja;
        }
        
        public void setOdabranaVrstaPredavanja(String aOdabranaVrstaPredavanja) {
            odabranaVrstaPredavanja = aOdabranaVrstaPredavanja;
        }

        //Spremanje termina u bazu
        static boolean store(int sifraKolegija,Date datum,String vrijemePocetka,String vrijemeKraja)throws Exception {
            try{
                try (Connection uspostaviKonekciju = SQLController.kreirajKonekciju()) {

                    String provjeraPostojanostiTermina ="SELECT COUNT(*) FROM termin WHERE datum=? AND sifra_kolegija=?;";

                    String sql = "INSERT INTO termin(datum,sifra_kolegija,created_at,updated_at,vrijeme_pocetka,vrijeme_kraja) VALUES " + "(?,?,?,?,?,?);";


                    Calendar calendar = Calendar.getInstance();
                    java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
                    

                   
                     PreparedStatement stmt1 = uspostaviKonekciju.prepareStatement(provjeraPostojanostiTermina);
                     stmt1.setDate(1, (java.sql.Date) datum);
                     stmt1.setInt(2,sifraKolegija);
                     ResultSet rs = stmt1.executeQuery();
                     rs.first();

                     int postojanost = rs.getInt("COUNT(*)");

                     if (postojanost == 1) {
                         JOptionPane.showMessageDialog(null,"Odabrani termin već postoji za taj kolegij","Obavijest",JOptionPane.WARNING_MESSAGE);
                         System.out.println("DEBUG|TerminCOntroller|store|provjeraPostojanostiTermina|TERMIN POSTOJI");
                         return  false;
                    }

                    PreparedStatement stmt = uspostaviKonekciju.prepareStatement(sql);
                    stmt.setDate(1, (java.sql.Date) datum);
                    stmt.setInt(2,sifraKolegija);
                    stmt.setTimestamp(3,ourJavaTimestampObject);
                    stmt.setTimestamp(4,ourJavaTimestampObject);
                    stmt.setString(5,vrijemePocetka);
                    stmt.setString(6,vrijemeKraja);
                    stmt.executeUpdate(); 
                    stmt.close();

                    JOptionPane.showMessageDialog(null,"Unos datuma: "+datum+" je uspješan.","Obavijest",JOptionPane.PLAIN_MESSAGE);
                    System.out.println("DEBUG|TerminCOntroller|store|Terminspremljen");
                }catch(SQLException e){
                     System.out.println("DEBUG|TerminCOntroller|store|GreskaSQL"+e.toString());
                    JOptionPane.showMessageDialog(null,"SQL greška\n"+e.toString(),"Greška kod rada s bazom podataka",JOptionPane.PLAIN_MESSAGE);
                    return false;
                }

                }catch(Exception greska){
                    System.out.println("DEBUG|TerminCOntroller|store|Greska"+greska.toString());
                    JOptionPane.showMessageDialog(null,"Greška\n"+greska,"GREŠKA!!!",JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            return true;
        }

        public boolean storeTermin(int sifraKolegija,Date datum,String vrijemePocetka,String vrijemeKraja) throws Exception{
            boolean spremljeno = store(sifraKolegija,datum,vrijemePocetka,vrijemeKraja);
            return spremljeno; 
        }
    
        //Brisanje termina iz baze
         static boolean destroy(int sifra_termina) throws Exception {
       
            try (Connection uspostaviKonekciju = SQLController.kreirajKonekciju()) {
                
                
                String provjeraKoristenjaFK ="SELECT COUNT(*)FROM evidencija LEFT OUTER JOIN termin ON termin.sifra_termina=evidencija.sifra_termina\n"
                        + " WHERE evidencija.sifra_termina=?;";
                

                String sql = "DELETE FROM termin WHERE sifra_termina=?;";
                System.out.println("DEBUG|TerminController|brisanjeTermina|SQL|" + sql);
                
                PreparedStatement stmt1 = uspostaviKonekciju.prepareStatement(provjeraKoristenjaFK);
                stmt1.setInt(1,sifra_termina);
                 ResultSet rs = stmt1.executeQuery();
                 rs.first();
            
                 int postojanost = rs.getInt("COUNT(*)");
            
                 if (postojanost != 0) {
                    System.out.println("DEBUG|TerminController|destroy|ProvjeraKoristenjaFK");

                     JOptionPane.showMessageDialog(null,"Termin ne možete obrisat.\nStudenti su prijavljeni za prisutnost za taj termin!!\n","Obavijest",JOptionPane.WARNING_MESSAGE);
                     return  false;
                 
                 }else if(JOptionPane.showConfirmDialog(null,"Da li ste sigurni da želite obrisat termin: ","Upozorenje!!!", JOptionPane.YES_NO_OPTION)==0){
            
                    PreparedStatement stmt = uspostaviKonekciju.prepareStatement(sql);
                    stmt.setInt(1,sifra_termina);
                    stmt.executeUpdate();
                    stmt.close();
                    System.out.println("DEBUG|TerminController|destroy|TERMIN OBRISAN!!!!!!!");
                    //JOptionPane.showMessageDialog(null,"Brisanje kolegija: "+kolegij.getNaziv()+" je uspješno.","Obavjest",JOptionPane.PLAIN_MESSAGE);
                 }
            }catch(SQLException sql){
                JOptionPane.showMessageDialog(null,"Greska\n"+sql.toString(), "Greška kod brisanja termina", JOptionPane.ERROR_MESSAGE);
                System.out.println("DEBUG|TerminController|brisanjeTermina|Exception|SQL" + sql.toString());
                return false;
            }catch(Exception greska){
                JOptionPane.showMessageDialog(null,"Greška\n"+greska.toString(),"GREŠKA!!!",JOptionPane.ERROR_MESSAGE);
                System.out.println("DEBUG|TerminController|brisanjeTermina|Exception|" + greska.toString());
                return false;
            }
        return true;
    }
    
        public boolean destrojTermin(int sifra_termina) throws Exception{
            boolean obrisano = destroy(sifra_termina);
            return obrisano;
        }
}
