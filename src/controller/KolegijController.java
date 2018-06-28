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
import javax.swing.JOptionPane;
import model.Kolegij;

/**
 *
 * @author Admin
 */
public class KolegijController {
    
   
       //Spremanje kolegija u bazu
    static boolean store(Kolegij kolegij)throws Exception {
        try{
            try (Connection uspostaviKonekciju = SQLController.kreirajKonekciju()) {
                
                String provjeraNaziva ="SELECT COUNT(*) FROM kolegij WHERE naziv=?;";
                String sql = "INSERT INTO kolegij(naziv,created_at,updated_at) VALUES " + "(?,?,?);";
                
                
                Calendar calendar = Calendar.getInstance();
                java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
    
                 PreparedStatement stmt1 = uspostaviKonekciju.prepareStatement(provjeraNaziva);
               
                 stmt1.setString(1,kolegij.getNaziv());
                         
                 ResultSet rs = stmt1.executeQuery();
                 rs.first();
            
                 int postojanost = rs.getInt("COUNT(*)");
            
                 if (postojanost == 1) {
                     JOptionPane.showMessageDialog(null,"Postoji kolegij s tim nazivom","Obavijest",JOptionPane.WARNING_MESSAGE);
                     System.out.println("DEBUG|KolegijController|store|PostojanjeKolegijaSaZeljenimNazivom");
                     return  false;
                }
                
                PreparedStatement stmt = uspostaviKonekciju.prepareStatement(sql);
                stmt.setString(1,kolegij.getNaziv());
                stmt.setTimestamp(2,ourJavaTimestampObject);
                stmt.setTimestamp(3,ourJavaTimestampObject);
                stmt.executeUpdate(); 
                stmt.close();
                
                JOptionPane.showMessageDialog(null,"Unos kolegija: "+kolegij.getNaziv()+" je uspješan.","Obavijest",JOptionPane.PLAIN_MESSAGE);
                System.out.println("DEBUG|KolegijController|store|Kolegijspremljen");
            }catch(SQLException e){
                 System.out.println("DEBUG|KolegijController|store|GreskaSQL"+e);
                //JOptionPane.showMessageDialog(null,"SQL greška\n"+e,"Greška kod rada s bazom podataka",JOptionPane.PLAIN_MESSAGE);
                return false;
            }
                
            }catch(Exception greska){
                System.out.println("DEBUG|KolegijController|store|Greska"+greska);
                //JOptionPane.showMessageDialog(null,"Greška\n"+greska,"GREŠKA!!!",JOptionPane.ERROR_MESSAGE);
                return false;
            }
        return true;
    }
    
    public boolean storeKolegij(String naziv) throws Exception{
        Kolegij kolegij = new Kolegij(naziv);
        boolean spremljeno = store(kolegij);
        return spremljeno; 
    }
    
