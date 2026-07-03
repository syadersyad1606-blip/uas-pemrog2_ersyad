/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package komputer;
import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author User
 */
public class StatusKomputer extends javax.swing.JFrame {

    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    DefaultTableModel model;

    /**
     * Creates new form StatusKomputer
     */
    public StatusKomputer() {
        initComponents();

        conn = new Koneksi().connect();

        setLocationRelativeTo(null);
        setTitle("Status Komputer");

        model = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        model.addColumn("ID");
        model.addColumn("Nomor Komputer");
        model.addColumn("Status");
        tblStatus.setModel(model);

        tampilData();
    }

    private void tampilData(){

        model.setRowCount(0);

        try{

            String sql = "SELECT id_komputer, nomor_komputer, status_komputer FROM tb_komputer ORDER BY nomor_komputer";

            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while(rs.next()){

                model.addRow(new Object[]{

                    rs.getString("id_komputer"),
                    rs.getString("nomor_komputer"),
                    rs.getString("status_komputer")

                });

            }

        }catch(Exception e){

            JOptionPane.showMessageDialog(null,e);

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblStatus = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        cbStatusBaru = new javax.swing.JComboBox<>();
        btnUbah = new javax.swing.JButton();
        btnTutup = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane1.setViewportView(tblStatus);

        jLabel1.setText("Status Baru");

        cbStatusBaru.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aktif", "Dipakai", "Maintenance" }));

        btnUbah.setText("Ubah Status");
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });

        btnTutup.setText("Tutup");
        btnTutup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTutupActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(10,10,10)
                        .addComponent(cbStatusBaru, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10,10,10)
                        .addComponent(btnUbah)
                        .addGap(10,10,10)
                        .addComponent(btnTutup)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15,15,15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1).addComponent(cbStatusBaru, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUbah).addComponent(btnTutup))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        // TODO add your handling code here:

        int baris = tblStatus.getSelectedRow();

        if(baris < 0){
            JOptionPane.showMessageDialog(null,"Pilih komputer pada tabel terlebih dahulu!");
            return;
        }

        String idKomputer = tblStatus.getValueAt(baris,0).toString();

        try{

            String sql = "UPDATE tb_komputer SET status_komputer=? WHERE id_komputer=?";

            pst = conn.prepareStatement(sql);
            pst.setString(1, cbStatusBaru.getSelectedItem().toString());
            pst.setString(2, idKomputer);

            pst.executeUpdate();

            JOptionPane.showMessageDialog(null,"Status Berhasil Diubah");

            tampilData();

        }catch(Exception e){

            JOptionPane.showMessageDialog(null,e);

        }
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnTutupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTutupActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnTutupActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StatusKomputer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StatusKomputer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StatusKomputer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StatusKomputer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StatusKomputer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTutup;
    private javax.swing.JButton btnUbah;
    private javax.swing.JComboBox<String> cbStatusBaru;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblStatus;
    // End of variables declaration//GEN-END:variables
}
