/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class SQLController {
    
     private boolean bazaSpojena;
    
      //Kreiranje konekcije na bazu
      static Connection kreirajKonekciju() throws SQLException {
        Connection uspostaviKonekciju = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            
           //uspostaviKonekciju = DriverManager.getConnection("jdbc:mysql://ucka.veleri.hr:3306/iperisa?characterEncoding=UTF-8", "iperisa", "67671666");
            uspostaviKonekciju = DriverManager.getConnection("jdbc:mysql://localhost/evidencija_studenti?characterEncoding=UTF-8", "loga", "loga");
            //uspostaviKonekciju = DriverManager.getConnection("jdbc:mysql://95.85.31.158:2083/cpsess4774462758/3rdparty/phpMyAdmin/db_structure.php?server=1&db=upecajst_evidencija&token=29a2d40b6c7e0b37ae9da90522fdd711?characterEncoding=UTF-8", "upecajst_loga", "loga123");

        } catch (CommunicationsException e) {
            JOptionPane.showMessageDialog(null, "Provjeri internetsku vezu.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
            System.out.println("DEBUG|SQLKontroler|stvaranjeKonekcije|CommunicationsException|" + e.toString());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Baza trenutno nije dostupna.", "Upozorenje", JOptionPane.ERROR_MESSAGE);
            System.out.println("DEBUG|SQLKontroler|stvaranjeKonekcije|SQLException|" + e.toString());
        } catch (ClassNotFoundException e) {
            System.out.println("DEBUG|SQLKontroler|stvaranjeKonekcije|ClassNotFoundException|" + e.toString());
        } catch (InstantiationException e) {
            System.out.println("DEBUG|SQLKontroler|stvaranjeKonekcije|InstantiationException|" + e.toString());
        } catch (IllegalAccessException e) {
            System.out.println("DEBUG|SQLKontroler|stvaranjeKonekcije|IllegalAccessException|" + e.toString());
        }
        return uspostaviKonekciju;
    }
      
      static boolean provjeraRadaBazePodataka() {

        boolean isBazaPovezana = false;

        try (Connection conn = kreirajKonekciju()) {
            if (conn != null) {
                isBazaPovezana = true;
            }
        } catch (Exception e) {
            System.out.println("DEBUG|SQLKontroler|provjeraRadaBazePodataka|Exception|" + e.toString());
        }
        return isBazaPovezana;
    }
      
      public boolean isBazaSpojena() {
          
        bazaSpojena = provjeraRadaBazePodataka();
        return bazaSpojena;
    }
   
    
    
}
