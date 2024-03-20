
package cat.proven.masterdetail.views;

import cat.proven.masterdetail.model.Product;
import cat.proven.masterdetail.model.ProductModel;

public class MDPanel extends javax.swing.JPanel {

    public MDPanel() {
        initComponents();
        Product p = (new ProductModel())
                .getProductByCode(mPanel.getSelectedCode());
        if (p!= null) {
            dPanel.setElement(p);
        }
        mPanel.addPropertyChangeListener("selectedCode", (pce) -> {
            Product pSel = (new ProductModel())
                    .getProductByCode((String)pce.getNewValue());
            if (pSel!= null) {
                dPanel.setElement(pSel);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dPanel = new cat.proven.masterdetail.views.DPanel();
        mPanel = new cat.proven.masterdetail.views.MPanel();

        setLayout(new java.awt.BorderLayout());
        add(dPanel, java.awt.BorderLayout.CENTER);
        add(mPanel, java.awt.BorderLayout.WEST);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private cat.proven.masterdetail.views.DPanel dPanel;
    private cat.proven.masterdetail.views.MPanel mPanel;
    // End of variables declaration//GEN-END:variables
}