/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import view.GlavniIzbornik;


/**
 *
 * @author Admin
 */
public class Glavna {
 public static void main(String[] args) {
     
        GlavniIzbornik prozor = null;
     try {
         prozor = new GlavniIzbornik();
     } catch (Exception ex) {
         Logger.getLogger(Glavna.class.getName()).log(Level.SEVERE, null, ex);
     }
        prozor.setDefaultCloseOperation(GlavniIzbornik.EXIT_ON_CLOSE);
        prozor.setLocationRelativeTo(null);
        prozor.setVisible(true);

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Glavna.class.getName()).log(Level.SEVERE, null, ex);
        }
        SwingUtilities.updateComponentTreeUI(prozor);

  
}

    
    
}
