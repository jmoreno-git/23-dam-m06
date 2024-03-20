package cat.proven.components.guidatabrowser;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.lang.reflect.Field;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 *
 * @author Jose
 * @param <T>
 * @param <I>
 */
public class DataBrowser<T, I> extends JPanel {
    
    private final Model<T,I> model;
    private final Controller<T,I> controller;
    private final DataBrowser<T,I> view;
    
    private ActionListener listener;
    
    private T element;
    
    private Map<String, JFormattedTextField> textFields;
    
    public DataBrowser(List<T> data) {
        //
        textFields = new HashMap<>();
        //
        model = new Model();
        controller = new Controller();
        view = this; 
        //
        controller.setModel(model);
        controller.setView(view); 
        //
        listener = controller;
        element = data.get(0);
        initComponents();
        //
        model.addPropertyChangeListener(controller);
        model.addVetoableChangeListener(controller);
        //
        try {
            model.setData(data);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(DataBrowser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<T> getData() {
        return model.getData();
    }
    
    public void setData(List<T> data) {
        try {
            model.setSelectedIndex(0);
            model.setData(data);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(DataBrowser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
        showElement(element);
    }
    
    private void initComponents() {
        setLayout(new BorderLayout());
        //header
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());
            headerPanel.add(new JLabel("Data browser"));
        add(headerPanel, BorderLayout.NORTH);
        //form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new FlowLayout());
        formPanel.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GREEN, Color.yellow, Color.white, Color.white));
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        JLabel lb;
        Class c = element.getClass();
        JFormattedTextField tf;
        Field[] fields = c.getDeclaredFields();
        int row = 0;
        for (Field f : fields) {
            String fieldName = f.getName();
            try {
                boolean isAccessible = f.canAccess(element);
                f.setAccessible(true);
                Object fieldValue = (Object) f.get(element);
                //System.out.format("fieldValue class: %s%n", fieldValue.getClass().getName());
                f.setAccessible(isAccessible);
                //
                lb = new JLabel();
                String labelText = fromCamelCaseToDisplayableString(fieldName);
                lb.setText(labelText);
                    gbc.fill=GridBagConstraints.NONE;
                    gbc.gridx = 0;
                    gbc.gridy = row;
                    gbc.anchor=GridBagConstraints.WEST;
                    gbc.gridwidth=1;
                    gbc.weightx = 0.0;
                    gbc.insets=new Insets(10,0,10,10);
                formPanel.add(lb, gbc);  
                //
                //tf = new JFormattedTextField(fieldValue);
                tf = createFormattedTextField(f);
                //System.out.format("%s: %s%n", fieldName, f.getType().getName());
                textFields.put(fieldName, tf);
                    gbc.fill=GridBagConstraints.HORIZONTAL;
                    gbc.gridx = 1;
                    gbc.gridy = row;
                    gbc.anchor=GridBagConstraints.WEST;
                    gbc.gridwidth = GridBagConstraints.REMAINDER;
                    gbc.weightx = 1.0;
                    gbc.insets=new Insets(0,0,0,0);
                    tf.setValue(fieldValue);
                formPanel.add(tf, gbc); 
                row++;
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(DataBrowser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(DataBrowser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(DataBrowser.class.getName()).log(Level.SEVERE, null, ex);
            }      
        }
        //  
        add(formPanel, BorderLayout.CENTER);        
        //buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
            String [] btnTexts = {"<<", "<", ">", ">>"};
            JButton btn;
            //
            for (String btnText: btnTexts) {
                btn = new JButton(btnText);
                btn.setActionCommand(btnText);
                btn.addActionListener(listener);
                buttonPanel.add(btn);
            }
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    protected void showElement(T element) {
        if (element != null){
            Class c = element.getClass();
            Field[] fields = c.getDeclaredFields();
            for (Field f: fields) {
                try {
                    String fieldName = f.getName();
                    JFormattedTextField tf = textFields.get(fieldName);
                    boolean isAccessible = f.canAccess(element);
                    f.setAccessible(true);
                    T fieldValue = (T) f.get(element);
                    f.setAccessible(isAccessible);
                    tf.setValue(fieldValue);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(DataBrowser.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(DataBrowser.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static String[] splitByCapitalLetters(String input) {
        return input.split("(?=\\p{Upper})");
    }

    public static String fromCamelCaseToDisplayableString(String input) {
        String rv = "";
        for (String s : splitByCapitalLetters(input)) {
            rv += s + " ";
        }
        rv = rv.trim();
        if (Character.isLowerCase(rv.charAt(0))) {
            rv = Character.toUpperCase(rv.charAt(0)) + rv.substring(1, rv.length());
        }
        return rv;
    }  

    private JFormattedTextField createFormattedTextField(Field field) {
        JFormattedTextField tf;
        Class<?> fieldType = field.getType();
        //System.out.println(fieldType);
        if (fieldType == int.class || fieldType == Integer.class) {
            tf = new JFormattedTextField(NumberFormat.getIntegerInstance());
        } else if (fieldType == double.class || fieldType == Double.class) {
            tf = new JFormattedTextField(NumberFormat.getNumberInstance());
        } else if (fieldType == float.class || fieldType == Float.class) {
            tf = new JFormattedTextField(NumberFormat.getNumberInstance());
        } else {
            tf = new JFormattedTextField();
        }
        return tf;
    }
}
