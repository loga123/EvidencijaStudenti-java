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
import java.util.Base64;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.Kolegij;
import model.StudentNaKolegiju;
import model.Termin;
import model.Users;
import org.mindrot.jbcrypt.BCrypt;
/**
 *
 * @author Admin
 */
public class UsersController {
    
    /**
     *Referenca
     */
    public  Users korisnik ;
    
        public int sifra;
    
        public void setSifra(int sifra){
    this.sifra=sifra;
    }
    
        static boolean provjeraLogin(String username, String password) throws Exception {
            boolean login;
             System.out.println("DEBUG|UsersController|provjeraKorisnickogImenaILozinke|falseILItrue| "+password);
            try (Connection uspostaviKonekciju = SQLController.kreirajKonekciju()) {
            login = false;
            
           String provjeriKorisnika =  ("SELECT COUNT(*) FROM users WHERE email=?;");
            PreparedStatement stmt4 = uspostaviKonekciju.prepareStatement(provjeriKorisnika);
            stmt4.setString(1, username);  
            
            ResultSet rs4 = stmt4.executeQuery();
            rs4.first();
            
             int postojanost4 = rs4.getInt("COUNT(*)");

            if (postojanost4 == 0) {
                System.out.println("Korisnik postojiILINe"+postojanost4);
                //JOptionPane.showMessageDialog(null, "Nepostojeći korisnik \nPokušajte ponovo\n","Autentifikacija", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            String dohvatiLozinkuHash = ("SELECT password FROM users WHERE email=?;");
            PreparedStatement stmtHash = uspostaviKonekciju.prepareStatement(dohvatiLozinkuHash);
            stmtHash.setString(1, username);
            ResultSet rsHash = stmtHash.executeQuery();
            rsHash.first();
            String lozinkaHash= rsHash.getString("password");
            
            String resultLozinka = lozinkaHash.replace("$2y$10$", "$2a$10$");
            
            System.out.println("DEBUG|UsersController|provjeraKorisnickogImenaILozinke|falseILItrue| "+resultLozinka);
            
            if (BCrypt.checkpw(password, resultLozinka)){
            
                String sql =("SELECT COUNT(*) FROM users WHERE email=? AND password=?;");

                //byte[] kodiranalozinka = Base64.getEncoder().encode(password.getBytes());
                //String lozinka = new String(kodiranalozinka);
               // System.out.println(""+lozinka);

                PreparedStatement stmt = uspostaviKonekciju.prepareStatement(sql);
                stmt.setString(1, username);   
                stmt.setString(2, lozinkaHash);

                ResultSet rs = stmt.executeQuery();
                rs.first();

                int postojanost = rs.getInt("COUNT(*)");

                if (postojanost == 1) {
                    System.out.println(postojanost);
                    login = true;
                }

                System.out.println("DEBUG|UsersController|provjeraKorisnickogImenaILozinke|falseILItrue| "+login);
                
            }else System.out.println("DEBUG|UsersController|provjeraLozinke|UsporedbaLozinki|Neuspješno ");
             }
            return login;

        }
            
        public boolean provjeriValjanostpodataka(String username, String password) throws Exception{
        boolean provjera=provjeraLogin(username, password);
        return provjera;
        }
     
        static  Users dohvatiKorisnika(String username, String password) throws Exception {
                  Users pronadjeni;
                  
               try (Connection uspostaviKonekciju = SQLController.kreirajKonekciju()) {
                   
                   String sql = ("SELECT * FROM users WHERE email=?;");
                   PreparedStatement stmt = uspostaviKonekciju.prepareStatement(sql);
                   stmt.setString(1, username);
                   
                   
                   ResultSet rs = stmt.executeQuery();
                   rs.first();
                   
                    int sifra = rs.getInt("sifra_korisnika");
                    String ime = rs.getString("ime");
                    String prezime = rs.getString("prezime");
                    String email = rs.getString("email");
                    int razina = rs.getInt("razina_prava");
            
                    pronadjeni = new Users(sifra, ime, prezime, email, razina);
                    System.out.println("DEBUG|UsersController|dohvatKorisnika|DOHVACEN| "+pronadjeni);
               }
                return pronadjeni;

             }
        
        public Users dohvatiCijelogKorisnika(String username, String password) throws Exception{
        Users akorisnik = dohvatiKorisnika(username,password);
        return akorisnik;
    }

         //Dohvacanje kolegija iz baze po ID-u profesora
        public static Collection dohvatiKolegijeProfesora(int id) throws Exception{

           ArrayList<Kolegij> lista = new ArrayList<>();
            try{
                Connection uspostaviKonekciju = SQLController.kreirajKonekciju();

                String sql =("SELECT * FROM kolegij WHERE sifra_profesora=? ORDER BY naziv;");

                PreparedStatement stmt = uspostaviKonekciju.prepareStatement(sql);
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()){

                    String naziv= rs.getString("naziv");
                    int sifra= rs.getInt("sifra_kolegija");
                     lista.add(new Kolegij(sifra,naziv));

                }
            }catch(SQLException greska){
                System.out.println("DEBUG|UsersController|dohvatiKolegijOdProfesora|Exception|SQL|" + greska.toString());
                JOptionPane.showMessageDialog(null,"SQL greška"+greska,"Greška kod rada s bazom",JOptionPane.PLAIN_MESSAGE);

            }

            return lista;
        }
    
