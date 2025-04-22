/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasir;

import org.junit.Test;
import static org.junit.Assert.*;
import kasir.History;
import javax.swing.JTable;

/**
 *
 * @author ricky
 */
public class HistoryTest {
    
    public HistoryTest() {
        @Test
        public void testColumnNames() {
            History history = new History();
            JTable table = history.getTableHistory();

            String[] expectedColumns = {"Nama", "Kode Transaksi", "Tanggal", "Jam", "Total"};

            for (int i = 0; i < expectedColumns.length; i++) {
                String actual = table.getColumnName(i);
                assertEquals("Kolom ke-" + (i + 1) + " tidak sesuai", expectedColumns[i], actual);
            }
        }
    }
    
}
