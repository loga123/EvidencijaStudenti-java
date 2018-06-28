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
import java.util.Collection;
import java.util.Date;
import javax.swing.JOptionPane;
import model.Users;

/**
 *
 * @author Admin
 */
public class StudentNaKolegijuController {
    
     public static Collection dohvatiStudenteKolegija(int sifra_kolegija) throws Exception{

           ArrayList<Users> lista = new ArrayList<>();
            try{
                Connection uspostaviKonekciju = SQLController.kreirajKonekciju();
                
                String sql ="SELECT users.* FROM student_na_kolegiju\n"
                           + "LEFT OUTER JOIN users ON student_na_kolegiju.sifra_korisnika=users.sifra_korisnika\n"
                           + "WHERE student_na_kolegiju.sifra_kolegija=?\n"
                           + "ORDER BY users.prezime;";

                PreparedStatement stmt = uspostaviKonekciju.prepareStatement(sql);
                stmt.setInt(1, sifra_kolegija);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()){

                    int sifra_korisnika = rs.getInt("sifra_korisnika");
                    String broj_iskaznice= rs.getString("broj_iskaznice");
                    String ime= rs.getString("ime");
                    String prezime= rs.getString("prezime");
                    String email= rs.getString("email");
                    lista.add(new Users(sifra_korisnika,broj_iskaznice,ime,prezime,email));

                }
            }catch(SQLException greska){
                System.out.println("DEBUG|StudentNaKolegijuController|dohvatiStudenteKolegija|Exception|SQL|" + greska.toString());
                JOptionPane.showMessageDialog(null,"SQL greška"+greska,"Greška kod rada s bazom",JOptionPane.PLAIN_MESSAGE);

            }

            return lista;
        }

        public Collection listaStudenata(int sifra_kolegija) throws Exception{
            return dohvatiStudenteKolegija(sifra_kolegija);
        }
        
    
}