        public Collection listaKolegijaOdProfesora(int id) throws Exception{
            return dohvatiKolegijeProfesora(id);
        }

        static  int provjeraStudentaUEvidenciji(String brojIskaznice,int broj,int termin) throws SQLException {
                  int pronadjeni = 0;
                  
               try (Connection uspostaviKonekciju = SQLController.kreirajKonekciju()) {
                   
                   String provjeraPrijave ="SELECT COUNT(*)FROM evidencija\n"
                           + "LEFT OUTER JOIN student_na_kolegiju ON evidencija.sifra_studenta_na_kolegiju=student_na_kolegiju.sifra_studenta_na_kolegiju\n"
                           + "LEFT OUTER JOIN users ON student_na_kolegiju.sifra_korisnika=users.sifra_korisnika\n"
                           + "WHERE users.broj_iskaznice=?\n"
                           + "AND student_na_kolegiju.sifra_kolegija=?\n"
                           + "AND evidencija.sifra_termina=?;";

                   PreparedStatement stmt = uspostaviKonekciju.prepareStatement(provjeraPrijave);
                   stmt.setString(1, brojIskaznice);
                    stmt.setInt(2, broj);
                   stmt.setInt(3, termin);
                   
                   ResultSet rs = stmt.executeQuery();
                   rs.first();
                   
                   
                    int postojanost = rs.getInt("COUNT(*)");
            
                    if (postojanost == 1) {
                        System.out.println(postojanost);
                        pronadjeni=postojanost;
                        System.out.println("DEBUG|UsersController|dohvatKorisnika|DOHVACEN| "+pronadjeni);
                    }

               }
                return pronadjeni;

             }
        
        public int provjeriEvidencijuZaStudenta(String broj_iskaznice,int broj,int termin) throws Exception{
            int provjeri = provjeraStudentaUEvidenciji(broj_iskaznice,broj,termin);
            return provjeri;
        }
        
        static int provjeriDaLiJeStudentUpisanNaKolegij(String brojIskaznice,int sifraKolegija) throws SQLException {
            int dohvaceniUnos = 0;

                try (Connection uspostaviKonekciju = SQLController.kreirajKonekciju()) {
                   
                   String provjeraStuNaKol ="SELECT COUNT(*)FROM student_na_kolegiju\n"
                           + "LEFT OUTER JOIN users ON student_na_kolegiju.sifra_korisnika=users.sifra_korisnika\n"
                           + "WHERE users.broj_iskaznice=?\n"
                           + "AND student_na_kolegiju.sifra_kolegija=?;";

                   PreparedStatement stmt = uspostaviKonekciju.prepareStatement(provjeraStuNaKol);
                   stmt.setString(1, brojIskaznice);
                   stmt.setInt(2, sifraKolegija);
                   ResultSet rs = stmt.executeQuery();
                   rs.first();

                    int postojanost = rs.getInt("COUNT(*)");
            
                    if (postojanost == 1) {
                        dohvaceniUnos=postojanost;
                        
                        System.out.println("DEBUG|UsersController|provjeriDaLiJeStudentUpisanNaKolegij|DOHVACEN| "+dohvaceniUnos);
                    }

               }
                return dohvaceniUnos;
    }
        
