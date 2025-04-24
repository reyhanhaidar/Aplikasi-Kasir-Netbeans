/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasir;

import org.junit.Test;
import static org.junit.Assert.*;
import javax.swing.JTable;

/**
 *
 * @author ricky
 */
public class HistoryTest {
    
        
    @Test
    public void testColumnNames() {
        History history = new History();
        JTable table = history.getTableHistory();

        String[] expectedColumns = {"Nama Makanan", "Kode Transaksi", "Tanggal", "Jam", "Total"};

        for (int i = 0; i < expectedColumns.length; i++) {
            String actual = table.getColumnName(i);
            assertEquals("Kolom ke-" + (i + 1) + " tidak sesuai", expectedColumns[i], actual);
        }
    }
    
    @Test
    public void testColumnSize() {
        History history = new History();
        JTable table = history.getTableHistory();
        
        int jumlahKolom = 5;
        
        assertEquals("Jumlah kolom tidak sesuai, kolom seharusnya berjumlah " + jumlahKolom, jumlahKolom, history.getTableHistory().getColumnCount());
    }
    
}
