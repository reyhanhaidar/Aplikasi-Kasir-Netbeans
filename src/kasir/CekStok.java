package kasir;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class CekStok extends JPanel {

    private final JTable tabel;
    private final DefaultTableModel model;
    private final JScrollPane scrollPane;

    public CekStok() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        // Buat kolom tabel
        model = new DefaultTableModel();
        model.addColumn("Kode Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Stok");

        tabel = new JTable(model);
        scrollPane = new JScrollPane(tabel);
        add(scrollPane);

        tampilkanData();
    }

    private void tampilkanData() {
        try {
            try ( // Ganti ini dengan koneksi dari class koneksi kamu sendiri
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nama_database", "user", "password")) {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM barang");
                
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getString("kode_barang"),
                        rs.getString("nama_barang"),
                        rs.getInt("stok")
                    });
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal load data: " + e.getMessage());
        }
    }

    void setViSIBLE(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