        public int provjeriUpisStudentaNaKolegiju(String broj_iskaznice,int broj) throws Exception{
            int provjeri = provjeriDaLiJeStudentUpisanNaKolegij(broj_iskaznice,broj);
            return provjeri;
        }
        
        static StudentNaKolegiju dohvatiStudentaNaKolegiju(String brojIskaznice,int sifraKolegija) throws SQLException {
            StudentNaKolegiju dohvaceni ;

                try (Connection uspostaviKonekciju = SQLController.kreirajKonekciju()) {

                   String dohvatiStuNaKol ="SELECT * FROM student_na_kolegiju\n"
                           + "LEFT OUTER JOIN users ON student_na_kolegiju.sifra_korisnika=users.sifra_korisnika\n"
                           + "WHERE users.broj_iskaznice=?\n"
                           + "AND student_na_kolegiju.sifra_kolegija=?;";
                   
                   PreparedStatement stmtStuNaKol = uspostaviKonekciju.prepareStatement(dohvatiStuNaKol);
                   stmtStuNaKol.setString(1, brojIskaznice);
                   stmtStuNaKol.setInt(2, sifraKolegija);
                   ResultSet rsStuNaKol = stmtStuNaKol.executeQuery();
                   rsStuNaKol.first();
                   
                    int sifra_studenta_na_kolegiju = rsStuNaKol.getInt("sifra_studenta_na_kolegiju");
                    int sifra_kolegija = rsStuNaKol.getInt("sifra_kolegija");
                    int sifra_korisnika = rsStuNaKol.getInt("sifra_korisnika");

                    dohvaceni = new StudentNaKolegiju(sifra_studenta_na_kolegiju,sifra_kolegija,sifra_korisnika);
                    System.out.println("DEBUG|UsersController|provjeriDaLiJeStudentUpisanNaKolegij|DOHVACEN| "+dohvaceni);
                    

               }
                return dohvaceni;
    }
 
        public StudentNaKolegiju dohvatiCijelogStudentaNaKolegiju(String brojIskaznice, int sifraKolegija) throws Exception{
            StudentNaKolegiju akorisnik = dohvatiStudentaNaKolegiju(brojIskaznice,sifraKolegija);
            return akorisnik;
    }

        //Dohvacanje kolegija iz baze po ID-u profesora
        public static Collection dohvatiEvidentiraneStudente(int sifra_termina,int sifra_kolegija) throws Exception{

           ArrayList<Users> lista = new ArrayList<>();
            try{
                Connection uspostaviKonekciju = SQLController.kreirajKonekciju();
                
                String sql ="SELECT users.* FROM users\n"
                           + "LEFT OUTER JOIN student_na_kolegiju ON users.sifra_korisnika=student_na_kolegiju.sifra_korisnika\n"
                           + "LEFT OUTER JOIN evidencija ON evidencija.sifra_studenta_na_kolegiju=student_na_kolegiju.sifra_studenta_na_kolegiju\n"
                           + "WHERE evidencija.sifra_termina=?\n"
                           + "AND student_na_kolegiju.sifra_kolegija=? AND evidencija.prisutnost=1 ORDER BY users.prezime;";

                PreparedStatement stmt = uspostaviKonekciju.prepareStatement(sql);
                stmt.setInt(1, sifra_termina);
                stmt.setInt(2,  sifra_kolegija);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()){

                    int sifra_korisnika = rs.getInt("sifra_korisnika");
                    String broj_iskaznice= rs.getString("broj_iskaznice");
                    String ime= rs.getString("ime");
                    String prezime= rs.getString("prezime");
                    String email= rs.getString("email");
                    lista.add(new Users(sifra_korisnika,broj_iskaznice,ime,prezime,email));
                     System.out.println("DEBUG|UsersController|dohvatiEvidentiraneStudente|Exception|SQL|" + lista);

                }
            }catch(SQLException greska){
                System.out.println("DEBUG|UsersController|dohvatiEvidentiraneStudente|Exception|SQL|" + greska.toString());
                JOptionPane.showMessageDialog(null,"SQL greška"+greska,"Greška kod rada s bazom",JOptionPane.PLAIN_MESSAGE);

            }

