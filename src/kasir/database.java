/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasir;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author WALIDHANIFATAULLAH
 */
public class database {

    public static Connection koneksiDB() {
        try {
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/manj_keuangan", "root", "");
            return koneksi;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}