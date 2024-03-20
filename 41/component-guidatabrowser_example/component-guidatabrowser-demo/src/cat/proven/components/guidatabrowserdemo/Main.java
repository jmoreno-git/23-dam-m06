package cat.proven.components.guidatabrowserdemo;

import cat.proven.components.guidatabrowser.DataBrowser;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Jose.
 */
public class Main {

    private DataBrowser dataBrowser;
    
    public static void main(String[] args) {
        Main ap = new Main();
        ap.run();
    }
    
     private void run() {
        SwingUtilities.invokeLater(() -> {
            initGui();
        });
     }  
     
    private void initGui() {
        JFrame frame = new JFrame("GUI component databrowser demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = frame.getContentPane();
        List<Friend> data = loadTestData1(); //simulate database access
        //List<Product> data = loadTestData2(); //simulate database access
        dataBrowser = new DataBrowser<>(data);
        pane.add(dataBrowser);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private List<Friend> loadTestData1() {
        List<Friend> d = new ArrayList<>();
        IntStream.range(1, 15)
            .boxed()
            .forEach((Integer t) -> {
                Friend p = new Friend(
                    String.valueOf(555555500+t),
                    String.format("name%02d", t),
                    20+t
                );
                d.add(p);
            });
        return d;
    }

    private List<Product> loadTestData2() {
        List<Product> d = new ArrayList<>();
        IntStream.range(1, 15)
            .boxed()
            .forEach((Integer t) -> {
                Product p = new Product(
                    t,
                    String.valueOf(100+t),
                    String.format("description%02d", t),
                    100.0+t
                );
                d.add(p);
            });
        return d;
    }
    
}