    //ažuriranje i izmjena kolegija
    static boolean update(Kolegij kolegij, String noviNaziv) throws Exception {
        try{
                try (Connection uspostaviKonekciju = SQLController.kreirajKonekciju()) {
                    
                    String provjeraNaziva ="SELECT COUNT(*) FROM kolegij WHERE naziv=?;";
                    System.out.println("DEBUG|KolegijController|provjeraNaziva|SQL|" + provjeraNaziva);
                    
                    String provjeraKoristenjaFKStudentNaKolegiju ="SELECT COUNT(*)FROM student_na_kolegiju LEFT OUTER JOIN kolegij ON kolegij.sifra_kolegija=student_na_kolegiju.sifra_kolegija WHERE student_na_kolegiju.sifra_kolegija=?;";
                    System.out.println("DEBUG|KolegijController|update|provjeraKoristenja|SQL|" + provjeraKoristenjaFKStudentNaKolegiju);
                    
                    String sql = "UPDATE kolegij SET naziv=?, updated_at=? WHERE sifra_kolegija = ?;";
                    System.out.println("DEBUG|KolegijController|update|SQL|" + sql);
                    
                    PreparedStatement stmt2 = uspostaviKonekciju.prepareStatement(provjeraKoristenjaFKStudentNaKolegiju);
                    stmt2.setInt(1,kolegij.getSifra_kolegija());
                    
                    ResultSet rs1 = stmt2.executeQuery();
                    rs1.first();
            
                    int koristenjeKolegija = rs1.getInt("COUNT(*)");

                    PreparedStatement stmt1 = uspostaviKonekciju.prepareStatement(provjeraNaziva);
                    stmt1.setString(1,noviNaziv);

                     ResultSet rs = stmt1.executeQuery();
                     rs.first();

                     int postojanostNaziva = rs.getInt("COUNT(*)");
                     
                    if (koristenjeKolegija !=0) {
                        JOptionPane.showMessageDialog(null,"Ne možete ažurirat kolegij\nStudenti pohađaju taj kolegij\n","Obavijest",JOptionPane.WARNING_MESSAGE);
                        System.out.println("DEBUG|KolegijController|update|PostojanjeKolegijaSaZeljenimNazivom");
                        return  false;
                        
                    }else if (postojanostNaziva != 0) {
                         JOptionPane.showMessageDialog(null,"Kolegij ne možete ažurirat.\nPostoji kolegij s tim nazivom","Obavijest",JOptionPane.WARNING_MESSAGE);
                         System.out.println("DEBUG|KolegijController|update|NemogucnostAzuriranja|");
                         return  false;

                     }else if(JOptionPane.showConfirmDialog(null,"Da li ste sigurni da želite ažurirat kolegij: "+kolegij.getNaziv(),"Upozorenje!!!", JOptionPane.YES_NO_OPTION)==0){
                        Calendar calendar = Calendar.getInstance();
                        java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
                         
                        PreparedStatement stmt = uspostaviKonekciju.prepareStatement(sql);
                        stmt.setString(1,noviNaziv);
                        stmt.setTimestamp(2,ourJavaTimestampObject);
                        stmt.setInt(3,kolegij.getSifra_kolegija());
                        stmt.executeUpdate();

                        // JOptionPane.showMessageDialog(null,"Kolegij je ažuriran.","Obavijest",JOptionPane.PLAIN_MESSAGE);
                        System.out.println("DEBUG|KolegijController|update|Kolegij ažuriran");
                        }
                }catch(SQLException e){
                    System.out.println("DEBUG|KolegijController|update|GreskaSQL"+e);
                    //JOptionPane.showMessageDialog(null,"SQL greška\n"+e,"Greška kod spremanja u bazu",JOptionPane.PLAIN_MESSAGE);
                    return false;
                }
                
            }catch(Exception greska){
                System.out.println("DEBUG|KolegijController|update|Greska"+greska);
                JOptionPane.showMessageDialog(null,"Greška\n"+greska,"GREŠKA!!!",JOptionPane.ERROR_MESSAGE);
                return false;
            }
        return true;
    }
    
    public boolean updateKolegij(Kolegij kolegij, String noviNaziv) throws Exception{
    boolean azurirano=update(kolegij,noviNaziv);
    return azurirano;
    }
    
    
    //Brisanje kolegija iz baze
    static boolean destroy(Kolegij kolegij) throws Exception {
       
            try (Connection uspostaviKonekciju = SQLController.kreirajKonekciju()) {
                
                
                String provjeraKoristenjaFKStudentNaKolegiju ="SELECT COUNT(*)FROM student_na_kolegiju LEFT OUTER JOIN kolegij ON kolegij.sifra_kolegija=student_na_kolegiju.sifra_kolegija WHERE student_na_kolegiju.sifra_kolegija=?;";
                System.out.println("DEBUG|KolegijController|provjeraKoristenjaKolegija|SQL|" + provjeraKoristenjaFKStudentNaKolegiju);
                
                String sql = "DELETE FROM kolegij WHERE(sifra_kolegija) = " + "(?);";
                System.out.println("DEBUG|KolegijController|brisanjeKolegija|SQL|" + sql);
                
                PreparedStatement stmt1 = uspostaviKonekciju.prepareStatement(provjeraKoristenjaFKStudentNaKolegiju);
                stmt1.setInt(1,kolegij.getSifra_kolegija());
                 
                 ResultSet rs = stmt1.executeQuery();
                 rs.first();
            
                 int postojanost = rs.getInt("COUNT(*)");
            
                 if (postojanost != 0) {
                    System.out.println("DEBUG|KolegijController|destroy|ProvjeraKoristenja");

                     JOptionPane.showMessageDialog(null,"Kolegij ne možete obrisat.\nStudenti pohađaju taj kolegij\n","Obavijest",JOptionPane.WARNING_MESSAGE);
                     return  false;
                 
                 }else if(JOptionPane.showConfirmDialog(null,"Da li ste sigurni da želite obrisat kolegij: "+kolegij.getNaziv(),"Upozorenje!!!", JOptionPane.YES_NO_OPTION)==0){
            
                    PreparedStatement stmt = uspostaviKonekciju.prepareStatement(sql);
                    stmt.setInt(1,kolegij.getSifra_kolegija());
                    stmt.executeUpdate();
                    stmt.close();
                    System.out.println("DEBUG|KolegijController|destroy|KOLEGIJ "+kolegij.getNaziv()+" OBRISAN!!!!!!!");
                    //JOptionPane.showMessageDialog(null,"Brisanje kolegija: "+kolegij.getNaziv()+" je uspješno.","Obavjest",JOptionPane.PLAIN_MESSAGE);
                 }
            }catch(SQLException sql){
                JOptionPane.showMessageDialog(null,"Greska\n"+sql, "Greška kod brisanja kolegija", JOptionPane.ERROR_MESSAGE);
                System.out.println("DEBUG|KolegijController|brisanjeKolegija|Exception|" + sql.toString());
                return false;
            }catch(Exception greska){
                JOptionPane.showMessageDialog(null,"Greška\n"+greska,"GREŠKA!!!",JOptionPane.ERROR_MESSAGE);
                System.out.println("DEBUG|KolegijController|brisanjeKolegija|Exception|" + greska.toString());
                return false;
            }
        return true;
    }
    
