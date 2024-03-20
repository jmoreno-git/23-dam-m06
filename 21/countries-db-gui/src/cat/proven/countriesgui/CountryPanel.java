package cat.proven.countriesgui;

import cat.proven.countriesgui.model.Country;
import cat.proven.countriesgui.model.DbConnect;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ProvenSoft
 */
public class CountryPanel extends JPanel implements ActionListener {

    private ActionListener listener;
    private ResultSet resultSet;
    private Connection connection;
    
    public CountryPanel() {
//        try {
//            DbConnect.loadDriver();
//            connection = DbConnect.getConnection();
//            resultSet = retrieveDataFromDatabase(connection);
            listener = this;
            initComponents();
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(CountryPanel.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(CountryPanel.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        JLabel header = new JLabel("Country form");
        header.setHorizontalAlignment(JLabel.CENTER);
        header.setBorder(new EmptyBorder(5,5,5,5));
        //header.setBorder(new BevelBorder(BevelBorder.LOWERED));
        add(header, BorderLayout.NORTH);
        //country form panel (data panel)
        JPanel formPanel = createFormPanel();
        add(formPanel, BorderLayout.CENTER);
        //button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton btn;
            //
            btn = createButton("CONN", "connect");
            buttonPanel.add(btn);
            //
            btn = createButton("<<", "first");
            buttonPanel.add(btn);
            //
            btn = createButton("<", "previous");
            buttonPanel.add(btn);            
            //
            btn = createButton(">", "next");
            buttonPanel.add(btn);            
            //
            btn = createButton(">>", "last");
            buttonPanel.add(btn);            
            //
            btn = createButton("INS", "insert");
            buttonPanel.add(btn); 
            //
            btn = createButton("UPD", "update");
            buttonPanel.add(btn);
            //
            btn = createButton("DEL", "delete");
            buttonPanel.add(btn);            
            //
        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String action = e.getActionCommand();
            switch (action) {
                case "connect" -> handleConnect();
                case "first" -> handleFirst();
                case "previous" -> handlePrevious();
                case "next" -> handleNext();
                case "last" -> handleLast();
                case "insert" -> handleInsert();
                case "update" -> handleUpdate();
                case "delete" -> handleDelete();
                default -> throw new AssertionError();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error querying database", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleFirst() {
        //TODO
        JOptionPane.showMessageDialog(this, "Moving to first", "Handling first", JOptionPane.INFORMATION_MESSAGE);        
    }
    
    private JButton createButton(String text, String actionCommand) {
        JButton button = new JButton(text);
        button.setActionCommand(actionCommand);
        button.addActionListener(listener);
        return button;
    }

    private void handlePrevious() {
        //TODO
        JOptionPane.showMessageDialog(this, "Moving to previous", "Handling previous", JOptionPane.INFORMATION_MESSAGE);        
    }

    private void handleNext() throws SQLException {
        //JOptionPane.showMessageDialog(this, "Moving to next", "Handling next", JOptionPane.INFORMATION_MESSAGE);        
        if (!resultSet.isLast()) {
            resultSet.next();
        }
        Country country = countryFromResultSet(resultSet);
        displayCountry(country);
    }

    private void handleLast() {
        //TODO
        JOptionPane.showMessageDialog(this, "Moving to last", "Handling last", JOptionPane.INFORMATION_MESSAGE);        
    }

    private void handleInsert() {
        //TODO
        JOptionPane.showMessageDialog(this, "Inserting", "Handling insert", JOptionPane.INFORMATION_MESSAGE);        
    }

    private void handleUpdate() {
        //TODO
        JOptionPane.showMessageDialog(this, "Updating", "Handling update", JOptionPane.INFORMATION_MESSAGE);        
    }

    private void handleDelete() {
        //TODO
        JOptionPane.showMessageDialog(this, "Deleting", "Handling delete", JOptionPane.INFORMATION_MESSAGE);        
    }

    private ResultSet retrieveDataFromDatabase(Connection connection) throws SQLException {
        final String sql = "select * from countries";
        Statement st = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = st.executeQuery(sql);
        rs.first();
        return rs;
    }

    private Country countryFromResultSet(ResultSet resultSet) throws SQLException {
        Country country;
        //retrieve the values of the fields.
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String capital = resultSet.getString("capital");
        double surface = resultSet.getDouble("surface");
        int inhabitants = resultSet.getInt("inhabitants");
        double pib = resultSet.getDouble("pib");
        int lifeexpectancy = resultSet.getInt("lifeexpectancy");
        //instantiate the object.
        country = new Country(id, name, capital, surface, inhabitants, pib, lifeexpectancy);
        return country;
    }

    private void displayCountry(Country country) {
        //TODO
        System.out.println(country.toString());
    }

    private void handleConnect() {
        try {
            DbConnect.loadDriver();
            connection = DbConnect.getConnection();
            resultSet = retrieveDataFromDatabase(connection);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Error: database driver not found", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error connecting to database", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JPanel createFormPanel() {
        JPanel form = new JPanel();
        form.setLayout(new GridLayout(0, 2));
        JLabel lb;
        //
        lb = new JLabel("name: ");
        form.add(lb);
        form.add(new JLabel());
        //
        lb = new JLabel("capital: ");
        form.add(lb);
        form.add(new JLabel());
        //
        lb = new JLabel("surface: ");
        form.add(lb);
        form.add(new JLabel());
        //
        lb = new JLabel("inhabitants: ");
        form.add(lb);
        form.add(new JLabel());
        //
        lb = new JLabel("pib: ");
        form.add(lb);
        form.add(new JLabel());
        //
        lb = new JLabel("life expectancy: ");
        form.add(lb);
        form.add(new JLabel());
        //
        return form;
    }
    
}
