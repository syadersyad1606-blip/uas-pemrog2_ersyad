/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package transaksi;
import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author User
 */
public class TransaksiBilling extends javax.swing.JFrame {

    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    DefaultTableModel model;

    /**
     * Creates new form TransaksiBilling
     */
    public TransaksiBilling() {
        initComponents();

        conn = new Koneksi().connect();

        setLocationRelativeTo(null);
        setTitle("Transaksi Billing");

        model = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        model.addColumn("ID");
        model.addColumn("Komputer");
        model.addColumn("Pelanggan");
        model.addColumn("Tarif");
        model.addColumn("Waktu Mulai");
        model.addColumn("Waktu Selesai");
        model.addColumn("Durasi (menit)");
        model.addColumn("Total Bayar");
        model.addColumn("Status");
        model.addColumn("Status Bayar");
        tblTransaksi.setModel(model);

        muatKomputer();
        muatPelanggan();
        muatTarif();
        tampilData();
    }

    private void muatKomputer(){

        cbKomputer.removeAllItems();

        try{

            String sql = "SELECT id_komputer, nomor_komputer FROM tb_komputer WHERE status_komputer='Aktif' ORDER BY nomor_komputer";

            pst = conn.prepareStatement(sql);

            rs = pst.executeQuery();

            while(rs.next()){

                cbKomputer.addItem(rs.getString("id_komputer") + " - " + rs.getString("nomor_komputer"));

            }

        }catch(Exception e){

            JOptionPane.showMessageDialog(null,e);

        }
    }

    private void muatPelanggan(){

        cbPelanggan.removeAllItems();

        cbPelanggan.addItem("0 - Umum (Tanpa Data Pelanggan)");

        try{

            String sql = "SELECT id_pelanggan, nama_pelanggan FROM tb_pelanggan ORDER BY nama_pelanggan";

            pst = conn.prepareStatement(sql);

            rs = pst.executeQuery();

            while(rs.next()){

                cbPelanggan.addItem(rs.getString("id_pelanggan") + " - " + rs.getString("nama_pelanggan"));

            }

        }catch(Exception e){

            JOptionPane.showMessageDialog(null,e);

        }
    }

    private void muatTarif(){

        cbTarif.removeAllItems();

        try{

            String sql = "SELECT id_tarif, nama_tarif, harga_per_jam FROM tb_tarif ORDER BY nama_tarif";

            pst = conn.prepareStatement(sql);

            rs = pst.executeQuery();

            while(rs.next()){

                cbTarif.addItem(rs.getString("id_tarif") + " - " + rs.getString("nama_tarif") + " (Rp " + rs.getString("harga_per_jam") + "/jam)");

            }

        }catch(Exception e){

            JOptionPane.showMessageDialog(null,e);

        }
    }

    private String ambilIdDariCombo(Object item){

        if(item == null){
            return null;
        }

        String teks = item.toString();

        int idx = teks.indexOf(" - ");

        if(idx < 0){
            return teks.trim();
        }

        return teks.substring(0, idx).trim();
    }

