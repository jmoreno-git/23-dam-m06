package cat.proven.countriesgui;

import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author ProvenSoft
 */
public class CountriesFrame extends JFrame {

    private CountryPanel countryPanel;
    
    public CountriesFrame() {
        initComponents();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CountriesFrame frame = new CountriesFrame();
            frame.setVisible(true);
        });
    }

    private void initComponents() {
        setTitle("Countries DB GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        countryPanel = new CountryPanel();
        setContentPane(countryPanel);
        validate();
        setSize(400, 300);
    }
    
}
