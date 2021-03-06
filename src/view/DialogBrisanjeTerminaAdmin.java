/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.KolegijController;
import controller.TerminController;
import java.util.List;
import javax.swing.JOptionPane;
import model.Kolegij;
import model.Termin;

/**
 *
 * @author Admin
 */
public class DialogBrisanjeTerminaAdmin extends javax.swing.JDialog {

      TerminController controllerTermin = new TerminController();
      KolegijController controllerKolegij = new KolegijController();
    /**
     * Creates new form DialogBrisanjeTerminaAdmin
     */
    public DialogBrisanjeTerminaAdmin(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        preuzimanjeKoelgija();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbKolegiji = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cmbTermin = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Brisanje termina - Administrator");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Brisanje termina kolegija"));

        jLabel1.setText("Odaberite kolegij:");

        cmbKolegiji.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbKolegijiItemStateChanged(evt);
            }
        });
        cmbKolegiji.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKolegijiActionPerformed(evt);
            }
        });

        jLabel2.setText("Odaberite termin:");

        cmbTermin.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTerminItemStateChanged(evt);
            }
        });

        jButton1.setText("Obriši termin ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbKolegiji, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbTermin, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(14, 14, 14))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbKolegiji, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTermin, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public void preuzimanjeKoelgija() {
        
        cmbKolegiji.removeAllItems();
        List<Kolegij> lista;
        

        try {
            lista = (List<Kolegij>) controllerKolegij.listaKolegija();
            lista.stream().forEach((i) -> {
                cmbKolegiji.addItem(i);
            });
            System.out.println("DEBUG|DialogBrisanjeTerminaAdmin|preuzimanjeSvihKolegija|USPJESAN");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Greška"+e, "Kritična Greška", JOptionPane.ERROR_MESSAGE);
            System.out.println("DEBUG|DialogBrisanjeTerminaAdmin|preuzimanjeSvihKolegija|Exception|" + e.toString());
            return;
        }

        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nema kolegija u bazi podataka!!!!.", "Obavijest", JOptionPane.PLAIN_MESSAGE);
            System.out.println("DEBUG|DialogBrisanjeTerminaAdmin|preuzimanjeSvihKolegija|Prazna lista");
        }
    }
    
    private void preuzimanjeTerminaPoKolegiju(int id) {
        
        cmbTermin.removeAllItems();
        List<Termin> lista;
        

        try {
            lista = (List<Termin>) controllerTermin.listaTerminaPoKolegiju(id);
            
            lista.stream().forEach((i) -> {
                cmbTermin.addItem(i);
            });
            System.out.println("DEBUG|DialogBrisanjeTerminaAdmin|preuzimanjeTerminaPoKolegiju|USPJESAN");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Greška"+e, "Kritična Greška", JOptionPane.ERROR_MESSAGE);
            System.out.println("DEBUG|DialogBrisanjeTerminaAdmin|preuzimanjeTerminaPoKolegiju|Exception|" + e.toString());
            return;
        }

        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nema termina za taj kolegij!!!!.", "Obavijest", JOptionPane.PLAIN_MESSAGE);
            System.out.println("DEBUG|DialogBrisanjeTerminaAdmin|preuzimanjeTerminaPoKolegiju|Prazna lista|Nema termina za taj kolegij");
        }
    }
    private void cmbKolegijiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbKolegijiItemStateChanged
        Kolegij odabrani = (Kolegij) cmbKolegiji.getSelectedItem();

        preuzimanjeTerminaPoKolegiju(odabrani.getSifra_kolegija());
    }//GEN-LAST:event_cmbKolegijiItemStateChanged

    private void cmbKolegijiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKolegijiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbKolegijiActionPerformed

    private void cmbTerminItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTerminItemStateChanged

    }//GEN-LAST:event_cmbTerminItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            Kolegij odabrani = (Kolegij)cmbKolegiji.getSelectedItem();
            Termin odabraniTermin = (Termin) cmbTermin.getSelectedItem();

            controllerTermin.destrojTermin( odabraniTermin.getSifra_termina());
            preuzimanjeTerminaPoKolegiju(odabrani.getSifra_kolegija());

        } catch (Exception f) {
            System.out.println("DEBUG|ProzorBrisanjeTermina|destroyTermina|Greska"+f.toString());
            JOptionPane.showMessageDialog(this,"Kontaktirajte administratora. \n Greška broj: " + f, "Kritična Greška", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DialogBrisanjeTerminaAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogBrisanjeTerminaAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogBrisanjeTerminaAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogBrisanjeTerminaAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogBrisanjeTerminaAdmin dialog = new DialogBrisanjeTerminaAdmin(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Kolegij> cmbKolegiji;
    private javax.swing.JComboBox<Termin> cmbTermin;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