    private void tampilData(){

        model.setRowCount(0);

        try{

            String sql = "SELECT t.id_transaksi, k.nomor_komputer, "
                    + "COALESCE(p.nama_pelanggan,'Umum') AS nama_pelanggan, "
                    + "tr.nama_tarif, t.waktu_mulai, t.waktu_selesai, t.durasi_menit, "
                    + "t.total_bayar, t.status_transaksi, t.status_bayar "
                    + "FROM tb_transaksi t "
                    + "JOIN tb_komputer k ON t.id_komputer = k.id_komputer "
                    + "LEFT JOIN tb_pelanggan p ON t.id_pelanggan = p.id_pelanggan "
                    + "JOIN tb_tarif tr ON t.id_tarif = tr.id_tarif "
                    + "ORDER BY t.id_transaksi DESC";

            pst = conn.prepareStatement(sql);

            rs = pst.executeQuery();

            while(rs.next()){

                model.addRow(new Object[]{

                    rs.getString("id_transaksi"),
                    rs.getString("nomor_komputer"),
                    rs.getString("nama_pelanggan"),
                    rs.getString("nama_tarif"),
                    rs.getString("waktu_mulai"),
                    rs.getString("waktu_selesai"),
                    rs.getString("durasi_menit"),
                    rs.getString("total_bayar"),
                    rs.getString("status_transaksi"),
                    rs.getString("status_bayar")

                });

            }

        }catch(Exception e){

            JOptionPane.showMessageDialog(null,e);

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbKomputer = new javax.swing.JComboBox<>();
        cbPelanggan = new javax.swing.JComboBox<>();
        cbTarif = new javax.swing.JComboBox<>();
        btnMulai = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTransaksi = new javax.swing.JTable();
        btnSelesai = new javax.swing.JButton();
        btnDetail = new javax.swing.JButton();
        btnBayar = new javax.swing.JButton();
        btnRiwayat = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Komputer");
        jLabel2.setText("Pelanggan");
        jLabel3.setText("Tarif");

        btnMulai.setText("Mulai Sesi");
        btnMulai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMulaiActionPerformed(evt);
            }
        });

        tblTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Komputer", "Pelanggan", "Tarif", "Waktu Mulai", "Waktu Selesai", "Durasi", "Total Bayar", "Status", "Status Bayar"
            }
        ));
        jScrollPane1.setViewportView(tblTransaksi);

        btnSelesai.setText("Selesai Sesi");
        btnSelesai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelesaiActionPerformed(evt);
            }
        });

        btnDetail.setText("Detail");
        btnDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailActionPerformed(evt);
            }
        });

        btnBayar.setText("Bayar");
        btnBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBayarActionPerformed(evt);
            }
        });

        btnRiwayat.setText("Riwayat");
        btnRiwayat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRiwayatActionPerformed(evt);
            }
        });

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(6,6,6)
                        .addComponent(cbKomputer, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15,15,15)
                        .addComponent(jLabel2)
                        .addGap(6,6,6)
                        .addComponent(cbPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15,15,15)
                        .addComponent(jLabel3)
                        .addGap(6,6,6)
                        .addComponent(cbTarif, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15,15,15)
                        .addComponent(btnMulai))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSelesai)
                        .addGap(12,12,12)
                        .addComponent(btnDetail)
                        .addGap(12,12,12)
                        .addComponent(btnBayar)
                        .addGap(12,12,12)
                        .addComponent(btnRiwayat)
                        .addGap(12,12,12)
                        .addComponent(btnRefresh)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbKomputer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(cbPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(cbTarif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMulai))
                .addGap(15,15,15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15,15,15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSelesai)
                    .addComponent(btnDetail)
                    .addComponent(btnBayar)
                    .addComponent(btnRiwayat)
                    .addComponent(btnRefresh))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMulaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMulaiActionPerformed
        // TODO add your handling code here:

        String idKomputer = ambilIdDariCombo(cbKomputer.getSelectedItem());
        String idPelangganTeks = ambilIdDariCombo(cbPelanggan.getSelectedItem());
        String idTarif = ambilIdDariCombo(cbTarif.getSelectedItem());

        if(idKomputer == null || idTarif == null){
            JOptionPane.showMessageDialog(null,"Pastikan Komputer dan Tarif sudah dipilih (tidak ada komputer aktif/tarif tersedia).");
            return;
        }

        try{

            String sql = "INSERT INTO tb_transaksi(id_komputer,id_pelanggan,id_tarif,waktu_mulai,status_transaksi,status_bayar) VALUES(?,?,?,?,?,?)";

            pst = conn.prepareStatement(sql);

            pst.setString(1, idKomputer);

            if(idPelangganTeks == null || idPelangganTeks.equals("0")){
                pst.setNull(2, java.sql.Types.INTEGER);
            }else{
                pst.setString(2, idPelangganTeks);
            }

            pst.setString(3, idTarif);
            pst.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            pst.setString(5, "Berlangsung");
            pst.setString(6, "Belum Bayar");

            pst.executeUpdate();

            String sqlUpdate = "UPDATE tb_komputer SET status_komputer='Dipakai' WHERE id_komputer=?";

            pst = conn.prepareStatement(sqlUpdate);
            pst.setString(1, idKomputer);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null,"Sesi Billing Dimulai");

            muatKomputer();
            tampilData();

        }catch(Exception e){

            JOptionPane.showMessageDialog(null,e);

        }
    }//GEN-LAST:event_btnMulaiActionPerformed

    private void btnSelesaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelesaiActionPerformed
        // TODO add your handling code here:

        int baris = tblTransaksi.getSelectedRow();

        if(baris < 0){
            JOptionPane.showMessageDialog(null,"Pilih transaksi pada tabel terlebih dahulu!");
            return;
        }

        String idTransaksi = tblTransaksi.getValueAt(baris,0).toString();
        String status = tblTransaksi.getValueAt(baris,8).toString();

        if(!status.equals("Berlangsung")){
            JOptionPane.showMessageDialog(null,"Transaksi ini sudah selesai!");
            return;
        }

        try{

            String sqlCari = "SELECT id_komputer, id_tarif, waktu_mulai FROM tb_transaksi WHERE id_transaksi=?";

            pst = conn.prepareStatement(sqlCari);
            pst.setString(1, idTransaksi);
            rs = pst.executeQuery();

            if(!rs.next()){
                JOptionPane.showMessageDialog(null,"Data transaksi tidak ditemukan!");
                return;
            }

            String idKomputer = rs.getString("id_komputer");
            String idTarif = rs.getString("id_tarif");
            Timestamp waktuMulai = rs.getTimestamp("waktu_mulai");

            String sqlTarif = "SELECT harga_per_jam FROM tb_tarif WHERE id_tarif=?";
            pst = conn.prepareStatement(sqlTarif);
            pst.setString(1, idTarif);
            rs = pst.executeQuery();

            double hargaPerJam = 0;
            if(rs.next()){
                hargaPerJam = rs.getDouble("harga_per_jam");
            }

            Timestamp waktuSelesai = new Timestamp(System.currentTimeMillis());

            long selisihMs = waktuSelesai.getTime() - waktuMulai.getTime();
            long durasiMenit = (long) Math.ceil(selisihMs / 60000.0);
            if(durasiMenit < 1){
                durasiMenit = 1;
            }

            double totalBayar = Math.ceil((durasiMenit / 60.0) * hargaPerJam);

            String sqlUpdate = "UPDATE tb_transaksi SET waktu_selesai=?, durasi_menit=?, total_bayar=?, status_transaksi='Selesai' WHERE id_transaksi=?";

            pst = conn.prepareStatement(sqlUpdate);
            pst.setTimestamp(1, waktuSelesai);
            pst.setLong(2, durasiMenit);
            pst.setDouble(3, totalBayar);
            pst.setString(4, idTransaksi);
            pst.executeUpdate();

            String sqlKomputer = "UPDATE tb_komputer SET status_komputer='Aktif' WHERE id_komputer=?";
            pst = conn.prepareStatement(sqlKomputer);
            pst.setString(1, idKomputer);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null,"Sesi Selesai. Durasi: " + durasiMenit + " menit, Total Bayar: Rp " + totalBayar);

            muatKomputer();
            tampilData();

        }catch(Exception e){

            JOptionPane.showMessageDialog(null,e);

        }
    }//GEN-LAST:event_btnSelesaiActionPerformed

    private void btnDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailActionPerformed
        // TODO add your handling code here:

        int baris = tblTransaksi.getSelectedRow();

        if(baris < 0){
            JOptionPane.showMessageDialog(null,"Pilih transaksi pada tabel terlebih dahulu!");
            return;
        }

        String idTransaksi = tblTransaksi.getValueAt(baris,0).toString();

        new DetailTransaksi(idTransaksi).setVisible(true);
    }//GEN-LAST:event_btnDetailActionPerformed

    private void btnBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBayarActionPerformed
        // TODO add your handling code here:

        int baris = tblTransaksi.getSelectedRow();

        if(baris < 0){
            JOptionPane.showMessageDialog(null,"Pilih transaksi pada tabel terlebih dahulu!");
            return;
        }

        String idTransaksi = tblTransaksi.getValueAt(baris,0).toString();
        String status = tblTransaksi.getValueAt(baris,8).toString();
        String statusBayar = tblTransaksi.getValueAt(baris,9).toString();

        if(!status.equals("Selesai")){
            JOptionPane.showMessageDialog(null,"Selesaikan sesi terlebih dahulu sebelum melakukan pembayaran!");
            return;
        }

        if(statusBayar.equals("Lunas")){
            JOptionPane.showMessageDialog(null,"Transaksi ini sudah lunas!");
            return;
        }

        Pembayaran dialog = new Pembayaran(idTransaksi);
        dialog.setVisible(true);

        tampilData();
    }//GEN-LAST:event_btnBayarActionPerformed

    private void btnRiwayatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRiwayatActionPerformed
        // TODO add your handling code here:
        new RiwayatTransaksi().setVisible(true);
    }//GEN-LAST:event_btnRiwayatActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        muatKomputer();
        muatPelanggan();
        muatTarif();
        tampilData();
    }//GEN-LAST:event_btnRefreshActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TransaksiBilling.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TransaksiBilling.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TransaksiBilling.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TransaksiBilling.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TransaksiBilling().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBayar;
    private javax.swing.JButton btnDetail;
    private javax.swing.JButton btnMulai;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnRiwayat;
    private javax.swing.JButton btnSelesai;
    private javax.swing.JComboBox<String> cbKomputer;
    private javax.swing.JComboBox<String> cbPelanggan;
    private javax.swing.JComboBox<String> cbTarif;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblTransaksi;
    // End of variables declaration//GEN-END:variables
}
