/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui.client;

import dao.ReservationDAO;
import models.Reservation;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 *
 * @author diaom
 */
public class ClientDashboard extends javax.swing.JFrame {

    private Integer clientId;

    public ClientDashboard(Integer clientId) {
        this.clientId = clientId;
        initComponents();
        chargerReservations();
        chargerChambresDisponibles();
        setupActions();
    }

    private void setupActions() {
       // btnAjouter.addActionListener(e -> ajouterReservation());
        btnModifier.addActionListener(e -> modifierReservation());
        btnSupprimer.addActionListener(e -> supprimerReservation());
        btnDeconnexion.addActionListener(e -> deconnexion());
        btnAjouterChambre.addActionListener(e -> reserverChambreSelectionnee());
    }

    private void chargerReservations() {
        try {
            List<Reservation> reservations = ReservationDAO.getAll();
            DefaultTableModel model = new DefaultTableModel();
            String[] colonnes = {"ID", "Check-in", "Check-out", "Statut", "Montant", "Acompte"};
            model.setColumnIdentifiers(colonnes);
            for (Reservation r : reservations) {
                if (r.getClientId() != clientId) continue;
                Object[] row = {
                    r.getId(),
                    r.getDateCheckin(),
                    r.getDateCheckout(),
                    r.getStatut(),
                    r.getMontantTotal(),
                    r.getAcompte()
                };
                model.addRow(row);
            }
            tableReservations.setModel(model);
            // Cacher la colonne ID
            tableReservations.getColumnModel().getColumn(0).setMinWidth(0);
            tableReservations.getColumnModel().getColumn(0).setMaxWidth(0);
            tableReservations.getColumnModel().getColumn(0).setWidth(0);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement des réservations : " + e.getMessage());
        }
    }

    private void chargerChambresDisponibles() {
        try {
            java.util.List<models.Chambre> chambres = dao.ChambreDAO.findAll();
            javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel();
            String[] colonnes = {"ID", "Numéro", "Type", "Statut", "Prix"};
            model.setColumnIdentifiers(colonnes);
            for (models.Chambre ch : chambres) {
                if (ch.getStatut() != enums.Enums.StatutChambre.DISPONIBLE) continue;
                models.TypeChambre type = dao.TypeChambreDAO.findById(ch.getTypeChambreId());
                Object[] row = {
                    ch.getId(),
                    ch.getNumero(),
                    (type != null ? type.getNom() : "N/A"),
                    ch.getStatut(),
                    (type != null ? type.getPrixBase() : "N/A")
                };
                model.addRow(row);
            }
            tableChambre.setModel(model);
            // Cacher la colonne ID
            tableChambre.getColumnModel().getColumn(0).setMinWidth(0);
            tableChambre.getColumnModel().getColumn(0).setMaxWidth(0);
            tableChambre.getColumnModel().getColumn(0).setWidth(0);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement des chambres : " + e.getMessage());
        }
    }

    private void ajouterReservation() {
        ReservationFrom form = new ReservationFrom(clientId, null);
        form.setLocationRelativeTo(this);
        form.setVisible(true);
        // Après fermeture du formulaire, rafraîchir la liste
        form.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                chargerReservations();
                chargerChambresDisponibles();
            }
        });
    }

    private void modifierReservation() {
        int selectedRow = tableReservations.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une réservation à modifier.");
            return;
        }
        int reservationId = Integer.parseInt(tableReservations.getValueAt(selectedRow, 0).toString());
        try {
            Reservation reservation = ReservationDAO.getById(reservationId);
            if (reservation == null) {
                JOptionPane.showMessageDialog(this, "Réservation introuvable.");
                return;
            }
            ReservationFrom form = new ReservationFrom(clientId, reservation);
            form.setLocationRelativeTo(this);
            form.setVisible(true);
            form.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    chargerReservations();
                    chargerChambresDisponibles();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement de la réservation : " + e.getMessage());
        }
    }

    private void supprimerReservation() {
        int selectedRow = tableReservations.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une réservation à supprimer.");
            return;
        }
        int reservationId = Integer.parseInt(tableReservations.getValueAt(selectedRow, 0).toString());
        int confirm = JOptionPane.showConfirmDialog(this, "Supprimer cette réservation ?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                ReservationDAO.delete(reservationId);
                chargerReservations();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erreur lors de la suppression : " + e.getMessage());
            }
        }
    }

    private void deconnexion() {
        int confirm = JOptionPane.showConfirmDialog(this, "Êtes-vous sûr de vouloir vous déconnecter ?", "Déconnexion", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            this.dispose();
            new ui.ConnexionForm().setVisible(true);
        }
    }

    private void reserverChambreSelectionnee() {
        int selectedRow = tableChambre.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une chambre à réserver.");
            return;
        }
        int chambreId = Integer.parseInt(tableChambre.getValueAt(selectedRow, 0).toString());
        ReservationFrom form = new ReservationFrom(clientId, null, chambreId);
        form.setLocationRelativeTo(this);
        form.setVisible(true);
        form.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                chargerReservations();
                chargerChambresDisponibles();
            }
        });
    }

    /**
     * Creates new form ClientDashboard
     */
    public ClientDashboard() {
        this(-1);
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        btnDeconnexion = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableReservations = new javax.swing.JTable();
        btnModifier = new javax.swing.JButton();
        btnSupprimer = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableChambre = new javax.swing.JTable();
        btnAjouterChambre = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        listEvenemnets = new javax.swing.JTable();
        btnAjouterChambre1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout(10, 0));

        jLabel1.setText("🏨 CLIENT DASHBOARD");

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "reservation" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        btnDeconnexion.setText("déconnexion");
        btnDeconnexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeconnexionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnDeconnexion)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(87, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(71, 71, 71)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105)
                .addComponent(btnDeconnexion)
                .addContainerGap(190, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, java.awt.BorderLayout.WEST);

        tableReservations.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tableReservations);

        btnModifier.setText("Modifier Reservation");
        btnModifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifierActionPerformed(evt);
            }
        });

        btnSupprimer.setText("Supprimer Reservation");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(197, 197, 197)
                .addComponent(btnModifier)
                .addGap(53, 53, 53)
                .addComponent(btnSupprimer)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModifier)
                    .addComponent(btnSupprimer))
                .addGap(0, 36, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Reservation", jPanel3);

        tableChambre.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tableChambre);

        btnAjouterChambre.setText("Ajouter reservation");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btnAjouterChambre)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(btnAjouterChambre)
                .addGap(0, 36, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("List Chambres", jPanel4);

        listEvenemnets.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(listEvenemnets);

        btnAjouterChambre1.setText("Ajouter reservation");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btnAjouterChambre1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(btnAjouterChambre1)
                .addGap(0, 36, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("List Evenements", jPanel5);

        jPanel1.add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeconnexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeconnexionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeconnexionActionPerformed

    private void btnModifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModifierActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClientDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAjouterChambre;
    private javax.swing.JButton btnAjouterChambre1;
    private javax.swing.JButton btnDeconnexion;
    private javax.swing.JButton btnModifier;
    private javax.swing.JButton btnSupprimer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable listEvenemnets;
    private javax.swing.JTable tableChambre;
    private javax.swing.JTable tableReservations;
    // End of variables declaration//GEN-END:variables
}
