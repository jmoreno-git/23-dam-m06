/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package cat.proven.leisure.main;

import javax.swing.JOptionPane;

/**
 *
 * @author alumne
 */
public class MainFrame extends javax.swing.JFrame {

    private String aboutMessage;

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        aboutMessage = "<html><p>Leisure manager application</p><p>(c) ProvenSoft 2024 v1.0</p></html>";
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

        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        exitItem = new javax.swing.JMenuItem();
        usersMenu = new javax.swing.JMenu();
        filterbyactivityItem = new javax.swing.JMenuItem();
        filterbyageItem = new javax.swing.JMenuItem();
        listsMenu = new javax.swing.JMenu();
        listbyactivityItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        fileMenu.setText("File");

        exitItem.setText("Exit");
        exitItem.setActionCommand("exit");
        exitItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                handleMenu(evt);
            }
        });
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);

        usersMenu.setText("Users");

        filterbyactivityItem.setText("Filter by activity");
        filterbyactivityItem.setActionCommand("filterbyactivity");
        filterbyactivityItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                handleMenu(evt);
            }
        });
        usersMenu.add(filterbyactivityItem);

        filterbyageItem.setText("Filter by age");
        filterbyageItem.setActionCommand("filterbyage");
        filterbyageItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                handleMenu(evt);
            }
        });
        usersMenu.add(filterbyageItem);

        menuBar.add(usersMenu);

        listsMenu.setText("Lists");

        listbyactivityItem.setText("By activity");
        listbyactivityItem.setActionCommand("listbyactivity");
        listbyactivityItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                handleMenu(evt);
            }
        });
        listsMenu.add(listbyactivityItem);

        menuBar.add(listsMenu);

        helpMenu.setText("Help");

        aboutItem.setText("About");
        aboutItem.setActionCommand("about");
        aboutItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                handleMenu(evt);
            }
        });
        helpMenu.add(aboutItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 277, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void handleMenu(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_handleMenu
        String action = evt.getActionCommand();
        if (action != null) {
            switch (action) {
                case "exit":
                    handleExit();
                    break;
                case "about":
                    handleAbout();
                    break;
                case "filterbyage":
                    handleFilterByAge();
                    break;
                case "listbyactivity":
                    break;
                case "filterbyactivity":
                    handleFilterByActivity();
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }//GEN-LAST:event_handleMenu

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        handleExit();
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutItem;
    private javax.swing.JMenuItem exitItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem filterbyactivityItem;
    private javax.swing.JMenuItem filterbyageItem;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem listbyactivityItem;
    private javax.swing.JMenu listsMenu;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu usersMenu;
    // End of variables declaration//GEN-END:variables
    private FilterUsersPanel filterUsersPanel;
    
    
    private void handleExit() {
        int answer = JOptionPane.showConfirmDialog(this, "Are you sure?", "Exit", JOptionPane.OK_CANCEL_OPTION);
        if (answer == JOptionPane.OK_OPTION) {
            System.exit(0);
        }
    }

    private void handleAbout() {
        JOptionPane.showMessageDialog(
                this, 
                aboutMessage, 
                "About", 
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void handleFilterByActivity() {
        filterUsersPanel = new FilterUsersPanel();
        setContentPane(filterUsersPanel);
        filterUsersPanel.getFilterPanel().setFilterByActivity();
        validate();
    }

    private void handleFilterByAge() {
        filterUsersPanel = new FilterUsersPanel();
        setContentPane(filterUsersPanel);
        filterUsersPanel.getFilterPanel().setFilterByAge();
        validate();
    }
}
