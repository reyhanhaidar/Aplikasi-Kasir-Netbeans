import io.prometheus.client.exporter.HTTPServer;
import io.prometheus.client.hotspot.DefaultExports;

public class MetricsServer {
    public static void main(String[] args) throws Exception {
        // Export JVM metrics, GC, Memory, etc.
        DefaultExports.initialize();

        // Start HTTP server on port 8080
        HTTPServer server = new HTTPServer(8080);

        System.out.println("Metrics server running on http://localhost:8080/metrics");
    }
}
