package cat.proven.formbind;

import javax.swing.SwingUtilities;

/**
 *
 * @author Jose
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame main = new MainFrame();
            main.setVisible(true); 
        });
    
    }
}
