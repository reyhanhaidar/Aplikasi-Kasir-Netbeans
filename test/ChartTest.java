import org.jfree.chart.ChartPanel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import kasir.Laporan;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 *
 * @author haida
 */
public class ChartTest {

    private Connection mockKoneksi;
    private PreparedStatement mockPst;
    private ResultSet mockRst;
    private JPanel chartPanel;
    

    private Laporan classUnderTest; 

    @Before
public void setUp() throws Exception {
    // Create the object under test
    classUnderTest = new Laporan();

    // Mock JDBC
    mockKoneksi = mock(Connection.class);
    mockPst = mock(PreparedStatement.class);
    mockRst = mock(ResultSet.class);

    // Set the mocked connection and chartPanel
    classUnderTest.setKoneksi(mockKoneksi);

    // Mock komponen GUI (Swing)
    chartPanel = spy(new JPanel());
    classUnderTest.setChartPanel(chartPanel);

    // Setup mock JDBC behavior
    when(mockKoneksi.prepareStatement(anyString())).thenReturn(mockPst);
    when(mockPst.executeQuery()).thenReturn(mockRst);
}


    @Test
    public void testTampilkanChart() throws Exception {
        // Simulasi hasil query
        when(mockRst.next()).thenReturn(true, true, false);
        when(mockRst.getString("Tanggal")).thenReturn("2025-04-01", "2025-04-02");
        when(mockRst.getInt("Jumlah")).thenReturn(7, 9);

        classUnderTest.tampilkanChart();

        // Verifikasi GUI dirender
        verify(chartPanel).removeAll();
        verify(chartPanel).setLayout(any(BorderLayout.class));
        verify(chartPanel).add(any(ChartPanel.class), eq(BorderLayout.CENTER));
        verify(chartPanel).validate();
    }
}