    public boolean destrojKolegij(Kolegij kolegij) throws Exception{
    boolean obrisano = destroy(kolegij);
    return obrisano;
    }
    
    //Dohvacanje kolegija iz baze
    public static Collection dohvatiKolegije() throws Exception{

       ArrayList<Kolegij> lista = new ArrayList<>();
        try{
            Connection uspostaviKonekciju = SQLController.kreirajKonekciju();
            
            String sql =("SELECT * FROM kolegij ORDER BY kolegij.naziv;");

            PreparedStatement stmt = uspostaviKonekciju.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()){
 
                String naziv= rs.getString("naziv");
                int sifra= rs.getInt("sifra_kolegija");
                 lista.add(new Kolegij(sifra,naziv));
               
            }
        }catch(SQLException greska){
            JOptionPane.showMessageDialog(null,"SQL greška","Greška kod rada s bazom",JOptionPane.PLAIN_MESSAGE);
            
        }
        
        return lista;
    }
    
    public Collection listaKolegija() throws Exception{
    return dohvatiKolegije();
    }
    
    //Dohvacanje kolegija iz baze
    public static Collection dohvatiKolegijeBezNositelja() throws Exception{

       ArrayList<Kolegij> lista = new ArrayList<>();
        try{
            Connection uspostaviKonekciju = SQLController.kreirajKonekciju();
            
            
            String sql =("SELECT * FROM kolegij where sifra_profesora IS NULL ORDER BY kolegij.naziv;");

            PreparedStatement stmt = uspostaviKonekciju.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()){
 
                String naziv= rs.getString("naziv");
                int sifra= rs.getInt("sifra_kolegija");
                 lista.add(new Kolegij(sifra,naziv));
               
            }
        }catch(SQLException greska){
            JOptionPane.showMessageDialog(null,"SQL greška","Greška kod rada s bazom",JOptionPane.PLAIN_MESSAGE);
            
        }
        
        return lista;
    }
    
     public Collection listaKolegija1() throws Exception{
    return dohvatiKolegijeBezNositelja();
    }
    

    /* public HashMap<String, Integer> populateCombo() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
         
      HashMap<String, Integer> map = new HashMap<String, Integer>();
     
       try {
           Connection uspostaviKonekciju;
            uspostaviKonekciju = SQLController.kreirajKonekciju();
             String sql =("SELECT * FROM kolegij;");

            PreparedStatement stmt = uspostaviKonekciju.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
  
           Kolegij kolegij;
           
     
           while(rs.next()){
               String naziv= rs.getString("naziv");
                int sifra= rs.getInt("sifra_kolegija");
               kolegij = new Kolegij(sifra, naziv);
               map.put(kolegij.getNaziv(),kolegij.getSifra_kolegija());
           }
           
       } catch (SQLException ex) {
           Logger.getLogger(SQLController.class.getName()).log(Level.SEVERE, null, ex);
       }
      
       return map;
   }*/
    
        //Dohvacanje kolegija iz baze
    public static Collection dohvatiKolegijeOdProfesora(int sifra) throws Exception{

       ArrayList<Kolegij> lista = new ArrayList<>();
        try{
            Connection uspostaviKonekciju = SQLController.kreirajKonekciju();
            
            String sql ="SELECT * FROM kolegij\n"
                           + "LEFT OUTER JOIN users ON kolegij.sifra_profesora=users.sifra_korisnika\n"
                           + "WHERE kolegij.sifra_profesora=?\n"
                           + "ORDER BY kolegij.naziv;";
            

            PreparedStatement stmt = uspostaviKonekciju.prepareStatement(sql);
            stmt.setInt(1, sifra);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()){
 
                String naziv= rs.getString("naziv");
                int sifraKolegija= rs.getInt("sifra_kolegija");
                 lista.add(new Kolegij(sifraKolegija,naziv));
               
            }
        }catch(SQLException greska){
            JOptionPane.showMessageDialog(null,"SQL greška","Greška kod rada s bazom",JOptionPane.PLAIN_MESSAGE);
            
        }
        
        return lista;
    }
    
    public Collection listaKolegijaProfesora(int sifra) throws Exception{
    return dohvatiKolegijeOdProfesora(sifra);
    }
        

   
    
}
