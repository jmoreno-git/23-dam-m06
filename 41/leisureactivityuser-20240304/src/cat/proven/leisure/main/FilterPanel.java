package cat.proven.leisure.main;

/**
 *
 * @author alumne
 */
public class FilterPanel extends javax.swing.JPanel {

    /**
     * Creates new form FilterPanel
     */
    public FilterPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        activityFilterPanel = new cat.proven.leisure.main.ActivityFilterPanel();
        ageFilterPanel = new cat.proven.leisure.main.AgeFilterPanel();

        setLayout(new java.awt.CardLayout());
        add(activityFilterPanel, "card2");
        add(ageFilterPanel, "card3");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private cat.proven.leisure.main.ActivityFilterPanel activityFilterPanel;
    private cat.proven.leisure.main.AgeFilterPanel ageFilterPanel;
    // End of variables declaration//GEN-END:variables

    public ActivityFilterPanel getActivityFilterPanel() {
        return activityFilterPanel;
    }

    public AgeFilterPanel getAgeFilterPanel() {
        return ageFilterPanel;
    }
    
    public void setFilterByActivity() {
        ageFilterPanel.setVisible(false);
        activityFilterPanel.setVisible(true);
    }
    
    public void setFilterByAge() {
        ageFilterPanel.setVisible(true);
        activityFilterPanel.setVisible(false);
    }
    
}