            return lista;
        }

        public Collection listaEvidentiranihStudenata(int sifra_termina,int sifra_kolegija) throws Exception{
            return dohvatiEvidentiraneStudente(sifra_termina,sifra_kolegija);
        }
        
        static boolean obrisiStudentaSTerminaPredavanja(int sifra_korisnika,Date termin,int sifra_kolegija) throws Exception {
       
            try (Connection uspostaviKonekciju = SQLController.kreirajKonekciju()) {
                
                String sifraStudentaNaKolegiju ="SELECT student_na_kolegiju.sifra_studenta_na_kolegiju FROM student_na_kolegiju\n"
                        + " WHERE student_na_kolegiju.sifra_korisnika=? AND student_na_kolegiju.sifra_kolegija=?";
                
                String izbrisiPrisutnostZaKorinika="UPDATE  evidencija SET evidencija.prisutnost=0\n "
                        + "WHERE evidencija.sifra_studenta_na_kolegiju=? AND evidencija.datum_evidentiranja=?";

                PreparedStatement stmt1 = uspostaviKonekciju.prepareStatement(sifraStudentaNaKolegiju);
                stmt1.setInt(1,sifra_korisnika);
                stmt1.setInt(2,sifra_kolegija);
                
                 ResultSet rs = stmt1.executeQuery();
                 rs.first();
            
                 int sifra_studenta_na_kolegiju = rs.getInt("sifra_studenta_na_kolegiju");
                 System.out.println("DEBUG|UsersController|obrisiStudentaSTerminaPredavanja|sifraStudentaNaKolegiju| "+sifra_studenta_na_kolegiju);

                 
                PreparedStatement stmt = uspostaviKonekciju.prepareStatement(izbrisiPrisutnostZaKorinika);
                stmt.setInt(1,sifra_studenta_na_kolegiju);
                stmt.setDate(2, (java.sql.Date) termin);
                stmt.executeUpdate();
                stmt.close();
                System.out.println("DEBUG|UsersController|obrisiStudentaSTerminaPredavanja|STUDENT OBRISAN!!!!!!!");
                //JOptionPane.showMessageDialog(null,"Brisanje studenta s termina je uspješno.","Obavjest",JOptionPane.PLAIN_MESSAGE);
                 
            }catch(SQLException sql){
                JOptionPane.showMessageDialog(null,"GreskaSQL\n"+sql.toString(), "Greška kod brisanja studenta s termina", JOptionPane.ERROR_MESSAGE);
                System.out.println("DEBUG|UsersController|obrisiStudentaSTerminaPredavanja|Exception|SQL| " + sql.toString());
                return false;
            }catch(Exception greska){
                JOptionPane.showMessageDialog(null,"Greška\n"+greska.toString(),"GREŠKA!!!",JOptionPane.ERROR_MESSAGE);
                System.out.println("DEBUG|UsersController|obrisiStudentaSTerminaPredavanja|Exception|" + greska.toString());
                return false;
            }
        return true;
        
        }

        public boolean obrisiStudentaSTerminapredavanja(int sifra_korisnika,Date termin,int sifra_kolegija) throws Exception{
            boolean obrisano=obrisiStudentaSTerminaPredavanja(sifra_korisnika,termin,sifra_kolegija);
            return obrisano;
        }

        static boolean updateLozinke(String Staralozinka,String NovaLozinka,int sifra_korisnika, JTextField stara){
        
         try{
                try (Connection uspostaviKonekciju = SQLController.kreirajKonekciju()) {
                    
                    String dohvatiLozinkuKorisnika ="SELECT password_2 FROM users WHERE sifra_korisnika=?;";
            
                    byte[] kodiranalozinkaNova = Base64.getEncoder().encode(NovaLozinka.getBytes());
                    String lozinkaNova = new String(kodiranalozinkaNova);
                    System.out.println(""+lozinkaNova);
                    
                    byte[] kodiranalozinkaStara = Base64.getEncoder().encode(Staralozinka.getBytes());
                    String lozinkaStara = new String(kodiranalozinkaStara);
                    System.out.println(""+lozinkaStara);
                    
                    String sql = "UPDATE users SET password_2=?, updated_at=? WHERE sifra_korisnika = ?;";
                    System.out.println("DEBUG|UsersController|updateLozinke|SQL|" + sql);
                    
                    PreparedStatement stmt1 = uspostaviKonekciju.prepareStatement(dohvatiLozinkuKorisnika);
                    stmt1.setInt(1,sifra_korisnika);
                    ResultSet rs = stmt1.executeQuery();
                    rs.first();
                    
                    String dohvacenaLozinka = rs.getString("password_2");
                    
                    if(dohvacenaLozinka.equals(lozinkaStara)== false){
                        System.out.println("DEBUG|UsersController|updateLozinke|StaraLozinkaNetocna|");
                        JOptionPane.showMessageDialog(null,"Unijeli ste netočno staru lozinku!!!","Upozorenje",JOptionPane.WARNING_MESSAGE);
                        stara.setText("");
                        return false;
                    }
                    
                    Calendar calendar = Calendar.getInstance();
                    java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());

                    PreparedStatement stmt = uspostaviKonekciju.prepareStatement(sql);
                    stmt.setString(1,lozinkaNova);
                    stmt.setTimestamp(2,ourJavaTimestampObject);
                    stmt.setInt(3,sifra_korisnika);
                    stmt.executeUpdate();

                    JOptionPane.showMessageDialog(null,"Lozinka je promjenjena.","Obavijest",JOptionPane.PLAIN_MESSAGE);
                    System.out.println("DEBUG|UsersController|update|Lozinka ažurirana");
                    
                }catch(SQLException e){
                    System.out.println("DEBUG|UsersController|update|GreskaSQL"+e);
                    //JOptionPane.showMessageDialog(null,"SQL greška\n"+e,"Greška kod spremanja u bazu",JOptionPane.PLAIN_MESSAGE);
                    return false;
                }
                
            }catch(Exception greska){
                System.out.println("DEBUG|UsersController|update|Greska"+greska);
                JOptionPane.showMessageDialog(null,"Greška\n"+greska,"GREŠKA!!!",JOptionPane.ERROR_MESSAGE);
                return false;
            }
        return true;
        
        }

        public boolean azurirajLozinku(String Staralozinka,String NovaLozinka,int sifra_korisnika, JTextField stara){
        
            boolean azurirano = updateLozinke(Staralozinka,NovaLozinka,sifra_korisnika,stara);
            return azurirano;
        }


        public static Collection dohvatiProfesore() throws Exception{

           ArrayList<Users> lista = new ArrayList<>();
            try{
                Connection uspostaviKonekciju = SQLController.kreirajKonekciju();
                
                String sql ="SELECT * FROM users\n"
                           + "WHERE users.razina_prava=?\n"
                           + "ORDER BY users.prezime;";

                PreparedStatement stmt = uspostaviKonekciju.prepareStatement(sql);
                stmt.setInt(1, 2);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()){

                    int sifra_korisnika = rs.getInt("sifra_korisnika");
                    int razina= rs.getInt("razina_prava");
                    String ime= rs.getString("ime");
                    String prezime= rs.getString("prezime");
                    String email = rs.getString("email");
                    lista.add(new Users(sifra_korisnika,ime,prezime,email,razina));

                }
            }catch(SQLException greska){
                System.out.println("DEBUG|UsersController|dohvatiProfesore|Exception|SQL|" + greska.toString());
                JOptionPane.showMessageDialog(null,"SQL greška"+greska,"Greška kod rada s bazom",JOptionPane.PLAIN_MESSAGE);

            }

            return lista;
        }

        public Collection listaProfesora() throws Exception{
            return dohvatiProfesore();
        }
        
        //unos nositelja kolegija
        static boolean unosNositeljaKolegija(Kolegij kolegij,Users users) throws Exception {
        try{
                try (Connection uspostaviKonekciju = SQLController.kreirajKonekciju()) {
                    
  
                    String sql = "UPDATE kolegij SET sifra_profesora=?, updated_at=? WHERE sifra_kolegija = ?;";
                    System.out.println("DEBUG|USERSController|unosNositeljaKolegija|SQL|" + sql);
                    
                   if(JOptionPane.showConfirmDialog(null,"Da li ste sigurni da želite postaviti nositelja: "+users.getIme()+" "+users.getPrezime()+ " za kolegij: "+kolegij.getNaziv(),"Upozorenje!!!", JOptionPane.YES_NO_OPTION)==0){
                        Calendar calendar = Calendar.getInstance();
                        java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
                         
                        PreparedStatement stmt = uspostaviKonekciju.prepareStatement(sql);
                        stmt.setInt(1,users.getSifra_korisnika());
                        stmt.setTimestamp(2,ourJavaTimestampObject);
                        stmt.setInt(3,kolegij.getSifra_kolegija());
                        stmt.executeUpdate();

                        JOptionPane.showMessageDialog(null,"Nositelj "+users.getIme()+" "+users.getPrezime()+" za kolegij "+kolegij.getNaziv()+" je postavljen.","Obavijest",JOptionPane.PLAIN_MESSAGE);
                        System.out.println("DEBUG|USERSController|unosNositeljaKolegija|Nositelj "+users.getIme()+" "+users.getPrezime()+" za kolegij "+kolegij.getNaziv()+" je postavljen.");
                        }
                }catch(SQLException e){
                    System.out.println("DEBUG|USERSController|unosNositeljaKolegija|GreskaSQL"+e);
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
        
        public boolean unosNositelja(Kolegij kolegij, Users users) throws Exception{
            boolean azurirano=unosNositeljaKolegija(kolegij,users);
            return azurirano;
    }
        
        public static Collection dohvatiStudente() throws Exception{

           ArrayList<Users> lista = new ArrayList<>();
            try{
                Connection uspostaviKonekciju = SQLController.kreirajKonekciju();

                String sql ="SELECT * FROM users\n"
                           + "WHERE users.razina_prava=?\n"
                           + "ORDER BY users.prezime;";

                PreparedStatement stmt = uspostaviKonekciju.prepareStatement(sql);
                stmt.setInt(1, 3);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()){

                    int sifra_korisnika = rs.getInt("sifra_korisnika");
                    int razina= rs.getInt("razina_prava");
                    String ime= rs.getString("ime");
                    String prezime= rs.getString("prezime");
                    String email = rs.getString("email");
                    lista.add(new Users(sifra_korisnika,ime,prezime,email,razina));

                }
            }catch(SQLException greska){
                System.out.println("DEBUG|UsersController|dohvatiStudente|Exception|SQL|" + greska.toString());
                JOptionPane.showMessageDialog(null,"SQL greška"+greska,"Greška kod rada s bazom",JOptionPane.PLAIN_MESSAGE);

            }

            return lista;
        }
        
         public Collection listaStudenata() throws Exception{
            return dohvatiStudente();
        }
         
          //unos nositelja kolegija
        static boolean unosStudentaNaKolegijGlavna(Kolegij kolegij,Users users) throws Exception {
        try{
                try (Connection uspostaviKonekciju = SQLController.kreirajKonekciju()) {
                    
                    String provjeraStuNaKol ="SELECT COUNT(*)FROM student_na_kolegiju\n"
                           + "LEFT OUTER JOIN users ON student_na_kolegiju.sifra_korisnika=users.sifra_korisnika\n"
                           + "WHERE users.sifra_korisnika=?\n"
                           + "AND student_na_kolegiju.sifra_kolegija=?;";
  
                     String sql = "INSERT INTO student_na_kolegiju(sifra_kolegija,sifra_korisnika,created_at,updated_at) VALUES " + "(?,?,?,?);";
                    
                     
                    
                     PreparedStatement stmt1 = uspostaviKonekciju.prepareStatement(provjeraStuNaKol);
                        stmt1.setInt(1, users.getSifra_korisnika());
                        stmt1.setInt(2, kolegij.getSifra_kolegija());
                        ResultSet rs1 = stmt1.executeQuery();
                        rs1.first();

                    int postojanost = rs1.getInt("COUNT(*)");
            
                    if (postojanost != 0) {
                    JOptionPane.showMessageDialog(null,"Student je vec upisan na kolegij!!","Obavijest",JOptionPane.WARNING_MESSAGE);
                     System.out.println("DEBUG|UsersController|unosStudentaNaKolegij|PostojanjeStudentaNaKolegiju");
                     return  false;

                    }

                        Calendar calendar = Calendar.getInstance();
                        java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
                         
                        PreparedStatement stmt = uspostaviKonekciju.prepareStatement(sql);
                        stmt.setInt(1,kolegij.getSifra_kolegija());
                        stmt.setInt(2,users.getSifra_korisnika());
                        stmt.setTimestamp(3,ourJavaTimestampObject);
                        stmt.setTimestamp(4,ourJavaTimestampObject);
                        stmt.executeUpdate();

                        JOptionPane.showMessageDialog(null,"Student "+users.getIme()+" "+users.getPrezime()+" dodan je na kolegij "+kolegij.getNaziv(),"Obavijest",JOptionPane.PLAIN_MESSAGE);
                        System.out.println("DEBUG|USERSController|unosStudentaNaKolegij|Student "+users.getIme()+" "+users.getPrezime()+" dodan je na kolegij "+kolegij.getNaziv());
                        
                }catch(SQLException e){
                    System.out.println("DEBUG|USERSController|unosStudentaNaKolegij|GreskaSQL"+e);
                    //JOptionPane.showMessageDialog(null,"SQL greška\n"+e,"Greška kod spremanja u bazu",JOptionPane.PLAIN_MESSAGE);
                    return false;
                }
                
            }catch(Exception greska){
                System.out.println("DEBUG|USERSController|unosStudentaNaKolegij|Greska"+greska);
                JOptionPane.showMessageDialog(null,"Greška\n"+greska,"GREŠKA!!!",JOptionPane.ERROR_MESSAGE);
                return false;
            }
        return true;
    }
        
        public boolean unosStudentaNaKolegij(Kolegij kolegij, Users users) throws Exception{
            boolean azurirano=unosStudentaNaKolegijGlavna(kolegij,users);
            return azurirano;
    }
        
        static boolean resetLozinke(String NovaLozinka,int sifra_korisnika){
        
         try{
                try (Connection uspostaviKonekciju = SQLController.kreirajKonekciju()) {

                    byte[] kodiranalozinkaNova = Base64.getEncoder().encode(NovaLozinka.getBytes());
                    String lozinkaNova = new String(kodiranalozinkaNova);
                    System.out.println(""+lozinkaNova);
 
                    String sql = "UPDATE users SET password_2=?, updated_at=? WHERE sifra_korisnika = ?;";
                    System.out.println("DEBUG|UsersController|resetLozinke|SQL|" + sql);
  
                    Calendar calendar = Calendar.getInstance();
                    java.sql.Timestamp ourJavaTimestampObject = new java.sql.Timestamp(calendar.getTime().getTime());

                    PreparedStatement stmt = uspostaviKonekciju.prepareStatement(sql);
                    stmt.setString(1,lozinkaNova);
                    stmt.setTimestamp(2,ourJavaTimestampObject);
                    stmt.setInt(3,sifra_korisnika);
                    stmt.executeUpdate();

                    JOptionPane.showMessageDialog(null,"Lozinka je resetirana.","Obavijest",JOptionPane.PLAIN_MESSAGE);
                    System.out.println("DEBUG|UsersController|resetLozinke|Lozinka resetirana");
                    
                }catch(SQLException e){
                    System.out.println("DEBUG|UsersController|resetLozinke|GreskaSQL"+e);
                    //JOptionPane.showMessageDialog(null,"SQL greška\n"+e,"Greška kod spremanja u bazu",JOptionPane.PLAIN_MESSAGE);
                    return false;
                }
                
            }catch(Exception greska){
                System.out.println("DEBUG|UsersController|resetLozinke|Greska"+greska);
                JOptionPane.showMessageDialog(null,"Greška\n"+greska,"GREŠKA!!!",JOptionPane.ERROR_MESSAGE);
                return false;
            }
        return true;
        
        }
        
        public boolean resetirajLozinku(String NovaLozinka,int sifra_korisnika){
        
            boolean azurirano = resetLozinke(NovaLozinka,sifra_korisnika);
            return azurirano;
        }
         
        //funkcije za evidenciju studenta po kolegiju
        public static Collection studentiZaUkupnuEvidenciju(int sifra_kolegija) throws Exception{

           ArrayList<Users> listaKorisnika = new ArrayList<>();
           
            try{
                Connection uspostaviKonekciju = SQLController.kreirajKonekciju();

                String sql ="SELECT * FROM users\n"
                           + "LEFT OUTER JOIN student_na_kolegiju ON student_na_kolegiju.sifra_korisnika=users.sifra_korisnika\n"
                           + "WHERE student_na_kolegiju.sifra_kolegija=?\n"
                           + "ORDER BY users.prezime;";

                PreparedStatement stmt = uspostaviKonekciju.prepareStatement(sql);
                stmt.setInt(1, sifra_kolegija);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()){

                    int sifra_korisnika = rs.getInt("sifra_korisnika");
                    int razina= rs.getInt("razina_prava");
                    String ime= rs.getString("ime");
                    String broj_iskaznice =rs.getString("broj_iskaznice");
                    String prezime= rs.getString("prezime");
                    String email = rs.getString("email");
                    listaKorisnika.add(new Users(sifra_korisnika,broj_iskaznice,ime,prezime,email,razina));

                }

            }catch(SQLException greska){
                System.out.println("DEBUG|UsersController|dohvatiStudente|Exception|SQL|" + greska.toString());
                JOptionPane.showMessageDialog(null,"SQL greška"+greska,"Greška kod rada s bazom",JOptionPane.PLAIN_MESSAGE);

            }

            return listaKorisnika;
        }
        
        public Collection listaStudenataKolegija(int sifra) throws Exception{
            return studentiZaUkupnuEvidenciju(sifra);
        }
         
        static int UkupanBrojTerminaZaKolegij(int sifraKolegija) throws SQLException {
            int brojTermina = 0;

                try (Connection uspostaviKonekciju = SQLController.kreirajKonekciju()) {
                   
                  String sqlBrojTermina ="SELECT COUNT(*) FROM kolegij\n"
                           + "LEFT OUTER JOIN termin ON termin.sifra_kolegija=kolegij.sifra_kolegija\n"
                           + "WHERE termin.sifra_kolegija=?;";

                   PreparedStatement stmt = uspostaviKonekciju.prepareStatement(sqlBrojTermina);
                   stmt.setInt(1, sifraKolegija);
                   ResultSet rs = stmt.executeQuery();
                   rs.first();

                    int ukupanBroj = rs.getInt("COUNT(*)");
                    brojTermina=ukupanBroj;
                    System.out.println("DEBUG|UsersController|UkupanBrojTerminaZaKolegij|DOHVACENBROJTERMINA| "+brojTermina);

               }
                return brojTermina;
    }
         
        public int ukupanBrojTerminaPoKolegiju(int broj) throws Exception{
            int brojTermina = UkupanBrojTerminaZaKolegij(broj);
            return brojTermina;
        }
        
        static int BrojPrisustvaKorisnika(int sifra_kolegija, int sifra_korisnika) throws SQLException {
            int brojPrisustva = 0;

                try (Connection uspostaviKonekciju = SQLController.kreirajKonekciju()) {
                   
                  String sqlBrojTermina ="SELECT COUNT(*) FROM users\n"
                           + "LEFT OUTER JOIN student_na_kolegiju ON student_na_kolegiju.sifra_korisnika=users.sifra_korisnika\n"
                           + "LEFT OUTER JOIN evidencija ON student_na_kolegiju.sifra_studenta_na_kolegiju=evidencija.sifra_studenta_na_kolegiju\n"
                           + "LEFT OUTER JOIN termin ON evidencija.sifra_termina=termin.sifra_termina\n"
                           + "LEFT OUTER JOIN kolegij ON termin.sifra_kolegija=kolegij.sifra_kolegija\n"
                           + "WHERE student_na_kolegiju.sifra_kolegija=?\n"
                           + "AND student_na_kolegiju.sifra_korisnika=?\n"
                           + "AND evidencija.prisutnost=?;";

                   PreparedStatement stmt = uspostaviKonekciju.prepareStatement(sqlBrojTermina);
                   stmt.setInt(1, sifra_kolegija);
                   stmt.setInt(2, sifra_korisnika);
                   stmt.setInt(3, 1);
                   ResultSet rs = stmt.executeQuery();
                   rs.first();

                    int ukupanBroj = rs.getInt("COUNT(*)");
                    brojPrisustva=ukupanBroj;
                    System.out.println("DEBUG|UsersController|BrojPrisustvaKorisnika|DOHVACENBROJPRISUSTVA| "+brojPrisustva);

               }
                return brojPrisustva;
    }
        
        public int ukupanBrojPrisustvaKorisnika(int sifra_kolegija, int sifra_korisnika) throws Exception{
            int brojPrisustva = BrojPrisustvaKorisnika(sifra_kolegija,sifra_korisnika);
            return brojPrisustva;
        }
        
              }

