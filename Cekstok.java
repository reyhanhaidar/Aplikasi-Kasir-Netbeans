public CekStok() {
        setTitle("Cek Stok Barang");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Tombol
        btnRefresh = new JButton("Refresh Stok");
        add(btnRefresh, BorderLayout.NORTH);

        // Tabel
        tabelStok = new JTable();
        scrollPane = new JScrollPane(tabelStok);
        add(scrollPane, BorderLayout.CENTER);

        // Aksi tombol
        btnRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tampilkanStokBarang();
            }
        });

        // Tampilkan data saat pertama kali
        tampilkanStokBarang();
    }

    private void tampilkanStokBarang() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Stok");

        try {
            Connection conn = koneksi.koneksiDB();
            String sql = "SELECT kode_barang, nama_barang, stok FROM barang";
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                model.addRow(new Object[]{
                    res.getString("kode_barang"),
                    res.getString("nama_barang"),
                    res.getInt("stok")
                });
            }
            tabelStok.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal menampilkan data: " + e.getMessage());
        }
    }

    // Untuk testing langsung class ini
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CekStok().setVisible(true);
        });
    }
}
